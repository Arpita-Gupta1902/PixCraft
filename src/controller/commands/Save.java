package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to save an image by converting it to a buffered image.
 */
public class Save implements ImageCommands {
  private final String targetName;
  private final String savingPath;

  /**
   * This is the constructor of Save class.
   */
  public Save(String savingPath, String targetName) {
    this.savingPath = savingPath;
    this.targetName = targetName;
  }

  /**
   * This method creates an image to a buffered image.
   * It computes its red, blue and green pixel components and sets them. Then it returns the
   * resultant image.
   */
  public static BufferedImage toBufferedImage(ImageModel image) {
    BufferedImage modifiedImage = new BufferedImage(image.getRow(), image.getColumn(),
            BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < image.getRow(); x++) {
      for (int y = 0; y < image.getColumn(); y++) {
        int red = image.getPixel(x, y).getRed();
        int green = image.getPixel(x, y).getGreen();
        int blue = image.getPixel(x, y).getBlue();
        int rgb = (red << 16) | (green << 8) | blue;
        modifiedImage.setRGB(x, y, rgb);
      }
    }
    return modifiedImage;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) throws IOException {
    writeImage(model.saveImage(targetName), savingPath);
  }

  /**
   * This method is used to save an image by converting it to a buffered image.
   * This buffered image is then saved to the given destination path.
   */
  public void writeImage(ImageModel toSaveImage, String savingPath) throws IOException {
    File output = new File(savingPath);
    try {
      ImageIO.write(toBufferedImage(toSaveImage), findExtension(savingPath), output);
    } catch (Exception e) {
      throw new IOException();
    }
  }

  /**
   * This method is used to find the extension from the path of the file.
   */
  private String findExtension(String source) {
    int lastIndex = source.lastIndexOf('.');
    return source.substring(lastIndex + 1).trim();
  }


}
