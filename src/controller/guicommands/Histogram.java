package controller.guicommands;

import controller.GUIImageCommands;
import model.ImageModel;
import view.IView;

import static controller.commands.Save.toBufferedImage;

/**
 * This GUI class creates a histogram for a given image.
 */
public class Histogram implements GUIImageCommands {

  private final IView view;

  /**
   * This is the constructor of Histogram class.
   */
  public Histogram(IView view) {
    this.view = view;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    if (model.getMap().get("2") == null) {
      return;
    }
    model.generateHistogram("2", "histogram");
    view.showHistogramRecent(toBufferedImage(model.getImage("histogram")));
  }
}
