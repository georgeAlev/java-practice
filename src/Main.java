import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String [] args){

//        /* 1 Declare Array and */
//        int[] myArray = new int[10];
//        for (int i = 0; i <100; i++) {
//           myArray[i] = i;
//        }

//        /* 2 Declare and print backwards */
//        int[] myArray2 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
//        for (int i = myArray2.length -1; i>=0; i--){
//            System.out.println(myArray2[i]);
//        }

//        /* 3 Efficiently Copy elements */
//        char[] myCoffee = {'I', 'l', 'o', 'v', 'e', 'F', 'r', 'e', 'd', 'd', 'o', 'E', 's', 'p', 'r', 'e', 's', 's', 'o'};
//        char[] order = new char[50];
//
//        System.arraycopy(myCoffee, 5, order, 0, myCoffee.length-5);
//        System.out.println(new String(order));

//        /* 4 Array Objects */
//        ExampleInt[] objArray = new ExampleInt[10];
//        for (int i = 0; i < 10 ; i++) {
//            objArray[i] = new ExampleInt();
//            objArray[i].setX(i);
//        }

        List<ExampleInt> myIntegers = new ArrayList<>();
        myIntegers.add(new ExampleInt(10));
        myIntegers.add(new ExampleInt(20));
        myIntegers.add(new ExampleInt(30));

        for (ExampleInt e: myIntegers) {
            System.out.println(e.getX());
        }
    }

}
