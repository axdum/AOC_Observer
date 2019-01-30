package active_object;

import application.TimeStampValue;
import proxy.Generator;
import proxy.Canal;

import java.util.concurrent.Callable;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public class GetValueImp implements GetValue {
    Generator generator;
    Canal canal;

    public GetValueImp(Generator generator, Canal canal) {
        this.generator = generator;
        this.canal = canal;
    }

    @Override
    public TimeStampValue call() {
        return this.generator.getValue(this.canal);
    }
}
