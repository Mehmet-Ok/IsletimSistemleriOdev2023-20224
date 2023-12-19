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
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
		LinkedList<Process> JobDispatchList = new LinkedList<>();
		Queue<Process> RealTimeQueue = new LinkedList<>();
		Queue<Process> UserJobQueue = new LinkedList<>();
		
		File fl = new File("docs/giris.txt");
		Scanner reader = new Scanner(fl);
		while(reader.hasNextLine()) {
			String data = reader.nextLine();
			JobDispatchList.add(new Process(data));
		}
		reader.close();
		
		Process[] deneme = JobDispatchList.toArray(new Process[JobDispatchList.size()]);

		
		
		int SYSTEM_TIME = 0;
		boolean realTimeProcess = false;
		boolean userProcess = false;
		
		while (true) 
		{
			Thread.sleep(1000);
			System.out.println("saniye: " + SYSTEM_TIME);
			System.out.println();
			
			//dağıtıcı
			for(int i = 0; i<JobDispatchList.size(); i++)
			{
				
				if(deneme[i].arrival_time == SYSTEM_TIME)
				{					
					if(deneme[i].priority == 0)
					{
						System.out.println("deneme"+i+": "+deneme[i].priority);
						realTimeProcess = true;
					}
					else 
					{
						userProcess = true;
					}
				}
				
				if(realTimeProcess)
				{
					RealTimeQueue.add(deneme[i]);
					//user thread dursun real time baslasın
				}
				if(userProcess)
				{
					System.out.println("user job eklendi");
					UserJobQueue.add(deneme[i]);
					//realtime ı beklesin realtime bitince devam etsin
				}
				
				
				userProcess = false;
				realTimeProcess = false;
			}
			
			//çalışma
			if(!RealTimeQueue.isEmpty())
			{
				System.out.println("realTimeProcess burst time before: "+RealTimeQueue.peek().burst_time);
				RealTimeQueue.peek().burst_time--;
				System.out.println("realTimeProcess burst time after: "+RealTimeQueue.peek().burst_time);
				if(RealTimeQueue.peek().burst_time == 0) {
					System.out.println("realtime queue bitti");
					RealTimeQueue.remove(RealTimeQueue.peek());
				}
			}
			else {
				System.out.println("user queue çalışıyor");
				System.out.println("userjobs, sytemtime: "+SYSTEM_TIME);
			}
			
			
			SYSTEM_TIME++;
//			if(SYSTEM_TIME<JobDispatchList.size())
//			{
//				SYSTEM_TIME++;
//			}
//			else
//			{
//				break;
//			}
			
		}
		
//		for(Process p: RealTimeQueue ) {
//			p.PrintProcess();
//		}
//		System.out.println("-------------------");
//		for(Process p: UserJobQueue) {
//			p.PrintProcess();
//		}
		
	}

}
