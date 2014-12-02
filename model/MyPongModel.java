package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

public class MyPongModel implements PongModel {
	private String leftPlayer;
	private String rightPlayer;
	private Dimension field;
	private Point ballPos;
	
	private int barPosLeft = 300;
	private int barPosRight = 300;
	private int barHeightLeft = 200;
	private int barHeightRight = 200;
	
	private String scoreLeft = "0";
	private String scoreRight = "0";
	
	private String message;
	
	
	
	public MyPongModel(String leftPlayer, String rightPlayer){
	this.leftPlayer = leftPlayer;
	this.rightPlayer = rightPlayer;
	this.field = new Dimension(1200, 600);
	this.ballPos = new Point(600, 300);
	}
	
	
	@Override
	public void compute(Set<Input> input, long delta_t) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getBarPos(BarKey k) {
		switch(k){
		case LEFT:
			return barPosLeft;
		case RIGHT: 
			return barPosRight;
		default:
			return 0;
		}
		
	}

	@Override
	public int getBarHeight(BarKey k) {
		switch(k){
		case LEFT:
			return barHeightLeft;
		case RIGHT: 
			return barHeightRight;
		default:
			return 0;
		}
	}

	@Override
	public Point getBallPos() {
	return this.ballPos.getLocation();
	}

	@Override
	public String getMessage() {
		
		return "Pong";
	}

	@Override
	public String getScore(BarKey k) {
		switch(k){
		case LEFT:
			return scoreLeft;
		case RIGHT: 
			return scoreRight;
		default:
			return null;
		}
	}

	@Override
	public Dimension getFieldSize() {
	return this.field.getSize();	
	}
}
