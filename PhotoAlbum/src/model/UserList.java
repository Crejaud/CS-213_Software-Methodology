
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class stores and provides functionality to maintain a general list of users for the application.
 * 
 * This is core of the serialization. The list of users is serialized into the users.dat file in the dat folder directory
 * 
 * @author      Corentin Rejaud
 * @author		Julia Sutula
 * @since       2016-03-31
 */

public class UserList implements Serializable {
	
	private static final long serialVersionUID = 9221355046218690511L;		
	public static final String storeDir = "dat";
	public static final String storeFile = "users.dat";
	
	private List<User> users;
	
	
	/**
	 * UserList contructor
	 */
	public UserList() {
		users = new ArrayList<User>();
	}
	  
	  
	  /** 
	   * getUserList
	   * Returns the overall list of Users for the application
	   * 
	   * @return the overall list of users for the application
	   */
	  public List<User> getUserList()
	  {
		  return users;
	  }
	  
	  /**
	   * addUserToList
	   * Adds indicated user to the overall list of users
	   * 
	   * @param u - the user to add to the overall list of users 	    
	   */  
	  public void addUserToList(User u)
	  {
		  users.add(u);
	  }
	  
	   /**removeUserFromList
	   * Remove indicated user from the overall list of users
	   * 
	   * @param u - the user to remove from the overall list of users 	    
	   */
	  public void removeUserFromList(User u)
	  {
		  users.remove(u);
	  }
	  
	  /**isUserInList
	   * Checks if a specific username and password combination is in the given list
	   * 
	   * @param un - the username to check for in the from the overall list of users 
	   * @param p - the password to check for in the from the overall list of users	
	   * @return boolean - return true if username and password combination is in the list, false if it isnt    
	   */
	  public boolean isUserInList(String un, String p)
	  {
		  for(User u : users)
		  {
			  if (u.getUsername().equals(un) && u.getPassword().equals(p))
				  return true;
		  }
		  return false;
	  }
	  
	  /**
	   * userExists
	   * Checks if a specific username already exists
	   * 
	   * @param un - the username to check for in the from the overall list of users 
	   * @return boolean - return true if username is in the list, false if it isnt    
	   */
	  public boolean userExists(String un)
	  {
		  for(User u : users)
		  {
			  if (u.getUsername().equals(un))
				  return true;
		  }
		  return false;
	  }
	  
	  /**
	   * getUserByUsername
	   * @param username	the user's username (unique)
	   * @return user with the same username
	   */
	  public User getUserByUsername(String username) {
		  for (User u : users)
		  {
			  if (u.getUsername().equals(username))
				  return u;
		  }
		  return null;
	  }
	  
	  /**
	   * toString
	   * Will display the usernames of all users
	   * 
	   * @return String - all usernames
	   */
	  public String toString() {
		  if (users == null)
			  return "no users";
		  String output = "";
		  for(User u : users)
		  {
			  output += u.getUsername() + " ";
		  }
		  return output;
	  }
	   
	  /**
	   * Read the users.dat file and return the UserList model containing the list of all users.
	   * @return	return the UserList model of all users
	   * @throws IOException		Exception for serialization
	   * @throws ClassNotFoundException		Exception for serialization
	   */
	   public static UserList read() throws IOException, ClassNotFoundException {
		   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
		   UserList ulist = (UserList) ois.readObject();
		   ois.close();
		   return ulist;
	   }
	   
	   /**
	    * Given the UserList model, write this data into users.dat, overwriting anything on there.
	    * @param ulist	The UserList model to write with
	    * @throws IOException		Exception for serialization
	    */
	   public static void write (UserList ulist) throws IOException {
		   ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
		   oos.writeObject(ulist);
		   oos.close();
	   }


}
