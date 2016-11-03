package model;

import java.io.Serializable;

/**
 * This class is a model for a tag.
 * 
 * A tag is assigned to a photo and has 2 attributes: type and value.
 * Type corresponds to the type of tag. Example: Location, Person, etc.
 * Value corresponds to the value of the type. Example: USA, Julia, etc.
 * 
 * This model implements serializable.
 * 
 * @author      Corentin Rejaud
 * @author		Julia Sutula
 * @since       2016-03-26
 */
public class Tag implements Serializable {

	private String type, value;
	
	/**
	 * Tag Constructor
	 * @param type		The tag type
	 * @param value		The tag value
	 */
	public Tag(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * getType
	 * @return type		The tag type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * getValue
	 * @return value	The tag value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * setType
	 * @param type		The tag type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * setValue
	 * @param value		The tag value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * toString
	 * @return string in form "type: value"
	 */
	public String toString() {
		return type + ": " + value;
	}
	
	/**
	 * hashCode function for when Tags are stored in a hash set
	 */
	@Override
	public int hashCode() {
		return value.hashCode()+type.hashCode();
	}
	
	/**
	 * Overridden equals method from search results
	 * @return true if the object is equal with the tag, else false
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null || !(obj instanceof Tag))
			   return false;

		Tag t =(Tag ) obj;

        return t.getValue().equals(value) && t.getType().equals(type);
	}
	
}
