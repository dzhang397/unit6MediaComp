import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List
import java.lang.Math;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setGreen(255 - pixelObj.getGreen());
        pixelObj.setBlue(255 - pixelObj.getBlue());
      }
    }
  }
  
  public void greyscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int average = (pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen())/3;
        pixelObj.setRed(average);
        pixelObj.setGreen(average);
        pixelObj.setBlue(average);
      }
    }
  }
  
  public void edgeDetection(int dist)
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel pixel1 = null;
    Pixel pixel2 = null;
    
    for (int row = 0; row < pixels.length - 1; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        pixel1 = pixels[row][col];
        pixel2 = pixels[row+1][col];
      
        //int average1 = (pixel1.getRed() + pixel1.getBlue() + pixel1.getGreen())/3;
        //int average2 = (pixel2.getRed() + pixel2.getBlue() + pixel2.getGreen())/3;
        if(pixel1.colorDistance(pixel2.getColor()) > dist)
        {
            pixel1.setRed(255);
            pixel1.setBlue(255);
            pixel1.setGreen(255);
        }
        else if(pixel1.colorDistance(pixel2.getColor()) > dist - 10)
        {
            pixel1.setRed(160);
            pixel1.setBlue(160);
            pixel1.setGreen(160);
        }
        else
        {
            pixel1.setRed(0);
            pixel1.setBlue(0);
            pixel1.setGreen(0);
        }
    }
  }
}
  public void fixUnderwater2()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen((int)((pixelObj.getGreen() - 130)*2.5));
        pixelObj.setBlue((int)((pixelObj.getBlue() - 135) * 3.7)); 
        pixelObj.setRed((int)(pixelObj.getRed() * 2));
      }
    }
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
          if(pixelObj.getBlue() < 80 && pixelObj.getRed() > 40 && pixelObj.getGreen() < 105 && pixelObj.getGreen() > 70)
          {
              pixelObj.setGreen(0);
              pixelObj.setBlue(0); 
              pixelObj.setRed(0);
          }
          else
          {
              pixelObj.setGreen(255);
              pixelObj.setBlue(255); 
              pixelObj.setRed(255);
          }
      }
    }
  }

  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen((int)((pixelObj.getGreen() - 130)*2.5));
        pixelObj.setBlue((int)((pixelObj.getBlue() - 135) * 3.7)); 
        pixelObj.setRed((int)(pixelObj.getRed() * 2));
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from right to left */
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = width - 1; col >= width / 2; col--)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }            
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    */
  public void mirrorHorizontalTopToBottom()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length / 2; row++)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length - 1 - row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    */
  public void mirrorHorizontalBottomToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int row = pixels.length - 1; row >= pixels.length / 2; row--)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length - 1 - row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel initialPixel = null;
    Pixel finalPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < Math.min(width, pixels.length); row++)
    { 
      for(int col = 0; col < row; col++)
      {
          initialPixel = pixels[row][col];
          finalPixel = pixels[col][row];
          finalPixel.setColor(initialPixel.getColor());
        }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  public void mirrorGull()
  {
    int mirrorPoint = 350;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 230; row < 330; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 230; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [2*mirrorPoint - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  public void mirrorLeftArms()
  {
    int mirrorPoint = 276;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 160; row < 190; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 100; col < 170; col++)
      {
        
        topPixel = pixels[row][col];      
        bottomPixel = pixels[pixels.length-row+80][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }  
    }
  public void mirrorRightArms()
  {
    int mirrorPoint = 276;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 170; row < 200; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 240; col < 300; col++)
      {
        
        topPixel = pixels[row][col];      
        bottomPixel = pixels[pixels.length-row+85][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }  
    }
   
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  public void cropAndCopy( Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol,
         int startDestRow, int startDestCol )
  {
    Pixel sourcePixel = null;
    Pixel destinationPixel = null;
    Pixel[][] destinationPixels = this.getPixels2D();
    Pixel[][] sourcePixels = sourcePicture.getPixels2D();
    for (int sourceRow = startSourceRow, destinationRow = startDestRow; 
        sourceRow < endSourceRow && destinationRow < destinationPixels.length;
        sourceRow++, destinationRow++)
    {
      for (int sourceCol = startSourceCol, destinationCol = startDestCol; 
          sourceCol < endSourceCol && destinationCol < destinationPixels[0].length;
          sourceCol++, destinationCol++)
      {
        sourcePixel = sourcePixels[sourceRow][sourceCol];
        destinationPixel = destinationPixels[destinationRow][destinationCol];
        destinationPixel.setColor(sourcePixel.getColor());
      }
    }   
  }
  
  void scaleByHalf( Picture sourcePicture)
  {
    Pixel pixel1 = null;
    Pixel pixel2 = null;
    Pixel[][] Pixels = this.getPixels2D();
    Pixel[][] sourcePixels = sourcePicture.getPixels2D();
    for (int sourceRow = 0, destRow = 0;
        sourceRow < sourcePixels.length;
        sourceRow +=2, destRow++)
    {
        for(int sourceCol = 0, destCol = 0; sourceCol < sourcePixels[0].length; sourceCol += 2, destCol++)
        {
            pixel1 = sourcePixels[sourceRow][sourceCol];
            pixel2 = Pixels[destRow][destCol];
            
            pixel2.setColor(pixel1.getColor());           
        }
    }
}

  void scale( Picture sourcePicture, int scaleAmt)
  {
    Pixel pixel1 = null;
    Pixel pixel2 = null;
    Pixel[][] Pixels = this.getPixels2D();
    Pixel[][] sourcePixels = sourcePicture.getPixels2D();
    for (int sourceRow = 0, destRow = 0;
        sourceRow < sourcePixels.length;
        sourceRow +=2, destRow++)
    {
        for(int sourceCol = 0, destCol = 0; sourceCol < sourcePixels[0].length; sourceCol += 2, destCol++)
        {
            pixel1 = sourcePixels[sourceRow][sourceCol];
            pixel2 = Pixels[destRow][destCol];
            
            pixel2.setColor(pixel1.getColor());           
        }
    }
}
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection1(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  public void fade(double amt)
  {
      Pixel[][] pixels = this.getPixels2D();
      
      double fade = 1;
      for(int row = 20; row > 0; row--)
      {
          for (int col = 0; col < pixels[0].length - 1; col++)
          {
              Pixel newPixel = pixels[row][col];
              newPixel.setRed((int)(newPixel.getRed() * fade));
              newPixel.setGreen((int)(newPixel.getGreen() * fade));
              newPixel.setBlue((int)(newPixel.getBlue() * fade));
            }
          fade += amt;
      }
      
      fade = 1;
      for(int row = pixels.length - 20; row < pixels.length - 1; row++)
      {
          for (int col = 0; col < pixels[0].length - 1; col++)
          {
              Pixel newPixel = pixels[row][col];
              newPixel.setRed((int)(newPixel.getRed() * fade));
              newPixel.setGreen((int)(newPixel.getGreen() * fade));
              newPixel.setBlue((int)(newPixel.getBlue() * fade));
            }
          fade += amt;
        }
        
      fade = 1;
      for(int col = 20; col > 0; col--)
      {
          for (int row = 0; row < pixels.length - 1; row++)
          {
              Pixel newPixel = pixels[row][col];
              newPixel.setRed((int)(newPixel.getRed() * fade));
              newPixel.setGreen((int)(newPixel.getGreen() * fade));
              newPixel.setBlue((int)(newPixel.getBlue() * fade));
            }
          fade += amt;
      }
        
      fade = 1;
      for(int col = pixels[0].length - 20; col < pixels[0].length - 1; col++)
      {
          for (int row = 0; row < pixels.length - 1; row++)
          {
              Pixel newPixel = pixels[row][col];
              newPixel.setRed((int)(newPixel.getRed() * fade));
              newPixel.setGreen((int)(newPixel.getGreen() * fade));
              newPixel.setBlue((int)(newPixel.getBlue() * fade));
            }
          fade += amt;
      }
    }
     
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
    /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture M1 = new Picture("Maxson.jpg");
    Picture M2 = new Picture("MaxsonBoatSmall.jpg");
    Picture M3 = new Picture("MaxsonSmall.jpg");
    Picture M4 = new Picture("MaxsonHead.jpg");
    Picture M5 = new Picture("MaxsonGolf.jpg");
    
    Pixel[][] sourcePixels1 = M1.getPixels2D();
    Pixel[][] sourcePixels2 = M2.getPixels2D();
    Pixel[][] sourcePixels3 = M3.getPixels2D();
    Pixel[][] sourcePixels4 = M4.getPixels2D();
    Pixel[][] sourcePixels5 = M5.getPixels2D();
                    
    M1.greyscale();
    M1.fade(.03);
    M1.mirrorHorizontalTopToBottom();
    this.cropAndCopy(M1, 0, sourcePixels1.length, 0, sourcePixels1[0].length, 54, 54);
    
    M5.mirrorHorizontalTopToBottom();
    M5.fade(.01);
    this.cropAndCopy(M5, 0, sourcePixels5.length, 0, sourcePixels5[0].length, 54, (1200 - sourcePixels5[0].length)/2);
    
    M4.keepOnlyBlue();
    M4.fade(.15);
    this.cropAndCopy(M4, 0, sourcePixels4.length, 0, sourcePixels4[0].length, 800 - 54 - sourcePixels4.length, 54);
    this.cropAndCopy(M4, 0, sourcePixels4.length, 0, sourcePixels4[0].length, 54, 54);
    
    for(int row = 0; row < 800; row += 54)
    {
        this.cropAndCopy(M2, 0, 54, 0, 54, row, 0);
        this.cropAndCopy(M2, 0, 54, 0, 54, row, 1146);
    }
     for(int col = 0; col < 1200; col += 54)
    {
        this.cropAndCopy(M2, 0, 54, 0, 54, 0, col);
        this.cropAndCopy(M2, 0, 54, 0, 54, 746, col);
    }
    
    this.mirrorHorizontalTopToBottom();
    
    Picture M3Small = new Picture(M3);
    M3Small.scaleByHalf(M3);
    this.cropAndCopy(M3Small, 0, sourcePixels3.length/2, 0, sourcePixels3[0].length/2, 500 - sourcePixels3.length/2, 600 - sourcePixels5[0].length/2);
    
    this.mirrorVertical();
    
    this.write("collage.jpg");
  }
} // this } is the end of class Picture, put all new methods before this
