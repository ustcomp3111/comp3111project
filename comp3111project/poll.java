import java.util.*;

public class poll {
	
	private String title;
	private LinkedList<candidate> candidates;
	private candidate winner;
	private int candidatenum;
	private String holder;
	
	public poll(){
		
		this.title="untitled";
		
		}

	
	public poll(String t,int n){
		
		this.title=t;
		this.candidatenum=n;

	}
	
	public void setTitle(String t){
		this.title=t;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void addCandidates(String n){
		candidate temp=new candidate(n);
		if(this.candidates==null)
		{
			candidates=new LinkedList<candidate>();
			candidates.addLast(temp);
		}
		else
		this.candidates.addLast(temp);
		
	}
	
	public String getWinner(){
		findWinner();
		return winner.getName();
	}
	
	private void findWinner(){
		
		
	}
	
	private void Listcandidate(){
		System.out.println("----------------------------------------------");
		System.out.println("Title : "+this.title);
		for(int i=0;i<this.candidatenum;i++)
		{
			System.out.println(candidates.get(i).getName()+", vote number: "+candidates.get(i).getVote());
		}
	}
		
		
		
	
	
	public static void main (String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Title of Polling : ");
		String t=in.nextLine();
		System.out.print("No. of candidates : ");
		int n=in.nextInt();

		String[] c=new String[n];

		for(int i = 0; i < c.length; i++)
		{
			System.out.print("Please input candidate "+(i+1)+": ");
			c[i]=in.next();
		}
		


		poll test=new poll(t,n);
		
		for(int i=0;i<n;i++)
		{
			test.addCandidates(c[i]);
		}
		
		test.Listcandidate();
		
	
	
	
	}
   
	
	
	
	
}
