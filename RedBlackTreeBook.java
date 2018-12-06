

/**
 * This class represents a red black tree of member.
 * 
 * @author Eli
 *
 */
public class RedBlackTreeBook
{  
   RBNode root; 

   static final int black = 1;
   static final int red = 0;
   private static final int negativeRed = -1;
   private static final int doubleBlack = 2;
   static int maxOfBooks = 0; // this is for method maxBooks
   static int sumOfMax = 0;

   /**
      build an empty tree.
   */
   public RedBlackTreeBook()
   {  
      root = null;
   }
   
   /**
      Inserts a new RBNode into the tree.
      @param obj the object to insert
   */
   public void add(Book obj) 
   {  
      RBNode newRBNode = new RBNode();
      newRBNode.data = obj;
      newRBNode.left = null;
      newRBNode.right = null;
      if (root == null) { root = newRBNode; }
      else { root.addRBNode(newRBNode); }
      fixAfterAdd(newRBNode);
      if (obj.getMember().getNumOfBooks()+1>maxOfBooks)
      {
    	  maxOfBooks = obj.getMember().getNumOfBooks()+1;
    	  sumOfMax = 1;
      }

      
      else if (obj.getMember().getNumOfBooks()+1==maxOfBooks)
    	  sumOfMax++;

   }

   /**
      Tries to find an object in the tree.
      @param obj the object to find
      @return true if the object is contained in the tree
   */
   public Book find(String s)
   {
      RBNode current = root;
      while (current != null)
      {
    	 int x =  current.data.getCode().compareTo( s );
         if (x==0) return current.data;
         else if (x>0) current = current.left;
         else current = current.right;
      }
      return null;
   }
   
   /**
      Tries to remove an object from the tree. Does nothing
      if the object is not contained in the tree.
      @param obj the object to remove
   */
   public void remove(String s)
   {
      // Find RBNode to be removed

      RBNode toBeRemoved = root;
      boolean found = false;
      while (!found && toBeRemoved != null)
      {
    	  	 int x =  toBeRemoved.data.getCode().compareTo( s );
         if (x == 0) { found = true; }
         else 
         {
            if (x>0) { toBeRemoved = toBeRemoved.left; }
            else { toBeRemoved = toBeRemoved.right; }
         }
      }

      if (!found)
      { 
    	  return;
      }

      if (toBeRemoved.data.getMember().numOfBooks()==maxOfBooks)
      {
    	if (sumOfMax>1)
    		sumOfMax--;
    	else
    		maxOfBooks--;
      }
      toBeRemoved.data.getMember().removeBook(toBeRemoved.data.getCode());
      toBeRemoved.data = null;

      // If one of the children is empty, use the other
      if (toBeRemoved.left == null || toBeRemoved.right == null)
      {
         RBNode newChild;
         if (toBeRemoved.left == null) { newChild = toBeRemoved.right; }
         else { newChild = toBeRemoved.left; }

         fixBeforeRemove(toBeRemoved); 
         
         if (toBeRemoved.parent == null) { root = newChild; } // Found in the root
         else { toBeRemoved.replaceWith(newChild); }
         return;
      }
      
      // Neither subtree is empty

      // Find smallest element of the right subtree

      RBNode smallest = toBeRemoved.right;
      while (smallest.left != null)
      {
         smallest = smallest.left;
      }

      // smallest contains smallest child in right subtree
         
      // Move contents, unlink child

      toBeRemoved.data = smallest.data;
      fixBeforeRemove(smallest);
      smallest.replaceWith(smallest.right);
   }
   
   /**
      Visits all RBNodes of this tree in order.
      @param v the visitor to apply at each RBNode
   */
   public void inOrderVisit(Visitor v)
   {
      inOrderVisit(root, v);
   }
   
   public static interface Visitor
   {
      /**
         This method is called at each RBNode.
         @param n the visited RBNode
      */
      void visit(RBNode n);
   }
   
   private static void inOrderVisit(RBNode n, Visitor v)
   {
      if (n == null) return;
      inOrderVisit(n.left, v);
      v.visit(n);
      inOrderVisit(n.right, v);
   }
   

   
   /**
      A RBNode of a red-black tree stores a data item and references
      of the child RBNodes to the left and to the right. The color
      is the "cost" of passing the RBNode; 1 for black or 0 for red.
      Temporarily, it may be set to 2 or -1. 
   */
   static class RBNode
   {  
      public Book data;
      public RBNode left;
      public RBNode right;
      public RBNode parent;
      public int color;
      
      /**
         Constructs a red RBNode with no data.
      */
 //     public RBNode() {}
      
      /**
         Sets the left child and updates its parent pointer.
         @param child the new left child
      */
      public void setLeftChild(RBNode child)
      {
         left = child;
         if (child != null) { child.parent = this; }
      }
      
      /**
         Sets the right child and updates its parent pointer.
         @param child the new right child
      */
      public void setRightChild(RBNode child)
      {
         right = child;
         if (child != null) { child.parent = this; }
      }
      
      /**
       * Updates the parent's and replacement RBNode's links when this RBNode is replaced.
       * @param replacement the RBNode that replaces this RBNode
       */
      public void replaceWith(RBNode replacement)
      {
    	  if (parent == null) return;
    	  if (this == parent.left) parent.setLeftChild(replacement);
    	  else parent.setRightChild(replacement);
      }
      
      /**
         Inserts a new RBNode as a descendant of this RBNode.
         @param newRBNode the RBNode to insert
      */
      public void addRBNode(RBNode newRBNode)
      {  
    	 int x =  newRBNode.data.getCode().compareTo( this.data.getCode() );
         if (x<0)
         {  
            if (left == null) 
            {
               left = newRBNode;
               left.parent = this;
            }
            else { left.addRBNode(newRBNode); }
         }
         else if (x>0)
         {  
            if (right == null) 
            {
               right = newRBNode;
               right.parent = this;
            }
            else { right.addRBNode(newRBNode); }
         }
      }
   }

   /**
      Restores the tree to a red-black tree after a RBNode has been added.
      @param newRBNode the RBNode that has been added
   */
   private void fixAfterAdd(RBNode newRBNode)
   {
      if (newRBNode.parent == null) 
      { 
         newRBNode.color = black; 
      }
      else
      {
         newRBNode.color = red;
         if (newRBNode.parent.color == red) { fixDoubleRed(newRBNode); }
      }
   }

   /** 	
     Fixes the tree so that it is a red-black tree after a RBNode has been removed.
     @param removed the RBNode that is to be removed
   */
   private void fixBeforeRemove(RBNode removed)
   {
      if (removed.color == red) { return; }

      if (removed.left != null || removed.right != null) // It is not a leaf
      {
         // Color the child black
         if (removed.left == null) { removed.right.color = black; }
         else { removed.left.color = black; }
      }	   
      else { bubbleUp(removed.parent); }
   }
   
   /**
      Move a charge from two children of a parent
      @param parent a RBNode with two children, or null (in which case nothing is done)
   */
   private void bubbleUp(RBNode parent)
   {
      if (parent == null) { return; }
      parent.color++;
      parent.left.color--;
      parent.right.color--;
	   
      RBNode child = parent.left;
      if (child.color == negativeRed) { fixNegativeRed(child); return; }
      else if (child.color == red)
      {
         if (child.left != null && child.left.color == red) 
         { 
            fixDoubleRed(child.left); 
            return; 
         }
         if (child.right != null && child.right.color == red) 
         { 
            fixDoubleRed(child.right); return; 
         }
      }
   
      child = parent.right;
      if (child.color == negativeRed) { fixNegativeRed(child); return; }
      else if (child.color == red)
      {
         if (child.left != null && child.left.color == red) 
         { 
            fixDoubleRed(child.left); 
            return; 
         }
         if (child.right != null && child.right.color == red) 
         { 
            fixDoubleRed(child.right); 
            return; 
         }
      }
	  
      if (parent.color == doubleBlack) 
      { 
         if (parent.parent == null) { parent.color = black; }
         else { bubbleUp(parent.parent); }
      }
   }
   
   
   /**
      Fixes a "double red" violation.
      @param child the child with a red parent
   */
   private void fixDoubleRed(RBNode child)
   {
      RBNode parent = child.parent;      
      RBNode grandParent = parent.parent;
      if (grandParent == null) { parent.color = black; return; }
      RBNode n1, n2, n3, t1, t2, t3, t4;
      if (parent == grandParent.left)
      {
         n3 = grandParent; t4 = grandParent.right;
         if (child == parent.left)
         {
            n1 = child; n2 = parent;
            t1 = child.left; t2 = child.right; t3 = parent.right;
         }
         else
         {
            n1 = parent; n2 = child;
            t1 = parent.left; t2 = child.left; t3 = child.right; 
         }
      }
      else
      {
         n1 = grandParent; t1 = grandParent.left;
         if (child == parent.left)
         {
            n2 = child; n3 = parent;
            t2 = child.left; t3 = child.right; t4 = parent.right;
         }
         else
         {
            n2 = parent; n3 = child;
            t2 = parent.left; t3 = child.left; t4 = child.right; 
         }         
      }
      
      if (grandParent == root)
      {
         root = n2;
         n2.parent = null;
      }
      else
      {
         grandParent.replaceWith(n2);
      }
      
      n1.setLeftChild(t1);
      n1.setRightChild(t2);
      n2.setLeftChild(n1);
      n2.setRightChild(n3);
      n3.setLeftChild(t3);
      n3.setRightChild(t4);
      n2.color = grandParent.color - 1; 
      n1.color = black;
      n3.color = black;

      if (n2 == root)
      {
         root.color = black;
      }
      else if (n2.color == red && n2.parent.color == red)
      {
         fixDoubleRed(n2);
      }
   }
   
   /**
      Fixes a "negative red" violation.
      @param negRed the negative red RBNode
   */
   private void fixNegativeRed(RBNode negRed)
   {	
      RBNode n1, n2, n3, n4, t1, t2, t3, child;
      RBNode parent = negRed.parent;
      if (parent.left == negRed)
      {
         n1 = negRed.left;
         n2 = negRed;
         n3 = negRed.right;
         n4 = parent;
         t1 = n3.left;
         t2 = n3.right;
         t3 = n4.right;
         n1.color = red;
         n2.color = black;
         n4.color = black;
         n2.setRightChild(t1);
         Book temp = n4.data; n4.data = n3.data; n3.data = temp;
         n3.setLeftChild(t2);
         n3.setRightChild(t3);
         n4.setRightChild(n3);
         child = n1;
      }
      else
      {
         n4 = negRed.right;
         n3 = negRed;
         n2 = negRed.left;
         n1 = parent;
         t3 = n2.right;
         t2 = n2.left;
         t1 = n1.left;
         n4.color = red;
         n3.color = black;
         n1.color = black;
         n3.setLeftChild(t3);
         Book temp = n1.data; n1.data = n2.data; n2.data = temp;
         n2.setRightChild(t2);
         n2.setLeftChild(t1);
         n1.setLeftChild(n2);
         child = n4;
      }
	   
      if (child.left != null && child.left.color == red) 
      { 
         fixDoubleRed(child.left); 
         return; 
      }
      if (child.right != null && child.right.color == red) 
      { 
         fixDoubleRed(child.right);  
      }
   }

   
}
