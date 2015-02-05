/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iprocompiler.datamodels;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Michael
 */
public class Data {
    private String[] codelines;
    private List<Integer> errors;
    private List<String> names;
    private HashMap<Integer,String> errorMessages;
    
    public String[] getCodelines() {
        Integer [] a = errorMessages.keySet().toArray(new Integer[0]);
        errorMessages.get(a[1]);
        for(int b :a ){
            //p
        }
        return codelines;
    }

    public HashMap<Integer, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(HashMap<Integer, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void setCodelines(String[] codelines) {
        this.codelines = codelines;
    }

    public List<Integer> getErrors() {
        return errors;
    }

    public void setErrors(List<Integer> errors) {
        this.errors = errors;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
   
}
