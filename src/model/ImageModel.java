package model;

import java.util.Map;

/**
 * This is an ImageModel interface that consists of various functions.
 * These functions are performed on the input image.
 */
public interface ImageModel {

  void getRedComponent(String imageName, String targetName);

  void getGreenComponent(String imageName, String targetName);

  void getBlueComponent(String imageName, String targetName);

  void flipHorizontal(String imageName, String targetName);

  void flipVertical(String imageName, String targetName);

  void brighten(int brightenFactor, String imageName, String targetName);

  void darken(int darkenFactor, String imageName, String targetName);

  void blurImage(String imageName, String targetName);

  void sharpenImage(String imageName, String targetName);

  void convertLumaGrayScale(String imageName, String targetName);

  void convertValueGrayScale(String imageName, String targetName);

  void convertIntensityGrayScale(String imageName, String targetName);

  void convertSepiaTone(String imageName, String targetName);

  void rgbSplit(String imageName, String target1, String target2, String target3);

  void rgbCombine(String image1, String image2, String image3, String targetName);

  int getRow();

  int getColumn();

  Pixel getPixel(int row, int col);

  void colorCorrection(String imageName, String targetName);

  void adjustLevel(String imageName, String targetName, double b, double m, double w);

  void loadImage(ImageModel image, String targetName);

  ImageModel saveImage(String targetName);

  void generateHistogram(String imageName, String targetName);

  void getCompressedImage(double threshold, String imageName, String targetName);

  void splitView(String imageName, String convertedImageName, double widthPercentage);

  String currentImage();

  ImageModel getImage(String imageName);

  Map<String, ImageModel> getMap();

  void splitViewGUI(String imageName, String convertedImageName, double widthPercentage);

}

