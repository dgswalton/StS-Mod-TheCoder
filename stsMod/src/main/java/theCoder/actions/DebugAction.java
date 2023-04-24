package theCoder.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.Iterator;

public class DebugAction extends AbstractGameAction {
    private boolean isUpgraded;

    public DebugAction(int buffGain, boolean upgraded) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.amount = buffGain;
        this.isUpgraded = upgraded;
    }

    public void update() {
        Iterator var1 = BetterExhaustAction.exhaustedCards.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.type == CardType.STATUS || c.type == CardType.CURSE) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new StrengthPower(AbstractDungeon.player, this.amount), this.amount));
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new DexterityPower(AbstractDungeon.player, this.amount), this.amount));
                if(this.isUpgraded){
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new ArtifactPower(AbstractDungeon.player, this.amount), this.amount));
                }
                break;
            }
        }

        this.isDone = true;
    }
}
