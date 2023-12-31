package grup47;

import java.util.*;

import grup47.Process.Status;

public class FirstComeFirstServe {

	public static Queue<Process> fcfsQueue;

	public FirstComeFirstServe() {
		this.fcfsQueue = new LinkedList<>();
	}

	public void add(Process p) {
		this.fcfsQueue.add(p);
		p.process_status = Status.ready;
	}

	public boolean isEmpty() {
		return this.fcfsQueue.isEmpty();
	}

	public void run() {
		// fcfs algoritması

		Process curP = fcfsQueue.poll();

		curP.burst_time--;
		curP.process_status = Status.running;
//		System.out.println("realTime calıştı, pid=" + curP.id + " , kalan zaman=" + curP.burst_time);
		if (curP.burst_time > 0) {
			fcfsQueue.add(curP);
			curP.process_status = Status.ready;
		}
		else {
			curP.process_status=Status.terminated;
		}
	}

}
