package cpuscheduling;
import java.util.LinkedList;

//what data structure do we use to construct the queue?

public class Queue<T> {
	
	private Node<T> head;
	private Node<T> tail;
	
	private class Node<T>
	{
		private T process;
		private Node<T> next;
		
		public T getProcess()
		{
			return process;
		}
		
		public void setProcess(T process)
		{
			this.process = process;
		}
		
		public Node<T> getNext()
		{
			return next;
		}
		
		public void setNext(Node<T> newNode)
		{
			this.next = next;
		}
	}
	
	public void add(T process)
	{
		Node<T> newNode = new Node<T>();
		newNode.setProcess(process);
		
		if (head == null)
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
			tail.setNext(newNode);
			tail = newNode;
		}
	}
	
	public void removeLead(T process)
	{
		if (head == null){
			System.out.println("Underflow");
		}
		Node<T> temp = head;
		head = temp.getNext();
		if (head == null)
			tail = null;
	}

	public Queue()
	{
		
	}
	
	
}
