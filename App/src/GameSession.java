package App.src;

public class GameSession {
	private Player player;
	private Player cpu;
	private DicePool dicePool;

	public GameSession(Player player, Player cpu) {
		this.player = player;
		this.cpu = cpu;
		dicePool = new DicePool();
	}

	public void startGame() {
		dicePool.throwAllDice();
		UI.printDieValues(dicePool);
		UI.printScoreCard(player);
		player.getPossibleNumbers(dicePool);
		int numberInput = UI.askForNumber();
	}
}
