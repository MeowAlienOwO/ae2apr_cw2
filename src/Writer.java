// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// Writer.java --- 
// Filename: Writer.java
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
//     Update #: 45
// 

// Code:

package stock;


import java.net.*;
import java.io.*;


class Writer implements Runnable{

    // variables
    
    
    OutputStreamWriter osw;
    Datapool datapool;
    
    // constructor
    Writer(OutputStream os, Datapool datapool){
	osw = new OutputStreamWriter(os);
	this.datapool = datapool;
    }
    // setter 
    
    // getter

    // method
    public void write(String msg) throws IOException{
	System.out.println("write called: " + msg);
	osw.write(msg);
	osw.flush();
    }


    public void run(){
	try {

	    while(datapool.isLoggedIn()){
		String command;
		System.out.println(datapool.getCommands().isEmpty());
		if((command = datapool.getCommands().poll()) != null){
		    System.out.println("write command!");
		    write(command);
		}

	    }	    
	}
	catch (IOException ioe) {
	    datapool.addException(ioe);
	}

    }
}

// 
// Writer.java ends here
