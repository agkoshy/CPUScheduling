package cpuscheduling;

import java.util.ArrayList;
import java.util.Scanner;

public class Process {
	
	int arrivalTime;
	int reqTime;
	int priority;
	ArrayList<Integer> cpuTime;
	ArrayList<Integer> IO;
	ArrayList<Integer> device;
	Scanner scn = new Scanner(System.in);
	
	public Process(int arrivalTime, int reqTime, int priority)
	{
		this.arrivalTime = arrivalTime;
		this.reqTime = reqTime;
		this.priority = priority;
	}
	public Process(int arrivalTime, ArrayList<Integer> cpuTime, int priority, ArrayList<Integer> IO, ArrayList<Integer> device)
	{
		this.arrivalTime = arrivalTime;
		this.cpuTime = cpuTime;
		this.priority = priority;
		this.IO = IO;
		this.device = device;
	}
	
	public Process(int arrivalTime, int reqTime) {
		this.arrivalTime = arrivalTime;
		this.reqTime = reqTime;
	}
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
	
	public ArrayList<Integer> getIO() 
	{
		return this.IO;
	}
	public ArrayList<Integer> getDevice()
	{
		return this.device;
	}
	public ArrayList<Integer> getCpuTime()
	{
		return this.cpuTime;
	}
	
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

	
}
