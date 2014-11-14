package Heuristic;

import tetrisGame.TetrisMap;
import tetrisGame.TetrisMap.Block;

public class RowTransitions extends Heuristic{

	@Override
	public double calculate(TetrisMap gm) {
		if( weight==0) return 0;
		int transitions = 0;
		int highest = gm.getHighestColSize();
		for(int rows=0; rows<highest; ++rows){
			Block blockLast = Block.Fill;
			for(int cols=0; cols<gm.col;++cols){
				Block block;
				try{
					 block = gm.map.get(rows).get(cols);
				}catch(IndexOutOfBoundsException e){
					block = Block.Blank;
				}
				if( blockLast != block){
					++transitions;
				}
			}
		}
		return transitions*weight;
	}
}
