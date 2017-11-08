package cpuscheduling;

public class Schedule {
	
	Process process;
	int size, quantum;
	int[] burst, wait, turnaround;
	public Schedule()
	{
		process = new Process();
//		this.size = process.numOfProcesses();
//		this.burst = process.burstTime();
//		this.quantum = process.quantumTime();
		
	}
	
	/*
	 * Nonpreemptive First-Come, First-Served (FCFS) Scheduling
	 */
	
	private void NP_FCFS()
	{
		
	}
	
	
	/*
	 * Nonpreemptive Shortest-Job-First (SJF) Scheduling
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
	 * Nonpreemptive Priority Scheduling
	 */
	
	private void NP_Priority()
	{
		
	}
	
	/*
	 * Preemptive Priority Scheduling
	 */
	
	private void P_Priority()
	{
		
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
				if(prc[i]>0)
					flag=1;
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
