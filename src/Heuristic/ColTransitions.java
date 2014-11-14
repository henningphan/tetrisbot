package Heuristic;

import tetrisGame.TetrisMap;
import tetrisGame.TetrisMap.Block;

public class ColTransitions extends Heuristic{

	@Override
	public double calculate(TetrisMap gm) {
		int transitions=0;
		
		for(int cols=0; cols<gm.col; ++cols){
			Block blockLast = Block.Fill;
			for(int rows=0;rows<gm.row;++rows){
				if(rows >= gm.map.get(cols).size()) break;
				Block block = gm.map.get(cols).get(rows);
				if( blockLast != block){
					++transitions;
					blockLast = block;
				}
			}
		}
		return transitions*weight;
	}

}
