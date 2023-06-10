package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.commons.lang3.ObjectUtils;
import theCoder.TheCoderMod;
import theCoder.characters.TheCoder;
import theCoder.powers.DarkModePower;
import theCoder.powers.ItsAFeaturePower;
import theCoder.powers.LinkedListPower;

import static theCoder.TheCoderMod.makeCardPath;
public class CardItsAFeature extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardItsAFeature.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("ItsAFeature.png");
    // use JSON to get the name and description of card
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int ARMOR = 3;
    private static final int UPGRADE_PLUS_ARMOR = 1;
    // /STAT DECLARATION/

    public CardItsAFeature() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = ARMOR;
        this.cardsToPreview = new Dazed();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new ItsAFeaturePower(p, this.magicNumber)));
        AbstractDungeon.actionManager.addToBottom(
                new MakeTempCardInDrawPileAction(new Dazed(), 1, true, false, false));
    }
    // Upgraded stats.
    @Override
    // Upgrades cost to 1
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_ARMOR);
            this.initializeDescription();
        }
    }
}
