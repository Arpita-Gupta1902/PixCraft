package controller;

import java.io.IOException;

import model.ImageModel;

/**
 * This is a GUIImageCommands interface that consists of execute function.
 * This function is used to execute an operation on the image.
 */
public interface GUIImageCommands {

  void execute(ImageModel model) throws IOException;
}
