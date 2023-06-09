package theCoder.modifiers;


import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theCoder.helpers.TheCoderCardTags;
//import thePackmaster.SpireAnniversary5Mod;

public abstract class AbstractMod extends AbstractCardModifier {
    public int value;

    public AbstractMod(int valueIn){
        this.value = valueIn;
    }

    public AbstractMod(){
        this.value = 0;
    }

    @Override
    public String modifyName(String cardName, AbstractCard card) {
        return cardName;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.tags.add(TheCoderCardTags.IS_MODDED);
    }
}