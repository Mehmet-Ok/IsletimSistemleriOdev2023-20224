package isletimodev;

public class Process implements Comparable<Process> {
	int id;
	int process_time;
	Status process_status;
	int arrival_time;
	int priority;
	int burst_time;
	int mbyte;
	int writer_count;
	int scanner_count;
	int modem_count;
	int cd_count;
	
	enum Status{
		newP,
		ready,
		running,
		waiting,
		terminated
	}
	
	public Process(int id,int arrival_time, int priority, int burst_time, int mbyte, int writer_count, int scanner_count, int modem_count, int cd_count)
	{
		this.id = id;
		this.process_time = 0;
		this.process_status = Status.newP;
		this.arrival_time = arrival_time;
		this.priority = priority;
		this.burst_time=burst_time;
		this.mbyte=mbyte;
		this.writer_count=writer_count;
		this.scanner_count=scanner_count;
		this.modem_count=modem_count;
		this.cd_count=cd_count;
	}
		
	public Process(String str,int id)
	{
		int []array = splitStringToIntArray(str);
		
		this.process_time = 0;
		this.process_status = Status.newP;
		this.id = id;
		
		this.arrival_time = array[0];
		this.priority =  array[1];
		this.burst_time= array[2];
		this.mbyte= array[3];
		this.writer_count= array[4];
		this.scanner_count= array[5];
		this.modem_count= array[6];
		this.cd_count= array[7];
	}
	
	public void PrintProcess() {
		System.out.println("Arrival Time: " + arrival_time);
	    System.out.println("Priority: " + priority);
	    System.out.println("Burst Time: " + burst_time);
//	    System.out.println("Memory Size: " + mbyte + " MB");
//	    System.out.println("Writer Count: " + writer_count);
//	    System.out.println("Scanner Count: " + scanner_count);
//	    System.out.println("Modem Count: " + modem_count);
//	    System.out.println("CD Count: " + cd_count);
//	    System.out.println("------------------------------------");
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
}
