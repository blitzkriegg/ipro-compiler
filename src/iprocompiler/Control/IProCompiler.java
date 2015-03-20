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
                              "\\s*(?i)(je|jg|jl)\\s+[\\w][\\w\\d]+\\s*",                                    //7je,jg,jl                      8
                              "\\s*(?i)\\w+:(\\n|\\r\\n?)*\\s*",                                          //8
                              "\\s*(i*)(push|pop)\\s+[a-f]\\s*",                                          //9push,pop                      11,12
                              "\\s*MACRO\\s+(?i)\\w+:\\s*",                                               //10Macro                         10
                              "\\s*(?i)(start|end|exit)\\s*",                                             //11start,end,exit                0
                              "\\s*(?i)\\w+\\s*",                                                         //12
                              "([ ]*jmp[ ][\\w][\\w\\d]+)",                                              //13jmp};                           9
                              "([ ]*[\\w][\\w\\d]+:)",                                                      //14label};                         6
                              "\\s*(?i)(\n)"                                                                //15 not next line
                    };                                                   
    
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
//            "end",
//            "\\s*(?i)(\n)"
//            
//    };
                            
                            
    /**
     * @param args the command line arguments
     */
    
    String[] codeline;
    int size;
    public void MakeCodeLine(String code){
        
        codeline = code.split("\n",-1);
        
    }
    
    public IproModel syntaxCheck(String code){
        
        IproModel model = new IproModel();
        MakeCodeLine(code);
        
        
        for (int i=0;i<codeline.length ;i++) {
            
            if (!syntaxCheckLine(codeline[i])) {  
                System.out.println(codeline[i]);
                String ErrorMessage ="Syntax Error";
                model.ErrorPush(i, ErrorMessage);
            } 
            
        }
        return model;
    }
        
    public boolean syntaxCheckLine(String s) {
        boolean retval = false;
        for (int i = 0; i< regexes.length && false == retval; i++) {
            retval = s.matches(regexes[i]);
            
        }
       
        return retval;
    }
    
    public int RegularExpressionCheck (String s){
        int retval = -1;
        for (int i = 0; i < regexes.length && retval ==-1 ; i++) {
           
            if (s.matches(regexes[i]))
                   retval = i;
        }
        return retval;
    }
        
    public IproModel StaticSymanticCheck(String code){
        
        IproModel model = new IproModel();
        MakeCodeLine(code);
        
        for (int i=0;i<codeline.length ;i++) {
            switch(this.RegularExpressionCheck(codeline[i])){    
                case 7: case 13:        
                        StringBuffer s = new StringBuffer(codeline[i]);
                        SymanticLabelChecker(i,model);          
                    break;                              
                case 6:
                        SymanticCmpChecker(i,model);                
                    break;
                                
    //            case 14:
    //                SymanticMacroChecker(lineNumber);
    //                break;                             
                default:;
                       
            }            
        }           
          
            return model;
	}
    
    public void SymanticMacroChecker(int lineNumber){        
        Boolean Status = null;
        Boolean hasEnd = null;
        for (; lineNumber<codeline.length;lineNumber++) {  
            
            if (codeline[lineNumber]!="\n"){    
                if (Status == null && !codeline[lineNumber].contains("start")){                    
                        System.out.println("                    Symantic Error ** MACRO can not found start keyword!");
                        Status = false;
                        hasEnd = false;
                }else{Status=true;}
                
                if(Status == true && RegularExpressionCheck(codeline[lineNumber]) == 13){                
                    System.out.println("                    Symantic Error ** MACRO is not capable of having jmp keyword!");
                    Status = false;
                }
                if(Status == true && RegularExpressionCheck(codeline[lineNumber]) == 7){                
                   System.out.println("                    Symantic Error ** MACRO is not capable of having je, jg, jl keyword!");
                   Status = false;
                }                  
                if (codeline[lineNumber].contains("end")){
                    Status = false;
                    hasEnd = true;
                }                     
            }
        }
        if (hasEnd == false){
            System.out.println("                    Symantic Error ** MACRO can not found end keyword!");
        }
    
    }
    
    public void SymanticCmpChecker(int lineNumber, IproModel model){        
        
        System.out.println(codeline[lineNumber]);
        for (lineNumber++; lineNumber!= codeline.length;lineNumber++){
            if (this.RegularExpressionCheck(codeline[lineNumber])==7){
                break;
            }
            if(this.RegularExpressionCheck(codeline[lineNumber])!=(7&15&2)){
                System.out.println(this.RegularExpressionCheck(codeline[lineNumber]));
                
                String Error ="Symantic Error! ** cmp should much with je, jg, jl";
                model.ErrorPush(lineNumber, Error);
                break;
            }
        }
    }
    
    public void SymanticLabelChecker(int lineNumber, IproModel model){
        
        StringBuffer label = new StringBuffer();
        int x=0;    
    
        for (;(codeline[lineNumber].charAt(x)==' ');x++);
        for (;(codeline[lineNumber].charAt(x)!=' ');x++);                              
        for (;(codeline[lineNumber].charAt(x)==' ');x++);
                                
        label.append(codeline[lineNumber].subSequence(x,codeline[lineNumber].length()));
        label.append(':');
         
        for (x=0 ; x<codeline.length-1; x++) {                                                
            if (x!=lineNumber && codeline[x].contains(label)){
                 break;          
            }
        }
                                
        if (x==codeline.length-1){
            String Error ="Symantics Error!! ** Label ("+label+") dose not found!!";
            model.ErrorPush(lineNumber, Error);
                                    
        }     
    }
        
              
}
