package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to blur the image.
 */
public class Blur implements ImageCommands {
  private final String targetName;
  private final String imageName;

  /**
   * This is the constructor of the Blur class.
   */
  public Blur(String imageName, String targetName) {
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.blurImage(imageName, targetName);
  }

}