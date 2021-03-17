/**
 * @author Zhifan li 30089428
 * @version 1.0
 * @since 1.0
 */


package edu.ucalgary.ensf409;

import java.io.*;
import java.util.Arrays;

public  class Translator{
  private TranslationText translation;
  private String fileName;
  /* getTranslation()
   * getter method
  */
  public TranslationText getTranslation(){
    return translation;
  }


 /* translate()
   * Accepts a month number (e.g., 1 for January), a day number (e.g., 31 for
   * the 31st), and a year. Throws an IllegalArgumentException if monthNum or dayNum is 
   * not valid. Returns the formatted sentence as a String. Notice that the String
   * containing formatting uses numbered arguments - this is because some languages
   * will put the words in the sentence in a different order, but the translate()
   * method must be able to work without knowledge of the language structure.
   * Note: You do not have to check if a day is valid for a particular month/year;
   * February 31 or February 29, 2021 can both be accepted, but out of range values
   * e.g., month 15 day 40, are not valid and should be handled with an 
   * IllegalArgumentException. 
  */

  public String translate(int month,int day,int year){
    if(month<1||month>12){
      throw new IllegalArgumentException();
    }
    if(day<1||day>31){
      throw new IllegalArgumentException();
    }
    String dayFormatted=translation.getDay(day-1);
    String monthFormatted=translation.getMonth(month-1);
    String result=String.format(translation.getSentence(),dayFormatted, monthFormatted,year);
    return result;


  }
  /* Constructor
   * Accepts a String of a two-letter language code, dash, and two-letter region
   * code, e.g., te-IN and throws an IllegalArgumentException if the language and
   * region code are not in the correct format. Language codes are ISO 639-1 and
   * region codes are ISO 3166, but this method only checks the format of the String, 
   * not the validity of the codes. It calls importTranslation().
  */
  public Translator(String code)throws ArgFileNotFoundException,IOException  {
	  
    if(code.length()!=5){
      throw new IllegalArgumentException();
    }
    if(!Character.isLetter(code.charAt(0))||!Character.isLetter(code.charAt(1))||!String.valueOf(code.charAt(2)).equals("-")){
      throw new IllegalArgumentException();
    }
    if(!Character.isLetter(code.charAt(3))||!Character.isLetter(code.charAt(4))){
      throw new IllegalArgumentException();
    }
    if(!Character.isUpperCase(code.charAt(3))||!Character.isUpperCase(code.charAt(4))){
      throw new IllegalArgumentException();
    }
    fileName=code; // set it to code;
    importTranslation();
  }
 /* importTranslation()
   * Calls deserialize() if the appropriate file exists, otherwise calls importFromText().
   * No arguments. Returns void.
  */
  public void importTranslation()throws ArgFileNotFoundException,IOException  {
		File name = new File(fileName+".ser");
    if(name.exists()){
      try{
    	  deserialize();
      }catch(IOException e){
       throw new IOException();
      }
    }else{
      try{
      importFromText();
      }catch(ArgFileNotFoundException e){
        throw new ArgFileNotFoundException();
      }catch(IOException e){
        throw new IOException();
      }
    }
  }
  /* importFromText()
   * Reads in from a the two-letter language code, dash, two-letter region code text 
   * file, in the form of ab-XY.txt, and instantiates a TranslationText object with
   * the data. It can throw I/O exceptions, as well as a custom ArgFileNotFoundException. 
   * We expect the .txt file to be in a valid format. The file is expected to be in the same 
   * directory. The files en-US.txt and el-GR.txt are examples of a valid .txt files. They will 
   * always consist of the month names, one per line, followed by the day names, one per line, 
   * followed by the sentence containing formatting strings. This is the last line in the file. You
   * cannot make any assumptions about what will appear on each line, only that each line
   * will contain only one data element, and that it will not contain an empty line.
   * No arguments. Returns void.
  */
  public void importFromText() throws ArgFileNotFoundException,IOException {
	  
    File file=new File(fileName+".txt");
    if(!file.exists()){
      throw new ArgFileNotFoundException();
    }
    FileReader a=null;
    BufferedReader b=null;
    try{
    a=new FileReader(fileName+".txt");
     b=new BufferedReader(a);
     String[] inputs=new String[44];
     String[] months=new String[12];
     String[] days=new String[31];
     String sentence="";
     int i=0;
     String input;
     while((input=b.readLine())!=null){
            inputs[i]=input;
            i++;
     }
     months=Arrays.copyOfRange(inputs,0,12);
     days=Arrays.copyOfRange(inputs,12,43);
     sentence=inputs[43];

      translation=new TranslationText(months,days,sentence); 
    }catch(IOException e){
      System.out.println("IOException occurs when reading file..");
      throw new IOException();
    }
    finally{
      if (b != null) {
				try {
					b.close();
				}
				catch (IOException e) {
					System.out.println("Couldn't close file ");
          throw new IOException();
				}
			}
      if (a != null) {
				try {
					a.close();
				}
				catch (IOException e) {
					System.out.println("Couldn't close file ");
          throw new IOException();
				}
			}
    }


    }
  

  
  /* serialize()
  * Creates a serialized object file of the TranslationText object, with the
  * name format la-CO.ser, where la is the two-letter language code and CO is
  * the two-letter region code. An example of a serialized object file can be
  * found in the exercise directory as es-BO.ser
  * I/O exceptions can be thrown.
   * No arguments. Returns void.
  */

  public void serialize() throws IOException{
    ObjectOutputStream output=null;
    try{
      output=new ObjectOutputStream(new FileOutputStream(fileName+".ser"));
      try{
        output.writeObject(translation);
      }catch(IOException e){
        System.err.println("Error when trying to writeObject");
        throw new IOException();
      }
    }catch(IOException e){
      System.err.println("Error opening file");
      throw new IOException();
    }
    finally{
      try {
				if (output != null) {
					output.close();
				}
			}
			catch(IOException e) {
				System.err.println("Error closing file.");
        throw new IOException();
			}
    }

  }

   /* deserialize()
   * Creates a TranslationText object from a .ser file. The files are named
   * xx-YY.ser, where xx is the two-letter language code and YY is the two-
   * letter region code. es-bo.ser is an example. It can throw I/O exceptions.
   * No arguments. Returns void.
  */
  public void deserialize() throws IOException {
    ObjectInputStream input=null;

    try{
      input=new ObjectInputStream(new FileInputStream(fileName+".ser"));
    }catch(IOException e){
        System.err.println("Error opening file");
        throw new IOException();
      }
    
    try{
    	translation=(TranslationText)input.readObject();
      }catch(ClassNotFoundException e){
        System.err.println("Class not found Error when trying to readObject...");
        throw new IOException();
      }catch(IOException e) {
    	  System.err.println("IO Error when trying to readObject...");
        throw new IOException();
      }
    finally{
      try {
				if (input != null) {
					input.close();
				}
			}
			catch(IOException e) {
				System.err.println("Error closing file.");
        throw new IOException();
			}
    }
  }

}