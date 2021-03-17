package edu.ucalgary.ensf409;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcessor{
    
    private final String storedString;
    
    public StringProcessor(String input){
        this.storedString = new String(input);
    }
    
    public String addTogetherMirror(String inputString) {
        //String combined = storedString.stripLeading() + inputString.stripTrailing();
    	StringBuilder result = new StringBuilder();
    	inputString = inputString.trim();
    	String temp = this.storedString.trim();
    	for(int i = inputString.length()-1; i>=0 ; i--) {
    		result.append(Character.toLowerCase(inputString.charAt(i)));
    	}
    	for(int i = temp.length()-1; i>=0; i--) {
    		result.append(Character.toLowerCase(temp.charAt(i)));
    	}
    	String combined = result.toString();
        return combined;
    }

    public static String idProcessing(String firstName, String lastName, String petName, int year) {
        
        //String petID = new String(String.valueOf(firstName.charAt(0)) + String.valueOf(lastName.charAt(0)) + String.valueOf(petName.charAt(0)) + String.valueOf(year));
    	if(!checkValidName(firstName) || !checkValidName(lastName) || !checkValidName(petName)) {
    		throw new IllegalArgumentException();
    	}
    	if(year > 2021 || String.valueOf(year).length() != 4) {
    		throw new IllegalArgumentException();
    	}
    	
    	String petID = Character.toString(firstName.charAt(0)) 
    				 + Character.toString(lastName.charAt(0))
    				 + Character.toString(petName.charAt(0))
    				 + Integer.toString(year);
    	;
        return petID;
    }
    
    private static boolean checkValidName(String name) {
    	String REGEX = "[A-Z]+[a-z]*([',\s,\\-,.,‘,’]{1}[A-Z]*[a-z]+)*[a-z]+";
    	Pattern PATTERN = Pattern.compile(REGEX);
    	Matcher m = PATTERN.matcher(name);
    	if(! m.matches()) {
    		return false;
    	}
    	
    	if(name.length() > 26 || name.length() < 2) {
    		return false;
    	}
    	return true;
    }

    public String secretCode(int offset) {
        String encodedString = new String();
        
        for (int i=0; i < storedString.length(); i++) {
        	int newUnicode = storedString.charAt(i);
        	if(Character.isLowerCase(newUnicode)) {
        		newUnicode = newUnicode + offset;
        		while(newUnicode - 97 > 25) {
        			newUnicode -= 26;
        		}
        		while(newUnicode - 97 < 0) {
        			newUnicode += 26;
        		}
        		encodedString += (char)newUnicode;
        	}
        	else if(Character.isUpperCase(newUnicode)) {
        		newUnicode = newUnicode + offset;
        		while(newUnicode - 65 > 25) {
        			newUnicode -= 26;
        		}
        		while(newUnicode - 65 < 0) {
        			newUnicode += 26;
        		}
        		encodedString += (char)newUnicode;
        	}
        	else {
        		encodedString += storedString.charAt(i);
        	}
        }
        
        /*
        for (int i = 0; i < storedString.length(); i++){
            int newUnicode = storedString.charAt(i) + offset;
            encodedString.concat(String.valueOf(newUnicode));
        }
        */
    	
        return encodedString;
    }
    
    public String getStoredString(){
        return this.storedString;
    }

}

