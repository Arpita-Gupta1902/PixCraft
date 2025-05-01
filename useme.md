## When you normally run the file in IDE,in terminal or using "java -jar Program.jar", its default way of running is using GUI

Using this GUI is pretty self-explanatory. It has all the buttons which has all the functionality
and
shows previous edited image on left and the current edited on right side with histogram in the
next row. Also, there is a toggle option for splitview with changing color feature.

## When you run the program using JAR file in this form "java -jar Program.jar -text", then the program runs in interactive text mode, allowing the user to type the script and execute it one line at a time

### Functionality and syntax to run the interactive text mode program.

The working directory path should be set to res folder for the entire project to run.
Name of the operations and their functionality:

1. load - Loads an image into the editor.
2. save - Saves the current edited image.
3. brighten - Increases the brightness of the image.
4. darken - Decreases the brightness of the image.
5. red-component - Isolates and displays the red component of the image.
6. green-component - Isolates and displays the green component of the image.
7. blue-component - Isolates and displays the blue component of the image.
8. vertical-flip - Flips the image vertically.
9. horizontal-flip - Flips the image horizontally.
9. rgb-split - Splits the RGB channels of the image for individual manipulation.
10. rgb-combine - Combines the previously split RGB channels back into a full-color image.
11. blur - Applies a blur effect to the image.
12. sharpen - Sharpens the details in the image.
13. sepia - Applies a sepia tone to the image for a vintage look.
14. luma-component - Isolates and displays the luma (brightness) component of the image.
15. intensity-component - Isolates and displays the intensity component of the image.
16. value-component - Isolates and displays the value component of the image.
17. run - Executes a series of predefined operations on the image.
18. color-correct - Corrects the color balance in the image.
19. levels-adjust - Adjusts the levels of the image for optimal contrast.
20. histogram - Generates a histogram for the image.
21. compress - Compresses the image file size.
22. split p - Provides a split view of the edited and original image for comparison.
23. jar - Here, when the jar file is ran

The name of the operations should be entered exactly as mentioned above by the user or else
the program won't run or will give unexpected results since the program is case-sensitive.

The syntax of how the arguments and operation names should be placed while giving the input
commands to the program.

1. For all the operations mentioned below
    - save and load:
      The syntax would be {operation-name} image-path(with extension) image-name
      For example:
      load tree.jpeg tree
      save blur-tree.jpeg blur-tree

2. For all the operations mentioned below
    - red-component, green-component, blue-component,vertical-flip, horizontal-flip, blur, sharpen,
    - sepia, luma-component, intensity-component, value-component, color-correct, histogram:
      The syntax of these operations will be {operation-name} image-des target-image-name
      For example
      a. red-component tree red-tree
      b. blur tree blur-tree

3. For operations mentioned below
    - darken and brighten:
      The syntax of these operations will be darken/brighten increment image-name dest-image-name
      For example
      darken 20 tree dark-tree

4. For operation
    - rgb-split
      syntax is- rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue
      for example
      rgb-split tree red-tree blue-tree green-tree

5. For operation
    - rgb-combine
      syntax is rgb-combine image-name red-image green-image blue-image
      for example
      rgb-split tree red-tree blue-tree green-tree


6. For operation
    - rgb-split
      syntax is- rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue
      For example
      rgb-combine combined-tree red-tree green-tree blue-tree

7. For
    - levels-adjust
      Syntax is- levels-adjust b m w image-name dest-image-name
      for example, levels-adjust 20 100 255 tree tree-adjusted

8. For
    - compress
      Syntax is- compress percentage image-name dest-image-name
      For example, compress 30 tree compress-tree-30

9. For
    - run
      The syntax is- run file-path
      For example - run script.txt

10. For directly running without a run command:
    For example - script-splitview.txt

## If the Jar file is opened with this format "java -jar Program.jar -file path-of-script-file"

## then the entire script is executed

## All the program paths should be set to res folder for this project to work.

 


