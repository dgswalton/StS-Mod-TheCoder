package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
public class CardBasicBug extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardBasicBug.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");
    // use JSON to get the name and description of card
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = -2;
    private static final int DAMAGE = 15;
    private static final int DAMAGE_TO_SELF = 5;
    private static final int UPGRADE_PLUS_DAMAGE = 5;

    // /STAT DECLARATION/

    public CardBasicBug() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = DAMAGE_TO_SELF;
    }

    // Actions the card should do.

    // this card will have an action played when drawn
    public void triggerWhenDrawn() {
        // This card will deal damage and have an animation
        this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        // this card will deal damage to self and have an animation
        this.addToBot(new DamageAction(AbstractDungeon.player,
                new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageInfo.DamageType.THORNS),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        // this will exhaust
        this.addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    // Upgraded stats.
    @Override
    // Upgrades cost to 1
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
            this.initializeDescription();
        }
    }
}
