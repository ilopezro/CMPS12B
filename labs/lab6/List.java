/**
 * Isai Lopez
 * ilopezro
 * Lab 6
 * Mar 4, 2018
 * (Doc Description)
 */

@SuppressWarnings("overrides")
public class List<T> implements ListInterface<T> {

	// private inner Node class
	private class Node {
		T item;
		Node next;

		Node(T x){
			item = x;
			next = null;
		}
	}

	// Fields for the List class
	private Node head;     // reference to first Node in List
	private int numItems;  // number of items in this List

	// List()
	// constructor for the List class
	public List(){
		head = null;
		numItems = 0;
	}

	// find()
	// returns a reference to the Node at position index in this List
	private Node find(int index){
		Node N = head;
		for(int i=1; i<index; i++){
			N = N.next;
		}
		return N;
	}

	public boolean isEmpty() {
		return (numItems ==0);
	}

	public int size() {
		return numItems;
	}

	public T get(int index) throws ListIndexOutOfBoundsException {
		if(index<1 || index>size())
			throw new ListIndexOutOfBoundsException("get(): invalid index: " + index);
		Node N = find(index); 
		return N.item;
	}

	public void add(int index, T newItem) throws ListIndexOutOfBoundsException {
		if(index<1 || index>(numItems+1))
			throw new ListIndexOutOfBoundsException("add(): invalid index: " + index);
		Node toAdd = new Node(newItem); 
		if(index == 1){
			toAdd.next = head;
			head = toAdd;
		}
		else {
			Node before = find(index-1); 
			Node after = before.next; 
			before.next = toAdd; 
			before = before.next;
			before.next = after; 
		}
		numItems++; 
	}

	public void remove(int index) throws ListIndexOutOfBoundsException {
		if(index<1||index>numItems)
			throw new ListIndexOutOfBoundsException("remove(): invalid index: " + index); 
		if(index == 1)
		{
			Node H = head; 
			head = head.next; 
			H.next = null; 
		}else {
			Node before = find(index-1);
			Node after = before.next;
			before.next = after.next;
			after.next = null;
		}
		numItems--; 
	}

	public void removeAll() {
		head = null; 
		numItems = 0; 

	}

	private String myString(Node H){
		if( H==null ){
			return "";
		}else{
			return (H.item + " " + myString(H.next));
		}
	}

	public String toString(){
		return myString(head);
	}

	// equals()
	// pre: none
	// post: returns true if this List matches rhs, false otherwise
	// Overrides Object's equals() method
	@SuppressWarnings("unchecked")
	public boolean equals(Object rhs){
		boolean eq = false;
		List<T> R = null;
		Node N = null;
		Node M = null;

		if(rhs instanceof List){
			R = (List<T>)rhs;
			eq = ( this.numItems == R.numItems );

			N = this.head;
			M = R.head;
			while(eq && N!=null){
				eq = (N.item == M.item);
				N = N.next;
				M = M.next;
			}
		}
		return eq;
	}
}