package theCoder.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theCoder.cards.AbstractCoderCard;
import theCoder.cards.AbstractDefaultCard;

import static theCoder.TheCoderMod.makeID;

public class Index2 extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("Index2");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "theDefault:" (or, your id) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractCoderCard) card).isIndexModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractCoderCard) card).index2;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractCoderCard) card).baseIndex2;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractCoderCard) card).upgradedIndex;
    }
}