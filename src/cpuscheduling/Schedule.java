package cpuscheduling;

import java.util.ArrayList;
import java.util.Random;

public class Schedule extends Queue {

	static Queue queue = new Queue();
	
	Process process;
	static int quota = 2;

	static int size;

	static int quantum = new Random().nextInt(10);
	static double waitTime = 0;
	static double turnAround = 0;
	static int[] burst;
	static int[] wait;
	static int[] turnaround;

	public Schedule() {
		// this.size = process.numOfProcesses();
		// this.burst = process.burstTime();
		// this.quantum = process.quantumTime();
	}

	/*
	 * Non-preemptive First-Come, First-Served (FCFS) Scheduling
	 */

	static void NP_FCFS(ArrayList<Process> list) {
		size = list.size();
		wait = new int[size];
		turnaround = new int[size];
		for (int i = 0; i < size; i++) {
			for (int j = i; 0 < j; j--) {

				wait[j] = list.get(j).getReqTime() - list.get(j).getArrivalTime();
				turnaround[i] = list.get(i).getReqTime() + wait[i];
			}
		}
		// build queue
		Queue q = readyQueue(list);

		// executing queue
		for (int i = 0; i < q.size(); i++) {
			try {
				Process p = q.dequeue();
				waitTime += p.reqTime - p.arrivalTime;
				turnAround += p.reqTime;
			} catch (Exception e) {

			}
		}

		average(waitTime, turnAround, size);
	}

	/*
	 * Non-preemptive Shortest-Job-First (SJF) Scheduling
	 */

	static void NP_SJF(ArrayList<Process> list) {
		Process temp;
		size = list.size();
		wait = new int[size + 1];
		turnaround = new int[size];
		for (int i = 0; i < list.size(); i++) {
			for (int j = i; 0 < j; j--) {
				if (list.get(j).getReqTime() < list.get(j - 1).getReqTime()) {
					temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j - 1, temp);
				}
			}
		}
		for (int i = 0; i < size; i++) {
			turnaround[i] = list.get(i).getReqTime() + wait[i];
			wait[i + 1] = turnaround[i];
		}

		// build queue
		Queue q = readyQueue(list);

		// executing queue
		for (int i = 0; i < q.size(); i++) {
			try {
				Process p = q.dequeue();
				waitTime += p.reqTime - p.arrivalTime;
				turnAround += p.reqTime;
			} catch (Exception e) {

			}
		}

		average(waitTime, turnAround, q.size());

	}

	/*
	 * Preemptive SJF (Shortest-Remaining-Time-First) Scheduling
	 */

	static void P_SRTF(ArrayList<Process> list) {
		int[] cpu;
		int[] cpuB4;
		Process temp;
		size = list.size();
		wait = new int[size + 1];
		turnaround = new int[size];
		for (int i = 0; i < list.size(); i++) {
			for (int j = i; 0 < j; j--) {
				cpu = list.get(j).getCpuTime();
				cpuB4 = list.get(j-1).getCpuTime();
				if (cpu[j] < cpuB4[j-1]) {
					temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j - 1, temp);
				}
			}
		}
		for (int i = 0; i < size; i++) {
			turnaround[i] = list.get(i).getReqTime() + wait[i];
			wait[i + 1] = turnaround[i];
		}

		// build queue
		Queue q = readyQueue(list);

		// executing queue
		for (int i = 0; i < q.size(); i++) {
			try {
				Process p = q.dequeue();
				waitTime += p.reqTime - p.arrivalTime;
				turnAround += p.reqTime;
			} catch (Exception e) {

			}
		}

		average(waitTime, turnAround, q.size());
	}

	/*
	 * Non-preemptive Priority Scheduling
	 * 
	 * Highest priority is current running process
	 */

	static void NP_Priority(ArrayList<Process> list) {
		Process temp;

		// insertion sort by priority
		for (int i = 0; i < list.size(); i++) {
			for (int j = i; 0 < j; j--) {
				if (list.get(j).getPriority() < list.get(j - 1).getPriority()) {
					temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j - 1, temp);
				}
			}
		}

		// build queue
		Queue q = readyQueue(list);

		// executing queue
		for (int i = 0; i < q.size(); i++) {
			try {
				Process p = q.dequeue();
				waitTime += p.reqTime - p.arrivalTime;
				turnAround += p.reqTime;
			} catch (Exception e) {

			}
		}
		average(waitTime, turnAround, q.size());
	}

	/*
	 * Preemptive Priority Scheduling
	 * 
	 * Runs by priority until completion
	 */

	static void P_Priority(ArrayList<Process> list) {
		Process temp;

		// insertion sort by priority
		for (int i = 0; i < list.size(); i++) {
			for (int j = i; 0 < j; j--) {
				if (list.get(j).getPriority() < list.get(j - 1).getPriority()) {
					temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j - 1, temp);
				}
			}
		}

		// build queue
		Queue q = readyQueue(list);

		// executing queue
		for (int i = 0; i < q.size(); i++) {
			try {
				Process p = q.dequeue();
				waitTime += p.reqTime - p.arrivalTime;
				turnAround += p.reqTime;
			} catch (Exception e) {

			}
		}
		average(waitTime, turnAround, q.size());
	}

	/*
	 * Preemptive Round-Robin (RR) Scheduling
	 */

	static void P_RR(ArrayList<Process> list) {
		int flag;
		size = list.size();
		int[] prc = new int[size];
		do {
			flag = 0;
			for (int i = 0; i < size; i++) {
				if (prc[i] >= quantum) {
					System.out.print("P" + (i + 1));
					for (int j = 0; j < size; j++) {
						if (j == i)
							prc[i] = prc[i] - quantum;
						else if (prc[j] > 0)
							wait[j] += quantum;
					}
				} else if (prc[i] > 0) {
					System.out.print("P" + (i + 1));
					for (int j = 0; j < size; j++) {
						if (j == i)
							prc[i] = 0;
						else if (prc[j] > 0)
							wait[j] += prc[i];
					}
				}
			}
			for (int i = 0; i < size; i++) {
				if (prc[i] > 0) {
					flag = 1;
				}

			}

		} while (flag == 1);

		for (int i = 0; i < size; i++) {
			turnaround[i] = wait[i] + burst[i];
		}

		for (int i = 0; i < size; i++) {
			waitTime = waitTime + wait[i];
			turnAround = turnAround + turnaround[i];
		}
		average(waitTime, turnAround, size);

	}

	/*
	 * Multilevel Queue Scheduling
	 */

	static void MLQ(ArrayList<Process> list) {
		
		ArrayList<Process> foreend = null;
		ArrayList<Process> backend = null;
		
		// insertion sort by priority
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).reqTime <= 1)
			{
				foreend.add(list.get(i));
			}
			else
				backend.add(list.get(i));
		}
		
		P_RR(foreend); // foreground P_RR(list);
		// queue quota process
		// assume certain process type, their priority are indicated by modulus
		NP_FCFS(backend); // background queue
	}

	/*
	 * Multilevel Feedback Queue Scheduling
	 */

	static void MLFQ(ArrayList<Process> list) {
		/*
		 * sorter to determine priority queue level of each process
		 */

		ArrayList<Process> q0 = null;
		ArrayList<Process> q1 = null;
		ArrayList<Process> q2 = null;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).reqTime <= quota) {
				q0.add(list.get(i));
			} else if (list.get(i).reqTime <= quota * 2) {
				q1.add(list.get(i));
			} else
				q2.add(list.get(i));
		}

		P_RR(q0); // Queue 0 quantum x
		P_RR(q1); // Queue 1 quantum y
		NP_FCFS(q2); // Queue 2
	}

	public static Queue readyQueue(ArrayList<Process> list) {
		for (Process p : list) {
			queue.enqueue(p);
		}
		return queue;
	}

	private static void average(double x, double y, int z) {
		if (x / z == 0 || y / z == 0) {
			System.out.println("Average wait time: " + (x / z) + "\nAverage turnaround time: " + (y / z));

		}
		System.out.println("Average wait time: " + (x / z) + "\nAverage turnaround time: " + (y / z));
	}
}
