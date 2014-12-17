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
//     Update #: 29
// 

// Code:

package stock;


import java.net.*;
import java.io.*;


class Writer{

    // variables
    
    
    OutputStreamWriter osw;
    // Datapool datapool;
    
    // constructor
    Writer(OutputStream os){
	osw = new OutputStreamWriter(os);
    }
    // setter 
    
    // getter

    // method
    public void write(String msg){
	osw.write(msg);
	osw.flush();
	
    }

}

// 
// Writer.java ends here
