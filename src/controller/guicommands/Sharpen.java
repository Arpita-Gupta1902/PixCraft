package controller.guicommands;

import controller.GUIImageCommands;
import model.ImageModel;
import view.IView;

import static controller.commands.Save.toBufferedImage;

/**
 * This GUI class is used to sharpen the image.
 */
public class Sharpen implements GUIImageCommands {
  private final IView view;

  /**
   * This is the constructor of Sharpen class.
   */
  public Sharpen(IView view) {
    this.view = view;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {

    if (model.getMap().get("1") == null) {
      view.popErrorMessage("Load an Image first before doing any operation");
    }

    view.showOriginalImage(toBufferedImage(model.getImage("1")));
    model.sharpenImage("1", "2");
    model.getMap().put("temp", model.getImage("1"));
    model.getMap().put("1", model.getImage("2"));
    view.showOutputImage(toBufferedImage(model.getImage("2")));
  }
}
