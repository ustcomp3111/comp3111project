package comp3111project;

public class RegularEventNode
{
	public RegularEventNode next=null;
	public RegularEvent regular_event;  //Points to the regular event which this objects refers to

public RegularEventNode(RegularEvent event)
{
	regular_event = event;
	}
public RegularEventNode(int Weekday, int Begin, int Duration) 
{
 regular_event = new RegularEvent("",0,Weekday,Begin,Duration,"") ;
}
RegularEventNode(String n,int i,int Weekday, int Begin, int Duration,String venue) 
{
	 regular_event = new RegularEvent(n,i,Weekday,Begin,Duration,venue) ;
	}
}

