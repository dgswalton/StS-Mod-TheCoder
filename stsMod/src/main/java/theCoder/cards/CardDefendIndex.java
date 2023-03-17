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

import static theCoder.TheCoderMod.PLACEHOLDER_POTION_HYBRID;
import static theCoder.TheCoderMod.makeCardPath;
public class CardDefendIndex extends CustomCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardDefendIndex.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");
    // use JSON to get the name and description of card
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int[] BLOCK = {4,8,12,16,20};
    private static final int[] UPGRADED_BLOCK = {6,10,14,18,22};

    private static final int UPGRADE_PLUS_BLOCK = 2;
    // /STAT DECLARATION/

    public CardDefendIndex() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block
        this.baseBlock = BLOCK[0];

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
                new GainBlockAction(p, p, this.block));
    }
    public void applyPowers() {
        AbstractPower index = AbstractDungeon.player.getPower("Index");
        if(index != null){
            if(index.amount<4) {
                if(upgraded){
                    this.baseBlock = UPGRADED_BLOCK[index.amount];
                } else{
                    this.baseBlock = BLOCK[index.amount];
                }
            } else{
                if(upgraded){
                    this.baseBlock = UPGRADED_BLOCK[4];
                } else {
                    this.baseBlock = BLOCK[4];
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
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.initializeDescription();
        }
    }
}
