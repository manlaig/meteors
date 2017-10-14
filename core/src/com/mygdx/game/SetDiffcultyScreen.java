package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by manlai on 10/13/2017.
 */

public class SetDiffcultyScreen extends InputAdapter implements Screen {

    ScreenViewport viewport;
    ShapeRenderer renderer;
    SpriteBatch batch;
    BitmapFont font;
    MyGdxGame game;

    public SetDiffcultyScreen(MyGdxGame game)
    {
        this.game = game;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));

        if(worldClick.dst(Constants.EASY_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS)
            game.setScreen(new MeteorScreen(game, Constants.DIFFICULTY_EASY));

        if(worldClick.dst(Constants.MEDIUM_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS)
            game.setScreen(new MeteorScreen(game, Constants.DIFFUCULTY_MEDIUM));

        if(worldClick.dst(Constants.HARD_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS)
            game.setScreen(new MeteorScreen(game, Constants.DIFFICULTY_HARD));
        return true;
    }

    @Override
    public void show() {
        viewport = new ScreenViewport();
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        Gdx.input.setInputProcessor(this);
        font.getData().setScale(2);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,0,0,0);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(200/255.0f, 0,0,0);
        renderer.circle(Constants.EASY_CENTER.x, Constants.EASY_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(150/255.0f, 0,0,0);
        renderer.circle(Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(130/255.0f,0,0,0);
        renderer.circle(Constants.HARD_CENTER.x, Constants.HARD_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.end();

        batch.begin();

        final GlyphLayout easyLayout = new GlyphLayout(font, "Easy");
        font.draw(batch, "Easy", Constants.EASY_CENTER.x, Constants.EASY_CENTER.y + easyLayout.height / 2, 0, Align.center, false);

        final GlyphLayout mediumLayout = new GlyphLayout(font, "Medium");
        font.draw(batch, "Medium", Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y + mediumLayout.height / 2, 0, Align.center, false);

        final GlyphLayout hardLayout = new GlyphLayout(font, "Hard");
        font.draw(batch, "Hard", Constants.HARD_CENTER.x, Constants.HARD_CENTER.y + hardLayout.height / 2, 0, Align.center, false);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
    }

    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
