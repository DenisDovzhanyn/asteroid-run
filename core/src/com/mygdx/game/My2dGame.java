package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

public class My2dGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture playerImage;
	private Texture asteroidImage;
	private OrthographicCamera camera;
	private Rectangle character;
	private Array<Rectangle> asteroids;
	private long lastAsteroidTime;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		playerImage = new Texture("facingrightandbottom.png");
		asteroidImage = new Texture("asteroid.png");
		character = new Rectangle();
		character.x = 400 - 64;
		character.y = 5;
		character.width = 32;
		character.height = 32;

		asteroids = new Array<Rectangle>();
		spawnAsteroid();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(playerImage, character.x, character.y);
		for (Rectangle asteroid: asteroids){
			batch.draw(asteroidImage, asteroid.x, asteroid.y);
		}
		batch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.A)) character.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.D)) character.x += 200 * Gdx.graphics.getDeltaTime();
		if (character.x > 800 - 32) character.x = 800 - 32;
		if (character.x < 0) character.x = 0;
		if (TimeUtils.nanoTime() - lastAsteroidTime > 1000000000) spawnAsteroid();

		for (Iterator<Rectangle> ast = asteroids.iterator(); ast.hasNext();){
			Rectangle asteroid = ast.next();
			asteroid.y -= 200 * Gdx.graphics.getDeltaTime();
			if (asteroid.y + 64 < 0) ast.remove();
			if (asteroid.overlaps(character))
			{
				dispose();

			}
		}




    }

	private void spawnAsteroid(){
		Rectangle asteroid = new Rectangle();
		asteroid.x = MathUtils.random(0, 800-64);
		asteroid.y = 480;
		asteroid.width = 32;
		asteroid.height = 32;
		asteroids.add(asteroid);
		lastAsteroidTime = TimeUtils.nanoTime();



	}
	
	@Override
	public void dispose () {
		batch.dispose();
		playerImage.dispose();
	}
}
