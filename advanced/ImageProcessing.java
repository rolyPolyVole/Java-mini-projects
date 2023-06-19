import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;
public class ImageProcessing {


	public static void main(String[] args) {
		//Initial image: mouse
		int[][] imageData = imgToTwoD("https://elitepestllc.com/wp-content/uploads/2016/06/mouse-1.jpg");

		//Spam image with rectangles
    	int[][] rectangledMouse = generateRectangles(imageData, 10);

		//Edit local html code
		twoDToImage(rectangledMouse, "./trimmed_apple.jpg");

		// int[][] allFilters = stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData), 50)), 200, 20, 40)));
	}




	// Image Processing Methods
	public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
		// Example Method
		if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
			int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
			for (int i = 0; i < trimmedImg.length; i++) {
				for (int j = 0; j < trimmedImg[i].length; j++) {
					trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
				}
			}
			return trimmedImg;
		} else {
			System.out.println("Cannot trim that many pixels from the given image.");
			return imageTwoD;
		}
	}
	public static int[][] negativeColor(int[][] imageTwoD) {
		int[][] negativeColorImage = new int[imageTwoD.length][imageTwoD[0].length];

    for (int i = 0; i < negativeColorImage.length; i++) {
      for (int j = 0; j < negativeColorImage[i].length; j++) {
        int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
        rgba[0] = 255 - rgba[0];
        rgba[1] = 255 - rgba[1];
        rgba[2] = 255 - rgba[2];
        negativeColorImage[i][j] = getColorIntValFromRGBA(rgba);
      }
    }
		return negativeColorImage;
	}
	public static int[][] stretchHorizontally(int[][] imageTwoD) {
		int[][] stretchedImage = new int[imageTwoD.length][imageTwoD[0].length * 2];
		
    for (int i = 0; i < imageTwoD.length; i++) {
      int columnIndex;
      for (int j = 0; j < imageTwoD[i].length; j++) {
        columnIndex = j * 2;
        stretchedImage[i][columnIndex] = imageTwoD[i][j];
        stretchedImage[i][columnIndex + 1] = imageTwoD[i][j];
      }
    }
    return stretchedImage;
	}
	public static int[][] shrinkVertically(int[][] imageTwoD) {
		int[][] shrankImage = new int[imageTwoD.length / 2][imageTwoD[0].length];

    for (int i = 0; i < imageTwoD[0].length; i++) {
      for (int j = 0; j < imageTwoD.length - 1; j+=2) {
        shrankImage[j / 2][i] = imageTwoD[j][i];
      }
    }

    return shrankImage;
	}
	public static int[][] invertImage(int[][] imageTwoD) {
		int[][] invertedImage = new int[imageTwoD.length][imageTwoD[0].length];

    for (int i = 0; i < imageTwoD.length; i++) {
      for (int j = 0; j < imageTwoD[i].length; j++) {
        invertedImage[i][j] = imageTwoD[imageTwoD.length - 1 - i][imageTwoD[0].length - 1 - j];
      }
    }
    
    return invertedImage;

	}
	public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
    int[][] newImage = new int[imageTwoD.length][imageTwoD[0].length];
    int[] changeValues = {redChangeValue, greenChangeValue, blueChangeValue, 0};

		for (int i = 0; i < imageTwoD.length; i++) {
      for (int j = 0; j < imageTwoD[i].length; j++) {
        int[] RGBAValues = getRGBAFromPixel(imageTwoD[i][j]);

        for (int index = 0; index < RGBAValues.length; index++) {
          RGBAValues[index] += changeValues[index];
        }

        for (int index = 0; index < RGBAValues.length; index++) {
          if (RGBAValues[index] > 255) RGBAValues[index] = 255; 
          else if (RGBAValues[index] < 0) RGBAValues[index] = 0;
          else continue;
        }

        newImage[i][j] = getColorIntValFromRGBA(RGBAValues);
      }
    }
    return newImage;
	}
	// Painting Methods
	public static int[][] paintRandomImage(int[][] canvas) {
		Random rand = new Random();

    for (int i = 0; i < canvas.length; i++) {
      for (int j = 0; j < canvas[i].length; j++) {
        int[] randomValues = {rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), 255};
        canvas[i][j] = getColorIntValFromRGBA(randomValues);
      }
    }
    return canvas;
	}
	public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
		for (int i = 0; i < canvas.length; i++) {
      for (int j = 0; j < canvas[i].length; j++) {
        if (i > rowPosition && i <= rowPosition + width && j >= colPosition && j <= colPosition + height) {
          canvas[i][j] = color;
        }
      }
    }
    return canvas;
	}
	public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
		Random rand = new Random();

    for (int i = 0; i <= numRectangles; i++) {
      int width = rand.nextInt(canvas[0].length);
      int height = rand.nextInt(canvas.length);

      int rowPosition = canvas.length - height;
      int columnPosition = canvas[0].length - width;

      int color = getColorIntValFromRGBA(new int[] {rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 255});

      canvas = paintRectangle(canvas, width, height, rowPosition, columnPosition, color);
    }
    return canvas;
	}
	// Utility Methods
	public static int[][] imgToTwoD(String inputFileOrLink) {
		try {
			BufferedImage image = null;
			if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
				URL imageUrl = new URL(inputFileOrLink);
				image = ImageIO.read(imageUrl);
				if (image == null) {
					System.out.println("Failed to get image from provided URL.");
				}
			} else {
				image = ImageIO.read(new File(inputFileOrLink));
			}
			int imgRows = image.getHeight();
			int imgCols = image.getWidth();
			int[][] pixelData = new int[imgRows][imgCols];
			for (int i = 0; i < imgRows; i++) {
				for (int j = 0; j < imgCols; j++) {
					pixelData[i][j] = image.getRGB(j, i);
				}
			}
			return pixelData;
		} catch (Exception e) {
			System.out.println("Failed to load image: " + e.getLocalizedMessage());
			return null;
		}
	}
	public static void twoDToImage(int[][] imgData, String fileName) {
		try {
			int imgRows = imgData.length;
			int imgCols = imgData[0].length;
			BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < imgRows; i++) {
				for (int j = 0; j < imgCols; j++) {
					result.setRGB(j, i, imgData[i][j]);
				}
			}
			File output = new File(fileName);
			ImageIO.write(result, "jpg", output);
		} catch (Exception e) {
			System.out.println("Failed to save image: " + e.getLocalizedMessage());
		}
	}
	public static int[] getRGBAFromPixel(int pixelColorValue) {
		Color pixelColor = new Color(pixelColorValue);
		return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
	}
	public static int getColorIntValFromRGBA(int[] colorData) {
		if (colorData.length == 4) {
			Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
			return color.getRGB();
		} else {
			System.out.println("Incorrect number of elements in RGBA array.");
			return -1;
		}
	}
	public static void viewImageData(int[][] imageTwoD) {
		if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
			int[][] rawPixels = new int[3][3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					rawPixels[i][j] = imageTwoD[i][j];
				}
			}
			System.out.println("Raw pixel data from the top left corner.");
			System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
			int[][][] rgbPixels = new int[3][3][4];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
				}
			}
			System.out.println();
			System.out.println("Extracted RGBA pixel data from top the left corner.");
			for (int[][] row : rgbPixels) {
				System.out.print(Arrays.deepToString(row) + System.lineSeparator());
			}
		} else {
			System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
		}
	}
}