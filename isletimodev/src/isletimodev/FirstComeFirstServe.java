package isletimodev;

import java.util.*;

public class FirstComeFirstServe {

	int size;
	int[] arrivalTimes;
	int[] burstTimes;
	int[] completionTimes;
	int[] turnAroundTimes;
	int[] waitingTimes;
	float averageWaitingTime;
	float averageTurnArpoundTime;
	
	public FirstComeFirstServe(LinkedList<Process> p) 
	{
		
		p.sort(null);
		this.size = p.size();
		Process[] temp = p.toArray(new Process[p.size()]);
		
		this.arrivalTimes = new int[size];
		this.burstTimes = new int[size];
		this.completionTimes = new int[size];
		this.turnAroundTimes = new int[size];
		this.waitingTimes = new int[size];
		this.averageTurnArpoundTime = 0;
		this.averageWaitingTime = 0;
		
		int i = 0;
		for (Process itr : temp) 
		{
			arrivalTimes[i] = itr.arrival_time;
			burstTimes[i] = itr.burst_time;
		}
	}
	

	public static void RunFCFS() {
		
	}

	

}
