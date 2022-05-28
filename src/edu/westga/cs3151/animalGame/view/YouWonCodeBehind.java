package edu.westga.cs3151.animalGame.view;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import edu.westga.cs3151.animalGame.Main;
import edu.westga.cs3151.animalGame.model.Node;
import edu.westga.cs3151.animalGame.viewModel.AnimalGameViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The Class YouWonCodeBehind.
 * 
 * @author wtjra
 * @version Spring 2022
 */
public class YouWonCodeBehind {

	private AnimalGameViewModel viewModel;

	@FXML
	private AnchorPane mainPane;

	@FXML
	private TextField answerTextField;

	@FXML
	private TextField questionTextField;

	@FXML
	private MenuItem saveFileButton;

	@FXML
	private MenuItem loadFileButton;

	@FXML
	private RadioButton yesRadioButton;

	@FXML
	private ToggleGroup yesOrNoSelection;

	@FXML
	private RadioButton noRadioButton;

	@FXML
	private Button submitButton;

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

	@FXML
	void submitAnimal(ActionEvent event) throws IOException {

		boolean setToYesLeft = false;
		Node question = new Node(this.questionTextField.getText());
		Node answer = new Node(this.answerTextField.getText());
		if (this.yesOrNoSelection.getSelectedToggle().equals(this.yesRadioButton)) {
			setToYesLeft = true;
		} else {
			setToYesLeft = false;
		}
		if (setToYesLeft) {
			question.setLeftChild(answer);
		} else {
			question.setRightChild(answer);
		}
		this.viewModel.getTree().insertNode(question);
		this.showPlayAgainDialog();

	}
	
	private void showPlayAgainDialog() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Do you Want to Play Again?", ButtonType.OK, ButtonType.CANCEL);
		((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
		((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
		alert.setHeaderText("Play again?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			this.goBackToStartPage();

		} else {
			this.saveFile(null);
			System.exit(0);
		}
	}
	
	private void goBackToStartPage() throws IOException {
		Stage stage = (Stage) this.submitButton.getScene().getWindow();
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

	/**
	 * Inits the params.
	 *
	 * @param viewModel the view model
	 */
	public void initParams(AnimalGameViewModel viewModel) {
		this.viewModel = viewModel;
	}

}
