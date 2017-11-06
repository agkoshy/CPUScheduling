package cpuscheduling;
import java.util.*;

//what data structure do we use to construct the queue?

public class Queue<T>{
	
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
		
		public void setNext(Node<T> next)
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
	
	public void addHead(Node<T> process)
	{
		Node<T> newNode = new Node<T>();
		if (head == null)
		{
			newNode.setNext(null);
			head = newNode;
		}
		else
		{
			newNode.next = head;
			head = newNode;
		}
	}
	
	public void insertAfter(Node<T> Pointer, Node<T> node)
	{
		Node<T>	newNode = new Node<T>();
		newNode.process = node.process;
		
		if (head == null)
		{
			newNode.setNext(null);
			head = newNode;
		}
		else
		{
			Node<T> p = head;
			do
			{
				p = p.next;
				if (p == null)
					System.out.println("Error");
			}
			while ( p != Pointer) ;

			newNode.next = p.next;
			p.next = newNode;
			
		}
	}
	
	public void removeHead(T process)
	{
		if (head == null)
			System.out.println("Underflow");
		Node<T> temp = head;
		head = temp.getNext();
		if (head == null)
			tail = null;
	}
	
	public void removeTail(T process)
	{
		if (head == null)
			System.out.println("Underflow");
		else
		{
			Node<T> temp1 = head;
			Node<T>	temp2 = null;
			if (temp1.next == null)
			{
				head = null;
				temp1 = null;
			}
			else
			{
				while (temp1.next != null)
				{
					temp2 = temp1;
					temp1.next = temp1;
				}
			}
			temp2.next = null;
			temp1 = null;
		}
	}
	
	public void deletePoint(Node<T> node)
	{
		if (head == null)
			System.out.println("Empty queue");
		else
		{
			Node<T> temp1, temp2;
			temp1 = head;
			do
			{
				temp1 = temp1.next;
				temp2 = temp1;
				if (temp1 == tail)
				{
					System.out.println("End of list, process not found");
					break;
				}
				else if (temp1 == node)
				{
					if (node == tail)
					{
						tail = null;
						head = null;
						temp1 = null;
					}
					else
					{
						if (temp1 == head)
						{
							head = head.next;
							temp1 = null;
						}
						else if (temp1 != head)
						{
							if (temp1.next == null)
							{
								temp2.next = null;
								temp1 = null;
							}
							else
							{
								temp2.next = temp1.next;
								temp1 = null;
							}
						}
					}
				}
			}
			while ( temp1 != node || temp1 != tail);
			
		}
	}

	public Queue()
	{
		this.head = null;
		this.tail = null;
	}
	
	
}
