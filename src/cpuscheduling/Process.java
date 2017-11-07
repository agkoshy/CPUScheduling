package cpuscheduling;

public class Process {
	
	int arrivalTime;
	int reqTime;
	int priority;
	
	/*
	private enum state
	{
		NEW, RUNNING, READY, WAITING, TERMINATED
	}
	*/
	
	public Process()
	{
		//empty
	}
	
	public Process(int arrivalTime, int reqTime, int priority)
	{
		this.arrivalTime = arrivalTime;
		this.reqTime = reqTime;
		this.priority = priority;
	}

}
