package edu.westga.cs3151.animalGame.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

/**
 * Creates a FileHandler class to load and save binary trees
 * 
 * @author wtjra
 * @version Spring 2022
 *
 */
public class FileHandler {

	/**
	 * Takes the given file and creats a binary tree from it
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param file the file to load a binary tree from
	 * @return the newly created binary tree
	 */
	public Node loadBinaryTree(File file) {
		Stack<Node> stk = new Stack<Node>();
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNext()) {
				String info = sc.nextLine();
				String delimeter = ":";
				String[] allinfo = info.split(delimeter);
				if (allinfo[0].equals("Answer")) {
					Node answer = this.extractedNode(allinfo);
					stk.push(answer);
				} else if (allinfo[0].equals("Question")) {
					Node question = this.extractedNode(allinfo);
					if (question.getRightChild() == null) {
						question.setRightChild(stk.pop());
					}
					if (question.getLeftChild() == null) {
						question.setLeftChild(stk.pop());
					}
					stk.push(question);
				}
			}

		} catch (FileNotFoundException ex) {
			System.err.println("Something bad happend in the loadBinaryTree method");
			System.err.println(ex.getMessage());
		}
		return stk.pop();
	}

	private Node extractedNode(String[] allinfo) {
		String info = "";
		for (int in = 1; in < allinfo.length; in++) {
			info += allinfo[in];
		}
		Node node = new Node(info);
		return node;
	}

	/**
	 * saves the given binary tree in post order
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param tree is the tree to save
	 * @param file is the file to save to
	 */
	public void saveBinaryTree(DecisionTree tree, File file) {

		this.writeFile(tree.getRoot(), file);
	}

	private void writeFile(Node mainNode, File file) {
		FileOutputStream outputStream = null;
		PrintWriter printWriter = null;

		try {

			outputStream = new FileOutputStream(file);
			printWriter = new PrintWriter(outputStream);

			this.write(mainNode, printWriter);

			printWriter.flush();

		} catch (IOException ex) {
			System.out.println("An error occured" + ex.getMessage());
		}

	}

	private void write(Node mainNode, PrintWriter wr) {
		if (mainNode != null) {
			this.write(mainNode.getLeftChild(), wr);
			this.write(mainNode.getRightChild(), wr);
			if (mainNode.getData().contains("?")) {
				wr.println("Question:" + mainNode.getData());
			} else {
				wr.println("Answer:" + mainNode.getData());
			}
		}
	}
}
