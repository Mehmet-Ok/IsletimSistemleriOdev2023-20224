package isletimodev;

import java.util.*;

import isletimodev.Process.Status;

public class FirstComeFirstServe {

	public static Queue<Process> fcfsQueue;
	public boolean isActive;
	private Memory RAM;
	
	public FirstComeFirstServe(Memory m) 
	{
		this.fcfsQueue = new LinkedList<>();
		this.isActive = false;
		RAM = m;
	}
		
	public void add(Process p) {
		p.process_status = Status.ready;
		System.out.println("pid="+p.id+"priority="+p.priority+", p_mb="+p.mbyte+" eklendi.");
		this.fcfsQueue.add(p);
	}
	
	public boolean isEmpty() {
		return this.fcfsQueue.isEmpty();
	}

	public void printProcess() {
		System.out.println("------------------------------------------");
		System.out.println("----p=0----");
		for(Process p: fcfsQueue)
			System.out.println("pid="+p.id+", status="+p.process_status);		
		System.out.println("------------------------------------------");
	}
	
	public void run() {
		//fcfs algoritması

			if(!fcfsQueue.peek().isAllocate) {
				RAM.allocateRealProcess(fcfsQueue.peek());
				fcfsQueue.peek().isAllocate = true;
//				System.out.println("realFrame allocated");
			}
			
			fcfsQueue.peek().burst_time--;
			fcfsQueue.peek().process_status = Status.running;
	
			if(fcfsQueue.peek().burst_time <= 0) 
			{
				fcfsQueue.peek().process_status = Status.terminated;
				fcfsQueue.poll();
				//rami burda bırak
				RAM.releaseRealFrame(fcfsQueue.peek());
//				System.out.println("realFrame released");
			}
		
	}

	

}
