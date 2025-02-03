package observable;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

import java.util.concurrent.TimeUnit;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class MayBeEx {
    public static void main(String[] args) throws InterruptedException {
        Maybe<String> mayBe = Maybe.just("Hello from Maybe!");

        mayBe.subscribe(
                item -> System.out.println(item),
                error -> System.err.println("Error: " + error.getMessage())
        );
        Thread.sleep(2000);
    }
}
