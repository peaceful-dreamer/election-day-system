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
	public void push(T element) {
		synchronized (Queue_vec) {
			Queue_vec.add(element);
		}
	}

	/**
	 * safe remove function
	 * 
	 * @return first element in Queue vector
	 */
	public T pop() {
		synchronized (Queue_vec) {
			if (!Queue_vec.isEmpty())
				return Queue_vec.remove(0);
			else
				return null;
		}

	}

	/**
	 * @return is the Queue vector empty
	 */
	public Boolean isEmpty() {
		synchronized (Queue_vec) {
			return Queue_vec.size() == 0;
		}
	}
}
