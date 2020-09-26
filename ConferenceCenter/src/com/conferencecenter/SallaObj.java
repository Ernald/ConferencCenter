package com.conferencecenter;

import java.util.ArrayList;
public class SallaObj {
	
		private int id;
	 	private String emerSalle;
	    private int numerVendesh;
		private float kostoDitore;
	    private ArrayList<AksesoreObj> aksesorelist ;
	    
	    
	    public SallaObj(Integer id, String emer, int kapacitet, float kosto) {
	    	this.id=id;
	        this.emerSalle = emer;
	        this.numerVendesh = kapacitet;
	        this.kostoDitore= kosto;
	        this.aksesorelist = new ArrayList<AksesoreObj>();

	    }
	    
	    
	    public SallaObj() {
			// TODO Auto-generated constructor stub
		}


	    public int getId() {
				return id;
			}
		public void setId(int id) {
				this.id = id;
			}
		public String getEmerSalle() {
			return emerSalle;
		}
		public void setEmerSalle(String emerSalle) {
			this.emerSalle = emerSalle;
		}
		public Integer getNumerVendesh() {
			return numerVendesh;
		}
		public void setNumerVendesh(Integer numerVendesh) {
			this.numerVendesh = numerVendesh;
		}
		public float getKostoDitore() {
			return kostoDitore;
		}
		public void setKostoDitore(float kostoDitore) {
			this.kostoDitore = kostoDitore;
		}
		public ArrayList<AksesoreObj> getAksesoreList() {
			return aksesorelist;
		}

		public void setAksesoreList(ArrayList<AksesoreObj> aksesorelist) {
			this.aksesorelist = aksesorelist;
		}

}
