package theCoder.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theCoder.cards.AbstractCoderCard;
import theCoder.cards.AbstractDefaultCard;

import static theCoder.TheCoderMod.makeID;

public class secIndex1 extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("SecIndex1");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "theDefault:" (or, your id) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractCoderCard) card).secIsIndexModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractCoderCard) card).secIndex1;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractCoderCard) card).secBaseIndex1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractCoderCard) card).secUpgradedIndex;
    }
}