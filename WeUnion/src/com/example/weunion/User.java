package com.example.weunion;

public class User {
		// singleton pattern
	    private static User current_user = new User();
	    private User(){}
	    public static User getInstance(){     
	        return current_user;
	    }
	    
	    //fields
	    private String name="Anonymous";
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

		private int id =-1;
		public String getName() {
			return name;
		}
		public void setName(String temp) {
			this.name = temp;
		}
	    
}