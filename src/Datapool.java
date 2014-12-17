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
//     Update #: 75
// 

// Code:

package stock;

import java.util.*;
import java.net.*;
import java.io.*;
class Datapool implements DataObservable{
    // variable
    private LinkedList<String> inputMsg;
    private LinkedList<String> outputMsg;
    private LinkedList<DataObserver> observers;
    private LoginInfor loginInfor;
    private LinkedList<TradeInfor> tradeInforList;
    private Socket socket;
    private Reader reader;
    private Writer writer;
    private static Datapool datapool;
    
    // constructor
    private Datapool(){
	    this.loginInfor = null;
	    this.inputMsg   = new LinkedList<String>();
	    this.outputMsg  = new LinkedList<String>();
	    this.observers  = new LinkedList<DataObserver>();
    }
    // setter

    public void setInputMsg(String msg){
	inputMsg.add(msg)
    }

    public void setOutputMsg(String msg){

        outputMsg.add(msg);

    }

    public void setLoginInfor(LoginInfor infor){
	this.loginInfor = infor;
    }

   
    // getter

    public Datapool getDatapool(){
       if(Datapool.datapool != null){
	   Datapool.datapool = new Datapool();
       }
       return Datapool.datapool;
    }


    // method
    @Override
    public void attach(DataObserver o){
        observers.add(o);
    };
    
    @Override
    public void detach(DataObserver o){
	observers.delete(o);
    };

    @Override
    public void notifyObservers(){
	for(int i = 0; i < observers.Size(); i++){
	    observers.get(i).update();
	}
    }

    public void work(){
	while(true){
	    try {
		if(!isLoggedIn()) continue;
	    
		if(!inputMsg.isEmpty()) executeInputMsg();
		if(!outputMsg.isEmpty()) executeOutputMsg();
	    
	
	    	
	    }
	    catch (Throwable e) {
		
	    }
	    notifyObservers();
	    
	}
	
    }

    
    public void logIn(LoginInfor infor){
	try {

	    setLoginInfor(infor);
	    this.socket = new Socket(infor.getHost(), infor.getPort());
	    this.reader = new Reader(socket.getInputStream());
	    this.writer = new Writer(socket.getOutputStream());
	}
	catch (Throwable e) {
	    System.out.println("Error " + e.getMessage());
	    e.printStackTrace();
	}
	
    }

    public void logOut(){
	setLoginInfor(null);

	writer.write("LOGOUT\n");
	this.reader = null;
	this.writer = null;
	
    }

    public void addInputMsg(String msg){
	inputMsg.add(msg);
    }

    public void addOutputMsg(String msg){
	outputMsg.add(msg);
    }

    public boolean isLoggedIn(){
	return loginInfor != null;
    }

}
// 
// Datapool.java ends here
