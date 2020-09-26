package com.conferencecenter;


public class AksesoreObj {

		private int ID;
	 	private String emerAksesori;
	    private int sallaID;
	    private int gjendjeAksesori;
	    
	    
	    public AksesoreObj(int ID, String emer, int gjendje, int idsalle) {
	        this.ID=ID;
	        this.emerAksesori = emer;
	        this.gjendjeAksesori = gjendje;
	        this.sallaID = idsalle;
	    }
	    
		public String getEmerAksesori() {
			return emerAksesori;
		}
		public void setEmerAksesori(String emerAksesori) {
			this.emerAksesori = emerAksesori;
		}
		
		
		public Integer getGjendjeAksesori() {
			return gjendjeAksesori;
		}
		public void setGjendjeAksesori(Integer gjendjeAksesori) {
			this.gjendjeAksesori = gjendjeAksesori;
		}
		public int getSallaID() {
			return sallaID;
		}
		public void setSallaID(Integer sallaid) {
			this.sallaID = sallaid;
		}

		public int getID() {
			return ID;
		}

		public void setID(int iD) {
			ID = iD;
		}
	  
	
	}


