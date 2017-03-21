package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Catan;
import com.mygdx.game.model.Constantes;


/**
 * Created by typhon0 on 28/02/17.
 */

public class HelpScreen implements Screen {

    private Skin skin;
    private Stage stage;
    private Catan game;


    // constructor to keep a reference to the main Game class
    public HelpScreen(Catan pgame) {
        this.game = pgame;

        stage = new Stage(new ExtendViewport(800, 500));

        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));
        Label regle = new Label(null, skin);
        regle.setText("Repudiandae nam in nam quia omnis possimus. Rem et est est iste. Rerum enim et autem quo fugiat quibusdam sit. Doloribus voluptatem et id alias labore impedit quibusdam atque.\n" +
                "Doloremque ut laborum dolorum et. Asperiores sint illo vel odit rerum reiciendis non accusamus. Architecto provident quae id velit est error autem.\n" +
                "Est totam in deleniti assumenda rerum quia. Et quia quo maiores aut error. Tenetur voluptatibus provident qui et harum ut. Ducimus fugiat sit culpa nobis aspernatur saepe iusto omnis. Quia in explicabo officia eius. Aut velit dolorem animi et aliquid iure nam qui.\n" +
                "Animi accusamus aliquid incidunt. Libero officiis doloribus debitis ullam voluptate expedita. Ex dolorum dolorum maiores tempore non harum. Quae dolores nobis tempore labore ipsum.\n" +
                "Et repellendus id inventore ipsa quia ullam. Id dolorem voluptatibus incidunt quia officiis. Odit debitis vel ipsam. Aut praesentium doloribus omnis ducimus recusandae rem.sdfsqDGSDQGDSG" +
                "SDQGSQGDSGSDGSDGSGDSQGSDQGKLSQDGJMSKQlkhjdjskqlhsdqghsdqghsdkqghsdjkghskqghkjdshgksqhgdjslghSLGHSDGhSKGDLJDSHGkjHGJKHgksqhgdkqshgkjdsqgkshqgkqshg sqdj gsdkjqg kjdsqhgjksdhg kjdsqhgjsqdjghsqdg hskjqgksqg sqghsdkjqgsjgdsqgks dqgsd qgsdkg sdjqgh ");
        regle.setWrap(true);
        ScrollPane sp = new ScrollPane(regle);

        Table table = new Table();

        table.setSize(800, 500);

        //Background
        Texture t = new Texture(Constantes.CHEMIN_ACCES_UI+"background_menu.jpg");
        Drawable d = new TextureRegionDrawable(new TextureRegion(t));
        table.setBackground(d);
        table.setFillParent(true);
/*
        CheckBox easyCB = new CheckBox("  Facile", skin);
        table.add(easyCB);

        */
        table.add(sp).width(700);
        table.row();

        stage.addActor(table);


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);


    }

    @Override
    public void render(float delta) {

        // clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // let the stage act and draw
        stage.act(delta);
        stage.draw();


        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            System.out.println("Back pressed");
            game.setScreen(new MainMenu(game));
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
        stage.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        // never called automatically
        stage.dispose();
    }
}

