package src.view.endScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndScreenView extends BorderPane {
	private Text whoWonText;
	private Text endState;
	private Button button;
	private LineChart<Number, Number> pointsChart;
	private LineChart<Number, Number> durationsChart;
	private Tab pointsTab;
	private Tab durationsTab;
	private Tab vBoxTab;
	private TabPane tabPane;
	private VBox topVBox;
	private VBox tabVBox;

	private Text avgDuration;
	private Text avgNumbersMissed;
	private Text avgPoints;
	private Label avgDurationLabel;
	private Label avgNumbersMissedLabel;
	private Label avgPointsLabel;

	public EndScreenView() {
		initializeNodes();
		layoutNodes();
	}

	private void initializeNodes() {
		NumberAxis xAxisPoints = new NumberAxis();
		xAxisPoints.setLabel("Turn");
		xAxisPoints.setTickUnit(1);

		NumberAxis yAxisPoints = new NumberAxis();
		yAxisPoints.setLabel("Total points");
		yAxisPoints.setTickUnit(1);

		NumberAxis xAxisDurations = new NumberAxis();
		xAxisDurations.setLabel("Turn");
		xAxisDurations.setTickUnit(1);
		xAxisDurations.setLowerBound(1);
		xAxisDurations.setAutoRanging(false);

		NumberAxis yAxisDurations = new NumberAxis();
		yAxisDurations.setLabel("Duration");
		yAxisDurations.setTickUnit(1);

		pointsChart = new LineChart<>(xAxisPoints, yAxisPoints);
		durationsChart = new LineChart<>(xAxisDurations, yAxisDurations);

		endState = new Text();
		whoWonText = new Text();
		whoWonText.setFont(new Font(70));
		topVBox = new VBox(whoWonText, endState);

		avgDurationLabel = new Label("Average Duration");
		avgPointsLabel = new Label("Average Points");
		avgNumbersMissedLabel = new Label("Average missed Numberfields per Action that crossed out a NumberField");
		avgDuration = new Text();
		avgNumbersMissed = new Text();
		avgPoints = new Text();
		tabVBox = new VBox(avgDurationLabel, avgDuration, avgPointsLabel, avgPoints, avgNumbersMissedLabel, avgNumbersMissed);

		button = new Button("Return to main menu");

		pointsTab = new Tab("Points");
		durationsTab = new Tab("Durations");
		vBoxTab = new Tab("Averages");
		tabPane = new TabPane(pointsTab, durationsTab, vBoxTab);
	}

	private void layoutNodes() {
		avgDuration.setFont(new Font(30));
		avgPoints.setFont(new Font(30));
		avgNumbersMissed.setFont(new Font(30));
		avgDurationLabel.setFont(new Font(25));
		avgPointsLabel.setFont(new Font(25));
		avgNumbersMissedLabel.setFont(new Font(25));

		setTop(topVBox);
		topVBox.setAlignment(Pos.CENTER);

		tabVBox.setAlignment(Pos.CENTER);
		tabVBox.setSpacing(20);
		setCenter(tabPane);
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		pointsTab.setContent(pointsChart);
		durationsTab.setContent(durationsChart);
		vBoxTab.setContent(tabVBox);

		setBottom(button);
		setAlignment(button, Pos.CENTER);
		setMargin(button, new Insets(10));
		button.setFocusTraversable(false);
		topVBox.setSpacing(20);
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

	LineChart<Number, Number> getPointsChart() {
		return pointsChart;
	}

	LineChart<Number, Number> getDurationsChart() {
		return durationsChart;
	}

	Text getAvgDuration() {
		return avgDuration;
	}

	Text getAvgNumbersMissed() {
		return avgNumbersMissed;
	}

	Text getAvgPoints() {
		return avgPoints;
	}
}
