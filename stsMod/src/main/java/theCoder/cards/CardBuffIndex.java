package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import theCoder.TheCoderMod;
import theCoder.characters.TheCoder;
import theCoder.powers.IndexPower;
import theCoder.powers.RarePower;

import static theCoder.TheCoderMod.PLACEHOLDER_POTION_HYBRID;
import static theCoder.TheCoderMod.makeCardPath;
public class CardBuffIndex extends CustomCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardBuffIndex.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");
    // use JSON to get the name and description of card
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int[] BUFF = {1,2,2,1,1};
    private static final int[] UPGRADED_BUFF = {2,4,4,2,2};
    // /STAT DECLARATION/

    public CardBuffIndex() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block


    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower index = p.getPower("Index");
        AbstractPower linkedList = p.getPower("LinkedListPower");
        if (!upgraded) {
            if (index != null) {
                if (index.amount == 1) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(p, p, new PlatedArmorPower(p, BUFF[1]), BUFF[1]));
                }
                else if (index.amount == 2) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(p, p, new RegenPower(p, BUFF[2]), BUFF[2]));
                }
                else if (index.amount == 3) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(p, p, new RitualPower(p, BUFF[3], false), BUFF[3]));
                }
                else{
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, BUFF[4]), BUFF[4]));
                }
                if (index.amount < 4 || linkedList != null) {
                    index.stackPower(1);
                }
            } else {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(p, p, new IndexPower(p, p, 1), 1));
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(p, p, new ArtifactPower(p, BUFF[0]), BUFF[0]));
            }
        }
        else {
            if (index != null) {
                if (index.amount == 1) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(p, p, new PlatedArmorPower(p, UPGRADED_BUFF[1]), UPGRADED_BUFF[1]));
                }
                else if (index.amount == 2) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(p, p, new RegenPower(p, UPGRADED_BUFF[2]), UPGRADED_BUFF[2]));
                }
                else if (index.amount == 3) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(p, p, new RitualPower(p, UPGRADED_BUFF[3], false), UPGRADED_BUFF[3]));
                }
                else{
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, UPGRADED_BUFF[4]), UPGRADED_BUFF[4]));
                }
                if (index.amount < 4 || linkedList != null) {
                    index.stackPower(1);
                }
            } else {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(p, p, new IndexPower(p, p, 1), 1));
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(p, p, new ArtifactPower(p, UPGRADED_BUFF[0]), UPGRADED_BUFF[0]));
            }
        }
    }

    @Override
    public void applyPowers() {
        AbstractPower index = AbstractDungeon.player.getPower("Index");
        if(index != null && index.amount > 3){
            this.exhaust = true;
        }
        else{
            this.exhaust = false;
        }
    }
    // Upgraded stats.
    @Override
    // Upgrades cost to 1
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
