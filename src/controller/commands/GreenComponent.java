package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to get the green component of the image.
 */
public class GreenComponent implements ImageCommands {

  private final String targetName;
  private final String imageName;

  /**
   * This is the constructor of GreenComponent class.
   */
  public GreenComponent(String imageName, String targetName) {
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.getGreenComponent(imageName, targetName);
  }
}