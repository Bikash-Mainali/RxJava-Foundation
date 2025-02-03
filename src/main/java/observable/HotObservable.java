package observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class HotObservable {
    /**
     * Imagine a scenario where you're implementing a stock price ticker.
     * You want to stream real-time stock prices to multiple users.
     * Each user should see the same prices in real-time from the moment
     * they start observing, not a separate sequence.
     */
    public static void main(String[] args) throws InterruptedException {
        ConnectableObservable<Long> stockPriceObservable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .take(5)
                .publish(); // Convert to ConnectableObservable (hot observable)

        // First subscriber
        stockPriceObservable.subscribe(price ->
                System.out.println("Subscriber 1: " + price));

        Thread.sleep(2000);

        // Second subscriber
        stockPriceObservable.subscribe(price ->
                System.out.println("Subscriber 2: " + price));

        // Connect to start emitting
        stockPriceObservable.connect();

        System.out.println(".....");
        // Sleep for some time to observe emissions
        Thread.sleep(50000);
    }
}

/**
 * Output:
 * Output:
 * Subscriber 1: 0
 * Subscriber 2: 0
 *
 * Subscriber 1: 1
 * Subscriber 2: 1
 *
 * Subscriber 1: 2
 * Subscriber 2: 2
 *
 * Subscriber 1: 3
 * Subscriber 2: 3
 *
 * Subscriber 1: 4
 * Subscriber 2: 4
 */
