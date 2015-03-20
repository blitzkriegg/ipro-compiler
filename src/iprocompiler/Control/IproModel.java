/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iprocompiler.Control;

import java.util.LinkedList;

/**
 *
 * @author Michael
 */
public class IproModel {
        
   public LinkedList<ErrorDatas> ErrorMessage;
   public int a,b,c,d,e,f;
   public int display;
   
   
    
   public IproModel (){
        ErrorMessage = new LinkedList<ErrorDatas>();
        a=b=c=d=e=f=0;
        display=0;
   }
   
   public void ErrorPush (int lineNumber,String error){
       ErrorDatas data = new ErrorDatas(lineNumber, error);
       ErrorMessage.add(data);
   }
   
    public class ErrorDatas {
        int lineNumber;
        String Message;
        String MessageLine;
        
        public ErrorDatas(int lineNumber, String Message){
            putLineNumber(lineNumber);
            putMessage(Message);
            setMessageLine();
        }
        
        public void putLineNumber(int lineNumber){
            this.lineNumber = lineNumber;
        }
        public void putMessage(String Message){
            this.Message = Message;
        }
        
        public int getLineNumber(){
            return lineNumber;
        }
        public String getMessage(){
            return Message;
        }
        
        public void setMessageLine(){
            MessageLine= Message +" "+"in "+lineNumber+"\n"; 
        }
        public String getMessageLine(){
            return MessageLine; 
        }
    }
   
    
}
