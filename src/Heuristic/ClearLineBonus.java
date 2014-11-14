package Heuristic;

import tetrisGame.TetrisMap;
// Shitty heuristic

public class ClearLineBonus extends Heuristic{

	@Override
	public double calculate(TetrisMap gm) {
		if(weight == 0) return 0;
		return gm.getLinesCleared()*weight;
	}

}
