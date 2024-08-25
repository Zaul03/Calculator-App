package Calculator.Evaluator;

import Calculator.Util.EvaluatorUtil;
import Calculator.Util.MathUtil;


public class Evaluator extends MathUtil {

    private static int nrL=0,nrR=0; //nr of parentheses
    private  static String number="";
    private static boolean editable;



    public static String[] parseIncoming(String expression,String result,String cmd)
    {

        boolean lastIsOp = EvaluatorUtil.lastIsOp(expression);
        boolean lastIsNr = EvaluatorUtil.lastIsNumber(expression);
        boolean lastIsEq = expression.endsWith("=");
        boolean emptyExp = expression.isEmpty();
        boolean duplicate = expression.endsWith(cmd);


        switch (cmd) {
            case "+", "-", "*", "/","^":
                if(!emptyExp &&
                       !lastIsEq &&
                       !expression.endsWith(".")&&
                       !expression.endsWith("(")){

                    if(lastIsOp){
                        expression=expression.substring(0,expression.length()-1); //delete the op
                        operatorStack.pop();}

                    if(lastIsNr){
                        numberStack.add(Double.parseDouble(number));
                        number="";}

                    operatorStack.add(cmd);

                    result=evaluate();
                    return new String[]{expression+cmd,result};}

                break;

            case ".":
                if(!lastIsEq && !duplicate) {
                    if(lastIsOp)
                        number="0"+cmd;
                    else
                        number+=cmd;

                    return new String[]{expression+cmd,result};}

                break;

            case "=":
                if( !lastIsOp &&
                    !expression.endsWith("(") &&
                    !expression.endsWith(".") &&
                    !duplicate &&
                    !emptyExp) {

                        if(lastIsNr)
                            numberStack.add(Double.parseDouble(number));

                        result=evaluate();

                        numberStack.add(Double.valueOf(result));
                        operatorStack.clear();

                        return new String[]{
                            expression+cmd,
                            result};
                }

                break;

            case "c","C":
                numberStack.clear();
                operatorStack.clear();
                number="";
                nrL=0;
                nrR=0;
                return new String[]{null,"0"};


            case "DEL":
                if(!emptyExp && !lastIsEq){

                    if(lastIsOp)
                        operatorStack.pop();

                    if(lastIsNr){
                       if(expression.endsWith("PI")){
                           expression=expression.substring(0,expression.length()-2);
                           numberStack.pop();
                           number="";
                           break;
                       }
                       else if(expression.endsWith("e")) {
                           numberStack.pop();
                           number="";}
                       else{
                           number=number.substring(0,number.length()-1);
                           numberStack.pop();
                           if(!number.isEmpty()){
                               numberStack.add(Double.parseDouble(number));}}}


                    if(expression.endsWith("(")){
                        nrL-=1;
                        operatorStack.pop();}

                    if(expression.endsWith(")")){
                        nrR-=1;
                        operatorStack.pop();}

                    if(expression.endsWith(".")){
                        number=number.substring(0,number.length()-1);
                        numberStack.add(Double.parseDouble(number));}

                    expression=expression.substring(0,expression.length()-1);
                }

                break;

            case "PI":

                if(!lastIsEq){

                    if(lastIsNr || expression.endsWith(")")){

                        operatorStack.add("*");
                        numberStack.add(Double.valueOf(number));

                        expression=expression+"*"+cmd;
                        result="PI";
                        number=String.valueOf(Math.PI);
                        break;}


                    if(emptyExp){
                        expression=cmd;
                        result=cmd;
                        number=String.valueOf(Math.PI);
                        break;}

                    expression=expression+cmd;
                    result="PI";

                    number=String.valueOf(Math.PI);

                }

                break;

            case "e":

                if(!lastIsEq) {

                    if (lastIsNr || expression.endsWith(")")) {

                        operatorStack.add("*");
                        numberStack.add(Double.valueOf(number));

                        expression = expression + "*" + cmd;
                        result = cmd;
                        number=String.valueOf(Math.E);
                        break;}


                    if(emptyExp){
                        expression=cmd;
                        result=cmd;
                        number=String.valueOf(Math.E);
                        break;}

                    expression=expression+cmd;
                    result="e";

                    number = String.valueOf(Math.E);
                }

                break;

            case "(":
                if(!lastIsEq){

                    if(lastIsNr){
                        numberStack.add(Double.valueOf(number));
                        operatorStack.add("*");

                        expression=expression+"*"+cmd;}

                    if(expression.endsWith(")")) {
                        operatorStack.add("*");
                        expression=expression+"*"+cmd;}


                    operatorStack.add(cmd);
                    nrL+=1;
                }
            break;

            case ")":
                if(!lastIsEq &&
                   !expression.endsWith("(") &&
                   nrR<nrL && !lastIsOp)
                {
                    numberStack.add(Double.valueOf(number));
                    operatorStack.add(cmd);
                    nrR+=1;

                    expression=expression+cmd;
                    result=evaluate();}

                break;


            case "1/x":

                if(!emptyExp){

                    if(numberStack.isEmpty()){
                        numberStack.add(Double.valueOf(number));
                        number="";}

                    double res= Double.parseDouble(evaluate());

                    operatorStack.clear();
                    numberStack.clear();

                    if(res==0.0){
                        result="Error! Division by 0!";
                        expression="";
                        break;}

                    result=String.valueOf(1/res);
                    expression="1/("+expression+")";

                    numberStack.add(1 / res);
                }
                break;

            case "0":
                if(!lastIsEq && !number.equals("0")){

                    if(expression.endsWith("/")) {
                        expression="";
                        result="Error! Division by 0!";
                        break;}

                    if(expression.endsWith("PI")||
                       expression.endsWith("e")){

                        expression=expression+"*"+cmd;
                        operatorStack.add("*");

                        if(expression.endsWith("E"))
                            numberStack.add(Math.E);
                        else
                            numberStack.add(Math.PI);

                        number=cmd;
                        break;}

                    number+="0";
                    expression=expression+cmd;
                    result=number;
                }

                break;

            case "1", "2", "3", "4", "5", "6", "7", "8", "9":

                if(!lastIsEq){
                    if(expression.endsWith("PI")||
                       expression.endsWith("e")){

                        expression=expression+"*"+cmd;
                        operatorStack.add("*");

                        if(expression.endsWith("E"))
                            numberStack.add(Math.E);
                        else
                            numberStack.add(Math.PI);

                        number=cmd;
                        break;}

                    if(expression.endsWith(")")){

                        expression=expression+"*"+cmd;
                        operatorStack.add("*");
                        number=cmd;
                        break;}

                    number+=cmd;

                    expression=expression+cmd;
                    result=number;
                }

                break;


            default:
                break;
        }


        return new String[]{expression,result}; //keep the same display
    }
}
