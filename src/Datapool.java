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
//     Update #: 29
// 

// Code:

package stock;

import java.util.*;

class Datapool implements DataObservable{
    // variable
    private LinkedList<String> inputMsg;
    private LinkedList<String> outputMsg;
    private LinkedList<Observer> observers;
    private boolean login;
    private static Datapool datapool;
    
    // constructor
    private Datapool(){
	    this.login = false;
	    this.inputMsg = new LinkedList<String>();
	    this.outputMsg = new LinkedList<String>();
	    
    }
    // setter

    public void setInputMsg(String msg){
	    inputMsg.add(msg)
    }

    public void setOutputMsg(String msg){
	
	    outputMsg.add(msg);

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
    public void detach(DataObserver o){};
        observers.delete(o);
    @Override
	public void notifyObservers(){

    }

    public void update(){
	
    }

}
// 
// Datapool.java ends here
