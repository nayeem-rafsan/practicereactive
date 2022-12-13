import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
//      1. Find all transactions in the year 2011 and sort them by value (small to high).
        transactions.stream()
                .filter(t-> t.getYear()==2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
//      2. What are all the unique cities where the traders work?
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
//      3. Find all traders from Cambridge and sort them by name.
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t-> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
//        4. Return a string of all traders’ names sorted alphabetically.
//        5. Are any traders based in Milan?
        boolean milan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(t -> t.getCity().equals("Milan"));
        System.out.println(milan);
 //       6. Print the values of all transactions from the traders living in Cambridge.
 //       7. What’s the highest value of all the transactions?
        Integer integer = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare).get();
        System.out.println(integer);
 //       8. Find the transaction with the smallest value.
        Transaction transaction = transactions.stream()
                .reduce((t1, t2) ->
                        t1.getValue() < t2.getValue() ? t1 : t2)
                .get();
        System.out.println(transaction);
    }
}