package strategy;

import application.TimeStampValue;
import observer.ObserverGenAsync;
import proxy.GeneratorImp;

import java.util.List;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public class AtomicDiffusion implements AlgoDiffusion {
    GeneratorImp generatorImp;
    List<ObserverGenAsync> copy ;

    /**
     * Configure the strategy.
     * @param generatorImp
     */
    public void configure(GeneratorImp generatorImp) {
        this.generatorImp = generatorImp;
    }

    /**
     * Get the times value.
     * @param observerGenAsync
     * @return
     */
    @Override
    public TimeStampValue TimeStampValue(ObserverGenAsync observerGenAsync) {
        this.generatorImp.removeCanal(observerGenAsync);
        return this.generatorImp.getGeneratedTsValue();
    }

    /**
     * Execute the algorithm.
     */
    public void execute() {
        this.copy = generatorImp.getCopy();
        if(this.copy.isEmpty()){
            generatorImp.copy();
            generatorImp.go();
        }else{
            if(this.generatorImp.isRunning()){
                generatorImp.stop();
                for (ObserverGenAsync observerGenAsync:this.copy) {
                    observerGenAsync.update();
                }
            }
        }
    }
}
