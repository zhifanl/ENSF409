import static org.junit.Assert.assertEquals;
import edu.ucalgary.ensf409.*;
import org.junit.Test;
 public class StringProcessorTest{
     

     @Test
     public void testAddTogetherMirror(){
        String input="Zhifan Li";
     StringProcessor processor=new StringProcessor(input);
        String result=processor.addTogetherMirror("is me");
        assertEquals("em siil nafihz",result);
     }

     @Test(expected=IllegalArgumentException.class)
     public void testIdProcessingNormal(){
        String input="Zhifan Li";
        StringProcessor processor=new StringProcessor(input);
        String firstName="Zhifan";
        String lastName="Li";
        String pet="Mika";
        int year=2022;
        String result=StringProcessor.idProces(firstName,lastName,pet,year);
       assertEquals("ZLM2022",result);

    }
    @Test(expected=IllegalArgumentException.class)
    public void testIdProcessingWrongNames(){
        String input="Zhifan Li";
        StringProcessor processor=new StringProcessor(input);
        String firstName="Z";
       String lastName="L";
       String pet="M";
       int year=2020;
       String result=StringProcessor.idProces(firstName,lastName,pet,year);
       

   }
   @Test(expected=IllegalArgumentException.class)
   public void testIdProcessingWrongPetName(){
       String input="Zhifan Li";
     StringProcessor processor=new StringProcessor(input);
      String firstName="Z8";
      String lastName="L1";
      String pet="M";
      int year=2020;
      String result=StringProcessor.idProces(firstName,lastName,pet,year);
      

  }
  @Test(expected=IllegalArgumentException.class)
  public void testIdProcessing26letters(){
      String input="Zhifan Li";
      StringProcessor processor=new StringProcessor(input);

     String firstName="Zuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu";
     String lastName="L";
     String pet="M";
     int year=2020;
     String result=StringProcessor.idProces(firstName,lastName,pet,year);
     

 }
 @Test(expected=IllegalArgumentException.class)
 public void testIdProcessingWrongDate(){
     String input="Zhifan Li";
     StringProcessor processor=new StringProcessor(input);

    String firstName="Zh";
    String lastName="Li";
    String pet="Mi";
    int year=2022;
    String result=StringProcessor.idProces(firstName,lastName,pet,year);
    

}
@Test(expected=IllegalArgumentException.class)
public void testIdProcessingCapital(){
    String input="zhifan Li";
    StringProcessor processor=new StringProcessor(input);

   String firstName="zhifan";
   String lastName="li";
   String pet="mika";
   int year=2020;
   String result=StringProcessor.idProces(firstName,lastName,pet,year);
   

}
@Test
public void testIdProcessingPunctuationSpace(){
    String input="Zhifan Li";  
       StringProcessor processor=new StringProcessor(input);

   String firstName="Zhifan li";
   String lastName="Last name";
   String pet="O'Malley";
   int year=2020;
   String result=StringProcessor.idProces(firstName,lastName,pet,year);
   assertEquals("ZLO2020",result);

}
@Test
public void testSecretCode(){
    String input="Zhifan Li";
    StringProcessor processor=new StringProcessor(input);

    String result=processor.secretCode(1);
    System.out.println("RESULT: "+result);
    
    assertEquals("Aijgbo Mj",result);


}
@Test
public void testSecretCode1(){
    String input="Zhifan Li";
    StringProcessor processor=new StringProcessor(input);

    String result=processor.secretCode(26);
    System.out.println("RESULT: "+result);
    
    assertEquals("Zhifan Li",result);

    String result1=processor.secretCode(52);
    System.out.println("RESULT: "+result1);
    
    assertEquals("Zhifan Li",result1);

    String result2=processor.secretCode(-26);
    System.out.println("RESULT: "+result2);
    
    assertEquals("Zhifan Li",result2);

    String result3=processor.secretCode(27);
    System.out.println("RESULT: "+result3);
    
    assertEquals("Aijgbo Mj",result3);
}


}