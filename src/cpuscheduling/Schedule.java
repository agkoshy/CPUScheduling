package cpuscheduling;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class Schedule extends Queue{
	
	static Queue queue = new Queue();

	public Schedule()
	{
		
	}
	
	/*
	 * Non-preemptive First-Come, First-Served (FCFS) Scheduling
	 */
	
	private void NP_FCFS()
	{
		
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
	 * Preemptive Round-Robin (RR) Scheduling
	 */
	
	private void P_RR()
	{
		
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
		
	}
	
	private void average()
	{
		//average waiting time
		//average turnaround time
	}
}
