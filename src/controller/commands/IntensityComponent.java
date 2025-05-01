package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This is a class that is used to convert the image to intensity greyscale image.
 */
public class IntensityComponent implements ImageCommands {

  private final String targetName;
  private final String imageName;

  /**
   * This is the constructor of IntensityComponent class.
   */
  public IntensityComponent(String imageName, String targetName) {
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.convertIntensityGrayScale(imageName, targetName);
  }
}
