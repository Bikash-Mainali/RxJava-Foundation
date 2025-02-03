package scheduler;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class IOScheduler {
    /**
     For I/O-bound operations.
     */
    public static void main(String[] args) throws InterruptedException {
        Observable.just("A", "AB", "ABC")
                .flatMap(v -> getLength(v)
                        .doOnNext(s -> System.out.println("Processing Thread "
                                + Thread.currentThread().getName()))
                        .subscribeOn(Schedulers.io()))
                .subscribe(length -> System.out.println("Receiver Thread "
                        + Thread.currentThread().getName()
                        + ", Item length " + length));

        Thread.sleep(10000);
    }
    protected static Observable<Integer> getLength(String v) {
        return Observable.just(v.length());
    }
}
//output
/*
Processing Thread RxIoScheduler-4
Processing Thread RxIoScheduler-3
Processing Thread RxIoScheduler-2
Receiver Thread RxIoScheduler-4, Item length 3
Receiver Thread RxIoScheduler-4, Item length 1
Receiver Thread RxIoScheduler-4, Item length 2
 */
