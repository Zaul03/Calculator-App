package Calculator.Util;
public class Parsing {

    private static int nrL=0;
    private static int nrR=0; //nr of left paranth and right paranth

    public static String[] parseIncoming(String crtTxt,String resTxt,String cmd)
    {



        boolean duplicate=crtTxt.endsWith(cmd);
        boolean lastisOp=ParsingLogic.lastIsOper(crtTxt);
        boolean lastIsNr=ParsingLogic.lastIsNumber(crtTxt);
        boolean lastIsEqual =crtTxt.endsWith("=");

        if(lastIsEqual&&!cmd.equals("c"))
                return new String[]{crtTxt,resTxt};


        switch (cmd) {
            case "+", "-", "*", "/","^":
                if (crtTxt.isEmpty()||lastisOp)
                    break;

                //if last is operator replace it

                //eval after the nr stack and operator stack has 2 levels

                return new String[] {crtTxt + cmd,"to eval"};  //comeback after eval

            case "=":  //print to history
                if(crtTxt.isEmpty())
                    break;
                if(crtTxt.endsWith("(")||lastisOp)
                    break;
                nrR=0;nrL=0;
                return new String[]{crtTxt+cmd,"to eval"};  //after implementing the evaluator, evaluates all, empty the stack

            case "c":
                nrR=0;nrL=0;
                return new String[]{null,"0"};   //empty the stack clear the screen

            case "DEL":                     //delete the last token, clear the res screen
                if (crtTxt.equals("0") || crtTxt.isEmpty())
                    return new String[]{null,"0"};
                if(crtTxt.endsWith(")"))
                    nrR-=1;
                if(crtTxt.endsWith("("))
                    nrL-=1;
                return new String[]{
                    crtTxt.substring(0, crtTxt.length() - 1),"to eval"}; //if last token is op displ res

            case "PI":                  //add pi's value to the stack
                if (duplicate)
                    break;
                if (crtTxt.equals("0"))
                    return new String[]{"PI","PI"};
                if(lastIsNr)
                    return new String[]{crtTxt+"*" + "PI","PI"};
                return new String[]{crtTxt+cmd,"PI"};

            case "e":                 //add e's val to the stack
                if (duplicate)
                    break;
                if (crtTxt.equals("0"))
                    return new String[]{cmd,cmd};
                if(lastIsNr)
                    return new String[]{crtTxt+"*"+cmd,cmd};
                return new String[]{crtTxt+cmd,cmd};

            case "(":               //increase precedence
                if (crtTxt.equals("0"))
                    return new String[]{cmd,"0"};
                if(lastIsNr)
                    return new String[]{crtTxt+"*"+cmd,resTxt};

                nrL+=1;
                return new String[]{crtTxt + cmd,resTxt};

            case ")":                    //pop the stack and eval
                if(crtTxt.endsWith("("))
                    break;
                if(lastIsNr && !crtTxt.equals("0")){
                    nrR+=1;
                    return new String[]{crtTxt + cmd,resTxt};}
                break;
            case "1/*":   /////evaluate and then inverse
                if (resTxt.equals("0")) {
                    return new String[]{crtTxt, "Error! Can not divide by 0!"};
                }

                return new String[]{"1/"+"("+crtTxt+")",resTxt}; //need to eval

            case "0":
                if (crtTxt.equals("0"))
                    break;
                if (crtTxt.endsWith("/"))
                    return new String[]{crtTxt,"Error! Can not divide by 0!"};  //empty the stack and print to history

                return new String[]{crtTxt + cmd,resTxt+cmd};

            case "1", "2", "3", "4", "5", "6", "7", "8", "9":
                if (crtTxt.isEmpty())  //
                    return new String[]{cmd,cmd};
                if(ParsingLogic.lastIsNumber(resTxt)&&lastIsNr)
                    return new String[]{crtTxt+cmd,resTxt+cmd};
                if(crtTxt.endsWith(")"))
                    return new String[]{crtTxt+"*"+cmd,cmd};

                return new String[]{crtTxt+cmd,cmd};

            default:
                break;
        }
        return new String[]{crtTxt,resTxt}; //keep the same display
    }
}
