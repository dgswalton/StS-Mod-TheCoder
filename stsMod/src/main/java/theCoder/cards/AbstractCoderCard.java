package theCoder.cards;
import basemod.abstracts.CustomCard;
import theCoder.helpers.TheCoderCardTags;

public abstract class AbstractCoderCard extends CustomCard {

    // Custom Abstract Cards can be a bit confusing. While this is a simple base for simply adding a second magic number,
    // if you're new to modding I suggest you skip this file until you know what unique things that aren't provided
    // by default, that you need in your own cards.

    // In this example, we use a custom Abstract Card in order to define a new magic number. From here on out, we can
    // simply use that in our cards, so long as we put "extends AbstractDynamicCard" instead of "extends CustomCard" at the start.
    // In simple terms, it's for things that we don't want to define again and again in every single card we make.

    public int defaultSecondMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int defaultBaseSecondMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.
    public boolean upgradedDefaultSecondMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public boolean isDefaultSecondMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)
    public int SecMagicValue;
    public int BaseSecMagicValue;
    public boolean upgradedSecMagic;
    public boolean isSecMagicModified;
    // CUSTOM VARIABLE
    public int testVar; // testing custom variable
//    public int[] indexList = new int[4];
//    public int curIdx = 0;

    public int index0;
    public int baseIndex0;
    public int index1;
    public int baseIndex1;
    public int index2;
    public int baseIndex2;
    public int index3;
    public int baseIndex3;
    public int index4;
    public int baseIndex4;
    public boolean upgradedIndex;
    public boolean isIndexModified;
    public int secIndex0;
    public int secBaseIndex0;
    public int secIndex1;
    public int secBaseIndex1;
    public int secIndex2;
    public int secBaseIndex2;
    public int secIndex3;
    public int secBaseIndex3;
    public int secIndex4;
    public int secBaseIndex4;
    public boolean secUpgradedIndex;
    public boolean secIsIndexModified;

    // /CUSTOM VARIABLE/

    public AbstractCoderCard(final String id,
                               final String name,
                               final String img,
                               final int cost,
                               final String rawDescription,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isDefaultSecondMagicNumberModified = false;
        isSecMagicModified = false;
        testVar = -1;
        isIndexModified = false;
        index0 = baseIndex0 = 0;
        index1 = baseIndex1 = 0;
        index2 = baseIndex2 = 0;
        index3 = baseIndex3 = 0;
        index4 = baseIndex4 = 0;
        secIsIndexModified = false;
        secIndex0 = secBaseIndex0 = 0;
        secIndex1 = secBaseIndex1 = 0;
        secIndex2 = secBaseIndex2 = 0;
        secIndex3 = secBaseIndex3 = 0;
        secIndex4 = secBaseIndex4 = 0;
    }

    public void updateDisplayName(String sBaseName) { // Get the display name for this card, including whether it is being pointed to
        name = (hasTag(TheCoderCardTags.POINTER_TARGET) ? "Pointer -> " : "") + sBaseName;
        this.initializeTitle();
    }

    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedDefaultSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            defaultSecondMagicNumber = defaultBaseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isDefaultSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }
        if(upgradedSecMagic){
            SecMagicValue = BaseSecMagicValue;
            isSecMagicModified = true;
        }
        if(upgradedIndex){
            index0 = baseIndex0;
            index1 = baseIndex1;
            index2 = baseIndex2;
            index3 = baseIndex3;
            index4 = baseIndex4;
            isIndexModified = true;
        }
        if(secUpgradedIndex){
            secIndex0 = secBaseIndex0;
            secIndex1 = secBaseIndex1;
            secIndex2 = secBaseIndex2;
            secIndex3 = secBaseIndex3;
            secIndex4 = secBaseIndex4;
            secIsIndexModified = true;
        }

    }

    public void upgradeDefaultSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        defaultBaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedDefaultSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }
    public void upgradeSecMagic(int amount){
        BaseSecMagicValue += amount;
        SecMagicValue = BaseSecMagicValue;
        upgradedSecMagic = true;
    }
    public void upgradeIndex(int amount){
        baseIndex0 += amount;
        index0 = baseIndex0;
        baseIndex1 += amount;
        index1 = baseIndex1;
        baseIndex2 += amount;
        index2 = baseIndex2;
        baseIndex3 += amount;
        index3 = baseIndex3;
        baseIndex4 += amount;
        index4 = baseIndex4;
        upgradedIndex = true;
    }
    public void upgradeSecIndex(int amount){
        secBaseIndex0 += amount;
        secIndex0 = secBaseIndex0;
        secBaseIndex1 += amount;
        secIndex1 = secBaseIndex1;
        secBaseIndex2 += amount;
        secIndex2 = secBaseIndex2;
        secBaseIndex3 += amount;
        secIndex3 = secBaseIndex3;
        secBaseIndex4 += amount;
        secIndex4 = secBaseIndex4;
        secUpgradedIndex = true;
    }
}