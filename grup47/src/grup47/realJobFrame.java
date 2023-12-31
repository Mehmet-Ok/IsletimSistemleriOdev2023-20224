package grup47;

public class realJobFrame {
	public Boolean isFree;
	public int pid;

	public realJobFrame() {
		isFree = true;
		pid = -1;
	}

	public void allocateFrame(int pid) {
		this.pid = pid;
		isFree = false;
	}

	public void releaseFrame() {
		isFree = true;
		pid = -1;
	}
}