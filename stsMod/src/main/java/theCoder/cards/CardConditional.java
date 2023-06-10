package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
public class CardConditional extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardConditional.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Conditional.png");
    // use JSON to get the name and description of card
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_DAMAGE = 4;
    private static final int UPGRADE_PLUS_BLOCK = 4;

    // /STAT DECLARATION/

    public CardConditional() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block
        this.baseDamage = DAMAGE;
        this.baseBlock = BLOCK;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m.intent == AbstractMonster.Intent.ATTACK
                || m.intent == AbstractMonster.Intent.ATTACK_BUFF
                || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF
                || m.intent == AbstractMonster.Intent.ATTACK_DEFEND){
            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(p, p, this.block));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

    }

    // Upgraded stats.
    @Override
    // Upgrades cost to 1
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.initializeDescription();
        }
    }
}
