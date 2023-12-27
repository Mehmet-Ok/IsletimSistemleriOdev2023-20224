package isletimodev;

import java.util.ArrayList;

public class Page {
	public ArrayList<Integer> pids;
	public int capacity;
	public int remainingSpace;
	public int index;
	
	public Page(int i) {
		this.pids = new ArrayList<Integer>();
		this.capacity = 64;
		this.remainingSpace = 64;
		this.index = i;
	}

	
}
