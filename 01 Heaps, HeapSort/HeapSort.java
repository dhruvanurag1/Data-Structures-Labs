// Name: Dhruv Anurag
// Date:
import java.text.*;
public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      /*SIZE = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));*/
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
       SIZE = 100;
       double[] heap = new double[SIZE + 1];
       heap = createRandom(heap);
       display(heap);
       makeHeap(heap, SIZE);
       display(heap); 
       sort(heap);
       display(heap);
       System.out.println(isSorted(heap));
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
      for(int i = array.length - 1; i > 1; i--) {
         swap(array, 1, i);
         heapDown1(array, 1, i-1);
      }
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
      if(array[3] > array[4]) {
         swap(array, 3, 4);
      }
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double c = array[a];
      array[a] = array[b];
      array[b] = c;
   }
   
   public static void heapDown(double[] array, int k, int size)
   {
      int left = k * 2;
      int right = k * 2 + 1;
      if(k > size || (left > size && right > size)) {
         return;
      }
      else {
         if(right <= size) {
            int max = 0;
            if(array[left] > array[right]) {
               max = left;
            }
            else {
               max = right;
            }
            if(array[k] < array[max]) {
               swap(array, k, max);
               heapDown(array, max, size);
            }
         }
      }
   }
   
   public static boolean isSorted(double[] arr)
   {
      if(arr.length < 2) {
         return true;
      }
      int index = 0;
      boolean left = false;
      boolean right = false;
      while(arr[index] <= arr[2 * index + 1]) {
         if(((index * 2) + 1) * 2 + 1 >= arr.length) {
            left = true;
            break;
         }
         else {
            index = (index * 2) + 1;
         }
      }
      index = 0;
      if(arr.length < 3) {
         right = true;
      }
      else {
         while(arr[index] <= arr[2 * index + 2]) {
            if(((index * 2) + 2) * 2 + 2 >= arr.length) {
               right = true;
               break;
            }
            else {
               index = (index * 2) + 2;
            }
         }
      }
      return left && right;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      DecimalFormat decimal = new DecimalFormat("0.00");
      for(int i = 1; i < 101; i++) {
         array[i] = Double.parseDouble(decimal.format(Math.random() * 100 + 1));
      }
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
      for(int i = size/2; i > 0; i--) {
         heapDown(array, i, size);
      }
   }
}

