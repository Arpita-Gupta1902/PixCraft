package controller.guicommands;

import controller.GUIImageCommands;
import model.ImageModel;
import view.IView;

import static controller.commands.Save.toBufferedImage;

/**
 * This GUI class is used to brighten the image by some increment factor given by the user.
 */
public class Brighten implements GUIImageCommands {

  private final IView view;

  private final String brightenValue;

  /**
   * This is the constructor of the Brighten class.
   */
  public Brighten(IView view, String amount) {
    this.view = view;
    this.brightenValue = amount;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    if (brightenValue.isEmpty()) {
      view.popErrorMessage("Brighten Field is empty");
    }
    double brightenPercentage = 0;
    try {
      brightenPercentage = Double.parseDouble(brightenValue);
    } catch (NumberFormatException e) {
      view.popErrorMessage("Please input correct values");
      return;
    }

    if (model.getMap().get("1") == null) {
      view.popErrorMessage("Load an Image first before doing any operation");
    }
    view.showOriginalImage(toBufferedImage(model.getImage("1")));
    model.brighten((int) brightenPercentage, "1", "2");
    model.getMap().put("temp", model.getImage("1"));
    model.getMap().put("1", model.getImage("2"));
    view.showOutputImage(toBufferedImage(model.getImage("2")));
  }
}
