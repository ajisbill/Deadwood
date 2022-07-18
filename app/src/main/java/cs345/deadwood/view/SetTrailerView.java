package cs345.deadwood.view;

import cs345.deadwood.model.ISet;

import javax.swing.*;

public class SetTrailerView {

    private final JFrame board;
    private ISet trailerSet;

    public SetTrailerView(JFrame parentContainer, ISet trailerSet) {
        board = parentContainer;
        trailerSet = trailerSet;

    }

}
