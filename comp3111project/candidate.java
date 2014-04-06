
public class candidate {
	
	private String name;
	private int vote;
	
	/* Constructor */
	public candidate(String name){
		this.name=name;
		vote=0;
	}
	
	public void incrementVote(){
		vote++;
	}

	public String getName(){
		return name;
	}
	
	public int getVote(){
		return vote;
	}
}
