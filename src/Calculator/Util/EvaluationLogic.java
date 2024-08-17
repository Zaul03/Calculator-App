package Calculator.Util;


public class EvaluationLogic {

    public static boolean lastIsOper(String equation) {
        return equation.endsWith("+")||
            equation.endsWith("-")||
            equation.endsWith("=")||
            equation.endsWith("*")||
            equation.endsWith("/")||
            equation.endsWith("^");}

    public static boolean lastIsNumber(String equation){
        if(equation.isEmpty())
            return false;

        char nr=equation.toCharArray()[equation.length()-1];
        if(nr<='9'&&nr>='0')
            return true;
        return equation.endsWith("e") || equation.endsWith("PI");
    }


}

