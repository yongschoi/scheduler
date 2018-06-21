package yongs.temp.db;

public class Greeting {
	private int seq;
	private String name;
	
	public Greeting(int seq, String name) {
		this.seq = seq;
		this.name = name;
	}	
	public Greeting(String name) {
		this.name = name;
	} 
	 
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
