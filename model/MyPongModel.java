<<<<<<< HEAD
package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

public class MyPongModel implements PongModel {
    private String leftPlayer;
    private String rightPlayer;
    private Dimension field;
    private Point ballPos;
	
    private int barPosLeft = 300	;
    private int barPosRight = 300;
    private int barHeightLeft = 200;
    private int barHeightRight = 200;
    public static int initBarHeight = 200;
	
    private String scoreLeft = "0";
    private String scoreRight = "0";
	
    private String message = "Pong";
	
    public static int xspeed = 5;
    public static int yspeed = 0;
    public static final int barSpeed = 8; 
	
    public boolean newGame = false;
	
    public MyPongModel(String leftPlayer, String rightPlayer){
	this.leftPlayer  = leftPlayer;
	this.rightPlayer = rightPlayer;
	this.field       = new Dimension(1200, 600);
	this.ballPos     = new Point(600, 300);
    }
	
    /* för testning */
    public MyPongModel(){
	this.leftPlayer  = leftPlayer;
	this.rightPlayer = rightPlayer;
	this.field       = new Dimension(1200, 600);
	this.ballPos     = new Point(600, 300);
    }
	
		
	
    public void compute(Set<Input> input, long delta_t) {
	
	if(newGame == true){
	    try {
		Thread.sleep(5000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	newGame = false;
	this.message = "Pong";
	/* barens förflyttelse vvvvvvvvv */
	
	for(Input i : input){
	    switch(i.key){
	    case LEFT:
		switch(i.dir){
		case UP:
		    if(barPosLeft  >= (barHeightLeft / 2)){
			barPosLeft  = barPosLeft - barSpeed;
			break;
		    }
		    break;
		case DOWN:
		    if(barPosLeft  <= field.getHeight() - (barHeightLeft / 2)){	
			barPosLeft  = barPosLeft + barSpeed;
			break;
		    }
		    break;
		}
		break;
	    case RIGHT:
		switch(i.dir){
		case UP:
		    if(barPosRight  >= (barHeightRight / 2)){
			barPosRight  = barPosRight - barSpeed;
			break;
		    }
		    break;
		case DOWN:
		    if(barPosRight  <= field.getHeight() - (barHeightRight / 2)){
			barPosRight  = barPosRight + barSpeed;
			break;
		    }
		    break;
		}
		break;
	    }
	}
	/* barens förflyttelse ^^^^^^^^^*/
		
		
	/* bollens förflyttelse  vvvvv*/
		
		
	if(getBallPos().getX()    >= 1170
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
		
		
		
			
	    if(xspeed  > 20){
		xspeed = -1 * xspeed;
		ballPos.setLocation(getBallPos().getX() + xspeed, getBallPos().getY() + yspeed);
	    }
	    xspeed = -1 * (xspeed + 5);
	    ballPos.setLocation(getBallPos().getX() + xspeed, getBallPos().getY() + yspeed );
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
		ballPos.setLocation(getBallPos().getX() + xspeed, getBallPos().getY() + yspeed);
	    }
	    xspeed = -1 * (xspeed - 5) ;
	    ballPos.setLocation(getBallPos().getX() + xspeed, getBallPos().getY() + yspeed);
	}   
	if(getBallPos().getX() > 1200){
	    int sl = Integer.parseInt(this.scoreLeft);
	    int sr = Integer.parseInt(this.scoreRight);
	    sl++;
	    this.scoreLeft = "";
	    scoreLeft = sl + "";
		
	    if  (sl - sr < 3) {
		barHeightRight = initBarHeight;
		barHeightLeft  = initBarHeight;
	    }
	    if ((sl - sr >=3) && (sl -sr <5)) {
		barHeightRight = initBarHeight + 20;
	    }
	    if ((sl - sr >= 5) && (sl - sr <7)){
		barHeightRight = initBarHeight + 50;
		barHeightLeft  =  initBarHeight;
	    }
	    if (sl - sr == 7) { 
	    	barHeightLeft = 100;
	    }
		
	    if(sl == 10){
		scoreLeft  = "0";
		scoreRight = "0";
		this.message = "PLAYER LEFT WINS!"; //gör en paus här
		barHeightLeft  = initBarHeight;
		barHeightRight = initBarHeight;
		barPosLeft  = 300;
		barPosRight = 300;
		newGame = true;
	    }
		
		
	    ballPos = new Point(600, 300);
	    xspeed  = -5;
	    yspeed  = 0;
	}

	if (getBallPos().getX() < 0){
	    int sr = Integer.parseInt(this.scoreRight);
	    int sl = Integer.parseInt(this.scoreLeft);
	    sr = sr + 1;
	    this.scoreRight = "";
	    scoreRight = sr + "";
	    if  (sr - sl < 3) {
		barHeightLeft  = initBarHeight;
		barHeightRight = initBarHeight;
	    }
	    if ((sr - sl >=3) && (sr -sl <5)) {
		barHeightLeft = initBarHeight + 20;
	    }
	    if ((sr - sl >= 5) && (sr - sl <7)){
		barHeightLeft  = initBarHeight + 50;
		barHeightRight =  initBarHeight;
	    }
	    if (sr - sl == 7) { 
	    	barHeightRight = 100;
	    }
		
	    if(sr == 10){
		scoreLeft  = "0";
		scoreRight = "0";
		this.message   = "PLAYER RIGHT WINS!"; //gör en paus här
		barHeightLeft  = initBarHeight;
		barHeightRight = initBarHeight;
		barPosLeft  = 300;
		barPosRight = 300;
		newGame = true;
	    }
		
			
		
	    ballPos = new Point(600, 300);
	    xspeed = 5; 
	    yspeed = 0;
	}


	
	if(getBallPos().getY() <= 21 || getBallPos().getY() >= 579){
	    yspeed = -1 * yspeed;
	}
	
	ballPos.setLocation(getBallPos().getX() + xspeed, getBallPos().getY() + yspeed);

				
	
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
    // For testing 
    public int getXSpeed(){
	return this.xspeed;
    }
}
=======
/* last edit 8/12-14 20:08*/

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
    public static int initBarHeight = 200;
	
    private String scoreLeft = "0";
    private String scoreRight = "0";
	
    private String message = "Pong";
	
    public static int xspeed = 5;
    public static int yspeed = 0;
    public static final int barSpeed = 8; 
	
    public boolean newGame = false;
	
    /** Creates the gamefield
     *  @param leftplayer controls the left bar
     * @param  rightplayer controls the right bar
     *
     *
     */
         public MyPongModel(String leftPlayer, String rightPlayer){
	this.leftPlayer  = leftPlayer;
	this.rightPlayer = rightPlayer;
	this.field       = new Dimension(1200, 600);
	this.ballPos     = new Point(600, 300);
    }
    /** Creates the gamefield*/	
    /* för testning */
    public MyPongModel(){
	this.leftPlayer  = leftPlayer;
	this.rightPlayer = rightPlayer;
	this.field       = new Dimension(1200, 600);
	this.ballPos     = new Point(600, 300);
    }
	
		
    /**All the physics in the game , controlls the bars , score and the ball 
     * @param input from the keyboard
     * @param delta_t I DONT KNOW !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */	
    public void compute(Set<Input> input, long delta_t) {
	
	if(newGame == true){
	    try {
		Thread.sleep(5000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	newGame = false;
	this.message = "Pong";
	/* barens förflyttelse vvvvvvvvv */
	
	for(Input i : input){  // move the bars depending on the keyboard input
	    switch(i.key){
	    case LEFT:
		switch(i.dir){
		case UP:
		    if(barPosLeft  >= (barHeightLeft / 2)){  
			barPosLeft  = barPosLeft - barSpeed;
			break;
		    }
		    break;
		case DOWN:
		    if(barPosLeft  <= field.getHeight() - (barHeightLeft / 2)){	
			barPosLeft  = barPosLeft + barSpeed;
			break;
		    }
		    break;
		}
		break;
	    case RIGHT:
		switch(i.dir){
		case UP:
		    if(barPosRight  >= (barHeightRight / 2)){
			barPosRight  = barPosRight - barSpeed;
			break;
		    }
		    break;
		case DOWN:
		    if(barPosRight  <= field.getHeight() - (barHeightRight / 2)){
			barPosRight  = barPosRight + barSpeed;
			break;
		    }
		    break;
		}
		break;
	    }
	}
	/* barens förflyttelse ^^^^^^^^^*/
		
		
	/* bollens förflyttelse  vvvvv*/
		
		
	if(getBallPos().getX()    >= 1170   //if the ball hits the right bar
	   && getBallPos().getY() <= barPosRight + (barHeightRight / 2) + 10
	   && getBallPos().getY() >= barPosRight - (barHeightRight / 2) - 10){  
	
	    if((barPosRight - (barHeightRight/2)) > getBallPos().getY() - 9  && (barPosRight - (barHeightRight/2) < getBallPos().getY() + 10)){  // if not the whole ball hits the top of the right bar
		if(yspeed > 0 ){
		    yspeed = yspeed * -1;
		}
			
		if(yspeed == 0){
		    yspeed = -5;	// the ball will not go straight anymore
		}
	    }
		
	    if((barPosRight + (barHeightRight/2)) > getBallPos().getY() -10 && (barPosRight + (barHeightRight/2) < getBallPos().getY() + 9)){  // if no the whole ball hits the bottom of the right bar 
		if(yspeed < 0 ){
		    yspeed = yspeed * -1;
		}
			
		if(yspeed == 0){
		    yspeed = 5;	// the ball will not go straight anymore
		}
	    }
		
		
		
			
	    if(xspeed  > 20){  // dont increse the speed if its to high, just change the direction of the ball
		xspeed = -1 * xspeed;
		ballPos.setLocation(getBallPos().getX() + xspeed, getBallPos().getY() + yspeed);
	    }
	    xspeed = -1 * (xspeed + 5); // increse the speed and change the direction
	    ballPos.setLocation(getBallPos().getX() + xspeed, getBallPos().getY() + yspeed );
	}
	if(getBallPos().getX() <= 30  //if the ball hits the left bar
	   && getBallPos().getY() <= barPosLeft + (barHeightLeft / 2) + 10 
	   && getBallPos().getY() >= barPosLeft - (barHeightLeft / 2) - 10){
		
	    if((barPosLeft - (barHeightLeft/2)) > getBallPos().getY() -9 && (barPosLeft - (barHeightLeft/2) < getBallPos().getY() + 10)){ // if not the whole ball hits the top of the left bar
		if(yspeed > 0 ){
		    yspeed = yspeed * -1;
		}
			
		if(yspeed == 0){
		    yspeed = -5; // the ball will not go straight anymore	
		}
	    }
	    if((barPosLeft + (barHeightLeft/2)) > getBallPos().getY() -10 && (barPosLeft + (barHeightLeft/2) < getBallPos().getY() + 9) ){// if no the whole ball hits the bottom of the left bar 
		if(yspeed < 0 ){
		    yspeed = yspeed * -1;
		}
			
		if(yspeed == 0){
		    yspeed = 5;	 // the ball will not go straight anymore
		}
	    }
		
		
	    if(xspeed < -20){   // dont increse the speed if its to high, just change the direction of the ball
		xspeed = -1 * xspeed ;
		ballPos.setLocation(getBallPos().getX() + xspeed, getBallPos().getY() + yspeed);
	    }
	    xspeed = -1 * (xspeed - 5) ;// increse the speed of the ball and change the direction
	    ballPos.setLocation(getBallPos().getX() + xspeed, getBallPos().getY() + yspeed);
	}   
	if(getBallPos().getX() > 1200){  // if the left player scores increase he score by one 
	    int sl = Integer.parseInt(this.scoreLeft);
	    int sr = Integer.parseInt(this.scoreRight);
	    sl++;
	    this.scoreLeft = "";
	    scoreLeft = sl + "";
		
	    if  (sl - sr < 3) {  // give the right player handicap if the left player have more goals scored
		barHeightRight = initBarHeight;
		barHeightLeft  = initBarHeight;
	    }
	    if ((sl - sr >=3) && (sl -sr <5)) {
		barHeightRight = initBarHeight + 20;
	    }
	    if ((sl - sr >= 5) && (sl - sr <7)){
		barHeightRight = initBarHeight + 50;
		barHeightLeft  =  initBarHeight;
	    }
	    if (sl - sr == 7) { 
	    	barHeightLeft = 100;
	    }
		
	    if(sl == 10){  
		scoreLeft  = "0";
		scoreRight = "0";
		this.message = "PLAYER LEFT WINS!"; //gör en paus här
		barHeightLeft  = initBarHeight;
		barHeightRight = initBarHeight;
		barPosLeft  = 300;
		barPosRight = 300;
		newGame = true;
	    }
		
		
	    ballPos = new Point(600, 300); // reset the ball with winner serve
	    xspeed  = -5;
	    yspeed  = 0;
	}

	if (getBallPos().getX() < 0){   // if the left player scores increase he score by on
	    int sr = Integer.parseInt(this.scoreRight);
	    int sl = Integer.parseInt(this.scoreLeft);
	    sr = sr + 1;
	    this.scoreRight = "";
	    scoreRight = sr + "";
	    if  (sr - sl < 3) {   // give the left player handicap if the right player have more goals scored
		barHeightLeft  = initBarHeight;
		barHeightRight = initBarHeight;
	    }
	    if ((sr - sl >=3) && (sr -sl <5)) {
		barHeightLeft = initBarHeight + 20;
	    }
	    if ((sr - sl >= 5) && (sr - sl <7)){
		barHeightLeft  = initBarHeight + 50;
		barHeightRight =  initBarHeight;
	    }
	    if (sr - sl == 7) { 
	    	barHeightRight = 100;
	    }
		
	    if(sr == 10){  // if the right player has made 10 goals the right payer wins
		scoreLeft  = "0";
		scoreRight = "0";
		this.message   = "PLAYER RIGHT WINS!"; //gör en paus här
		barHeightLeft  = initBarHeight;
		barHeightRight = initBarHeight;
		barPosLeft  = 300;
		barPosRight = 300;
		newGame = true;
	    }
		
			
		
	    ballPos = new Point(600, 300);  // reset the ball with winner serve
	    xspeed = 5; 
	    yspeed = 0;
	}


	
	if(getBallPos().getY() <= 10 || getBallPos().getY() >= 590){   // if the ball hits the top or bottom wall, then bounce 
	    yspeed = -1 * yspeed;
	}
	
	ballPos.setLocation(getBallPos().getX() + xspeed, getBallPos().getY() + yspeed);

				
	
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
<<<<<<< HEAD
	}
	
	/* FÖR TESTNING */
	public int getXSpeed(){
		return this.xspeed;
	}
}
=======
    }


    // For testing 
    public int getXSpeed(){
	return this.xspeed;
    }
}
>>>>>>> baed823bfba72071189a2fd1d139ede50ccd3d46
