package com.conferencecenter;


public class UserObj {
	
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String salt;
    private int RolID;
    private int status;
    public boolean valid;
    
    
    public UserObj(String username, String firstName, String lastName, String email, int rolid, int status) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.RolID = rolid;
        this.status=status;
    }

    public UserObj() {
    	
    }
    
	
    public String getFirstName() {
       return firstName;
	}

    public void setFirstName(String newFirstName) {
       firstName = newFirstName;
	}

	
    public String getLastName() {
       return lastName;
			}

    public void setLastName(String newLastName) {
       lastName = newLastName;
			}
			

    public String getPassword() {
       return password;
	}

    public void setPassword(String newPassword) {
       password = newPassword;
	}
	
			
    public String getUsername() {
       return username;
			}

    public void setUserName(String newUsername) {
       username = newUsername;
			}

				
    public boolean isValid() {
       return valid;
	}

    public void setValid(boolean newValid) {
       valid = newValid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getRolID() {
		return RolID;
	}

	public void setRolID(Integer rolID) {
		RolID = rolID;
	}

	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
}
