package multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qianzhaoliang
 * @since 2019/4/1
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(10);

        AtomicInteger result = new AtomicInteger(0);

        Thread[] threads = new Thread[10];
        for (int i=0; i<10; i++) {
            int sameElement = i+1;
            threads[i] = new Thread(() -> {
                result.addAndGet(sumOfArray(newIntArray(5, sameElement)));
                latch.countDown();
            });
            threads[i].start();
        }

        latch.await();

        // 5*1 + 5*2 + ... + 5*10 = 5 * (1 + 2 + ... + 10) = 5 * 55 =  275
        System.out.println(result.get());

    }

    private static int sumOfArray(int[] arr) {
        int result = 0;
        for (int i=0; i<arr.length; i++) {
            result  += arr[i];
        }
        return result ;
    }

    private static int[] newIntArray(int size, int sameElement) {

        int[] result = new int[size];
        for (int i=0; i<size; i++) {
            result[i] = sameElement;
        }

        return result;
    }


}
