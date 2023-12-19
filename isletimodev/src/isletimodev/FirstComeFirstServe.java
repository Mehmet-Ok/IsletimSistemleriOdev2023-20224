package isletimodev;

import java.util.*;

public class FirstComeFirstServe {

	public static Queue<Process> fcfsQueue;
	
	public FirstComeFirstServe() 
	{
		this.fcfsQueue = new LinkedList<>();
	}
	
	public void add(Process p) {
		this.fcfsQueue.add(p);
	}
	
	public boolean isEmpty() {
		return this.fcfsQueue.isEmpty();
	}

	public void run() {
		//fcfs algoritması
		
		Process curP = fcfsQueue.poll();
		
		curP.burst_time--;
		System.out.println("realTime calıştı, pid="+curP.id+" , kalan zaman="+curP.burst_time);
		if(curP.burst_time > 0)
			fcfsQueue.add(curP);
	}

	

}
