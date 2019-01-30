package strategy;

import application.TimeStampValue;
import observer.ObserverGenAsync;
import proxy.GeneratorImp;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public class PeriodDiffusion implements AlgoDiffusion {
    GeneratorImp generatorImp;

    /**
     * Configure the strategy.
     * @param generatorImp
     */
    @Override
    public void configure(GeneratorImp generatorImp) {
        this.generatorImp = this.generatorImp;
    }

    /**
     * Get the times value.
     * @param observerGenAsync
     * @return
     */
    @Override
    public TimeStampValue TimeStampValue(ObserverGenAsync observerGenAsync) {
        return this.generatorImp.getGeneratedTsValue();
    }

    /**
     * Execute the algorithm.
     */
    @Override
    public void execute() {
        this.generatorImp.copy();
        this.generatorImp.go();
        for (ObserverGenAsync o: this.generatorImp.getCopy()) {
            o.update();
        }
    }
}
