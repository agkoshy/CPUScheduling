package cpuscheduling;

import java.util.ArrayList;
import java.util.Random;
/**
 * Schedule class
 *
 * This class contains all the scheduling algorithms detailed in the assignment. It utilizes in conjunction to User and Process, where User defines
 * what the processes are the Process define what method is used for scheduling.
 *
 * @author Alvis Koshy, Zhu Su
 * @version 1.0
 * @since 2017-11-14
 */
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
	static boolean newP = false;
	static boolean readyP = false;
	static boolean terminated = false;
	static boolean waitingP = false;
	static boolean runningP = false;

	public Schedule() {
		
	}

	/**
	 * Non-preemptive First-Come, First-Served (FCFS) Scheduling
	 * Sorts the process based on time of arrival
	 * 
	 * NOTE: Our program runs on a predetermined arrival times but the logic behind sorting them based on the arrival
	 * times still hold.
	 * 
	 * 
	 * @param list
	 * 
	 * 	Takes a list parameter
	 */

	static void NP_FCFS(ArrayList<Process> list) {
		Process temp;
		size = list.size();
		wait = new int[size];
		turnaround = new int[size];
		for (int i = 0; i < size; i++) {
			newP = true;
			System.out.println("P" + list.get(i).getArrivalTime() + " state: New");
			System.out.println("P" + list.get(i).getArrivalTime() + " state: Ready");

			for (int j = i; 0 < j; j--) {
				//iterates through the list and checks for the arrivaltime of the previous with the current
				if (list.get(j).getArrivalTime() < list.get(j - 1).getArrivalTime()) {
					temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j - 1, temp);
				}
				wait[j] = list.get(j).getReqTime() - list.get(j).getArrivalTime();
				turnaround[i] = list.get(i).getReqTime() + wait[i];
			}
			System.out.println("P" + list.get(i).getArrivalTime() + " state: Terminated");
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

	/**
	 * Non-preemptive Shortest-Job-First (SJF) Scheduling
	 * Sorts the process based on the process that takes the cpu the shortest amount of time to execute.
	 * 
	 *  
	 * @param list
	 * 
	 * 	Takes a list parameter
	 */

	static void NP_SJF(ArrayList<Process> list) {
		Process temp;
		size = list.size();
		wait = new int[size + 1];
		turnaround = new int[size];
		for (int i = 0; i < list.size(); i++) {
			System.out.println("P" + list.get(i).getArrivalTime() + " state: New");

			for (int j = i; 0 < j; j--) {
				System.out.println("P" + list.get(j).getArrivalTime() + " state: Ready");
				//iterates through the list and compares for burst time, swapping if one is lower than the other if necessary
				if (list.get(j).getReqTime() < list.get(j - 1).getReqTime()) {
					
					temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j - 1, temp);
				}
			System.out.println("P" + list.get(j).getArrivalTime() + " state: Terminated");

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

	/**
	 * Preemptive SJF (Shortest-Remaining-Time-First) Scheduling
	 * The processes are sorted by shortest remaining time, relies on IO bursting.
	 * 
	 * 
	 * @param list
	 * 
	 * 	Takes a list parameter
	 */

	static void P_SRTF(ArrayList<Process> list) {
		int[] cpu;
		int[] cpuB4;
		int[] c;
		Process temp;
		size = list.size();
		wait = new int[size + 1];
		turnaround = new int[size];
		//iterates through the list
		for (int i = 0; i < list.size(); i++) {
			for (int j = i; 0 < j; j--) {
				//sets the getter arrays into an array
				cpu = list.get(j).getCpuTime();
				cpuB4 = list.get(j-1).getCpuTime();
				//iterates through the indices of the arrays and compares it with the next process
				for(int k = 0; k < cpu.length; k++) {
					if (cpu[k] < cpuB4[k]) {
						temp = list.get(j);
						list.set(j, list.get(j - 1));
						list.set(j - 1, temp);
					}
				}
				
			}
		}
		for (int i = 0; i < size; i++) {
			c = list.get(i).getCpuTime();
			turnaround[i] = c[i] + wait[i];
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

	/**
	 * Non-preemptive Priority Scheduling
	 * 
	 * Highest priority is current running process
	 * 
	 * * 
	 * @param list
	 * 
	 * 	Takes a list parameter
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

	/**
	 * Preemptive Priority Scheduling
	 * 
	 * Runs by priority until completion
	 * 
	 * 
	 * @param list
	 * 
	 * 	Takes a list parameter
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

	/**
	 * Preemptive Round-Robin (RR) Scheduling
	 * Schedules process in a round robin sort. Gated by a quantum number.
	 * 
	 * 
	 * @param list
	 * 
	 * 	Takes a list parameter
	 */

	static void P_RR(ArrayList<Process> list) {
		int flag;
		size = list.size();
		int n;
		//a random quantum number between 1-6
		int quantum = new Random().nextInt(5) + 1;
		do {
			flag = 0;
			for (int i = 0; i < size; i++) {
				//if the time left is greater than the quantum, subtract it from the quantum
				if (list.get(i).getReqTime() >= quantum) {
					System.out.print("P" + (i + 1));
					for (int j = 0; j < size; j++) {
						if (j == i) {
							n = list.get(i).getReqTime(); 
							n = list.get(i).getReqTime() - quantum;
						} else if (list.get(j).getReqTime() > 0)
							wait[j] += quantum;
					}
					//if less than quantum and greater than 0, add the remaining time in waiting time
				} else if (list.get(i).getReqTime() > 0) {
					System.out.print("P" + (i + 1));
					for (int j = 0; j < size; j++) {
						if (j == i) {
							n = list.get(i).getReqTime();
							n = 0;
						} else if (list.get(j).getReqTime() > 0)
							wait[j] += list.get(i).getReqTime();
					}
				}
			}
			for (int i = 0; i < size; i++) {
				if (list.get(i).getReqTime() > 0) {
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

	/**
	 * Multilevel Queue Scheduling
	 * Sorts based on the priority of scheduled processes and adds them to a queue. 
	 * Order is SJF, RR, FCFS, Priority
	 * 
	 * @param list
	 * 
	 * 	Takes a list parameter
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
		System.out.println(foreend);
	}

	/**
	 * Multilevel Feedback Queue Scheduling
	 * Sorts based on ability to move processes inbetween queues
	 *  
	 *  
	 * @param list
	 * 
	 * 	Takes a list parameter
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
	/**
	 * Readies the queue for scheduling, enqueues the process
	 * 
	 * @param list
	 * 		takes a list parameter
	 * @return 
	 * 		returns the queue
	 */
	public static Queue readyQueue(ArrayList<Process> list) {
		for (Process p : list) {
			queue.enqueue(p);
		}
		return queue;
	}

	/**
	 * Takes the values that are passed by a scheduling method
	 * 
	 * @param x		 total waiting time
	 * @param y		 total turnaround time
	 * @param z		 size of list
	 */
	private static void average(double x, double y, int z) {
		if (x / z == 0 || y / z == 0) {
			System.out.println("Average wait time: " + (x / z) + "\nAverage turnaround time: " + (y / z));

		}
		System.out.println("Average wait time: " + (x / z) + "\nAverage turnaround time: " + (y / z));
	}
}
