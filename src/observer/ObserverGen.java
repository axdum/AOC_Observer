package observer;

import javafx.beans.property.IntegerProperty;
import proxy.GeneratorAsync;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public interface ObserverGen {
    public void update(GeneratorAsync genAsync);
}
