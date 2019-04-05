/**
 * Isai Lopez
 * ilopezro
 * Lab 7
 * Mar 12, 2018
 * (Doc Description)
 */

public class Dictionary implements DictionaryInterface {

	//Private Methods 

	// inner Node class w
	private class Node 
	{
		String mKey;
		String mValue; 
		Node mLeft;
		Node mRight; 

		private Node(String key, String value){
			mKey = key;
			mValue = value; 
			mLeft = null;
			mRight = null; 
		}
	}

	private Node mRoot;     // reference to root node
	private int mNumPairs;  // number of items in this Dictionary

	// findKey()
	// returns the Node containing key k in the subtree rooted at mRoot, or returns
	// NULL if no such Node exists
	private Node findKey(Node root, String key)
	{ 
		if(root == null ||key.compareTo(root.mKey) == 0) {
			return root; 
		}
		if(key.compareTo(root.mKey)< 0) {
			return findKey(root.mLeft, key); 
		}else {
			return findKey(root.mRight, key); 
		}
	}
	
	//findParent()
	//return the parent of N in the subtree rooted at R, or return null 
	//if N is equal to R 
	//pre: mRoot != null
	private Node findParent(Node N, Node R){
		Node parentNode = null; 
		if(N != R) {
			parentNode = R; 
			while(parentNode.mLeft!= N && parentNode.mRight!=N) {
				if(N.mKey.compareTo(parentNode.mKey) < 0)
					parentNode = parentNode.mLeft; 
				else
					parentNode = parentNode.mRight; 
			}
		}
		return parentNode; 
	}
	
	// findLeftmost()
	// returns the leftmost Node in the subtree rooted at mRoot, or NULL if mRoot is NULL.
	private Node findLeftmost(){
		Node leftMost = this.mRoot; 
		if(leftMost!=null) { for(; leftMost.mLeft !=null; leftMost = leftMost.mLeft); }
		return leftMost; 
	}
	
	// printInOrder()
	// prints the (key, value) pairs belonging to the subtree rooted at R in order
	// of increasing keys to file pointed to by out.
	private String printInOrder(Node root){
		String toString = ""; 
		if(root != null) {
			toString += printInOrder(root.mLeft); 
			toString += root.mKey + " " + root.mValue + "\n";
			toString += printInOrder(root.mRight); 
		}
		return toString; 
	}
	
	// deleteAll()
	// deletes all Nodes in the subtree rooted at N.
	private void deleteAll(){
		mRoot = null; 
		mNumPairs = 0; 
	}

	//Public Methods used from DictionaryInterface
	
	// constructor for Dictionary class
	public Dictionary()
	{
		mRoot = null;
		mNumPairs = 0;
	}
	
	// isEmpty()
	// returns 1 (true) if D is empty, 0 (false) otherwise
	// pre: none
	public boolean isEmpty() {
		return (mNumPairs==0);
	}

	// size()
	// returns the number of (key, value) pairs in D
	// pre: none
	public int size() {
		return mNumPairs;
	}

	// lookup()
	// returns the value v such that (k, v) is in D, or returns NULL if no 
	// such value v exists.
	// pre: none
	public String lookup(String key) {
		Node toFind = findKey(this.mRoot, key);
		if(toFind == null)
			return null; 
		else 
			return toFind.mValue; 
	}

	// insert()
	// inserts new (key,value) pair into D
	// pre: lookup(key)==NULL
	public void insert(String key, String value) throws DuplicateKeyException {
		Node toAdd, toTraverse, helper; 
		if(lookup(key) != null) {
			throw new DuplicateKeyException("Dictionary Error: cannot insert() duplicate key: " + key); 
		}
		toAdd = new Node(key, value); 
		helper = null; 
		toTraverse = this.mRoot; 
		while(toTraverse != null) {
			helper = toTraverse; 
			if(key.compareTo(toTraverse.mKey) < 0)
				toTraverse = toTraverse.mLeft; 
			else
				toTraverse = toTraverse.mRight; 
		}
		if(helper == null)
			this.mRoot = toAdd; 
		else if(key.compareTo(helper.mKey) < 0)
			helper.mLeft = toAdd; 
		else
			helper.mRight = toAdd; 
		this.mNumPairs++;
	}

	// delete()
	// deletes pair with the key k
	// pre: lookup(D, k)!=NULL
	public void delete(String key) throws KeyNotFoundException {
		Node toDelete, parentNode, fourthCase; 
		if(lookup(key) == null) {
			throw new KeyNotFoundException("Dictionary Error: cannot delete() non-existent key: " + key); 
		}
		toDelete = findKey(mRoot, key);
		//#1 case: no children 
		if(toDelete.mLeft == null && toDelete.mRight == null) {
			if(toDelete == mRoot) {
				mRoot = null; 
			}else {
				parentNode = findParent(toDelete, mRoot);
				if(parentNode.mRight ==toDelete) {
					parentNode.mRight = null; 
				}else {
					parentNode.mLeft = null; 
				}
			}
		}
		//#2 case: left child, but no right child
		else if(toDelete.mRight == null) {
			if(toDelete == mRoot) {
				mRoot = toDelete.mLeft; 
			}else {
				parentNode = findParent(toDelete, mRoot); 
				if(parentNode.mRight == toDelete) {
					parentNode.mRight = toDelete.mLeft; 
				}else {
					parentNode.mLeft = toDelete.mLeft; 
				}
			}
		}
		//#3 case: right child, but no left child
		else if(toDelete.mLeft == null) {
			if(toDelete == mRoot) {
				mRoot = toDelete.mRight; 
			}else {
				parentNode = findParent(toDelete, mRoot); 
				if(parentNode.mRight == toDelete) {
					parentNode.mRight = toDelete.mRight; 
				}else {
					parentNode.mLeft = toDelete.mRight; 
				}
			}
		}
		//case #4: two children if(toDelete.mLeft != null && toDelete.mRight != null)
		else {
			fourthCase = findLeftmost(); 
			toDelete.mKey = fourthCase.mKey; 
			toDelete.mValue = fourthCase.mValue; 
			parentNode = findParent(fourthCase, toDelete); 
			if(parentNode.mRight == fourthCase) {
				parentNode.mRight = fourthCase.mRight; 
			}else {
				parentNode.mLeft = fourthCase.mRight; 
			}
		}
		mNumPairs--; 
	}

	// makeEmpty()
	// re-sets D to the empty state.
	// pre: none
	public void makeEmpty() {
		deleteAll();
	}

	// toString()
	// pre: none
	// prints a text representation of D to the file pointed to by out
	public String toString() {
		return printInOrder(mRoot); 
	}
}