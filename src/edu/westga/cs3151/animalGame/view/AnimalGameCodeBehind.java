package edu.westga.cs3151.animalGame.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.westga.cs3151.animalGame.Main;
import edu.westga.cs3151.animalGame.viewModel.AnimalGameViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Creates a CodeBehind/controller class for the gui
 * 
 * @author wtjra
 * @version Spring 2022
 *
 */
public class AnimalGameCodeBehind {
	private boolean tryingToLoad = false;
	private AnimalGameViewModel viewModel;

	@FXML
	private AnchorPane startMenuPane;

	@FXML
	private MenuItem saveMenuButton;

	@FXML
	private MenuItem loadMenuButton;

	@FXML
	private Button startButton;

	@FXML
	void initialize() {
		this.viewModel = new AnimalGameViewModel();
	}

	@FXML
	void goToQuestion(ActionEvent event) throws IOException {
		Stage stage = (Stage) this.startButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(Main.QUESTIONPAGE));
		Parent parent = loader.load();

		QuestionCodeBehind qcb = loader.<QuestionCodeBehind>getController();
		if (!this.tryingToLoad) {
			this.viewModel.getTree().loadDefaultTree();
			this.viewModel.getTree().setCurrentToBeginningParent();
		}
		qcb.initParams(this.viewModel);
		Scene scene = new Scene(parent);
		stage.setTitle(Main.WINDOW_TITLE);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void loadFile(ActionEvent event) throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			this.viewModel.loadFile(selectedFile);
			this.tryingToLoad = true;
		} else {
			this.tryingToLoad = false;
		}
	}

	@FXML
	void saveFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showSaveDialog(null);
		if (selectedFile != null) {
			this.viewModel.saveFile(selectedFile);
		}

	}

	/**
	 * Set the viewModel of this page to the given viewModel
	 * 
	 * @precondition none
	 * @postCondtion viewModel == viewModel
	 * 
	 * @param viewModel the view model to set to
	 */
	public void initParams(AnimalGameViewModel viewModel) {
		this.viewModel = viewModel;
		this.tryingToLoad = true;
	}

}
