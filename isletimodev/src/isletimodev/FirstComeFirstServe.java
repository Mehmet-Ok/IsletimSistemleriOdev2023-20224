package isletimodev;

import java.util.*;

import isletimodev.Process.Status;

public class FirstComeFirstServe {

	public static Queue<Process> fcfsQueue;
	public boolean isActive;
	
	public FirstComeFirstServe() 
	{
		this.fcfsQueue = new LinkedList<>();
		this.isActive = false;
	}
	
//	public void beklet() {
//		
//		this.isActive = false;
//		
//		for(Process p: fcfsQueue) {
//			p.process_status = Status.waiting;
//		}
//		
//		
//		
//	}
//	
//	public void devamEt() {
//		
//		this.isActive = true;
//		
//		for(Process p: fcfsQueue) {
//			p.process_status = Status.running;
//		}
//	}
	
	public void add(Process p) {
		p.process_status = Status.ready;
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
		

		
			System.out.println("real job");
			fcfsQueue.peek().burst_time--;
			fcfsQueue.peek().process_status = Status.running;
			System.out.println("realTime calıştı, pid="+fcfsQueue.peek().id+", status="+fcfsQueue.peek().process_status);
			if(fcfsQueue.peek().burst_time <= 0) {
				//System.out.println("biten process id="+fcfsQueue.peek().id+", biten yer=p0");
				fcfsQueue.peek().process_status = Status.terminated;
				fcfsQueue.poll();
			}
		
	}

	

}
