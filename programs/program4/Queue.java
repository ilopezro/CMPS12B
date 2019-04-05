/**
 * Isai Lopez
 * ilopezro
 * Program 4
 * Feb 9, 2018
 * (Doc Description)
 */

public class Queue implements QueueInterface 
{
	//create Node class within Queue class
	private class Node
	{
		Object mValue; 
		Node mNext; 

		private Node(Object value)
		{
			mValue = value; 
			mNext = null; 
		}
	}

	// reference to first node in list + numItem
	private Node head; 
	private int numItems; 

	// constructor for Queue class
	public Queue()
	{
		head = null;
		numItems = 0;
	}

	//checks if queue is empty 
	public boolean isEmpty() 
	{
		if(head == null)
			return true; 
		else
			return false;
	} 

	//returns length of queue
	public int length() { return numItems; } 

	//adds item to end of list 
	public void enqueue(Object newItem)
	{
		Node toAdd = new Node(newItem); 
		if(numItems == 0)
		{
			head = toAdd; 
		}
		else
		{
			Node temp = head; 
			while(temp.mNext!=null)
				temp = temp.mNext; 
			temp.mNext = toAdd; 
		}
		numItems++; 
	}

	//takes item out from the front and returns remaining queue
	public Object dequeue() throws QueueEmptyException
	{
		if(numItems == 0)
			throw new QueueEmptyException("Cannot perform dequeue: queue is empty");
		
		Node temp = head; 
		head = head.mNext;
		numItems--; 
		return temp.mValue; 

	} 

	//returns first item in queue
	public Object peek() throws QueueEmptyException 
	{
		if(numItems == 0)
			throw new QueueEmptyException("Cannot perform peek: queue is empty"); 
		else
			return head.mValue; 
	}

	//removes everything in queue 
	public void dequeueAll() throws QueueEmptyException
	{
		if(numItems == 0)
			throw new QueueEmptyException("Cannot dequeue all: queue is empty"); 
		else
		{
			numItems = 0; 
			head = null; 
		}
	}

	//toString method that returns the String of the queue 
	public String toString() 
	{
		String str = ""; 
		Node temp = head; 
		while(temp != null)
		{
			str += temp.mValue + " ";
			temp = temp.mNext; 
		}
		return str; 	
	}
}