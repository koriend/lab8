package multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MultiTest {
    private static long startTime;
    private static long endTime;
    public static void main(String[] args) {

        int poolSize = 10;
        ExecutorService exec = Executors.newFixedThreadPool(poolSize);
        CountDownLatch latch = new CountDownLatch(poolSize);

        List<RunMultiTest> threads = new ArrayList<>();
        RunMultiTest mult = new RunMultiTest();

        int j = 0;
        startTime = System.currentTimeMillis();

        while (j < 10){
            j++;
            //exec.execute(new multithread.RunMultiTest());

            mult.run();
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
                System.out.println(":(");
            }
        }
        exec.shutdown();
        try{
            exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis() - startTime;
        System.out.println("\u001B[1;35m" +  "Time main thread: " + endTime + "\033[0m");
    }
}
