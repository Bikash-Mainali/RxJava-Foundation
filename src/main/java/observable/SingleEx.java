package observable;

import io.reactivex.rxjava3.core.Single;

import java.util.concurrent.TimeUnit;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class SingleEx {
    public static void main(String[] args) throws InterruptedException {
        Single<String> single = Single.just("Immediate Hello from Single!");
        single.delay(1, TimeUnit.SECONDS)
                .subscribe(
                item -> System.out.println(item),
                error -> System.err.println("Error: " + error.getMessage())
        );
        Thread.sleep(2000);
    }
}
