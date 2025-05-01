package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to brighten the image by some increment factor given by the user.
 */
public class Brighten implements ImageCommands {

  private final String targetName;
  private final String imageName;
  private final int incrementFactor;

  /**
   * This is the constructor of the Brighten class.
   */
  public Brighten(String incrementFactor, String imageName, String targetName) {
    this.incrementFactor = Integer.parseInt(incrementFactor);
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.brighten(incrementFactor, imageName, targetName);
  }
}
