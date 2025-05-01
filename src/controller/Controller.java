package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import controller.commands.Run;
import controller.guicommands.AdjustLevels;
import controller.guicommands.BlueComponent;
import controller.guicommands.Blur;
import controller.guicommands.Brighten;
import controller.guicommands.ColorCorrection;
import controller.guicommands.Compression;
import controller.guicommands.Darken;
import controller.guicommands.FlipHorizontal;
import controller.guicommands.FlipVertical;
import controller.guicommands.GreenComponent;
import controller.guicommands.Histogram;
import controller.guicommands.IntensityComponent;
import controller.guicommands.Load;
import controller.guicommands.LumaComponent;
import controller.guicommands.RedComponent;
import controller.guicommands.Save;
import controller.guicommands.Sepia;
import controller.guicommands.Sharpen;
import controller.guicommands.SplitView;
import controller.guicommands.ValueComponent;
import model.ImageModel;
import view.IView;

/**
 * This is a controller class that computes the command line argument given by the user.
 */
public class Controller implements Features {

  private final InputStream in;
  private final ImageModel model;
  private final Appendable out;
  private GUIImageCommands cmd = null;
  private IView view;

  /**
   * This is a constructor of the controller class.
   */
  public Controller(ImageModel model, InputStream in, Appendable out) {
    this.in = in;
    this.model = model;
    this.out = out;
  }

  /**
   * This is a method to compute the command given by the user.
   * It calls different operational functions based on the keywords given in the command.
   */
  public static void compute(String command, ImageModel model) throws Exception {
    String[] words = command.split(" ");
    Map<String, Integer> keys = new HashMap<>();
    int i = 1;
    for (String word : words) {
      keys.put(word, i);
      i++;
    }
    ImageCommands cmd = null;
    if (keys.containsKey("split")) {

      cmd = new controller.commands.SplitView(Arrays.copyOfRange(words, 0, 3), words[4]);
    } else if (keys.containsKey("load")) {
      cmd = new controller.commands.Load(words[1], words[2]);

    } else if (keys.containsKey("save")) {
      cmd = new controller.commands.Save(words[1], words[2]);

    } else if (keys.containsKey("brighten")) {
      cmd = new controller.commands.Brighten(words[1], words[2], words[3]);

    } else if (keys.containsKey("darken")) {
      cmd = new controller.commands.Darken(words[1], words[2], words[3]);

    } else if (keys.containsKey("red-component")) {
      cmd = new controller.commands.RedComponent(words[1], words[2]);

    } else if (keys.containsKey("green-component")) {
      cmd = new controller.commands.GreenComponent(words[1], words[2]);

    } else if (keys.containsKey("blue-component")) {
      cmd = new controller.commands.BlueComponent(words[1], words[2]);

    } else if (keys.containsKey("vertical-flip")) {
      cmd = new controller.commands.FlipVertical(words[1], words[2]);

    } else if (keys.containsKey("horizontal-flip")) {
      cmd = new controller.commands.FlipHorizontal(words[1], words[2]);

    } else if (keys.containsKey("rgb-split")) {
      cmd = new controller.commands.SplitRGB(words[1], words[2], words[3], words[4]);

    } else if (keys.containsKey("rgb-combine")) {
      cmd = new controller.commands.CombineRGB(words[2], words[3], words[4], words[1]);

    } else if (keys.containsKey("blur")) {
      cmd = new controller.commands.Blur(words[1], words[2]);

    } else if (keys.containsKey("sharpen")) {
      cmd = new controller.commands.Sharpen(words[1], words[2]);

    } else if (keys.containsKey("sepia")) {
      cmd = new controller.commands.Sepia(words[1], words[2]);

    } else if (keys.containsKey("luma-component")) {
      cmd = new controller.commands.LumaComponent(words[1], words[2]);

    } else if (keys.containsKey("intensity-component")) {
      cmd = new controller.commands.IntensityComponent(words[1], words[2]);

    } else if (keys.containsKey("value-component")) {
      cmd = new controller.commands.ValueComponent(words[1], words[2]);

    } else if (keys.containsKey("run")) {
      cmd = new Run(words[1]);
    } else if (keys.containsKey("color-correct")) {
      cmd = new controller.commands.ColorCorrection(words[1], words[2]);

    } else if (keys.containsKey("levels-adjust")) {
      cmd = new controller.commands.AdjustLevels(words[4], words[5], words[1], words[2], words[3]);

    } else if (keys.containsKey("histogram")) {
      cmd = new controller.commands.Histogram(words[1], words[2]);
    } else if (keys.containsKey("compress")) {
      cmd = new controller.commands.Compression(words[1], words[2], words[3]);
    } else if (keys.containsKey("exit")) {
      System.exit(0);
    } else if (keys.containsKey("")) {
      return;
    } else {
      Path path = Paths.get(command);
      if (Files.exists(path)) {
        cmd = new Run(command);
      } else if (command.contains(".txt")) {
        throw new Exception("Wrong file path provided");
      } else {
        throw new Exception("Invalid Command or syntax too bad");
      }
    }
    if (cmd != null) {
      cmd.execute(model);
    }
  }

  /**
   * This method is used to set the view where the image and buttons are displayed.
   */
  public void setView(IView v) {
    view = v;
    view.addFeatures(this);
  }

  /**
   * This is a method that scans the command line argument given by the user.
   * It then computes the command using the compute function.
   */
  public void execute() throws Exception {
    Scanner sc = new Scanner(in);
    while (sc.hasNextLine()) {
      String command = sc.nextLine();
      if (Objects.equals(command, "exit")) {
        break;
      }
      compute(command, model);
    }
    sc.close();
  }

  /**
   * This method is used to load the image.
   */
  @Override
  public void loadImage() throws Exception {
    try {
      this.out.append("load was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new Load(view);
    cmd.execute(model);
    this.showHistogram();
  }

  /**
   * This method is used to get the red component of the image.
   */
  @Override
  public void redComponent() throws Exception {
    try {
      this.out.append("red-component was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new RedComponent(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to blur the image.
   */
  @Override
  public void blurComponent() throws Exception {
    try {
      this.out.append("blur-component was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new Blur(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to get the luma component of the image.
   */
  @Override
  public void lumaComponent() throws Exception {
    try {
      this.out.append("luma-component was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new LumaComponent(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to get the intensity component of the image.
   */
  @Override
  public void intensityComponent() throws Exception {
    try {
      this.out.append("intensity-component was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new IntensityComponent(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to get the value component of the image.
   */
  @Override
  public void valueComponent() throws Exception {
    try {
      this.out.append("value-component was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new ValueComponent(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to flip the image horizontally.
   */
  @Override
  public void flipHorizontal() throws Exception {
    try {
      this.out.append("horizontal-flip was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new FlipHorizontal(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to flip the image vertically.
   */
  @Override
  public void flipVertical() throws Exception {
    try {
      this.out.append("vertical-flip was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new FlipVertical(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to get the blue component of the image.
   */
  @Override
  public void blueComponent() throws Exception {
    try {
      this.out.append("blue-component was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new BlueComponent(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to get the green component of the image.
   */
  @Override
  public void greenComponent() throws Exception {
    try {
      this.out.append("green-component was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new GreenComponent(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to get the sepia tone of the image.
   */
  @Override
  public void sepiaTone() throws Exception {
    try {
      this.out.append("sepia was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new Sepia(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to sharpen the image.
   */
  @Override
  public void sharpen() throws Exception {
    try {
      this.out.append("sharpen was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new Sharpen(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to color-correct the image.
   */
  @Override
  public void colorCorrection() throws Exception {
    try {
      this.out.append("color-correction was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new ColorCorrection(view);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to compress the image.
   */
  @Override
  public void compression(String compressionPercentage) throws Exception {
    try {
      this.out.append("compression was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new Compression(view, compressionPercentage);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to get the split view of the image.
   */
  @Override
  public void splitView(String splitPercentage) throws Exception {
    try {
      this.out.append("split-view was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new SplitView(view, splitPercentage);
    cmd.execute(model);
    this.showHistogram();
  }

  /**
   * This method is used to brighten the image.
   */
  @Override
  public void brighten(String amount) throws Exception {
    try {
      this.out.append("brighten was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new Brighten(view, amount);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to darken the image.
   */
  @Override
  public void darken(String amount) throws Exception {
    try {
      this.out.append("darken was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new Darken(view, amount);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to make a histogram of the image.
   */
  @Override
  public void showHistogram() throws IOException {
    cmd = new Histogram(view);
    cmd.execute(model);
  }

  /**
   * This method is used to adjust the blackpoint, midpoint and whitepoint levels of the image.
   */
  @Override
  public void adjustLevels(String blackPoint, String midPoint, String whitePoint)
          throws Exception {
    try {
      this.out.append("adjust-levels was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new AdjustLevels(view, blackPoint, midPoint, whitePoint);
    cmd.execute(model);
    this.showHistogram();
    togglesSplitCheck();
  }

  /**
   * This method is used to save the image.
   */
  @Override
  public void saveImage() throws Exception {
    try {
      this.out.append("save was called \n");
    } catch (Exception e) {
      throw new Exception();
    }
    cmd = new Save(view);
    cmd.execute(model);

  }

  /**
   * This method is used to toggle the splitview check.
   */
  private void togglesSplitCheck() {
    if (view.getSplitViewToggleStatus()) {
      view.toggleSplitButtonColor();
    }
  }

}


