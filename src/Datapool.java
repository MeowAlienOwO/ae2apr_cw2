// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// Datapool.java --- 
// Filename: Datapool.java
// Description: 
// Author: Zhang Huayan
// ID number: 6511043
// E-mail: zy11043@nottingham.edu.cn / MeowAlienOwO@gmail.com
// Version: 
// 

// Commentary: 
// 
// 

// Change Log:
// Status: 
// Table of Contents: 
// 
//     Update #: 440
// 

// Code:

package stock;

import java.util.*;
import java.net.*;
import java.io.*;

class Datapool implements DataObservable{
    /**
     * Datapool is the main part for process all the information.
     * The GUI part's deployment is depend on the data in datapool
     * 
     * All operations in GUI will be regard as executable commands,
     * these will be stored in the commands list. 
     * For each command, the client should send it to server and then
     * get the server's feedback for further processing.
     * 
     * Server will send three kind of information: Tick/Exec, confirm
     * information on commands, and Error messages.
     * When the reader get the information, it should divide the information
     * into three part. If it is Tick/Exec message, automatically add into 
     * server infor; if is feedback, return the string to futer processing;
     * if is error, throw expection. 
     */
    // constants related to protocal
    public static final String ERROR  = "ERROR";
    public static final String LOGIN  = "LOGIN";
    public static final String LOGOUT = "LOGOUT";
    public static final String ASK    = "ASK";
    public static final String BID    = "BID";
    public static final String BOOK   = "BOOK";
    public static final String CANCEL = "CANCEL";
    public static final String TICK   = "TICK";
    public static final String EXEC   = "EXEC";
    public static final String CONFIRM = "OK";
    public static final int    PROTOCAL_VERSION = 1;

    public static String CreateBOOK(){
	return (Datapool.BOOK + "\n");
    }

    public static String CreateCancel(String id){
	return (Datapool.CANCEL + " " + id + "\n");
    }

    public static String CreateLogin(String port, String host, String usrname, String passwd){
	return (Datapool.LOGIN + " " + port + " " + host + " " + usrname + " " + passwd + "\n");
    }

    public static String CreateTrade(String type, String company, String volume, String price){
	return (type + " " + company + " " + volume + " " + price + "\n");
    }
    private static Datapool datapool;       
    // variable
    private LinkedList<String> serverInfor;
    private LinkedList<String> commands;
    private Vector<Vector<String>> books;
    private LinkedList<DataObserver> observers;
    private LinkedList<Exception> errors;
    private LoginInfor loginInfor;
    private Socket socket;
    private Reader reader;
    private Writer writer;
    private boolean loggedin;
    private boolean bookChanged;

    
    // constructor
    private Datapool(){
	this.loggedin = false;
	this.bookChanged  = false;
	this.loginInfor = null;
	this.serverInfor   = new LinkedList<String>();
	this.commands  = new LinkedList<String>();
	this.errors = new LinkedList<Exception>();
	this.observers  = new LinkedList<DataObserver>();
	this.books     = new Vector<Vector<String>>();
    }
    // setter

    public void setServerInfor(String msg){
	synchronized(serverInfor){
	    serverInfor.add(msg);
	}
    }

    public void setCommands(String msg){
	synchronized(commands){
	    commands.add(msg);
	}
    }

    public void setLoginInfor(LoginInfor infor){
	this.loginInfor = infor;
    }

    public void setBooks(LinkedList<String> inputBooks){
	synchronized(books){
	    books.clear();
	    for(int i = 0; i < inputBooks.size(); i++){
		String[] splited = inputBooks.get(i).split(" ");
		Vector<String> book = new Vector<String>();
		for(int j = 1; j < splited.length; j++){
		    book.add(splited[j]);
		}
		books.add(book);
	    }
	}

	this.bookChanged = true;
    }

    
    public void addException(Exception e){
	synchronized(errors){
	    errors.add(e);
	}
    }

    public void setBookChanged(boolean changed){
	
	    this.bookChanged = changed;
	
    }
    // getter

    public static Datapool getDatapool(){
       if(Datapool.datapool == null){
	   Datapool.datapool = new Datapool();
       }
       return Datapool.datapool;
    }
    
    public LinkedList<String> getServerInfor(){
	synchronized(serverInfor){
	    return serverInfor;
	}
    }
    public Vector<Vector<String>> getBooks(){
	synchronized(books){
	    return books;
	}
    }
    public LinkedList<String> getCommands(){
	synchronized(commands){
	    return commands;
	}
    }
    public LinkedList<Exception> getException(){
	synchronized(errors){
	    return errors;
	}
    }

    // method
    @Override
    public void attach(DataObserver o){
        observers.add(o);
    }
    
    @Override
    public void detach(DataObserver o){
	observers.remove(o);
    }

    @Override
    public void notifyObservers(){
	for(int i = 0; i < observers.size(); i++){
	    observers.get(i).update();
	}
    }


    public void printBook(){

    	for(int i = 0; i < books.size(); i++){
    	    for(int j = 0; j < books.get(i).size(); j++){
    		System.out.print(books.get(i).get(j));
    		System.out.print(" ");
    	    }
    	    System.out.println();
    	}
    }


    public void work(){
	System.out.println("Working...");

	while(true){
	    try {
		Thread.sleep(1000);		
	    }
	    catch (Exception e) {
		System.out.println("Error " + e.getMessage());
		e.printStackTrace();
	    }

	    if(isLoggedIn() && isChanged()){

		refresh();
		notifyObservers();
		// setBookChanged(false);

	    }
	    
	}
	
    }

    public void refresh(){
	commands.add(Datapool.BOOK + "\n");
	setBookChanged(false);
    }

    
    public void logIn(LoginInfor infor) throws ServerErrorException, UnknownHostException, IOException{
	System.out.println("log in start");
	this.socket = new Socket(infor.getHost(), infor.getPort());
	this.reader = new Reader(socket.getInputStream(), this);
	this.writer = new Writer(socket.getOutputStream(), this);
	System.out.println("login infor:" + infor.toString());
	writer.write(infor.toString());
	System.out.println(reader.readFeedback());
	
	loggedin = true;
	Thread readerThread = new Thread(reader);
	Thread writerThread = new Thread(writer);
	
	readerThread.start();
	writerThread.start();
	refresh();
	    
    }

    public void logOut() throws IOException{
	
	loggedin = false;
	writer.write("LOGOUT\n");
	this.reader = null;
	this.writer = null;
	socket.close();
	
    }
    
    public boolean isLoggedIn(){
	return loggedin;
    }
    
    public boolean isChanged(){
	
	    return bookChanged         ||
		!serverInfor.isEmpty() ||
		!errors.isEmpty();
	
    }
}
// 
// Datapool.java ends here
