package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This class creates a histogram for a given image.
 */
public class Histogram implements ImageCommands {
  private final String targetName;
  private final String imageName;

  /**
   * This is the constructor of Histogram class.
   */
  public Histogram(String imageName, String targetName) {
    this.imageName = imageName;
    this.targetName = targetName;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.generateHistogram(imageName, targetName);
  }
}