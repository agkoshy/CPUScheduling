package cpuscheduling;

import java.util.Scanner;

public class Process {
	
	int arrivalTime;
	int reqTime;
	int priority;
	int[] burst;
	Scanner scn = new Scanner(System.in);
	
	public Process(int arrivalTime, int reqTime, int priority)
	{
		this.arrivalTime = arrivalTime;
		this.reqTime = reqTime;
		this.priority = priority;
//		burst = new int[this.numOfProcesses()];
	}
<<<<<<< HEAD
	
	public int getArrivalTime()
	{
		return this.arrivalTime;
	}
	
	public int getReqTime()
	{
		return this.reqTime;
	}
	
	public int getPriority()
	{
		return this.priority;
	}
=======
//	
//	public int numOfProcesses() {
//		System.out.println("Enter number of Processes");
//		return scn.nextInt();
//	} 
//	
//	public int[] burstTime() {
//		int i;
//		System.out.println("Enter burst time");
//		for(i = 0; i < this.numOfProcesses(); i++) {
//			burst[i] = scn.nextInt();
//		}
//		return burst;
//	}
//	
//	public int quantumTime() {
//		System.out.println("Enter quantum time");
//		return scn.nextInt();
//	}

>>>>>>> branch 'master' of https://github.com/agkoshy/CPUScheduling.git
}
