/**
 * @author Zhifan li 30089428
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.io.Serializable;

/* TranslationText
 * Serializable representation of the data file. Has the serialVersionUID of 19.
 * No method in this class throws an exception.
*/
public class TranslationText implements Serializable{
  private static final long serialVersionUID=19;
  private String sentence;
  private String[]months;
  private String[]days;

  public String getSentence(){
    return sentence;
  }
  public String[] getMonths(){
    return months;

  }
  public String[] getDays(){
    return days;

  }
  public String getMonth(int index){
    return months[index];
  }
  public String getDay(int index){
    return days[index];
  }
  public TranslationText(String[] months,String[]days,String sentence){
    this.months=months;
    this.days=days;
    this.sentence=sentence;
  }

}

  /* getSentence()
   * Getter method, returns String[]
  */

  /* getMonths()
   * Getter method, returns String[]
  */

  /* getDays()
   * Getter method, returns String[]
  */

  /* getMonth()
   * Accepts an integer 0-11 corresponding to an index in the months array,
   * and returns the value at that index.
  */

  /* getDay()
   * Accepts an integer 0-30 corresponding to an index in the day array,
   * and returns the value at that index.
  */

  /* Constructor
   * Accepts a String array of months, a String array of days, and a String 
   * containing a sentence with formatting.
  */

