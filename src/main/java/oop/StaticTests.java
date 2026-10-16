package oop;

public class StaticTests extends StaticSuper {

    static int rand = 5;

//    static {
////        rand = (int) (Math.random() * 6);
//        System.out.println("static block " + StaticTests.rand);
//        System.out.println("static block " + StaticSuper.rand);
//    }
    {
        System.out.println("Instance block");
    }

    StaticTests() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        System.out.println("in main");
        System.out.println(StaticTests.rand);
        StaticTests st = new StaticTests();
//        StaticTests st1 = new StaticTests();
    }

}
