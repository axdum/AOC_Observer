package proxy;

import application.TimeStampValue;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.concurrent.ExecutionException;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 */
public class DisplayImp implements Display {
    private int displayId;
    private int value;
    private TimeStampValue timeStampValue;
    @FXML
    private Label display;

    public DisplayImp(int displayId, Label display) {
        this.display = display;
        this.display.setText("0");
        this.displayId = displayId;
        this.value = 0;
        this.timeStampValue = new TimeStampValue(0);
    }

    /**
     * Update the display.
     * @param genAsync
     */
    public void update(GeneratorAsync genAsync) {
        try {
            TimeStampValue tsv = genAsync.getValue().get();
            if(tsv.getTimeStamp().after(this.timeStampValue.getTimeStamp())){
                this.setValue(tsv.getNbTsValue());
                this.timeStampValue = tsv;
                Platform.runLater(() -> this.display.setText(String.valueOf(this.value)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Setters

    /**
     * Set Value.
     * @param value
     */
    private void setValue(int value) {
        this.value=value;
    }
}
