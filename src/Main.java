import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
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
//        transactions.stream()
//                .filter(t-> t.getYear()==2011)
//                .sorted(Comparator.comparingInt(Transaction::getValue))
//                .forEach(System.out::println);
////      2. What are all the unique cities where the traders work?
//        transactions.stream()
//                .map(t -> t.getTrader().getCity())
//                .distinct()
//                .forEach(System.out::println);
////      3. Find all traders from Cambridge and sort them by name.
//        transactions.stream()
//                .map(Transaction::getTrader)
//                .filter(t-> t.getCity().equals("Cambridge"))
//                .sorted(Comparator.comparing(Trader::getName))
//                .forEach(System.out::println);
//////        4. Return a string of all traders’ names sorted alphabetically.
//////        5. Are any traders based in Milan?
//        boolean milan = transactions.stream()
//                .map(Transaction::getTrader)
//                .anyMatch(t -> t.getCity().equals("Milan"));
////        System.out.println(milan);
//// //       6. Print the values of all transactions from the traders living in Cambridge.
//// //       7. What’s the highest value of all the transactions?
//        Integer integer = transactions.stream()
//                .map(Transaction::getValue)
//                .max(Integer::compare).get();
//        System.out.println(integer);
// //       8. Find the transaction with the smallest value.
//        Transaction transaction = transactions.stream()
//                .reduce((t1, t2) ->
//                        t1.getValue() < t2.getValue() ? t1 : t2)
//                .get();
//        System.out.println(transaction);

        //use of comparator

//        ArrayList<Apple> list = new ArrayList<Apple>();
//        for(int i=0;i<5;i++){
//            list.add(new Apple(i*.3));
//        }
//        list.add(new Apple(0));
//        list.sort(new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return Double.compare(o1.getWeight(), o2.getWeight());
//            }
//        });
//        for(Apple apple:list){
//            System.out.println(apple.getWeight());
//        }

        //use of future and completablefuture

    }
    private static Future<Double> getPriceAsync(){
        CompletableFuture<Double> futurecall = new CompletableFuture<>();
        // there is one thread which is the main, which has to wait for the calculatePrice to fix execution then it proceeds.
        // so we create a another thread and assign it calculate the price then whenever it is ready the CompletableFuture
        // carries the value.
        new Thread(()-> {
            try {
                double price = calculatePrice("moneybag");
                futurecall.complete(price);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ).start();
        return futurecall;
    }
    private static double calculatePrice(String product) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}