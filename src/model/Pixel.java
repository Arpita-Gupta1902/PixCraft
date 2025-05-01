package model;

/**
 * This is a class pixel that contains the red, blue and green pixel components of the image.
 */
public class Pixel {
  private final int red;
  private final int green;
  private final int blue;

  /**
   * This is a constructor of the pixel class.
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    this.red = Math.max(0, Math.min(255, red));
    this.green = Math.max(0, Math.min(255, green));
    this.blue = Math.max(0, Math.min(255, blue));
  }

  /**
   * This is a class that is used to get the red component pixel of the image.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * This is a class that is used to get the green component pixel of the image.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * This is a class that is used to get the blue component pixel of the image.
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * This is a class that is used to get the max of the red, blue or green component of the image.
   */
  public int maxValue() {
    return Math.max(red, Math.max(blue, green));
  }

  /**
   * This is a class that is used to compute the intensity of the image.
   */
  public int intensity() {
    return (int) ((red + green + blue) / 3.0);
  }

  /**
   * This is a class that is used to compute the luma of the image.
   */
  public int luma() {
    return (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
  }

  /**
   * This is a class that is used to compute the sepia tone of the image.
   */
  public Pixel getSepia() {
    double newRed = 0.393 * red + 0.769 * green + 0.189 * blue;
    double newGreen = 0.349 * red + 0.686 * green + 0.168 * blue;
    double newBlue = 0.272 * red + 0.534 * green + 0.131 * blue;

    return new Pixel((int) newRed, (int) newGreen, (int) newBlue);
  }

}

