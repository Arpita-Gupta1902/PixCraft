package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to compress the image by some percentage.
 * Compression in turn reduces the size of the image.
 */
public class Compression implements ImageCommands {
  private final String targetName;
  private final String imageName;
  private final double compressionPercentage;

  /**
   * This is the constructor of Compression class.
   */
  public Compression(String compressionPercentage, String imageName, String targetName) {
    this.compressionPercentage = Double.parseDouble(compressionPercentage);
    this.imageName = imageName;
    this.targetName = targetName;
    if ((this.compressionPercentage < 0) || (this.compressionPercentage > 100)) {
      throw new IllegalArgumentException("Compression percentage should be between 0 and 100");
    }
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.getCompressedImage(compressionPercentage, imageName, targetName);
  }
}