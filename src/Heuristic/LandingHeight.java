package Heuristic;

import tetrisGame.TetrisMap;

public class LandingHeight extends Heuristic {

	@Override
	public double calculate(TetrisMap gm) {
		
		return gm.map.get(gm.getcolLast()).size()*weight;
	}

}
