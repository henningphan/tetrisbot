package Heuristic;

import tetrisGame.TetrisMap;
// Shitty heuristic

public class ClearLineBonus extends Heuristic{

	@Override
	public double calculate(TetrisMap gm) {
		return gm.getLinesCleared()*weight;
	}

}
