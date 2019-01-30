package proxy;

import application.TimeStampValue;

import java.util.concurrent.Future;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public interface GeneratorAsync {
    /**
     * Get Value Async.
     * @return
     */
    Future<TimeStampValue> getValue();
}
