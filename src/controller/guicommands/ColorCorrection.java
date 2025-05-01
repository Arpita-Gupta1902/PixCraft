package controller.guicommands;

import controller.GUIImageCommands;
import model.ImageModel;
import view.IView;

import static controller.commands.Save.toBufferedImage;

/**
 * This GUI class is used to do the color correction of the image.
 */
public class ColorCorrection implements GUIImageCommands {
  private final IView view;

  /**
   * This is the constructor of ColorCorrection class.
   */
  public ColorCorrection(IView view) {
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
    model.colorCorrection("1", "2");
    model.getMap().put("temp", model.getImage("1"));
    model.getMap().put("1", model.getImage("2"));
    view.showOutputImage(toBufferedImage(model.getImage("2")));
  }
}
