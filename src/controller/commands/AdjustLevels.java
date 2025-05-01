package controller.commands;

import controller.ImageCommands;
import model.ImageModel;

/**
 * This is a class created for adjusting the histogram according to the non-linear function.
 */
public class AdjustLevels implements ImageCommands {

  private final String targetName;
  private final String imageName;
  private final double blackPoint;
  private final double midPoint;
  private final double whitePoint;

  /**
   * This is the constructor of AdjustLevels class.
   */
  public AdjustLevels(String imageName, String targetName, String blackPoint,
                      String midPoint, String whitePoint) {
    this.imageName = imageName;
    this.targetName = targetName;
    this.blackPoint = Double.parseDouble(blackPoint);
    this.midPoint = Double.parseDouble(midPoint);
    this.whitePoint = Double.parseDouble(whitePoint);
    if ((this.midPoint < this.blackPoint) || (this.whitePoint < this.midPoint)) {
      throw new IllegalArgumentException("B M W values should be in ascending order.");
    }
    if ((this.midPoint > 255) || (this.midPoint < 0)) {
      throw new IllegalArgumentException("Values should like between 0 and 255");
    }
    if ((this.whitePoint > 255) || (this.whitePoint < 0)) {
      throw new IllegalArgumentException("Values should like between 0 and 255");
    }
    if ((this.blackPoint > 255) || (this.blackPoint < 0)) {
      throw new IllegalArgumentException("Values should like between 0 and 255");
    }

  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    model.adjustLevel(imageName, targetName, blackPoint, midPoint, whitePoint);
  }
}
