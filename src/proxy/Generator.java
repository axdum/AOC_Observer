package proxy;

import application.TimeStampValue;
import observer.ObserverGenAsync;
import observer.Subject;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public interface Generator extends Subject {
    /**
     * Get timed value.
     * @param obsGenAsync
     * @return
     */
    public TimeStampValue getValue(ObserverGenAsync obsGenAsync);

    /**
     * Link observer.
     * @param obsGenAsync
     */
    public void attach(ObserverGenAsync obsGenAsync);

    /**
     * Unlink observer.
     * @param obsGenAsync
     */
    public void detach(ObserverGenAsync obsGenAsync);
}
