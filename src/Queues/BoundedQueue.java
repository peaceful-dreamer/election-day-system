package Queues;
import java.util.Vector;

public class BoundedQueue<T> {

	private Vector<T> Queue_vec;
	private int bound;
	/**
	 * Constructor
	 * @param bound - upper bound
	 */
	public BoundedQueue(int bound) {
		Queue_vec = new Vector<T>();
		this.bound = bound;
	}
	
	/**
	 * insert element into Queue vector
	 * @param element
	 */
	public boolean push(T element)
	{
		if(Queue_vec.size() < bound)
			Queue_vec.add(element);
		else
			return false;
		return true;
	}
	
	/**
	 * safe remove function
	 * @return first element in Queue vector
	 */
	public T pop()
	{
		if(Queue_vec.size() > 0)
			return Queue_vec.remove(0);
		else
			return null;
	}
	
	/**
	 * @return true if Queue full to bound
	 */
	public boolean isFull()
	{
		if(Queue_vec.size() == bound)
			return true;
		return false;
	}
	
	/**
	 * @return size of Queue vector
	 */
	public int size()
	{
		return Queue_vec.size();
	}
}
