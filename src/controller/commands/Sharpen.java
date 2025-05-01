package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to sharpen the image.
 */
public class Sharpen implements ImageCommands {
  private final String targetName;
  private final String imageName;

  /**
   * This is the constructor of Sharpen class.
   */
  public Sharpen(String imageName, String targetName) {
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.sharpenImage(imageName, targetName);
  }
}
