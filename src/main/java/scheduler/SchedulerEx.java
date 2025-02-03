package scheduler;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.*;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class SchedulerEx {

    public static void main(String[] args) throws InterruptedException {
        usingRxJava();
        usingFuture();
        usingTraditionalMethod();
    }

    public static void usingRxJava() throws InterruptedException {
        Observable.just("Hello, RxJava!")
                .subscribeOn(Schedulers.io())  // Perform subscription on IO scheduler
                .observeOn(Schedulers.single())  // Switch to a single-thread scheduler for observation
                .map(str -> str + " - processed")
                .observeOn(Schedulers.computation())  // Switch again to computation scheduler
                .subscribe(
                        System.out::println,  // onNext
                        Throwable::printStackTrace,  // onError
                        () -> System.out.println("Completed")  // onComplete
                );

        Thread.sleep(1000);  // Delay to see output in console
    }

    public static void usingFuture() throws InterruptedException {
        ExecutorService ioExecutor = Executors.newCachedThreadPool(); // Executor for IO-bound tasks
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor(); // Single thread executor
        ExecutorService computationExecutor = Executors.newFixedThreadPool(2); // Fixed pool for computation tasks

        // Perform subscription on IO scheduler
        Future<String> future = ioExecutor.submit(() -> {
            return "Hello, Traditional Multithreading!";
        });

        // Switch to a single-thread scheduler for observation
        Future<String> processedFuture = singleExecutor.submit(() -> {
            String result = future.get() + " - processed";
            System.out.println(result);
            return result;
        });

        // Switch again to computation scheduler
        computationExecutor.submit(() -> {
            try {
                String finalResult = processedFuture.get();
                System.out.println(finalResult);
                // Additional computation tasks if needed
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // Shutdown executors
        ioExecutor.shutdown();
        singleExecutor.shutdown();
        computationExecutor.shutdown();

        // Delay to see output in console
        Thread.sleep(1000);
    }

    public static void usingTraditionalMethod() throws InterruptedException {
        ExecutorService ioExecutor = Executors.newCachedThreadPool(); // Executor for IO-bound tasks
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor(); // Single thread executor
        ExecutorService computationExecutor = Executors.newFixedThreadPool(2); // Fixed pool for computation tasks

        // Perform subscription on IO scheduler
        ioExecutor.execute(() -> {
            String result = "Hello, Traditional Multithreading!";
            System.out.println(result);

            // Switch to a single-thread scheduler for observation
            singleExecutor.execute(() -> {
                String processedResult = result + " - processed";
                System.out.println(processedResult);

                // Switch again to computation scheduler
                computationExecutor.execute(() -> {
                    String finalResult = processedResult.toUpperCase(); // Additional computation
                    System.out.println(finalResult);
                });
            });
        });

        // Shutdown executors
        ioExecutor.shutdown();
        singleExecutor.shutdown();
        computationExecutor.shutdown();

        // Wait for all tasks to complete or timeout after 5 seconds
        ioExecutor.awaitTermination(5, TimeUnit.SECONDS);
        singleExecutor.awaitTermination(5, TimeUnit.SECONDS);
        computationExecutor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
