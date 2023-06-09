package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theCoder.TheCoderMod;
import theCoder.actions.calcDamage;
import theCoder.characters.TheCoder;
import theCoder.powers.IndexPower;

import static theCoder.TheCoderMod.makeCardPath;
public class CardStrikeIndex extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardStrikeIndex.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int[] DAMAGE = {4,8,12,16,20};
    private static final int UPGRADE_PLUS_DAMAGE = 2;

    // /STAT DECLARATION/

    public CardStrikeIndex() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block
        this.index0 = this.baseIndex0 = DAMAGE[0];
        this.index1 = this.baseIndex1 = DAMAGE[1];
        this.index2 = this.baseIndex2 = DAMAGE[2];
        this.index3 = this.baseIndex3 = DAMAGE[3];
        this.index4 = this.baseIndex4 = DAMAGE[4];
        // making this a strike according to the game
        // so cards like "perfected strike" will scale
        this.tags.add(CardTags.STRIKE);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.calculateCardDamage(m);
        AbstractPower index = p.getPower("Index");
        if(index != null){
            if(index.amount == 1)
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.index1, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            else if(index.amount == 2)
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.index2, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            else if(index.amount == 3)
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.index3, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            else
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.index4, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
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
        }

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        calcDamage calc = new calcDamage(AbstractDungeon.player, mo);
        this.index0 = calc.calculateDamage(this.baseIndex0);
        this.index1 = calc.calculateDamage(this.baseIndex1);
        this.index2 = calc.calculateDamage(this.baseIndex2);
        this.index3 = calc.calculateDamage(this.baseIndex3);
        this.index4 = calc.calculateDamage(this.baseIndex4);
        if(this.index0 != this.baseIndex0){
            this.isIndexModified = true;
        }
        else{
            this.isIndexModified = false;
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
            this.initializeDescription();
        }
    }
}
