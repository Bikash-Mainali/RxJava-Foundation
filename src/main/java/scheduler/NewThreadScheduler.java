package scheduler;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class NewThreadScheduler {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> observable = Observable.just("Job 1", "Job 2", "Job 3");

        testObserveOn(observable);
        System.out.println(".........................");
        testSubscribeOn(observable);
    }
/**
 Once that you set in your pipeline the observerOn all the next steps of your pipeline will be executed in another thread
 It can be called multiple times to switch threads at different stages
 */
    private static void testObserveOn(Observable<String> observable) throws InterruptedException {
        observable
                .doOnNext(item -> System.out.println("First step " + Thread.currentThread().getName()))
                .observeOn(Schedulers.newThread())

                .doOnNext(item -> System.out.println("Second step " + Thread.currentThread().getName()))
                .observeOn(Schedulers.newThread())

                .doOnNext(item -> System.out.println("Third Step " + Thread.currentThread().getName()))
                .subscribe(x -> System.out.println(x));
        // Sleep to allow observable to complete
        Thread.sleep(6000);

    }

    /**
     Does not matter at what point in your pipeline you set your subscribeOn, once that is set in the pipeline,
     all steps will be executed in another thread but the same thread.
     */
    private static void testSubscribeOn(Observable<String> observable) throws InterruptedException {
        observable
                .doOnNext(item -> System.out.println("First step " + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.newThread())

                .doOnNext(item -> System.out.println("Second step " + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.newThread())

                .doOnNext(item -> System.out.println("Third Step " + Thread.currentThread().getName()))
                .subscribe(x -> System.out.println(x));
        // Sleep to allow observable to complete
        Thread.sleep(6000);

    }

    private static void sleep() throws InterruptedException {

        Thread.sleep(1000);
    }
}

