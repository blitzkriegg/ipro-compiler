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
                              "\\s*",                                                                      //2        \n
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
                              "([ ]*jmp[ ][\\w][\\w\\d]+)",                                              //13jmp};                           
                              "\\s*(?i)(\n)"                                                                //14 not next line
                    };                                                   
                
                            
    /**
     * @param args the command line arguments
     */
    
    
    public int put (int lineNumber,IproModel model){
        StringBuffer val= new StringBuffer();
        int x=0;
        int y;
        x=labelSkip(lineNumber);
        for (y=x+1;y<codeline[lineNumber].length() && (codeline[lineNumber].charAt(y)!=' ');y++);  

        val.append(codeline[lineNumber].subSequence(x,y));
        System.out.println(val);
        
        switch(val.toString()){
            case "a":
                return model.a;                 
            case "b":
                return  model.b;                
            case "c":
                return model.c;
            case "d":
                return model.d;
            case "e":
                return model.e;
            case "f":
               return model.f;
            default:
                return Integer.parseInt(val.toString());
        }
        
    }
    
    public int jmp (int lineNumber,IproModel model){
        StringBuffer val= new StringBuffer();
        int x=0;
        int y;
        x=labelSkip(lineNumber);
        for (y=x+1;y<codeline[lineNumber].length() && (codeline[lineNumber].charAt(y)!=' ');y++);  

        val.append(codeline[lineNumber].subSequence(x,y));
        System.out.println(val);
        
        for (x=0 ; x<codeline.length-1; x++) {                                                
            if (x!=lineNumber && codeline[x].contains(val)){
                 break;          
            }
        }
        
        return x;
    }
    
    
    public int labelSkip(int lineNumber){
        int x=0;
        
        for (;(codeline[lineNumber].charAt(x)==' ');x++);
        for (;(codeline[lineNumber].charAt(x)!=' ');x++);                              
        for (;(codeline[lineNumber].charAt(x)==' ');x++);
        
        return x;
    }
    
    public int calcuraion (int lineNumber, IproModel model){
        int x =0,y=0;
        int ret =0;
        
        StringBuffer operation= new StringBuffer();
        StringBuffer val= new StringBuffer();
        StringBuffer val2= new StringBuffer();
        
        for (;(codeline[lineNumber].charAt(x)==' ');x++);
        for (y=x+1;y<codeline[lineNumber].length() && (codeline[lineNumber].charAt(y)!=' ');y++);        
        operation.append(codeline[lineNumber].subSequence(x,y));
       
        
        for (x=y+1;(codeline[lineNumber].charAt(x)==' ');x++);
        for (y=x+1;y<codeline[lineNumber].length() && (codeline[lineNumber].charAt(y)!=' ') && (codeline[lineNumber].charAt(y)!=',');y++);
        val.append(codeline[lineNumber].subSequence(x,y));
        
        
        for (x=y+1;(codeline[lineNumber].charAt(x)==' ');x++);
        for (y=x+1;y<codeline[lineNumber].length() && (codeline[lineNumber].charAt(y)!=' ');y++);
        val2.append(codeline[lineNumber].subSequence(x,y));
        
        int variable1=0,variable2=0;
        
        switch(val.toString()){
            case "a":
                    variable1=model.a;
                break;
            case "b":
                    variable1=model.b;
                break;      
            case "c":
                    variable1=model.c;
                break;
            case "d":
                    variable1=model.d;
                break;
            case "e":
                    variable1=model.e;
                break;
            case "f":
                    variable1=model.f;
                break; 
            default:
                variable1=Integer.parseInt(val.toString());
                
        }
        
        switch(val2.toString()){
            case "a":
                    variable2=model.a;
                break;
            case "b":
                    variable2=model.b;
                break;      
            case "c":
                    variable2=model.c;
                break;
            case "d":
                    variable2=model.d;
                break;
            case "e":
                    variable2=model.e;
                break;
            case "f":
                    variable2=model.f;
                break; 
            default:
                variable1=Integer.parseInt(val2.toString());
        }
        //add,sub,mul,div,mod
        switch(operation.toString()){
            case "add":
                    ret = variable1+variable2;
                break;
            case "sub":
                    ret = variable1-variable2;
                break;      
            case "mul":
                    ret = variable1*variable2;
                break;
            case "div":
                    ret = variable1/variable2;
                break;
            case "mod":
                    ret = variable1%variable2;
                break;
            default:
                break;
        }
        
        return ret;
    }
    
    public IproModel set (int lineNumber,IproModel model){
        StringBuffer val= new StringBuffer();
        StringBuffer number= new StringBuffer();
        
        int fx=0,lx=0;
        int fy=0,ly=0;
        
        fx=labelSkip(lineNumber);        
        fy=codeline[lineNumber].indexOf(','); 
        for (lx=fx+1;lx<fy &&(codeline[lineNumber].charAt(lx)!=' ');lx++);               
        for (fy++,ly=fy+1;ly<codeline[lineNumber].length() && (codeline[lineNumber].charAt(ly)!=' ');ly++);

        val.append(codeline[lineNumber].subSequence(fx,lx));
        number.append(codeline[lineNumber].subSequence(fy,ly));


        switch(val.toString()){
            case "a":
                model.a = Integer.parseInt(number.toString());             
            case "b":
                model.b = Integer.parseInt(number.toString());
                break;
            case "c":
                model.c = Integer.parseInt(number.toString());
                break;
            case "d":
                model.d = Integer.parseInt(number.toString());
                break;
            case "e":
                model.e = Integer.parseInt(number.toString());
                break;
            case "f":
                model.f = Integer.parseInt(number.toString());
                break;
            default:
               
                break;
        }
        return model;
    }
    
    public String[] codeline;
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
                                
                case 8: 
                    
                    if (codeline[i].contains("MACRO")){
                        SymanticMacroChecker(i,model);
                    }
                    break;                             
                default:;
                       
            }            
        }           
          
            return model;
	}
    
    public void SymanticMacroChecker(int lineNumber, IproModel model){        
        Boolean Status = null;
        Boolean hasEnd = false;
        
        for (lineNumber++; lineNumber<codeline.length-1;lineNumber++) {  
            System.out.println(codeline[lineNumber]);
            if (RegularExpressionCheck( codeline[lineNumber])!=2){    
                if (Status == null && !codeline[lineNumber].contains("start")){                  
                        String Error ="Symantic Error ** MACRO can not found start keyword!";
                        model.ErrorPush(lineNumber, Error);
                        Status = false;
                }else{Status=true;}
                
                if(Status == true && RegularExpressionCheck(codeline[lineNumber]) == 13){                 
                    String Error ="Symantic Error ** MACRO is not capable of having jmp keyword!";
                    model.ErrorPush(lineNumber, Error);
                    Status = false;
                }
                if(Status == true && RegularExpressionCheck(codeline[lineNumber]) == 7){     
                    String Error =" Symantic Error ** MACRO is not capable of having je, jg, jl keyword!";
                    model.ErrorPush(lineNumber, Error); 
                    Status = false;
                }                  
                if (codeline[lineNumber].contains("end")){
                    Status = false;
                    hasEnd = true;
                }                     
            }
        }
        if (hasEnd == false){           
            String Error ="Symantic Error ** MACRO can not found end keyword!";
            model.ErrorPush(lineNumber, Error); 
        }
    
    }
    
    public void SymanticCmpChecker(int lineNumber, IproModel model){        
        
        for (lineNumber++; lineNumber!= codeline.length;lineNumber++){
            if (this.RegularExpressionCheck(codeline[lineNumber])==7){
                break;
            }
            if(this.RegularExpressionCheck(codeline[lineNumber])!=(7&14&2)){
               
                String Error ="Symantic Error! ** cmp should much with je, jg, jl";
                model.ErrorPush(lineNumber, Error);
                break;
            }
        }
    }
    
    public void SymanticLabelChecker(int lineNumber, IproModel model){
        
        StringBuffer label = new StringBuffer();
        int x=0;    
    
        x=labelSkip(lineNumber);
                                
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
