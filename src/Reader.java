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
//     Update #: 15
// 

// Code:




package stock;


import java.io.*;
import java.util.*;
class Reader {


    // variables

    private BufferedReader br;
    
    // constructor
    Reader(InputStream is){	
	br = new BufferedReader(new InputStreamReader(is));
    }
    

    // setter
    

    // getter

    // methods

    public LinkedList<String> read(){
	

	LinkedList<String> res;
	String line;
	while((line = br.readLine()) != null){
	    res.add(line);
	}
	return res;
    }

    public String readLine(){
	
    }

}
// 
// Reader.java ends here
