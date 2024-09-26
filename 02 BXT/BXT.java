// Name: Dhruv Anurag
// Date:  
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      StringTokenizer st = new StringTokenizer(str);
      Stack<TreeNode> stack = new Stack<TreeNode>();
      TreeNode par;
      TreeNode temp;
      while(st.hasMoreTokens()) {
         stack.push(new TreeNode(st.nextToken()));
      }
      root = stack.pop();
      par = root;
      temp = null;
      while(!stack.isEmpty()) {
         temp = stack.pop();
         if(isOperator(temp.getValue()+"")) {
            if(par.getLeft() != null && par.getRight() == null){
               par.setRight(temp);
            }
            else if(par.getLeft() == null && par.getRight() != null) {
               par.setLeft(temp);
            }
            else {
               par.setRight(temp);
            }
            par = temp;
         }
         else {
            if(par.getLeft() != null && par.getRight() == null) {
               par.setRight(temp);
            }
            else if(par.getLeft() == null && par.getRight() != null) {
               par.setLeft(temp);
            }
            else {
               par.setRight(temp);
            }
            if(par.getLeft() != null && par.getRight() != null) {
               par = root;
            }
         }
      }
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t == null) {
         return 0;
      }
      if(isOperator((String)t.getValue())) {
         return computeTerm((String)t.getValue(), evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      }
      else {
         return Double.parseDouble((String) t.getValue());
      }
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+")) {
         return a + b;
      }
      if(s.equals("-")) {
         return a - b;
      }
      if(s.equals("*")) {
         return a * b;
      }
      if(s.equals("/")) {
         return a / b;
      }
      else {
         return 0;
      }
   }
   
   private boolean isOperator(String s)
   {
      if(s.length() < 1) {
         return false;
      }
      else {
         if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            return true;
         }
         else {
            return false;
         }
      }
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null) {
         return "";
      }
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += inorderTraverse(t.getRight());
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null) {
         return "";
      }
      toReturn += root.getValue() + " ";
      toReturn += preorderTraverse(root.getLeft());
      toReturn += preorderTraverse(root.getRight());
      return toReturn;
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}