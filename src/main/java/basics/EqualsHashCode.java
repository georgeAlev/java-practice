package basics;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EqualsHashCode {

    static class NoOverride {
        String name;
        NoOverride(String name) {this.name = name;}
    }

    static class EqualsOnly {
        String name;
        EqualsOnly(String name) { this.name = name; }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || this.getClass() != o.getClass()) { return false; }
            EqualsOnly other = (EqualsOnly) o;
            return Objects.equals(this.name, other.name);                       // null-safe
        }
    }

    static class EqualsAndHashCode {
        String name;
        EqualsAndHashCode(String name) {this.name = name;}

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) return false;
            EqualsAndHashCode that = (EqualsAndHashCode) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
    }

    public static void main(String[] args) {

        /* case 1 no override */
        var x = new NoOverride("Promo");
        var y = new NoOverride("Promo");
        System.out.println("""
                NoOverride
                    x == y          %s
                    x.equals(y)     %s
                """.formatted(x == y, x.equals(y)));

        /* case 2 override equal only */
        var z = new EqualsOnly("Draft");
        var w = new EqualsOnly("Draft");
        Set<EqualsOnly> setEqualsOnly = new HashSet<>();
        setEqualsOnly.add(z);
        boolean containsW = setEqualsOnly.contains(w);   // checked BEFORE w is added
        setEqualsOnly.add(w);                            // different hashCode → different bucket → added
        System.out.print("""
                EqualsOnly
                    z == w          %-5s
                    z.equals(w)     %-5s
                    set contains w  %-5s   (before adding w)
                    set size        %-5s   (after adding w)
                """.formatted(z == w, z.equals(w), containsW, setEqualsOnly.size()));

        /* case 3 override equal and hashcode */
        var k = new EqualsAndHashCode("Draft");
        var l = new EqualsAndHashCode("Draft");
        Set<EqualsAndHashCode> setEqualsAndHashCode = new HashSet<>();
        setEqualsAndHashCode.add(k);
        boolean containsl = setEqualsAndHashCode.contains(l);   // checked BEFORE w is added
        setEqualsAndHashCode.add(l);
        System.out.print("""
                EqualsAndHashCode
                    z == w          %-5s
                    z.equals(w)     %-5s
                    set contains w  %-5s   (before adding w)
                    set size        %-5s   (after adding w)
                """.formatted(k == l, k.equals(l), containsl, setEqualsAndHashCode.size()));
    }


}
