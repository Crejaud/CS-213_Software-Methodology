package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class is a model for a user.
 * 
 * Has a name, username, password, and list of albums
 * 
 * This model implements serializable
 * 
 * @author      Corentin Rejaud
 * @author		Julia Sutula
 * @since       2016-03-26
 */
public class User implements Serializable {

	private String name, username, password;
	private List<Album> albums;

	
	/**
	 * Users Contructor
	 * 
	 * @param name		The user's name
	 * @param username	The username
	 * @param password	The password
	 */
	public User(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
		albums = new ArrayList<Album>();
	}
	
	/**
	 * Name Accessor
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Username Accessor
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Password Accessor
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Changes the user's password to a new one
	 * @param password		the new password
	 */
	public void changePassword(String password) {
		this.password = password;
	}
	
	/**
	 * Albums accessor
	 * @return albums
	 */
	public List<Album> getAlbums() {
		return albums;
	}
	
	/**
	 * addAlbum
	 * Creates a new album with name albumName
	 * @param albumName - album name
	 */
	public void addAlbum(String albumName) {
		albums.add(new Album(albumName));
	}
	
	/**
	 * addAlbum
	 * Adds an existing album to albums
	 * @param a - the existing album
	 */
	public void addAlbum(Album a) {
		albums.add(a);
	}
	
	/**
	 * addPhotoToAlbum
	 * adds a photo object to an album at album index
	 * @param p - Photo object to be added
	 * @param albumIndex - the index of the album in albums list
	 */
	public void addPhotoToAlbum(Photo p, int albumIndex) {
		albums.get(albumIndex).addPhoto(p);
	}
	
	/**
	 * albumNameExists
	 * Checks to see if albumName exists already in albums.
	 * Necessary to check when adding new albums.
	 * Album names must be unique
	 * @param albumName		albumName to be checked before adding
	 * @return true if the album name already exists, else false
	 */
	public boolean albumNameExists(String albumName) {
		for (Album a: albums)
			if (a.getName().toLowerCase().equals(albumName.trim().toLowerCase()))
				return true;
		
		return false;
	}
	
	/**
	 * getAlbumIndexByAlbum
	 * @param a		The album being checked
	 * @return Given an album, find the index to which it resides in albums
	 */
	public int getAlbumIndexByAlbum(Album a) {
		for (int i = 0; i < albums.size(); i++)
			if (albums.get(i).getName().equals(a.getName()))
				return i;
		return -1;
	}
	
	
	/**
	 * getAlbumByName
	 * @param name		The album name
	 * @return Given an album name, return the album
	 */
	public Album getAlbumByName(String name) {
		for(Album a : albums)
		{
			if(a.getName().equals(name))
				return a;
		}
		return null;
	}
	
	/**
	 * removeAlbum
	 * delete indicated album from user's album list
	 * @param album		The album being removed
	 * 
	 */
	
	public void removeAlbum(Album album)
	{
		albums.remove(album);
	}
	
}
