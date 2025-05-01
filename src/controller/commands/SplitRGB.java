package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to split the image into its three red, blue and green component images.
 */
public class SplitRGB implements ImageCommands {
  private final String imageName;
  private final String target1;
  private final String target2;
  private final String target3;

  /**
   * This is the constructor of SplitRGB class.
   */
  public SplitRGB(String imageName, String target1, String target2, String target3) {
    this.imageName = imageName;
    this.target1 = target1;
    this.target2 = target2;
    this.target3 = target3;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.rgbSplit(imageName, target1, target2, target3);
  }
}
