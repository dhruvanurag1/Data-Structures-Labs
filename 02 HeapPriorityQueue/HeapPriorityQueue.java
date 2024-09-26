 //Name: Dhruv Anurag 
 //Date:
 
import java.util.*;


/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      if(myHeap.size() >= 3) {
         heapUp(myHeap.size() - 1);
      }
      return true;
   }
   
   public E remove()
   {
      if(myHeap.isEmpty()) {
         return null;
      } 
      E obj = myHeap.get(1); 
      if(myHeap.size() < 3) {
         myHeap.remove(1);
      }
      else {
         swap(1, myHeap.size() - 1);
         myHeap.remove(myHeap.size() - 1);
         heapDown(1, myHeap.size());
      }
      return obj;
   }  
   
   public E peek()
   {
      if(myHeap.size() == 1) {
         return myHeap.get(0);
      }
      else {
         return myHeap.get(1);
      }
      
   }
   
   public boolean isEmpty()
   {
      if(myHeap.size() <= 1) {
         return true;
      }
      else {
         return false;
      }
   }
   
   private void heapUp(int k)
   {
      int a = k/2;
      if(a < 1) {
         return;
      }
      else {
         if(myHeap.get(k).compareTo(myHeap.get(a)) > 0) {
            return;
         }
         else {
            swap(k, a);
            heapUp(a);
         }
      }
   }
   
   private void swap(int a, int b)
   {
      Collections.swap(myHeap, a, b);
   }
   
   private void heapDown(int k, int size)
   {
      int left = k * 2;
      int right = k * 2 + 1;
      if(k >= size || (left >= size && right >= size)) {
         return;
      }
      else {
         if(right < size) {
            int max = 0;
            if(myHeap.get(left).compareTo(myHeap.get(right)) < 0) {
               max = left;
            }
            else {
               max = right;
            }
            if(myHeap.get(k).compareTo(myHeap.get(max)) > 0) {
               swap(k, max);
               heapDown(max, size);
            }
         }
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
