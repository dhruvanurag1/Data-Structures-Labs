// Name: Dhruv Anurag
// Date: 

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
         System.out.println("File not Found");
      }
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>> dictionary = new TreeMap<String, Set<String>>();
      while(infile.hasNext()) {
         add(dictionary, infile.next(), infile.next());
      }
      return dictionary;
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
      if(dictionary.isEmpty()) {
         Set<String> temp = new HashSet<String>();
         temp.add(translation);
         dictionary.put(word, temp);
      }
      else if(dictionary.containsKey(word)) {
         dictionary.get(word).add(translation);
      }
      else {
         Set<String> tempa = new HashSet<String>();
         tempa.add(translation);
         dictionary.put(word, tempa);
      }
   }
   
   public static void display(Map<String, Set<String>> m)
   {
      for(String a : m.keySet()) {
         System.out.println(a + " " + m.get(a));
      }
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>> reverse = new TreeMap<String, Set<String>>();
      for(String a: dictionary.keySet()) {
         for(String str: dictionary.get(a)) {
            add(reverse, str, a);
         }
      }
      return reverse;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/