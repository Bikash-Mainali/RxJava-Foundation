package observable;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 7/7/24
 */
public class FlowableEx {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.create(flowableEmitter -> {
            flowableEmitter.onNext(1);
            flowableEmitter.onNext(2);
            flowableEmitter.onNext(3);
        }, BackpressureStrategy.BUFFER);
        flowable.subscribe(item -> System.out.println(item));
    }
}
