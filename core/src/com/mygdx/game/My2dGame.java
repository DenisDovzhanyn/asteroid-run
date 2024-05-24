package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Rectangle;
import org.w3c.dom.Text;

import java.awt.*;
import java.awt.peer.TextComponentPeer;
import java.util.Iterator;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.SynchronousQueue;

public class My2dGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public Texture backGround;
	public Rectangle back;
	public static int highScore;

	
	@Override
	public void create () {
		font = new BitmapFont();
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));
		back = new Rectangle();
		back.width = 800;
		back.height = 480;

	}

	@Override
	public void render () {
		super.render();
	}

	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
	public String getHighScore(){
		String high = String.valueOf(highScore);
		return high;
	}
	public void setHighScore(int score){
		highScore = score;
	}
}
