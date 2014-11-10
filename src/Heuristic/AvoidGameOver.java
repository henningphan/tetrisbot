package Heuristic;

import tetrisGame.TetrisMap;

public class AvoidGameOver extends Heuristic {

	@Override
	public double calculate(TetrisMap gm) {
		return gm.isGameOver()?super.weight:0;
	}

}
