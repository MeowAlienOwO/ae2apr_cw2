//                              -*- Mode: Java -*- 
// DataObservable.java --- 
// Filename: DataObservable.java
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
//     Update #: 7
// 

// Code:

package stock;
interface DataObservable{

    public void attach(DataObserver o);
    public void detach(DataObserver o);
    public void notifyObservers();

}


// 
// DataObservable.java ends here
