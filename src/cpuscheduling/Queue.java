package cpuscheduling;
/**
 * Queue class
 *
 * This class is a simple queue class that contains all the necessary tools required to manipulate the queue.
 * Queues are used to store process that have been scheduled to sort.
 *
 * @author Alvis Koshy, Zhu Su
 * @version 1.0
 * @since 2017-11-14
 */
public class Queue {

	private Node<Process> head;
	private Node<Process> tail;
	private int size;

	@SuppressWarnings("hiding")
	public static class Node<Process> {
		private Process content;
		private Node<Process> next;

		public Node(Process process) {
			this.content = process;
		}

		public Node() {

		}

		public Node<Process> getNext() {
			return next;
		}

		public void setNext(Node<Process> next) {
			this.next = next;
		}
	}

	public boolean isEmpty() {
		return head == null;
	}

	public Process peek() {
		if (isEmpty())
			System.out.println("No processes");
		return head.content;
	}

	public int size() {
		return this.size;
	}

	public void enqueue(Process process) {
		Node<Process> newNode = new Node<Process>();
		newNode.content = process;
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size = size + 1;
	}

	public Process dequeue() throws Exception {
		if (isEmpty())
			throw new Exception("Nothing to dequeue");
		else {
			Node<Process> temp;
			temp = head;
			head.next = head;
			size = size - 1;
			return temp.content;
		}
	}

	public Queue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

}
