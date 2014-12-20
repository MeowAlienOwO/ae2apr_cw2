// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// TradeFrame.java --- 
// Filename: TradeFrame.java
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
//     Update #: 57
// 

// Code:
package stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class TradeFrame{

    private String type;
    private JFrame frame;
    private JPanel panel, companyPanel, volumePanel, pricePanel, buttonPanel;
    private JTextField company, volume, price;
    private JLabel companyLabel, volumeLabel, priceLabel;
    private JButton ok, cancel;
    private Datapool datapool;

    TradeFrame(String type, Datapool datapool){

	setType(type);
	this.datapool = datapool;

	
	this.company = new JTextField(20);
	this.volume  = new JTextField(20);
	this.price   = new JTextField(20);
	this.companyLabel = new JLabel("Company:");
	this.volumeLabel  = new JLabel("Volume:");
	this.priceLabel   = new JLabel("Price:");
	this.ok = new JButton("OK");
	this.cancel = new JButton("Cancel");
	
	this.companyPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	companyPanel.add(companyLabel);
	companyPanel.add(company);
	this.volumePanel  = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	volumePanel.add(volumeLabel);
	volumePanel.add(volume);
 	this.pricePanel   = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	pricePanel.add(priceLabel);
	pricePanel.add(price);
	this.buttonPanel  = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	buttonPanel.add(ok);
	buttonPanel.add(cancel);
	cancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){

		    frame.dispose();
		}
	    });

	ok.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    try {

			String companyText = company.getText();
			String volumeText  = volume.getText();
			String priceText   = price.getText();
			
			datapool.setCommands(Datapool.CreateTrade(
					    type,
					    companyText,
					    volumeText,
					    priceText ));
			frame.dispose();
		    }
		    catch (NullPointerException npe) {
			System.out.println("Error " + npe.getMessage());
			npe.printStackTrace();
		    }

		}
	    });

	this.panel = new JPanel(new GridLayout(4, 1));
	panel.add(companyPanel);
	panel.add(volumePanel);
	panel.add(pricePanel);
	panel.add(buttonPanel);

	this.frame = new JFrame(type);
	frame.add(panel);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.pack();
	frame.setVisible(true);
	frame.setResizable(false);


    }

    public void setType(String type){
	this.type = type;
    }
}

// 
// TradeFrame.java ends here
