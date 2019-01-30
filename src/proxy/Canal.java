package proxy;

import active_object.GetValueImp;
import active_object.UpdateImp;
import application.TimeStampValue;
import observer.ObserverGen;
import observer.ObserverGenAsync;

import java.util.concurrent.*;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public class Canal implements ObserverGenAsync, GeneratorAsync {
    GeneratorImp generatorImp;
    ObserverGen display;
    ScheduledExecutorService scheduler;

    public Canal(){
        this.scheduler = new ScheduledThreadPoolExecutor(4);
    }

    /**
     * Get Async result.
     * @return
     */
    public Future<TimeStampValue> getValue() {
        GetValueImp getValue = new GetValueImp(this.generatorImp, this);
        return scheduler.schedule(getValue, 5000, TimeUnit.MILLISECONDS);
    }

    /**
     * Update values to display.
     */
    public void update() {
        UpdateImp update = new UpdateImp(this.display, this);
        scheduler.schedule(update, ThreadLocalRandom.current().nextInt(200,1000), TimeUnit.MILLISECONDS);
    }

    /**
     * Link observer.
     * @param observerGen
     */
    public void attach(ObserverGen observerGen) {
        this.display = observerGen;
    }

    // Setters

    /**
     * Set the generator of the Canal.
     * @param generatorImp
     */
    public void setGeneratorImp(GeneratorImp generatorImp) {
        this.generatorImp = generatorImp;
    }
}
