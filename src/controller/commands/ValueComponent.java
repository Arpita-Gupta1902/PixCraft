package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class converts the image to a value grayscale image.
 */
public class ValueComponent implements ImageCommands {

  private final String targetName;
  private final String imageName;

  /**
   * This is the constructor of ValueComponent class.
   */
  public ValueComponent(String imageName, String targetName) {
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.convertValueGrayScale(imageName, targetName);
  }
}
