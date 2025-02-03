package scheduler;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class TrampolineScheduler {

    /**
     Executes tasks in a FIFO order on the current thread. Suitable for queuing work on the current thread.
     */
    public static void main(String[] args) throws InterruptedException {
        Observable.just("A", "AB", "ABC")
                .flatMap(v -> getLength(v)
                        .doOnNext(s -> System.out.println("Processing Thread " + Thread.currentThread().getName()))
                        .subscribeOn(Schedulers.trampoline()))
                .subscribe(length -> System.out.println("Receiver Thread " + Thread.currentThread().getName() + ", Item length " + length));

        Thread.sleep(10000);
    }
    protected static Observable<Integer> getLength(String v) {
        return Observable.just(v.length());
    }
}
//output
/*
Processing Thread main
Receiver Thread main, Item length 1
Processing Thread main
Receiver Thread main, Item length 2
Processing Thread main
Receiver Thread main, Item length 3
 */
