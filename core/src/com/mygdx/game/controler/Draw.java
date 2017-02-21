package com.mygdx.game.controler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.mygdx.game.model.Board;

import java.util.ArrayList;

public class Draw extends ApplicationAdapter {

	private SpriteBatch batch,batch2;
	private Sprite sprite;

	private ShapeRenderer sr;
	private FloatArray vertices,v2,v3;
	private Vector2 center;
	//
	private Texture texture,texture2, textureBrick;
	private TextureRegion texture3, texreg;
	private PolygonSprite polygonSprite,polygonSprite2;
	private PolygonSpriteBatch polyBatch;
	private ArrayList<PolygonRegion>listePolygonRegion;
	private Board board;

	//private SpriteBatch batch;
	//
	@Override
	public void create () {
		sr = new ShapeRenderer();
		polyBatch = new PolygonSpriteBatch();

		board = new Board();
		board.generate();

		batch = new SpriteBatch();
		batch2 = new SpriteBatch();

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 130, 175, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.println("+ "+board.getHextiles().size());


		batch.begin();
		// Affichage de la mer
		batch.draw(board.getTextureMer(),0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		// Affichage de chaque Tuile/Terrain
		for (int i=0 ; i<board.getHextiles().size() ;i++) {
			batch.draw(board.getHextiles().get(i).getTextureTerrain(),board.getHextiles().get(i).getBottomLeftCorner().x,board.getHextiles().get(i).getBottomLeftCorner().y);
		}
		// Affichage de chaque Jeton
		for (int i=0 ; i<board.getHextiles().size() ;i++) {
			Texture t = board.getHextiles().get(i).getJeton().getTextureJeton();
			float lex = board.getHextiles().get(i).getBottomLeftCorner2().x;
			float ley = board.getHextiles().get(i).getBottomLeftCorner2().y;
			batch.draw(t,lex,ley);

		}
		batch.end();

		// Affichage du squelette en surcouche
		for (int i=0 ; i<board.getHextiles().size() ;i++) {
			sr.begin(ShapeRenderer.ShapeType.Line);
			sr.polygon(board.getHextiles().get(i).getVertices());
			sr.setColor(0,0,0,1);
			Gdx.gl.glLineWidth(10);
			sr.end();

		}
	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}


}
