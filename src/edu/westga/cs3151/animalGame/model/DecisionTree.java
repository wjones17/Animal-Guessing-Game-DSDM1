package edu.westga.cs3151.animalGame.model;

/**
 * Creates a binary tree class
 * 
 * @author wtjra
 * @version Spring 2022
 */
public class DecisionTree {
	private Node root;
	private Node currentNode;

	/**
	 * Creates a binary tree and initializes the data members
	 * 
	 * @precondition none
	 * @postcondition root == null && currentNode == root
	 */
	public DecisionTree() {
		this.root = null;
		this.currentNode = this.root;
	}

	/**
	 * loads the default tree if a tree isn't loaded in already
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void loadDefaultTree() {
		this.root = new Node("Is it a mammal?");
		this.root.setLeftChild(new Node("whale"));
		this.root.setRightChild(new Node("goldfish"));
	}

	/**
	 * gets the root node for the tree and returns it
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the root node of the tree
	 */
	public Node getRoot() {
		return this.root;
	}

	/**
	 * Sets the root node of the tree
	 * 
	 * @precondition root cannot be null
	 * @postcondition getRoot() == root
	 * 
	 * @param root is the node to set to the root of the tree
	 */
	public void setRoot(Node root) {
		if (root == null) {
			throw new IllegalArgumentException("root cannot be null");
		}
		this.root = root;
	}

	/**
	 * Gets the current node and returns it
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the current node
	 */
	public Node getCurrentNode() {
		return this.currentNode;
	}

	/**
	 * Sets the current node.
	 * 
	 * @precondition currentNode cannot be null
	 * @postcondition getCurrentNode() == currentNode
	 * @param currentNode the new current node
	 */
	public void setCurrentNode(Node currentNode) {
		if (currentNode == null) {
			throw new IllegalArgumentException("currentNode cannot be null");
		}
		this.currentNode = currentNode;
	}

	/**
	 * gets the current nodes parent and sets the parent to the current
	 * 
	 * @precondition none
	 * @postcondition currentNode() == currentNode.getParent()
	 * @return true if current has been changed to its parent, false otherwise
	 */
	public boolean setCurrentToParent() {
		boolean set = false;
		if (this.currentNode == null) {
			set = false;
		} else {
			Node parent = this.currentNode.getParent();
			if (parent != null) {
				this.setCurrentNode(parent);
				set = true;
			}
		}
		return set;
	}

	/**
	 * gets the current nodes left child and sets it to the current
	 * 
	 * @precondition none
	 * @postcondition currentNode() == currentNode.getLeftChild()
	 * @return true if current has been changed to its left child, false otherwise
	 */
	public boolean setCurrentToLeftChild() {
		boolean set = false;
		if (this.currentNode == null) {
			set = false;
		} else if (this.currentNode.getLeftChild() == null && this.currentNode.getRightChild() == null) {
			set = false;
		} else {
			Node question = this.currentNode;
			this.currentNode = question.getLeftChild();
			set = true;
		}

		return set;
	}

	/**
	 * gets the current nodes Right child and sets it to the current
	 * 
	 * @precondition none
	 * @postcondition currentNode() == currentNode.getRightChild()
	 * @return true if current has been changed to its right child, false otherwise
	 */
	public boolean setCurrentToRightChild() {
		boolean set = false;
		if (this.currentNode == null) {
			set = false;
		} else {
			Node question = this.currentNode;
			this.currentNode = question.getRightChild();
			set = true;
		}

		return set;
	}

	/**
	 * gets the current nodes Right child and sets it to the current
	 * 
	 * @precondition none
	 * @postcondition currentNode() == currentNode.getRightChild()
	 */
	public void setCurrentToBeginningParent() {
		this.currentNode = this.root;

	}

	/**
	 * Insert node into the tree
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param node is the node to insert
	 */
	public void insertNode(Node node) {
		if (this.currentNode == null) {
			this.setCurrentNode(node);
			return;
		}
		Node currentParent = this.currentNode.getParent();
		if (this.currentNode.getLeftChild() == null && this.currentNode.getRightChild() == null) {
			if (node.getRightChild() == null) {
				node.setRightChild(this.currentNode);
			} else if (node.getLeftChild() == null) {
				node.setLeftChild(this.currentNode);
			}
		}

		if (currentParent != null) {
			if (this.currentNode == currentParent.getLeftChild()) {
				currentParent.setLeftChild(node);
			} else if (this.currentNode == currentParent.getRightChild()) {
				currentParent.setRightChild(node);
			}
		}

	}
}
