import com.codahale.metrics.*;
import com.readytalk.metrics.StatsDReporter;

import java.util.concurrent.TimeUnit;

public class Metrics {

  public   Metrics(String host,int port,int pollInterval){
        initializeStatsdReporter(host,port,pollInterval);
    }
    private static  MetricRegistry registry = new MetricRegistry();

    public static Meter meter(String name ) {

       return  Metrics.registry.meter(name);
    }

    public static Counter counter(String name ) {

        return  Metrics.registry.counter(name);
    }

    public static Timer timer(String name){
        return Metrics.registry.timer(name);
    }

    private void initializeStatsdReporter(String host,int port,int pollInterval) {
        StatsDReporter reporter = StatsDReporter.forRegistry(registry)
                .prefixedWith("linseven")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(host, port);
        reporter.start(pollInterval, TimeUnit.SECONDS);
    }
}
