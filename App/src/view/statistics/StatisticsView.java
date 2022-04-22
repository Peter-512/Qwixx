package App.src.view.statistics;

import App.src.model.Turn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StatisticsView extends BorderPane {

    private VBox vBox;
    private Text statText;
    private Button backButton;

    public StatisticsView(){
        initializeNodes();
        layoutNodes();
    }

    public void initializeNodes(){
        statText = new Text("Global Statistics");
        statText.setFont(new Font(70));
        backButton = new Button("Back");

        vBox = new VBox(statText,backButton);
    }

    public void layoutNodes(){
        setBottom(backButton);
        setAlignment(backButton, Pos.CENTER);
        setMargin(backButton,new Insets(10));

        setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
    }



    Button getBackButton(){return backButton;}
}

