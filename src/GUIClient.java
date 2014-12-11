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
//     Update #: 14
// 

// Code:





package stock;


public class GUIClient{

    private boolean running = true;
    private int port = 1234;
    
    public static void main(String[] args){

	GUI gui = GUI.getGUI();
	gui.update();
	// initialize();

	// while(running){

	//     update();
	// }
	
	
	// end();
    }


    private void initialize(){
	
	

	// connect();
    }

    private void update(){

    }

    private void end(){

    }

    private boolean running(){
	return true;
    }


}
// 
// GUIClient.java ends here
