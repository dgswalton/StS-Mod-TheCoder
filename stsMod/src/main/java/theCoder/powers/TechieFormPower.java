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

public class TechieFormPower extends AbstractPower {
    public AbstractCreature source;
    public static final String POWER_ID = TheCoderMod.makeID("TechieFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theCoderResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theCoderResources/images/powers/placeholder_power32.png");
    private static final int[] DAMAGE = {3,5,8,13,21};
    public TechieFormPower(final AbstractCreature owner) {
        this.name = NAME;

        this.ID = "TechieFormPower";
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.description = DESCRIPTIONS[0];
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
            AbstractPower index = AbstractDungeon.player.getPower("Index");
            if(index != null){
                if(index.amount < 4){
                    this.amount = DAMAGE[index.amount];
                }
                else{
                    this.amount = DAMAGE[4];
                }
                AbstractPower linkedList = AbstractDungeon.player.getPower("LinkedListPower");
                if(index.amount < 4 || linkedList != null){
                    index.stackPower(1);
                }
            }
            else{
                this.amount = DAMAGE[0];
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IndexPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
            }
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.amount, true), DamageType.THORNS, AttackEffect.FIRE, true));
        }
    }

}