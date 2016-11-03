package model;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * SerializableImage
 * Needed to serialize an image, since image is not inherently serializable.
 * Records width, height, and a 2D array of rgb int pixels.
 * Maps the Image to these values so that they can be serialized
 * 
 * This model implements Serializable.
 * 
 * @author Corentin Rejaud
 * @author Julia Sutula
 */
public class SerializableImage implements Serializable {

	private int width, height;
	private int[][] pixels;
	
	/**
	 * Default Constructor
	 */
	public SerializableImage() {}
	
	/**
	 * setImage
	 * Converts Image to 2d array of pixels
	 * @param image		Image to be converted
	 */
	public void setImage(Image image) {
		width = ((int) image.getWidth());
		height = ((int) image.getHeight());
		pixels = new int[width][height];
		
		PixelReader r = image.getPixelReader();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				pixels[i][j] = r.getArgb(i, j);
	}
	
	/**
	 * getImage
	 * Converts the 2d array of pixels to an Image
	 * @return Image object
	 */
	public Image getImage() {
		WritableImage image = new WritableImage(width, height);
		
		PixelWriter w = image.getPixelWriter();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				w.setArgb(i, j, pixels[i][j]);
		
		return image;
	}
	
	/**
	 * Width accessor
	 * @return	width int
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Height accessor
	 * @return	height int
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * 2D Pixels array accessor
	 * @return	pixels 2D int array
	 */
	public int[][] getPixels() {
		return pixels;
	}
	
	/**
	 * check if two images are equal.
	 * Two serializable images are equal if their attributes are the same
	 * @param si	The serializable image to be checked
	 * @return true if they're equal, else false
	 */
	public boolean equals(SerializableImage si) {
		if (width != si.getWidth())
			return false;
		if (height != si.getHeight())
			return false;
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				if (pixels[i][j] != si.getPixels()[i][j])
					return false;
		return true;
	}
	
}
