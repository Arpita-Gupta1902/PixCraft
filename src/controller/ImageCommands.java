package controller;

import java.io.IOException;

import model.ImageModel;

/**
 * This is an interface that contains execute method.
 * This method is used to execute the operational commands that can be performed on the image.
 */
public interface ImageCommands {

  /**
   * This method executes the image model.
   */
  void execute(ImageModel model) throws IOException;
}
