package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class is used to flip the image horizontally.
 */
public class FlipHorizontal implements ImageCommands {
  private final String targetName;
  private final String imageName;

  /**
   * This is the constructor of FlipHorizontal class.
   */
  public FlipHorizontal(String imageName, String targetName) {
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.flipHorizontal(imageName, targetName);
  }
}
