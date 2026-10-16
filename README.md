# java-practice

Small, runnable Java 21 exercises I use to keep the core language sharp: each class has a `main()` you can run, and the domain-style exercises come with JUnit 5 tests.

## Layout

| Package | What's inside |
|---|---|
| `basics` | `==` vs `equals`, the `Integer` cache and unboxing `null` (`IntegerTrap`), `equals` / `hashCode` and why `HashSet` needs both (`EqualsHashCode`), string equality |
| `basics.arrays` | Arrays and lists: filling, copying with `System.arraycopy`, object arrays, `Iterator.remove`, `LinkedList` |
| `collections` | Map katas: word count with `getOrDefault` and `merge` |
| `concurrency` | The `counter++` race condition, fixed with `synchronized` and with `AtomicInteger` |
| `modern.records` | A small bank-account model with records (`Money`, `Transaction`), enums and a custom exception, plus tests |
| `oop` | Static vs instance initialization order across a class hierarchy |

## Run

Requires JDK 21. The Maven wrapper downloads Maven on first use.

```bash
./mvnw test                     # compile + run the tests
```

To run one exercise, open its class in an IDE and run `main()`, or after `./mvnw compile`:

```bash
java -cp target/classes basics.IntegerTrap
```
