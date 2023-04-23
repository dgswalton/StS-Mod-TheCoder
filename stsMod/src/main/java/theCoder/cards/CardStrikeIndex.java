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
import com.megacrit.cardcrawl.powers.AbstractPower;
import theCoder.TheCoderMod;
import theCoder.actions.calcDamage;
import theCoder.characters.TheCoder;
import theCoder.powers.IndexPower;
import theCoder.powers.RarePower;

import static theCoder.TheCoderMod.makeCardPath;
public class CardStrikeIndex extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardStrikeIndex.class.getSimpleName());
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");

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
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block
        this.indexDamage0 = this.baseIndexDamage0 = DAMAGE[0];
        this.indexDamage1 = this.baseIndexDamage1 = DAMAGE[1];
        this.indexDamage2 = this.baseIndexDamage2 = DAMAGE[2];
        this.indexDamage3 = this.baseIndexDamage3 = DAMAGE[3];
        this.indexDamage4 = this.baseIndexDamage4 = DAMAGE[4];
        // making this a strike according to the game
        // so cards like "perfected strike" will scale
        this.tags.add(CardTags.STRIKE);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        System.out.println(indexDamage0);
        this.calculateCardDamage(m);
        System.out.println(indexDamage0);
        AbstractPower index = p.getPower("Index");
        if(index != null){
            if(index.amount == 1)
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.indexDamage1, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            else if(index.amount == 2)
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.indexDamage2, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            else if(index.amount == 3)
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.indexDamage3, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            else
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, this.indexDamage4, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            AbstractPower linkedList = p.getPower("LinkedListPower");
            if(index.amount < 4 || linkedList != null) {
                index.stackPower(1);
            }
        } else{
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new IndexPower(p, p, 1), 1));
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, this.indexDamage0, this.damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        calcDamage calc = new calcDamage(AbstractDungeon.player, mo);
        indexDamage0 = calc.calculateDamage(baseIndexDamage0);
        indexDamage1 = calc.calculateDamage(baseIndexDamage1);
        indexDamage2 = calc.calculateDamage(baseIndexDamage2);
        indexDamage3 = calc.calculateDamage(baseIndexDamage3);
        indexDamage4 = calc.calculateDamage(baseIndexDamage4);
        if(indexDamage0 != baseIndexDamage0){
            isIndexDamageModified = true;
        }
        else{
            isIndexDamageModified = false;
        }
    }

    // Upgraded stats.
    @Override
    // Upgrades cost to 1
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeIndexDamage(UPGRADE_PLUS_DAMAGE);
            this.initializeDescription();
        }
    }
}
