package Heuristic;

import tetrisGame.TetrisMap;

public class LandingHeight extends Heuristic {

	@Override
	public int calculate(TetrisMap gm) {
		
		return gm.map.get(gm.getcolLast()).size()*weight;
	}

}
