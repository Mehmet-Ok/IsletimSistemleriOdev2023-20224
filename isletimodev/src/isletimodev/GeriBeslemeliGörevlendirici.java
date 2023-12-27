package isletimodev;

import java.util.LinkedList;
import java.util.Queue;

import isletimodev.Process.Status;

import java.util.Collections;

public class GeriBeslemeliGörevlendirici {
	
	Queue<Process> priorityFirst;
	Queue<Process> prioritySecond;
	LinkedList<Process> priorityThird;
	boolean isActive;
	
	public GeriBeslemeliGörevlendirici() {
		priorityFirst = new LinkedList<>();
		prioritySecond = new LinkedList<>();
		priorityThird = new LinkedList<>();
		this.isActive = true;
	}
	
	public void printProcess() {
		System.out.println("------------------------------------------");
		System.out.println("----p=1----");
		for(Process p: priorityFirst)
			System.out.println("pid="+p.id+", status="+p.process_status);
		System.out.println("----p=2----");
		for(Process p: prioritySecond)
			System.out.println("pid="+p.id+", status="+p.process_status);
		System.out.println("----p=3----");
		for(Process p: priorityThird)
			System.out.println("pid="+p.id+", status="+p.process_status);
		System.out.println("------------------------------------------");
	}
	public void add(Process p) {
		
		p.process_status = Status.ready;
		if(p.priority == 1)
			priorityFirst.add(p);
		else if(p.priority == 2)
			prioritySecond.add(p);
		else if(p.priority == 3)
			priorityThird.add(p);
		else
			System.out.println("HATA-HATA-HATA");
	}
	
	public void stop() {
		if(!priorityFirst.isEmpty())
		{
			for(Process p: priorityFirst)
				if(p.process_status == Status.running)
					p.process_status = Status.ready;
		}
		else if(!prioritySecond.isEmpty())
		{			
			for(Process p: prioritySecond)
				if(p.process_status == Status.running)
					p.process_status = Status.ready;
		}
		else if(!priorityThird.isEmpty()) {	
			for(Process p: priorityThird)
				if(p.process_status == Status.running)
					p.process_status = Status.ready;
		}			
	}
	
	public void run() {
	
			System.out.println("user job");
			if(!priorityFirst.isEmpty())
			{
				Process curP = priorityFirst.poll();			
				curP.process_status = Status.running;
				curP.burst_time--;
				if(curP.burst_time > 0)
					prioritySecond.add(curP);
				else 
					curP.process_status = Status.terminated;
				System.out.println("p=1 calıştı, pid="+curP.id+", status="+curP.process_status);

			}
			else if(!prioritySecond.isEmpty())
			{			
				Process curP = prioritySecond.poll();			
				curP.process_status = Status.running;
				curP.burst_time--;
				if(curP.burst_time > 0)
					priorityThird.add(curP);
				else 
					curP.process_status = Status.terminated;
				System.out.println("p=2 calıştı, pid="+curP.id+", status="+curP.process_status);

			}
			else if(!priorityThird.isEmpty()) {	
				Process curP = priorityThird.poll();
				curP.process_status = Status.running;
				curP.burst_time--;
				if(curP.burst_time > 0)
					priorityThird.add(curP);		
				else 
					curP.process_status = Status.terminated;
				System.out.println("p=3 calıştı, pid="+curP.id+", status="+curP.process_status);
			}			
		
	}
}




