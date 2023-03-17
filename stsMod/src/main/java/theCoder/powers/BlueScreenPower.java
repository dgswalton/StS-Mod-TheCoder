package theCoder.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.Settings;
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
import com.megacrit.cardcrawl.vfx.combat.OmegaFlashEffect;
import theCoder.TheCoderMod;
import theCoder.util.TextureLoader;

import java.util.Iterator;

import static org.lwjgl.util.mapped.MappedObject.foreach;

public class BlueScreenPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = TheCoderMod.makeID("BlueScreenPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theCoderResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theCoderResources/images/powers/placeholder_power32.png");
    public BlueScreenPower(final AbstractCreature owner, final int newAmount) {
        this.name = NAME;
        this.ID = "BlueScreenPower";
        this.owner = owner;
        this.amount = newAmount;
        this.type = PowerType.BUFF;
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        this.updateDescription();

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.flash();
            Iterator var2 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var2.hasNext()) {
                AbstractMonster m = (AbstractMonster)var2.next();
                if (m != null && !m.isDeadOrEscaped()) {
                    if (Settings.FAST_MODE) {
                        this.addToBot(new VFXAction(new OmegaFlashEffect(m.hb.cX, m.hb.cY)));
                    } else {
                        this.addToBot(new VFXAction(new OmegaFlashEffect(m.hb.cX, m.hb.cY), 0.2F));
                    }
                }
            }

            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.amount, true), DamageType.THORNS, AttackEffect.FIRE, true));
            this.addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), 3, true, false, false));
        }

    }


}