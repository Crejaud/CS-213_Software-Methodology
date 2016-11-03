package controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Album;
import model.Photo;
import model.Tag;
import model.User;
import model.UserList;

/**
 * The controller for display photos
 * This controller implements Errorable and Logoutable
 * This controller is accessed by the AlbumListController and the PhotoSearchController
 * @author Corentin Rejaud
 * @author Julia Sutula
 */
public class PhotoDisplayController implements Errorable, Logoutable {

	public final static int CAME_FROM_ALBUM_CONTENT = 0,
			CAME_FROM_PHOTO_SEARCH = 1;
	
	@FXML
	ListView<Tag> tagListView;
	
	@FXML
	ImageView photoImageView;
	
	@FXML
	Text captionText, photoDateText;
	
	@FXML
	Button previousPhotoBtn, nextPhotoBtn;
	
	private ObservableList<Tag> obsList;
	private int photoIndex;
	private Album album;
	private User user;
	private List<Photo> photos;
	private UserList ulist;
	private int key;
	
	/**
	 * Updates all photo details and initializes everything for start up
	 * @param mainStage		The main stage
	 */
	public void start(Stage mainStage) {
		if (key == CAME_FROM_ALBUM_CONTENT)
			photos = album.getPhotos();
		
		updatePhotoDetails();

	}
	
	/**
	 * handleLogoutButton
	 * Logs out of the user on logout button click
	 * @param event		logout button click
	 * @throws ClassNotFoundException		Exception for switching scenes
	 */
	@FXML 
	protected void handleLogoutButton(ActionEvent event) throws ClassNotFoundException {
    	logout(event);          
	}
	
	/**
	 * Go back to the previous scene. This depends on the key assigned to this controller.
	 * If the key == CAME_FROM_ALBUM_CONTENT, then this means that it came from AlbumContent. Go back to this scene.
	 * If the key == CAME_FROM_PHOTO_SEARCH, then this means that it came from PhotoSearch. Go back to this scene.
	 * @param event		back button click
	 * @throws ClassNotFoundException		Exception for switching scenes
	 */
	@FXML
	protected void back(ActionEvent event) throws ClassNotFoundException {
		Parent parent;
	   	 
		try {
			if (key == CAME_FROM_ALBUM_CONTENT) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlbumContent.fxml"));
		        parent = (Parent) loader.load();
		        PhotoListController ctrl = loader.<PhotoListController>getController();
		        //send user index to album list controller
		        ctrl.setUser(user);
		        ctrl.setAlbum(album);
		        ctrl.setUlist(ulist);
		        Scene scene = new Scene(parent);
		        
		        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();	
		        
				ctrl.start(app_stage);
		         
				app_stage.setScene(scene);
				app_stage.show();
			}
			if (key == CAME_FROM_PHOTO_SEARCH) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PhotoSearch.fxml"));
		        parent = (Parent) loader.load();
		        PhotoSearchController ctrl = loader.<PhotoSearchController>getController();
		        //send user index to album list controller
		        ctrl.setUser(user);
		        ctrl.setUlist(ulist);
		        Scene scene = new Scene(parent);
		        
		        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();	
		        
				ctrl.start(app_stage);
		         
				app_stage.setScene(scene);
				app_stage.show();
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * addTag
	 * Add a tag to this displayed photo
	 * This opens up a dialog for creation of a tag
	 * @param event		add tag button  click
	 */
	@FXML
	protected void addTag(ActionEvent event) {
		
		Dialog<Album> dialog = new Dialog<>();
		dialog.setTitle("Add Tag");
		dialog.setHeaderText("Add a new Tag to this photo");
		dialog.setResizable(true);
		   
		Label keyLabel = new Label("Tag Key: ");
		TextField keyTextField = new TextField();
		Label valueLabel = new Label("Tag Value: ");
		TextField valueTextField = new TextField();
		
		GridPane grid = new GridPane();
		grid.add(keyLabel, 1, 1);
		grid.add(keyTextField, 2, 1);
		grid.add(valueLabel, 1, 2);
		grid.add(valueTextField, 2, 2);
		
		dialog.getDialogPane().setContent(grid);
		   
		ButtonType buttonTypeOk = new ButtonType("Add", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
	//wait for response from add button
		
		
		Optional<Album> result = dialog.showAndWait();
		
		String error = checkFields(keyTextField.getText(), valueTextField.getText());
					   
			
		   
			   
			// if user presses Add, add the user to the overall list
			if (result.isPresent()) {
				if (error != null) {
					showError(error);
						
				}
				else
				{	
				Photo p = photos.get(photoIndex);
				p.addTag(keyTextField.getText(),valueTextField.getText());			
				updatePhotoDetails();  
				try{
					  UserList.write(ulist);
				  }
				  catch(Exception i)
				  {
					  i.printStackTrace();
				  }
				}
			}
				 
		}		   
	
	/**
	 * checkFields	
	 * Check the fields, return null if no errors found
	 * @param type		The tag type being checked
	 * @param value		The tag value being checked
	 * @return An error string if errors were found, else null
	 */
	private String checkFields(String type, String value) {
		for(Tag t : photos.get(photoIndex).getTags())
		{
			if(t.getType().equals(type) && t.getValue().equals(value))
			return "This tag already exists for this photo";
		}
		if (type.equals("")|| value.equals(""))
			return "You must enter both a type and value";
		
		return null;
	}
	
	/**
	 * previousPhoto
	 * display the previous photo in the album or list of photos
	 * @param event		< button click
	 */
	@FXML
	protected void previousPhoto(ActionEvent event) {
		photoIndex--;
		updatePhotoDetails();
	}
	
	/**
	 * nextPhoto
	 * display the next photo in the album or list of photos
	 * @param event		> button click
	 */
	@FXML
	protected void nextPhoto(ActionEvent event) {
		photoIndex++;
		updatePhotoDetails();
	}
	
	/**
	 * updatePhotoDetails
	 * to be called when flipping through photos
	 * updates image, caption, and tags list
	 */
	public void updatePhotoDetails() {
		photoImageView.setImage(photos.get(photoIndex).getImage());
		captionText.setText("Caption: " + photos.get(photoIndex).getCaption());
		photoDateText.setText("Photo Date: " + photos.get(photoIndex).getDate());
		obsList = FXCollections.observableArrayList(photos.get(photoIndex).getTags());
		
		tagListView.setCellFactory(new Callback<ListView<Tag>, ListCell<Tag>>(){
			
			@Override
			public ListCell<Tag> call(ListView<Tag> t) {
				
				return new TagCell();
			}
		});	
		
		tagListView.setItems(obsList);
		
		// check to see if previousPhoto or nextPhoto buttons need to be disabled
		previousPhotoBtn.setDisable(photoIndex == 0);
		nextPhotoBtn.setDisable(photoIndex == photos.size()-1);
	}
	
	/**
	 * setAlbum
	 * Lets the program know who which album was picked
	 * @param a		The album being set
	 */
	public void setAlbum(Album a) {
		album = a;
	}
	
	/**
	 * setUser
	 * Lets the controller know who signed in
	 * @param u		The user being set
	 */
	public void setUser(User u) {
		user = u;
	}
	
	/**
	 * setPhotoIndex
	 * Lets the controller know which photo index it is on
	 * @param i		The photo index being set
	 */
	public void setPhotoIndex(int i) {
		photoIndex = i;
	}
	
	/**
	 * setUlist
	 * Lets the controller know the list of users
	 * Necessary for serialization
	 * @param ulist		The UserList model being set
	 */
	public void setUlist(UserList ulist) {
		this.ulist = ulist;
	}
	
	/**
	 * setPhotos
	 * Lets the controller know the list of photos to display
	 * @param photos	The list of photos being set
	 */
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	/**
	 * setKey
	 * Lets the controller know where this scene came from (to know which controller to switch back to)
	 * @param key		The key being set
	 */
	public void setKey(int key) {
		this.key = key;
	}
	
	/**
	 * The overridden ListCell to display the Tag data in the ListView cell
	 * 
	 * @author Corentin Rejaud
	 * @author Julia Sutula
	 */
	private class TagCell extends ListCell<Tag> {
			
		AnchorPane apane = new AnchorPane();
		
		Label tagLabel = new Label();
		Button deleteTagBtn = new Button("Delete");
		Button editTagBtn = new Button("Edit");
			
		public TagCell() {
			super();
			
			AnchorPane.setLeftAnchor(tagLabel, 0.0);
			AnchorPane.setTopAnchor(tagLabel, 0.0);
			
			AnchorPane.setRightAnchor(deleteTagBtn, 0.0);
			AnchorPane.setTopAnchor(deleteTagBtn, 0.0);
			
			AnchorPane.setRightAnchor(editTagBtn, 80.0);
			AnchorPane.setTopAnchor(editTagBtn, 0.0);
			
			tagLabel.setMaxWidth(200.0);
			
			deleteTagBtn.setVisible(false);
			editTagBtn.setVisible(false);
			
			apane.getChildren().addAll(tagLabel, deleteTagBtn, editTagBtn);
			
			setGraphic(apane);
		}
			
		@Override
		public void updateItem(Tag tag, boolean empty) {
			super.updateItem(tag, empty);
			setText(null);
			
			if (tag != null) {
				tagLabel.setText(tag.toString());
				deleteTagBtn.setVisible(true);
				editTagBtn.setVisible(true);
				
				deleteTagBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						deleteTag(e, tag);
					}
				});
				
				editTagBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						editTag(e, tag);
					}
				});
				
			}	
		}
	}
		
		/**
		 * deleteTag
		 * Deletes the Tag
		 * @param e		delete tag button click
		 * @param tag	the tag to be deleted
		 */
		public void deleteTag(ActionEvent e, Tag tag) {
			Alert alert = 
	 				   new Alert(AlertType.INFORMATION);
	 		   alert.setTitle("Delete Tag");
	 		   alert.setHeaderText(
	 				   "There's no going back!");

	 		   String content = "Are you sure you want to delete the tag " + tag.getType() + " : " + tag.getValue()+"?";

	 		   alert.setContentText(content);

	 		   Optional<ButtonType> result = alert.showAndWait();
	 		   
	 		  if(result.isPresent())
	 		  {
	 			 Photo p = photos.get(photoIndex);
	 			 for(int t = 0; t< p.getTags().size(); t++)
	 			 {
	 				 if(p.getTag(t).equals(tag))
	 				 {
	 					 p.removeTag(t);
	 					 updatePhotoDetails();
	 					try {
	 						
	 						UserList.write(ulist);
	 						
	 					} catch (Exception i) {
	 						// TODO Auto-generated catch block
	 						i.printStackTrace();
	 					}
	 				 }
	 			 }	 		 
				
				
				
	 		  }
		}
		
		/**
		 * editTag
		 * Edits the tag. Opens up an edit dialog.
		 * @param e		edit tag button click
		 * @param tag	the tag being editted
		 */
		public void editTag(ActionEvent e, Tag tag) {
			 Dialog<User> dialog = new Dialog<>();
			   dialog.setTitle("Edit Tag");
			   dialog.setHeaderText("Change the key or value of the tag");
			   dialog.setResizable(true);
			   
			   Label keyLabel = new Label("Tag Key: ");
				TextField keyTextField = new TextField();
				keyTextField.setText(tag.getType());
				Label valueLabel = new Label("Tag Value: ");
				TextField valueTextField = new TextField();
				valueTextField.setText(tag.getValue());
				
				GridPane grid = new GridPane();
				grid.add(keyLabel, 1, 1);
				grid.add(keyTextField, 2, 1);
				grid.add(valueLabel, 1, 2);
				grid.add(valueTextField, 2, 2);
				
				dialog.getDialogPane().setContent(grid);
				   
				ButtonType buttonTypeOk = new ButtonType("Ok", ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
			//wait for response from edit button
				
				
				Optional<User> result = dialog.showAndWait();
				
				String error = checkFields(keyTextField.getText(), valueTextField.getText());
							   
		
					if (result.isPresent()) {
						if (error != null) {
							showError(error);
						
						}
						else
						{
							Photo p = photos.get(photoIndex);
							for(int t = 0; t< p.getTags().size(); t++)
				 			 {
				 				 if(p.getTag(t).equals(tag))
				 				 {
				 					 p.editTag(t, keyTextField.getText(), valueTextField.getText());
				 					 updatePhotoDetails();
									  try{
										  UserList.write(ulist);
									  }
									  catch(Exception i)
									  {
										  i.printStackTrace();
									  }
					
					   
				 				 	}
						   
				 			 }
						}
					}
					
				
	
		}
	
}
