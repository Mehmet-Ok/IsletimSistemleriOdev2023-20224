package grup47;

import java.util.LinkedList;
import java.util.Queue;

import grup47.Process.Status;

public class GeriBeslemeliGörevlendirici {

	Resource scanner;
	Resource writer;
	Resource modem;
	Resource cd;

	LinkedList<Process> priorityFirst;
	LinkedList<Process> prioritySecond;
	LinkedList<Process> priorityThird;

	Queue<Process> resourceWaitingQueue;

	public GeriBeslemeliGörevlendirici() {
		priorityFirst = new LinkedList<>();
		prioritySecond = new LinkedList<>();
		priorityThird = new LinkedList<>();

		writer = new Resource("writer", 2);
		scanner = new Resource("scanner", 1);
		modem = new Resource("modem", 1);
		cd = new Resource("cd", 2);
	}

	public void add(Process p) {

		if (p.priority == 1)
			priorityFirst.add(p);
		else if (p.priority == 2)
			prioritySecond.add(p);
		else if (p.priority == 3)
			priorityThird.add(p);
		else
			System.out.println("HATA-HATA-HATA");
		p.process_status = Status.ready;

	}

	public void run() {
		Boolean didP1Run = false;
		Boolean didP2Run = false;
		if (!priorityFirst.isEmpty()) {

			Process curP;

			for (int i = 0; i < priorityFirst.size(); i++) {// her prosesi gezer

				curP = priorityFirst.get(i);
				if (curP.process_time >= 20) {

					priorityFirst.remove(i);
					didP1Run = false;
				} else {
					if (writer.isAllocatable(curP.writer_count) && curP.writer_count >= 0
							&& scanner.isAllocatable(curP.scanner_count) && curP.scanner_count >= 0
							&& modem.isAllocatable(curP.modem_count) && curP.modem_count >= 0
							&& cd.isAllocatable(curP.cd_count) && curP.cd_count >= 0) {// tahsis edilebirse

						writer.allocate(curP.writer_count);
						scanner.allocate(curP.scanner_count);
						modem.allocate(curP.modem_count);
						cd.allocate(curP.cd_count);

						curP.has_allocated = true;
						curP.process_status = Status.running;

						curP.burst_time--;
						if (curP.burst_time > 0) {
							prioritySecond.add(curP);
							curP.process_status = Status.ready;

						} else {// proses sonlanirsa kaynaklar serbest birakilir.
							writer.release(curP.writer_count);
							scanner.release(curP.scanner_count);
							modem.release(curP.modem_count);
							cd.release(curP.cd_count);

//							System.out.println(curP.id + "||" + curP.writer_count + " adet yazici serbest birakildi");
//							System.out
//									.println(curP.id + "||" + curP.scanner_count + " adet tarayici serbest birakildi");
//							System.out.println(curP.id + "||" + curP.modem_count + " adet modem serbest birakildi");
//							System.out.println(curP.id + "||" + curP.cd_count + " adet cd serbest birakildi");

							curP.process_status = Status.terminated;
						}

						priorityFirst.remove(i);
						didP1Run = true;
						break;

					} else {
						// eger hic bir prosesin calısması icin yeterli kaynak yoksa diger oncelige
						// gecer

						didP1Run = false;

					}
				}

			}

		}
		if (!prioritySecond.isEmpty() && !didP1Run) {

			Process curP;
			for (int i = 0; i < prioritySecond.size(); i++) {

				curP = prioritySecond.get(i);
				if (curP.process_time >= 20) {

					prioritySecond.remove(i);
					didP2Run = false;
				} else {
					if (curP.has_allocated) {

						curP.process_status = Status.running;
						curP.burst_time--;
						if (curP.burst_time > 0) {
							curP.process_status = Status.ready;
							priorityThird.add(curP);

						}

						else {// proses sonlanirsa kaynaklar serbest birakilir.
							writer.release(curP.writer_count);
							scanner.release(curP.scanner_count);
							modem.release(curP.modem_count);
							cd.release(curP.cd_count);

//							System.out.println(curP.id + "||" + curP.writer_count + " adet yazici serbest birakildi");
//							System.out
//									.println(curP.id + "||" + curP.scanner_count + " adet tarayici serbest birakildi");
//							System.out.println(curP.id + "||" + curP.modem_count + " adet modem serbest birakildi");
//							System.out.println(curP.id + "||" + curP.cd_count + " adet cd serbest birakildi");
							curP.process_status = Status.terminated;
						}

						prioritySecond.remove(i);
						didP2Run = true;
						break;

					} else {
						if (writer.isAllocatable(curP.writer_count) && curP.writer_count >= 0
								&& scanner.isAllocatable(curP.scanner_count) && curP.scanner_count >= 0
								&& modem.isAllocatable(curP.modem_count) && curP.modem_count >= 0
								&& cd.isAllocatable(curP.cd_count) && curP.cd_count >= 0) {

							writer.allocate(curP.writer_count);
							scanner.allocate(curP.scanner_count);
							modem.allocate(curP.modem_count);
							cd.allocate(curP.cd_count);

							curP.has_allocated = true;
							curP.process_status = Status.running;
							curP.burst_time--;
							if (curP.burst_time > 0) {

								curP.process_status = Status.ready;
								priorityThird.add(curP);

							} else {// proses sonlanirsa kaynaklar serbest birakilir.
								writer.release(curP.writer_count);
								scanner.release(curP.scanner_count);
								modem.release(curP.modem_count);
								cd.release(curP.cd_count);

//								System.out
//										.println(curP.id + "||" + curP.writer_count + " adet yazici serbest birakildi");
//								System.out.println(
//										curP.id + "||" + curP.scanner_count + " adet tarayici serbest birakildi");
//								System.out.println(curP.id + "||" + curP.modem_count + " adet modem serbest birakildi");
//								System.out.println(curP.id + "||" + curP.cd_count + " adet cd serbest birakildi");
								curP.process_status = Status.terminated;

							}

							prioritySecond.remove(i);
							didP2Run = true;
							break;

						} else {
							// eger hic bir prosesin calısması icin yeterli kaynak yoksa diger oncelige
							// gecer
							didP2Run = false;
						}
					}
				}

			}
		}
		if (!priorityThird.isEmpty() && !didP2Run && !didP1Run)

		{

			Process curP;
			for (int i = 0; i < priorityThird.size(); i++) {

				curP = priorityThird.get(i);
				if (curP.process_time >= 20) {

					priorityThird.remove(i);

				} else {
					if (curP.has_allocated) {

						curP.process_status = Status.running;
						curP.burst_time--;
						if (curP.burst_time > 0) {
							curP.process_status = Status.ready;
							priorityThird.remove(i);
							priorityThird.add(curP);

						} else {
							writer.release(curP.writer_count);
							scanner.release(curP.scanner_count);
							modem.release(curP.modem_count);
							cd.release(curP.cd_count);

//							System.out.println(curP.id + "||" + curP.writer_count + " adet yazici serbest birakildi");
//							System.out
//									.println(curP.id + "||" + curP.scanner_count + " adet tarayici serbest birakildi");
//							System.out.println(curP.id + "||" + curP.modem_count + " adet modem serbest birakildi");
//							System.out.println(curP.id + "||" + curP.cd_count + " adet cd serbest birakildi");
							curP.process_status = Status.terminated;
							priorityThird.remove(i);
						}

						break;
					} else {
						if (writer.isAllocatable(curP.writer_count) && curP.writer_count >= 0
								&& scanner.isAllocatable(curP.scanner_count) && curP.scanner_count >= 0
								&& modem.isAllocatable(curP.modem_count) && curP.modem_count >= 0
								&& cd.isAllocatable(curP.cd_count) && curP.cd_count >= 0) {

							writer.allocate(curP.writer_count);
							scanner.allocate(curP.scanner_count);
							modem.allocate(curP.modem_count);
							cd.allocate(curP.cd_count);

							curP.has_allocated = true;
							curP.process_status = Status.running;
							curP.burst_time--;
							if (curP.burst_time > 0) {
								curP.process_status = Status.ready;
								priorityThird.remove(i);
								priorityThird.add(curP);

							} else {// proses sonlanirsa kaynaklar serbest birakilir.
								writer.release(curP.writer_count);
								scanner.release(curP.scanner_count);
								modem.release(curP.modem_count);
								cd.release(curP.cd_count);

//								System.out
//										.println(curP.id + "||" + curP.writer_count + " adet yazici serbest birakildi");
//								System.out.println(
//										curP.id + "||" + curP.scanner_count + " adet tarayici serbest birakildi");
//								System.out.println(curP.id + "||" + curP.modem_count + " adet modem serbest birakildi");
//								System.out.println(curP.id + "||" + curP.cd_count + " adet cd serbest birakildi");
								curP.process_status = Status.terminated;
								priorityThird.remove(i);
							}

							break;
						}
					}

				}

			}
		}
	}
}
