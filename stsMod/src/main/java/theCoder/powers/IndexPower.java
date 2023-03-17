package theCoder.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theCoder.TheCoderMod;
import theCoder.cards.DefaultRareAttack;
import theCoder.util.TextureLoader;

public class IndexPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = TheCoderMod.makeID("IndexPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("theCoderResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theCoderResources/images/powers/placeholder_power32.png");

    public IndexPower(final AbstractCreature owner, final AbstractCreature source, int amount) {
        this.name = NAME;
        this.description = DESCRIPTIONS[0] + amount;
        this.ID = "Index";
        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 999) {
            this.amount = 999;
        }

        this.updateDescription();
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.canGoNegative = false;
    }

    public void stackPower(int stackAmount) {
        AbstractPower hardCode = AbstractDungeon.player.getPower("HardCodePower");
        if(hardCode != null){
            this.updateDescription();
            return;
        }
        this.fontScale = 8.0F;
        this.amount += stackAmount;

        if (this.amount >= 999) {
            this.amount = 999;
        }
        this.updateDescription();
    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;

        if (this.amount >= 999) {
            this.amount = 999;
        }
        this.updateDescription();
    }

    public void updateDescription() {

        this.description = DESCRIPTIONS[0] + this.amount;
        this.type = PowerType.BUFF;

    }

}
