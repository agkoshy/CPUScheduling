package cpuscheduling;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Process class
 *
 * This class contains the definition of what a Process contains. There are various constructors to define the 
 * many different types of processes that need defined and send for parsing.
 *
 * @author Alvis Koshy, Zhu Su
 * @version 1.0
 * @since 2017-11-14
 */
public class Process {

	int arrivalTime;
	int reqTime;
	int priority;
	int[] cpuTime;
	int[] IO;
	int[] device;
	Scanner scn = new Scanner(System.in);

	/**
	 * A constructor used to define a process with priorities
	 * 
	 * @param arrivalTime	arrival time of process
	 * @param reqTime		cpu time of process to execute	
	 * @param priority		level of priority
	 */
	public Process(int arrivalTime, int reqTime, int priority) {
		this.arrivalTime = arrivalTime;
		this.reqTime = reqTime;
		this.priority = priority;
	}
	
	/**
	 * A constructor used to define a process that contain IO devices
	 * 
	 * @param arrivalTime	arrival time of process
	 * @param cpuTime		array of cpu times
	 * @param priority		level of priority if needed
	 * @param IO			array of io times
	 * @param device		array of device numbers
	 */
	public Process(int arrivalTime, int[] cpuTime, int priority, int[] IO, int[] device)
	{
		this.arrivalTime = arrivalTime;
		this.cpuTime = cpuTime;
		this.priority = priority;
		this.IO = IO;
		this.device = device;
	}
	/**
	 * A constructor that contains arrival time and burst time
	 * @param arrivalTime	arrival time of process
	 * @param reqTime		cpu time of process
	 */
	public Process(int arrivalTime, int reqTime) {
		this.arrivalTime = arrivalTime;
		this.reqTime = reqTime;
	}
	
	/**
	 * Getter method for arrival time
	 * @return
	 * 		arrivalTime - holds an int representing arrival time
	 */
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	/**
	 * Getter method of single burst time
	 * @return
	 *		 reqTime - holds an int representing cpu burst time
	 */		
	public int getReqTime() {
		return this.reqTime;
	}
	/**
	 * Getter method of priority level
	 * @return
	 *		 priority - holds an int representing priority
	 */	
	public int getPriority() {
		return this.priority;
	}
	
	/**
	 * Getter method for IO
	 * @return
	 * 		IO - holds an int array of IO, represents the multiple IO times
	 */
	
	public int[] getIO() 
	{
		return this.IO;
	}
	/**
	 * Getter method for device
	 * @return
	 * 		device - holds an int array of device, represents the multiple devices
	 */
	public int[] getDevice()
	{
		return this.device;
	}
	/**
	 * Getter method for cpuTime
	 * @return
	 * 		cpuTime - holds an int array of cpuTime, represents the multiple cpuTimes times
	 */
	public int[] getCpuTime()
	{
		return this.cpuTime;
	}

}
