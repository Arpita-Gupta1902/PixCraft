package controller;

import java.io.IOException;

/**
 * This is a Features interface that consists of various functions.
 * These functions are performed on the input image.
 */
public interface Features {

  void loadImage() throws Exception;

  void saveImage() throws Exception;

  void redComponent() throws Exception;

  void blueComponent() throws Exception;

  void greenComponent() throws Exception;

  void blurComponent() throws Exception;

  void lumaComponent() throws Exception;

  void intensityComponent() throws Exception;

  void valueComponent() throws Exception;

  void flipHorizontal() throws Exception;

  void flipVertical() throws Exception;

  void sepiaTone() throws Exception;

  void splitView(String splitPercentage) throws Exception;

  void brighten(String brightenAmount) throws Exception;

  void darken(String darkenAmount) throws Exception;

  void sharpen() throws Exception;

  void colorCorrection() throws Exception;

  void compression(String compressionPercentage) throws Exception;

  void adjustLevels(String blackPoint, String midPoint, String whitePoint) throws Exception;

  void showHistogram() throws IOException;

}
