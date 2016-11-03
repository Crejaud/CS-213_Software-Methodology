package controller;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class is a controller for the Admin Home Screen.
 * 
 * It controls how the user list is displayed along with adding
 * and deleting users.
 * 
 * @author      Corentin Rejaud
 * @author		Julia Sutula
 * @since       2016-03-26
 */
public class UserListController implements Errorable, Logoutable {

	@FXML
	TableView<User> table;
	
	@FXML
	TableColumn<User,String> usernameColumn;
	
	@FXML
	TableColumn<User,String> passwordColumn;
	
	@FXML
	TableColumn<User,String> nameColumn;
	
	@FXML
	TableColumn<User,User> deleteColumn;
	
	private ObservableList<User> obsList;
	private List<User> users = new ArrayList<User>();
	private UserList ulist;
	
	/**
	 * Loads all users and initializes everything for start up
	 * @param mainStage		The main stage
	 * @throws IOException 	Exception for reading the list of users
	 * @throws ClassNotFoundException 	Exception for reading the list of users
	 */
	public void start(Stage mainStage) throws ClassNotFoundException, IOException {
		
		//users = UserList.getUserList();
		// Serialization
		ulist = UserList.read();
		users = ulist.getUserList();
		
		obsList = FXCollections.observableArrayList(users);
				
		usernameColumn.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<User, String> u) {
		         return new SimpleStringProperty(u.getValue().getUsername());
		     }
		  });
		 
	
		 nameColumn.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<User, String> u) {
		    	 return new SimpleStringProperty(u.getValue().getName());
		     }
		  });
		 
		 passwordColumn.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<User, String> u) {
		    	 return new SimpleStringProperty(u.getValue().getPassword());
		     }
		  });
		 
		 deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		  deleteColumn.setCellFactory(param -> new TableCell<User,User>() {
	            private final Button deleteButton = new Button("x");

	            @Override
	            protected void updateItem(User user, boolean empty) {
	                super.updateItem(user, empty);

	                if (user == null) {
	                    setGraphic(null);
	                    return;
	                }

	                setGraphic(deleteButton);
	                deleteButton.setOnAction(event -> {
									                	  Alert alert = 
									           				   new Alert(AlertType.INFORMATION);
									           		   alert.setTitle("Delete User");
									           		   alert.setHeaderText(
									           				   "There's no going back!");
								
									           		   String content = "Are you sure you want to delete " + user.getUsername() + "?";
								
									           		   alert.setContentText(content);
								
									           		   Optional<ButtonType> result = alert.showAndWait();
									           		   
	                								if(!user.getName().equals("admin") && result.isPresent())
	                								{
	                									obsList.remove(user);
	                									users.remove(user);
	                									//ulist.removeUserFromList(user);
	                									try {
															UserList.write(ulist);
														} catch (Exception e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
	                								}
	                								else if(user.getName().equals("admin"))
	                									   showError("You cannot delete the admin!");});
	            }
	        });
	
		
		table.setItems(obsList);		

		
	      // select the first item
	      if (!obsList.isEmpty())
	    	  table.getSelectionModel().select(0);
	      



	}

	/**
	 * handleLogoutButton
	 * logs out of the admin on logout button click
	 * 
	 * @param event		The button press event
	 * @throws ClassNotFoundException		Exception for switching scenes
	 */
	@FXML 
	protected void handleLogoutButton(ActionEvent event) throws ClassNotFoundException {
	    logout(event);     
	}
	 
	  /**
	    * handleAddButton
	    * Add Button: OnClick
	    * Lets admin add a new user
	    */
	   @FXML
	   private void handleAddButton(ActionEvent event) throws IOException {
		  int index = table.getSelectionModel().getSelectedIndex();
		   Dialog<User> dialog = new Dialog<>();
		   dialog.setTitle("Create a New User");
		   dialog.setHeaderText("Add a new user to PhotoBox!");
		   dialog.setResizable(true);
		   
		   Label usernameLabel = new Label("Username: ");
		   Label passwordLabel = new Label("Password: ");
		   Label nameLabel = new Label("User's Full Name: ");
		   TextField usernameTextField = new TextField();
		   TextField passwordTextField = new TextField();
		   TextField nameTextField = new TextField();
		   
		   GridPane grid = new GridPane();
		   grid.add(usernameLabel, 1, 1);
		   grid.add(usernameTextField, 2, 1);
		   grid.add(passwordLabel, 1, 2);
		   grid.add(passwordTextField, 2, 2);
		   grid.add(nameLabel, 1, 3);
		   grid.add(nameTextField, 2, 3);
		   
		   dialog.getDialogPane().setContent(grid);
		   
		   ButtonType buttonTypeOk = new ButtonType("Add", ButtonData.OK_DONE);
		   dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		   
		   dialog.setResultConverter(new Callback<ButtonType, User>() {
			   @Override
			   public User call(ButtonType b) {
				   if (b == buttonTypeOk) {
					   
					   String error = checkFields(usernameTextField.getText(),passwordTextField.getText(),nameTextField.getText());
					   
					   if (error != null) {
						   showError(error);
						   return null;
					   }
											   
					   return new User(nameTextField.getText().trim(),usernameTextField.getText().trim(),
							   passwordTextField.getText().trim());
				   }
				   return null;
			   }

			
		   });
		   
		   //wait for response from add button
		   Optional<User> result = dialog.showAndWait();
		   
		   // if user presses Add, add the user to the overall list
		   if (result.isPresent()) {
			   User tempUser = (User) result.get(); //store result
			   //ulist.addUserToList(tempUser);
			   obsList.add(tempUser);
			   users.add(tempUser);
			   UserList.write(ulist);
			   
			 
			   
			   //sort?
			   
			   //if this is first user added, then select it
			   if (obsList.size() == 1) {
				   table.getSelectionModel().select(0);
			   }
			   else
			   {
				   index = 0;
				   for(User s: ulist.getUserList())
				   {
					   
					   if(s == tempUser)
					   {
						  table.getSelectionModel().select(index);
						  break;
					   }
					   
					   index++;
				   }
			   }
				   
		   }
	   }
	   
	   /**
	    * 
	    * Check the fields, return null if no errors found
	    * @return the error message in string format, null if no errors
	    */
	   private String checkFields(String username, String password, String name) {
		   if (username.trim().isEmpty())
			   return "Username is a required field.";
		   else if (password.trim().isEmpty())
			   return "Password is a required field.";
		   else if (name.trim().isEmpty())
			   return "Full Name is a required field.";
		   if (ulist.userExists(username))
			   return "This username is already taken, please try another username.";
		   else	   
			   return null;
	   }

}
