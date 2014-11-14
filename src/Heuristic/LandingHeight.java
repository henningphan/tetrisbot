package Heuristic;

import tetrisGame.TetrisMap;

public class LandingHeight extends Heuristic {

	@Override
	public double calculate(TetrisMap gm) {
		if( weight == 0) return 0;
		return gm.map.get(gm.getcolLast()).size()*weight;
	}

}
