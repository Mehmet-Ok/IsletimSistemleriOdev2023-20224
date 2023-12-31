package isletimodev;

import java.util.LinkedList;
import java.util.Queue;

import isletimodev.Process.Status;

import java.util.Collections;

public class GeriBeslemeliGörevlendirici {
	
	LinkedList<Process> priorityFirst;
	LinkedList<Process> prioritySecond;
	LinkedList<Process> priorityThird;
	boolean isActive;
	Memory RAM;
	
	public GeriBeslemeliGörevlendirici(Memory m) {
		priorityFirst = new LinkedList<>();
		prioritySecond = new LinkedList<>();
		priorityThird = new LinkedList<>();
		this.isActive = true;
		this.RAM = m;
		System.out.println("(GBGKURUCU)ramde kalan yer= "+RAM.emptyFrameCount);
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
			System.out.println("pid="+p.id+"priority="+p.priority+", p_mb="+p.mbyte+" eklendi.");
			if(p.priority == 1)
				priorityFirst.add(p);
			else if(p.priority == 2)
				prioritySecond.add(p);
			else if(p.priority == 3)
				priorityThird.add(p);
			else
				System.out.println("HATA-HATA-HATA");
	}
	
//	public void stop() {
//		if(!priorityFirst.isEmpty())
//		{
//			for(Process p: priorityFirst)
//				if(p.process_status == Status.running)
//					p.process_status = Status.ready;
//		}
//		else if(!prioritySecond.isEmpty())
//		{			
//			for(Process p: prioritySecond)
//				if(p.process_status == Status.running)
//					p.process_status = Status.ready;
//		}
//		else if(!priorityThird.isEmpty()) {	
//			for(Process p: priorityThird)
//				if(p.process_status == Status.running)
//					p.process_status = Status.ready;
//		}			
//	}
	
	public void allocation() {
		
	}
	
	public void run() {
		
		boolean kont = false;
		System.out.println("priority first");
		for(Process p:priorityFirst)
			System.out.println("	pid="+p.id);
		System.out.println("priority second");
		for(Process p:prioritySecond)
			System.out.println("	pid="+p.id);
		System.out.println("priority third");
		for(Process p:priorityThird)
			System.out.println("	pid="+p.id);
		
		if(!priorityFirst.isEmpty())
		{	
			Process curP;
			int index;
			System.out.println("ramde kalan yer= "+RAM.emptyFrameCount);
			for(index = 0; index<priorityFirst.size(); index++)
			{
				if(!priorityFirst.get(index).isAllocate)
				{
					if(RAM.isAllocateble(priorityFirst.get(index)))
					{						
						RAM.allocateUserProcess(priorityFirst.get(index));						
						kont = true;
						break;
					}
				}
			}
			curP = priorityFirst.get(index);
			priorityFirst.remove(index);
			if(curP.burst_time > 0)
			{
				curP.burst_time--;
				System.out.println("p1 çalıştı");
				prioritySecond.add(curP);
			}
			else 
			{
				curP.process_status = Status.terminated;
				RAM.releaseAllocatedUserFrame(curP);
			}
		}
		else if(!prioritySecond.isEmpty())
		{
			Process curP;
			int index = 0;
			System.out.println("ramde kalan yer= "+RAM.emptyFrameCount);
			for(int i = 0; i<prioritySecond.size(); i++)
			{
				if(!prioritySecond.get(i).isAllocate)
				{
					if(RAM.isAllocateble(prioritySecond.get(i)))
					{
						RAM.allocateUserProcess(prioritySecond.get(i));
						kont = true;
						index = i;
						break;
					}
				}
			}
			//System.out.println("index="+index);
			curP = prioritySecond.get(index);
			prioritySecond.remove(index);
			if(curP.burst_time > 0)
			{
				curP.burst_time--;
				System.out.println("p2 çalıştı");
				priorityThird.add(curP);
			}
			else
			{						
				curP.process_status = Status.terminated;
				RAM.releaseAllocatedUserFrame(curP);
			}
		}
		else if(!priorityThird.isEmpty())
		{
			Process curP;
			int index = 0;
			System.out.println("ramde kalan yer= "+RAM.emptyFrameCount);
			for(int i = 0; i<priorityThird.size(); i++)
			{	
				if(!priorityThird.get(i).isAllocate)
				{
					if(RAM.isAllocateble(priorityThird.get(i)))
					{
						RAM.allocateUserProcess(priorityThird.get(i));
						kont = true;
						index = i;
						break;
					}
				}
			}
			curP = priorityThird.get(index);
			priorityThird.remove(index);
			if(curP.burst_time > 0)
			{
				curP.burst_time--;	
				priorityThird.add(curP);
				System.out.println("p3 çalıştı");
			}							
			else
			{
				curP.process_status = Status.terminated;
				RAM.releaseAllocatedUserFrame(curP);
			}
//			System.out.println("KİTLENDİ AMK");
		}
		if(kont)
			System.out.println("KİTLENDİ AMK");
		
		

		
	}
}




