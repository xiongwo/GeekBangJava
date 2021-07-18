package witcher.cirilla;

import java.util.concurrent.*;

public class MainThread {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        // 1.Thread
        new Thread(() -> {
            threadField = sum();
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();

        System.out.println("异步计算结果为：" + threadField);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 2.Runnable
        ResultRunnable resultRunnable = new ResultRunnable();
        Thread thread = new Thread(resultRunnable);
        thread.start();
        thread.join();

        System.out.println("异步计算结果为：" + resultRunnable.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 3.Callable
        Callable<Integer> callable = MainThread::sum;
        Integer result = callable.call();

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 4.FutureTask
        FutureTask<Integer> integerFutureTask = new FutureTask<>(MainThread::sum);
        integerFutureTask.run();

        System.out.println("异步计算结果为：" + integerFutureTask.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 5.Executors
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executorService.submit(MainThread::sum);

        System.out.println("异步计算结果为：" + submit.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        executorService.shutdown();

        // 然后退出main线
    }

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static int threadField;

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    private static class ResultRunnable implements Runnable {

        private int result;

        @Override
        public void run() {
            result = sum();
        }

        int getResult() {
            return result;
        }

    }

}
