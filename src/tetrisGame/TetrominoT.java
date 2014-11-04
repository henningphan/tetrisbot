package tetrisGame;

import java.util.LinkedList;
import java.util.Vector;

import tetrisGame.TetrisMap.Block;

public class TetrominoT extends TetrominoAbs{

	public TetrominoT(){
		super.map = new Vector<LinkedList<Block>>();
		LinkedList<Block> list = new LinkedList<>();
		list.add(Block.Fill);
		super.map.add(list);
		list = new LinkedList<>();
		list.add(Block.Fill);
		list.add(Block.Fill);
		super.map.add(list);
		list = new LinkedList<>();
		list.add(Block.Fill);
		super.map.add(list);
		super.col=3;
		super.row=2;
	}
	@Override
	public int getSides() {
		return 4;
	}
	public String toString(){
		return "Tetris piece: T";
	}
}
