package basics;

import java.util.HashMap;
import java.util.Map;

/**
 * The int vs Integer trap: autoboxing, the Integer cache, and unboxing a null.
 * Run main() and compare each line with the "expected" comment.
 */
public class IntegerTrap {

    public static void main(String[] args) {
        autoboxing();
        cacheTrap();
        nullUnboxing();
        realWorldBug();
    }

    // 1. Autoboxing / unboxing: Java converts between int and Integer for you
    static void autoboxing() {
        System.out.println("\n== 1. Autoboxing ==");

        int primitive = 42;
        Integer boxed = primitive;      // autoboxing:  int → Integer  (Integer.valueOf(42))
        int back = boxed;               // unboxing:    Integer → int  (boxed.intValue())

        System.out.println(boxed + " " + back);   // expected: 42 42
    }

    // 2. The cache trap: == on Integer compares REFERENCES, not values
    static void cacheTrap() {
        System.out.println("\n== 2. The Integer cache trap ==");

        Integer a = 127, b = 127;
        System.out.println("127 == 127      -> " + (a == b));        // expected: true  (same cached object)

        Integer c = 128, d = 128;
        System.out.println("128 == 128      -> " + (c == d));        // expected: false (two different objects!)
        System.out.println("128 equals 128  -> " + c.equals(d));     // expected: true  (compares the value)

        int e = 128, f = 128;
        System.out.println("int 128 == 128  -> " + (e == f));        // expected: true  (primitives compare values)

        // Why: Integer.valueOf() caches the objects for -128..127 and reuses them.
        // Outside that range every boxing creates a NEW object, so == compares two references.
        // Rule: compare Integer objects with equals() (or unbox to int first), never with ==.
    }

    // 3. Unboxing a null: an int can't be null, so Java throws
    static void nullUnboxing() {
        System.out.println("\n== 3. Unboxing null ==");

        Integer maybe = null;
        try {
            int value = maybe;          // unboxing calls maybe.intValue() → NullPointerException
            System.out.println(value);
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException: an Integer was null when unboxed to int");
        }
    }

    // 4. Where it bites in real code: values from a Map are Integer objects
    static void realWorldBug() {
        System.out.println("\n== 4. A realistic bug ==");

        Map<String, Integer> sentPerCampaign = new HashMap<>();
        sentPerCampaign.put("black-friday", 1000);
        sentPerCampaign.put("cyber-monday", 1000);

        Integer x = sentPerCampaign.get("black-friday");
        Integer y = sentPerCampaign.get("cyber-monday");

        System.out.println("same count? (==)      -> " + (x == y));          // expected: false  ❌ the bug
        System.out.println("same count? (equals)  -> " + x.equals(y));       // expected: true   ✅ the fix

        Integer missing = sentPerCampaign.get("unknown");                   // a missing key returns null
        System.out.println("missing key returns   -> " + missing);           // expected: null
        // int total = missing + 1;   // would throw NullPointerException (unboxing null)
    }
}
