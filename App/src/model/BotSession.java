package App.src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BotSession extends PlayerSession {

	public BotSession(String name, boolean startingPlayer) {
		super(name, startingPlayer);
	}

	private NumberField chooseColoredNumber(DicePool coloredDicePool, DicePool publicDicePool) {
		Random random = new Random();
		final HashMap<Color, ArrayList<NumberField>> coloredNumberFields = getScoreCard().getColoredNumberFields(coloredDicePool, publicDicePool);
		final ArrayList<NumberField> numberFields = coloredNumberFields.get(Color.random());
		return numberFields.get(random.nextInt(numberFields.size()));
	}


	private NumberField choosePublicNumber(int total) {
		final HashMap<Color, NumberField> publicNumberFields = getScoreCard().getPublicNumberFields(total);
		return publicNumberFields.get(Color.random());
	}

	public void takeTurn(int total, DicePool coloredDicePool, DicePool publicDicePool) {
		choosePublicNumber(total).setCrossed();
		if (isActivePlayer()) {
			chooseColoredNumber(coloredDicePool, publicDicePool).setCrossed();
		}
	}
}
