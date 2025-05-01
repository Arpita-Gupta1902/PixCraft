# GRIME: Graphical Image Manipulation and Enhancement

# Java Swing-Based Image Editor with Histogram Visualization and Batch Processing

This project is a Java-based desktop application for interactive and scriptable image processing, built using Java Swing. It allows users to load, view, edit, and save images (PPM, PNG, JPG) through a graphical user interface with scrollable image panels and a real-time RGB histogram. The application supports both GUI mode and script-based batch processing, providing a full-featured image manipulation experience.

# Key Features

1. Built with Java Swing, following MVC architecture for clean separation of concerns.

2. Interactive GUI with image display and scroll support for large images.

3. Real-time RGB histogram updates reflecting changes to the displayed image.

4. Load and save images in multiple formats (PPM, JPG, PNG).

5. Visualize individual color components (Red, Green, Blue).

6. Image transformations including:

7. Flip (Horizontal & Vertical)

8. Blur and Sharpen

9. Greyscale (Luma) and Sepia filter

10. Color correction

11. Compression with user-defined quality

12. Level adjustments (black, mid, white values)

13. Split view with adjustable comparison slider

14. Supports command-line operations:

    -file mode for script execution
    
    -text mode for command-line input

15. Default GUI launch via JAR double-click

# Tech Stack

Java 17

Java Swing

JUnit for controller testing

IntelliJ IDEA for development

# CONTROLLER

# ImageProcessing Class

This class has a main function that is the start of the program. It first creates a new image model
and then creates a new controller object of the controller class that takes in the model and
system.in. It then calls the go function from the controller.

# controller Class

This class basically takes in the user input and computes it by calling different functions
associated with the input command.
It consists of a class constructor and various methods.

## controller() (controller Class Constructor)

This is a constructor of the controller class.

## execute() Method

It takes in the user input as the command and computes the command using compute function described
below until the user does not was to exit or the session is not closed.

## compute(String command, ImageModel model) Method

This method creates a functionController and a hashmap.
It then splits the command given by the user
and stores the operation to be performed as a key in the hashmap.
Each key is associated with an integer indexing which acts as a value
for that key.

## load() method

This method creates an object of Load class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## save() method

This method creates an object of Save class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## brightenImage() method

This method creates an object of Brighten class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method..

## darkenImage() method

This method creates an object of Save class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## redComponent() method

This method creates an object of RedComponent class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## greenComponent() method

This method creates an object of GreenComponent class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## blueComponent() method

This method creates an object of BlueComponent class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## verticalFlip() method

This method creates an object of verticalFlip class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.m.

## horizontalFlip() method

This method creates an object of horizontalFlip class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## blurImage() method

This method creates an object of blurImage class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## sharpenImage() method

This method creates an object of sharpenImage class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## sepiaImage() method

This method creates an object of sepiaImage class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## lumaImage() method

This method creates an object of lumaImage class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## intensityImage() method

This method creates an object of intensityImage class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## valueImage() method

This method creates an object of valueImage class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## Compression() method

This method creates an object of Compression class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.am.

## Histogram() method

This method creates an object of Histogram class from GUICommands and executes it.

## ColorCorrection() method

This method creates an object of ColorCorrection class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## SplitView() method

This method creates an object of SplitView class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

## AdjustLevels() method

This method creates an object of AdjustLevels class from GUICommands and executes it
by passing the model to it and then calls the showHistogram method.

# Commands Folder

This folder consists of different command classes that can be used to perform the
image manipulation. Following are the classes:

## load() Class

This class checks if the string array consists of "load" keyword to load the image.
if any keyword is present in the string array that contains ".png", ".jpg" or ".ppm" extensions
then it assigns that keyword value to the source path variable.
The last keyword left in the string array becomes the destination path as specified by the user
in the command.
If source or destination path is not specified by the user then it throws
"Image file not provided exception".
Otherwise it calls the loadImage(sourcePath, targetName) method.

## save() Class

This class checks if the string array consists of "save" keyword to save the image.
if any keyword is present in the string array that contains ".png", ".jpg" or ".ppm" extensions
then it assigns that keyword value to the source path variable.
The last keyword left in the string array becomes the destination path as specified by the user
in the command.
If source or destination path is not specified by the user then it throws
"Image file not provided exception".
Otherwise it calls the saveImage(sourcePath, targetName) method.

## brightenImage() Class

This class checks if the string array contains the word "brighten".
It then check if an integer value is passed in the command. This integer value will become the
increment factor to brighten the image.
Then it tries to get the initial image from the imageMap to perform the brighten
operation on this image.
If it is able to get the image from the map then the keyword becomes the source and
the last keyword left will become the target filename.
It throws the illegal argument exception if the increment factor is less than 0
or the inital image is null.
Otherwise, it puts the target Image name in the image map.

## darkenImage() class

This class checks if the string array contains the word "darken".
It then check if an integer value is passed in the command. This integer value will become the
decrement factor to darken the image.
Then it tries to get the initial image from the imageMap to perform the darken
operation on this image.
If it is able to get the image from the map then the keyword becomes the source and
the last keyword left will become the target filename.
It throws the illegal argument exception if the decrement factor is less than 0
or the inital image is null.
Otherwise, it puts the target Image name in the image map.

## redComponent() Class

This class checks if the keyword "red-component" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial image that is
the source image path and the target image file name.
Then it calls the getRedComponent() method to get the red component of the initial image and stores
the result and target image name in the map.

## greenComponent() Class

This class checks if the keyword "green-component" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial image that is
the source image path and the target image file name.
Then it calls the getGreenComponent() method to get the green component of the initial image
and stores the result and target image name in the map.

## blueComponent() Class

This class checks if the keyword "blue-component" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial image that is
the source image path and the target image file name.
Then it calls the getBlueComponent() method to get the blue component of the initial image
and stores the result and target image name in the map.

## verticalFlip() Class

This class checks if the keyword "vertical-flip" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial image that is
the source image path and the target image file name.
Then it calls the flipVertical() method to get the vertically flipped image of the initial image
and stores the result and target image name in the map.

## horizontalFlip() Class

This class checks if the keyword "horizontal-flip" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial image that is
the source image path and the target image file name.
Then it calls the flipHorizontal() method to get the horizontally flipped image of the initial image
and stores the result and target image name in the map.

## rgbSplit() Class

This class checks if the keyword "rbg-split" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial image that is
the source image path.
It then checks the "red", "blue" and "green" keywords and then assigns the target image file names
based on these keywords.
Then it calls the rgbSplit() method to split the initial image into its red, green and blue
components and stores the results and target image names in the map respectively.
It throws an exception if the initial image that we have to split is null.

## rgbCombine() Class

This class checks if the keyword "rbg-combine" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial images
that is the source images for red, green and blue components by checking if the keywords
"red", "green" and "blue" are present in the filename respectively.
Then it calls the rgbCombine() method to combine the initial images a single image
and stores the result and target image name in the map respectively.
It throws an exception if any of the red, green or blue initial images that we have to combine
is null.

## blurImage() Class

This class checks if the keyword "blur" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial image that is
the source image path and the target image file name.
Then it calls the blurImage() method to get the blurred image of the initial image
and stores the result and target image name in the map.

## sharpenImage() Class

This class checks if the keyword "sharpen" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial image that is
the source image path and the target image file name.
Then it calls the sharpenImage() method to get the sharpened image of the initial image
and stores the result and target image name in the map.

## sepiaImage() Class

This class checks if the keyword "sepia" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial image that is
the source image path and the target image file name.
Then it calls the sepiaImage() method to get the sepia toned image of the initial image
and stores the result and target image name in the map.

## lumaImage() Class

This class checks if the keyword "luma-component" is present in the map via the extract function
that is explained below. It then traverses the extracted key value to get the initial image that is
the source image path and the target image file name.
Then it calls the convertLumaGrayScale() method to get the luma image of the initial image
and stores the result and target image name in the map.

## intensityImage() Class

This class checks if the keyword "intensity-component" is present in the map via the extract
function that is explained below. It then traverses the extracted key value to get the initial
image that is the source image path and the target image file name.
Then it calls the convertIntensityGrayScale() method to get the intensity toned image of the
initial image and stores the result and target image name in the map.

## valueImage() Class

This class checks if the keyword "value-component" is present in the map via the extract
function that is explained below. It then traverses the extracted key value to get the initial
image that is the source image path and the target image file name.
Then it calls the convertValueGrayScale() method to get the value toned image of the
initial image and stores the result and target image name in the map.

## Compression() Class

This class is used to compress the image. A number of helper methods have also been created to
compress the image. First the image is padded, then transformed. Compression is then applied on the
transformed image. Then it is inverse transformed, unpadded and stored in the result. Compression
reduces the size of the resultant image.

## Histogram() Class

This class is used to create a histogram of the given image. Some helper functions have also been
created to create the histogram by measuring its peak values, checking if it has single peak or
multiple peaks etc.

## ColorCorrection() Class

This class is used to color correct the given image. It requires first creating the histograms of
the given image's each channels and then modifying the peaks of each channel in such a way that they
coincide at a same point.

## SplitView() Class

This class is used to apply operations on a single image and split it.
The percentage of the split can be defined by the user. For example: 60% image should be split
to convert that part to blur and rest of the 40% of image should be same.
A number of helper methods have been created to perform the similar operations on the image.

## AdjustLevels() Class

This class is used to adjust the levels of given image and save the output image referred to
henceforth by the given destination name.

# GUICommands Folder

This folder consists of different GUI command classes which are called when the
buttons from the Jframe are clicked.

## load() Class

This class opens the dialog box for loading an image with help of view and then
when an image is selected it is loaded in the model.

## save() Class

This class opens the dialog box for saving an image with help of view and then
when an image name is given the latest edited image is being saved to the
desired destination.

## brightenImage() Class

This class brightens the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## darkenImage() class

This class darkens the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## redComponent() Class

This class extracts the red component of the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## greenComponent() Class

This class extracts the green component of the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## blueComponent() Class

This class extracts the blue component of the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## verticalFlip() Class

This class vertically flips the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## horizontalFlip() Class

This class horizontally flips the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## blurImage() Class

This class blurs the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## sharpenImage() Class

This class sharpens the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## sepiaImage() Class

This class speia tones the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## lumaImage() Class

This class applies luma greyscale to the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## intensityImage() Class

This class applies intensity greyscale to the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## valueImage() Class

This class applies value greyscale to the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## Compression() Class

This class compresses the latest image with a specific compression percentage
and stores it inside the image map present in the model.
It also displays the edited image on the right side of the graphical interface with its new
histogram.

## Histogram() Class

This class is being called after every-operation because we need to display histogram of
the image after each operation.

## ColorCorrection() Class

This class color corrects the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

## SplitView() Class

This class is used to apply operations on a single image and split it.
The percentage of the split can be defined by the user. For example: 60% image should be split
to convert that part to blur and rest of the 40% of image should be same.

## AdjustLevels() Class

This class adjusting level operations to the latest image and stores it inside the image map
present in the model. It also displays the edited image on the right side
of the graphical interface with its new histogram.

# ImageCommands Interface

This interface contains the following functions to perform operations on the source image:
getRedComponent(), getGreenComponent(), getBlueComponent(), flipHorizontal(), flipVertical(),
brighten(int brightenFactor), darken(int darkenFactor), blurImage(), sharpenImage(),
convertLumaGrayScale(), convertValueGrayScale(), convertIntensityGrayScale(), convertSepiaTone(),
rgbSplit(), rgbCombine(ArrayList<Image> images).

# GUIImageCommands Interface

This is implemented by methods there in GUICommands package

## execute method

It has a method called execute which takes in an ImageModel object as an input.

# Features Interface

This is implemented by out controller and it has all the call back functions
which has being called from the view once buttons are pressed

## loadImage method

Callback function for loading an image from a file.

## saveImage method

Callback function for saving the current image.

## redComponent method

Callback function for applying the red component filter to the image.

## blueComponent method

Callback function for applying the blue component filter to the image.

## greenComponent method

Callback function for applying the green component filter to the image.

## blurComponent method

Callback function for applying a blur effect to the image.

## lumaComponent method

Callback function for converting the image to luma greyscale.

## intensityComponent method

Callback function for converting the image to intensity greyscale.

## valueComponent method

Callback function for converting the image to value greyscale.

## flipHorizontal method

Callback function for flipping the image horizontally.

## flipVertical method

Callback function for flipping the image vertically.

## sepiaTone method

Callback function for applying a sepia tone effect to the image.

## splitView method

Callback function for splitting the view of the image based on the provided percentage.

## brighten method

Callback function for adjusting the brightness of the image.

## darken method

Callback function for adjusting the darkness of the image.

## sharpen method

Callback function for sharpening the image.

## colorCorrection method

Callback function for correcting the color balance of the image.

## compression method

Callback function for applying compression to the image based on the provided percentage.

## adjustLevels method

Callback function for adjusting the levels of the image based on specified black, mid, and white
points.

## showHistogram method

Callback function for displaying the histogram of the image.

# MODEL

# ImageModel interface

This interface contains the following functions to perform operations on the source image:
getRedComponent(), getGreenComponent(), getBlueComponent(), flipHorizontal(), flipVertical(),
brighten(int brightenFactor), darken(int darkenFactor), blurImage(), sharpenImage(),
convertLumaGrayScale(), convertValueGrayScale(), convertIntensityGrayScale(), convertSepiaTone(),
rgbSplit(), rgbCombine(ArrayList<Image> images).

# Image Class

This class implements the ImageModel interface.

## image() (image Class Constructor)

This constructor takes in the row, column and an array of pixels values.

## applyOperationToAllPixels(Function<Pixel, Pixel> operation) Method

This method creates a new image, applies the operations on the pixel values in the image and
returns the resultant image.

## getRedComponent() Method

It calls applyOperationToAllPixels method, gets the red component of the image and marks the green
and blue components as 0. Then it returns the resultant image.

## getGreenComponent() Method

It calls applyOperationToAllPixels method, gets the green component of the image and marks the red
and blue components as 0. Then it returns the resultant image.

## getBlueComponent() Method

It calls applyOperationToAllPixels method, gets the blue component of the image and marks the green
and red components as 0. Then it returns the resultant image.

## flipHorizontal() Method

This method creates a new image. It then flips each pixel of the new image horizontally by
reversing the x component of the pixel. The reversed x component becomes this.row - x - 1.
It assigns the pixel value by extracting the red, blue, green components of the original image
and returns the result.

## flipVertical() Method

This method creates a new image. It then flips each pixel of the new image vertically by
reversing the y component of the pixel. The reversed y component becomes this.col - y - 1.
It assigns the pixel value by extracting the red, blue, green components of the original image
and returns the result.

## brighten(int brightenFactor) Method

This method adds the brightenFactor to the red, blue and green pixel components of the original
image by calling applyOperationToAllPixels function and returns the resultant image.

## darken(int darkenFactor) Method

This method adds the darkenFactor to the red, blue and green pixel components of the original
image by calling applyOperationToAllPixels function and returns the resultant image.

## blurImage() Method

This method applies the blurring filter on the image by calling the applyFilter(filter) function
and returns rhe result.

## sharpenImage() Method

This method applies the sharpening filter on the image by calling the applyFilter(filter) function
and returns rhe result.

## applyFilter(double[][] filter) Method

This method computes the row and column values of the image object, creates a new image using these
row and column values. It then stores the row and column length of the filter. Then it applies the
filter on each of the pixel values of the image. It finds product of the image and filter pixel
values on the x and y indexes and adds them. In case no pixel is present on the x and y indexes
then it does not compute the product result for that particular pixel. The function calculates the
red, green and blue components of the pixels in this manner and replaces the corresponding values
in the resultant image. The resultant image is then returned from the function once all the
computation is done.

## convertLumaGrayScale() Method

This method calls and applies the luma() function which is present in the pixel class using
applyOperationToAllPixels method and returns the resultant image.

## convertValueGrayScale() Method

This method calculates the max value among the red, blue, green pixel components using the
maxValue() function which is present in the pixel class and applies it on the image using
applyOperationToAllPixels method and returns the resultant image.

## convertIntensityGrayScale() Method

This method calculates the sepia tone of the red, blue, green pixel components using the
intensity() function which is present in the pixel class and applies it on the image using
applyOperationToAllPixels method and returns the resultant image.

## convertSepiaTone() Method

This method calculates the intensity value of the red, blue, green pixel components using the
getSepia() function which is present in the pixel class and applies it on the image using
applyOperationToAllPixels method and returns the resultant image.

## rgbSplit() Method

This method splits the original image into its respective red, blue and green component images by
getting their corresponding red, blue and green components using getRed(), getBlue() and getGreen()
methods respectively and marking the other color components as 0.
Then it returns the resultant image.

## rgbCombine(ArrayList<Image> images) Method

It consists of an arraylist of images in the parameter. It first checks if this array list is empty.
If the list is empty then it throws the exception. Otherwise, it gets the row and column length of
the reference image. If the row and column length of the red, blue and green component images does
not match with that of the reference image then it throws an exception for invalid dimensions. Else,
it gets the individual pixel values of the red, blue and green image components, maps it into one
single value, assigns this value to the resultant image pixel. It computes the value for all the
resultant image pixels in the same manner and returns the resultant image formed.

## Compression() Method

This method is used to compress the image. A number of helper methods have also been created to
compress the image. First the image is padded, then transformed. Compression is then applied on the
transformed image. Then it is inverse transformed, unpadded and stored in the result. Compression
reduces the size of the resultant image.

## Histogram() Method

This method is used to create a histogram of the given image. Some helper functions have also been
created to create the histogram by measuring its peak values, checking if it has single peak or
multiple peaks etc.

## ColorCorrection() Method

This method is used to color correct the given image. It requires first creating the histograms of
the given image's each channels and then modifying the peaks of each channel in such a way that they
coincide at a same point.

## SplitView() Class

This class is used to apply operations on a single image and split it.
The percentage of the split can be defined by the user. For example: 60% image should be split
to convert that part to blur and rest of the 40% of image should be same.
A number of helper methods have been created to perform the similar operations on the image.

## SplitViewGUI() Class

This class is for splitting operation only just the difference is that it is called when split is
used in GUI, and it saves the file with a specific name which is different from the method above.

## AdjustLevels() Method

This method is used to adjust the levels of given image and save the output image referred to
henceforth by the given destination name.

# ImageCompressionFunction Class

This class majorly performs the functionality to compress the image. Following are the methods used
in compression.

## ImageCompressionFunction() (ImageCompressionFunction constructor)

This is the constructor of the image compression class.

## transform2DHar()

This method is used to perform a haar transform on a 2D image.

## inverseTransform2DHar()

This method is used to perform an inverse of haar transform on a 2D image.

## transformAnArray()

This method is used to transform a 1D array by parsing it by length.

## transformHarRow()

This method is used to perform a haar transform on the rows of the array.

## inverseTransformHarRow()

This method is used to perform an inverse of the haar transform on the rows of the array.

## findThresholdValue()

This method is used to find the threshold values to perform the transform on an array.

## containsWithPrecision()

This method checks the precision of the values.

## afterThresholdingValues()

This method makes the values 0 if those values are less than the threshold value.

## afterUnpadInverse()

This method is used to inverse the image model after unpadding it.

## compressionBeforeThreshold()

This method is used to perform the compression on an image. It applies the 2d haar
transformation on the image.

## padImage()

This method is used to pad the image to make it as an nXn image.
Here n is a digit which is represented as raised to the power of 2.

## unpadImage()

This method is used to remove the padding of the image.

## imageToDoubleArray()

This method is used to convert the image model to an array.

## doubleArrayToImage()

This method is used to convert the 2d array into an image model.

# Pixel Class

This class consists of the red, blue and green components of the pixel and performs
operations on them. It has a class constructor and some other methods as follows.

## pixel() (pixel Class Constructor)

This is a constructor for the pixel class that takes the red, green and blue component
values of the pixel.

## getRed() Method

This method returns the red component of the pixel.

## getGreen() Method

This method returns the green component of the pixel.

## getBlue() Method

This method returns the blue component of the pixel.

## maxValue() Method

This method returns the max value among the red, green and blue components of the pixel.

## intensity() Method

This method returns the intensity of each pixel by computing the sum of the red, green and blue
components of the pixel and dividing the result by 3. It returns the integer value of the result.

## luma() Method

This method computes the luma value of the pixel by multiplying the red component value by 0.2126,
green component value by 0.7152 and blue component value by 0.0722, then computing the sum of these
values and returning the result in integer form.

## getSepia() Method

This method is used to get the sepia tone of the image. It computes the sepia tone of each red,
blue and green pixel, converts the result to integer and returns the new pixel created using these
values.

# VIEW

This package displays the resultant images to the user.

# IView Interface

This is the interface that our view implements and it has all the
methods being called from outside of the view by the controller.

## Methods

## showOriginalImage(BufferedImage image)

This method displays the original image in the graphical interface.

## showOutputImage(BufferedImage image)

This method displays the output image in the graphical interface.

## showHistogramRecent(BufferedImage image)

This method displays the histogram of the recent image in the graphical interface.

## popErrorMessage(String error)

This method displays an error message to the user.

## showProcessingIndicator()

This method shows a processing indicator to indicate that an operation is in progress.

## hideProcessingIndicator()

This method hides the processing indicator.

## addFeatures(Features feature)

This method adds features to the graphical interface.

## toggleSplitButtonColor()

This method toggles the color of the split button in the graphical interface.

## getSplitViewToggleStatus()

This method retrieves the current status of the split view toggle.

## clearOriginalImage()

This method clears the display of the original image in the graphical interface.

## clearOutputImage()

This method clears the display of the output image in the graphical interface.

## loadDialogBox()

This method opens a dialog box for loading an image and returns the selected image path.

## saveDialogBox()

This method opens a dialog box for saving an image and returns the specified destination path.

These methods provide the necessary functionality for interacting with the graphical
interface and managing image display and operations.

# JFrameView

## showOriginalImage method

Displays the provided original image on the GUI.

## showOutputImage method

Displays the provided output image on the GUI.

## showHistogramRecent method

Displays the provided histogram image on the GUI.

## showImage method

Helper method to set and display images on a JLabel.

## setGrayBorder method

Sets a gray border around the specified JLabel.

## popErrorMessage method

Displays an error message dialog box with the given error message.

## toggleSplitButtonColor method

Toggles the color of the "split-view" button between red and black.

## getSplitViewToggleStatus method

Returns the current toggle status of the "split-view" button.

## clearOriginalImage method

Clears the original image display on the GUI.

## clearOutputImage method

Clears the output image display on the GUI.

## isFileAllowed method

Checks if the provided file path has an allowed image file extension.

## loadDialogBox method

Displays a file chooser dialog for loading images and returns the selected file path.

## saveDialogBox method

Displays a file chooser dialog for saving images and returns the selected file path.

## showProcessingIndicator method

Displays a dialog with a loading animation to indicate image processing.

## hideProcessingIndicator method

Hides the processing indicator dialog.

## addFeatures method

Associates the GUI buttons with corresponding image processing features.

## setPlaceholderText method

Sets placeholder text and formatting for input text fields.

# HOW TO RUN THE COMMANDS WHEN OPENED IN INTERACTIVE TEXT MODE USING (java -jar Program.jar -text)

Loading, manipulating and saving the images is done using simple text-based commands.
Here is a list of example test commands that should be supported. Syntax is as follows:

load image-path image-name: Load an image from the specified path and refer it to henceforth
in the program by the given image name.

save image-path image-name: Save the image with the given name to the specified path which
should include the name of the file.

red-component image-name dest-image-name: Create an image with the red-component of the image
with the given name, and refer to it henceforth in the program by the given destination name.
Similar commands for green, blue, value, luma, intensity components is supported.

horizontal-flip image-name dest-image-name: Flip an image horizontally to create a new image,
referred to henceforth by the given destination name.

brighten increment image-name dest-image-name: brighten the image by the given increment
to create a new image, referred to henceforth by the given destination name.
The increment may be positive (brightening) or negative (darkening).

rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue:
split the given image into three images containing its red, green and blue components respectively.
These would be the same images that would be individually produced with the red-component,
green-component and blue-component commands.

rgb-combine image-name red-image green-image blue-image: Combine the three images that are
individually red, green and blue into a single image that gets its red, green and blue components
from the three images respectively.

compress threshold image-name dest-image-name: compress the image by the given threshold
to create a new image, referred to henceforth by the given destination name.
color-correct image-name dest-image-name: color-correct the image to create a new image, referred
to henceforth by the given destination name.
histogram image-name dest-image-name: create a histogram of the given image, referred
to henceforth by the given destination name.
levels-adjust level1 level2 level3 image-name dest-image-name: Adjust the levels of given image and
save the output image referred to henceforth by the given destination name.
operation image-name dest-image-name split p: split the image by p% to perform different operation
on one half and rest of the image remains same on other half, and save the output image referred to
henceforth by the given destination name.

run script-file: Load and run the script commands in the specified file.

# HOW TO RUN THE COMMANDS USING THE SCRIPT

Refer useme.md file to understanding the working of the scripts.
The working directory path should be set to res folder for the entire project to run.