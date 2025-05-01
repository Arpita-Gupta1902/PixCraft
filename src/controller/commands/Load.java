package controller.commands;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import controller.ImageCommands;
import model.Image;
import model.ImageModel;
import model.Pixel;

/**
 * This class is used to load the image from a particular source path.
 */
public class Load implements ImageCommands {
  private final String sourcePath;
  private final String targetName;

  /**
   * This is a constructor of the Load class.
   */
  public Load(String sourcePath, String targetName) {
    this.targetName = targetName;
    this.sourcePath = sourcePath;
  }

  /**
   * This method is used to create a buffered image.
   * Then it extracts the red, blue and green pixel components from the imageFile, assigns it
   * to the buffered image and returns the result.
   */
  public static ImageModel toImage(File imageFile, String extension) {
    Pixel[][] resultImagePixels = null;
    BufferedImage image = null;
    try {
      if (extension.equals("ppm")) {
        return imageFromPPM(imageFile);
      }
      image = ImageIO.read(imageFile);
      resultImagePixels = new Pixel[image.getWidth()][image.getHeight()];
      if (image != null) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < width; x++) {
          for (int y = 0; y < height; y++) {
            int rgb = image.getRGB(x, y);
            int red = (rgb >> 16) & 0xFF;
            int blue = (rgb >> 8) & 0xFF;
            int green = rgb & 0xFF;
            resultImagePixels[x][y] = new Pixel(red, blue, green);
          }
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException();
    }
    return new Image(image.getWidth(), image.getHeight(), resultImagePixels);
  }

  /**
   * This method is used to create a buffered image.
   * Then it extracts the red, blue and green pixel components from the imageFile, assigns it
   * to the buffered image and returns the result.
   */
  public static ImageModel toImage(BufferedImage image) {
    Pixel[][] resultImagePixels = new Pixel[image.getWidth()][image.getHeight()];
    if (image != null) {
      int width = image.getWidth();
      int height = image.getHeight();
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          int rgb = image.getRGB(x, y);
          int red = (rgb >> 16) & 0xFF;
          int blue = (rgb >> 8) & 0xFF;
          int green = rgb & 0xFF;
          resultImagePixels[x][y] = new Pixel(red, blue, green);
        }
      }
    }
    ImageModel result = new Image(image.getWidth(), image.getHeight(), resultImagePixels);
    return result;
  }

  /**
   * This method is used to compute the file which is in ppm format.
   * It reads, its magic number, gets the width and height, then gets the red, blue and
   * green components based on this data. Creates a new pixel with these components and sets this
   * for every pixel in the result image and returns the resultant image after computation.
   */
  public static ImageModel imageFromPPM(File ppmFile) {

    int width = 0;
    int height = 0;
    Pixel[][] resultImagePixels = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(ppmFile))) {
      String magicNumber = reader.readLine();
      if (!"P3".equals(magicNumber)) {
        throw new IOException("Unsupported PPM format: " + magicNumber);
      }
      String dimensions = reader.readLine();
      StringTokenizer dimensionTokenizer = new StringTokenizer(dimensions);
      width = Integer.parseInt(dimensionTokenizer.nextToken());
      height = Integer.parseInt(dimensionTokenizer.nextToken());
      resultImagePixels = new Pixel[width][height];
      List<Integer> pixelValues = new ArrayList<>();
      while (pixelValues.size() < width * height * 3) {
        String line = reader.readLine();
        StringTokenizer pixelTokenizer = new StringTokenizer(line);
        while (pixelTokenizer.hasMoreTokens()) {
          pixelValues.add(Integer.parseInt(pixelTokenizer.nextToken()));
        }
      }
      int pixelIndex = 0;
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int red = pixelValues.get(pixelIndex++);
          int green = pixelValues.get(pixelIndex++);
          int blue = pixelValues.get(pixelIndex++);
          resultImagePixels[x][y] = new Pixel(red, green, blue);
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException();
    }
    ImageModel result = new Image(width, height, resultImagePixels);
    return result;
  }

  /**
   * This method executes the image model.
   */
  @Override
  public void execute(ImageModel model) {
    ImageModel image = convertToImageModel(sourcePath);
    model.loadImage(image, targetName);

  }

  /**
   * This method is used to convert the image to an image model.
   */
  public ImageModel convertToImageModel(String sourceFilePath) {
    File imageFile = new File(sourceFilePath);
    return toImage(imageFile, findExtension(sourceFilePath));
  }

  /**
   * This method is used to find the extension from the path of the file.
   */
  private String findExtension(String source) {
    int lastIndex = source.lastIndexOf('.');
    return source.substring(lastIndex + 1).trim();
  }

}
