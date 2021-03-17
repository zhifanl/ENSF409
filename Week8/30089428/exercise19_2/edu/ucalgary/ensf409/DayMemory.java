/**
 * @author Zhifan li 30089428
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.io.IOException;

/* main()
   * Accept a command-line argument which specifies a translation file.
   * The argument should be in the form of a two-letter language code,
   * followed by a dash and a two-letter region code, e.g., en-US
   * which corresponds with files en-US.txt and en-US.ser
   * If no argument is specified, it throws a custom exception,
   * CommandArgumentNotProvidedException. Additional arguments are
   * ignored.
  */

public class DayMemory{
public static void main(String[] args) throws CommandArgumentNotProvidedException,ArgFileNotFoundException,IOException {
  if(args.length==0){
    throw new CommandArgumentNotProvidedException();
  }
  Translator trans=new Translator(args[0]);
  
  System.out.println(trans.translate(3, 8, 2021));
  
}
}
