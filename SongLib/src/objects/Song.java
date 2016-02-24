package objects;

/**
 * @author      Corentin Rejaud
 * @author		Julia Sutula
 * @version     1.0
 * @since       2016-02-10
 */
public class Song {
	
	private String title;
	private String artist;
	private String album;
	private String year;
	
	/**
	 * Song Constructor
	 * 
	 * @param title			song title
	 * @param artist		song artist	
	 * @param album			song album
	 * @param year			song year
	 */
	public Song(String title, String artist, String album, String year)
	{
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
	
	}
	
	/**
	 * Song Title Accessor
	 * @return				song title
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 * Song Artist Accessor
	 * @return				song artist
	 */
	public String getArtist() {
		return this.artist;
	}
	/**
	 * Song Album Accessor
	 * @return				song album
	 */
	public String getAlbum() {
		return this.album;
	}
	/**
	 * Song Year Accessor
	 * @return				song year
	 */
	public String getYear() {
		return this.year;
	}
	
	/**
	 * Song Title Mutator
	 * 
	 * @param title			new song title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Song Artist Mutator
	 * 
	 * @param artist			new song artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	/**
	 * Song Album Mutator
	 * 
	 * @param album			new song album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	/**
	 * Song Year Mutator
	 * 
	 * @param year			new song year
	 */
	public void setYear(String year) {
		this.year = year;
	}
}


