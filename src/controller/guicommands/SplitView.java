package controller.guicommands;

import controller.GUIImageCommands;
import model.ImageModel;
import view.IView;

import static controller.commands.Save.toBufferedImage;

/**
 * This is a GUI splitView class. It splits the image into two parts.
 * Then it performs different operations on one part and other part remains same as original image.
 */
public class SplitView implements GUIImageCommands {

  private final IView view;

  private final String splitPercentage;

  /**
   * This is the constructor of SplitView class.
   */
  public SplitView(IView view, String splitPercentage) {
    this.view = view;
    this.splitPercentage = splitPercentage;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {

    if (model.getMap().get("temp") == null) {
      view.popErrorMessage("Please do an operation first for a split preview");
      return;
    }

    if (splitPercentage.isEmpty()) {
      view.popErrorMessage("Split Field is empty");
      return;
    }
    if (!view.getSplitViewToggleStatus()) {
      double splitPercentageDouble = 0;
      try {
        splitPercentageDouble = Double.parseDouble(splitPercentage);
        view.toggleSplitButtonColor();
      } catch (NumberFormatException e) {
        view.popErrorMessage("Please input double values only");
        return;
      }
      view.showOriginalImage(toBufferedImage(model.getImage("temp")));
      model.splitViewGUI("2", "temp", splitPercentageDouble);
      model.getMap().put("1", model.getImage("2"));
      view.showOutputImage(toBufferedImage(model.getImage("splitView")));
    } else {
      view.toggleSplitButtonColor();
      view.showOriginalImage(toBufferedImage(model.getImage("temp")));
      model.getMap().remove("splitView");
      view.showOutputImage(toBufferedImage(model.getImage("2")));
    }
  }
}
