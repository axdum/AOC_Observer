package proxy;

import application.TimeStampValue;
import strategy.AlgoDiffusion;
import observer.ObserverGenAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public class GeneratorImp implements Generator {
    private AlgoDiffusion strategy;
    private TimeStampValue generatedTsValue;
    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> scheduledFuture;
    private List<ObserverGenAsync> observerGenAsyncList;
    private List<ObserverGenAsync> copy;
    private boolean isRunning;

    public GeneratorImp() {
        this.generatedTsValue = new TimeStampValue(0);
        this.observerGenAsyncList = new ArrayList<ObserverGenAsync>();
        this.copy = new ArrayList<ObserverGenAsync>();
        this.scheduler = new ScheduledThreadPoolExecutor(1);
        this.isRunning = false;
    }

    public TimeStampValue getValue(ObserverGenAsync obsGenAsync) {
        return this.strategy.TimeStampValue(obsGenAsync);
    }

    @Override
    public void attach(ObserverGenAsync obsGenAsync) {
        this.observerGenAsyncList.add(obsGenAsync);
    }

    @Override
    public void detach(ObserverGenAsync obsGenAsync) {
        if (this.observerGenAsyncList.contains(obsGenAsync)) {
            this.observerGenAsyncList.remove(obsGenAsync);
        }
    }

    public TimeStampValue getTimeStampValue(ObserverGenAsync observerGenAsync) {
        return this.strategy.TimeStampValue(observerGenAsync);
    }

    public void run() {
        this.copy();
        this.scheduledFuture = scheduler.scheduleAtFixedRate(() -> generateNewTimeStampValue(), 800, 1000, TimeUnit.MILLISECONDS);
    }

    /**
     * Clone observerGenAsyncList.
     */
    public void copy() {
        this.copy = (ArrayList) new ArrayList<ObserverGenAsync>(observerGenAsyncList).clone();
    }

    /**
     * Generate a new times value.
     */
    private void generateNewTimeStampValue() {
        if (isRunning) {
            System.out.println("Generated value: " + this.getGeneratedTsValue().getNbTsValue());
            this.generatedTsValue = new TimeStampValue(this.generatedTsValue.getNbTsValue() + 1);
        }
        this.strategy.execute();
    }

    /**
     * Stop the generator.
     */
    public void stopG() {
        this.scheduledFuture.cancel(false);
    }

    /**
     * Remove the observer from async generators list.
     * @param canal
     */
    public void removeCanal(ObserverGenAsync canal) {
        this.copy.remove(canal);
    }

    // Getters

    /**
     * Get the copy of observerGenAsyncList.
     * @return
     */
    public List<ObserverGenAsync> getCopy() {
        return this.copy;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public TimeStampValue getGeneratedTsValue() {
        return this.generatedTsValue;
    }

    // Setters

    /**
     * Set a broadcast strategy.
     * @param strategy
     */
    public void setStrategy(AlgoDiffusion strategy) {
        this.strategy = strategy;
        this.strategy.configure(this);
    }

    /**
     * Set isRunning indicator to false.
     */
    public void stop(){
        this.isRunning = false;
        System.out.println("Generator stopped");
    }

    /**
     * Set isRunning indicator to true.
     */
    public void go(){
        this.isRunning = true;
        System.out.println("Generator launched");
    }
}
