package cs345.deadwood.model;

import cs345.deadwood.view.ButtonView;

public class Button {

    private String label;
    private ButtonView buttonView;

    public Button(String label){
        this.label = label;
    }

    public void registerObservers(ButtonView buttonView){
        this.buttonView = buttonView;
    }
}
