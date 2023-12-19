package isletimodev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class semaphore {

	public static int SYSTEM_TIME = 0;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {

		System.out.println("aaaaa");
		LinkedList<Process> JobDispatchList = new LinkedList<>();
		Queue<Process> RealTimeQueue = new LinkedList<>();
		Queue<Process> UserJobQueue = new LinkedList<>();

		File fl = new File("docs/giris.txt");
		Scanner reader = new Scanner(fl);
		while (reader.hasNextLine()) {
			String data = reader.nextLine();
			JobDispatchList.add(new Process(data));
		}
		reader.close();

		Process[] deneme = JobDispatchList.toArray(new Process[JobDispatchList.size()]);

		Semaphore s1 = new Semaphore(1);
		Semaphore s2 = new Semaphore(0);

		Thread realThread = new Thread(() -> {
			while (true) {
				try {
					s1.acquire();
					System.out.println("s1s1s1s1");
					s1.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread userThread = new Thread(() -> {
			while (true) {
				try {
					s2.acquire();
					System.out.println("s2s2s2s2s2");
					s2.release();

				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
			}
		});

		realThread.start();
		userThread.start();
	}

}
