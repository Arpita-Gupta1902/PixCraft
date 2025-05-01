package model;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static controller.commands.Load.toImage;

/**
 * This is a class image that implements ImageModel interface.
 * It contains the row, col and pixel information.
 * It then calls the respective function that performs the particular operation on the image.
 */
public class Image implements ImageModel {
  private final int row;
  private final int col;
  private final Pixel[][] pixel;
  private final Map<String, ImageModel> mappedObjects = new HashMap<>();
  private String initialImage;


  /**
   * This is a constructor of the image class.
   */
  public Image(int row, int col, Pixel[][] pixel) {
    this.row = row;
    this.col = col;
    if (pixel != null) {
      this.pixel = Arrays.copyOf(pixel, pixel.length);
    } else {
      this.pixel = new Pixel[0][0];
    }
  }

  @Override
  public ImageModel getImage(String imageName) {
    return mappedObjects.get(imageName);
  }

  @Override
  public Map<String, ImageModel> getMap() {
    return mappedObjects;
  }

  /**
   * This function creates a new image and applies different operations on pixels of the image.
   */
  private ImageModel applyOperationToAllPixels(Function<Pixel, Pixel> operation, String imageName) {
    ImageModel image = mappedObjects.get(imageName);
    if (image == null) {
      throw new IllegalStateException("Image Not found or name entered in wrong syntax");
    }
    Pixel[][] resultImagePixels = new Pixel[image.getRow()][image.getColumn()];
    for (int x = 0; x < image.getRow(); x++) {
      for (int y = 0; y < image.getColumn(); y++) {
        resultImagePixels[x][y] = operation.apply(image.getPixel(x, y));
      }
    }
    ImageModel copy = new Image(image.getRow(), image.getColumn(), resultImagePixels);
    return copy;
  }

  /**
   * This method is used to get the rows in an image.
   */
  @Override
  public int getRow() {
    return this.row;
  }

  /**
   * This method is used to get the columns in an image.
   */
  @Override
  public int getColumn() {
    return this.col;
  }

  /**
   * This method is used to get the pixels in an image.
   */
  @Override
  public Pixel getPixel(int row, int col) {
    return pixel[row][col];
  }

  /**
   * This method is used to get the split view of an image.
   * One half of the image displays on type of operation performed on the image
   * and other half displays other type of operation.
   */
  public void splitView(String imageName, String convertedImageName, double widthPercentage) {
    ImageModel original = mappedObjects.get(imageName);
    ImageModel imageConverted = mappedObjects.get(convertedImageName);
    Pixel[][] resultImagePixels = new Pixel[original.getRow()][original.getColumn()];
    int widthLimit = (int) (original.getRow() * (widthPercentage) / 100);
    for (int i = 0; i < original.getRow(); i++) {
      for (int j = 0; j < original.getColumn(); j++) {
        if (i < widthLimit) {
          resultImagePixels[i][j] = original.getPixel(i, j);
        } else {
          resultImagePixels[i][j] = imageConverted.getPixel(i, j);
        }
      }
    }
    ImageModel splitImage = new Image(original.getRow(), original.getColumn(), resultImagePixels);
    mappedObjects.put(convertedImageName, splitImage);
  }

  /**
   * This is a method for splitview display in the view GUI.
   */
  public void splitViewGUI(String imageName, String convertedImageName, double widthPercentage) {
    ImageModel original = mappedObjects.get(imageName);
    ImageModel imageConverted = mappedObjects.get(convertedImageName);
    Pixel[][] resultImagePixels = new Pixel[original.getRow()][original.getColumn()];
    int widthLimit = (int) (original.getRow() * (widthPercentage) / 100);
    for (int i = 0; i < original.getRow(); i++) {
      for (int j = 0; j < original.getColumn(); j++) {
        if (i < widthLimit) {
          resultImagePixels[i][j] = original.getPixel(i, j);
        } else {
          resultImagePixels[i][j] = imageConverted.getPixel(i, j);
        }
      }
    }
    ImageModel splitImage = new Image(original.getRow(), original.getColumn(), resultImagePixels);
    mappedObjects.put("splitView", splitImage);
  }

  /**
   * This method is used to return the current image on which the operations are performed.
   */
  @Override
  public String currentImage() {
    return initialImage;
  }

  /**
   * This method is used to get the red component of the image.
   */
  public void getRedComponent(String imageName, String targetName) {
    ImageModel result = applyOperationToAllPixels(pixel -> new Pixel(pixel.getRed(), 0,
            0), imageName);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method is used to get the green component of the image.
   */
  public void getGreenComponent(String imageName, String targetName) {
    ImageModel result = applyOperationToAllPixels(pixel -> new Pixel(0, pixel.getGreen(),
            0), imageName);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method is used to get the blue component of the image.
   */
  public void getBlueComponent(String imageName, String targetName) {
    ImageModel result = applyOperationToAllPixels(pixel -> new Pixel(0, 0,
            pixel.getBlue()), imageName);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method is used to flip the image horizontally.
   */
  public void flipHorizontal(String imageName, String targetName) {
    ImageModel image = mappedObjects.get(imageName);
    Pixel[][] resultImagePixels = new Pixel[image.getRow()][image.getColumn()];
    for (int x = 0; x < image.getRow(); x++) {
      for (int y = 0; y < image.getColumn(); y++) {
        resultImagePixels[image.getRow() - x - 1][y] = image.getPixel(x, y);
      }
    }
    ImageModel copy = new Image(image.getRow(), image.getColumn(), resultImagePixels);
    mappedObjects.put(targetName, copy);
  }

  /**
   * This method is used to flip the image vertically.
   */
  public void flipVertical(String imageName, String targetName) {
    ImageModel image = mappedObjects.get(imageName);
    Pixel[][] resultImagePixels = new Pixel[image.getRow()][image.getColumn()];
    for (int x = 0; x < image.getRow(); x++) {
      for (int y = 0; y < image.getColumn(); y++) {
        resultImagePixels[x][image.getColumn() - y - 1] = image.getPixel(x, y);

      }
    }
    ImageModel copy = new Image(image.getRow(), image.getColumn(), resultImagePixels);
    mappedObjects.put(targetName, copy);
  }

  /**
   * This method is used to brighten the image by some increment factor given by the user.
   */
  public void brighten(int brightenFactor, String imageName, String targetName) {
    ImageModel result = applyOperationToAllPixels(pixel1 -> new Pixel(
            (pixel1.getRed() + brightenFactor),
            (pixel1.getGreen() + brightenFactor),
            (pixel1.getBlue() + brightenFactor)
    ), imageName);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method is used to darken the image by some decrement factor given by the user.
   */
  public void darken(int darkenFactor, String imageName, String targetName) {
    ImageModel result = applyOperationToAllPixels(pixel1 -> new Pixel(
            (pixel1.getRed() - darkenFactor),
            (pixel1.getGreen() - darkenFactor),
            (pixel1.getBlue() - darkenFactor)
    ), imageName);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method is used to blur the image.
   */
  public void blurImage(String imageName, String targetName) {
    ImageModel image = mappedObjects.get(imageName);
    double[][] filter = {
            {(double) 1 / 16, (double) 1 / 8, (double) 1 / 16},
            {(double) 1 / 8, (double) 1 / 4, (double) 1 / 8},
            {(double) 1 / 16, (double) 1 / 8, (double) 1 / 16}
    };
    ImageModel result = applyFilter(filter, image);
    mappedObjects.put(targetName, result);
  }


  /**
   * This method is used to sharpen the image.
   */
  public void sharpenImage(String imageName, String targetName) {
    ImageModel image = mappedObjects.get(imageName);
    double[][] filter = {
            {-(double) 1 / 8, -(double) 1 / 8, -(double) 1 / 8, -(double) 1 / 8, -(double) 1 / 8},
            {-(double) 1 / 8, (double) 1 / 4, (double) 1 / 4, (double) 1 / 4, -(double) 1 / 8},
            {-(double) 1 / 8, (double) 1 / 4, (double) 1, (double) 1 / 4, -(double) 1 / 8},
            {-(double) 1 / 8, (double) 1 / 4, (double) 1 / 4, (double) 1 / 4, -(double) 1 / 8},
            {-(double) 1 / 8, -(double) 1 / 8, -(double) 1 / 8, -(double) 1 / 8, -(double) 1 / 8}
    };
    ImageModel result = applyFilter(filter, image);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method applies the filer on different pixels of the image.
   */
  private ImageModel applyFilter(double[][] filter, ImageModel image) {
    int rows = image.getRow();
    int cols = image.getColumn();
    Pixel[][] resultImagePixels = new Pixel[rows][cols];
    int filterRowLen = filter.length;
    int filterColLen = filter[0].length;
    for (int x = 0; x < rows; x++) {
      for (int y = 0; y < cols; y++) {
        double totRed = 0;
        double totGreen = 0;
        double totBlue = 0;
        for (int i = 0; i < filterRowLen; i++) {
          for (int j = 0; j < filterColLen; j++) {
            int neighborX = x - filterRowLen / 2 + i;
            int neighborY = y - filterColLen / 2 + j;
            if (neighborX >= 0 && neighborX < rows && neighborY >= 0 && neighborY < cols) {
              double factor = filter[i][j];
              totRed += image.getPixel(neighborX, neighborY).getRed() * factor;
              totGreen += image.getPixel(neighborX, neighborY).getGreen() * factor;
              totBlue += image.getPixel(neighborX, neighborY).getBlue() * factor;
            }
          }
        }
        int red = (int) totRed;
        int green = (int) totGreen;
        int blue = (int) totBlue;
        resultImagePixels[x][y] = new Pixel(red, green, blue);
      }
    }
    ImageModel resImage = new Image(rows, cols, resultImagePixels);
    return resImage;
  }

  /**
   * This method converts the image to a luma grayscale image.
   */
  public void convertLumaGrayScale(String imageName, String targetName) {
    ImageModel result = applyOperationToAllPixels(pixel1 -> new Pixel((pixel1.luma()),
            (pixel1.luma()), (pixel1.luma())), imageName);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method converts the image to a value grayscale image.
   */
  public void convertValueGrayScale(String imageName, String targetName) {
    ImageModel result = applyOperationToAllPixels(pixel1 -> new Pixel((pixel1.maxValue()),
            (pixel1.maxValue()), (pixel1.maxValue())), imageName);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method converts the image to intensity grayscale image.
   */
  public void convertIntensityGrayScale(String imageName, String targetName) {
    ImageModel result = applyOperationToAllPixels(pixel1 -> new Pixel(pixel1.intensity(),
            pixel1.intensity(), pixel1.intensity()), imageName);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method modifies the sepia tone of the image.
   */
  public void convertSepiaTone(String imageName, String targetName) {
    ImageModel result = applyOperationToAllPixels(Pixel::getSepia, imageName);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method is used to split the image into its three red, blue and green component images.
   */
  public void rgbSplit(String imageName, String target1, String target2, String target3) {
    ArrayList<ImageModel> results = getAllComponentImages(imageName);
    mappedObjects.put(target1, results.get(0));
    mappedObjects.put(target2, results.get(1));
    mappedObjects.put(target3, results.get(2));
  }

  private ArrayList<ImageModel> getAllComponentImages(String imageName) {
    ArrayList<ImageModel> images = new ArrayList<>();
    ImageModel image1 = applyOperationToAllPixels(pixel -> new Pixel(pixel.getRed(), 0,
            0), imageName);
    ImageModel image2 = applyOperationToAllPixels(pixel -> new Pixel(0, pixel.getGreen(),
            0), imageName);
    ImageModel image3 = applyOperationToAllPixels(pixel -> new Pixel(0, 0,
            pixel.getBlue()), imageName);
    images.add(image1);
    images.add(image2);
    images.add(image3);
    return images;
  }

  /**
   * This method is used to combine the red, blue and green component images into a single image.
   */
  public void rgbCombine(String image1Name, String image2Name, String image3Name,
                         String targetName) {
    ImageModel image1 = mappedObjects.get(image1Name);
    ImageModel image2 = mappedObjects.get(image2Name);
    ImageModel image3 = mappedObjects.get(image3Name);
    ImageModel resultImage = combineAllComponents(image1, image2, image3);
    mappedObjects.put(targetName, resultImage);
  }

  /**
   * This method is used to combine all the three images into an resultant image.
   */
  ImageModel combineAllComponents(ImageModel image1, ImageModel image2, ImageModel image3) {
    if (!areDimensionsEqual(image1, image2, image3)) {
      throw new IllegalArgumentException("Image dimensions are not equal.");
    }

    int referenceWidth = image1.getRow();
    int referenceHeight = image1.getColumn();
    Pixel[][] resultImagePixels = new Pixel[referenceWidth][referenceHeight];
    int red;
    int blue;
    int green;
    for (int x = 0; x < referenceWidth; x++) {
      for (int y = 0; y < referenceHeight; y++) {
        red = image1.getPixel(x, y).getRed() + image2.getPixel(x, y).getRed()
                + image3.getPixel(x, y).getRed();
        green = image1.getPixel(x, y).getGreen() + image2.getPixel(x, y).getGreen()
                + image3.getPixel(x, y).getGreen();
        blue = image1.getPixel(x, y).getBlue() + image2.getPixel(x, y).getBlue()
                + image3.getPixel(x, y).getBlue();
        resultImagePixels[x][y] = new Pixel(red, green, blue);
      }
    }
    ImageModel resultImage = new Image(referenceWidth, referenceHeight, resultImagePixels);
    return resultImage;
  }

  /**
   * This method is used to generate the histogram from an image.
   */
  public void generateHistogram(String imageName, String targetName) {
    ImageModel image = mappedObjects.get(imageName);
    BufferedImage histogramImage = new BufferedImage(256, 256,
            BufferedImage.TYPE_INT_RGB);
    Graphics graphics = histogramImage.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, 256, 256);
    int[] redHistogramArray = channelHistogramValues("red", image);
    int[] greenHistogramArray = channelHistogramValues("green", image);
    int[] blueHistogramArray = channelHistogramValues("blue", image);

    int[] concatenatedHistogramArray = new int[redHistogramArray.length
            + greenHistogramArray.length + blueHistogramArray.length];
    System.arraycopy(redHistogramArray, 0, concatenatedHistogramArray, 0
            , redHistogramArray.length);
    System.arraycopy(greenHistogramArray, 0, concatenatedHistogramArray
            , redHistogramArray.length, greenHistogramArray.length);
    System.arraycopy(blueHistogramArray, 0, concatenatedHistogramArray
            , redHistogramArray.length + greenHistogramArray.length
            , blueHistogramArray.length);

    int maxFrequency = 0;
    for (int value : concatenatedHistogramArray) {
      maxFrequency = Math.max(maxFrequency, value);
    }

    drawHistogram(graphics, Color.RED, redHistogramArray, maxFrequency);
    drawHistogram(graphics, Color.GREEN, greenHistogramArray, maxFrequency);
    drawHistogram(graphics, Color.BLUE, blueHistogramArray, maxFrequency);
    ImageModel result = toImage(histogramImage);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method helps in drawing the histogram for an image.
   */
  private void drawHistogram(Graphics graphics, Color color, int[] histogramArray
          , int maxFrequency) {
    graphics.setColor(color);
    for (int frequency : histogramArray) {
      maxFrequency = Math.max(maxFrequency, frequency);
    }

    for (int i = 0; i < 255; i++) {
      int normalizedFrequency1 = (int) (255.0 * histogramArray[i] / maxFrequency);
      int normalizedFrequency2 = (int) (255.0 * histogramArray[i + 1] / maxFrequency);

      graphics.drawLine(i, 255 - normalizedFrequency1, i + 1,
              255 - normalizedFrequency2);
    }
  }

  /**
   * This method is used to get the histogram values for the red, blue and green channels.
   */
  private int[] channelHistogramValues(String channel, ImageModel image) {
    int[] histogram = new int[256];

    for (int i = 0; i < image.getRow(); i++) {
      for (int j = 0; j < image.getColumn(); j++) {
        Pixel pixel = image.getPixel(i, j);
        int value = 0;
        if ("red".equals(channel)) {
          value = pixel.getRed();
        } else if ("green".equals(channel)) {
          value = pixel.getGreen();
        } else if ("blue".equals(channel)) {
          value = pixel.getBlue();
        }
        histogram[value]++;
      }
    }

    return histogram;
  }

  /**
   * This method is used for color correcting the image.
   */
  @Override
  public void colorCorrection(String imageName, String targetName) {
    ImageModel image = mappedObjects.get(imageName);
    int[] redHistogram = channelHistogramValues("red", image);
    int[] greenHistogram = channelHistogramValues("green", image);
    int[] blueHistogram = channelHistogramValues("blue", image);

    int redPeak = findPeak(redHistogram);
    int greenPeak = findPeak(greenHistogram);
    int bluePeak = findPeak(blueHistogram);

    if (hasTwoPeaks(redHistogram, redPeak) || hasTwoPeaks(greenHistogram, greenPeak)
            || hasTwoPeaks(blueHistogram, bluePeak)) {
      throw new IllegalStateException("Two peaks exist, impossible to color correct images.");
    }
    int averagePeakPosition = (redPeak + greenPeak + bluePeak) / 3;

    int redOffset = averagePeakPosition - redPeak;
    int greenOffset = averagePeakPosition - greenPeak;
    int blueOffset = averagePeakPosition - bluePeak;

    Pixel[][] resultImagePixels = new Pixel[image.getRow()][image.getColumn()];
    for (int i = 0; i < image.getRow(); i++) {
      for (int j = 0; j < image.getColumn(); j++) {
        Pixel pixel = image.getPixel(i, j);
        int newRed = Math.min(255, Math.max(0, pixel.getRed() + redOffset));
        int newGreen = Math.min(255, Math.max(0, pixel.getGreen() + greenOffset));
        int newBlue = Math.min(255, Math.max(0, pixel.getBlue() + blueOffset));
        resultImagePixels[i][j] = new Pixel(newRed, newGreen, newBlue);
      }
    }
    ImageModel result = new Image(image.getRow(), image.getColumn(), resultImagePixels);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method is used for adjusting the pixel levels for red, blue and green components of image.
   */
  @Override
  public void adjustLevel(String imageName, String targetName, double blackPoint, double midPoint,
                          double whitePoint) {

    double calculationA = blackPoint * blackPoint * (midPoint - whitePoint)
            - blackPoint * (midPoint * midPoint - whitePoint * whitePoint)
            + whitePoint * midPoint * midPoint - midPoint * whitePoint * whitePoint;

    double calculationAa = -blackPoint * (128 - 255) + 128 * whitePoint - 255 * midPoint;
    double calculation = blackPoint * blackPoint * (128 - 255) + 255 * midPoint * midPoint
            - 128 * whitePoint * whitePoint;
    double calculationAc = blackPoint * blackPoint * (255 * midPoint - 128 * whitePoint)
            - blackPoint * (255 * midPoint * midPoint - 128 * whitePoint * whitePoint);

    double a = calculationAa / calculationA;
    double b = calculation / calculationA;
    double c = calculationAc / calculationA;

    ImageModel image = mappedObjects.get(imageName);
    Pixel[][] resultImagePixels = new Pixel[image.getRow()][image.getColumn()];
    for (int i = 0; i < image.getRow(); i++) {
      for (int j = 0; j < image.getColumn(); j++) {
        Pixel pixel = image.getPixel(i, j);
        int newRed = changePixelsLevels(pixel.getRed(), a, b, c);
        int newGreen = changePixelsLevels(pixel.getGreen(), a, b, c);
        int newBlue = changePixelsLevels(pixel.getBlue(), a, b, c);
        resultImagePixels[i][j] = new Pixel(newRed, newGreen, newBlue);
      }
    }
    ImageModel result = new Image(image.getRow(), image.getColumn(), resultImagePixels);
    mappedObjects.put(targetName, result);
  }

  /**
   * This method is used for compressing the image.
   * Compresssion reduces the size of the image.
   */
  public void getCompressedImage(double compressionPercentage, String imageName,
                                 String targetName) {
    ArrayList<ImageModel> splittedImages = getAllComponentImages(imageName);
    ImageModel originalImage = mappedObjects.get(imageName);
    int rowNumber = originalImage.getRow();
    int colNumber = originalImage.getColumn();
    ImageCompressionFunction imageCompression = new ImageCompressionFunction(rowNumber, colNumber);
    imageCompression.compressionBeforeThreshold(splittedImages.get(0));
    imageCompression.compressionBeforeThreshold(splittedImages.get(1));
    imageCompression.compressionBeforeThreshold(splittedImages.get(2));
    imageCompression.findThresholdValue(compressionPercentage);
    imageCompression.afterThresholdingValues();
    ArrayList<ImageModel> results = imageCompression.afterUnpadInverse();
    ImageModel result = combineAllComponents(results.get(0), results.get(1), results.get(2));
    mappedObjects.put(targetName, result);
  }

  /**
   * This method is used for changing the pixel levels of the image.
   */
  private int changePixelsLevels(int channelValue, double a, double b, double c) {
    return (int) (a * channelValue * channelValue + b * channelValue + c);
  }

  /**
   * This method is used for finding the peak of the histogram.
   */
  private int findPeak(int[] histogram) {
    int maxIndex = 0;
    int maxValue = 0;
    for (int i = 11; i < 245; i++) {
      if (histogram[i] > maxValue) {
        maxValue = histogram[i];
        maxIndex = i;
      }
    }

    return maxIndex;
  }

  /**
   * This method is used to check if the histogram has two peaks.
   * It returns the boolean value.
   */
  private boolean hasTwoPeaks(int[] histogram, int peak) {
    int peakNum = 0;
    for (int i = 11; i < 245; i++) {
      if (histogram[i] == peak) {
        peakNum++;
      }
    }
    return peakNum > 1;
  }

  /**
   * This method is used to check if the dimensions of the image are equal.
   */
  private boolean areDimensionsEqual(ImageModel image1, ImageModel image2, ImageModel image3) {
    return image1.getRow() == image2.getRow()
            && image1.getColumn() == image2.getColumn()
            && image1.getRow() == image3.getRow()
            && image1.getColumn() == image3.getColumn();
  }

  /**
   * This method is used to load the image from the given path.
   */
  @Override
  public void loadImage(ImageModel image, String target) {

    mappedObjects.put(target, image);
    initialImage = target;
  }

  /**
   * This method is used to save the image to a target path.
   */
  @Override
  public ImageModel saveImage(String targetName) {
    return this.mappedObjects.get(targetName);
  }


}

 