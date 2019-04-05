/**
 * Isai Lopez
 * ilopezro
 * Lab 7
 * Mar 13, 2018
 * (Doc Description)
 */

public class DictionaryTest {
	
	public static void main(String [] args){
		Dictionary D = new Dictionary(); 
		
		System.out.println(D.isEmpty());
		System.out.println(D.size()); 
		D.insert("1", "one");
		D.insert("2", "two");
		D.insert("3", "three");
		System.out.println(D);
		System.out.println(D.isEmpty());
		System.out.println(D.size()); 
		D.makeEmpty(); 
		System.out.println(D);
		System.out.println(D.isEmpty());
		System.out.println(D.size()); 
		D.insert("5", "five");
		System.out.println(D);
		System.out.println(D.isEmpty());
		System.out.println(D.size()); 
	}
}