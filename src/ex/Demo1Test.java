package ex;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;


public class Demo1Test {

	// 110. Is Binary Tree balanced
	boolean balanced = true;
	boolean isBinaryTreeBalanced(TreeNode root) {
		height(root);
		return balanced;
	}
	// height() 
	private int height(TreeNode root) {
		if (root == null) return 0;
		int leftHeight = height(root.left);
		int rightHeight = height(root.right);
		
		// is tree balanced?
		if (Math.abs(leftHeight - rightHeight) > 1) balanced = false;
		return 1 + Math.max(leftHeight, rightHeight);
	}
	
	
	// 104. Max depth of Binary Tree
	// use height()
	private int maxDepthOfBT(TreeNode root) {
		if (root == null) return 0;
		return 1 + Math.max(maxDepthOfBT(root.left), maxDepthOfBT(root.right));
	}
	
	
	// 94. BT InOrder, PreOrder, PostOrder traversal
	void inOrder(TreeNode root) {
		if (root == null) return;
		// PreOrder: System.out.println(root.data + ", ");
		inOrder(root.left);
		System.out.println(root.data + ", ");
		inOrder(root.right);
		// PostOrder:  System.out.println(root.data + ", ");
	}
	
	// InOrder Iterative
	
	// PreOrder Iterative
	
	// PostOrder Iterative
	
	
	// 102. BT Level Order Traversal (Top Down)
	
	
	// 107. Binary Tree Level Order Traversal II (Bottom Up)
	// Same as levelOrderTraversal, except result list (always insert to front of the list) 

	
	
	// 100. Same Binary Tree
	boolean isSameBinaryTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) return true;
		if (p == null || q == null) return false;
		
		if (p.data != q.data) return false;
		return isSameBinaryTree(p.left, q.left) && isSameBinaryTree(p.right, q.right);
	}
	
	// 101. Symmetric BT
	boolean isSymmetricBinaryTree(TreeNode root) {
		if (root == null) return true;
		return symmetricHelper(root.left, root.right);
	}
	boolean symmetricHelper(TreeNode left, TreeNode right) {
		if (left == null && right == null) return true;
		if (left == null || right == null) return false;
		if (left.data != right.data) return false;
		return symmetricHelper(left.right, right.left) &&
				symmetricHelper(left.left, right.right);
	}
	
	//98. Validate Binary Search Tree (BST)
	// Recursive

	
	// Insert data into BST(root)
	static Node insertNodeBST(Node root, int d) {
		if (root == null) return new Node(d);
		Node cur;
		if (d <= root.data) {
			cur = insertNodeBST(root.left, d);
			root.left = cur;
		} else {
			cur = insertNodeBST(root.right, d);
			root.right = cur;			
		}
		return root;
	}
	// Insert Data into BST (Simple by using built-in Insert)
	static Node insertNodeBST2(Node root, int d) {
		if (root == null) return new Node(d);
		root.insert(d);
		return root;
	}
	
	
	// LinkedList: find merge node (2 list intercepts)
	int findMergeNode(ListNode head1, ListNode head2) {
		// 1: 1 -> 2 -> 3 -> 7 -> 8
		// 2:      5 -> 6 -> 7 -> 8
		// 1: 1 -> 2 -> 3 -> 7 -> 8 -> 5 -> 6 -> 7 -> 8  (length 9)
		// 2: 5 -> 6 -> 7 -> 8 -> 1 -> 2 -> 3 -> 7 -> 8  (length 9)

		ListNode cur1 = head1;
		ListNode cur2 = head2;
		while (cur1 != cur2) {
			if (cur1 == null) 
				cur1 = head1;
			else 
				cur1 = cur1.next;
			
			if (cur2 == null) 
				cur2 = head2;
			else 
				cur2 = cur2.next;
		}
		// eventually cur1 = cur2
		return cur1.data;
	}
	
	
	
}


class TreeNode {
	int data;
	TreeNode left;
	TreeNode right;
	TreeNode (int d) {
		this.data = d;
	}
}

// SinglyLinkedList
class ListNode {
	int data;
	ListNode next;
	ListNode (int d) {
		this.data = d;
	}
}

// BST Node
class Node {
	int data;
	Node left, right;
	Node (int data) {
		this.data = data;
	}
	
	// BST Insert
	void insert(int d) {
		if (d <= this.data) {
			if (left == null) {
				left = new Node(d);
			} else {
				left.insert(d);
			}
		} else if (d > this.data) {
			if (right == null) {
				right = new Node(d);
			} else {
				right.insert(d);
			}
		}
	}
	
	// BST find
	Node find(Integer data) {
		if (this.data == data) {
			return this;
		}
		if (data < this.data && left != null) {
			return left.find(data);
		}
		if (right != null) {
			return right.find(data);
		}
		return null;
	}
	
	// BST traverse inOrdeer
	void traverseInOrder() {
		if (left != null) {
			left.traverseInOrder();
		}
		System.out.println(this.data + " ");
		if (right != null) {
			right.traverseInOrder();
		}
	}
	// BST traverse preOrdeer
	void traversePreOrder() {
		System.out.println(this.data + " ");
		if (left != null) {
			left.traversePreOrder();
		}
		if (right != null) {
			right.traversePreOrder();
		}
	}
	// BST traverse postOrdeer
	void traversePostOrder() {
		if (left != null) {
			left.traversePostOrder();
		}
		if (right != null) {
			right.traversePostOrder();
		}
		System.out.println(this.data + " ");
	}
	
	// BST isLeaf
	boolean isLeaf() {
		return left == null && right == null;
	}
	
	// BST height
	int height() {
		if (isLeaf()) return 1;
		int leftHeight = 0;
		int rightHeight = 0;
		if (left != null) leftHeight = left.height();
		if (right != null) rightHeight = right.height();
		return 1 + Math.max(leftHeight, rightHeight);
	}
	
	// BST Number of Leaf Nodes
	int numOfLeafNodes() {
		if (isLeaf()) return 1;
		int leftLeaves = 0;
		int rightLeaves = 0;
		if (left != null) leftLeaves = left.numOfLeafNodes();
		if (right != null) rightLeaves = right.numOfLeafNodes();
		return leftLeaves + rightLeaves;
	}
	
	// BST Largest() {
	Integer largest() {
		if (right == null) return this.data;
		return right.largest();
	}
	
	// BST Smallest() {
	Integer smallest () {
		if (left == null) return this.data;
		return left.smallest();
	}
	
	// BST add sorted  array into a tree to create a BST
	public static Node addSorted(int[] data, int start, int end) {
		if (end >= start) {
			int mid = (start + end)	/ 2;
			Node newNode = new Node(data[mid]); // Middle becomes the root of the BST tree
			newNode.left = addSorted(data, start, mid-1);
			newNode.right = addSorted(data, mid+1, end);
			return newNode;
		}
		return null;
	}

}

class BST {
	private Node root;
	
	// Insert
	public void insert(int d) {
		if (root == null)
			root = new Node(d);
		else
			root.insert(d);
	}
	
	
	// Traverse
	public void traverseInOrder() {
		if (root == null) return;
		root.traverseInOrder();
	}
	public void traversePreOrder() {
		if (root == null) return;
		root.traversePreOrder();
	}
	public void traversePostOrder() {
		if (root == null) return;
		root.traversePostOrder();
	}
	
	// Find
	public Node find(int d) {
		if (root == null) return null;
		return root.find(d);
	}
	
	// Smallest
	public Integer smallest() {
		if (root == null) return null;
		return root.smallest();
	}
	
	// Height
	public int height() {
		if (root == null) return 0;
		return root.height();
	}
	
	// number of leaf nodes
	int numOfLeafNodes() {
		if (root == null) return 0;
		return root.numOfLeafNodes();
	}
	
	// Create BST from sorted array
	public static BST createFromSortedArray(int[] data) {
		BST bst = new BST();
		if (data != null && data.length > 0) {
			bst.root = Node.addSorted(data, 0, data.length-1);
		}
		return bst;
	}
}