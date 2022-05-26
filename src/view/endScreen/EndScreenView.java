package src.view.endScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndScreenView extends BorderPane {
	Text text;
	Button button;
	LineChart<Number, Number> chart;

	public EndScreenView(boolean playerWon) {
		initializeNodes(playerWon);
		layoutNodes();
	}

	private void initializeNodes(boolean playerWon) {
		text = playerWon ? new Text("You won!") : new Text("You lost!");
		text.setFont(new Font(70));

		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Turns");
		xAxis.setTickUnit(1);

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Total points");
		yAxis.setTickUnit(1);

		chart = new LineChart<>(xAxis, yAxis);

		button = new Button("Return to main menu");
	}

	private void layoutNodes() {
		setTop(text);
		setAlignment(text, Pos.CENTER);
		setCenter(chart);
		setBottom(button);
		setAlignment(button, Pos.CENTER);
		setMargin(button, new Insets(10));
		button.setFocusTraversable(false);
	}

	Button getButton() {
		return button;
	}

	public LineChart<Number, Number> getChart() {
		return chart;
	}
}
