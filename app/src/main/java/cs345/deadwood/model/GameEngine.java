package cs345.deadwood.model;

import java.util.List;

public class GameEngine {
    private final int numberOfPlayers;
    private final List<ISet> setList;
    private final List<ICard> cardList;

    public GameEngine(int numberOfPlayers, List<ISet> setList, List<ICard> cardList) {
        this.numberOfPlayers = numberOfPlayers;
        this.setList = setList;
        this.cardList = cardList;
    }


    public List<ISet> getSets(){
        return setList;
    }

    public List<ICard> getCards(){
        return cardList;
    }

}
