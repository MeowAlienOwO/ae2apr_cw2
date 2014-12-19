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
//     Update #: 199
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

    public static String CreateBOOK(){
	return (Datapool.BOOK + "\n");
    }

    public static String CreateCancel(int id){
	return (Datapool.CANCEL + " " + String.valueOf(id) + "\n");
    }

    public static String CreateLogin(String port, String host, String usrname, String passwd){
	return (Datapool.LOGIN + " " + port + " " + host + " " + usrname + " " + passwd + "\n");
    }

    public static String CreateTrade(String type, String company, String volume, String price){
	return (type + " " + company + " " + volume + " " + price + "\n");
    }
       
    // variable
    private LinkedList<String> serverInfor;
    private LinkedList<String> commands;
    private LinkedList<TradeInfor> tradeInforList;
    private LinkedList<DataObserver> observers;
    private LoginInfor loginInfor;
    private Socket socket;
    private Reader reader;
    private Writer writer;

    private static Datapool datapool;
    
    // constructor
    private Datapool(){
	    this.loginInfor = null;
	    this.serverInfor   = new LinkedList<String>();
	    this.commands  = new LinkedList<String>();
	    this.observers  = new LinkedList<DataObserver>();
    }
    // setter

    public void setServerInfor(String msg){
	serverInfor.add(msg);
    }

    public void setCommands(String msg){
        commands.add(msg);
    }

    public void setLoginInfor(LoginInfor infor){
	this.loginInfor = infor;
    }

   
    // getter

    public static Datapool getDatapool(){
       if(Datapool.datapool != null){
	   Datapool.datapool = new Datapool();
       }
       return Datapool.datapool;
    }
    
    public LinkedList<String> getServerInfor(){
	return serverInfor;
    }

    public LinkedList<TradeInfor> getTradeInfor(){
	return tradeInforList;
    }

    // method
    @Override
    public void attach(DataObserver o){
        observers.add(o);
    };
    
    @Override
    public void detach(DataObserver o){
	observers.remove(o);
    };

    @Override
    public void notifyObservers(){
	for(int i = 0; i < observers.size(); i++){
	    observers.get(i).update();
	}
    }

    public void work(){
	while(true){
	    try {
		synchronized(commands){
		    if(!commands.isEmpty()) executeCommands();
		}
	    }catch (ServerErrorException see) {
		
	    }catch (UnknownHostException uhe){

	    }catch (IOException ioe){

	    }
	    
	    notifyObservers();
	    
	}
	
    }


    public void executeCommands() throws ServerErrorException, UnknownHostException, IOException {
	
	String command;
	while((command = commands.poll()) != null){

	    String[] splited = command.split(" ");
	    
	    // login
	    if(splited[0].equals(Datapool.LOGIN)){
		// login infor has a special format,
		// for it need to store the host information.
		// this command should be created by Datapool's
		// static methods.
		logIn(new LoginInfor(Integer.parseInt(splited[1]),
				     splited[2],
				     splited[3],
				     splited[4]));
	    }
	    // logout
	    if(splited[0].equals(Datapool.LOGOUT)){
		logOut();
	    }

	    // trade
	    if(splited[0].equals(Datapool.ASK) ||
	       splited[0].equals(Datapool.BID)){
		writer.write(command);
		String id = confirm();
		tradeInforList.add(new TradeInfor(
						  splited[0],
						  splited[1],
						  splited[2],
						  splited[3],
						  id ));
	    }
		    
	    // cancel
	    if(splited[0].equals(Datapool.CANCEL)){
		writer.write(command);
		confirm();
		refresh();
		// deleteTrade(Integer.parseInt(splited[1]));
	    }

	    // book
	    if(splited[0].equals(Datapool.BOOK)){
		refresh();
	    }
	}
       
    }
    
    public void logIn(LoginInfor infor) throws ServerErrorException, UnknownHostException, IOException{
	

	    setLoginInfor(infor);
	    this.socket = new Socket(infor.getHost(), infor.getPort());
	    this.reader = new Reader(socket.getInputStream(), this);
	    this.writer = new Writer(socket.getOutputStream());
	    writer.write(infor.toString());
	    confirm();
	
    }

    public void logOut() throws IOException{
	setLoginInfor(null);

	writer.write("LOGOUT\n");
	this.reader = null;
	this.writer = null;
	socket.close();
	
    }
    
    public void deleteTrade(int id) {
	Iterator<TradeInfor> itr = tradeInforList.iterator();

	while(itr.hasNext()){
	    if(itr.next().getID() == id){
		itr.remove();
		break;
	    }
	}
    }

    public void refresh() throws ServerErrorException, IOException{

	
	writer.write(Datapool.BOOK + "\n");
	List<String> books = reader.readBook();
	// BOOK return format:
	// BOOK id company volume price type\n
	if(!books.isEmpty()){
	    tradeInforList = new LinkedList<TradeInfor>();
	    Iterator<String> itr = books.iterator();
	    while(itr.hasNext()){
		String[] splited = itr.next().split(" ");
		tradeInforList.add(new TradeInfor(
				  splited[5], // type
				  splited[2], // company
				  splited[3], // volume
				  splited[4], // price
				  splited[1]  // id
						  ));
	    }

	  
	}
	
    }

    public String confirm() throws ServerErrorException, IOException{

	String[] splited = reader.readFeedback().split(" ");
	
	return splited.length > 1? splited[0] : splited[1];

    }


    public boolean isLoggedIn(){
	return loginInfor != null;
    }

}
// 
// Datapool.java ends here
