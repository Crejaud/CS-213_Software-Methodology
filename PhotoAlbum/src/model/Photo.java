package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;

/**
 * This class is a model for a photo.
 * 
 * Has a serializable image, caption, list of tags, and a date (cal).
 * 
 * Implements the serializable interface.
 * 
 * @author      Corentin Rejaud
 * @author		Julia Sutula
 * @since       2016-03-26
 */
public class Photo implements Serializable {

	//private Image image;
	private SerializableImage image;
	private String caption;
	private List<Tag> tags;
	private Calendar cal;
	
	/**
	 * Default Photo Constructor
	 */
	public Photo() {
		caption = "";
		tags = new ArrayList<Tag>();
		cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		image = new SerializableImage();
	}
	
	/**
	 * Photo contructor given an image
	 * @param i		The image
	 */
	public Photo(Image i) {
		this();
		image.setImage(i);
	}
	
	
	
	/**
	 * addTag
	 * @param type		The tag type
	 * @param value		The tag value
	 */
	public void addTag(String type, String value) {
		tags.add(new Tag(type, value));
	}
	
	/**
	 * editTag
	 * Changed the values of tag type and tag value of the tag at a specified index in the tags list
	 * @param index		the tag index in the tags list
	 * @param type		the tag type
	 * @param value		the tag value
	 */
	public void editTag(int index, String type, String value) {
		tags.get(index).setType(type);
		tags.get(index).setValue(value);
	}
	
	/**
	 * removeTag
	 * @param index		The index of the tag to be removed from the tags list
	 */
	public void removeTag(int index) {
		tags.remove(index);
	}
	
	/**
	 * getTag
	 * @param index		The index of the tag in the tags list
	 * @return Tag object at index
	 */
	public Tag getTag(int index) {
		return tags.get(index);
	}
	
	/**
	 * setCaption
	 * @param caption	Tag caption
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	/**
	 * getCaption
	 * @return caption		The caption string of the photo
	 */
	public String getCaption() {
		return caption;
	}
	
	/**
	 * Get the calendar object assigned to the photo.
	 * @return cal			The date assigned to the photo
	 */
	public Calendar getCalendar() {
		return cal;
	}
	
	/**
	 * getDate
	 * @return string 		form of the date using Calendar
	 */
	public String getDate() {
		String[] str = cal.getTime().toString().split("\\s+");
		return str[0] + " " + str[1] + " " + str[2] + ", " + str[5];
	}
	
	/**
	 * Image Accessor
	 * @return image		photo image
	 */
	public Image getImage() {
		return image.getImage();
	}
	
	/**
	 * SerializableImage Accessor
	 * @return The serializable image
	 */
	public SerializableImage getSerializableImage() {
		return image;
	}
	
	
	/**
	 * hasSubset
	 * Uses hashset of tags. Checks if parameter is a subset of tags
	 * @param tlist		The list to be compared with
	 * @return true if tlist is a subset of tags, else false
	 */
	public boolean hasSubset(List<Tag> tlist) {
		Set<Tag> allTags = new HashSet<Tag>();
		allTags.addAll(tags);
		
		for (Tag t: tlist) {
			if (!allTags.contains(t))
				return false;
		}
		
		return true;
	}
	
	/**
	 * isWithinDateRange
	 * @param fromDate	The from Local Date 
	 * @param toDate	The to Local Date
	 * @return true if photo date is within range, else false
	 */
	public boolean isWithinDateRange(LocalDate fromDate, LocalDate toDate) {
		LocalDate date = cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		return date.isBefore(toDate) && date.isAfter(fromDate) || date.equals(fromDate) || date.equals(toDate);
	}
	
	/**
	 * getTags
	 * @return list of all tags in a photo
	 */
	public List<Tag> getTags() {
		return tags;
	}
	
}
