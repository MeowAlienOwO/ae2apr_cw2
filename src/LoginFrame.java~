// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// LoginFrame.java --- 
// Filename: LoginFrame.java
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
//     Update #: 73
// 

// Code:

package stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginFrame{
    
    private JFrame frame;
    private JTextField server, usrname;
    private JPasswordField passwd;
    private JLabel serverLabel, usrLabel, passwdLabel;
    private JPanel panel, serverPanel, usrPanel, passwdPanel, buttonPanel;
    private JButton login, cancel;

    LoginFrame(){
	this.frame       = new JFrame("Log in");
	this.server      = new JTextField("address-string:port-number");
	this.usrname     = new JTextField("username");
	this.passwd      = new JPasswordField(6);
	this.serverLabel = new JLabel("Server:");
	this.usrLabel    = new JLabel("Username:");
	this.passwdLabel = new JLabel("Password:");
	this.login       = new JButton("Login");
	this.cancel      = new JButton("Cancel");
	

	this.serverPanel = new JPanel();
	serverPanel.add(serverLabel);
	serverPanel.add(server);
	
	this.usrPanel    = new JPanel();
	usrPanel.add(usrLabel);
	usrPanel.add(usrname);
	this.passwdPanel = new JPanel();
	passwdPanel.add(passwdLabel);
	passwdPanel.add(passwd);
	this.buttonPanel = new JPanel();
	buttonPanel.add(login);
	buttonPanel.add(cancel);

	this.panel = new JPanel(new GridLayout(4, 1));
	panel.add(serverPanel);
	panel.add(usrPanel);
	panel.add(passwdPanel);
	panel.add(buttonPanel);
	frame.add(panel);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.pack();
	frame.setVisible(true);

	cancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){

		    frame.dispose();
		}
	    });
    }

}

// 
// LoginFrame.java ends here
