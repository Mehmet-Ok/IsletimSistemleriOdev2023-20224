package isletimodev;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Queue<Process> JobDispatchList = new LinkedList<>();
		
		File fl = new File("docs/giris.txt");
		Scanner reader = new Scanner(fl);
		while(reader.hasNextLine()) {
			String data = reader.nextLine();
			JobDispatchList.add(new Process(data));
		}
		reader.close();
		
		for (Process process : JobDispatchList) {
			process.PrintProcess();
		}
	}

}
