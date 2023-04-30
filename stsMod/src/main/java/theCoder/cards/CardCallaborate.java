package theCoder.cards;

// Type: Common Attack
// Cost: 2
// Description: Deal 8 damage; Gain 8 block;
// Upgrade: Cost -> 1

import basemod.abstracts.CustomCard;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.DamageHooks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theCoder.TheCoderMod;
import theCoder.actions.calcDamage;
import theCoder.characters.TheCoder;

import static theCoder.TheCoderMod.makeCardPath;
public class CardCallaborate extends AbstractCoderCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = TheCoderMod.makeID(CardCallaborate.class.getSimpleName());
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
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 2;

    // /STAT DECLARATION/

    public CardCallaborate() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.getCurrRoom().monsters.monsters.forEach(monster -> {
            if(!monster.isDeadOrEscaped()){
                if(monster.intent == AbstractMonster.Intent.ATTACK
                        || monster.intent == AbstractMonster.Intent.ATTACK_BUFF
                        || monster.intent == AbstractMonster.Intent.ATTACK_DEBUFF
                        || monster.intent == AbstractMonster.Intent.ATTACK_DEFEND){
                    int d = monster.getIntentBaseDmg();
                    double calc = d;
                    AbstractPower strength = monster.getPower("Strength");
                    if(strength != null){
                        calc += strength.amount;
                    }
                    AbstractPower vulnerable = m.getPower("Vulnerable");
                    if(vulnerable != null){
                        calc = (calc * 3) / 2;
                    }
                    AbstractPower flight = m.getPower("Flight");
                    if(flight != null){
                        calc = calc / 2;
                    }
                    AbstractPower weak = monster.getPower("Weak");
                    if(weak != null){
                        calc = (calc * 3) / 4;
                    }
                    d = (int) calc;

                    AbstractDungeon.actionManager.addToBottom(
                            new DamageAction(m, new DamageInfo(p, d, this.damageTypeForTurn),
                                    AbstractGameAction.AttackEffect.SMASH));
                }
            }
        });
    }

    // Upgraded stats.
    @Override
    // Upgrades cost to 1
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
