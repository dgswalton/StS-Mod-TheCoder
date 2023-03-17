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
import theCoder.characters.TheCoder;
import theCoder.powers.IndexPower;
import theCoder.powers.RarePower;

import static theCoder.TheCoderMod.makeCardPath;
public class CardStrikeIndex extends CustomCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardStrikeIndex.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");
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
    private static final int[] DAMAGE = {4,8,12,16,20};
    private static final int[] UPGRADED_DAMAGE = {6,10,14,18,22};
    private static final int UPGRADE_PLUS_DAMAGE = 2;

    // /STAT DECLARATION/

    public CardStrikeIndex() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block
        this.baseDamage = DAMAGE[0];

        // making this a strike according to the game
        // so cards like "perfected strike" will scale
        this.tags.add(CardTags.STRIKE);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower index = p.getPower("Index");
        if(index != null){
            AbstractPower linkedList = p.getPower("LinkedListPower");
            if(index.amount < 4 || linkedList != null) {
                index.stackPower(1);
            }
        } else{
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new IndexPower(p, p, 1), 1));
        }
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
    public void applyPowers() {
        AbstractPower index = AbstractDungeon.player.getPower("Index");
        if(index != null){
            if(index.amount<4) {
                if(upgraded){
                    this.baseDamage = UPGRADED_DAMAGE[index.amount];
                } else{
                    this.baseDamage = DAMAGE[index.amount];
                }
            } else{
                if(upgraded){
                    this.baseDamage = UPGRADED_DAMAGE[4];
                } else {
                    this.baseDamage = DAMAGE[4];
                }
            }
        }
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
