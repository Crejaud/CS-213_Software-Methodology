package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;	
	 
/**
 * The controller for logging in.
 * This is the first controller assigned in the program.
 * Assigned to the login page (first scene)
 * @author Corentin Rejaud
 * @author Julia Sutula
 */
public class LoginController {
		
		
	@FXML private Button loginButton;
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Text actionTarget;
	    
	/**
	 * Handle the Login Button.
	 * Checks to see if the login credentials are correct, else displays red text to notify user.
	 * Checks to see whether the username and password belongs to an admin or non-admin user.
	 * @param event			Login button click
	 * @throws ClassNotFoundException		Exception for switching scenes
	 */
	@FXML protected void handleLoginButtonAction(ActionEvent event) throws ClassNotFoundException {
	    	
		String username = usernameField.getText();
		String password = passwordField.getText();
	    	
	    Parent parent;
	    	 
	    try {
			System.out.println(UserList.read());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    	
	    UserList ulist = null;
		try {
			ulist = UserList.read();
			System.out.println(ulist);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    	
		try {
					
			if(ulist.isUserInList(username, password)) {
				//if(UserList.isUserInList(username, password)){
				if(username.equals("admin") && password.equals("admin")){	 
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHome.fxml"));
					parent = (Parent) loader.load();
					        
					UserListController ctrl = loader.getController();
					Scene scene = new Scene(parent);
								
					Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();	
				                
					ctrl.start(app_stage);
				             
				    app_stage.setScene(scene);
				    app_stage.show();  
							
				}
				else{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NonAdminHome.fxml"));
					parent = (Parent) loader.load();
					// UserListController ctrl = loader.getController(); //change this to appropriate controller later
					AlbumListController ctrl = loader.<AlbumListController>getController();
					//send user index to album list controller
					ctrl.setUlist(ulist);
					ctrl.setUser(ulist.getUserByUsername(username));
					Scene scene = new Scene(parent);
					        
					Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();	
				                
					ctrl.start(app_stage);
				             
				    app_stage.setScene(scene);
				    app_stage.show();  
				}
					
			}
			else
				actionTarget.setText("Incorrect username and password combination. Please try again."); // change text in designate box to indicate incorrect password
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	         
	         
	}
	    
	   
}
