package basics.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Arrays and lists basics: declaring, iterating, copying, object arrays,
 * ArrayList growth, removing with an Iterator, LinkedList.
 * Run main() and read each section's output.
 */
public class ArraysAndLists {

    public static void main(String[] args) {
        declareAndFill();
        printBackwards();
        copyEfficiently();
        objectArray();
        arrayListWithIteratorRemoval();
        linkedList();
    }

    // 1. Declare an array and fill it (loop bound = the array's length, not a magic number)
    static void declareAndFill() {
        System.out.println("\n== 1. Declare and fill ==");
        int[] myArray = new int[10];
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = i;
        }
        System.out.println(Arrays.toString(myArray));
    }

    // 2. Declare with values and print backwards
    static void printBackwards() {
        System.out.println("\n== 2. Print backwards ==");
        int[] myArray2 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        for (int i = myArray2.length - 1; i >= 0; i--) {
            System.out.print(myArray2[i] + " ");
        }
        System.out.println();
    }

    // 3. Efficiently copy elements with System.arraycopy
    static void copyEfficiently() {
        System.out.println("\n== 3. System.arraycopy ==");
        char[] myCoffee = {'I', 'l', 'o', 'v', 'e', 'F', 'r', 'e', 'd', 'd', 'o', 'E', 's', 'p', 'r', 'e', 's', 's', 'o'};
        char[] order = new char[myCoffee.length - 5];
        System.arraycopy(myCoffee, 5, order, 0, myCoffee.length - 5);
        System.out.println(new String(order));    // FreddoEspresso
    }

    // 4. An array of objects
    static void objectArray() {
        System.out.println("\n== 4. Array of objects ==");
        ExampleInt[] objArray = new ExampleInt[10];
        for (int i = 0; i < objArray.length; i++) {
            objArray[i] = new ExampleInt(i);
        }
        System.out.println("last x = " + objArray[objArray.length - 1].getX());
    }

    // 5 + 6. ArrayList, then remove safely while iterating
    static void arrayListWithIteratorRemoval() {
        System.out.println("\n== 5–6. ArrayList + Iterator.remove ==");
        // Backed by an array (default capacity 10). When it's full, adding copies everything
        // into a bigger array: costly once, but amortized O(1) per add.
        List<ExampleInt> myIntegers = new ArrayList<>();
        myIntegers.add(new ExampleInt(10));
        myIntegers.add(new ExampleInt(20));
        myIntegers.add(new ExampleInt(30));

        // Removing inside a for-each throws ConcurrentModificationException; Iterator.remove() is safe
        Iterator<ExampleInt> iter = myIntegers.iterator();
        while (iter.hasNext()) {
            ExampleInt e = iter.next();
            if (e.getX() == 20) {
                iter.remove();
            }
        }
        System.out.print("after removing 20: ");
        for (ExampleInt e : myIntegers) {
            System.out.print(e.getX() + " ");
        }
        System.out.println();
    }

    // 7. LinkedList: no fixed capacity, cheap adds at the ends, slow get(i)
    static void linkedList() {
        System.out.println("\n== 7. LinkedList ==");
        List<ExampleInt> myLinkedIntegers = new LinkedList<>();
        myLinkedIntegers.add(new ExampleInt(40));
        myLinkedIntegers.add(new ExampleInt(50));
        myLinkedIntegers.add(new ExampleInt(60));
        System.out.println("size = " + myLinkedIntegers.size());
    }
}
