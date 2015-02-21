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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michael
 */
public class IProCompiler {
    private int     lineNo;
    private String  line;
    final   String  nextline ="\n";    
    private int     patternNo;

    static String[] regexes = {"\\s*(?i)put\\s+((?i)[a-f]|\\w*(\\d{1,})*(\\W\\D)*|\\n|\\t\\b)\\s*",     //0 put                          2
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
    
     public int StaticSymanticChecker(String path){
            BufferedReader br = null; 
            
            String subline; //case7 (cmp)
            int counter=0;//case7 (cmp) if get out method need to traverse file pointer 
                
            StringBuffer compare = new StringBuffer(line),label = new StringBuffer();//case 8 9 (MACRO, Label)
            
            
		try { 
			br = new BufferedReader(new FileReader(path));                     
                        
			switch(patternNo){    
                            case 7: case 13:
                                int x=0;
                                
                                for (;(compare.charAt(x)==' ');x++);
                                for (;(compare.charAt(x)!=' ');x++);                              
                                for (;(compare.charAt(x)==' ');x++);
                                
                                label.append(compare.subSequence(x,compare.length()));
                                label.append(':');
                                for (x=0;(subline = br.readLine()) != null;x++){               
                                    if (x!=lineNo && subline.contains(label)){
                                        break;          
                                    }
                                }
                                if (subline==null){
                                    System.out.println("                    Symantics Error!! ** Label dose not found!!");                                   
                                }     
                                break;
                            case 6:
                                for(int i=0;(subline = br.readLine()) != null && i<lineNo;i++);
                                for (lineNo--;(subline = br.readLine()) != null && subline.compareTo("\n")==-1;lineNo++,counter++); 
                                Pattern r = Pattern.compile(regexes[7]);                                   
                                Matcher m = r.matcher(subline);
                                if (!m.find( )) {
                                    System.out.println("                    Symantic Error! ** cmp should much with je, jg, jl");   
                                    
                                }
                                break;
                            
                            case 14:
                                r = Pattern.compile(regexes[10]);                                   
                                m = r.matcher(line);
                                
                                //if it is MACRO then
                                if (m.find( )) {
                                    for(int i=0;(subline = br.readLine()) != null && i<=lineNo;i++);
                                    x=1;
                                    for (int i=0;(subline = br.readLine()) != null && x!=0;i++){
                                        for (;subline.compareTo("\n")==-1 && (subline = br.readLine()) != null;);
                                        if (i==0 && !subline.contains("start")){
                                            System.out.println("                    Symantic Error ** MACRO can not found start keyword!");
                                            x=0;
                                            break;
                                        }
                                        r = Pattern.compile(regexes[13]);                                   
                                        m = r.matcher(subline);
                                        if (m.find( )){
                                            System.out.println("                    Symantic Error ** MACRO is not capable of having jmp keyword!");
                                            
                                            x=0;
                                            break;
                                        }
                                        r = Pattern.compile(regexes[7]);                                   
                                        m = r.matcher(subline);
                                        if (m.find( )){
                                            System.out.println("                    Symantic Error ** MACRO is not capable of having je, jg, jl keyword!");
                                            
                                            x=0;
                                            break;
                                        }     
                                        if (subline.contains("start") && i!=0)
                                            x++;
                                        else if (subline.contains("end"))
                                            x--;
                                    }
                                    if (x>0){
                                        System.out.println("                    Symantic Error ** MACRO can not found end keyword!");
                                        
                                    }
                                }
                                break;                             
                            default:;
                        }
                                                                                               
//=======================			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		
			
		try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return counter;	
	}
        
        public void DynamicSymanticChecker(String path ,int patternNo){
            BufferedReader br = null; 

		try { 
			br = new BufferedReader(new FileReader(path));                     
                        
			switch(patternNo){    
                            case 7: case 13:
                                
                                break;
                            case 6:
                                
                            case 14:
                                
                                break;                             
                            default:;
                        }
                                                                                               
//=======================			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		
			
		try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
        }
        
        public void RegExChecker(String path){
		
		BufferedReader br = null;
                int counter=0;
                
		try { 
			
			br = new BufferedReader(new FileReader(path));                     
                       
			while ((line = br.readLine()) != null) {                           
                            for (;line.compareTo(nextline)==-1 && (line = br.readLine()) != null; lineNo++);
                            System.out.print(line);
                                                             
                            for (patternNo=0;patternNo<regexes.length;patternNo++){
          
                                Pattern r = Pattern.compile(regexes[patternNo]);                                   
                                Matcher m = r.matcher(line);
                                if (m.find( )) {
                                    System.out.println("\n");//                    Success!");
                                    counter=this.StaticSymanticChecker(path);
                                    for (;counter>0 && (line = br.readLine()) != null ;counter--);
                                    break;
                                }
                            }
                            if (patternNo>=regexes.length){
                                System.out.println("                    Syntax Error!!");
                                
                            }
                            lineNo++;
                            
			}
                
//=======================			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		
			
		try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
			
	}
    
}
