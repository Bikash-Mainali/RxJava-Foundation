package operators;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class Buffer {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4,
                5, 6, 7, 8, 9);

        observable.subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS, Schedulers.io())
                .buffer(3)
                .subscribe(
                        integers -> System.out.println(integers), //onNext()
                        throwable -> {
                            throw new RuntimeException(throwable.getCause());
                        }, //onError
                        () -> System.out.println("done") //onComplete
                );
        Thread.sleep(3000);
    }
}


