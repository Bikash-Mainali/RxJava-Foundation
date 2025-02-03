package observable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;

import java.util.concurrent.TimeUnit;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class CompletableEx {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("....completable....");
        Disposable disposable = Completable.complete()
                .delay(2, TimeUnit.SECONDS)
                .subscribeWith(new DisposableCompletableObserver(){
                                   @Override
                                   public void onStart() {
                                       System.out.println("Started!");
                                   }
                                   @Override
                                   public void onComplete() {
                                       System.out.println("Done!");
                                   }
                                   @Override
                                   public void onError(@NonNull Throwable e) {
                                       throw new RuntimeException("Error");
                                   }
                               }
                        );

                Thread.sleep(3000);

                //start observing
                disposable.dispose();
    }
}
