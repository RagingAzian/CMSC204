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
					switch(Character.toString(c)){
						case "+":
							myStack.push(Integer.toString(Integer.parseInt(num2)+Integer.parseInt(num1)));
							break;
						case "-":
							myStack.push(Integer.toString(Integer.parseInt(num2)-Integer.parseInt(num1)));
							break;
						case "*":
							myStack.push(Integer.toString(Integer.parseInt(num2)*Integer.parseInt(num1)));
							break;
						case "/":
						myStack.push(Integer.toString(Integer.parseInt(num2)/Integer.parseInt(num1)));
					}
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


	public static boolean checkIfOperator(String string) {
		return (string.equalsIgnoreCase("+") || string.equalsIgnoreCase("-") || string.equalsIgnoreCase("*")
				|| string.equalsIgnoreCase("/"));
	}
}