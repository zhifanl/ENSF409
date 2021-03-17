package edu.ucalgary.ensf409;

public class StringProcessor{
    
    private final String storedString;
    
    public StringProcessor(String input){
        this.storedString = new String(input);
    }
    
    public String addTogetherMirror(String inputString) {
        String combined = storedString.trim() + inputString.trim();

        return new StringBuilder(combined.toLowerCase()).reverse().toString();
    }

    public static String idProcessing(String firstName, String lastName, String petName, int year) {
        for(int i=0;i<firstName.length();i++){
            if(!Character.isLetter(firstName.charAt(i))){
                if(firstName.charAt(i)!='''||firstName.charAt(i)!='-'||firstName.charAt(i)!=' '){
                    throw new IllegalArgumentException();

                }
            }
        }
        if(!Character.isLetter(firstName.charAt(0))||!Character.isLetter(lastName.charAt(0))||!Character.isLetter(petName.charAt(0))){
            throw new IllegalArgumentException();
        }
        if(!Character.isLetter(firstName.charAt(firstName.length()-1))||!Character.isLetter(lastName.charAt(lastName.length()-1))||!Character.isLetter(petName.charAt(petName.length()-1))){
            throw new IllegalArgumentException();
        }
        if(!Character.isLowerCase(firstName.charAt(firstName.length()-1))||!Character.isLowerCase(lastName.charAt(lastName.length()-1))||!Character.isLowerCase(petName.charAt(petName.length()-1))){
            throw new IllegalArgumentException();
        }
        if(!Character.isUpperCase(firstName.charAt(0))||!Character.isUpperCase(lastName.charAt(0))||!Character.isUpperCase(petName.charAt(0))){
            throw new IllegalArgumentException();
        }
        if(firstName.length()>26||firstName.length()<2||lastName.length()>26||lastName.length()<2||petName.length()>26||petName.length()<2){
            throw new IllegalArgumentException();
        }
        if(year>2021){
            throw new IllegalArgumentException();
        }
        String petID = new String(String.valueOf(firstName.charAt(0)) + String.valueOf(lastName.charAt(0)) + String.valueOf(petName.charAt(0)) + String.valueOf(year));
        return petID;
    }

    public String secretCode(int offset) {

        StringBuilder encodedString = new StringBuilder();

        while(offset>=26){
            offset-=26;
        }
        while(offset<0){
            offset+=26;
        }
        for (int i = 0; i < storedString.length(); i++){
            if(Character.isLetter(storedString.charAt(i))){
            int newUnicode = storedString.charAt(i) + offset;
            if(newUnicode>90&&storedString.charAt(i)>=65&&storedString.charAt(i)<=90){
                newUnicode-=26;
            }else if(newUnicode<65&&storedString.charAt(i)>=65&&storedString.charAt(i)<=90){
                newUnicode+=26;
            }else if(newUnicode>122&&storedString.charAt(i)>=97&&storedString.charAt(i)<=122){
                newUnicode-=26;
                
            }else if(newUnicode<97&&storedString.charAt(i)>=97&&storedString.charAt(i)<=122){
                newUnicode+=26;
            }
            encodedString.append(String.valueOf((char)newUnicode));}
            else{
            encodedString.append(String.valueOf(storedString.charAt(i)));
            }
        }
        
        return encodedString.toString();
    }
    
    public String getStoredString(){
        return this.storedString;
    }

}