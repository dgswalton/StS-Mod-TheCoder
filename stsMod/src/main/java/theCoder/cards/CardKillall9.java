/*package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: [TESTING] Kill all monster processes; Testing dynamic variable: !theCoder:TEST_VAR!;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theCoder.TheCoderMod;
import theCoder.characters.TheCoder;
import theCoder.helpers.TheCoderCardTags;

import static theCoder.TheCoderMod.makeCardPath;
public class CardKillall9 extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

/*    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardKillall9.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");
    // use JSON to get the name and description of card
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int DAMAGE = 9999;

    // /STAT DECLARATION/

    public CardKillall9() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        // making card do damage and gain block
        this.baseDamage = DAMAGE;

        // making this a strike according to the game
        // so cards like "perfected strike" will scale
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);

        // Add a custom tag
        tags.add(TheCoderCardTags.TEST_TAG);

        // Update the testing Custom Variable
        this.testVar = 4200;

        // Add 1 if the custom tag was successfully applied
        if (hasTag(TheCoderCardTags.TEST_TAG)) {
            this.testVar++;
        }

        // [Testing] Add the Pointer tag and update the card name
//        tags.add(TheCoderCardTags.POINTER_TARGET); // This card was pointed to using the card Pointer
//        updateDisplayName(NAME);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // This card will deal damage and have an animation
        AbstractDungeon.getMonsters().monsters.forEach(monster -> monster.die());
    }

    @Override
    // For this card, disallow upgrades
    public boolean canUpgrade() {
        return false;
    }

    // Upgraded stats.
    @Override
    // Upgrade not applicable for this card, but we still need to override anyways
    public void upgrade() {
    }
}
*/