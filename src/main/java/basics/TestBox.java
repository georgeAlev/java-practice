package basics;

/** Head First Java exercise: unboxing a null Integer into an int throws a NullPointerException in go(). */
public class TestBox {

    private Integer i;
    private int j;

    public static void main(String[] args) {
        TestBox testBox = new TestBox();
        testBox.go();
    }

    public void go() {
        j = i;
        System.out.println(i);
        System.out.println(j);
    }

}
