package grup47;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import grup47.Process.Reason;
import grup47.Process.Status;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	@SuppressWarnings({ "static-access", "static-access" })

	public static void print(Process[] processes, int time) throws IOException {
		
		
		System.out.println("Pid varış öncelik cpu MBytes prn scn modem cd status");
		System.out.println("============================================================================");

		for (Process process : processes) {
			if (process.burst_time >= 0 && process.arrival_time <= time) {
				System.out.println(process.toString());
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {

		LinkedList<Process> JobDispatchList = new LinkedList<>();

		GeriBeslemeliGörevlendirici userQueue = new GeriBeslemeliGörevlendirici();
		FirstComeFirstServe realTimeQueue = new FirstComeFirstServe();

		File fl = new File("docs/giris.txt");
		Scanner reader = new Scanner(fl);
		int idIndex = 0;
		while (reader.hasNextLine()) {
			String data = reader.nextLine();
			JobDispatchList.add(new Process(data, idIndex));
			idIndex++;
		}
		reader.close();

		Process[] deneme = JobDispatchList.toArray(new Process[JobDispatchList.size()]);

		int SYSTEM_TIME = 0;
		boolean realTimeProcess = false;
		boolean userProcess = false;

		while (true) {
			Thread.sleep(1000);

			// dağıtıcı
			for (int i = 0; i < JobDispatchList.size(); i++) {

				if (deneme[i].arrival_time == SYSTEM_TIME) {
					if (deneme[i].priority == 0) {
						// System.out.println("deneme" + i + ": " + deneme[i].priority);
						realTimeProcess = true;
					} else {
						userProcess = true;
					}
				}

				if (realTimeProcess) {
					if (!(deneme[i].mbyte > 64 || deneme[i].modem_count > 0 || deneme[i].cd_count > 0
							|| deneme[i].scanner_count > 0 || deneme[i].writer_count > 0))
						realTimeQueue.add(deneme[i]);
					else if (deneme[i].mbyte > 64) {

						deneme[i].process_status = Status.terminated;
						deneme[i].deletionReason = Reason.mb64;

					} else if (deneme[i].modem_count > 0 || deneme[i].cd_count > 0 || deneme[i].scanner_count > 0
							|| deneme[i].writer_count > 0) {
						deneme[i].process_status = Status.terminated;
						deneme[i].deletionReason = Reason.realResource;
					}

					// user thread dursun real time baslasın
				}
				if (userProcess) {
					// System.out.println("user job eklendi");
					if (!(deneme[i].mbyte > 960 || deneme[i].modem_count > 1 || deneme[i].cd_count > 2
							|| deneme[i].scanner_count > 1 || deneme[i].writer_count > 2))
						userQueue.add(deneme[i]);
					else if (deneme[i].mbyte > 960) {

						deneme[i].process_status = Status.terminated;
						deneme[i].deletionReason = Reason.mb960;
					} else if (deneme[i].modem_count > 1 || deneme[i].cd_count > 2 || deneme[i].scanner_count > 1
							|| deneme[i].writer_count > 2) {

						deneme[i].process_status = Status.terminated;
						deneme[i].deletionReason = Reason.userResource;
					}
					// realtime ı beklesin realtime bitince devam etsin
				}
				userProcess = false;
				realTimeProcess = false;
			}

			// çalışma
			if (!realTimeQueue.isEmpty()) {
				realTimeQueue.run();
			} else {
				userQueue.run();
			}

			for (Process p : deneme) {
				if (p.arrival_time <= SYSTEM_TIME && p.process_status != Status.terminated) {
					p.process_time++;
				}
			}
			
			
			print(deneme,SYSTEM_TIME);
			SYSTEM_TIME++;

		}

	}

}
