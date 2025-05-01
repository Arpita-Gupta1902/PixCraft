package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * This class is for image compression. It is used to compress the image.
 * By compressing the image, size of the image is reduced.
 */
public class ImageCompressionFunction {

  private static final double EPSILON = 0.0000001;
  private final int originalRows;
  private final int originalCols;
  private final ArrayList<double[][]> transformedImages = new ArrayList<>();
  private double thresholdCompression;

  /**
   * This is a constructor of ImageCompressionFunction class.
   */
  public ImageCompressionFunction(int originalRows, int originalCols) {
    this.originalRows = originalRows;
    this.originalCols = originalCols;
  }

  /**
   * This method is used to perform a haar transform on a 2D image.
   */
  public static double[][] transform2DHar(double[][] image) {
    int rows = image.length;
    int cols = image[0].length;
    double[][] temp = new double[rows][cols];
    for (int i = 0; i < rows; i++) {
      temp[i] = transformHarRow(image[i]);
    }

    for (int j = 0; j < cols; j++) {
      double[] column = new double[rows];
      for (int i = 0; i < rows; i++) {
        column[i] = temp[i][j];
      }
      column = transformHarRow(column);
      for (int i = 0; i < rows; i++) {
        temp[i][j] = column[i];
      }
    }

    return temp;
  }

  /**
   * This method is used to perform an inverse of haar transform on a 2D image.
   */
  public static double[][] inverseTransform2DHar(double[][] image) {
    int rows = image.length;
    int cols = image[0].length;

    double[][] temp = new double[rows][cols];
    for (int j = 0; j < cols; j++) {
      double[] column = new double[rows];
      for (int i = 0; i < rows; i++) {
        column[i] = image[i][j];
      }
      column = inverseTransformHarRow(column);
      for (int i = 0; i < rows; i++) {
        temp[i][j] = column[i];
      }
    }
    for (int i = 0; i < rows; i++) {
      temp[i] = inverseTransformHarRow(temp[i]);
    }
    return temp;
  }

  /**
   * This method is used to transform a 1D array by parsing it by length.
   */
  public static double[] transformAnArray(double[] s) {
    double[] result = Arrays.copyOf(s, s.length);
    for (int i = 0; i < s.length; i += 2) {
      double a = (s[i] + s[i + 1]) / Math.sqrt(2);
      double d = (s[i] - s[i + 1]) / Math.sqrt(2);
      result[i / 2] = a;
      result[i / 2 + s.length / 2] = d;
    }
    return result;
  }

  /**
   * This method is used to perform a haar transform on the rows of the array.
   */
  public static double[] transformHarRow(double[] s) {
    int m = s.length;
    double[] temp = Arrays.copyOf(s, s.length);
    while (m > 1) {
      double[] firstHalf = transformAnArray(Arrays.copyOfRange(temp, 0, m));
      double[] secondHalf = Arrays.copyOfRange(temp, m, temp.length);
      System.arraycopy(firstHalf, 0, temp, 0, firstHalf.length);
      System.arraycopy(secondHalf, 0, temp, firstHalf.length, secondHalf.length);
      m = m / 2;
    }
    return temp;
  }

  /**
   * This method is used to perform an inverse of the haar transform on the rows of the array.
   */
  public static double[] inverseTransformHarRow(double[] s) {
    int m = 1;
    double[] temp = Arrays.copyOf(s, s.length);
    while (m < s.length) {
      double[] firstHalf = Arrays.copyOfRange(temp, 0, m);
      double[] secondHalf = Arrays.copyOfRange(temp, m, 2 * m);

      for (int i = 0; i < m; i++) {
        double a = firstHalf[i];
        double d = secondHalf[i];
        temp[i * 2] = (a + d) / Math.sqrt(2);
        temp[i * 2 + 1] = (a - d) / Math.sqrt(2);
      }
      m *= 2;
    }
    return temp;
  }

  /**
   * This method is used to find the threshold values to perform the transform on an array.
   */
  void findThresholdValue(double compressionPercentage) {
    ArrayList<Double> allValues = new ArrayList<>();
    for (double[][] image : transformedImages) {
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[i].length; j++) {
          if (!containsWithPrecision(allValues, Math.abs(image[i][j]))
                  && Math.abs(image[i][j]) >= 0.001) {
            allValues.add(Math.abs(image[i][j]));
          }
        }
      }
    }
    Collections.sort(allValues);
    int thresholdIndex = compressionPercentage == 100.0 ? allValues.size() - 1 :
            (int) ((compressionPercentage / 100) * (allValues.size()));
    thresholdCompression = allValues.get(thresholdIndex);
  }

  /**
   * This method checks the precision of the values.
   */
  private boolean containsWithPrecision(List<Double> list, double value) {
    for (double listItem : list) {
      if (Math.abs(listItem - value) < EPSILON) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method makes the values 0 if those values are less than the threshold value.
   */
  void afterThresholdingValues() {
    for (double[][] image : transformedImages) {
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[i].length; j++) {
          if (Math.abs(image[i][j]) <= thresholdCompression) {
            image[i][j] = 0.0;
          }
        }
      }
    }
  }

  /**
   * This method is used to inverse the image model after unpadding it.
   */
  ArrayList<ImageModel> afterUnpadInverse() {
    ArrayList<ImageModel> results = new ArrayList<>();
    ArrayList<double[][]> unpaddedArrays = new ArrayList<>();
    for (double[][] image : transformedImages) {
      double[][] tempInvertedImage = inverseTransform2DHar(image);
      double[][] tempUnpaddedImage = unpadImage(tempInvertedImage, originalRows, originalCols);
      unpaddedArrays.add(tempUnpaddedImage);
    }
    results.add(doubleArrayToImage(unpaddedArrays.get(0), "red"));
    results.add(doubleArrayToImage(unpaddedArrays.get(1), "green"));
    results.add(doubleArrayToImage(unpaddedArrays.get(2), "blue"));
    return results;
  }

  /**
   * This method is used to perform the compression on an image.
   * It applies the 2d haar transformation on the image.
   */
  public void compressionBeforeThreshold(ImageModel originalImage) {
    double[][] inputImage = imageToDoubleArray(originalImage);
    double[][] tempPaddedImage = padImage(inputImage);
    double[][] tempTransformedImage = transform2DHar(tempPaddedImage);
    transformedImages.add(tempTransformedImage);
  }

  /**
   * This method is used to pad the image to make it as an nXn image.
   * Here n is a digit which is represented as raised to the power of 2.
   */
  public double[][] padImage(double[][] image) {
    int rows = image.length;
    int cols = image[0].length;
    int maxDim = Math.max(rows, cols);
    int paddedDim = 1;
    while (paddedDim < maxDim) {
      paddedDim *= 2;
    }
    double[][] paddedImage = new double[paddedDim][paddedDim];

    for (int i = 0; i < paddedDim; i++) {
      for (int j = 0; j < paddedDim; j++) {
        if (i < rows && j < cols) {
          paddedImage[i][j] = image[i][j];
        } else {
          paddedImage[i][j] = 0;
        }
      }
    }
    return paddedImage;
  }

  /**
   * This method is used to remove the padding of the image.
   */
  public double[][] unpadImage(double[][] image, int originalRows, int originalCols) {
    double[][] unpaddedImage = new double[originalRows][originalCols];
    int rows = image.length;
    int cols = image[0].length;

    for (int i = 0; i < originalRows; i++) {
      for (int j = 0; j < originalCols; j++) {
        if (i < rows && j < cols) {
          unpaddedImage[i][j] = image[i][j];
        }
      }
    }
    return unpaddedImage;
  }

  /**
   * This method is used to convert the image model to an array.
   */
  public double[][] imageToDoubleArray(ImageModel originalImage) {
    int rows = originalImage.getRow();
    int cols = originalImage.getColumn();
    double[][] result = new double[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        Pixel pixel = originalImage.getPixel(i, j);
        result[i][j] = (pixel.getRed() + pixel.getGreen() + pixel.getBlue());
      }
    }
    return result;
  }

  /**
   * This method is used to convert the 2d array into an image model.
   */
  public ImageModel doubleArrayToImage(double[][] array, String channel) {
    Pixel pixel = null;
    int rows = array.length;
    int cols = array[0].length;
    Pixel[][] resultImagePixels = new Pixel[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (Objects.equals(channel, "red")) {
          pixel = new Pixel((int) Math.round(array[i][j]), 0, 0);
        } else if (Objects.equals(channel, "green")) {
          pixel = new Pixel(0, (int) Math.round(array[i][j]), 0);
        } else if (Objects.equals(channel, "blue")) {
          pixel = new Pixel(0, 0, (int) Math.round(array[i][j]));
        }
        resultImagePixels[i][j] = pixel;
      }
    }
    return new Image(rows, cols, resultImagePixels);
  }

}
