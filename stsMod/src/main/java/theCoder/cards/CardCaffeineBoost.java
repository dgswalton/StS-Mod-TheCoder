package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theCoder.TheCoderMod;
import theCoder.characters.TheCoder;
import theCoder.powers.LoseDrawNextTurn;
import theCoder.powers.LoseEnergyNextTurn;

import static theCoder.TheCoderMod.makeCardPath;
public class CardCaffeineBoost extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardCaffeineBoost.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("CaffeineBoost.png");
    // use JSON to get the name and description of card
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int DRAW = 3;
    private static final int ENERGY = 2;
    private static final int UPGRADE_PLUS_DRAW = 2;
    private static final int UPGRADE_PLUS_ENERGY = 1;

    // /STAT DECLARATION/

    public CardCaffeineBoost() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = DRAW;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber = ENERGY;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber, false));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.defaultSecondMagicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new LoseDrawNextTurn(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new LoseEnergyNextTurn(p, this.defaultSecondMagicNumber), this.defaultSecondMagicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.upgradeMagicNumber(UPGRADE_PLUS_DRAW);
            this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_ENERGY);
            this.initializeDescription();
        }
    }
}
