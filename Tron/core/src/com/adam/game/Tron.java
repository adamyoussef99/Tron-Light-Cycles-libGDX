package com.adam.game;


import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Tron extends ApplicationAdapter {
	SpriteBatch batch;
	
	//textures to import images
	Texture main;
	Texture instMenu;
	Texture winImg1;
	Texture winImg2;
	Texture startBut;
	Texture startButHigh;
	Texture instBut;
	Texture instButHigh;
	Texture backBut;
	Texture backButHigh;
	Texture gameImg;
	
	//sprites to draw and move images
	Sprite menu;
	Sprite instruct;
	Sprite p1Win;
	Sprite p2Win;
	Sprite start;
	Sprite startHigh;
	Sprite inst;
	Sprite instHigh;
	Sprite back;
	Sprite backHigh;
	Sprite gameScreen;
	
	//variable to control what screen the user is on
	String page = "main menu";
	
	//determines if game is running or not
	boolean gameOn;
	//variable containing the winner
	String winner;
	
	//player coordinates
	int p1x=100;
	int p1y=250;
	int p2x=400;
	int p2y=250;
	
	//mouse x and y for pressing buttons
	int mx;
	int my;
	
	//speed of players
	int speed1 = 2;
	int speed2 = 2;
	
	//direction of players
	String dir1 = "";
	String dir2 = "";
	
	//ArrayList that holds trails left behind by both players
	ArrayList<Rectangle>trail1 = new ArrayList<Rectangle>();
	ArrayList<Rectangle>trail2 = new ArrayList<Rectangle>();	
	
	//renderer used to draw shapes
	ShapeRenderer shapeRenderer;
		
	@Override
	public void create () {
		batch = new SpriteBatch();
				
		//main menu
		main = new Texture("Menu.jpg");
		menu = new Sprite(main);
		
		//instructions screen
		instMenu = new Texture("Instruct.png");
		instruct = new Sprite(instMenu);
		
		//game screens
		gameImg = new Texture("game screen.jpg");
		gameScreen = new Sprite(gameImg);
		
		//win screens
		winImg1 = new Texture("p1 win.jpg");
		winImg2 = new Texture("p2 win.jpg");
		p1Win = new Sprite(winImg1);
		p2Win = new Sprite(winImg2);
		
		//Buttons
		
		//start
		startBut = new Texture("Start.png");
		start = new Sprite(startBut);
		startButHigh = new Texture("Start high.png");
		startHigh = new Sprite(startButHigh);
		
		//instructions
		instBut = new Texture("Instruction.png");
		inst = new Sprite(instBut);
		instButHigh = new Texture("Instruction high.png");
		instHigh = new Sprite(instButHigh);
		
		//back
		backBut = new Texture("Back.png");
		back = new Sprite(backBut);
		backButHigh = new Texture("Back high.png");
		backHigh = new Sprite(backButHigh);
						
		shapeRenderer = new ShapeRenderer();
		
		//changes directions to starting directions
		dir1 = "RIGHT";
		dir2 = "A";
		
		//starts game
		gameOn = true;
	}
	
	public void update(){
		
		//rectangles to add onto trail ArrayList
		Rectangle p1current = new Rectangle(p1x, p1y, 5, 5);
		Rectangle p2current = new Rectangle(p2x, p2y, 5, 5);
		
		//checks if trails are in ArrayList to determine winner
		if (trail1.contains(p1current)) {
			winner = "PLAYER 2";
			gameOn = false;
		} 
		else if(trail1.contains(p2current)){
			winner = "PLAYER 1";
			gameOn = false;
		}
		else {
			//adds trail if it isn't in the ArrayList
			trail1.add(p1current);
		}
		
		if (trail2.contains(p1current)) {
			winner = "PLAYER 2";
			gameOn = false;
		} 
		else if(trail2.contains(p2current)){
			winner = "PLAYER 1";
			gameOn = false;
		}
		else {
			//adds trail if it isn't in the ArrayList
			trail2.add(p2current);
		}
		
		//player 1 movement
		//moves player based on direction
		
		if (dir1.equals("RIGHT")){ 
			if(Gdx.input.isKeyPressed(Keys.SPACE)){
				//player goes turbo speed if key is pressed
				speed1 = 3;
				p1x += speed1;
			}
			//regular speed if key not pressed
			else{
				speed1 = 2;
				p1x += speed1;
			}
		}
		else if (dir1.equals("LEFT")) {
			if(Gdx.input.isKeyPressed(Keys.SPACE)){
				speed1 = 3;
				p1x -= speed1;
			}
			else{
				speed1 = 2;
				p1x -= speed1;
			}
		}
		else if (dir1.equals("UP")){
			if(Gdx.input.isKeyPressed(Keys.SPACE)){
				speed1 = 3;
				p1y += speed1;
			}
			else{
				speed1 = 2;
				p1y += speed1;
			}
		} 
		else if (dir1.equals("DOWN")) {
			if(Gdx.input.isKeyPressed(Keys.SPACE)){
				speed1 = 3;
				p1y -= speed1;
			}
			else{
				speed1 = 2;
				p1y -= speed1;
			}
		}
				
		//makes sure player can't go backwards
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT) && !(dir1.equals("LEFT"))){
			dir1 = "RIGHT";
		}
		else if(Gdx.input.isKeyJustPressed(Keys.LEFT) && !(dir1.equals("RIGHT"))){
			dir1 = "LEFT";
		}
		
		else if(Gdx.input.isKeyJustPressed(Keys.UP) && !(dir1.equals("DOWN"))){
			dir1 = "UP";
		}
		
		else if(Gdx.input.isKeyJustPressed(Keys.DOWN) && !(dir1.equals("UP"))){
			dir1 = "DOWN";
		}
		
		//player 2 movement
		
		if (dir2.equals("D")){
			if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
				speed2 = 3;
				p2x += speed2;
			}
			else{
				speed2 = 2;
				p2x += speed2;
			}
		} 
		else if (dir2.equals("A")) {
			if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
				speed2 = 3;
				p2x -= speed2;
			}
			else{
				speed2 = 2;
				p2x -= speed2;
			}
		}
		
		if (dir2.equals("W")){
			if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
				speed2 = 3;
				p2y += speed2;
			}
			else{
				speed2 = 2;
				p2y += speed2;
			}
		} 
		else if (dir2.equals("S")) {
			if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
				speed2 = 3;
				p2y -= speed2;
			}
			else{
				speed2 = 2;
				p2y -= speed2;
			}
		}
		
		//ensures player 2 does not move backwards
		if (Gdx.input.isKeyJustPressed(Keys.D) && !(dir2.equals("A"))){
			dir2 = "D";
		}
		else if(Gdx.input.isKeyJustPressed(Keys.A) && !(dir2.equals("D"))){
			dir2 = "A";
		}
		
		else if(Gdx.input.isKeyJustPressed(Keys.W) && !(dir2.equals("S"))){
			dir2 = "W";
		}
		
		else if(Gdx.input.isKeyJustPressed(Keys.S) && !(dir2.equals("W"))){
			dir2 = "S";
		}	
		
	}
	
	//draws game board and calls update to move characters
	public void gameDraw(){
		//if the game is not over
		if (gameOn) {
			update(); //calls update
			//clears screen
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			//draws every trail after screen is cleared
			for(int i = 0; i < trail1.size(); i++){
				shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(Color.BLUE);
				shapeRenderer.rect(trail1.get(i).getX(), trail1.get(i).getY(), 5, 5);
				shapeRenderer.setColor(Color.ORANGE);
				shapeRenderer.end();
			}
			
			for(int i = 0;i < trail2.size(); i++){
				shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(Color.ORANGE);
				shapeRenderer.rect(trail2.get(i).getX(), trail2.get(i).getY(), 5, 5);
				shapeRenderer.end();
			}
						
			//checks if player is out of bounds
			if(p1x < 0 || p1y < 0 || p1x > 500 || p1y > 500){
				page = "p2 winner";
			}
			else if(p2x < 0 || p2y < 0 || p2x > 500 || p2y > 500){
				page = "p1 winner";
			}
		}
		//if game is it declares the winner
		else {
			if(winner.equals("PLAYER 1")){
				page = "p1 winner";
			}
			else if(winner.equals("PLAYER 2")){
				page = "p2 winner";
			}
			
		}
	}
	
	//called if player 1 wins
	public void p1Winner(){
		
		//gets mouse coordinates
		mx = Gdx.input.getX();
		my = Gdx.input.getY();
		
		batch.begin();
		
		//p1 winner background
		p1Win.setSize(500, 500);
		p1Win.setPosition(0, 0);
		p1Win.draw(batch);
		
		//draws back button
		back.setSize(130, 50);
		back.setPosition(185, 15);
		back.draw(batch);
		
		batch.end();
		
		//highlights button when mouse is on top of it
		if(mx >= 185 && mx<= 315 && my >= 430 && my <= 480){
			batch.begin();
			backHigh.setSize(130, 50);
			backHigh.setPosition(185, 15);
			backHigh.draw(batch);
			batch.end();
			//changes to game screen when back button is pressed
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				page = "main menu";
			}
		}
	}
	//called if player 2 wins
	public void p2Winner(){
		
		//mouse x and y
		mx = Gdx.input.getX();
		my = Gdx.input.getY();
		
		batch.begin();
		
		//p1 winner background
		p2Win.setSize(500, 500);
		p2Win.setPosition(0, 0);
		p2Win.draw(batch);
		
		//draws back button
		back.setSize(130, 50);
		back.setPosition(185, 15);
		back.draw(batch);
		
		batch.end();
		
		//highlights back button when mouse is on top of it
		if(mx >= 185 && mx<= 315 && my >= 430 && my <= 480){
			batch.begin();
			backHigh.setSize(130, 50);
			backHigh.setPosition(185, 15);
			backHigh.draw(batch);
			batch.end();
			//changes to game screen when back button is pressed
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				page = "main menu";
			}
		}
	}
	
	//instructions screen
	public void instructions(){
		//gets mouse x and y
		mx = Gdx.input.getX();
		my = Gdx.input.getY();
		
		batch.begin();
		
		//instructions background
		instruct.setSize(500, 500);
		instruct.setPosition(0, 0);
		instruct.draw(batch);
		
		//draws back button
		back.setSize(130, 50);
		back.setPosition(185, 15);
		back.draw(batch);
		
		batch.end();
		
		//highlights back button when mouse is on top of it
		if(mx >= 185 && mx<= 315 && my >= 430 && my <= 480){
			batch.begin();
			backHigh.setSize(130, 50);
			backHigh.setPosition(185, 15);
			backHigh.draw(batch);
			batch.end();
			//changes to game screen when back button is pressed
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				page = "main menu";
			}
		}
	}
	
	//resets stats of game
	public void reset(){
		//sets player coordinates to where they started
		p1x=100;
		p1y=250;
		p2x=400;
		p2y=250;
		
		//sets player directions to starting directions 
		dir1 = "RIGHT";
		dir2 = "A";
		
		//sets to no winner
		winner = "";
		gameOn = true;
		
		//clears trails
		trail1.clear();
		trail2.clear();
	}
	
	//main menu screen
	public void mainMenu(){
		
		mx = Gdx.input.getX();
		my = Gdx.input.getY();
		
		batch.begin();
		
		//Main menu background
		menu.setSize(500, 500);
		menu.setPosition(0, 0);
		menu.draw(batch);
		
		//draws start button
		start.setSize(130, 50);
		start.setPosition(20, 15);
		start.draw(batch);
		
		//draws instructions button
		inst.setSize(130, 50);
		inst.setPosition(350, 15);
		inst.draw(batch);
		
		batch.end();
		
		//if mouse is on top of the button, highlights the button
		if(mx >= 20 && mx<= 150 && my >= 430 && my <= 480){
			batch.begin();
			startHigh.setSize(130, 50);
			startHigh.setPosition(20, 15);
			startHigh.draw(batch);
			batch.end();
			//changes to game screen when button is pressed
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				page = "game";
			}
		}
		//if mouse is on top of the button, highlights the button
		else if(mx >= 350 && mx<= 480 && my >= 430 && my <= 480){
			batch.begin();
			instHigh.setSize(130, 50);
			instHigh.setPosition(350, 15);
			instHigh.draw(batch);
			batch.end();
			//changes to game screen when button is pressed
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				page = "instructions";
			}
		}
	}

	@Override
	public void render () {
		//calls whatever page is being used
		if(page.equals("main menu")){
			mainMenu();
		}
		if(page.equals("instructions")){
			instructions();
		}
		if(page.equals("p1 winner")){
			p1Winner();
			reset();
		}
		if(page.equals("p2 winner")){
			p2Winner();
			reset();
		}
		if(page.equals("game")){
			gameDraw();
		}
	}
}
