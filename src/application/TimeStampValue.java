package application;

import java.sql.Timestamp;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public class TimeStampValue {
    private Integer nbTsValue;
    private Timestamp timeStamp;

    public TimeStampValue(Integer nbTsValue) {
        this.nbTsValue = nbTsValue;
        this.timeStamp = new Timestamp(System.currentTimeMillis());
    }

    public Integer getNbTsValue() {
        return this.nbTsValue;
    }

    public Timestamp getTimeStamp() {
        return this.timeStamp;
    }
}
