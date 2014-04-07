package comp3111project;

 //For constructing linked list
public class EventNode
{

   public EventNode next = null;
   public Events event = null;
   public EventNode(Events e)
   {
	   event = e;
   }
   EventNode(String a, int b, User c, DateAndTime d, int e,String f) {
       event = new Events(a,b, c, d, e,f);
   } 
};
