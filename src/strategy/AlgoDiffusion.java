package strategy;

import application.TimeStampValue;
import observer.ObserverGenAsync;
import proxy.GeneratorImp;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public interface AlgoDiffusion {
    /**
     * Configure the strategy.
     * @param generatorImp
     */
    public void configure(GeneratorImp generatorImp);

    /**
     * Get the times value.
     * @param observerGenAsync
     * @return
     */
    public TimeStampValue TimeStampValue(ObserverGenAsync observerGenAsync);

    /**
     * Execute the algorithm.
     */
    public void execute();
}
