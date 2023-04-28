package theCoder.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import theCoder.TheCoderMod;
import theCoder.util.TextureLoader;

import static org.lwjgl.util.mapped.MappedObject.foreach;

public class ItsAFeaturePower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = TheCoderMod.makeID("ItsAFeaturePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theCoderResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theCoderResources/images/powers/placeholder_power32.png");

    private static boolean isUpgraded;
    public ItsAFeaturePower(final AbstractCreature owner, final int amount) {
        this.name = NAME;
        this.ID = "ItsAFeaturePower";
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.updateDescription();

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount > 999) {
            this.amount = 999;
        }

        this.updateDescription();
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner != this.owner && info.type != DamageType.HP_LOSS && info.type != DamageType.THORNS && damageAmount > 0) {
            this.flash();
            this.stackPower(1);
        }

    }

    public void onRemove() {
        if (!this.owner.isPlayer) {
            this.addToBot(new ChangeStateAction((AbstractMonster)this.owner, "ARMOR_BREAK"));
        }
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        this.flash();
        this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
    }
}
