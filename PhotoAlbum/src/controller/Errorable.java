package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Errorable Interface
 * Interface for showing errors in an alert
 * This method is used many times in nearly all of the scenes
 * @author Corentin Rejaud
 * @author Julia Sutula
 */
public interface Errorable {
	
	/**
	 * showError shows an error alert dialog when something goes wrong
	 * @param error		The error string to be displayed in the alert
	 */
	default void showError(String error) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText("Error");
		String content = error;
		alert.setContentText(content);
		alert.showAndWait();
	}

}
