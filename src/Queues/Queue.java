package Queues;

import java.util.Vector;

// synchronized queue
public class Queue<T> {
	private Vector<T> Queue_vec;

	public Queue() {
		Queue_vec = new Vector<T>();
	}

	/**
	 * insert element into Queue vector
	 * 
	 * @param element
	 */
	public synchronized void push(T element) {
		Queue_vec.add(element);
	}

	/**
	 * safe remove function
	 * 
	 * @return first element in Queue vector
	 */
	public synchronized T pop() {
		if (!Queue_vec.isEmpty())
			return Queue_vec.remove(0);
		else
			return null;
	}

	/**
	 * @return is the Queue vector empty
	 */
	public synchronized Boolean isEmpty() {
		return Queue_vec.size() == 0;
	}
}
