package tetrisBot;

import tetrisGame.TetrisMap;
import tetrisGame.Tetromino;
import tetrisGame.TetrominoAbs;
import tetrisGame.TetrominoBag;
import tetrisGame.TetrominoI;
import tetrisGame.TetrominoO;
import tetrisGame.TetrominoS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import Heuristic.*;
public class Bot {
	public TetrisMap gm;
	private Vector<Heuristic> listHeur;
	public Vector<TetrisMap> gmList;
	public Vector<Integer> scores;
	private PrintWriter writer;
	
	public Bot(){
		gm = new TetrisMap();
		listHeur= new Vector<>();
		scores = new Vector<>();
		gmList = new Vector<>();
		Heuristic h;
		h = new LowAsPossible().setWeight(3);
		listHeur.add(h);
		h = new PunishColHoles().setWeight(6);
		listHeur.add(h);
		h = new Smooth().setWeight(0);
		listHeur.add(h);
		h = new AvoidGameOver().setWeight(100);
		listHeur.add(h);
		h = new Wells().setWeight(1	);
		listHeur.add(h);
	}
	public Bot(boolean print, Vector<Integer> weights){
		gm = new TetrisMap();
		listHeur= new Vector<>();
		scores = new Vector<>();
		gmList = new Vector<>();
		Heuristic h;
		h = new Wells().setWeight(weights.get(0));
		listHeur.add(h);
		h = new Smooth().setWeight(weights.get(1));
		listHeur.add(h);
		h = new LowAsPossible().setWeight(weights.get(2));
		listHeur.add(h);
		h = new PunishColHoles().setWeight(weights.get(3));
		listHeur.add(h);
		h = new ClearLineBonus().setWeight(weights.get(4));
		listHeur.add(h);
		h = new LandingHeight().setWeight(weights.get(5));
		listHeur.add(h);
		
		
		h = new AvoidGameOver().setWeight(100);
		listHeur.add(h);
		if(print){
			File debugFile = new File("TetrisBot.debug");
			debugFile.delete();
			try {
				debugFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer = new PrintWriter(debugFile, "UTF-8");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void createGmList(TetrominoAbs piece){
		gmList.clear();
		scores.clear();
		for(int i=0; i< piece.getSides(); ++i){
			for(int col=0; col+piece.getCol()<gm.col+1; ++col){
				TetrisMap gmTemp = new TetrisMap(gm);
				gmTemp.addTetromino(col, piece);
				gmList.add(gmTemp);
			}
			piece.rotateRight();
		}
	}
	public int calculateScore(TetrisMap map){
		int score = 0;
		for(Heuristic h: listHeur){
			score+=h.calculate(map);
		}
		return score;
	}
	public void calculateScores(){
		for(TetrisMap gm: gmList){
			scores.add(calculateScore(gm));
		}
	}
	public void pickLowestScoreAsGm(){
		int highest = Integer.MAX_VALUE;
		int index=0;
		for(int i=0; i<scores.size(); ++i){
			if( scores.get(i) < highest){
				highest= scores.get(i);
				index = i;
			}
		}
		gm = gmList.get(index);
	}
	public void next(TetrominoAbs piece){
		createGmList(piece);
		calculateScores();
		pickLowestScoreAsGm();
	}
	public void writeln(String line){
		if(writer != null){
			writer.println(line);
			writer.flush();
		}
	}
	public void writeClose(){
		if(writer!= null){
			writer.close();
		}
	}
	static void customBot(Vector<Integer> weights, int seed, 
			boolean print, int pieces){
		TetrominoBag bag = new TetrominoBag(seed);
		Bot bot = new Bot(print, weights);
		int linesClrCount = 0;
		int i;
		for( i=1; i<pieces;++i){
			TetrominoAbs piece = bag.next();
			bot.next(piece);
			linesClrCount+= bot.gm.getLinesCleared();
			bot.writeln(bot.gm.printMap());
			if(bot.gm.isGameOver()){
				System.out.println("game over:" + i);
				break;
			}
	
		}
//		System.out.println(bot.gm.printMap());
//		System.out.println(bot.gm.getHighestColSize());
		System.out.println("pieces placed: " + i+", linesCleared: " + linesClrCount);
		bot.writeClose();
	}
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Vector<Integer> weights = new Vector<>();
		weights.add(1); //well
		weights.add(0); //smooth
		weights.add(3); //lowAsPossible
		weights.add(6); //punishHoles
		weights.add(0); //clearLineBonus
		weights.add(0); //LandingHeight
						// avoidGameOver(100);
//		customBot(weights,7,true,40000);
		for(int i=0; i<10; ++i){
			customBot(weights, i, false, 40000);
			System.out.println("Iteration: " +i);
		}
	}
	/**
	 *  Features, diagonal, Wells
	 */

}
