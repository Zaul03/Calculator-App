package Calculator.Util;

public class ParsingUtil {
    public static String[] parseIncoming(String crtTxt,String resTxt,String cmd)
    {

        boolean duplicate=crtTxt.endsWith(cmd);
        boolean lastisOp=ParsingLogic.lastIsOper(crtTxt);
        boolean lastIsNr=ParsingLogic.lastIsNumber(crtTxt);
        boolean lastIsEq=crtTxt.endsWith("=");


        switch (cmd) {
            case "+", "-", "*", "/","^":
                if (crtTxt.isEmpty()||lastisOp||lastIsEq)
                    break;
                return new String[] {crtTxt + cmd," "};  //comeback after eval

            case "=":
                if(crtTxt.isEmpty())
                    break;
                if(lastIsEq||crtTxt.endsWith("(")||lastisOp)
                    break;
                return new String[]{crtTxt+cmd,"to eval"};  //after implementing the evaluator

            case "c":
                return new String[]{null,"0"};

            case "DEL":
                if (crtTxt.equals("0") || crtTxt.isEmpty())
                    return new String[]{null,"0"};

                return new String[]{
                    crtTxt.substring(0, crtTxt.length() - 1),""};

            case "PI":
                if(lastIsEq)
                    break;
                if (duplicate)
                    break;
                if (crtTxt.equals("0"))
                    return new String[]{"PI","PI"};
                if(lastIsNr)
                    return new String[]{crtTxt+"*" + "PI","PI"};
                return new String[]{crtTxt+cmd,"PI"};

            case "e":
                if(lastIsEq)
                    break;
                if (duplicate)
                    break;
                if (crtTxt.equals("0"))
                    return new String[]{cmd,cmd};
                if(lastIsNr)
                    return new String[]{crtTxt+"*"+cmd,cmd};
                return new String[]{crtTxt+cmd,cmd};

            case "(":
                if(lastIsEq)
                    break;
                if (crtTxt.equals("0"))
                    return new String[]{cmd,"0"};
                if(lastIsNr)
                    return new String[]{crtTxt+"*"+cmd,resTxt};
                return new String[]{crtTxt + cmd,resTxt};

            case ")":
                if(lastIsEq||crtTxt.endsWith("("))
                    break;
                if(lastIsNr && !crtTxt.equals("0"))
                    return new String[]{crtTxt + cmd,resTxt};
                break;

            case "1/*":   /////come back after eval
                if (resTxt.equals("0"))
                    return new String[]{crtTxt,"Error! Can not divide by 0!"};
                return new String[]{"1/"+"("+crtTxt+")",resTxt}; //need to eval

            case "0":
                if(lastIsEq)
                    break;
                if (crtTxt.endsWith("/"))
                    return new String[]{crtTxt,"Error! Can not divide by 0!"};
                if (crtTxt.equals("0"))
                    break;
                return new String[]{crtTxt + cmd,resTxt+cmd};

            case "1", "2", "3", "4", "5", "6", "7", "8", "9":
                if(lastIsEq)
                    break;
                if (crtTxt.isEmpty())
                    return new String[]{cmd,cmd};
                if(ParsingLogic.lastIsNumber(resTxt)&&lastIsNr) //result panel and eqution panel are both numbers
                    return new String[]{crtTxt+cmd,resTxt+cmd};
                if(crtTxt.endsWith(")"))
                    return new String[]{crtTxt+"*"+cmd,cmd};

                return new String[]{crtTxt+cmd,cmd};

            default:
                break;
        }
        return new String[]{crtTxt,resTxt};
    }
}
