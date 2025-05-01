package controller.guicommands;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import controller.GUIImageCommands;
import model.ImageModel;
import view.IView;

import static controller.commands.Load.toImage;

/**
 * This GUI class is used to load the image from a particular source path.
 */
public class Load implements GUIImageCommands {
  private final IView view;

  /**
   * This is a constructor of the Load class.
   */
  public Load(IView view) {
    this.view = view;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {

    String selectedFilePath = view.loadDialogBox();
    File imageFile = new File(selectedFilePath);
    BufferedImage image;
    try {
      image = ImageIO.read(imageFile);
    } catch (Exception e) {
      throw new IllegalStateException("Loading not successful");
    }
    view.showOriginalImage(image);
    model.loadImage(toImage(image), "1");
    model.loadImage(toImage(image), "2");
    model.getMap().remove("temp");
    model.getMap().remove("splitView");
    view.clearOutputImage();
  }
}
