package cpuscheduling;

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

	/*
	 * 
	 * public void add(Node<Process> node) { Node<Process> newNode = new
	 * Node<Process>(node.content); newNode = node;
	 * 
	 * if (isEmpty()) { head = newNode; tail = newNode; } else {
	 * tail.setNext(newNode); tail = newNode; } }
	 * 
	 * public void addHead(Node<Process> node) { Node<Process> newNode = new
	 * Node<Process>(node.content); newNode = node; if (isEmpty()) {
	 * newNode.setNext(null); head = newNode; } else { newNode.next = head; head
	 * = newNode; } }
	 * 
	 * public void insertAfter(Node<Process> Pointer, Node<Process> node) {
	 * Node<Process> newNode = new Node<Process>(node.content); newNode.content
	 * = node.content;
	 * 
	 * if (isEmpty()) { newNode.setNext(null); head = newNode; } else {
	 * Node<Process> p = head; do { p = p.next; if (p == null)
	 * System.out.println("Error"); } while ( p != Pointer) ;
	 * 
	 * newNode.next = p.next; p.next = newNode;
	 * 
	 * } }
	 * 
	 * public void removeHead(Node<Process> process) { if (isEmpty())
	 * System.out.println("Underflow"); Node<Process> temp = head; head =
	 * temp.getNext(); if (isEmpty()) tail = null; }
	 * 
	 * public void removeTail(Node<Process> process) { if (isEmpty())
	 * System.out.println("Underflow"); else { Node<Process> temp1 = head;
	 * Node<Process> temp2 = null; if (temp1.next == null) { head = null; temp1
	 * = null; } else { while (temp1.next != null) { temp2 = temp1; temp1.next =
	 * temp1; } } temp2.next = null; temp1 = null; } }
	 * 
	 * public void deletePoint(Node<Process> node) { if (isEmpty())
	 * System.out.println("Empty queue"); else { Node<Process> temp1, temp2 =
	 * null; temp1 = head; do { temp1 = temp1.next; if (temp1 == tail) {
	 * System.out.println("End of list, process not found"); break; } else if
	 * (temp1 == node) { if (node == head && node == tail) { tail = null; head =
	 * null; temp1 = null; } else { if (temp1 == head) { head = head.next; temp1
	 * = null; } else if (temp1 != head) { if (temp1.next == null) { temp2.next
	 * = null; temp1 = null; } else { temp2.next = temp1.next; temp1 = null; } }
	 * } } temp2 = temp1; } while ( temp1 != node || temp1 != tail);
	 * 
	 * } }
	 * 
	 */

	public Queue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

}
