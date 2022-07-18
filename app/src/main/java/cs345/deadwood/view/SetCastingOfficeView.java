package cs345.deadwood.view;

import cs345.deadwood.model.ISet;

import javax.swing.*;

public class SetCastingOfficeView {

    private final JFrame board;
    private ISet officeSet;

    public SetCastingOfficeView(JFrame parentContainer, ISet officeSet) {
        board = parentContainer;
        officeSet = officeSet;
    }
}
