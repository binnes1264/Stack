import java.util.Scanner;

public class Expression {

	public static void main(String[] args) {
		
		String infix = "";
		String prefix = "";
		String postfix = "";
		double result = 0;
		
		//Open scanner for command line
		Scanner in = new Scanner(System.in);
		
		//Create operator stack
		Stack op = new Stack();
		
		//Prompt user for infix expression
		System.out.println("Please input your Infix Expression.");
		
		//read in expression, then close scanner
		infix = in.nextLine();
		in.close();
		
		//Display infix expression, then saves to array after splitting
		System.out.println("Infix expression: "+ infix);
		String a[] = infix.split(" ");

		//Call postfix method and print to console
		postfix = postf(infix, op, a);
		System.out.println("Postfix expression:" + postfix);
		
		//Call prefix method and print to console
		prefix = pref(infix, op, a);
		System.out.println("Prefix expression:" + prefix);
		
		//Create evaluation stack to calculate postfix expression
		Stack eval = new Stack();
		
		//Call calculation method and print final answer to console
		result = calculation(postfix, eval);
		System.out.println("Evaluation: " + result);
	
	}

	/*
	 * Prefix method: converts infix string to prefix string
	 */
	public static String pref(String infix, Stack op, String[] a) {
		
		String prefix = " ";
		String popped = null;
		
		//For loop traverses array backwards
		for(int j = (a.length)-1; j >= 0; j--) {
			
		//If-else statements organize operators and operands based on parenthesis
			
			//Adds close parenthesis to stack
			if( a[j].equals(")") ){
				op.push(a[j]);
			}
			
			//Retrieves the operator between the parenthesis and adds to prefix string
			else if(a[j].equals("(")){
				
				while(!(op.peek().equals(")"))) {
					popped = op.pop();
					prefix =  popped + " " + prefix;
				}
				op.pop();
				
			}
			
			//Adds operators to stack
			else if( a[j].equals("+") || a[j].equals("-") || a[j].equals("*") || a[j].equals("/")) {
				op.push(a[j]);
			}
			
			//Adds operands to prefix string
			else{
				prefix = a[j] + " " + prefix;
			}
			
		//reset variable
		popped = null;
		
		}
		
		//Checks for remaining operators in the case of missing parenthesis
		if( !(op.isEmpty()) ) {
			popped = op.pop();
			prefix = prefix + popped;
			
		}
		//Removes remaining elements in stack in case of error
		op.removeALL();
		
		//Return fully evaluated prefix string to main
		return prefix;
	}
	
	
	/*
	 * Postfix method: converts infix string to postfix string
	 */
	public static String postf(String infix, Stack op, String[] a) {
		String postfix = "";
		String popped = null;
		
		//For loop traverses forwards
		for(int i = 0; i < (a.length); i++) {
			
		//If-else statements organize operators and operands based on parenthesis
			
			//Adds open parenthesis to stack
			if(a[i].equals("(")) {
				op.push(a[i]);
			}
			
			//Retrieves the operator between the parenthesis and adds to postfix string
			else if(a[i].equals(")")) {
				
				while(!(op.peek().equals("("))) {
					popped = op.pop();
					postfix = postfix + popped + " ";
				}
				op.pop();
			}
			
			//Adds operators to stack
			else if( a[i].equals("+") || a[i].equals("-") || a[i].equals("*") || a[i].equals("/")) {
				op.push(a[i]);
			}
			
			//Adds operands to postfix string
			else {
				postfix = postfix + a[i] + " ";
			}
			
		//Reset variable
		popped = null;

		}

		//Checks for remaining operators in the case of missing parenthesis
		if( !(op.isEmpty()) ) {
			popped = op.pop();
			postfix = postfix + popped;
			
		}
		
		//Removes remaining elements in stack in case of error
		op.removeALL();
	
		//Returns fully evaluated postfix string to main
		return postfix;
	}
	
	/*
	 * Calculation method: Uses postfix expression to evaluate original infix expression
	 */
	
	public static double calculation(String postfix, Stack eval) {
		double result = 0;
		double num1 = 0;
		double num2 = 0;
		
		//Creates new array with postfix string split with space delimiter 
		String b[] = postfix.split(" ");
		
		//For loop traverses forward
		for(int k = 0; k < (b.length); k++) {
			
			//Executes when array index is an operator
			if( b[k].equals("+") || b[k].equals("-") || b[k].equals("*") || b[k].equals("/") ) {
				
				//Pops two numbers off the top of the stack and converts to double
				num1 = Double.parseDouble(eval.pop());
				num2 = Double.parseDouble(eval.pop());
				
				//Evaluates operator and two doubles
				if(b[k].equals("+")) { 
					result = num2 + num1;
				}
				else if(b[k].equals("-")) {
						result = num2 - num1;
				}
				else if(b[k].equals("*")) {
						result = num2 * num1;
				}
				else{
						result = num2 / num1;
				}
				
				//Pushes result back to stack to be evaluated with later operators
				eval.push(Double.toString(result));
			}
			
			//Pushes operand to stack
			else {
				eval.push(b[k]);
			}
			
			//Reset numbers
			num1 = 0;
			num2 = 0;
		}
		
		//Returns fully evaluated expression to main
		return result;
		
	}
}
