package cs345.deadwood.model;

import cs345.deadwood.view.GameLog;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BudgetCard implements CardStrategy{


    private static List<ICard> sortedCards;

    public BudgetCard(List<ICard> cards){
        sortedCards = cards;
        Collections.sort(cards, new Comparator<ICard>() {
            @Override
            public int compare(ICard o1, ICard o2) {
                return o1.getBudget() - o2.getBudget();
            }
        });

    }
    @Override
    public ICard getNextCard(List<ICard> cards) {
        ICard card = sortedCards.get(0);
        sortedCards.remove(card);
        return card;
    }
}
