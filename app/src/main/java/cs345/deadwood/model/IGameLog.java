package cs345.deadwood.model;

import cs345.deadwood.view.BoardView;

public interface IGameLog {

    public void registerObservers(BoardView board);
}
