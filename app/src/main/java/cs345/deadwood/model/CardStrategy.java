package cs345.deadwood.model;

import java.util.List;

public interface CardStrategy {

    ICard getNextCard(List<ICard> cards);
}
