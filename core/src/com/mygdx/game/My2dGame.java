package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class My2dGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture characterimage;
	private OrthographicCamera camera;
	private Rectangle character;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		characterimage = new Texture("facingrightandbottom.png");
		character = new Rectangle();
		character.x = 400 - 32;
		character.y = 240;
		character.width = 32;
		character.height = 32;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(characterimage, character.x, character.y);
		batch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.A)) character.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.D)) character.x += 200 * Gdx.graphics.getDeltaTime();
		if (character.x > 800 - 32) character.x = 800 - 32;
		if (character.x < 0) character.x = 0;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		characterimage.dispose();
	}
}
