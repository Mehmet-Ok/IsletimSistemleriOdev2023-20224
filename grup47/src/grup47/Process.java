package grup47;

public class Process implements Comparable<Process> {

	Status process_status;
	Reason deletionReason;
	int process_time;
	Boolean isAllocate;
	int pageCount;
	int id;
	int arrival_time;
	int priority;
	int burst_time;
	int mbyte;
	int writer_count;
	int scanner_count;
	int modem_count;
	int cd_count;
	boolean has_allocated;

	enum Status {
		newP, ready, running, waiting, terminated
	}

	enum Reason {

		userResource, realResource, mb64, mb960, empty
	}

	public Process(int arrival_time, int priority, int burst_time, int mbyte, int writer_count, int scanner_count,
			int modem_count, int cd_count) {
		this.arrival_time = arrival_time;
		this.priority = priority;
		this.burst_time = burst_time;
		this.mbyte = mbyte;
		this.writer_count = writer_count;
		this.scanner_count = scanner_count;
		this.modem_count = modem_count;
		this.cd_count = cd_count;
	}

	public Process(String str, int id) {
		int[] array = splitStringToIntArray(str);
		this.process_time = 0;
		this.arrival_time = array[0];
		this.priority = array[1];
		this.burst_time = array[2];
		this.mbyte = array[3];
		this.writer_count = array[4];
		this.scanner_count = array[5];
		this.modem_count = array[6];
		this.cd_count = array[7];
		this.id = id;
		has_allocated = false;
		this.process_status = Status.newP;
		this.deletionReason = Reason.empty;
	}

	

	public void PrintProcess() {
		System.out.println("Arrival Time: " + arrival_time);
		System.out.println("Priority: " + priority);
		System.out.println("Burst Time: " + burst_time);
	    System.out.println("Memory Size: " + mbyte + " MB");
	    System.out.println("Writer Count: " + writer_count);
	    System.out.println("Scanner Count: " + scanner_count);
	    System.out.println("Modem Count: " + modem_count);
	    System.out.println("CD Count: " + cd_count);
	    System.out.println("------------------------------------");
	}

	@Override
	public int compareTo(Process other) {
		return Integer.compare(this.arrival_time, other.arrival_time);
	}

	public static int[] splitStringToIntArray(String input) {
		String[] stringArray = input.split(",");
		int[] intArray = new int[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			intArray[i] = Integer.parseInt(stringArray[i].trim());
		}
		return intArray;
	}

	public String toString() {
		if (this.process_time >= 20) {
			return String.format("%d    %s", id, "HATA - Proses zaman aşımı (20 sn de tamamlanamadı)");
		} else if (this.deletionReason == Reason.mb64) {
			return String.format("%d    %s", id,
					"HATA - Gerçek-zamanlı proses (64MB) tan daha fazla bellek talep ediyor - proses silindi");
		} else if (this.deletionReason == Reason.mb960) {
			return String.format("%d    %s", id,
					"HATA - Proses (960 MB) tan daha fazla bellek talep ediyor – proses silindi");
		} else if (this.deletionReason == Reason.realResource) {
			return String.format("%d    %s", id,
					"HATA - Gerçek-zamanlı proses çok sayıda kaynak talep ediyor - proses silindi");
		} else if (this.deletionReason == Reason.userResource) {
			return String.format("%d    %s", id, "HATA - Proses çok sayıda kaynak talep ediyor - proses silindi");
		} else {
			return String.format("%d   %d   %d   %d   %d   %d   %d   %d   %d   %s", id, arrival_time, priority,
					burst_time, mbyte, writer_count, scanner_count, modem_count, cd_count, process_status.toString());
		}
	}
}
