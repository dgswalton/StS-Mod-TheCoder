package theCoder.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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

public class LinkedListPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = TheCoderMod.makeID("LinkedListPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theCoderResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theCoderResources/images/powers/placeholder_power32.png");

    private static boolean isUpgraded;
    public LinkedListPower(final AbstractCreature owner, final boolean upgraded) {
        this.name = NAME;
        this.ID = "LinkedListPower";
        this.owner = owner;
        this.isUpgraded = upgraded;
        this.type = PowerType.BUFF;
        this.updateDescription();

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }
    public void updateDescription() {
        AbstractPower index = AbstractDungeon.player.getPower("Index");
        if(index != null) {
            if(this.isUpgraded) {
                this.amount = 2 * index.amount;
            } else {
                this.amount = index.amount;
            }
        } else {
            this.amount = 0;
        }
        this.description = DESCRIPTIONS[0] + this.amount;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.updateDescription();
    }

    public void onVictory() {
        this.updateDescription();
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.heal(this.amount);
        }

    }
}
