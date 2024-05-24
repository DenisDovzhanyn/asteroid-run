package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen {

    final My2dGame game;
    OrthographicCamera camera;

    public MainMenu(final My2dGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        game.backGround = new Texture(Gdx.files.internal("background.png"));

    }

    @Override
    public void show() {

    }


    public void render(float delta){
        ScreenUtils.clear(0, 0, 0 , 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(game.backGround,0,0);
        game.font.draw(game.batch, "Help Timmy dodge the asteroids for as long as possible!", 100, 150);
        game.font.draw(game.batch, "Click anywhere to begin!", 100, 100);
        game.font.draw(game.batch, game.getHighScore(), 0, 480 );
        game.batch.end();

        if (Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }


    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
