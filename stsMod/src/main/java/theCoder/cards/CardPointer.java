package theCoder.cards;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theCoder.TheCoderMod;
import theCoder.characters.TheCoder;

import java.util.Iterator;

import static theCoder.TheCoderMod.makeCardPath;
public class CardPointer extends AbstractCoderCard implements OnObtainCard {
    public static final String ID = TheCoderMod.makeID(CardPointer.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // path to picture
    public static final String IMG = makeCardPath("Attack.png");
    // use JSON to get the name and description of card
    //public static final String NAME = cardStrings.NAME;
    public static String NAME = "";
    // public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static String DESCRIPTION = ""; // override since this card's description is dynamic

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheCoder.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static AbstractCard targetCard = null;  // The card we are pointing at
    private boolean cardSelected = true; // A boolean to indicate whether or not we selected a card
    // (It's set to false on Equip)

    public CardPointer() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        updateName(); // Update the card name
        updateDescription(); // Update the card description


    }

    // Update the name for this card
    private void updateName() {
        NAME = cardStrings.NAME + " -> "; // Basic card name starting text
        originalName = NAME + "null"; // Original card name points to null

        if (targetCard != null)
            NAME += targetCard.name; // ... else use the target card's actual name
        name = NAME; // Save the name in the main class (AbstractCard)
        this.initializeTitle();
    }

    // Update the name for this card
    private void updateDescription() {
        DESCRIPTION = cardStrings.DESCRIPTION + "\""; // Basic card description starting text
        if (targetCard == null)
            DESCRIPTION += "null"; // If there's no target card, use "null"
        else
            DESCRIPTION += targetCard.name; // ... else use the target card's actual name
        DESCRIPTION += "\";"; // Description ending text
        rawDescription = DESCRIPTION; // Save the description in the main class (AbstractCard)
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m){

    }

    public void upgrade() {
        if (!upgraded) {
            updatePointer(); // Use the upgrade function to reset the pointer
            this.upgradeName();
        }
    }

    public void onObtainCard() {
        //System.out.println(">>CardPointer:onObtainCard");
        updatePointer(); // Update the card to which we are pointing
    }

    // Get the list of cards in the library without any Pointer cards
    public CardGroup getMasterDeckWithoutPointerCards() {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (!c.name.equals(NAME)) {
                retVal.addToTop(c);
            }
        }

        return retVal;
    }

    // Update card to which we are pointing
    private void updatePointer() {
        targetCard = null; // blank out current card (if any)
        updateName(); // blank out the name
        updateDescription(); // blank out the description

        // Based on code from BottledPlaceholderRelic
        cardSelected = false; // By default, we haven't selected a card yet
        /*if (AbstractDungeon.isScreenUp) { // 3. If the map is open - hide it.
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }*/
        //AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        // 4. Set the room to INCOMPLETE - don't allow us to use the map, etc.
        //CardGroup group = CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck); // 5. Get a card group of all currently unbottled cards
        CardGroup group = getMasterDeckWithoutPointerCards(); // Get a version of the master deck without any reference to Pointer cards
        AbstractDungeon.gridSelectScreen.open(group, 1, "Select a target card for this pointer", false, false, false, false);
        // 6. Open the grid selection screen with the cards from the CardGroup we specified above. The description reads "Select a card to bottle for" + (relic name) + "."







        /*
        AbstractDungeon.gridSelectScreen.selectedCards.clear();

        //AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck,1,false,"Select a card to point to TEST");
        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1,"TESTING SELECT CARD",false,false,false,false);
        //while(AbstractDungeon.gridSelectScreen.selectedCards.isEmpty());
        System.out.println("Checking for selected cards...");
        AbstractDungeon.gridSelectScreen.selectedCards.forEach(card -> {
            System.out.print(">>>CardPointer: Selected ");
            System.out.println(card.name);
        });
        System.out.println("...done");
        */
    }

    public void update() {
        super.update();
        //System.out.println("CardPointer:update()");

        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            // If the card hasn't been bottled yet and we have cards selected in the gridSelectScreen (from onEquip)
            cardSelected = true; //Set the cardSelected boolean to be true - we're about to bottle the card.
            targetCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0); // The custom Savable "card" is going to equal
            // The card from the selection screen (it's only 1, so it's at index 0)
            /*
            BottledPlaceholderField.inBottledPlaceholderField.set(card, true); // Use our custom spire field to set that card to be bottled.
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.INCOMPLETE) {
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            }
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE; // The room phase can now be set to complete (From INCOMPLETE in onEquip)
            AbstractDungeon.gridSelectScreen.selectedCards.clear(); // Always clear your grid screen after using it.*/
            //setDescriptionAfterLoading(); // Set the description to reflect the bottled card (the method is at the bottom of this file)
            updateName();
            updateDescription();
        }




    }
}
