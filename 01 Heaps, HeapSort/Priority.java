import java.util.*;
public class Priority {
   public static void main(String[] args) {
      PriorityQueue<Character> pq = new PriorityQueue<Character>();
      pq.add('e');
      pq.add('d');
      pq.add('H');
      System.out.print(pq.remove() + "\t" + pq);
   }
}