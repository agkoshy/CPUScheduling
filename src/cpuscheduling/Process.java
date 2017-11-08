package cpuscheduling;

public class Process {
	
	int arrivalTime;
	int reqTime;
	int priority;
	
	public Process(int arrivalTime, int reqTime, int priority)
	{
		this.arrivalTime = arrivalTime;
		this.reqTime = reqTime;
		this.priority = priority;
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
}
