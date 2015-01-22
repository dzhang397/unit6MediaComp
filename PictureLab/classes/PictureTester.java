/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("Maxson1.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  /** Method to test mirrorVerticalRightToLeft */
  public static void testMirrorVerticalRightToLeft()
  {
    Picture caterpillar = new Picture("Maxson1.jpg");
    caterpillar.explore();
    caterpillar.mirrorVerticalRightToLeft();
    caterpillar.explore();
  }
  /** Method to test mirrorHorizontalTopToBottom */
  public static void testMirrorHorizontalTopToBottom()
  {
    Picture caterpillar = new Picture("Maxson1.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontalTopToBottom();
    caterpillar.explore();
  }
  /** Method to test mirrorHorizontalBottomToTop */
  public static void testMirrorHorizontalBottomToTop()
  {
    Picture caterpillar = new Picture("Maxson1.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontalBottomToTop();
    caterpillar.explore();
  }
  /** Method to test mirrorHorizontalBottomToTop */
  public static void testMirrorDiagonal()
  {
    Picture caterpillar = new Picture("Maxson1.jpg");
    caterpillar.explore();
    caterpillar.mirrorDiagonal();
    caterpillar.explore();
  }
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  public static void testMirrorGull()
  {
    Picture test = new Picture("seagull.jpg");
    test.explore();
    test.mirrorGull();
    test.explore();
  }
  public static void testKeepOnlyBlue()
  {
    Picture test = new Picture("Maxson1.jpg");
    test.explore();
    test.keepOnlyBlue();
    test.explore();
  }
  public static void testNegate()
  {
    Picture test = new Picture("Maxson1.jpg");
    test.explore();
    test.negate();
    test.explore();
  }
  public static void testGreyscale()
  {
    Picture test = new Picture("Maxson1.jpg");
    test.explore();
    test.greyscale();
    test.explore();
  }
  public static void testMirrorArms()
  {
    Picture snowman = new Picture("Maxson1.jpg");
    snowman.explore();
    snowman.mirrorLeftArms();
    snowman.mirrorRightArms();
    snowman.explore();

  }
  public static void testFixUnderwater()
  {
    Picture snowman = new Picture("water.jpg");
    snowman.explore();
    snowman.fixUnderwater();
    snowman.explore();

  }
  public static void testFixUnderwater2()
  {
    Picture snowman = new Picture("water.jpg");
    snowman.explore();
    snowman.fixUnderwater2();
    snowman.explore();

  }
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection1()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection1(10);
    swan.explore();
  }
  public static void testCropAndCopy()
  {
    Picture maxson = new Picture("Maxson1.jpg");
    Picture snowman = new Picture("Snowman.jpg");
    maxson.cropAndCopy(snowman, 75, 295, 172, 240, 230, 400);
    maxson.explore();
  }
  public static void testScaleByHalf()
  {
    Picture maxson = new Picture("Maxson1.jpg");
    maxson.scaleByHalf(maxson);
    maxson.explore();
  }
  public static void testEdgeDetection()
  {
    Picture maxson = new Picture("Maxson1.jpg");
    maxson.edgeDetection(30);
    maxson.explore();
  }
  
  public static void testCreateCollage()
  {
      Picture maxsonCollage = new Picture(800, 1200);
      maxsonCollage.createCollage();
      maxsonCollage.explore();
  }
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    testNegate();
    testGreyscale();
    testEdgeDetection();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorVerticalRightToLeft();
    //testMirrorHorizontalTopToBottom();
    //testMirrorHorizontalBottomToTop();
    //testMirrorDiagonal();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}