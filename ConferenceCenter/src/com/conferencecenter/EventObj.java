package com.conferencecenter;

import java.sql.Date;

public class EventObj {
	
	private int EventID;
    private String username;
    private String eventname;
    private String pershkrim;
    private Date data;
    private int statusi;
    private int sallaID;
    
    
    public EventObj(int eventID, String username, String eventname, String pershkrim, Date data, int statusi,int sallaID) {
        this.setEventID(eventID);
        this.username = username;
        this.eventname = eventname;
        this.pershkrim = pershkrim;
        this.data = data;
        this.statusi = statusi;
        this.sallaID = sallaID;
    }
    
    
    
    
	public EventObj() {
		// TODO Auto-generated constructor stub
	}




	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getPershkrim() {
		return pershkrim;
	}
	public void setPershkrim(String pershkrim) {
		this.pershkrim = pershkrim;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date date2) {	
	    data=date2;
		
	}
	public int getStatusi() {
		return statusi;
	}
	public void setStatusi(int statusi) {
		this.statusi = statusi;
	}

	public int getEventID() {
		return EventID;
	}


	public void setEventID(int eventID) {
		EventID = eventID;
	}


	public int getSallaID() {
		return sallaID;
	}

	public void setSallaID(int sallaID) {
		this.sallaID = sallaID;
	}
	
}
