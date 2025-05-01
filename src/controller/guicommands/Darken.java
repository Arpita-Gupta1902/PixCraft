package controller.guicommands;

import controller.GUIImageCommands;
import model.ImageModel;
import view.IView;

import static controller.commands.Save.toBufferedImage;

/**
 * This GUI class is used to darken the image by some decrement factor given by the user.
 */
public class Darken implements GUIImageCommands {

  private final IView view;

  private final String darkenValue;

  /**
   * This is the constructor of Darken class.
   */
  public Darken(IView view, String amount) {
    this.view = view;
    this.darkenValue = amount;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    if (darkenValue.isEmpty()) {
      view.popErrorMessage("Darken Field is empty");
    }
    double darkenPercentage = 0;
    try {
      darkenPercentage = Double.parseDouble(darkenValue);
    } catch (NumberFormatException e) {
      view.popErrorMessage("Please input correct values");
      return;
    }

    if (model.getMap().get("1") == null) {
      view.popErrorMessage("Load an Image first before doing any operation");
    }
    view.showOriginalImage(toBufferedImage(model.getImage("1")));
    model.darken((int) darkenPercentage, "1", "2");
    model.getMap().put("temp", model.getImage("1"));
    model.getMap().put("1", model.getImage("2"));
    view.showOutputImage(toBufferedImage(model.getImage("2")));

  }
}

