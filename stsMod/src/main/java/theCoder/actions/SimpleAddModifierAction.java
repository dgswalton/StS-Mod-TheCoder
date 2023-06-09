package theCoder.actions;


import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theCoder.helpers.TheCoderCardTags;
//import thePackmaster.SpireAnniversary5Mod;

public class SimpleAddModifierAction extends AbstractGameAction {
    private AbstractCardModifier mod;
    private AbstractCard card;

    public SimpleAddModifierAction(AbstractCardModifier mod, AbstractCard card) {
        this.mod = mod;
        this.card = card;
    }

    @Override
    public void update() {
        if (!isDone) {
            if (!card.hasTag(TheCoderCardTags.CANT_MOD))
                CardModifierManager.addModifier(card, mod);
            card.superFlash(Color.CHARTREUSE.cpy());
        }

        isDone = true;
    }
}