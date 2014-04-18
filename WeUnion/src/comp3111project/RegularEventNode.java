package comp3111project;

public class RegularEventNode
{
	RegularEventNode next=null;
	RegularEvent regular_event;  //Points to the regular event which this objects refers to

RegularEventNode(RegularEvent event)
{
	regular_event = event;
	}
RegularEventNode(int Weekday, int Begin, int Duration) 
{
 regular_event = new RegularEvent("",0,Weekday,Begin,Duration) ;
}
RegularEventNode(String n,int i,int Weekday, int Begin, int Duration) 
{
	 regular_event = new RegularEvent(n,i,Weekday,Begin,Duration) ;
	}
}

