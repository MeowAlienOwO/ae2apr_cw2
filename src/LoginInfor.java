// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// LoginInfor.java --- 
// Filename: LoginInfor.java
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
//     Update #: 17
// 

// Code:
package stock;


class LoginInfor{
    // variables
    private int port;
    private String host;
    private String usrname;
    private String passwd;

    // constructor
    LoginInfor(int port, String host, String usrname, String passwd){
	setPort(port);
	setHost(host);
	setUsrname(usrname);
	setPasswd(passwd);
    }

    // setter
    public void setPort(int port){

	this.port = port;

    }

    public void setHost(String host){
	this.host = host;
    }

    public void setUsrname(String usrname){
	this.usrname = usrname;
    }

    public void setPasswd(String passwd){
	this.passwd = passwd;
    }

    // getter
    public int getPort(){
	return port;
    }

    public String getHost(){
	return host;
    }

    public String getUsrname(){
	return usrname;
    }

    public String getPasswd(){
	return passwd;
    }
    // method
    @Override
    public String toString(){
	return ( Datapool.LOGIN + " "
		 + Datapool.PROTOCAL_VERSION + " "
	       + usrname        + " "
	       + passwd         + "\n");
    }
}


// 
// LoginInfor.java ends here
