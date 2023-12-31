package grup47;

import java.util.ArrayList;

public class Memory {

	public ArrayList<Frame> frames;
	public realJobFrame realJobFrame;
	public int realJobFrameSize;
	public int frameCount;
	public int emptyFrameCount;
	public int memSize;
	public int frameSize;

	public Memory() {

		memSize = 960;
		frameSize = 16;
		frameCount = memSize / frameSize;

		frames = new ArrayList<Frame>();
		for (int i = 0; i < frameCount; i++) {
			frames.add(new Frame());
		}
		realJobFrame = new realJobFrame();
		emptyFrameCount = frameCount;
	}

	public Boolean isAllocateble(Process p) {
		return emptyFrameCount >= p.pageCount;
	}

	public void allocateUserProcess(Process p) {

		boolean k = false;

		if (p.priority != 0) {
			// System.out.println("AAA emptyFrame="+emptyFrameCount+",
			// pr.pageCount="+p.pageCount);
			if (emptyFrameCount >= p.pageCount) {
				// System.out.println("BBB");
				int pc = p.pageCount;

				for (Frame f : frames) {
					if (f.isEmpty) {
						// System.out.println("CCC");
						f.allocateFrame(p.id);
						// emptyFrameCount--;
						pc--;
					}

//					if(pc == 0)
//					{
//						System.out.println("ALLOCATE");
//					}
				}
				emptyFrameCount -= p.pageCount;
				p.isAllocate = true;
				System.out.println("tahsis edilen proses=" + p.id + ", pageCount=" + p.pageCount + ", ramde kalan yer="
						+ emptyFrameCount);
			}
		}
	}

	public Boolean allocateRealProcess(Process p) {

		if (p.priority == 0) {
			if (realJobFrame.isFree) {
				realJobFrame.allocateFrame(p.id);
				return true;
			}
		}
		return false;
	}

	public void releaseAllocatedUserFrame(Process p) {
		if (p.priority != 0) {
			for (Frame f : frames) {
				if (f.pid == p.id) {
					f.releaseFrame();
					emptyFrameCount++;
				}
			}
			System.out.println("alan serbest bırakıldı");
		}
	}

	public void releaseRealFrame(Process p) {
		if (p.priority == 0) {
			realJobFrame.releaseFrame();
		}
	}

	public void writeToDisk(Process p) {
		if (p.priority != 0) {
			for (Frame f : frames) {
				if (f.pid == p.id) {
					f.releaseFrame();
					emptyFrameCount++;
				}
			}
			System.out.println("alan serbest bırakıldı");
		}
	}

}