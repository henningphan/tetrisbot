package tetrisGame;

import java.util.LinkedList;
import java.util.Vector;

import tetrisGame.TetrisMap.Block;

public class TetrominoZ extends TetrominoAbs{
	
	public TetrominoZ(){
		super.map = new Vector<>();
		LinkedList<Block> list = new LinkedList<>();
		list.add(Block.Blank);
		list.add(Block.Fill);
		super.map.add(list);
		list = new LinkedList<>();
		list.add(Block.Fill);
		list.add(Block.Fill);
		super.map.add(list);
		list = new LinkedList<>();
		list.add(Block.Fill);
		super.map.add(list);
		super.col = 3;
		super.row = 2;
	}
	@Override
	public int getSides() {
		return 2;
	}
	public String toString(){
		return "Tetris piece: Z";
	}

}
