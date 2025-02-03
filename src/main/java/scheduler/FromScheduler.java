package scheduler;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.Executors;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class FromScheduler {
    /**
     Allows using a custom Executor for scheduling tasks.
     */
    public static void main(String[] args) throws InterruptedException {
        Observable.just("A", "AB", "ABC")
                .flatMap(v -> getLength(v)
                        .doOnNext(s -> System.out.println("Processing Thread " + Thread.currentThread().getName()))
                        .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(3))))
                .subscribe(length -> System.out.println("Receiver Thread " + Thread.currentThread().getName() + ", Item length " + length));

        Thread.sleep(10000);
    }
    protected static Observable<Integer> getLength(String v) {
            return Observable.just(v.length());
    }
}
