// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// TradeInfor.java --- 
// Filename: TradeInfor.java
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
//     Update #: 20
// 

// Code:
package stock;

class TradeInfor{
    
    private String type;
    private String company;
    private int price, volume;
    private int id;


    // constructor
    TradeInfor(String type, String company, int volume, int price, int id){
	setType(type);
	setCompany(company);
	setVolume(volume);
	setPrice(price);
	setID(id);
    }

    TradeInfor(String type, String company, String volume, String price, String id){
	setType(type);
	setCompany(company);
	setVolume(Integer.parseInt(volume));
	setPrice(Integer.parseInt(price));
	setID(Integer.parseInt(id));
    }

    // setter

    public void setType(String type){
	this.type = type;
    }

    public void setCompany(String company){
	this.company = company;
    }

    public void setPrice(int price){
	this.price = price;
    }

    public void setVolume(int volume){
	this.volume = volume;
    }

    public void setID(int id){
	this.id = id;
    }

    // getter
    public String getType(){
	return type;
    }

    public String getCompany(){
	return company;
    }
    
    public int getPrice(){
	return price;
    }

    public int getVolume(){
	return volume;
    }

    public int getID(){
	return id;
    }
}


// 
// TradeInfor.java ends here
