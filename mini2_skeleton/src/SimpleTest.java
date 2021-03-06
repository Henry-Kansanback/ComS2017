import mini2.GridUtil;

//import reference.OurGridUtil;


public class SimpleTest
{

  public static void main(String[] args)
  {
    // 6x7 array with sequential values 1 through 42 (as in pdf)
    int[][] test = new int[6][7];
    int count = 1;
    for (int row = 0; row < test.length; ++row)
    {
      for (int col = 0; col < test[0].length; ++col)
      {
        test[row][col] = count;
        count += 1;
      }
    }
    
    int[][] sub = GridUtil.getSubArray(test, 3, 2, 1, false);
    GridUtil.printArray(sub);   
  }
}
