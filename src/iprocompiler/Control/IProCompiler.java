/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iprocompiler.Control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michael
 */
public class IProCompiler {
    static String[] regexes = {
                              "\\s*(?i)put\\s+((?i)[a-f]|\\w*(\\d{1,})*(\\W\\D)*|\\n|\\t\\b)\\s*",     //0 put                          2
                              "\\s*(?i)get\\s+(?i)[a-f](\\s*|\\n|\\r\\n?)\\s*",                           //1get                          1
                              "\\s*",                                                                      //2
                              "\\s*(?i)set\\s+(?i)[a-f],\\s*\\d{1,}\\s*",                                 //3set                           3
                              "\\s*(?i)(add|sub|mul|div|mod)\\s+(?i)[a-f],\\s*((?i)[a-f]|\\d{0,})\\s*",   //4add,sub,mul,div,mod           4
                              "\\s*(?i)mov\\s+(?i)[a-f],\\s*(?i)[a-f]\\s*",                               //5mov                           5
                              "\\s*(?i)cmp\\s+(?i)[a-f],\\s*((?i)[a-f]|\\d)",                             //6comp                          7
                              "\\s*(?i)(je|jg|jl)\\s+label\\d{1}\\s*",                                    //7je,jg,jl                      8
                              "\\s*(?i)\\w+:(\\n|\\r\\n?)*\\s*",                                          //8
                              "\\s*(i*)(push|pop)\\s+[a-f]\\s*",                                          //9push,pop                      11,12
                              "\\s*MACRO\\s+(?i)\\w+:\\s*",                                               //10Macro                         10
                              "\\s*(?i)(start|end|exit)\\s*",                                             //11start,end,exit                0
                              "\\s*(?i)\\w+\\s*",                                                         //12
                              "([ ]*jmp[ ][\\w][\\w\\d]+)",                                              //13jmp};                           9
                              "([ ]*[\\w][\\w\\d]+:)"};                                                   //14label};                         6
    
//            "start|exit",   //blocks
//            "([ ]*get[ ]+[a-f])",//get statement
//            "([ ]*put[ ]+[\\d\\w_])",   //put
//            "([ ]*set[ ]+[\\d\\w_]+,[0-9]+)", //set satement
//            "([ ]*add|sub|div|mul[ ]+[a-f],([a-f]|[\\d]))", //operation
//            "([ ]*mov[ ]+[a-f],([a-f]|[\\d]*))",  //mov statement
//            "([ ]*[A-Z]+:)",    //MACRO label    
//            "([ ]*[\\w][\\w\\d]+:)",        //label
//            "([ ]*cmp[ ][a-f],[a-f])",          //compaire
//            "([ ]*(je|jg|jl)[ ]+[\\w][\\w\\d]+)",  //pair with cmp
//            "([ ]*jmp[ ][\\w][\\w\\d]+)",   //jmp
//            "([ ]*pop[ ]+[a-f])",   //pop
//            "([ ]*push[ ]+(\\w\\d)*)",  //push
//            "end"};
                            
                            
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
    
    public static int RegularExpressionCheck (String s){
        int retval = -1;
        for (int i = 0; i < regexes.length && retval ==-1 ; i++) {
            if (s.matches(regexes[i]))
                   retval = i;
        }
        return retval;
    }
        
    public int StaticSymanticCheck(String code, int lineNumber){
        
        String subline; //case7 (cmp)
        String [] codelines = code.split("//n");
        String codeline = codelines[lineNumber];

        
        
            int counter=0;//case7 (cmp) if get out method need to traverse file pointer 
                
            StringBuffer compare = new StringBuffer(codeline);//case 8 9 (MACRO, Label)
            
        
			switch(this.RegularExpressionCheck(codeline)){    
                            case 7: case 13:
                                StringBuffer label = new StringBuffer();
                                int x=0;                                
                                for (;(compare.charAt(x)==' ');x++);
                                for (;(compare.charAt(x)!=' ');x++);                              
                                for (;(compare.charAt(x)==' ');x++);
                                
                                label.append(compare.subSequence(x,compare.length()));
                                label.append(':');
                                
                               //Read Another ilne again                                
                                for (x=0; x<codelines.length;x++) {
                                    subline =codelines[x];                                    
                                    if (x!=lineNumber && subline.contains(label)){
                                        break;          
                                    }
                                }
                                
                                if (x==codeline.length()){
                                    System.out.println("                    Symantics Error!! ** Label dose not found!!");   
                                    //put error in data class
                                }     
                                break;
                                
                                
                            case 6:
                                subline = null;
                                for (lineNumber++; lineNumber!= codelines.length && codelines[lineNumber] != "\n" ;lineNumber++){
                                    subline = codelines[lineNumber];
                                }
                                if(this.RegularExpressionCheck(subline)!=7){
                                    System.out.println("                    Symantic Error! ** cmp should much with je, jg, jl");
                                    // put Error in Data class
                                }
                                                               
                                break;
                            
//                            case 14:
//                                r = Pattern.compile(regexes[10]);                                   
//                                m = r.matcher(line);
//                                
//                                //if it is MACRO then
//                                if (m.find( )) {
//                                    for(int i=0;(subline = lineCode.readLine()) != null && i<=lineNo;i++);
//                                    x=1;
//                                    for (int i=0;(subline = lineCode.readLine()) != null && x!=0;i++){
//                                        for (;subline.compareTo("\n")==-1 && (subline = lineCode.readLine()) != null;);
//                                        if (i==0 && !subline.contains("start")){
//                                            System.out.println("                    Symantic Error ** MACRO can not found start keyword!");
//                                            x=0;
//                                            break;
//                                        }
//                                        r = Pattern.compile(regexes[13]);                                   
//                                        m = r.matcher(subline);
//                                        if (m.find( )){
//                                            System.out.println("                    Symantic Error ** MACRO is not capable of having jmp keyword!");
//                                            
//                                            x=0;
//                                            break;
//                                        }
//                                        r = Pattern.compile(regexes[7]);                                   
//                                        m = r.matcher(subline);
//                                        if (m.find( )){
//                                            System.out.println("                    Symantic Error ** MACRO is not capable of having je, jg, jl keyword!");
//                                            
//                                            x=0;
//                                            break;
//                                        }     
//                                        if (subline.contains("start") && i!=0)
//                                            x++;
//                                        else if (subline.contains("end"))
//                                            x--;
//                                    }
//                                    if (x>0){
//                                        System.out.println("                    Symantic Error ** MACRO can not found end keyword!");
//                                        
//                                    }
//                                }
//                                break;                             
//                            default:;
//                       
                        }
		return counter;	
	}
        
        public void DynamicSymanticChecker(String lineCode){
           
                        
			switch(this.RegularExpressionCheck(lineCode)){    
                            case 7: case 13:
                                
                                break;
                            case 6:
                                
                            case 14:
                                
                                break;                             
                            default:;
                        }
			
		
        }
              
}
