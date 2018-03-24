package crop;

import java.awt.image.BufferedImage;  // cropping
import java.awt.Color;                // dealing with color of our image
import javax.imageio.ImageIO;         // reading in image
import java.io.File;                  // files
import java.io.IOException;           // exceptions

/**
 * Handles cropping of BufferedImages
 */ 
public class Cropper {
  private int x, y, width, height;      // dimensions of crop
  private BufferedImage img;            // image we wish to crop
  private final String output = "out";  // output directory

  public Cropper(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;

    // create output directory if not already there
    new File(output).mkdir();
  }

  /**
   * Read in the file as a BufferedImage
   */ 
  private void loadImage(File file) {
    try {
      img = ImageIO.read(file);
    } catch (IOException e) {
      System.out.println("Exception Thrown: " + e);
      System.exit(0);
    }
  }

  /**
   * Crops the image
   */
  private void crop() {
    img = img.getSubimage(x, y, width, height);
  }

  /**
   * Changes the color of a pixel
   */
  private void colorChange() {
    // color
  }

  /**
   * Automatically crops excess space from the image
   */
  private void autoCrop() {
    // code
  }

  /**
   * Convert image to a checkered pattern
   */
  private void checker() {
    int color = new Color(255, 0, 0).getRGB(),
        yStart = 0;

    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = yStart; y < img.getHeight(); y += 2) {
        img.setRGB(x, y, color);
      }
      yStart = yStart == 0 ? 1 : 0;
    }
  }

  /**
   * Mass loads and converts files
   */
  public void convert(String dir) {
    // grab all files from given directory
    File[] files = new File(dir).listFiles();

    // check if valid directory
    if (files == null) {
      System.out.println("Invalid directory: " + dir);
      System.exit(0);
    }

    // perform conversions for all files
    for (int i = 0; i < files.length; i++) {
      if (files[i].isFile()) {
        String name = files[i].getName();
        String extension = (name.lastIndexOf(".") != -1 && name.lastIndexOf(".") != 0) ? 
          name.substring(name.lastIndexOf(".") + 1) : "png";

        System.out.println(extension);
        System.out.println("File: " + name);
        loadImage(files[i]);
        crop();
        checker();

        try {
          File outFile = new File("out/" + name);
          ImageIO.write(img, extension, outFile);
        } catch (IOException e) {
          System.out.println("Exception Thrown: " + e);
          System.exit(0);
        }
      }
    }
  }
}

