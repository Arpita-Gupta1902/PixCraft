package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.imageio.ImageIO;

import model.Image;
import model.ImageModel;
import view.IView;

/**
 * This is a class that is used to test the controller.
 */
public class ControllerTest {

  ImageModel model;
  OutputStream outStream;
  PrintStream out;
  String redTest;
  String blueTest;
  String greenTest;
  String darkenTest;
  String brightTest;
  String valueTest;
  String intenseTest;
  String lumaTest;
  String horTest;
  String vertTest;
  String sepiaTest;
  String blurTest;
  String sharpenTest;
  String vertHorTest;
  String runTest;
  String combineTest;
  String splitTest;
  String colorCorrectTest;
  String adjustLevelsTest;
  String directScriptTest;
  String splitViewTest;
  String negativeSplitViewTest;
  String histogramTest;
  String compression30;
  String compression80;
  String ppmTest;
  String multipleOperations;
  String negativeCompression;
  String compression140;
  String wrongMidpointTest;
  String wrongWhitepointTest;
  String wrongBlackpointTest;
  String negativeBrighten;
  String negativeDarken;

  /**
   * This method is used to check if the images are equal.
   *
   * @param image1 is the first image.
   * @param image2 is the second image.
   * @return a boolean value tree if images are the same and false if they are not.
   */
  private boolean areImagesEqual(BufferedImage image1, BufferedImage image2) {
    if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
      return false;
    }
    for (int x = 0; x < image1.getWidth(); x++) {
      for (int y = 0; y < image1.getHeight(); y++) {
        int pixel1 = image1.getRGB(x, y);
        int pixel2 = image2.getRGB(x, y);
        if (pixel1 != pixel2) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * This method is used to load the files for testing.
   */
  @Before
  public void setup() {
    redTest = "load tree.jpeg tree\n" +
            "red-component tree red-tree\n" +
            "save images/red-tree.jpeg red-tree";
    blueTest = "load tree.jpeg tree\n" +
            "blue-component tree blue-tree\n" +
            "save images/blue-tree.jpeg blue-tree";
    greenTest = "load tree.jpeg tree\n" +
            "green-component tree green-tree\n" +
            "save images/green-tree.jpeg green-tree";
    darkenTest = "load tree.jpeg tree\n" +
            "darken 50 tree darken-tree\n" +
            "save images/darken-tree.jpeg darken-tree";
    brightTest = "load tree.jpeg tree\n" +
            "brighten 50 tree brighten-tree\n" +
            "save images/brighten-tree.jpeg brighten-tree";
    intenseTest = "load tree.jpeg tree\n" +
            "intensity-component tree intense-tree\n" +
            "save images/intense-tree.jpeg intense-tree";
    valueTest = "load tree.jpeg tree\n" +
            "value-component tree value-tree\n" +
            "save images/value-tree.jpeg value-tree ";
    lumaTest = "load tree.jpeg tree\n" +
            "luma-component tree luma-tree\n" +
            "save images/luma-tree.jpeg luma-tree";
    horTest = "load tree.jpeg tree\n" +
            "horizontal-flip tree hor-tree\n" +
            "save images/hor-tree.jpeg hor-tree";
    vertTest = "load tree.jpeg tree\n" +
            "vertical-flip tree vert-tree\n" +
            "save images/vert-tree.jpeg vert-tree";
    sepiaTest = "load tree.jpeg tree\n" +
            "sepia tree sepia-tree\n" +
            "save images/sepia-tree.jpeg sepia-tree";
    blurTest = "load tree.jpeg tree\n" +
            "blur tree blur-tree\n" +
            "save images/blur-tree.jpeg blur-tree";
    sharpenTest = "load tree.jpeg tree\n" +
            "sharpen tree sharpen-tree\n" +
            "save images/sharpen-tree.jpeg sharpen-tree";
    vertHorTest = "load tree.jpeg tree\n" +
            "horizontal-flip tree hor-tree\n" +
            "vertical-flip hor-tree vertHor-tree\n" +
            "save  images/vert-hor-tree.jpeg vertHor-tree";
    runTest = "run script.txt";
    combineTest = "load tree.jpeg tree\n" +
            "red-component tree red-tree\n" +
            "blue-component tree blue-tree\n" +
            "green-component tree green-tree\n" +
            "rgb-combine combinedtree red-tree green-tree blue-tree \n" +
            "save images/combinedtree.jpeg combinedtree";
    splitTest = "load tree.jpeg tree\n" +
            "rgb-split tree red-tree green-tree blue-tree\n" +
            "save images/split-red-tree.jpeg red-tree\n" +
            "save images/split-green-tree.jpeg green-tree\n" +
            "save images/split-blue-tree.jpeg blue-tree";
    colorCorrectTest = "load tree.jpeg tree\n" +
            "color-correct tree tree-corrected\n" +
            "save images/tree-corrected.jpeg tree-corrected";
    adjustLevelsTest = "load tree.jpeg tree\n" +
            "levels-adjust 20 100 255 tree tree-adjusted\n" +
            "save images/tree-adjusted.jpeg tree-adjusted";
    wrongMidpointTest = "load tree.jpeg tree\n" +
            "levels-adjust 120 100 255 tree tree-adjusted\n" +
            "save images/tree-adjusted.jpeg tree-adjusted";
    wrongWhitepointTest = "load tree.jpeg tree\n" +
            "levels-adjust 20 100 55 tree tree-adjusted\n" +
            "save images/tree-adjusted.jpeg tree-adjusted";
    wrongBlackpointTest = "load tree.jpeg tree\n" +
            "levels-adjust 420 100 55 tree tree-adjusted\n" +
            "save images/tree-adjusted.jpeg tree-adjusted";
    directScriptTest = "script.txt";
    splitViewTest = "load tree.jpeg tree\n" +
            "sepia tree split-sepia-tree split 70\n" +
            "save images/sepia-split-view.jpeg split-sepia-tree";
    negativeSplitViewTest = "load tree.jpeg tree\n" +
            "sepia tree split-sepia-tree split -70\n" +
            "save images/sepia-split-view.jpeg split-sepia-tree";
    histogramTest = "load tree.jpeg tree\n" +
            "histogram tree hist-tree\n" +
            "save images/hist-tree.jpeg hist-tree";
    compression30 = "load tree.jpeg tree\n" +
            "compress 30 tree compress-tree-30\n" +
            "save images/compress-tree-30.jpeg compress-tree-30";
    compression80 = "load tree.jpeg tree\n" +
            "compress 80 tree compress-tree-80\n" +
            "save images/compress-tree-80.jpeg compress-tree-80";
    negativeCompression = "load tree.jpeg tree\n" +
            "compress -80 tree compress-tree-80\n" +
            "save images/compress-tree-80.jpeg compress-tree-80";
    compression140 = "load tree.jpeg tree\n" +
            "compress 140 tree compress-tree-80\n" +
            "save images/compress-tree-80.jpeg compress-tree-80";
    ppmTest = "load tree.ppm tree\n" +
            "red-component tree red-tree\n" +
            "save images/red-ppm-tree.jpeg red-tree";
    multipleOperations = "load tree.jpeg tree\n" +
            "blue-component tree blue-tree\n" +
            "vertical-flip blue-tree vertical-blue-tree\n" +
            "save images/vertical-blue-tree.jpeg vertical-blue-tree";
    negativeBrighten = "load tree.jpeg tree\n" +
            "brighten -50 tree negative-bright-tree\n" +
            "save images/negative-bright-tree.jpeg negative-bright-tree";
    negativeDarken = "load tree.jpeg tree\n" +
            "darken -50 tree negative-dark-tree\n" +
            "save images/negative-dark-tree.jpeg negative-dark-tree";

    model = new Image(0, 0, null);
    outStream = new ByteArrayOutputStream();
    out = new PrintStream(outStream);
  }

  /**
   * Test to check compression exception where compression percent is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void checkCompressionException1() throws Exception {
    File expectedRed = new File("compress-tree-80.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRed);
    InputStream in = new ByteArrayInputStream(negativeCompression.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File actualImageFile = new File("images/compress-tree-80.jpeg");
    BufferedImage actualImage = ImageIO.read(actualImageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test to check compression exception where compression percent is greater than 100.
   */
  @Test(expected = IllegalArgumentException.class)
  public void checkCompressionException2() throws Exception {
    File expectedRed = new File("compress-tree-80.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRed);
    InputStream in = new ByteArrayInputStream(compression140.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File actualImageFile = new File("images/compress-tree-80.jpeg");
    BufferedImage actualImage = ImageIO.read(actualImageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test to check adjust level exception where midpoint is greater than black-point.
   */
  @Test(expected = IllegalArgumentException.class)
  public void adjustLevelException1() throws Exception {
    File expectedColorCorrected = new File("tree-adjusted.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedColorCorrected);
    InputStream in = new ByteArrayInputStream(wrongMidpointTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/tree-adjusted.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test to check adjust level exception where white-point is greater than midpoint.
   */
  @Test(expected = IllegalArgumentException.class)
  public void adjustLevelException2() throws Exception {
    File expectedColorCorrected = new File("tree-adjusted.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedColorCorrected);
    InputStream in = new ByteArrayInputStream(wrongWhitepointTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/tree-adjusted.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test to check adjust level exception where black-point is greater than 255.
   */
  @Test(expected = IllegalArgumentException.class)
  public void adjustLevelException3() throws Exception {
    File expectedColorCorrected = new File("tree-adjusted.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedColorCorrected);
    InputStream in = new ByteArrayInputStream(wrongBlackpointTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/tree-adjusted.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of processing
   * a red component image.
   * It loads the expected red image, processes it,
   * and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void redImageTest() throws Exception {
    File expectedRed = new File("red-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRed);
    InputStream in = new ByteArrayInputStream(redTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File actualImageFile = new File("images/red-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(actualImageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * processing a blue component image.
   * It loads the expected blue image, processes it, and
   * checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void blueImageTest() throws Exception {

    File expectedBlue = new File("blue-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedBlue);
    InputStream in = new ByteArrayInputStream(blueTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/blue-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of processing
   * a green component image.
   * It loads the expected green image, processes it,
   * and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void greenImageTest() throws Exception {

    File expectedGreen = new File("green-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedGreen);
    InputStream in = new ByteArrayInputStream(greenTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/green-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * darkening an image.
   * It loads the expected darkened image, processes
   * it, and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void darkenImageTest() throws Exception {

    File expectedDark = new File("dark-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedDark);
    InputStream in = new ByteArrayInputStream(darkenTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/darken-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * brightening an image.
   * It loads the expected brightened image, processes it,
   * and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void brightImageTest() throws Exception {

    File expectedBright = new File("bright-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedBright);
    InputStream in = new ByteArrayInputStream(brightTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/brighten-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * processing an intense component image.
   * It loads the expected intense image, processes it,
   * and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void intenseImageTest() throws Exception {

    File expectedIntense = new File("intense-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedIntense);
    InputStream in = new ByteArrayInputStream(intenseTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/intense-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * processing a value component image.
   * It loads the expected value image, processes it,
   * and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void valueImageTest() throws Exception {

    File expectedValue = new File("value-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedValue);
    InputStream in = new ByteArrayInputStream(valueTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/value-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * processing a luma component image.
   * It loads the expected luma image, processes it,
   * and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void lumaImageTest() throws Exception {

    File expectedLuma = new File("luma-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedLuma);
    InputStream in = new ByteArrayInputStream(lumaTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/luma-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * horizontally flipping an image.
   * It loads the expected horizontally flipped
   * image, processes it, and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void horImageTest() throws Exception {

    File expectedHor = new File("hor-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedHor);
    InputStream in = new ByteArrayInputStream(horTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("hor-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * vertically flipping an image.
   * It loads the expected vertically flipped
   * image, processes it, and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void vertImageTest() throws Exception {

    File expectedVert = new File("vert-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedVert);
    InputStream in = new ByteArrayInputStream(vertTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/vert-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * applying a sepia filter to an image.
   * It loads the expected sepia-toned image,
   * processes it, and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void sepiaImageTest() throws Exception {

    File expectedSepia = new File("sepia-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedSepia);
    InputStream in = new ByteArrayInputStream(sepiaTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/sepia-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * applying a blur filter to an image.
   * It loads the expected blurred image,
   * processes it, and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void blurImageTest() throws Exception {

    File expectedBlur = new File("blur-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedBlur);
    InputStream in = new ByteArrayInputStream(blurTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("blur-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case to verify the functionality of
   * applying a sharpen filter to an image.
   * It loads the expected sharpened image, processes
   * it, and checks if the processed image matches the expected image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void sharpenImageTest() throws Exception {

    File expectedSharpen = new File("sharpen-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedSharpen);
    InputStream in = new ByteArrayInputStream(sharpenTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/sharpen-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the scriptImageTest method to ensure
   * that it correctly processes a text script.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void scriptImageTest() throws Exception {

    File expectedRun = new File("vertical-blue-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(runTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/vertical-blue-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the combineTestImageTest method to ensure that it correctly combines the images.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void combineTestImageTest() throws Exception {

    File expectedCombine = new File("combinedtree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedCombine);
    InputStream in = new ByteArrayInputStream(combineTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/combinedtree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the splitTestImageTest method to ensure that it
   * correctly splits an image based on an image script.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void splitTestImageTest() throws Exception {

    InputStream in = new ByteArrayInputStream(splitTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File expectedRed = new File("red-tree.jpeg");
    File expectedGreen = new File("green-tree.jpeg");
    File expectedBlue = new File("blue-tree.jpeg");
    BufferedImage expectedImageRed = ImageIO.read(expectedRed);
    File imageFileRed = new File("images/split-red-tree.jpeg");
    BufferedImage expectedImageGreen = ImageIO.read(expectedGreen);
    File imageFileGreen = new File("images/split-green-tree.jpeg");
    BufferedImage expectedImageBlue = ImageIO.read(expectedBlue);
    File imageFileBlue = new File("images/split-blue-tree.jpeg");
    BufferedImage actualImageRed = ImageIO.read(imageFileRed);
    BufferedImage actualImageGreen = ImageIO.read(imageFileGreen);
    BufferedImage actualImageBlue = ImageIO.read(imageFileBlue);
    Assert.assertTrue(areImagesEqual(actualImageRed, expectedImageRed));
    Assert.assertTrue(areImagesEqual(actualImageGreen, expectedImageGreen));
    Assert.assertTrue(areImagesEqual(actualImageBlue, expectedImageBlue));
  }

  /**
   * Test the colorCorrection method to ensure that it
   * color corrects an image based on an image script.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void colorCorrectImageTest() throws Exception {

    File expectedColorCorrected = new File("tree-corrected.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedColorCorrected);
    InputStream in = new ByteArrayInputStream(colorCorrectTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/tree-corrected.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the adjustLevels method to ensure that it
   * correctly adjusts the pixel levels of  an image based on an image script.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void adjustLevelsImageTest() throws Exception {

    File expectedColorCorrected = new File("tree-adjusted.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedColorCorrected);
    InputStream in = new ByteArrayInputStream(adjustLevelsTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/tree-adjusted.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the ppmImage method to ensure that it
   * generates the image correctly based on ppm format.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void ppmImageTest() throws Exception {
    File expectedRun = new File("red-ppm-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(ppmTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/red-ppm-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the script by directly giving the script name only without giving the command.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void directScriptImageTest() throws Exception {

    File expectedRun = new File("vertical-blue-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(directScriptTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/vertical-blue-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the splitting of the image function where one operation is applied on one part of split.
   * Similarly other operation is applied on the other part of the split.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void splitViewImageTest() throws Exception {
    File expectedRun = new File("sepia-split-view.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(splitViewTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/sepia-split-view.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test an invalid input for split percentage.
   *
   * @throws IllegalArgumentException if an error occurs during the test.
   */
  @Test(expected = IllegalArgumentException.class)
  public void negativeSplitTest() throws Exception {
    File expectedRun = new File("sepia-split-view.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(negativeSplitViewTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/sepia-split-view.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the histogram function to create the histogram for a given image.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void histogramImageTest() throws Exception {

    File expectedRun = new File("hist-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(histogramTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/hist-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the compression to compress the image by 30%.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void compression30Test() throws Exception {

    File expectedRun = new File("compress-tree-30.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(compression30.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/compress-tree-30.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the compression to compress the image by 80%.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void compression80Test() throws Exception {
    File expectedRun = new File("compress-tree-80.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(compression80.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/compress-tree-80.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the multiple operations on an image.
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void multipleOperationTest() throws Exception {
    File expectedRun = new File("vertical-blue-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(multipleOperations.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/vertical-blue-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test for comparing with wrong images (failed test).
   *
   * @throws Exception If there is an exception during the test execution.
   */
  @Test
  public void failedOperationTest() throws Exception {
    File expectedRun = new File("blue-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(redTest.getBytes());

    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/red-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertFalse(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test an invalid command scenario.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test(expected = Exception.class)
  public void invalidCommand() throws Exception {
    String txt = "make tree.png tree";
    InputStream in = new ByteArrayInputStream(txt.getBytes());
    ImageModel model = new Image(0, 0, null);
    Controller controller = new Controller(model, in, out);
    controller.execute();
  }

  /**
   * Test an invalid syntax scenario.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test(expected = Exception.class)
  public void invalidSyntax() throws Exception {
    String txt = "load tree.png tree\n" + "flip-horizontal tree hor-tree";
    InputStream in = new ByteArrayInputStream(txt.getBytes());
    ImageModel model = new Image(0, 0, null);
    Controller controller = new Controller(model, in, out);
    controller.execute();
  }

  /**
   * Test the negative brighten operation which will give a darken image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void negativeBrighten() throws Exception {
    File expectedRun = new File("dark-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(negativeBrighten.getBytes());
    ImageModel model = new Image(0, 0, null);
    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/negative-bright-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test the negative darken operation which will give a brightened image.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void negativeDarken() throws Exception {
    File expectedRun = new File("bright-tree.jpeg");
    BufferedImage expectedImage = ImageIO.read(expectedRun);
    InputStream in = new ByteArrayInputStream(negativeDarken.getBytes());
    Controller controller = new Controller(model, in, out);
    controller.execute();
    File imageFile = new File("images/negative-dark-tree.jpeg");
    BufferedImage actualImage = ImageIO.read(imageFile);
    Assert.assertTrue(areImagesEqual(actualImage, expectedImage));
  }

  /**
   * Test case for the viewRedComponentTest method in the Controller class.
   * Verifies that the redComponent method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewRedComponentTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.redComponent();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("red-component was called \n", outStream.toString());
  }

  /**
   * Test case for the viewValueComponentTest method in the Controller class.
   * Verifies that the valueComponent method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewValueComponentTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.valueComponent();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("value-component was called \n", outStream.toString());
  }

  /**
   * Test case for the viewFlipHorizontalTest method in the Controller class.
   * Verifies that the flipHorizontal method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewFlipHorizontalTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.flipHorizontal();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("horizontal-flip was called \n", outStream.toString());
  }

  /**
   * Test case for the viewFlipVerticalTest method in the Controller class.
   * Verifies that the flipVertical method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewFlipVerticalTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.flipVertical();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("vertical-flip was called \n", outStream.toString());
  }

  /**
   * Test case for the viewBlurComponentTest method in the Controller class.
   * Verifies that the blurComponent method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewBlurComponentTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.blurComponent();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("blur-component was called \n", outStream.toString());
  }

  /**
   * Test case for the viewLumaComponentTest method in the Controller class.
   * Verifies that the lumaComponent method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewLumaComponentTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.lumaComponent();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("luma-component was called \n", outStream.toString());
  }

  /**
   * Test case for the viewIntensityComponentTest method in the Controller class.
   * Verifies that the intensityComponent method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewIntensityComponentTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.intensityComponent();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("intensity-component was called \n", outStream.toString());
  }

  /**
   * Test case for the viewSepiaToneTest method in the Controller class.
   * Verifies that the sepiaTone method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewSepiaToneTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.sepiaTone();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("sepia was called \n", outStream.toString());
  }

  /**
   * Test case for the viewSharpenTest method in the Controller class.
   * Verifies that the sharpen method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewSharpenTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.sharpen();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("sharpen was called \n", outStream.toString());
  }

  /**
   * Test case for the viewColorCorrectionTest method in the Controller class.
   * Verifies that the colorCorrection method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewColorCorrectionTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.colorCorrection();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("color-correction was called \n", outStream.toString());
  }

  /**
   * Test case for the viewCompressionTest method in the Controller class.
   * Verifies that the compression method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewCompressionTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.compression("50");
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("compression was called \n", outStream.toString());
  }

  /**
   * Test case for the viewSplitViewTest method in the Controller class.
   * Verifies that the splitView method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewSplitViewTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.splitView("50");
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("split-view was called \n", outStream.toString());
  }

  /**
   * Test case for the viewBrightenTest method in the Controller class.
   * Verifies that the brighten method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewBrightenTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.brighten("50");
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("brighten was called \n", outStream.toString());
  }

  /**
   * Test case for the viewDarkenTest method in the Controller class.
   * Verifies that the darken method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewDarkenTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.darken("50");
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("darken was called \n", outStream.toString());
  }

  /**
   * Test case for the viewAdjustLevelsTest method in the Controller class.
   * Verifies that the adjustLevels method is called back from the View
   * when the particular button is clicked, and it outputs the expected message.
   */
  @Test
  public void viewAdjustLevelsTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.adjustLevels("0", "128", "255");
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("adjust-levels was called \n", outStream.toString());
  }

  /**
   * Test case for the viewMultipleOperationsTest method in the Controller class.
   * Verifies that the adjustLevels and redComponent methods are called back from the View
   * when the particular buttons are clicked, and it outputs the expected messages.
   */
  @Test
  public void viewMultipleOperationsTest() throws Exception {
    InputStream in = new ByteArrayInputStream("".getBytes());
    IView view = new MockView();
    Controller controller = new Controller(model, in, out);
    controller.setView(view);
    try {
      controller.adjustLevels("0", "128", "255");
    } catch (Exception e) {
      throw new Exception();
    }
    try {
      controller.redComponent();
    } catch (Exception e) {
      throw new Exception();
    }
    Assert.assertEquals("adjust-levels was called \n" +
            "red-component was called \n", outStream.toString());
  }

  class MockView implements IView {

    @Override
    public void showOriginalImage(BufferedImage image) {
      //This method does not have any implementation.
      // It is added because all the methods of the interface were needed here.
    }

    @Override
    public void showOutputImage(BufferedImage image) {
      //This method does not have any implementation.
      // It is added because all the methods of the interface were needed here.
    }

    @Override
    public void showHistogramRecent(BufferedImage image) {
      //This method does not have any implementation.
      // It is added because all the methods of the interface were needed here.
    }

    @Override
    public void popErrorMessage(String error) {
      //This method does not have any implementation.
      // It is added because all the methods of the interface were needed here.
    }

    @Override
    public void showProcessingIndicator() {
      //This method does not have any implementation.
      // It is added because all the methods of the interface were needed here.
    }

    @Override
    public void hideProcessingIndicator() {
      //This method does not have any implementation.
      // It is added because all the methods of the interface were needed here.
    }

    @Override
    public void addFeatures(Features future) {
      //This method does not have any implementation.
      // It is added because all the methods of the interface were needed here.
    }

    @Override
    public void toggleSplitButtonColor() {
      //This method does not have any implementation.
      // It is added because all the methods of the interface were needed here.
    }

    @Override
    public boolean getSplitViewToggleStatus() {
      return false;
    }

    @Override
    public void clearOriginalImage() {
      //This method does not have any implementation.
      // It is added because all the methods of the interface were needed here.
    }

    @Override
    public void clearOutputImage() {
      //This method does not have any implementation.
      // It is added because all the methods of the interface were needed here.
    }

    @Override
    public String loadDialogBox() {
      return null;
    }

    @Override
    public String saveDialogBox() {
      return "";
    }
  }


}