package controller.guicommands;


import controller.GUIImageCommands;
import model.ImageModel;
import view.IView;

import static controller.commands.Save.toBufferedImage;

/**
 * This is a GUI class created for adjusting the histogram according to the non-linear function.
 */
public class AdjustLevels implements GUIImageCommands {
  private final IView view;
  private final String blackPoint;
  private final String midPoint;
  private final String whitePoint;

  /**
   * This is the constructor of AdjustLevels class.
   */
  public AdjustLevels(IView view, String blackPoint, String midPoint, String whitePoint) {
    this.view = view;
    this.blackPoint = blackPoint;
    this.midPoint = midPoint;
    this.whitePoint = whitePoint;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    if (blackPoint.isEmpty()) {
      view.popErrorMessage("blackPoint Field is empty");
    }

    if (midPoint.isEmpty()) {
      view.popErrorMessage("midPoint Field is empty");
    }

    if (whitePoint.isEmpty()) {
      view.popErrorMessage("whitePoint Field is empty");
    }
    double bPoint = 0;
    double mPoint = 0;
    double wPoint = 0;
    try {
      bPoint = Double.parseDouble(blackPoint);
      mPoint = Double.parseDouble(midPoint);
      wPoint = Double.parseDouble(whitePoint);
    } catch (NumberFormatException e) {
      view.popErrorMessage("Please input correct double values");
      return;
    }

    if (model.getMap().get("1") == null) {
      view.popErrorMessage("Load an Image first before doing any operation");
    }
    view.showOriginalImage(toBufferedImage(model.getImage("1")));
    model.adjustLevel("1", "2", bPoint, mPoint, wPoint);
    model.getMap().put("temp", model.getImage("1"));
    model.getMap().put("1", model.getImage("2"));
    view.showOutputImage(toBufferedImage(model.getImage("2")));
  }
}
