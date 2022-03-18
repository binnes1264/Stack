import java.util.ArrayList;

public class Stack {

	static ArrayList<String> s;
	
	//Creates instance of the stack by utilizing an ArrayList
	public Stack() {
		
		s = new ArrayList<String>();
		
	}
	
	//Adds value to top of the stack
	public void push(String k) {
		
		s.add(k);
		
	}
	
	//Returns value on top of stack and sets new top
	public String pop() {
		
		try {
			String lastE = s.get(s.size()-1);
			//System.out.println("Removed item is " + lastE);
			s.remove(s.size()-1);
			return lastE;
		}catch(IndexOutOfBoundsException e) {
			System.out.println("hi");
			System.out.println(e);
			return null;
		}
		
	}
	
	//Returns the top without removing it from the stack
	public String peek() {
		
		try {
			String see = s.get(s.size()-1);
			return see;
		}catch(IndexOutOfBoundsException e) {
			System.out.println("bonjour");
			System.out.println(e);
			return null;
		}
		
	}
	
	//Checks if stack is empty
	public boolean isEmpty() {
		
		return s.isEmpty();
		
	}
	
	//Removes all elements from the stack until empty
	public void removeALL() {
		
		while( !(s.isEmpty()) ) {
			s.remove(s.size()-1);
		}
		
	}
	
	//Prints stack
	public void printS() {
		
		System.out.println(s.toString());
	}
	
}
