import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;

import java.util.Random;

public class App {


    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(()->{

            Metrics m = new Metrics("localhost",8125,1);
            Counter counter = m.counter("jiejie.love");
            Meter meter = m.meter("jiejie.testmeter");
            Timer timer = m.timer("jiejie.timer");
            Random radom = new Random();
            for(;;) {
                counter.inc(radom.nextInt(100));
                meter.mark();
                Timer.Context timerContext = timer.time();
                try {
                    Thread.sleep(radom.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timerContext.stop();
            }

        });

        thread.start();




    }
}
