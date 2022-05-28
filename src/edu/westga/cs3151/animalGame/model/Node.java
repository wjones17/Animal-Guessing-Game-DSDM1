package edu.westga.cs3151.animalGame.model;

/**
 * The Class Node.
 * 
 * @author wtjra
 * @version Spring 2022
 */
public class Node {
	private String data;
	private Node parent;
	private Node rightChild;
	private Node leftChild;

	/**
	 * Creates a new node with given data string
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param data the data to set for this node
	 */
	public Node(String data) {
		this.setData(data);
		this.parent = null;
		this.rightChild = null;
		this.leftChild = null;
	}

	/**
	 * gets the data from the node and returns it
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the data for the node calling this method
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * sets the data for the node
	 * 
	 * @precondition data cannot be null or empty
	 * @postcondition none
	 * @param data is the data to set for the node
	 */
	public void setData(String data) {
		if (data == null) {
			throw new IllegalArgumentException("data cannot be null");
		}
		if (data.isEmpty()) {
			throw new IllegalArgumentException("data cannot be empty");
		}
		this.data = data;
	}

	/**
	 * gets the parent for the node and returns it
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the parent of the node calling this method
	 */
	public Node getParent() {
		return this.parent;
	}

	/**
	 * sets the parent for this node to the given node
	 * 
	 * @precondition parent cannot be null
	 * @postcondition getParent()==parent
	 * @param parent the parent to set this node's parent node to
	 */
	public void setParent(Node parent) {
		if (parent == null) {
			throw new IllegalArgumentException("parent cannot be null");
		}
		this.parent = parent;
	}

	/**
	 * Sets the left child
	 * 
	 * @precondition none
	 * @postcondition getLeftChild() == child
	 * @param child the new left child of this node
	 */
	public void setLeftChild(Node child) {
		child.setParent(this);
		this.leftChild = child;
	}

	/**
	 * Sets the right child
	 *
	 * @precondition none
	 * @postcondition getRightChild() == child
	 * @param child the new right child of this node
	 */
	public void setRightChild(Node child) {
		child.setParent(this);
		this.rightChild = child;
	}

	/**
	 * Returns the left child node
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return returns the left child of this node
	 */
	public Node getLeftChild() {
		return this.leftChild;
	}

	/**
	 * Returns the right child node
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return returns the right child of this node
	 */
	public Node getRightChild() {
		return this.rightChild;
	}

	/**
	 * Checks if this node has a left child
	 *
	 * @precondition none
	 * @postcondition none
	 * @return true if this node has a left child
	 */
	public boolean hasLeftChild() {
		return this.leftChild != null;
	}

	/**
	 * Checks if this node has a right child
	 *
	 * @precondition none
	 * @postcondition none
	 * @return true if this node has a right child
	 */
	public boolean hasRightChild() {
		return this.rightChild != null;
	}

}
