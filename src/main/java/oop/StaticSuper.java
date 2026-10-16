package oop;

public class StaticSuper {

    static int rand = 1;

    static {
        System.out.println("super static block");
    }

    StaticSuper () {
        System.out.println("Super constructor");
    }

}
