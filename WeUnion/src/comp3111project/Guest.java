package comp3111project;

 // For constructing linked list
public class Guest 
{
    Guest next = null;
    boolean attend = false;
    User user; 
    public Guest(User u)
    {
    	user=u;
    }
    Guest(String Name, int id) {
        user=new User(Name, id);
    }
};
