package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen implements Screen {
    final My2dGame game;

     Texture playerImage;
     Texture asteroidImage;
     Texture impactFrame;
     Sound falling;
     Sound impact;
     Music ingame;
     OrthographicCamera camera;
     Rectangle character;
     Array<Rectangle> asteroids;
     long lastAsteroidTime;
     int score;


    public GameScreen (final My2dGame game) {
        this.game = game;
        ingame = Gdx.audio.newMusic(Gdx.files.internal("ingame.mp3"));
        ingame.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        playerImage = new Texture("facingrightandbottom.png");
        asteroidImage = new Texture("asteroid.png");
        impactFrame = new Texture(Gdx.files.internal("impactframe.png"));
        game.backGround = new Texture("background.png");
        falling = Gdx.audio.newSound(Gdx.files.internal("falling.wav"));
        impact = Gdx.audio.newSound(Gdx.files.internal("impact.wav"));

        character = new Rectangle();
        character.x = 400 - 64;
        character.y = 80;
        character.width = 32;
        character.height = 32;

        asteroids = new Array<Rectangle>();
        spawnAsteroid();
    }


    public void render (float delta) {

        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(game.backGround,0,0);
        game.font.draw(game.batch, "Asteroids dodged:" + score, 0, 480);
        game.batch.draw(playerImage, character.x, character.y);
        for (Rectangle asteroid: asteroids){
            game.batch.draw(asteroidImage, asteroid.x, asteroid.y);
        }
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.A)) character.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.D)) character.x += 200 * Gdx.graphics.getDeltaTime();
        if (character.x > 800 - 32) character.x = 800 - 32;
        if (character.x < 0) character.x = 0;
        if (TimeUtils.nanoTime() - lastAsteroidTime > 200000000){
            spawnAsteroid();

        }

        for (Iterator<Rectangle> ast = asteroids.iterator(); ast.hasNext();){
            Rectangle asteroid = ast.next();
            asteroid.y -= 300 * Gdx.graphics.getDeltaTime();
            if (asteroid.y + 64 < 80){
                ast.remove();
                this.score++;
                falling.play(0.3f);
                game.batch.begin();
                game.batch.draw(impactFrame, asteroid.x, asteroid.y);
                game.batch.end();

            }
            if (asteroid.overlaps(character))
            {
                dispose();
                if (score > Integer.parseInt(game.getHighScore())) game.setHighScore(score);
                game.setScreen(new MainMenu(game));

            }
        }




    }

    public void spawnAsteroid(){
        Rectangle asteroid = new Rectangle();
        asteroid.x = MathUtils.random(0, 800-64);
        asteroid.y = 480;
        asteroid.width = 32;
        asteroid.height = 32;
        asteroids.add(asteroid);
        lastAsteroidTime = TimeUtils.nanoTime();




    }

    @Override
    public void show() {
        ingame.play();
        impact.play();

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
    public void dispose () {
        asteroidImage.dispose();
        playerImage.dispose();
        falling.dispose();
        impact.dispose();
        game.backGround.dispose();
        impactFrame.dispose();
        ingame.dispose();

    }

}
