package scheduler;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */

/**
 * In RxJava, a scheduler is a component responsible for coordinating the
 * execution of asynchronous tasks and managing concurrency. It abstracts away
 * the complexity of thread management and allows you to control when and where
 * computations or tasks should run
 */
public class ComputationScheduler {
    /**
     For CPU-intensive computations.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Observable.just("A", "AB", "ABC")
                .flatMap(v -> getLength(v)
                        .doOnNext(s -> System.out.println("Processing Thread " + Thread.currentThread().getName()))
                        .subscribeOn(Schedulers.computation()))
                .subscribe(length -> System.out.println("Receiver Thread " + Thread.currentThread().getName() + ", Item length " + length));

        Thread.sleep(10000);
    }
    protected static Observable<Integer> getLength(String v) {
        return Observable.just(v.length());
    }
}
//output
/*
Processing Thread RxComputationScheduler-2
Processing Thread RxComputationScheduler-3
Processing Thread RxComputationScheduler-1
Receiver Thread RxComputationScheduler-2, Item length 2
Receiver Thread RxComputationScheduler-2, Item length 1
Receiver Thread RxComputationScheduler-2, Item length 3
 */
