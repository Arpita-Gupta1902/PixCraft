package controller.guicommands;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import controller.GUIImageCommands;
import model.ImageModel;
import view.IView;

import static controller.commands.Save.toBufferedImage;

/**
 * This GUI class is used to compress the image by some percentage.
 * Compression in turn reduces the size of the image.
 */
public class Compression implements GUIImageCommands {
  private final IView view;
  private final String compressionValue;

  /**
   * This is the constructor of Compression class.
   */
  public Compression(IView view, String amount) {
    this.view = view;
    this.compressionValue = amount;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    if (compressionValue.isEmpty()) {
      view.popErrorMessage("Compression Field is empty");
      return;
    }

    double compressionPercentage = 0;
    try {
      compressionPercentage = Double.parseDouble(compressionValue);
    } catch (NumberFormatException e) {
      view.popErrorMessage("Please input correct values");
      return;
    }

    if (model.getMap().get("1") == null) {
      view.popErrorMessage("Load an Image first before doing any operation");
      return;
    }

    if (compressionPercentage < 0 || compressionPercentage > 100) {
      view.popErrorMessage("Enter a valid compression value between 0 and 100");
      return;
    }

    view.showOriginalImage(toBufferedImage(model.getImage("1")));

    double finalCompressionPercentage = compressionPercentage;
    SwingWorker<Void, Void> worker = new SwingWorker<>() {
      @Override
      protected Void doInBackground() {
        model.getCompressedImage(finalCompressionPercentage, "1", "2");
        model.getMap().put("temp", model.getImage("1"));
        model.getMap().put("1", model.getImage("2"));
        return null;
      }

      @Override
      protected void done() {
        view.hideProcessingIndicator();
        try {
          get();
          view.showOutputImage(toBufferedImage(model.getImage("2")));
        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
        }
      }
    };
    worker.execute();
    view.showProcessingIndicator();
  }
}
