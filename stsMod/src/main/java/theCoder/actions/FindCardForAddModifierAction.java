package theCoder.actions;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.random.Random;
import theCoder.helpers.TheCoderCardTags;
//import thePackmaster.SpireAnniversary5Mod;
//import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.List;
public class FindCardForAddModifierAction extends AbstractGameAction {
    private static final UIStrings uiSTRINGS = CardCrawlGame.languagePack.getUIString("theCoder:AddModifierUI");
    private AbstractCardModifier mod;
    private int count;
    private boolean random;
    private CardGroup targetgroup;
    private Predicate<AbstractCard> requirements;
    private AbstractCard input = null;


    public FindCardForAddModifierAction(AbstractCardModifier mod, int count, boolean random, CardGroup targetgroup, Predicate<AbstractCard> requirements, AbstractCard inputCard) {
        this.mod = mod;
        this.count = count;
        this.random = random;
        this.targetgroup = targetgroup;
        this.requirements = requirements;
        this.input = inputCard;
    }

    public FindCardForAddModifierAction(AbstractCardModifier mod, int count, boolean random, CardGroup targetgroup) {
        this.mod = mod;
        this.count = count;
        this.random = random;
        this.targetgroup = targetgroup;
        this.requirements = new Predicate<AbstractCard>() {
            @Override
            public boolean test(AbstractCard abstractCard) {
                return true;
            }
        };
    }

    public FindCardForAddModifierAction(AbstractCardModifier mod, int count, boolean random, CardGroup targetgroup, Predicate<AbstractCard> requirements) {
        this.mod = mod;
        this.count = count;
        this.random = random;
        this.targetgroup = targetgroup;
        this.requirements = requirements;
    }

    @Override
    public void update() {
        //Predicate<AbstractCard> tagCheck = card -> !card.hasTag(SpireAnniversary5Mod.ISCARDMODIFIED);
        Predicate<AbstractCard> tagCheck = card -> !card.hasTag(TheCoderCardTags.CANT_MOD);

        if (random) {
            ArrayList<AbstractCard> chosen = new ArrayList<>();
            ArrayList<AbstractCard> potentialTargets = new ArrayList<>();

            for (AbstractCard c : targetgroup.group) {
                if (requirements.and(tagCheck).test(c)) {
                    potentialTargets.add(c);
                }
            }
            AbstractCard n;

            if (count >= potentialTargets.size()) {
                chosen.addAll(potentialTargets);
            } else {
                for (int i = 0; i < count; i++) {
                    if (potentialTargets.isEmpty()) {
                        isDone = true;
                        break;
                    } else {
                        n = getRandomItem(potentialTargets, AbstractDungeon.cardRandomRng);
                        potentialTargets.remove(n);
                        chosen.add(n);
                    }
                }
            }

            if (!chosen.isEmpty()) {
                for (AbstractCard c2 : chosen) {
                    CardModifierManager.addModifier(c2, mod);
                    c2.superFlash(Color.CHARTREUSE.cpy());
                }
            }
        } else {
            AbstractDungeon.actionManager.addToBottom(new SelectCardsAction(targetgroup.group, count, uiSTRINGS.TEXT[0], false,
                    requirements.and(tagCheck),

                    (cards) -> {
                        for (AbstractCard c2 : cards) {
                            CardModifierManager.addModifier(c2, mod);
                            c2.superFlash(Color.CHARTREUSE.cpy());
                        }
                    }
            ));
        }
        isDone = true;
    }
    public static <T> T getRandomItem(List<T> list, Random rng) {
        return list.isEmpty() ? null : list.get(rng.random(list.size() - 1));
    }
}