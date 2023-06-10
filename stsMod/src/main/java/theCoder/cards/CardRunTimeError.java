package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theCoder.TheCoderMod;
import theCoder.characters.TheCoder;

import static theCoder.TheCoderMod.makeCardPath;
public class CardRunTimeError extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardRunTimeError.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // create image path
    public static final String IMG = makeCardPath("RunTimeError.png");
    // get name and description from JSON
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int DAMAGE = 0;
    private static final int ATTACK_TIMES = 10;
    // number of times this attack hits for
    private static final int UPGRADE_PLUS_TIMES = 5;

    // /STAT DECLARATION/

    public CardRunTimeError() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block
        this.baseDamage = DAMAGE;
        // making "magic number" which just changes the in game string seen on card
        this.magicNumber = this.baseMagicNumber = ATTACK_TIMES;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // the for loop is to repeat the attack animation and damage as many times as I need
        // This card will deal damage and have an animation
        for(int i=0; i<ATTACK_TIMES; i++) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    // Upgraded stats.
    @Override
    // Upgrades block +4
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_TIMES);
            this.initializeDescription();
        }
    }
}
