/**
 * Isai Lopez
 * ilopezro
 * Program 3
 * Feb 2, 2018
 * (Doc Description)
 */

public class Dictionary implements DictionaryInterface 
{
	// inner Node class w
	private class Node 
	{
		String mKey;
		String mValue; 
		Node next;

		private Node(String key, String value){
			mKey = key;
			mValue = value; 
			next = null;
		}
	}

	private Node head;     // reference to first Node in List
	private int numItems;  // number of items in this IntegerList

	// constructor for Dictionary class
	public Dictionary()
	{
		head = null;
		numItems = 0;
	}

	// return a reference to the Node containing its argument key
	//, or return null if no such Node exists
	//	Note: findKey() will help with: insert(), delete() and lookup().
	private Node findKey(String key)
	{
		Node N = head;
		for(Node H = N; H!=null; H= H.next){
			if(H.mKey == key)
				return H; 
		}
		return null;
	}

	public boolean isEmpty() 
	{
		if(head == null)
			return true;
		else
			return false; 
	}

	public int size() 
	{
		return numItems;
	}

	public String lookup(String key) 
	{
		if(findKey(key) != null)
		{
			Node temp = head; 
			while(temp != findKey(key))
			{
				temp = temp.next; 
			}
			return temp.mValue;
		}
		else 
			return null; 
	}

	public void insert(String key, String value) throws DuplicateKeyException
	{
		if(findKey(key) != null)
			throw new DuplicateKeyException("cannot insert duplicate keys"); 

		Node toAdd = new Node(key, value); 
		if (numItems == 0)
		{
			head = toAdd; 
		}
		else
		{
			Node temp = head; 
			while (temp.next != null)
				temp = temp.next; 
			temp.next = toAdd; 
		}
		numItems++; 
	}

	public void delete(String key) throws KeyNotFoundException 
	{
		if(findKey(key) == null)
			throw new KeyNotFoundException("cannot delete non-existent key"); 
		else
		{
			if(head == findKey(key))
			{
				Node temp = head; 
				head = temp.next; 
			}
			else
			{
				Node temp = head; 
				while(temp.next != findKey(key))
				{
					temp = temp.next; 
				}
				temp.next = temp.next.next;
			}
			numItems--; 
		}
	}

	public void makeEmpty() 
	{
		head = null; 
		numItems = 0; 
	}

	public String toString()
	{
		String toString = ""; 
		Node temp = head; 
		while(temp != null)
		{
			toString = toString + temp.mKey + " " + temp.mValue + "\n"; 
			temp = temp.next; 
		}
		return toString;
	}
}