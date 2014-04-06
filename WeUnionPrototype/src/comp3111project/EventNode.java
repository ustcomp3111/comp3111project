package comp3111project;

 //For constructing linked list
public class EventNode
{

   public EventNode next = null;
   public Event event = null;
   public EventNode(Event e)
   {
	   event = e;
   }
   EventNode(String a, int b, User c, DateAndTime d, int e,String f) {
       event = new Event(a,b, c, d, e,f);
   } 
};
