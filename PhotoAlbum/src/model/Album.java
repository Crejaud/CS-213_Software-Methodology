package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/**
 * This class is a model for an album.
 * 
 * Has a name, list of photos, oldest photo, and earliest photo.
 * 
 * This model implements the serializable interface.
 * 
 * @author      Corentin Rejaud
 * @author		Julia Sutula
 * @since       2016-03-26
 */
public class Album implements Serializable {

	private String name;
	private List<Photo> photos;
	private Photo oldestPhoto;
	private Photo earliestPhoto;
	
	/**
	 * Album Constructor
	 * @param name		Album name
	 */
	public Album(String name) {
		this.name = name;
		oldestPhoto = null;
		earliestPhoto = null;
		photos = new ArrayList<Photo>();
	}
	
	/**
	 * setName
	 * @param name		Album name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getName
	 * @return album name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * addPhoto
	 * @param photo		Photo to be added to album
	 */
	public void addPhoto(Photo photo) {
		photos.add(photo);
		findOldestPhoto();
		findEarliestPhoto();
	}
	
	/**
	 * removePhoto
	 * @param index		Index of photo to be removed
	 */
	public void removePhoto(int index) {
		photos.remove(index);
		findOldestPhoto();
		findEarliestPhoto();
	}
	
	/**
	 * getPhoto
	 * @param index		index at which the photo belongs in the list of photos
	 * @return photo at index in photos list
	 */
	public Photo getPhoto(int index) {
		return photos.get(index);
	}
	
	/**
	 * getCount
	 * @return count of photos in album
	 */
	public int getCount() {
		return photos.size();
	}
	
	/**
	 * findOldestPhoto
	 * sets oldestPhoto to be the oldest photo in photos
	 */
	public void findOldestPhoto() {
		if (photos.size() == 0)
			return;
		
		Photo temp = photos.get(0);
		
		for (Photo p : photos)
			if (p.getCalendar().compareTo(temp.getCalendar()) < 0)
				temp = p;
		
		oldestPhoto = temp;
	}
	
	/**
	 * findEarliestPhoto
	 * sets earliestPhoto to be the earliest photo in photos
	 */
	public void findEarliestPhoto() {
		if (photos.size() == 0)
			return;
		
		Photo temp = photos.get(0);
		
		for (Photo p : photos)
			if (p.getCalendar().compareTo(temp.getCalendar()) > 0)
				temp = p;
		
		earliestPhoto = temp;
	}
	
	/**
	 * getOldestPhotoDate
	 * @return oldest photo date in string form
	 */
	public String getOldestPhotoDate() {
		if (oldestPhoto == null)
			return "NA";
		return oldestPhoto.getDate();
	}
	
	/**
	 * getEarliestPhotoDate
	 * @return earliest photo date in string form
	 */
	public String getEarliestPhotoDate() {
		if (earliestPhoto == null)
			return "NA";
		return earliestPhoto.getDate();
	}
	
	/**
	 * getDateRange
	 * @return date of oldest to earliest photo date
	 */
	public String getDateRange() {
		return getOldestPhotoDate() + " - " + getEarliestPhotoDate();
	}
	
	/**
	 * getAlbumPhoto
	 * @return image of first photo in the list
	 */
	public Image getAlbumPhoto() {
		if (photos.isEmpty())
			return null;
		return photos.get(0).getImage();
	}
	
	/**
	 * getPhotos
	 * @return the list of all photos in the album
	 */
	public List<Photo> getPhotos() {
		return photos;
	}
	
	/**
	 * findIndexByPhoto
	 * Given a photo, find the index to which it belongs in an album
	 * @param photo		The photo the index of the photo
	 * @return the index at which the photo belongs in the photos list
	 */
	public int findIndexByPhoto(Photo photo) {
		for (int i = 0; i < photos.size(); i++)
			if (photos.get(i).equals(photo))
				return i;
		return -1;
	}
	
}
