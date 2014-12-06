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
	
	private String message = "Pong";
	
	public static int xspeed = 5;
	public static int yspeed = 0;
	public static final int barSpeed = 8; 
	
	public MyPongModel(String leftPlayer, String rightPlayer){
	this.leftPlayer = leftPlayer;
	this.rightPlayer = rightPlayer;
	this.field = new Dimension(1200, 600);
	this.ballPos = new Point(600, 300);
	}
	
	
	@Override
	public void compute(Set<Input> input, long delta_t) {
	
	/* barens förflyttelse vvvvvvvvv */
	
		for(Input i : input){
		switch(i.key){
		case LEFT:
			switch(i.dir){
			case UP:
			if(this.barPosLeft >= (barHeightLeft / 2)){
			this.barPosLeft  = barPosLeft - barSpeed;
			break;
			}
			break;
			case DOWN:
			if(barPosLeft <= field.getHeight() - (barHeightLeft / 2)){	
			this.barPosLeft  = barPosLeft + barSpeed;
			break;
			}
			break;
			}
			break;
		case RIGHT:
			switch(i.dir){
			case UP:
			if(this.barPosRight >= (barHeightRight / 2)){
			this.barPosRight  = barPosRight - barSpeed;
			break;
			}
			break;
			case DOWN:
			if(this.barPosRight <= field.getHeight() - (barHeightRight / 2)){
			this.barPosRight  = barPosRight + barSpeed;
			break;
			}
			break;
			}
			break;
      	}
	}
	/* barens förflyttelse ^^^^^^^^^*/
		
		
	/* bollens förflyttelse  vvvvv*/
		
		
	if(getBallPos().getX() >= 1170
			&& getBallPos().getY() <= barPosRight + (barHeightRight / 2) + 10 
			&& getBallPos().getY() >= barPosRight - (barHeightRight / 2) - 10){
	
		if((barPosRight - (barHeightRight/2)) > getBallPos().getY() - 9  && (barPosRight - (barHeightRight/2) < getBallPos().getY() + 10)){
			if(yspeed > 0 ){
				yspeed = yspeed * -1;
			}
			
			if(yspeed == 0){
			 yspeed = -5;	
			}
		}
		
		if((barPosRight + (barHeightRight/2)) > getBallPos().getY() -10 && (barPosRight + (barHeightRight/2) < getBallPos().getY() + 9)){
			if(yspeed < 0 ){
				yspeed = yspeed * -1;
			}
			
			if(yspeed == 0){
			 yspeed = 5;	
			}
		}
		
		
		
			
		if(xspeed > 20){
		xspeed = -1 * xspeed;
		ballPos.setLocation(getBallPos().getX() + xspeed , getBallPos().getY() + yspeed);
	}
		xspeed = -1 * (xspeed + 5);
		ballPos.setLocation(getBallPos().getX() + xspeed , getBallPos().getY() + yspeed );
    }
	if(getBallPos().getX() <= 30  
			&& getBallPos().getY() <= barPosLeft + (barHeightLeft / 2) + 10 
			&& getBallPos().getY() >= barPosLeft - (barHeightLeft / 2) - 10){
		
		if((barPosLeft - (barHeightLeft/2)) > getBallPos().getY() -9 && (barPosLeft - (barHeightLeft/2) < getBallPos().getY() + 10)){
			if(yspeed > 0 ){
				yspeed = yspeed * -1;
			}
			
			if(yspeed == 0){
			 yspeed = -5;	
			}
		}
		if((barPosLeft + (barHeightLeft/2)) > getBallPos().getY() -10 && (barPosLeft + (barHeightLeft/2) < getBallPos().getY() + 9) ){
			if(yspeed < 0 ){
				yspeed = yspeed * -1;
			}
			
			if(yspeed == 0){
			 yspeed = 5;	
			}
		}
		
		
		if(xspeed < -20){
			xspeed = -1 * xspeed ;
			ballPos.setLocation(getBallPos().getX() + xspeed , getBallPos().getY() + yspeed);
		}
		xspeed = -1 * (xspeed - 5) ;
		ballPos.setLocation(getBallPos().getX() + xspeed , getBallPos().getY() + yspeed);
	}   
	if(getBallPos().getX() > 1200){
		int sl = Integer.parseInt(this.scoreLeft);
		sl++;
		this.scoreLeft = "";
		scoreLeft = sl + "";
		if(sl == 10){
			scoreLeft = "0";
			scoreRight = "0";
			this.message = "PLAYER LEFT WINS!";				
		}
		this.message = "Pong";
		ballPos = new Point(600, 300);
	    xspeed = -5;
	    yspeed = 0;
	}

	if (getBallPos().getX() < 0){
		int sr = Integer.parseInt(this.scoreRight);
		sr = sr + 5;
		this.scoreRight = "";
		scoreRight = sr + "";
		if(sr == 10){
			scoreLeft = "0";
			scoreRight = "0";
			this.message = "PLAYER RIGHT WINS!";
		}
	
			
		this.message = "Pong";
	    ballPos = new Point(600, 300);
	    xspeed = 5; 
	    yspeed = 0;
	}

	if(getBallPos().getY() <= 10 || getBallPos().getY() >= 590){
		yspeed = -1 * yspeed;
	}
	
		ballPos.setLocation(getBallPos().getX() + xspeed , getBallPos().getY() + yspeed);

				
	
	}
	
	/* bollen förflyttelse ^^^^^^ */
	


	/**
	 * Returns the position of a BarKey.
	 * @param k a BarKey.
	 * @return The position of k.
	 */
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
	/**
	 * Returns the height of a Barkey.
	 * @param k a  BarKey.
	 * @return The height of k.
	 */
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
	/**
	 * Returns the position of the ball.
	 * @return the position of Ball. 
	 */
	@Override
	public Point getBallPos() {
	return this.ballPos.getLocation();
	}
	/**
	 * Return the message.
	 * @return message	
	 */
	@Override
	public String getMessage() {
		
		return message;
	}

	/**
	 * Returns the score of a BarKey.
	 * @param k to get the score from.
	 * @return the score of k.
	 */
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

	/**
	 * Returns the field size.
	 * @return size of field.
	 */
	@Override
	public Dimension getFieldSize() {
	return this.field.getSize();	
	}
}
