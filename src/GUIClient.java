// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// GUIClient.java --- 
// Filename: GUIClient.java
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
//     Update #: 40
// 

// Code:





package stock;


public class GUIClient{

    public static void main(String[] args){

	try {


	    if(args.length != 0) throw new Exception("Illegal argument");


	GUIClient client = new GUIClient();
	client.initialize();

	// while(client.running){

	//     client.update();
	// }

	}
	catch (Throwable e) {
	    System.out.println("Error " + e.getMessage());
	    e.printStackTrace();
	}
    }
    // variables
    private boolean running = true;
    private int port = 1234;
    private MainFrame mainframe;
    private Datapool datapool;
    // methods
    private void initialize(){

	datapool = Datapool.getDatapool();
	
	mainframe = MainFrame.getMainFrame(datapool);
		
    }

    private void update(){
	// datapool.update();
	
	// .update();
	
    }

    private boolean running(){
	return true;
    }


}
// 
// GUIClient.java ends here
