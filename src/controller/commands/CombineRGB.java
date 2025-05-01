package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to combine the red, blue and green component images into a single image.
 */
public class CombineRGB implements ImageCommands {
  private final String targetName;
  private final String image1;
  private final String image2;
  private final String image3;

  /**
   * This is the constructor of CombineRGB class.
   */
  public CombineRGB(String image1, String image2, String image3, String targetName) {
    this.image1 = image1;
    this.image2 = image2;
    this.image3 = image3;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.rgbCombine(image1, image2, image3, targetName);
  }
}
