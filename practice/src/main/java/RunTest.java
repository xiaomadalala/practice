import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RunTest {
    public static CountDownLatch begin = new CountDownLatch(2);
    public static CountDownLatch end = new CountDownLatch(2);




    static class Runer  implements Runnable{
        private String name;
        public Runer(String name) {
            this.name = name;
        }

        public void run() {
            try {
                begin.countDown();
                System.out.println("运动员"+name+"准备好了");
                begin.await();
                System.out.println("运动员"+name+"开始跑了");
                long startTime = System.currentTimeMillis();
                Thread.sleep(new Random().nextInt(10000));
                long endTime = System.currentTimeMillis();
                long spend = endTime - startTime;
                end.countDown();
                System.out.println("运动员"+name + "到达终点");
                end.await();
                System.out.println("运动员"+name + "花费了" + spend + "毫秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
