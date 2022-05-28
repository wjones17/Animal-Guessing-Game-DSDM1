package edu.westga.cs3151.animalGame.view;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import edu.westga.cs3151.animalGame.Main;
import edu.westga.cs3151.animalGame.viewModel.AnimalGameViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Code behind for the question gui
 * 
 * @author wtjra
 * @version Spring 2022
 */
public class QuestionCodeBehind {
	private AnimalGameViewModel viewModel;

	@FXML
	private AnchorPane mainPane;

	@FXML
	private Button noButton;

	@FXML
	private Button yesButton;

	@FXML
	private Label questionLabel;

	@FXML
	private MenuItem saveFileButton;

	@FXML
	private MenuItem loadFileButton;

	@FXML
	void intialize() {

	}

	/**
	 * sets this viewModel to the given viewModel
	 * 
	 * @precondition none
	 * @postcondition viewModel == viewModel
	 * @param viewModel the viewModel to set to
	 */
	public void initParams(AnimalGameViewModel viewModel) {
		this.viewModel = viewModel;
		this.questionLabel.setVisible(true);
		this.questionLabel.setText(this.viewModel.getTree().getCurrentNode().getData());
	}

	@FXML
	void handleNoButton(ActionEvent event) throws IOException {
		String out = "Is your animal a/an ";
		if (this.viewModel.getTree().getCurrentNode().getLeftChild() == null
				&& this.viewModel.getTree().getCurrentNode().getRightChild() == null) {
			this.goToYouWonPage();
		} else if (this.viewModel.getTree().getCurrentNode().getRightChild() != null) {
			this.viewModel.getTree().setCurrentToRightChild();
			if (this.viewModel.getTree().getCurrentNode().getLeftChild() == null
					&& this.viewModel.getTree().getCurrentNode().getRightChild() == null) {
				this.questionLabel.setText(out + this.viewModel.getTree().getCurrentNode().getData());
			} else {
				this.questionLabel.setText(this.viewModel.getTree().getCurrentNode().getData());
			}
		}

	}

	@FXML
	void handleYesbutton(ActionEvent event) throws IOException {
		String out = "Is your animal a/an ";
		if (this.viewModel.getTree().getCurrentNode().getLeftChild() == null
				&& this.viewModel.getTree().getCurrentNode().getRightChild() == null) {
			this.showPlayAgainDialog();

		} else if (this.viewModel.getTree().getCurrentNode().getLeftChild() != null) {
			this.viewModel.getTree().setCurrentToLeftChild();
			if (this.viewModel.getTree().getCurrentNode().getLeftChild() == null
					&& this.viewModel.getTree().getCurrentNode().getRightChild() == null) {
				this.questionLabel.setText(out + this.viewModel.getTree().getCurrentNode().getData());
			} else {
				this.questionLabel.setText(this.viewModel.getTree().getCurrentNode().getData());
			}

		}

	}

	private void goToYouWonPage() throws IOException {
		Stage stage = (Stage) this.noButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(Main.YOUWONPAGE));
		Parent parent = loader.load();

		YouWonCodeBehind ycb = loader.<YouWonCodeBehind>getController();
		ycb.initParams(this.viewModel);

		Scene scene = new Scene(parent);
		stage.setTitle(Main.WINDOW_TITLE);
		stage.setScene(scene);
		stage.show();
	}

	private void goBackToStartPage() throws IOException {
		Stage stage = (Stage) this.noButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(Main.STARTPAGE));
		Parent parent = loader.load();

		AnimalGameCodeBehind agcb = loader.<AnimalGameCodeBehind>getController();

		this.viewModel.getTree().setCurrentToBeginningParent();
		agcb.initParams(this.viewModel);
		Scene scene = new Scene(parent);
		stage.setTitle(Main.WINDOW_TITLE);
		stage.setScene(scene);
		stage.show();
	}

	private void showPlayAgainDialog() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Do you Want to Play Again?", ButtonType.OK, ButtonType.CANCEL);
		((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
		((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
		alert.setHeaderText("I WIN!!!!");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			this.goBackToStartPage();

		} else {
			this.saveFile(null);
			System.exit(0);
		}
	}

	@FXML
	void loadFile(ActionEvent event) {

	}

	@FXML
	void saveFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showSaveDialog(null);
		if (selectedFile != null) {
			this.viewModel.saveFile(selectedFile);
		}

	}
}
