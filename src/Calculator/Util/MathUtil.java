package Calculator.Util;

import java.util.Stack;

public class MathUtil {

    //shunting yard algorithm

    protected static final Stack<Double> numberStack=new Stack<Double>();
    protected static final Stack<String> operatorStack=new Stack<String>();



    public static String evaluate(){



        while(!operatorStack.isEmpty()){


            if(operatorStack.peek().equals(")")){

                    while (!operatorStack.peek().equals("(")){
                        numberStack.push(
                            applyOp(operatorStack.pop(),
                                    numberStack.pop(),
                                    numberStack.pop()));}
                    operatorStack.pop();
            }
            else if(operatorStack.peek().equals("+")||
                        operatorStack.peek().equals("-")||
                        operatorStack.peek().equals("*")||
                        operatorStack.peek().equals("/")||
                        operatorStack.peek().equals("^")){

                String tmp=operatorStack.pop();
                    while (!operatorStack.empty() &&
                           hasPrecedence(tmp, operatorStack.peek()))
                    {
                        numberStack.push(
                            applyOp(
                            operatorStack.pop(),
                            numberStack.pop(),
                            numberStack.pop()));
                    }
            }

            while (!operatorStack.empty())
                numberStack.push(applyOp(operatorStack.pop(),
                    numberStack.pop(),
                    numberStack.pop()));
        }

        return String.valueOf(numberStack.peek());
    }

    public static double applyOp(String op,double a, double b){

        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;  //I already checked for division by 0 in Evaluator.java
            case "^" -> Math.pow(a, b);
            default -> -1111111111;
        };

    }

    public static boolean hasPrecedence(
        String op1, String op2)
    {
        if (op2.equals("(") || op2.equals(")"))
            return false;

        if(op1.equals("^") && !op2.equals("^"))
            return false;

        if ((op1.equals("*") || op1.equals("/")) &&
                (op2.equals("+") || op2.equals("-")))
            return false;
        else
            return true;
    }


}
