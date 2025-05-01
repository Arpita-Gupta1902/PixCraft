package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to darken the image by some decrement factor given by the user.
 */
public class Darken implements ImageCommands {

  private final String targetName;
  private final String imageName;
  private final int decrementFactor;

  /**
   * This is the constructor of Darken class.
   */
  public Darken(String decrementFactor, String imageName, String targetName) {
    this.decrementFactor = Integer.parseInt(decrementFactor);
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.darken(decrementFactor, imageName, targetName);
  }
}