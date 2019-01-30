package strategy;

import application.TimeStampValue;
import observer.ObserverGenAsync;
import proxy.GeneratorAsync;
import proxy.GeneratorImp;

import java.util.List;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public class SequencialDiffusion implements AlgoDiffusion {
    GeneratorImp generatorImp;
    GeneratorAsync generatorAsync;
    List<ObserverGenAsync> observerGenAsyncList;
    TimeStampValue timeStampValueCopy;

    /**
     * Configure the strategy.
     * @param generatorImp
     */
    @Override
    public void configure(GeneratorImp generatorImp) {

    }

    /**
     * Get the times value.
     * @param observerGenAsync
     * @return
     */
    @Override
    public TimeStampValue TimeStampValue(ObserverGenAsync observerGenAsync) {
        this.generatorImp.removeCanal(observerGenAsync);
        return this.timeStampValueCopy;
    }

    /**
     * Execute the algorithm.
     */
    @Override
    public void execute() {
        generatorImp.go();
        if(observerGenAsyncList.isEmpty()){
            this.generatorImp.copy();
            this.observerGenAsyncList = generatorImp.getCopy();
            for (ObserverGenAsync o:this.observerGenAsyncList) {
                o.update();
            }
            this.timeStampValueCopy = this.generatorImp.getGeneratedTsValue();
        }
    }
}
