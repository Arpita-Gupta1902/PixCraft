package controller.commands;

import java.io.BufferedReader;
import java.io.FileReader;

import controller.ImageCommands;
import model.ImageModel;


/**
 * This class is used to run the command line script.
 */
public class Run implements ImageCommands {

  private final String filePath;

  /**
   * This is the constructor of Run class.
   */
  public Run(String filePath) {
    this.filePath = filePath;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      String line = reader.readLine();
      while (!line.isEmpty()) {
        if (line.contains(filePath)) {
          throw new Exception("Same Run script can't be entered in the run script");
        }

        if (line.contains("exit")) {
          break;
        }
        line = reader.readLine();
      }
    } catch (Exception e) {
      // This catch is empty.
    }

  }
}
