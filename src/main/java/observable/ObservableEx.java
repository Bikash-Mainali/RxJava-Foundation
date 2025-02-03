package observable;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class ObservableEx {
    public static void main(String[] args) {
        System.out.println("....just....");
        Observable<Integer> observable1 = Observable.just(1,2,3);
        observable1.subscribe(System.out::println);

        System.out.println("....create....");
        Observable<Integer> observable2 = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        });
        observable2.subscribe(System.out::println);

        System.out.println("....fromArray....");
        String[] items = {"a", "b", "c"};
        Observable<String> observable3 = Observable.fromArray(items);
        observable3.subscribe(System.out::println);

        System.out.println("....fromIterable....");
        List<String> items2 = List.of("a", "b", "c");
        Observable<String> observable4 = Observable.fromIterable(items2);
        observable4.subscribe(System.out::println);

        System.out.println("....fromCallable....");
        //this is useful in scenario where you have a computation or an I/O operation that you want
        //to defer until subscription time, rather than eagerly executing it.
        Observable<String> observable5 = Observable.fromCallable(() -> {
            // Some expensive computation or I/O operation
            return "From Callable";
        });
        observable5.subscribe(System.out::println);


        System.out.println("....fromFuture....");
        Future<String> future = Executors.newSingleThreadExecutor().submit(() -> "From Future");
        Observable<String> observable6 = Observable.fromFuture(future);
        observable6.subscribe(System.out::println);

        System.out.println("....completable....");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "From Completable Future");
        Observable<String> observable7 = Observable.fromCompletionStage(completableFuture);
        observable7.subscribe(System.out::println);


        System.out.println("....interval....");
        Observable<Long> observable9 = Observable.interval(1,TimeUnit.SECONDS);
        observable9.take(3).subscribe(System.out::println);

    }
}
