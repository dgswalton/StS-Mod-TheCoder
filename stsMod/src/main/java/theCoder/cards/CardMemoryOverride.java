/*package theCoder.cards;

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
public class CardMemoryOverride extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION
/*
    public static final String ID = TheCoderMod.makeID(CardMemoryOverride.class.getSimpleName());
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
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 1;

    // /STAT DECLARATION/

    public CardMemoryOverride() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // trying to create a new "intent" of target monster
        // basicly if somthing is going to do big damage, and you dont want it to
        // use this card to make it go to its next intent
        /*System.out.println("---------------------------");
        System.out.println(m.nextMove);
        //AbstractDungeon.actionManager.addToBottom(new RollMoveAction(m));
        m.rollMove();
        System.out.println(m.nextMove);
        m.rollMove();
        System.out.println(m.nextMove);
        m.rollMove();
        System.out.println(m.nextMove);
        m.rollMove();
        System.out.println(m.nextMove);
        m.rollMove();
        System.out.println(m.nextMove);
        m.rollMove();
        System.out.println(m.nextMove);
        m.rollMove();
        System.out.println(m.nextMove);
        if (m.nextMove == 4) {
            m.setMove((byte)3, AbstractMonster.Intent.ATTACK, ((DamageInfo)m.damage.get(0)).base);
        } else {
            m.setMove(m.MOVES[0], (byte) 4, AbstractMonster.Intent.BUFF);
        }
        System.out.println(m.nextMove);*/
 /*   }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.exhaust = false;
            this.initializeDescription();
        }
    }
}
*/