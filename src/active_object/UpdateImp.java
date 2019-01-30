package active_object;

import observer.ObserverGen;
import proxy.Canal;

import java.util.concurrent.Callable;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public class UpdateImp implements Update {
    private ObserverGen display;
    private Canal canal;

    public UpdateImp(ObserverGen display, Canal canal) {
        this.display = display;
        this.canal = canal;
    }

    @Override
    public Void call() {
        this.display.update(canal);
        return null;
    }
}
