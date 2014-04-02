package sandbox;

import cern.colt.Arrays;

public class sandbox {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("hello");

    double[] doublearray = null;
    double[] testarray = new double[] {3.4, 4.5, 6.6};

    doublearray = testarray;

    System.out.println(Arrays.toString(doublearray));
    System.out.println(doublearray[1]);

  }
}
