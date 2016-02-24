package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.ListController;

/**
 * @author      Corentin Rejaud
 * @author		Julia Sutula
 * @version     1.0
 * @since       2016-02-10
 */
public class SongLib extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();   
	      loader.setLocation(
	         getClass().getResource("/view/SongList.fxml"));
	      BorderPane root = (BorderPane)loader.load();

	      ListController listController = 
	         loader.getController();
	      listController.start(primaryStage);

	      Scene scene = new Scene(root, 400, 300);
	      primaryStage.setTitle("Song Library - Julia Sutula and Corentin Rejaud");
	      primaryStage.setScene(scene);
	      primaryStage.setResizable(true);
	      primaryStage.show(); 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
