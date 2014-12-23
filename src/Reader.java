// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// Reader.java --- 
// Filename: Reader.java
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
//     Update #: 129
// 

// Code:




package stock;


import java.io.*;
import java.util.*;
class Reader implements Runnable{


    // variables

    private BufferedReader br;
    private Datapool datapool;
    // constructor
    Reader(InputStream is, Datapool datapool){	
	br = new BufferedReader(new InputStreamReader(is));
	this.datapool = datapool;
    }
    

    // setter
    

    // getter

    // methods

    public String readFeedback() throws ServerErrorException, IOException{
    	String line;

    	while((line = br.readLine()) != null){
		
    	    String[] splited = line.split(" ");
    	    if(isError(splited[0]))
    		throw new ServerErrorException(line);

    	    else if(isFeedback(splited[0])) break;
	    
    	    else if(isServerMsg(splited[0])){
    		datapool.setServerInfor(line);
    	    }

    	}
	
    	return line;
    }

    // public String readLine() throws IOException{
    // 	String line = br.readLine();
    // 	System.out.println("readline: " + line);
    // 	return line;

    // }

    // public List<String> readBook() throws ServerErrorException, IOException{
    // 	LinkedList<String> books = new LinkedList<String>();

    // 	String line;

    // 	while((line = br.readLine()) != null){
    // 	    String[] splited = line.split(" ");
    // 	    if(isError(splited[0]))
    // 		throw new ServerErrorException(line);

    // 	    if(splited[0].equals(Datapool.BOOK))
    // 		books.add(line);
    // 	}

    // 	return books;
    // }


    @Override
    public void run(){
	System.out.println("reader start");
	while(datapool.isLoggedIn()){

	    System.out.println("reader running");
	    String line;
	    
	    try{
		while((line = br.readLine()) != null){
		    // System.out.println(line);
		    String[] splited = line.split(" ");

		    if(isError(splited[0]))
			throw new ServerErrorException(line);
		    if(isFeedback(splited[0])) 
			continue;
		    if(isServerMsg(splited[0])){
			datapool.setServerInfor(line);
		    }

		    if(isBook(splited[0])){
			LinkedList<String> books = new LinkedList<String>();
			books.add(line);
			while((line = br.readLine()) != null
			      && isBook(line.split(" ")[0])){

			    // assume the feedback of book is not been separated
			    // by other command.
			    System.out.println(line);
			    books.add(line);
			}
			
			datapool.setBooks(books);
		    }

		}		
	    }catch(ServerErrorException see){
		
		    datapool.addException(see);
		
	    }catch(IOException ioe){
		
		    datapool.addException(ioe);
		
	    }
	    

	}
	System.out.println("reader end");
    }

    public boolean isError(String input){
	return input.equals(Datapool.ERROR);
    }

    public boolean isServerMsg(String input){
	return input.equals(Datapool.TICK)
	    || input.equals(Datapool.EXEC);
    }

    public boolean isFeedback(String input){
	return input.equals(Datapool.CONFIRM);
    }

    public boolean isBook(String input){
	return input.equals(Datapool.BOOK);
    }

}

class ServerErrorException extends Exception {
    public ServerErrorException() {
	super();
    }
    public ServerErrorException(String msg) {
	super(msg);
    }
    public ServerErrorException(String msg, Throwable cause) {
	super(msg, cause);
    }
    public ServerErrorException(Throwable cause) {
	super(cause);
    }

}

// 
// Reader.java ends here
