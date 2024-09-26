// Name: Dhruv Anurag
// Date: 3/4/2021

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
   private static String display(TreeNode t, int level)
   {
      // turn your head towards left shoulder visualize tree
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += inorderTraverse(t.getRight());
        
      return toReturn;
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += postorderTraverse(t.getLeft());
      toReturn += postorderTraverse(t.getRight());
      toReturn += t.getValue() + " ";
      return toReturn;
   }
   
   public static int countNodes(TreeNode t)
   {
      if(t == null) {
         return 0;
      }
      return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
   }
   
   public static int countLeaves(TreeNode t)
   {
      if (t == null) {
         return 0;
      }
      else {
         if(t.getLeft() == null && t.getRight() == null) {
            return 1;
         }
         else {
            return countLeaves(t.getLeft()) + countLeaves(t.getRight());
         }
      }
   }
   
   /*  there are clever ways and hard ways to count grandparents  */ 
   public static int countGrandParents(TreeNode t)
   {
      if(t == null) {
         return 0;
      }
      if(t.getLeft() == null && t.getRight() == null) {
         return 0;
      }
      if(t.getLeft() != null && t.getRight() == null) {
         if(t.getLeft().getLeft() == null && t.getLeft().getRight() == null) {
            return 0;
         }
      }
      if(t.getRight() != null && t.getLeft() == null) {
         if(t.getRight().getLeft() == null && t.getRight().getRight() == null) {
            return 0;
         }
      }
      if(t.getLeft() != null && t.getLeft() != null) {
         if(t.getLeft().getLeft() == null && t.getLeft().getRight() == null && t.getRight().getLeft() == null && t.getRight().getRight() == null) {
            return 0;
         }
      }
      return 1 + countGrandParents(t.getLeft()) + countGrandParents(t.getRight());
   }
   
   public static int countOnlys(TreeNode t)
   {
      if(t == null) {
         return 0;
      }
      else {
         if(t.getLeft() != null && t.getRight() == null) {
            return 1 + countOnlys(t.getLeft());
         }
         if(t.getLeft() == null && t.getRight() != null) {
            return 1 + countOnlys(t.getRight());
         }
         else {
            return countOnlys(t.getLeft()) + countOnlys(t.getRight());
         }
      }
   }
   
  /* returns the max of the heights to the left and the heights to the right  
     returns -1 in case the tree is null
    */
   public static int height(TreeNode t)
   {
      if(t == null) {
         return -1;
      }
      int left = height(t.getLeft());
      int right = height(t.getRight());
      if(left > right) {
         return left + 1;
      }
      else {
         return right + 1;
      }
   }
   
 /* return the max of the sum of the heights to the left and the heights to the right  
 */
   public static int longestPath(TreeNode t)
   {
      if(t == null) {
         return 0;
      }
      while(t.getLeft() == null || t.getRight() == null) {
         if(t.getLeft() == null) {
            t = t.getRight();
         }
         else if(t.getRight() == null) {
            t = t.getLeft();
         }
      }
      return 2 + height(t.getLeft()) + height(t.getRight());
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t)
   {
      if(t == null) {
         return "z";
      }
      Object min = t.getValue();
      Object left = min(t.getLeft());
      Object right = min(t.getRight());
      if(left.toString().compareTo(""+min) < 0) {
         min = left;
      }
      if(right.toString().compareTo(""+min) < 0) {
         min = right;
      }
      return min;
      
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object max(TreeNode t)
   {
      if(t == null) {
         return "A";
      }
      Object max = t.getValue();
      Object left = max(t.getLeft());
      Object right = max(t.getRight());
      if(left.toString().compareTo(""+max) > 0) {
         max = left;
      }
      if(right.toString().compareTo(""+max) > 0) {
         max = right;
      }
      return max;
   }
      
   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
   public static String displayLevelOrder(TreeNode t)
   {
      Queue<TreeNode> nodes = new LinkedList<TreeNode>();
      nodes.add(t);
      String answer = "";
      while(!nodes.isEmpty()) {
         TreeNode a = nodes.remove();
         answer += (String) a.getValue();
         if(a.getLeft() != null) {
            nodes.add(a.getLeft());
         }
         if(a.getRight() != null) {
            nodes.add(a.getRight());
         }
      }
      return answer;
   }
}

/***************************************************
    ----jGRASP exec: java TreeLab
 		  	E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC    
 *******************************************************/


