package comp3111project;

 // For constructing linked list
public class Guest 
{
    Guest next = null;
    
    boolean respond = false, attend = false;
    
    User user; 
    Guest(User u)
    {
    	user=u;
    }
    Guest(String Name, int id) {
        user=new User(Name, id);
    }
};
