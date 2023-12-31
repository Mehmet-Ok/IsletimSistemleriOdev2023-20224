package isletimodev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	@SuppressWarnings({ "static-access", "static-access" })
	
	public static void print(Process[] processes) {
		
		System.out.println("Pid varış öncelik cpu MBytes prn scn modem cd status");
        System.out.println("============================================================================");

        for (Process process : processes) {
        	if(process.burst_time >= 0) {
        		System.out.println(process.toString());
    		}
        }
	}
	

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
		Memory RAM = new Memory();
		System.out.println(RAM.frameCount);
		System.out.println(RAM.emptyFrameCount);
		System.out.println(RAM.memSize);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		LinkedList<Process> JobDispatchList = new LinkedList<>();

		
		GeriBeslemeliGörevlendirici userQueue = new GeriBeslemeliGörevlendirici(RAM);
		FirstComeFirstServe realTimeQueue = new FirstComeFirstServe(RAM);
		
		File fl = new File("docs/giris.txt");
		Scanner reader = new Scanner(fl);
		int idIndex = 0;
		while(reader.hasNextLine()) {
			String data = reader.nextLine();
			JobDispatchList.add(new Process(data,idIndex));
			idIndex++;
		}
		reader.close();
		
		
		
		Process[] deneme = JobDispatchList.toArray(new Process[JobDispatchList.size()]);
		
//		
//		for(Process p: deneme)
//			System.out.println("pid="+p.id+", status="+p.process_status);
	
		int SYSTEM_TIME = 0;
		boolean realTimeProcess = false;
		boolean userProcess = false;
		
		while (true) 
		{
			Thread.sleep(1000);
			System.out.println("------------------------------------------");
			System.out.println("saniye: " + SYSTEM_TIME);
			System.out.println();
			
			
			
//			System.out.println("------------------------------------------");
//			for(Process p: deneme)
//				System.out.println("pid="+p.id+", status="+p.process_status);
//			System.out.println("------------------------------------------");
			
			//dağıtıcı
			for(int i = SYSTEM_TIME ; i<JobDispatchList.size(); i++)
			{
				
				if(deneme[i].arrival_time == SYSTEM_TIME)
				{				
					if(deneme[i].priority == 0)
					{
						
						realTimeProcess = true;
					}
					else 
					{
						userProcess = true;
					}
				}
				
				if(realTimeProcess)
				{
					if(!(deneme[i].mbyte >64 || deneme[i].modem_count > 0 || deneme[i].cd_count >0 || deneme[i].scanner_count > 0||  deneme[i].writer_count>0) ) 
							realTimeQueue.add(deneme[i]);
					else
						System.out.println("silindi="+deneme[i].id);
						
					//user thread dursun real time baslasın
				}
				if(userProcess)
				{
					//System.out.println("user job eklendi");
					if(!(deneme[i].mbyte > 960 || deneme[i].modem_count > 1 || deneme[i].cd_count >2 || deneme[i].scanner_count > 1||  deneme[i].writer_count>2) )
						userQueue.add(deneme[i]);
					else
						System.out.println("silindi="+deneme[i].id);
					//realtime ı beklesin realtime bitince devam etsin
				}							
				userProcess = false;
				realTimeProcess = false;
			}
			
			//çalışma
			if(!realTimeQueue.isEmpty())
			{
				realTimeQueue.run();				
			}
			else {
				userQueue.run();
			}
			
//			for(Process p: deneme)
//			{
//				if(p.arrival_time <=SYSTEM_TIME)
//				{
//					p.process_time++;
//				}
//				if(p.process_time >=20)
//				{
//					
//				}
			
			
//			realTimeQueue.printProcess();
//			userQueue.printProcess();
			print(deneme);
			SYSTEM_TIME++;
			System.out.println("------------------------------------------");
		}

	

}}

