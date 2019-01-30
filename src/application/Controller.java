package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import observer.ObserverGen;
import proxy.Canal;
import proxy.DisplayImp;
import proxy.GeneratorImp;
import strategy.AtomicDiffusion;
import strategy.PeriodDiffusion;
import strategy.SequencialDiffusion;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Axel DUMONT M2-MIAGE (DLIS)
 * UI Controller.
 */
public class Controller implements Initializable {
    private GeneratorImp generatorImp;

    // Canals
    private Canal canal_1;
    private Canal canal_2;
    private Canal canal_3;
    private Canal canal_4;

    // Afficheurs
    private ObserverGen display_1;
    private ObserverGen display_2;
    private ObserverGen display_3;
    private ObserverGen display_4;

    // UI
    @FXML
    private RadioButton radioAtomic;

    @FXML
    private RadioButton radioPeriod;

    @FXML
    private RadioButton radioSequential;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;

    @FXML
    private ToggleGroup radioGroup;

    @FXML
    private Label display1;

    @FXML
    private Label display2;

    @FXML
    private Label display3;

    @FXML
    private Label display4;

    @FXML
    private Label display1_bg;

    @FXML
    private Label display2_bg;

    @FXML
    private Label display3_bg;

    @FXML
    private Label display4_bg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setDigitalFont();
        btnStop.setDisable(true);

        this.canal_1 = new Canal();
        this.canal_2 = new Canal();
        this.canal_3 = new Canal();
        this.canal_4 = new Canal();

        this.generatorImp = new GeneratorImp();
        canal_1.setGeneratorImp(this.generatorImp);
        canal_2.setGeneratorImp(this.generatorImp);
        canal_3.setGeneratorImp(this.generatorImp);
        canal_4.setGeneratorImp(this.generatorImp);

        this.generatorImp.attach(this.canal_1);
        this.generatorImp.attach(this.canal_2);
        this.generatorImp.attach(this.canal_3);
        this.generatorImp.attach(this.canal_4);

        this.display_1 = new DisplayImp(1, display1);
        this.display_2 = new DisplayImp(2, display2);
        this.display_3 = new DisplayImp(3, display3);
        this.display_4 = new DisplayImp(4, display4);

        this.canal_1.attach(this.display_1);
        this.canal_2.attach(this.display_2);
        this.canal_3.attach(this.display_3);
        this.canal_4.attach(this.display_4);
    }

    /**
     * Set digital fonts to labels.
     */
    private void setDigitalFont(){
        this.display1.setFont(Font.loadFont("file:resources/fonts/DS-DIGII.TTF", 64));
        this.display2.setFont(Font.loadFont("file:resources/fonts/DS-DIGII.TTF", 64));
        this.display3.setFont(Font.loadFont("file:resources/fonts/DS-DIGII.TTF", 64));
        this.display4.setFont(Font.loadFont("file:resources/fonts/DS-DIGII.TTF", 64));
        this.display1_bg.setFont(Font.loadFont("file:resources/fonts/DS-DIGII.TTF", 64));
        this.display2_bg.setFont(Font.loadFont("file:resources/fonts/DS-DIGII.TTF", 64));
        this.display3_bg.setFont(Font.loadFont("file:resources/fonts/DS-DIGII.TTF", 64));
        this.display4_bg.setFont(Font.loadFont("file:resources/fonts/DS-DIGII.TTF", 64));
    }

    @FXML
    private void onClickAtomic(){
        this.setStrategy();
    }

    @FXML
    private void onClickSequencial(){
        this.setStrategy();
    }

    @FXML
    private void onClickPeriod(){
        this.setStrategy();
    }

    /**
     * Change broadcast strategy.
     */
    private void setStrategy() {
        if (this.radioGroup.getSelectedToggle() != null) {
            RadioButton selectedRadioButton = (RadioButton) this.radioGroup.getSelectedToggle();
            switch (selectedRadioButton.getId()) {
                case "radioAtomic":
                    generatorImp.setStrategy(new AtomicDiffusion());
                    System.out.println("Strategy changed -> Atomic");
                    break;
                case "radioSequential":
                    generatorImp.setStrategy(new SequencialDiffusion());
                    System.out.println("Strategy changed -> Sequencial");
                    break;
                case "radioPeriod":
                    generatorImp.setStrategy(new PeriodDiffusion());
                    System.out.println("Strategy changed -> Period");
                    break;
            }
        }
    }

    @FXML
    private void onClickStart() {
        btnStart.setDisable(true);
        btnStop.setDisable(false);
        radioAtomic.setDisable(true);
        radioSequential.setDisable(true);
        radioPeriod.setDisable(true);
        this.setStrategy();
        this.generatorImp.go();
        this.generatorImp.run();
    }

    @FXML
    private void onClickStop() {
        btnStart.setDisable(false);
        btnStop.setDisable(true);
        radioAtomic.setDisable(false);
        radioSequential.setDisable(false);
        radioPeriod.setDisable(false);
        this.generatorImp.stop();
        this.generatorImp.stopG();
    }
}
