package Heuristic;

import tetrisGame.TetrisMap;
// Shitty heuristic

public class ClearLineBonus extends Heuristic{

	@Override
	public int calculate(TetrisMap gm) {
		return gm.getLinesCleared()*weight;
	}

}
