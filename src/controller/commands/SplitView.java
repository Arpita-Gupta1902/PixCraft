package controller.commands;

import java.util.Objects;

import controller.ImageCommands;
import model.ImageModel;

import static controller.Controller.compute;

/**
 * This is a splitView class. It splits the image into two parts.
 * Then it performs different operations on one part and other part remains same as original image.
 */
public class SplitView implements ImageCommands {
  private final String[] commands;
  private final double splitPercentage;

  /**
   * This is the constructor of SplitView class.
   */
  public SplitView(String[] commands, String splitPercentage) {
    this.commands = commands;
    this.splitPercentage = Double.parseDouble(splitPercentage);
    if ((this.splitPercentage < 0) || (this.splitPercentage > 100)) {
      throw new IllegalArgumentException("Split percentage should be between 0 and 100");
    }
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    String innerCommand = concatenateString(commands);
    if (isValidSplit(commands[0])) {
      try {
        compute(innerCommand, model);
      } catch (Exception e) {
        throw new IllegalStateException("SplitView not working");
      }
      model.splitView(commands[1], commands[2], splitPercentage);
    } else {
      throw new IllegalArgumentException("Invalid Command with split operation");
    }
  }

  private String concatenateString(String[] array) {
    StringBuilder result = new StringBuilder();

    for (String element : array) {
      result.append(element).append(" ");
    }
    if (result.length() > 0) {
      result.deleteCharAt(result.length() - 1);
    }

    return result.toString();
  }

  /**
   * This method is used to check if the split happened is a valid split.
   * This is done by checking if the operations performed on the image are valid operations.
   */
  private boolean isValidSplit(String word) {
    return Objects.equals(word, "blur") || Objects.equals(word, "sharpen")
            || Objects.equals(word, "sepia") || Objects.equals(word, "levels-adjust")
            || Objects.equals(word, "color-correct")
            || Objects.equals(word, "value-component")
            || Objects.equals(word, "intensity-component")
            || Objects.equals(word, "luma-component");
  }

}
