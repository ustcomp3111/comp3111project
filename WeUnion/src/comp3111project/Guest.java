package comp3111project;

 // For constructing linked list
public class Guest 
{
   public Guest next = null;
    
    public boolean respond = false, attend = false;
    
    public User user; 
    Guest(User u,boolean r,boolean a)
    {
    	user=u;
    respond = r;
    attend = a;
    }
    public Guest(String Name, int id,boolean r,boolean a) {
        user=new User(Name, id);
        respond = r;
        attend = a;
        
    }
};
