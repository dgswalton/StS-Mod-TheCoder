package theCoder.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.CollectPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import theCoder.powers.ForLoopPower;

public class calcDamage {
    private AbstractPlayer player;
    private AbstractMonster monster;
    public calcDamage(AbstractPlayer p, AbstractMonster m) {
        this.player = p;
        this.monster = m;
    }

    public int calculateDamage(int d) {
        float damage = d;
        AbstractPower strength = this.player.getPower("Strength");
        if(strength != null){
            damage += strength.amount;
        }
        if(this.monster != null){
            AbstractPower vulnerable = this.monster.getPower("Vulnerable");
            AbstractRelic frog = this.player.getRelic("Paper Frog");
            if(vulnerable != null){
                if(frog != null){
                    damage = (damage *7) /4;
                } else {
                    damage = (damage *3) /2;
                }
            }
        }
        AbstractPower weak = this.player.getPower("Weak");
        if(weak != null){
            damage = (damage *3) /4;
        }
        if(this.player.stance.ID.equals("Wrath")){
            damage *= 2;
        } else if(this.player.stance.ID.equals("Divinity")){
            damage *= 3;
        }
        return (int) damage;
    }
}

