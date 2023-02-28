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
import com.megacrit.cardcrawl.powers.StrengthPower;
import theCoder.TheCoderMod;
import theCoder.characters.TheCoder;
import java.util.Random;

import static theCoder.TheCoderMod.makeCardPath;
public class CardIntermittentBug extends CustomCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardIntermittentBug.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");
    // use JSON to get the name and description of card
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 4;
    private static final int DAMAGE = 20;
    private static final int ENEMY_STR = 5;
    private static final int ENEMY_HEAL = 20;
    private static final int UPGRADE_PLUS_BLOCK = 1;
    private static int ACTION = -1;

    // /STAT DECLARATION/

    public CardIntermittentBug() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card choose one of the four actions at random
        Random rand = new Random();
        ACTION = rand.nextInt(4);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = ENEMY_STR;
        this.baseHeal = ENEMY_HEAL;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 25% chance of insta killing enemy if they are not boss
        if((ACTION == 0) && (m.type != AbstractMonster.EnemyType.BOSS)){
            AbstractDungeon.actionManager.addToBottom(new InstantKillAction(m));
        }
        // 50% chance of dealing 10 damage if enemy is a boss
        // 25% chance of dealing 10 damage if enemy is not a boss
        else if ((ACTION == 1) || (ACTION == 0)){
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        // 25% chance of giving an enemy 2 strength
        else if (ACTION == 2){
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(m, p, new StrengthPower(m, this.magicNumber),
                            this.magicNumber));
        }
        // 25% chance of healing enemy by 10
        else{
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(m, p, baseHeal));
        }
    }

    // Upgraded stats.
    @Override
    // Upgrades cost to 1
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.initializeDescription();
        }
    }
}
