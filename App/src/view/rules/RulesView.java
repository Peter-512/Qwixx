package App.src.view.rules;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RulesView extends BorderPane {

    private VBox vBox;
    private Text rulesText;
    private Text paragraphText;
    private Button backButton;

    public RulesView(){
        initializeNodes();
        layoutNodes();

    }

    private void initializeNodes(){
        rulesText= new Text("Rules");
        rulesText.setFont(new Font(70));
        paragraphText = new Text("""
                Qwixx is a simple multiplayer game. Each player has a score sheet with four rows of 11 numbers.
                There is a six sided die in the color of each row and two white dice. Players take turns rolling the six dice, after which two actions these two actions are carried out in order:
                1-All players may tick the sum of the white dice in a color of their choice. 
                2-The active player may add up one white and one colored die and tick the sum in the row of the colored die
                If you wish to cross out the number at the extreme right end of a color-row (red/yellow 12, green/ blue 2) you must have first crossed out at least five numbers in that row.
                The dice of this color is now removed and the row is locked for everyone.The game is over when a second color is locked or when a player has ticked all penalty boxes.""" );
        paragraphText.setFont(new Font(15));
        paragraphText.setWrappingWidth(500);
        backButton = new Button("Back");

        vBox = new VBox(rulesText,paragraphText);
    }

    private void layoutNodes(){
        setBottom(backButton);
        backButton.setAlignment(Pos.CENTER);
        setMargin(backButton,new Insets(10));

        setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
    }

    Text getRules(){return rulesText;}
    Text getParagraph(){return paragraphText;}
    Button getBackButton(){return backButton;}

}
