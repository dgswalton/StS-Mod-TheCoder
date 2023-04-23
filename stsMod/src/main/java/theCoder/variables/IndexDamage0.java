package theCoder.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theCoder.cards.AbstractDefaultCard;

import static theCoder.TheCoderMod.makeID;

public class IndexDamage0 extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return "theCoder:IndexDamage0";
        //return makeID("IndexDamage0");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "theDefault:" (or, your id) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractDefaultCard) card).isIndexDamageModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractDefaultCard) card).indexDamage0;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractDefaultCard) card).baseIndexDamage0;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractDefaultCard) card).upgradedIndexDamage;
    }
}