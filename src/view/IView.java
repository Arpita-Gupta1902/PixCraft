package view;

import java.awt.image.BufferedImage;

import controller.Features;

/**
 * This is an IView interface that consists of methods that support the View.
 * Images and buttons are displayed properly inside view using these methods.
 * They also pop error messages in case of any errors.
 */
public interface IView {

  void showOriginalImage(BufferedImage image);

  void showOutputImage(BufferedImage image);

  void showHistogramRecent(BufferedImage image);

  void popErrorMessage(String error);

  void showProcessingIndicator();

  void hideProcessingIndicator();

  void addFeatures(Features future);

  void toggleSplitButtonColor();

  boolean getSplitViewToggleStatus();

  void clearOriginalImage();

  void clearOutputImage();

  String loadDialogBox();

  String saveDialogBox();

}
