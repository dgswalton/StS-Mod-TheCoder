package theCoder.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import theCoder.TheCoderMod;
import theCoder.cards.CardImport;
import theCoder.characters.TheCoder;

import java.util.ArrayList;
import java.util.Iterator;

public class ImportAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private static String ID = "";

    public ImportAction(final int amount, final String CardID) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        ID = CardID;
    }

    public void update(){
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(this.generateCardChoices(), CardRewardScreen.TEXT[1], true);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard dupeCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();

                    dupeCard.current_x = -1000.0F * Settings.xScale;
                    if (AbstractDungeon.player.hand.size() < 10) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(dupeCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(dupeCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    }

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> retVal = new ArrayList();
        while(retVal.size() != this.amount) {
            boolean dupe = false;
            int roll = AbstractDungeon.cardRandomRng.random(99);
            AbstractCard.CardRarity cardRarity;
            if (roll < 55) {
                cardRarity = CardRarity.COMMON;
            } else if (roll < 85) {
                cardRarity = CardRarity.UNCOMMON;
            } else {
                cardRarity = CardRarity.RARE;
            }
            AbstractCard tmp = AbstractDungeon.getCard(cardRarity);
            Iterator var6 = retVal.iterator();

            while(var6.hasNext()) {
                AbstractCard c = (AbstractCard)var6.next();
                if (!tmp.cardID.equals(ID) && c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                retVal.add(tmp.makeCopy());
            }
        }
        return retVal;
    }
}
