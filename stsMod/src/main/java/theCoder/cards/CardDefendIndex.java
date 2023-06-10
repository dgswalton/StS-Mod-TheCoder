package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theCoder.TheCoderMod;
import theCoder.actions.calcBlock;
import theCoder.characters.TheCoder;
import theCoder.powers.IndexPower;

import static theCoder.TheCoderMod.makeCardPath;
public class CardDefendIndex extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardDefendIndex.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("DefendIndex.png");
    // use JSON to get the name and description of card
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int[] BLOCK = {4,8,12,16,20};
    private static final int UPGRADE_PLUS_BLOCK = 2;
    // /STAT DECLARATION/

    public CardDefendIndex() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block
        this.index0 = this.baseIndex0 = BLOCK[0];
        this.index1 = this.baseIndex1 = BLOCK[1];
        this.index2 = this.baseIndex2 = BLOCK[2];
        this.index3 = this.baseIndex3 = BLOCK[3];
        this.index4 = this.baseIndex4 = BLOCK[4];

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.calculateCardBlock();
        AbstractPower index = p.getPower("Index");
        if(index != null){
            if(index.amount == 1)
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(p, p, this.index1));
            else if(index.amount == 2)
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(p, p, this.index2));
            else if(index.amount == 3)
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(p, p, this.index3));
            else
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(p, p, this.index4));
            AbstractPower linkedList = p.getPower("LinkedListPower");
            if(index.amount < 4 || linkedList != null) {
                index.stackPower(1);
            }
        } else{
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new IndexPower(p, p, 1), 1));
            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(p, p, this.index0));
        }
    }
    public void calculateCardBlock(){
        calcBlock calc = new calcBlock(AbstractDungeon.player);
        this.index0 = calc.calculateBlock(this.baseIndex0);
        this.index1 = calc.calculateBlock(this.baseIndex1);
        this.index2 = calc.calculateBlock(this.baseIndex2);
        this.index3 = calc.calculateBlock(this.baseIndex3);
        this.index4 = calc.calculateBlock(this.baseIndex4);
        if(this.index0 != this.baseIndex0){
            this.isIndexModified = true;
        }
        else{
            this.isIndexModified = false;
        }
    }
    @Override
    public void applyPowers() {
        calculateCardBlock();
        super.applyPowers();
    }

    @Override
    public void atTurnStart() {
        calculateCardBlock();
        super.atTurnStart();
    }

    // Upgraded stats.
    @Override
    // Upgrades cost to 1
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeIndex(UPGRADE_PLUS_BLOCK);
            this.initializeDescription();
        }
    }
}
