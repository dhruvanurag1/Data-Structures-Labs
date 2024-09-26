// Name: Dhruv Anurag
// Date:
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   //returns the grid as a String
   int edgeCount();
   List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix,Warshall,Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  enter your code here  */  
   public AdjMat(int size) {
      vertices = new TreeMap<String, Integer>();
      grid = new int[size][size];
      for(int i = 0; i < size; i++) {
         for(int j = 0; j < size; j++) {
            grid[i][j] = 0;
         }
      }
   }
   public void addEdge(int source, int target) {
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target) {
      grid[source][target] = 0;
   }
   public boolean isEdge(int from, int to) {
      return grid[from][to] < 9999 && grid[from][to] != 0;
   }
   public int edgeCount() {
      int count = 0;
      for(int i = 0; i < grid.length; i++) {
         for(int j = 0; j < grid[0].length; j++) {
            if(grid[i][j] < 9999 && grid[i][j] != 0) {
               count++;
            }
         }
      }
      return count;
   }
   public String toString() {
      String string = "";
      for(int i = 0; i < grid.length; i++) {
         for(int j = 0; j < grid.length; j++) {
            string += Integer.toString(grid[i][j]) + " ";
         }
         string.trim();
         string += "\n";
      }
      return string;
   }
   public List<Integer> getNeighbors(int source) {
      List<Integer> list = new ArrayList<Integer>();
      for(int i = 0; i < grid[0].length; i++) {
         if(grid[source][i] == 1 && i != source) {
            list.add(i);
         }
      }
      return list;
   }
   public boolean isEdge(String from, String to) {
      return isEdge(vertices.get(from), vertices.get(to));
   }
   public Map<String, Integer> getVertices() {
      return vertices;
   }    
   public void readNames(String fileName) throws FileNotFoundException {
      Scanner sc = new Scanner(new File(fileName));
      int length = sc.nextInt();
      for(int i = 0; i < length; i++) {
         vertices.put(sc.next(), i);
      }
   }
   public void readGrid(String fileName) throws FileNotFoundException {
      Scanner sc = new Scanner(new File(fileName));
      int size = sc.nextInt();
      for(int i = 0; i < size; i++) {
         for(int j = 0; j < size; j++) {
            grid[i][j] = sc.nextInt();
         }
      }
   }
   public void displayVertices() {
      for(String s : vertices.keySet()) {
         System.out.println(vertices.get(s) + "-" + s);
      }
      System.out.println();
   }
   public void allPairsReachability() {
      for(int i = 0; i < grid.length; i++) {
         for(int j = 0; j < grid.length; j++) {
            for(int k = 0; k < grid[0].length; k++) {
               if(isEdge(j, i) && isEdge(i, k)) {
                  addEdge(j, k);
               }
            }
         }
      }
   } 
   public int getCost(int from, int to) {
      return grid[from][to];
   }
   public int getCost(String from, String to) {
      return getCost(vertices.get(from), vertices.get(to));
   }
   public void allPairsWeighted() {
      for(int a = 0; a < grid.length; a++) {
         for(int b = 0; b < grid.length; b++) {
            for(int c = 0; c < grid.length; c++) {
               if(grid[b][c] > grid[b][a] + grid[a][c]) {
                  grid[b][c] = grid[b][a] + grid[a][c];
               }
            }
         }
      }
      /*int var = 0;
      for(int a = 0; a < 2; a++) {
         for(int b = 0; b < grid.length; b++) {
            for(int c = 0; c < grid.length; c++) {
               for(int d = 0; d < grid[0].length; d++) {
                  if(isEdge(c, b) && isEdge(b, d)) {
                     var = grid[c][b] + grid[b][d];
                     if(var < grid[c][d]) {
                        grid[c][d] = var;
                     }
                  }
               }
            }
         }
      }*/
   }
}
