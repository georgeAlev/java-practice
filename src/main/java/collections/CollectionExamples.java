package collections;

import java.util.HashMap;
import java.util.Map;

public class CollectionExamples {

    static class Words {
        private final String sentence;

        private Words(String sentence) {
            this.sentence = sentence;
        }

        private Map<String, Integer> countWithGetOrDefault() {
            var map = new HashMap<String, Integer>();
            var list = this.sentence.split(" ");
            for (var item: list) {
                map.put(item, map.getOrDefault(item, 0)+1);
            }
            return map;
        }

        private Map<String, Integer> countWithMerge() {
            var map = new HashMap<String, Integer>();
            var list = this.sentence.split(" ");
            for (var item: list) {
                map.merge(item, 1, Integer::sum);
            }
            return map;
        }
    }

    public static void main(String[] args) {
        var words = new Words("promo sale promo new sale promo");
        var map = words.countWithGetOrDefault();
        System.out.println(map);

        var map2 = words.countWithMerge();
        System.out.println(map2);
    }

}
