package isletimodev;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;

public class GeriBeslemeliGörevlendirici {
	
	Queue<Process> priorityFirst;
	Queue<Process> prioritySecond;
	LinkedList<Process> priorityThird;
	
	public GeriBeslemeliGörevlendirici() {
		priorityFirst = new LinkedList<>();
		prioritySecond = new LinkedList<>();
		priorityThird = new LinkedList<>();
	}
	
	public void add(Process p) {
		if(p.priority == 1)
			priorityFirst.add(p);
		else if(p.priority == 2)
			prioritySecond.add(p);
		else if(p.priority == 3)
			priorityThird.add(p);
		else
			System.out.println("HATA-HATA-HATA");
	}
	
	public void run() {
		if(!priorityFirst.isEmpty())
		{
			Process curP = priorityFirst.poll();			
			curP.burst_time--;
			if(curP.burst_time > 0)
				prioritySecond.add(curP);
			System.out.println("p=1 calıştı, pid="+curP.id);

		}
		else if(!prioritySecond.isEmpty())
		{			
			Process curP = prioritySecond.poll();						
			curP.burst_time--;
			if(curP.burst_time > 0)
				priorityThird.add(curP);
			System.out.println("p=2 calıştı, pid="+curP.id);

		}
		else if(!priorityThird.isEmpty()) {
			//??
			Collections.reverse(priorityThird);	
			
			Process curP = priorityThird.poll();
			curP.burst_time--;
			if(curP.burst_time > 0)
				priorityThird.add(curP);		
			System.out.println("p=3 calıştı, pid="+curP.id);
		}
	}
}




