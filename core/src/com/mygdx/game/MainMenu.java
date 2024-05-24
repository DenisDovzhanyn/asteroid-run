package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class MainMenu implements Screen {

    final My2dGame game;
    OrthographicCamera camera;
    public Texture clickAnywhere;
    public Rectangle anywhere;
    GameScreen menu;

    public MainMenu(final My2dGame game){
        this.game = game;
        menu = new GameScreen(game);



        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        game.backGround = new Texture(Gdx.files.internal("background.png"));
        clickAnywhere = new Texture(Gdx.files.internal("clickanywhere.png"));
        anywhere = new Rectangle();
        anywhere.width = 200;
        anywhere.height = 100;

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
        game.batch.draw(clickAnywhere,400 - 100,240 - 50);
        game.font.draw(game.batch, game.getHighScore(), 0, 480 );
        game.batch.draw(menu.playerImage,menu.character.x,menu.character.y);
        game.batch.end();



            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        if (menu.character.x < MathUtils.random(32, 750)) menu.character.x += 200 * Gdx.graphics.getDeltaTime();
        else
        if (menu.character.x > MathUtils.random(32, 750)) menu.character.x -= 200 * Gdx.graphics.getDeltaTime();


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
