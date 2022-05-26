package src.view.endScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class EndScreenView extends BorderPane {
	Text whoWonText;
	Text endState;
	Button button;
	LineChart<Number, Number> chart;

	VBox vBox;

	public EndScreenView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		whoWonText = new Text();
		whoWonText.setFont(new Font(70));

		endState = new Text();

		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Turns");
		xAxis.setTickUnit(1);

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Total points");
		yAxis.setTickUnit(1);

		chart = new LineChart<>(xAxis, yAxis);

		vBox = new VBox(whoWonText, endState);

		button = new Button("Return to main menu");
	}

	private void layoutNodes() {
		setTop(vBox);
		vBox.setAlignment(Pos.CENTER);
		setCenter(chart);
		setBottom(button);
		setAlignment(button, Pos.CENTER);
		setMargin(button, new Insets(10));
		button.setFocusTraversable(false);
		vBox.setSpacing(20);
	}


	Text getEndState() {
		return endState;
	}

	Button getButton() {
		return button;
	}

	Text getWhoWonText() {
		return whoWonText;
	}

	LineChart<Number, Number> getChart() {
		return chart;
	}
}
