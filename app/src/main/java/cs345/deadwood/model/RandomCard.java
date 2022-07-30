package cs345.deadwood.model;

import java.util.List;
import java.util.Random;

public class RandomCard implements CardStrategy{


    @Override
    public ICard getNextCard(List<ICard> cards) {
        Random rand = new Random();
        int randInt = rand.nextInt(cards.size());
        ICard randCard = cards.get(randInt);
        cards.remove(randInt);
        return randCard;
    }
}
