package theCoder.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import java.util.Iterator;

public class CountermeasuresAction extends AbstractGameAction {
    private int Burn = 4;
    private int Dazed = 2;
    private int Void = 2;

    public CountermeasuresAction(final int Burn, final int Dazed, final int Void) {
        this.Burn = Burn;
        this.Dazed = Dazed;
        this.Void = Void;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();
        AbstractPlayer p = AbstractDungeon.player;
        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.cardID == "Burn") {
                AbstractDungeon.actionManager.addToTop(new HealAction(p, p, this.Burn));
                break;
            } else if (c.cardID == "Dazed"){
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.Dazed, false));
            } else if (c.cardID == "Void"){
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.Void));
            }
        }

        this.isDone = true;
    }
}
