package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to convert the image to sepia toned image.
 */
public class Sepia implements ImageCommands {

  private final String targetName;
  private final String imageName;

  /**
   * This is the constructor of Sepia class.
   */
  public Sepia(String imageName, String targetName) {
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.convertSepiaTone(imageName, targetName);
  }
}
