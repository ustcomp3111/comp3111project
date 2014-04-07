package com.example.weunion;

public class User {
		// singleton pattern
	    private static User current_user = new User();
	    private User(){}
	    public static User getInstance(){     
	        return current_user;
	    }
	    
	    //fields
	    private String id="admin";
	    
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	    
}