    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpp_kovalev;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author lexvenim
 */

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = addRadioButton(1, group, true, false);
        RadioButton rb2 = addRadioButton(2, group, false, false);
        RadioButton rb3 = addRadioButton(3, group, false, true);
        
        VBox vb_rb = addVBox(Pos.CENTER_LEFT, 10, new Insets(0, 0, 0, 20));
        vb_rb.getChildren().addAll(rb1, rb2, rb3);
                             
        Spinner spn = addSpinner();
        
        addToggleListener(group, spn);
        
        VBox vb_spn = addVBox(Pos.TOP_RIGHT, 10, new Insets(20, 20, 0, 0));
        vb_spn.getChildren().add(spn);

        HBox hb = addHBox(Pos.CENTER_LEFT, 50, new Insets(0, 0, 0, 0));
        hb.getChildren().addAll(vb_rb, vb_spn);
        
        StackPane root = new StackPane();
        root.getChildren().add(hb);
               
        Scene scene = new Scene(root, 250, 100);
        
        primaryStage.setTitle("Window Title");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private RadioButton addRadioButton(int id, ToggleGroup group, boolean isSelected, boolean isDisable){
        RadioButton rb = new RadioButton("option " + id);
        rb.setToggleGroup(group);
        rb.setSelected(isSelected);
        rb.setDisable(isDisable);
        rb.setUserData(id);
        return rb;
    }
    
    private Spinner addSpinner(){
        Spinner spn = new Spinner();
        spn.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2,
            Integer.parseInt("1")));
        spn.setPrefWidth(50);
        return spn;
    }
    
    private VBox addVBox(Pos alignment, int spacing, Insets padding) {
        VBox vb = new VBox();
        vb.setAlignment(alignment);
        vb.setSpacing(spacing);
        vb.setPadding(padding);
        return vb;
    }
    
     private HBox addHBox(Pos alignment, int spacing, Insets padding) {
        HBox hb = new HBox();
        hb.setAlignment(alignment);
        hb.setSpacing(spacing);
        hb.setPadding(padding);
        return hb;
    }

    private void addToggleListener(ToggleGroup group, Spinner spn) {
        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) -> {
            spn.getValueFactory().setValue(t1.getUserData());
        });
        spn.getValueFactory().valueProperty().addListener((ObservableValue ov, Object oldVal, Object newVal) -> {
            group.selectToggle(group.getToggles().get((int)newVal - 1));
        });
    }
}
