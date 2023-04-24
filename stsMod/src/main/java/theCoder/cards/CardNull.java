package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.EndTurnDeathPower;
import theCoder.TheCoderMod;
import theCoder.characters.TheCoder;

import static theCoder.TheCoderMod.makeCardPath;
public class CardNull extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardNull.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");
    // use JSON to get the name and description of card
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int MAGIC = 0;

    // /STAT DECLARATION/

    public CardNull() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        calcMagic(p);
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.magicNumber, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, this.magicNumber));
        if(this.magicNumber > 10) {
            AbstractDungeon.actionManager.addToBottom(
                    new DrawCardAction(p, 10, false));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(
                    new DrawCardAction(p, this.magicNumber, false));
        }
    }

    @Override
    public void applyPowers() {
        calcMagic(AbstractDungeon.player);
    }
    private void calcMagic(AbstractPlayer p){
        this.magicNumber = this.baseMagicNumber;
        AbstractPower strength = p.getPower("Strength");
        AbstractPower dexterity = p.getPower("Dexterity");
        AbstractPower index = p.getPower("Index");
        if(strength != null){
            this.magicNumber += strength.amount;
        }
        if(dexterity != null){
            this.magicNumber += dexterity.amount;
        }
        if(this.upgraded && index != null){
            this.magicNumber += index.amount;
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
