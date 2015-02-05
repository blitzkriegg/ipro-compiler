/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iprocompiler.Control;

/**
 *
 * @author Michael
 */
public class IProCompiler {
    static String[] regexes = {"\\s*(?i)put\\s+((?i)[a-f]|\\w*(\\d{1,})*(\\W\\D)*|\\n|\\t\\b)\\s*",
			    "\\s*(?i)get\\s+(?i)[a-f](\\s*|\\n|\\r\\n?)\\s*",
                            "\\s*",
			    "\\s*(?i)set\\s+(?i)[a-f],\\s*\\d{1,}\\s*",
                            "\\s*(?i)(add|sub|mul|div|mod)\\s+(?i)[a-f],\\s*((?i)[a-f]|\\d{0,})\\s*",
                            "\\s*(?i)mov\\s+(?i)[a-f],\\s*(?i)[a-f]\\s*",
                            "\\s*(?i)cmp\\s+(?i)[a-f],\\s*((?i)[a-f]|\\d)",
                            "\\s*(?i)(je|jg|jl)\\s+label\\d{1}\\s*",
                            "\\s*(?i)\\w+:(\\n|\\r\\n?)*\\s*",
                            "\\s*(i*)(push|pop)\\s+[a-f]\\s*",
                            "\\s*MACRO\\s+(?i)\\w+:\\s*",
                            "\\s*(?i)(start|end|exit)\\s*",                        
                            "\\s*(?i)\\w+\\s*"};
    /**
     * @param args the command line arguments
     */
    public static String [] syntaxCheck(String code){
        String [] codelines = code.split("//n");
        for (String temp : codelines) {
            if (!IProCompiler.syntaxCheckLine(temp)) {
                //push error line number & error message to the list
                //store label,MACRO names and their line number
            } 
        }
        return codelines;
    }
    public static boolean syntaxCheckLine(String s) {
        boolean retval = false;
        for (int i = 13; i >= 0 && false == retval; i--) {
            retval = s.matches(regexes[i]);
        }
        return retval;
    }
    
}
