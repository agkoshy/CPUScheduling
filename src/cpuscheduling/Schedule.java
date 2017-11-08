package cpuscheduling;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class Schedule extends Queue{
	
	static Queue queue = new Queue();

	Process process;
	int size, quantum;
	int[] burst, wait, turnaround;
	public Schedule()
	{
		//		this.size = process.numOfProcesses();
		//		this.burst = process.burstTime();
		//		this.quantum = process.quantumTime();

	}

	/*
	 * Non-preemptive First-Come, First-Served (FCFS) Scheduling
	 */

	private void NP_FCFS()
	{
		int t1 = 0;
		int t2 = 0;
		wait[0]=0;
		for(int i=0;i<size;i++)
		{
			wait[i]=burst[i]+wait[i];
			t1+=wait[i];
		}
		for(int i=0;i<size;i++)
		{
			turnaround[i]=burst[i]+wait[i];
			t2+=turnaround[i];
		}
		System.out.println("Average Waiting time= "+ t1/size);
		System.out.println("Average Turn Around time= "+ t2/size);
	}


	/*
	 * Non-preemptive Shortest-Job-First (SJF) Scheduling
	 */


	private void NP_SJF()
	{

	}


	/*
	 * Preemptive SJF (Shortest-Remaining-Time-First) Scheduling
	 */

	private void P_SRTF()
	{

	}


	/*
	 * Non-preemptive Priority Scheduling
	 * Highest priority is current running process
	 */
	
	private static void NP_Priority(ArrayList<Process> list)
	{
		Process temp;
		
		// insertion sort to sort prioritized processes
		for (int i = 0; i < list.size(); i++)
		{
			for (int j = i; 0 < j; j--)
			{
				if ( list.get(j).getPriority() < list.get(j - 1).getPriority())
				{
					temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j -1, temp);
				}
			}
		}
	}

	/*
	 * Preemptive Priority Scheduling
	 * Runs by priority until completion
	 */
	
	private static void P_Priority(ArrayList<Process> list)
	{
		Process temp;
		for (int i = 0; i < list.size(); i++)
		{
			for (int j = i; 0 < j; j--)
			{
				if ( list.get(j).getPriority() < list.get(j - 1).getPriority())
				{
					temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j -1, temp);
				}
			}
		}
		
	}

	/*
	 * Preemptive Round-Robin (RR) Scheduling
	 */

	private void P_RR()
	{
		int flag;
		int bt = 0;
		int ta = 0;
		int[] prc = new int[size];
		do{
			flag=0;
			for(int i=0; i<size; i++)
			{
				if(prc[i]>=quantum)
				{
					System.out.print("P"+(i+1));
					for(int j=0; j<size; j++)
					{
						if(j==i)
							prc[i]=prc[i]-quantum;
						else if(prc[j]>0)
							wait[j]+=quantum;
					}
				}
				else if(prc[i]>0)
				{
					System.out.print("P"+(i+1));
					for(int j=0;j<size;j++)
					{
						if(j==i)
							prc[i]=0;
						else if(prc[j]>0)
							wait[j]+=prc[i];
					}
				}
			}
			for(int i=0;i<size;i++) 
			{
				if(prc[i]>0) 
				{
					flag=1;
				}

			}

		}while(flag==1);
		for(int i=0;i<size;i++) {
			turnaround[i]=wait[i]+burst[i];
		}

		for(int i = 0; i < size; i++)
		{
			bt = bt + wait[i];
			ta  = ta + turnaround[i];
		}

		System.out.println("Waiting time: " + bt/size);
		System.out.println("Turnaround time: " + ta/size);
	}

	/*
	 * Multilevel Queue Scheduling
	 */

	private void MLQ()
	{

	}

	/*
	 * Multilevel Feedback Queue Scheduling
	 */

	private void MLFQ()
	{

	}


	public static void main()
	{
		ArrayList<Process> list = new ArrayList<Process>();
		for (int i = 0; i < 5; i++)
		{
			list.add((Process) new Process(i, new Random().nextInt(6)  , new Random().nextInt(6)));
		}
		NP_Priority(list);
		//build array of processes
		//call a schedule using said array
		//schedule will build the queue with respective rules
	}

	private void average()
	{
		//average waiting time
		//average turnaround time
	}
}
