package grup47;

import java.util.ArrayList;

public class Frame {

	public boolean isEmpty;
	public int pid;

	public Frame() {
		isEmpty = true;
		pid = -1;
	}

	public void allocateFrame(int pid) {
		isEmpty = false;
		this.pid = pid;
//		System.out.println("GELDİK");
	}

	public void releaseFrame() {
		isEmpty = true;
		pid = -1;
	}
}
