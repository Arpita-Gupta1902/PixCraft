package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This is a class created to extract the blue component of the image.
 */
public class BlueComponent implements ImageCommands {

  private final String targetName;
  private final String imageName;

  /**
   * This is the constructor of the BlueComponent class.
   */
  public BlueComponent(String imageName, String targetName) {
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.getBlueComponent(imageName, targetName);
  }
}