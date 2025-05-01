package controller;

import java.io.ByteArrayInputStream;

import model.Image;
import model.ImageModel;
import view.IView;
import view.JFrameView;

/**
 * This is a class called ImageProcessing.
 * It executes the main function which is the start of the program.
 */
public class ImageProcessing {
  /**
   * This is the main function. It is the start of the program.
   * It creates the controller object and calls it.
   */
  public static void main(String[] args) throws Exception {
    ImageModel model = new Image(0, 0, null);
    if (args.length == 0) {
      Controller controller = new Controller(model, new ByteArrayInputStream("".getBytes())
              , System.out);
      IView view = new JFrameView("Image Processing Program");
      controller.setView(view);
    } else if (args[0].equals("-text")) {
      Controller controller = new Controller(model, System.in, System.out);
      controller.execute();

    } else if (args[0].contains(".txt")) {
      Controller controller = new Controller(model, new ByteArrayInputStream(args[0].getBytes())
              , System.out);
      controller.execute();
    } else {
      throw new IllegalArgumentException("Invalid Command Line provided");
    }
  }
}
