package view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;


import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;

/**
 * This is a JFrameView class that implements IView interface.
 * It creates different buttons and displays the initial image, output image and histogram in the
 * view.
 */
public class JFrameView extends JFrame implements IView {
  private final JLabel originalImageLabel;
  private final JLabel outputImageLabel;
  private final JLabel histogram;
  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton redButton;
  private final JButton greenButton;
  private final JButton blueButton;
  private final JButton blurButton;
  private final JButton sepiaTone;
  private final JButton lumaButton;
  private final JButton valueGrayScaleButton;
  private final JButton intensityGrayScaleButton;
  private final JButton flipHorizontalButton;
  private final JButton flipVerticalButton;
  private final JButton splitView;
  private final JButton brighten;
  private final JButton darken;
  private final JButton sharpen;
  private final JButton colorCorrect;
  private final JButton adjustLevelsButton;
  private final JButton compression;
  private final JTextField splitTextField;
  private final JTextField brightenTextField;
  private final JTextField darkenTextField;
  private final JTextField compressionTextField;
  private final JTextField blackPointTextField;
  private final JTextField midPointTextField;
  private final JTextField whitePointTextField;
  boolean toggleSplit = false;
  private JDialog progressDialog;

  /**
   * This is a constructor of the JFrameView class.
   */
  public JFrameView(String caption) {
    super(caption);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);
    setLayout(new BorderLayout());
    JPanel buttonPanel = new JPanel(new GridLayout(3, 0));
    loadButton = new JButton("Load");
    buttonPanel.add(loadButton);
    saveButton = new JButton("Save");
    buttonPanel.add(saveButton);
    redButton = new JButton("red-component");
    buttonPanel.add(redButton);
    greenButton = new JButton("green-component");
    buttonPanel.add(greenButton);
    blueButton = new JButton("blue-component");
    buttonPanel.add(blueButton);
    blurButton = new JButton("blur");
    buttonPanel.add(blurButton);
    lumaButton = new JButton("luma-greyscale");
    buttonPanel.add(lumaButton);
    flipHorizontalButton = new JButton("flip-horizontal");
    buttonPanel.add(flipHorizontalButton);
    flipVerticalButton = new JButton("flip-vertical");
    buttonPanel.add(flipVerticalButton);
    intensityGrayScaleButton = new JButton("intensity-grayscale");
    buttonPanel.add(intensityGrayScaleButton);
    valueGrayScaleButton = new JButton("value-grayscale");
    buttonPanel.add(valueGrayScaleButton);
    sepiaTone = new JButton("sepia-tone");
    buttonPanel.add(sepiaTone);
    sharpen = new JButton("sharpen");
    buttonPanel.add(sharpen);
    colorCorrect = new JButton("Color-correct");
    buttonPanel.add(colorCorrect);
    splitView = new JButton(("split-view"));
    buttonPanel.add(splitView);
    splitTextField = new JTextField(5);
    buttonPanel.add(splitTextField);
    setPlaceholderText(splitTextField, "Enter split percentage");
    brighten = new JButton(("brighten"));
    buttonPanel.add(brighten);
    brightenTextField = new JTextField(5);
    buttonPanel.add(brightenTextField);
    setPlaceholderText(brightenTextField, "Enter brightening value");
    darken = new JButton(("darken"));
    buttonPanel.add(darken);
    darkenTextField = new JTextField(5);
    buttonPanel.add(darkenTextField);
    setPlaceholderText(darkenTextField, "Enter darkening value");
    compression = new JButton(("compression"));
    buttonPanel.add(compression);
    compressionTextField = new JTextField(5);
    buttonPanel.add(compressionTextField);
    setPlaceholderText(compressionTextField, "Enter compression percentage");
    adjustLevelsButton = new JButton("levelAdjust");
    buttonPanel.add(adjustLevelsButton);
    blackPointTextField = new JTextField(5);
    buttonPanel.add(blackPointTextField);
    setPlaceholderText(blackPointTextField, "Enter black-point value");
    midPointTextField = new JTextField(5);
    buttonPanel.add(midPointTextField);
    setPlaceholderText(midPointTextField, "Enter mid-point value");
    whitePointTextField = new JTextField(5);
    buttonPanel.add(whitePointTextField);
    setPlaceholderText(whitePointTextField, "Enter white-point value");
    add(buttonPanel, BorderLayout.NORTH);
    JPanel imagePanel = new JPanel();
    imagePanel.setLayout(new GridLayout(2, 2));
    originalImageLabel = createImageLabel("Original Image");
    JScrollPane originalImageScrollPane = new JScrollPane(originalImageLabel);
    imagePanel.add(originalImageScrollPane);
    outputImageLabel = createImageLabel("Output Image");
    JScrollPane outputImageScrollPane = new JScrollPane(outputImageLabel);
    imagePanel.add(outputImageScrollPane);
    histogram = createImageLabel("Histogram Image");
    JScrollPane histogramScrollPane = new JScrollPane(histogram);
    imagePanel.add(histogramScrollPane);
    add(imagePanel, BorderLayout.CENTER);
    pack();
    setVisible(true);
  }

  /**
   * Creates a JLabel for displaying an image with the given title.
   *
   * @param title The title of the image.
   * @return A JLabel for displaying the image.
   */
  private JLabel createImageLabel(String title) {
    JLabel imageLabel = new JLabel(title);
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
    return imageLabel;
  }

  /**
   * Displays the original image in the UI.
   *
   * @param image The original image.
   */
  @Override
  public void showOriginalImage(BufferedImage image) {
    showImage(originalImageLabel, image);
  }

  /**
   * Displays the output image in the UI.
   *
   * @param image The output image.
   */
  @Override
  public void showOutputImage(BufferedImage image) {
    showImage(outputImageLabel, image);
  }

  /**
   * Displays the recent histogram image in the UI.
   *
   * @param image The histogram image.
   */
  @Override
  public void showHistogramRecent(BufferedImage image) {
    showImage(histogram, image);
  }

  /**
   * Displays an image in the specified label.
   *
   * @param label The label to display the image.
   * @param image The image to be displayed.
   */
  private void showImage(JLabel label, BufferedImage image) {
    if (image != null) {
      ImageIcon icon = new ImageIcon(image.getScaledInstance(image.getWidth(), image.getHeight()
              , Image.SCALE_SMOOTH));
      label.setIcon(icon);
      label.setText(null);
      label.setBorder(null);
    } else {
      label.setIcon(null);
      label.setText("Please load the image");
      setGrayBorder(label);
    }
  }


  /**
   * Sets a gray border for the specified label.
   *
   * @param label The label for which the border is set.
   */
  private void setGrayBorder(JLabel label) {
    Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
    label.setBorder(border);
  }

  /**
   * Displays an error message in a pop-up dialog.
   *
   * @param error The error message to be displayed.
   */
  @Override
  public void popErrorMessage(String error) {
    JOptionPane.showMessageDialog(null, error, "Error"
            , JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Toggles the color of the split view button.
   */
  @Override
  public void toggleSplitButtonColor() {
    toggleSplit = !toggleSplit;
    if (toggleSplit) {
      splitView.setForeground(Color.red);
    } else {
      splitView.setForeground(Color.black);
    }
  }

  /**
   * Gets the toggle status of the split view.
   *
   * @return The toggle status of the split view.
   */
  @Override
  public boolean getSplitViewToggleStatus() {
    return toggleSplit;
  }

  /**
   * Clears the original image from the UI.
   */
  @Override
  public void clearOriginalImage() {
    originalImageLabel.setIcon(null);
    originalImageLabel.setText("Original Image");
  }

  /**
   * Clears the output image from the UI.
   */
  @Override
  public void clearOutputImage() {
    outputImageLabel.setIcon(null);
    outputImageLabel.setText("Output Image");
  }

  /**
   * Checks if the given file path has an allowed extension.
   *
   * @param filePath The file path to be checked.
   * @return True if the file has an allowed extension, otherwise false.
   */
  private boolean isFileAllowed(String filePath) {
    String[] allowedExtensions = {"png", "jpeg", "jpg", "ppm"};
    for (String extension : allowedExtensions) {
      if (filePath.toLowerCase().endsWith("." + extension)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Opens a file dialog to allow the user to select an image for loading.
   *
   * @return The file path of the selected image, or an empty string if no file is selected.
   */
  @Override
  public String loadDialogBox() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files"
            , "png", "jpeg", "jpg", "ppm"));

    int result = fileChooser.showOpenDialog(new Frame());
    if (result == JFileChooser.APPROVE_OPTION) {
      String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
      if (!isFileAllowed(selectedFilePath)) {
        System.err.println("Invalid file type. Please select an image file.");
      }
      return selectedFilePath;
    } else {
      return "";
    }
  }

  /**
   * Opens a file dialog to allow the user to select a location for saving an image.
   */
  @Override
  public String saveDialogBox() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("PNG, JPEG, JPG Images"
            , "png", "jpeg", "jpg"));
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
      if (!isFileAllowed(selectedFilePath)) {
        System.err.println("Invalid file type. Please select a valid image file format.");
      }
      return selectedFilePath;
    } else {
      return "";
    }
  }

  /**
   * Displays a processing indicator dialog during image processing.
   */
  @Override
  public void showProcessingIndicator() {
    progressDialog = new JDialog(this, "Processing...", true);
    progressDialog.setLayout(new BorderLayout());

    File gifFile = new File("Loading.gif");
    try {
      URL gifUrl = gifFile.toURI().toURL();
      ImageIcon loadingIcon = new ImageIcon(gifUrl);
      JLabel loadingLabel = new JLabel(loadingIcon);
      loadingLabel.setHorizontalAlignment(JLabel.CENTER);
      JLabel textLabel = new JLabel("Compression takes a few seconds");
      textLabel.setHorizontalAlignment(JLabel.CENTER);
      JPanel panel = new JPanel(new BorderLayout());
      panel.add(loadingLabel, BorderLayout.CENTER);
      panel.add(textLabel, BorderLayout.SOUTH);
      progressDialog.setUndecorated(true);
      progressDialog.setModal(true);
      progressDialog.setSize(350, 250);
      progressDialog.setLocationRelativeTo(this);
      progressDialog.add(panel, BorderLayout.CENTER);
      progressDialog.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Hides the processing indicator dialog.
   */
  @Override
  public void hideProcessingIndicator() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dispose();
    }
  }

  /**
   * Adds action listeners to the UI components for various image processing features.
   *
   * @param feature The Features object that handles the image processing operations.
   */
  @Override
  public void addFeatures(Features feature) {
    loadButton.addActionListener(evt -> {
      try {
        feature.loadImage();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    saveButton.addActionListener(evt -> {
      try {
        feature.saveImage();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    redButton.addActionListener(evt -> {
      try {
        feature.redComponent();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    greenButton.addActionListener(evt -> {
      try {
        feature.greenComponent();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    blueButton.addActionListener(evt -> {
      try {
        feature.blueComponent();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    blurButton.addActionListener(evt -> {
      try {
        feature.blurComponent();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    lumaButton.addActionListener(evt -> {
      try {
        feature.lumaComponent();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    intensityGrayScaleButton.addActionListener(evt -> {
      try {
        feature.intensityComponent();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    valueGrayScaleButton.addActionListener(evt -> {
      try {
        feature.valueComponent();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    flipHorizontalButton.addActionListener(evt -> {
      try {
        feature.flipHorizontal();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    flipVerticalButton.addActionListener(evt -> {
      try {
        feature.flipVertical();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    colorCorrect.addActionListener(evt -> {
      try {
        feature.colorCorrection();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    sharpen.addActionListener(evt -> {
      try {
        feature.sharpen();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    sepiaTone.addActionListener(evt -> {
      try {
        feature.sepiaTone();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    splitView.addActionListener(evt -> {
      try {
        feature.splitView(splitTextField.getText());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    brighten.addActionListener(evt -> {
      try {
        feature.brighten(brightenTextField.getText());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      brightenTextField.setText("");
    });

    darken.addActionListener(evt -> {
      try {
        feature.darken(darkenTextField.getText());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      darkenTextField.setText("");
    });

    compression.addActionListener(evt -> {
      try {
        feature.compression(compressionTextField.getText());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      compressionTextField.setText("");
    });

    adjustLevelsButton.addActionListener(evt -> {
      try {
        feature.adjustLevels(blackPointTextField.getText(), midPointTextField.getText()
                , whitePointTextField.getText());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      blackPointTextField.setText("");
      whitePointTextField.setText("");
      midPointTextField.setText("");
    });
  }

  /**
   * Sets a placeholder text in a JTextField with specific styling and behavior.
   *
   * @param textField   The JTextField for which to set the placeholder text.
   * @param placeholder The placeholder text to be set.
   */
  private void setPlaceholderText(JTextField textField, String placeholder) {
    Font placeholderFont = new Font(textField.getFont().getFamily(), Font.ITALIC, 10);
    textField.setText(placeholder);
    textField.setFont(placeholderFont);
    textField.setForeground(Color.GRAY);
    textField.setHorizontalAlignment(JTextField.CENTER);
    textField.addFocusListener(new FocusListener() {

      @Override
      public void focusGained(FocusEvent e) {
        if (textField.getText().equals(placeholder)) {
          textField.setText("");
          textField.setForeground(Color.BLACK);
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (textField.getText().isEmpty()) {
          textField.setText(placeholder);
          textField.setForeground(Color.GRAY);
        }
      }
    });
  }

}