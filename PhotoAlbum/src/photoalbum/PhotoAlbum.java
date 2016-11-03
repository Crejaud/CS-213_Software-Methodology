package photoalbum;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.User;
import model.UserList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.LoginController;

/**
 * This is the main class for the PhotoAlbum
 * This class reads the list of users from the users.dat file and starts the LoginController.
 * @author Corentin Rejaud
 * @author Julia Sutula
 */
public class PhotoAlbum extends Application {
	
	Stage mainStage;
	
	/**
	 * Sets everything up for app startup
	 * @throws IOException					Exception for switching scenes
	 * @throws ClassNotFoundException		Exception for switching scenes
	 */
	@Override
	public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
		
		
		//UserList.initializeUserList("src/usersInfo.txt");
//		//Serialization code
//		Write users to userlist
//		UserList ulist = new UserList();
//		User tempUser = new User("Corey", "crejaud", "Testing1");
//		tempUser.addAlbum("Test Album");
//		
//		ulist.addUserToList(tempUser);
//		ulist.addUserToList(new User("Julia", "jsutula", "Testing1"));
//		ulist.addUserToList(new User("Dummy", "dummy", "Testing1"));
//		ulist.addUserToList(new User("Admin", "admin", "admin"));
//		UserList.write(ulist);
		//Read users from userlist
		UserList ulist2 = UserList.read();
		System.out.println(ulist2);
		
		mainStage = primaryStage;
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Login.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root);
			
			
			mainStage.setScene(scene);
			mainStage.setTitle("Photo Album");
			mainStage.setResizable(false);
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
