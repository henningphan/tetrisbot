package Heuristic;

import java.util.LinkedList;

import tetrisGame.TetrisMap;
import tetrisGame.TetrisMap.Block;

public class LowAsPossible extends Heuristic {


	@Override
	public int calculate(TetrisMap gm) {
		int maxHeigt=0;
		for(LinkedList<Block> li: gm.map){
			maxHeigt = li.size()>maxHeigt?li.size():maxHeigt;
		}
		return maxHeigt*weight;
	}
	public String toString(){
		return "LowAsPossible " + weight;
	}
}
