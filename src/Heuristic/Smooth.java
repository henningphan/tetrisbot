package Heuristic;

import java.util.LinkedList;

import tetrisGame.TetrisMap;
import tetrisGame.TetrisMap.Block;

public class Smooth extends Heuristic {


	@Override
	public int calculate(TetrisMap gm) {
		int lastHeight = gm.map.get(0).size();
		int count=0;
		for(int i=1; i<gm.map.size()-1; ++i){
			LinkedList<Block>list = gm.map.get(i);
			count +=Math.abs(lastHeight-list.size());
		}
		return count*weight;
	}

}
