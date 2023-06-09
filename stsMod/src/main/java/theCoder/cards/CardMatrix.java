package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theCoder.TheCoderMod;
import theCoder.actions.calcBlock;
import theCoder.actions.calcDamage;
import theCoder.characters.TheCoder;
import theCoder.powers.IndexPower;

import static theCoder.TheCoderMod.makeCardPath;
public class CardMatrix extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardMatrix.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int[] DAMAGE = {2,4,6,8,10};
    private static final int[] BLOCK = {2,4,6,8,10};
    private static final int UPGRADE_PLUS_DAMAGE = 2;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    // /STAT DECLARATION/

    public CardMatrix() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.index0 = this.baseIndex0 = DAMAGE[0];
        this.index1 = this.baseIndex1 = DAMAGE[1];
        this.index2 = this.baseIndex2 = DAMAGE[2];
        this.index3 = this.baseIndex3 = DAMAGE[3];
        this.index4 = this.baseIndex4 = DAMAGE[4];
        this.secIndex0 = this.secBaseIndex0 = BLOCK[0];
        this.secIndex1 = this.secBaseIndex1 = BLOCK[1];
        this.secIndex2 = this.secBaseIndex2 = BLOCK[2];
        this.secIndex3 = this.secBaseIndex3 = BLOCK[3];
        this.secIndex4 = this.secBaseIndex4 = BLOCK[4];
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.calculateCardDamage(m);
        AbstractPower index = p.getPower("Index");
        if(index != null){
            if(index.amount == 1) {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.index1, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(p, p, this.secIndex1));
            }
            else if(index.amount == 2) {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.index2, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(p, p, this.secIndex2));
            }
            else if(index.amount == 3) {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.index3, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(p, p, this.secIndex3));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.index4, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(p, p, this.secIndex4));
            }
            AbstractPower linkedList = p.getPower("LinkedListPower");
            if(index.amount < 4 || linkedList != null) {
                index.stackPower(1);
            }
        } else{
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new IndexPower(p, p, 1), 1));
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, this.index0, this.damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(p, p, this.secIndex0));
        }

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        calcDamage calcD = new calcDamage(AbstractDungeon.player, mo);
        this.index0 = calcD.calculateDamage(this.baseIndex0);
        this.index1 = calcD.calculateDamage(this.baseIndex1);
        this.index2 = calcD.calculateDamage(this.baseIndex2);
        this.index3 = calcD.calculateDamage(this.baseIndex3);
        this.index4 = calcD.calculateDamage(this.baseIndex4);
        if(this.index0 != this.baseIndex0){
            this.isIndexModified = true;
        }
        else{
            this.isIndexModified = false;
        }
        calcBlock calcB = new calcBlock(AbstractDungeon.player);
        this.secIndex0 = calcB.calculateBlock(this.secBaseIndex0);
        this.secIndex1 = calcB.calculateBlock(this.secBaseIndex1);
        this.secIndex2 = calcB.calculateBlock(this.secBaseIndex2);
        this.secIndex3 = calcB.calculateBlock(this.secBaseIndex3);
        this.secIndex4 = calcB.calculateBlock(this.secBaseIndex4);
        if(this.secIndex0 != this.secBaseIndex0){
            this.secIsIndexModified = true;
        }
        else{
            this.secIsIndexModified = false;
        }
    }

    @Override
    public void applyPowers() {
        calculateCardDamage(null);
        super.applyPowers();
    }

    @Override
    public void atTurnStart() {
        calculateCardDamage(null);
        super.atTurnStart();
    }

    // Upgraded stats.
    @Override
    // Upgrades cost to 1
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeIndex(UPGRADE_PLUS_DAMAGE);
            this.upgradeSecIndex(UPGRADE_PLUS_BLOCK);
            this.initializeDescription();
        }
    }
}
