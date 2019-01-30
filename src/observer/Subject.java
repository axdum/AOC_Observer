package observer;

import observer.ObserverGenAsync;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public interface Subject {
    public void attach(ObserverGenAsync observerGenAsync);
    public void detach(ObserverGenAsync observerGenAsync);
}
