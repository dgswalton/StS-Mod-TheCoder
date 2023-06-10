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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.EndTurnDeathPower;
import theCoder.TheCoderMod;
import theCoder.characters.TheCoder;

import static theCoder.TheCoderMod.makeCardPath;
public class CardRestart extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardRestart.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Restart.png");
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

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;
    private static final int HEAL = 10;
    // /STAT DECLARATION/

    public CardRestart() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = HEAL;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.getCurrRoom().monsters.monsters.forEach(monster -> {
            this.addToBot(new HealAction(monster, p, this.magicNumber));
            monster.powers.clear();
        });
        this.addToBot(new HealAction(p, p, this.magicNumber));
        p.powers.clear();
    }

    // Upgraded stats.
    @Override
    // Upgrades cost to 1
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
            this.initializeDescription();
        }
    }
}
