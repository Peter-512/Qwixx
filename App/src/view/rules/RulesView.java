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
                There is a six sided die in the color of each row and two white dice. Players take turns rolling the six dice, after which these two actions are carried out in order:
                1. All players may tick the sum of the white dice in a color of their choice.
                2. The active player may add up one white and one colored die and tick the sum in the row of the colored die.
                The active player must choose to do one of these actions at least or take a penalty instead.
                If you wish to cross out the number at the far right end of a row (red/yellow 12, green/blue 2) you must have first crossed out at least five numbers in that row.
                If you do so you can choose to lock that row. The dice of this color will then removed and the row is locked for everyone. 
                The game is over when at the end of an action more than one row is locked or a player has received 4 penalties.
                Players receive points depending on the amount of crossed out fields (locking a row counts towards that) and lose 5 points per penalty.
                Whoever has more points when the game ends, wins.""" );
        paragraphText.setFont(new Font(15));
        paragraphText.setWrappingWidth(500);
        backButton = new Button("Back");

        vBox = new VBox(rulesText,paragraphText);
    }

    private void layoutNodes(){
        setBottom(backButton);
        setAlignment(backButton, Pos.CENTER);
        setMargin(backButton,new Insets(10));

        setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
    }

    Text getRules(){return rulesText;}
    Text getParagraph(){return paragraphText;}
    Button getBackButton(){return backButton;}

}
