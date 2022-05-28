package edu.westga.cs3151.animalGame.viewModel;

import java.io.File;
import java.io.FileNotFoundException;
import edu.westga.cs3151.animalGame.model.DecisionTree;
import edu.westga.cs3151.animalGame.model.*;

/**
 * The Class AnimalGameViewModel.
 * 
 * @author wtjra
 * @version Spring 2022
 */
public class AnimalGameViewModel {
	private DecisionTree tree = new DecisionTree();

	/**
	 * Reads the given file and stores it in a binary tree
	 * 
	 * @precondition file != null
	 * @postcondition none
	 * @param file the file to read
	 * @throws FileNotFoundException
	 */
	public void loadFile(File file) throws FileNotFoundException {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		FileHandler fileIo = new FileHandler();
		this.setTree(fileIo.loadBinaryTree(file));
	}

	/**
	 * Saves the current binary tree to the given file
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param file the file to save the binary tree to
	 */
	public void saveFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		FileHandler fileIo = new FileHandler();
		fileIo.saveBinaryTree(this.tree, file);
	}

	/**
	 * gets the decision tree and returns it
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the decision tree
	 */
	public DecisionTree getTree() {
		return this.tree;
	}

	/**
	 * sets the decisionTree root node to the given node
	 * 
	 * @precondition newRoot cannot be null
	 * @postcondition getTree() == dTree
	 * @param newRoot is the new root node for the new tree
	 */
	public void setTree(Node newRoot) {
		if (newRoot == null) {
			throw new IllegalArgumentException("newRoot cannot be null");
		}
		this.tree.setRoot(newRoot);
		this.tree.setCurrentToBeginningParent();
	}

}
