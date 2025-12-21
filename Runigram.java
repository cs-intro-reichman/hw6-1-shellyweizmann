import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);


		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
		
		//// Write here whatever code you need in order to test your work.
		image = flippedVertically(tinypic);
		System.out.println();
		print(image);
		//// You can continue using the image array.
		
		// test function luminecense 
		Color yellow = new Color(255, 255, 0);
		Color grColor =  luminance(yellow);
		System.out.println("greyscalled");
		print(grColor);

		//Test function grayScaled
		System.out.println();
		System.out.println("testing function grayscaled");
		image = grayScaled(tinypic);
		print(image);

		/*System.out.println();
		System.out.println("testing function blend");
		image = blend(tinypic);
		print(image);*/
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		//System.out.println("rows and columbs: " + numRows + numCols);
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int i = 0; i < numRows; i++) {

        int[] place = new int[3];  
        int counter = 0;
        int col = 0;               

      		 for (int j = 0; j < numCols * 3; j++) {
           		int pixVal = in.readInt();
          		place[counter] = pixVal;
          		counter++;

          		if (counter == 3) {
              		  Color pixel = new Color(place[0], place[1], place[2]);
               		 image[i][col] = pixel;
             		   col++;             
              		  counter = 0;       
           	 }
      	  }
    	}
		return image;
	}	
	
	
		
	
	


    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}
	/*Implementation tip: To print Color values, use the given (overloaded) print(Color c) function. To
	print a 2D array of Color values, iterate through the array elements and print each element
	individually. Your output should be formatted exactly the same as the example shown above. */

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
		//System.out.println("image length: " + image.length + " colomb length: " + image[0].length);
		for(int i = 0; i < image.length; i++){
			for(int j = 0; j<image[0].length; j++){
				//System.out.println("i= " +i + " j= " + j);
			//print(image[i][j]);
			System.out.print("(");
			System.out.printf("%3s,", image[i][j].getRed());   
			System.out.printf("%3s,", image[i][j].getGreen()); 
        	System.out.printf("%3s",  image[i][j].getBlue()); 
			System.out.print(")  ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	/*Start the function’s code by creating a new image (a 2D array of Color objects)
	that has the same dimensions as the given image. Then fill the new image with the correct values.
	Finally, terminate the function’s code by returning this new image. These two tips apply to all every
	function in this class that returns a new image. */
	public static Color[][] flippedHorizontally(Color[][] image) {	
		Color[][] horiFlip = new Color[image.length][image[0].length];
		//System.out.println("horiFlip i and j: " + image.length + image[0].length);
		int numRows = image.length;
		int numCols = image[0].length;
		for(int i = 0; i < numRows; i++){
			for(int j=0; j < numCols; j++){
				horiFlip[i][j] = image [i][numCols-1-j];
			}
		}
		return horiFlip;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//System.out.println("flipped vertically");
		Color[][] vertiFlip = new Color[image.length][image[0].length];
		int numRows = image.length;
		int numCols = image[0].length;
		for(int j = 0; j < numCols; j++){
			for(int i=0; i < numRows; i++){
				vertiFlip[i][j] = image [numRows-1-i][j];
			}
		}
		return vertiFlip;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		//System.out.println("in luminence");
		//print(pixel);
		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();
		//System.out.println("r= " + r + "g= "+ g + "b= " + b);
		int greyFactor = (int)(0.299 * r + 0.587 * g + 0.114 * b);
		//System.out.println("number= " + greyFactor);
		Color grColor = new Color(greyFactor, greyFactor, greyFactor);

		return grColor;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		int numRows = image.length;
		int numCols = image[0].length;
		Color[][] grayImage = new Color[image.length][image[0].length];

		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				//System.out.println("i is= " + i + "j is= " + j);
				grayImage[i][j] = luminance(image[i][j]);
				
			}
		}
		//System.out.println("grayimage is /n");
		//print(grayImage);
		return grayImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		int ogWidth = image[0].length;
		int ogLength = image.length;
		Color[][] scaledImage = new Color[height][width];
		 
    	for (int i = 0; i < height; i++) {
        int scaleRow = i * ogLength / height;

        for (int j = 0; j < width; j++) {
            int scaleCol = j * ogWidth / width;

            scaledImage[i][j] = image[scaleRow][scaleCol];
        }
    }
		
		return scaledImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int r1 = c1.getRed();
    	int g1 = c1.getGreen();
   		int b1 = c1.getBlue();

  		int r2 = c2.getRed();
    	int g2 = c2.getGreen();
    	int b2 = c2.getBlue();

		int r = (int)(alpha * r1 + (1.0 - alpha) * r2);
    	int g = (int)(alpha * g1 + (1.0 - alpha) * g2);
    	int b = (int)(alpha * b1 + (1.0 - alpha) * b2);
		//System.out.println("blended values: " +r+g+b);

   		return new Color(r, g, b);
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		int length = image1.length;        
    	int width  = image1[0].length;     

    	Color[][] result = new Color[length][width];

    	for (int i = 0; i < length; i++) {
       		for (int j = 0; j < width; j++) {
            	result[i][j] = blend(image1[i][j], image2[i][j], alpha);
    		}
   		}	

    	return result;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		int sourceHeight = source.length;
    	int sourceWidth  = source[0].length;

    	if (target.length != sourceHeight || target[0].length != sourceWidth) {
        target = scaled(target, sourceWidth, sourceHeight);
    	}

   		if (n <= 0) {
        	Color[][] result = blend(source, target, 0.0); 
        	display(result);
       		return;
   		}

    	for (int i = 0; i <= n; i++) {
      		double alpha = (double) (n - i) / n;

       		Color[][] stepImage = blend(source, target, alpha);

        	display(stepImage);
        	StdDraw.pause(500);
    	}
	}	
	
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

