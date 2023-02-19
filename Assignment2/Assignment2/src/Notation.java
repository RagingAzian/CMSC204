public class Notation {
	public Notation() {
	}

	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
		MyStack<String> myStack = new MyStack<String>(postfixExpr.length());
		String num1, num2;
		char[] list = postfixExpr.toCharArray();
		try{
			for(char c: list){
				if(Character.isDigit(c)){
					myStack.push(Character.toString(c));
				}
				else if(checkIfOperator(Character.toString(c))){
					num1 = myStack.pop();
					num2 = myStack.pop();
					
					myStack.push(Integer.toString(doOperation(Character.toString(c), num1, num2)));
				}
				else if(c==' '){
					continue;
				}
			}
			if (myStack.size()>1)
				throw new Exception();
		} catch(Exception e){
			throw new InvalidNotationFormatException();
		}
		return Integer.parseInt(myStack.toString());
	}

	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
		MyStack<String> myStack = new MyStack<String>(postfix.length());
		String num1, num2;
		char [] list = postfix.toCharArray();
		try{
			for(char c: list){
				if(c==' ')
					continue;
				else if(Character.isDigit(c)){
					myStack.push(Character.toString(c));
				}
				else if(checkIfOperator(Character.toString(c))){
					num1 = myStack.pop();
					num2 = myStack.pop();
					myStack.push("("+num2+c+num1+")");
				}
			}

			if(myStack.size()>1)
				throw new Exception();
		} catch(Exception e){
			throw new InvalidNotationFormatException();
		}
		return myStack.toString();	
	}
	
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
		MyQueue<String> queue = new MyQueue<String>(infix.length());
		MyStack<String> stack = new MyStack<String>(infix.length());

		char [] list = infix.toCharArray();
		try{
			for(char c: list){
				if(c==' ')
					continue;
				else if (Character.isDigit(c)){
					queue.enqueue(Character.toString(c));
				}
				else if(c=='(')
					stack.push(Character.toString(c));
				else if (checkIfOperator(Character.toString(c))){
					if(!stack.isEmpty()&& checkIfOperator(stack.top())&&precedence(stack.top(),Character.toString(c))){
						queue.enqueue(stack.pop());
					}
					stack.push(Character.toString(c));
				}
				else if(c==')'){
					while(!stack.isEmpty()&&checkIfOperator(stack.top()))
						queue.enqueue(stack.pop());
					if(!stack.isEmpty()&&stack.top().equals("("))
						stack.pop();
					else
					throw new Exception();
				}
			}
			while(!stack.isEmpty()&& checkIfOperator(stack.top()))
				queue.enqueue(stack.pop());
		} catch(Exception E){
			throw new InvalidNotationFormatException();
		}
		return queue.toString();
 	}

	public static boolean checkIfOperator(String string) {
		return (string.equalsIgnoreCase("+") || string.equalsIgnoreCase("-") || string.equalsIgnoreCase("*")
				|| string.equalsIgnoreCase("/"));
	}

	public static int doOperation(String op, String num1, String num2){
		int int1 = Integer.parseInt(num1);
		int int2 = Integer.parseInt(num2);
		switch(op){
			case "+":
				return int2+int1;
			case "-":
				return int2-int1;
			case "*":
				return int2*int1;
			case "/":
				return int2/int1;
			default:
				return 0;
		}
	}

	public static boolean precedence(String op1, String op2){
		int prec1, prec2;

		if(op1.equals("*")||op1.equals("/"))
			prec1 = 2;
		else 
			prec1 = 1;

		if(op2.equals("*")||op2.equals("/"))
			prec2 = 2;
		else 
			prec2 = 1;
		return(prec1>=prec2);
	}
}