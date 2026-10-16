package basics;

/** String literals are pooled; new String always creates a new object. Compare strings with equals(). */
public class StringEquality {


    public static void main(String[] args) {
        String a = "hi";
        String b = "hi";
        String c = new String("hi");

        System.out.println(a == b);         // true:  both literals → the same pooled object
        System.out.println(a == c);         // false: new String → a different object
        System.out.println(a.equals(c));    // true:  same characters
    }

}
