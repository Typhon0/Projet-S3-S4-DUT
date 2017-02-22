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
import com.mygdx.game.model.Plateau;

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
	private Plateau plateau;

	//private SpriteBatch batch;
	//
	@Override
	public void create () {
		sr = new ShapeRenderer();
		polyBatch = new PolygonSpriteBatch();

		plateau = new Plateau();
		plateau.generer();

		batch = new SpriteBatch();
		batch2 = new SpriteBatch();

		System.out.println(plateau.getListeTuiles().get(0).getListeSommets().get(2).toString());
		System.out.println(plateau.getListeTuiles().get(1).getListeSommets().get(6).toString());

		System.out.println(plateau.getListeTuiles().get(0).getListeSommets().get(2).equals(plateau.getListeTuiles().get(1).getListeSommets().get(6)));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 130, 175, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//System.out.println("+ "+plateau.getListeTuiles().size());

		batch.begin();
		// Affichage de la mer
		batch.draw(plateau.getTextureMer(),0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		// Affichage de chaque Tuile/Terrain
		for (int i = 0; i< plateau.getListeTuiles().size() ; i++) {
			batch.draw(plateau.getListeTuiles().get(i).getTextureTuile(),
					plateau.getListeTuiles().get(i).getCoinInferieurGaucheTuile().x,
					plateau.getListeTuiles().get(i).getCoinInferieurGaucheTuile().y,
					(float)Math.sqrt(3)* plateau.TAILLE_TUILE,
					(float)(plateau.TAILLE_TUILE *2));
		}

		// Affichage de chaque Jeton
		for (int i = 0; i< plateau.getListeTuiles().size() ; i++) {
			batch.draw(plateau.getListeTuiles().get(i).getJeton().getTextureJeton(),
					plateau.getListeTuiles().get(i).getCoinInferieurGaucheJeton().x,
					plateau.getListeTuiles().get(i).getCoinInferieurGaucheJeton().y);
		}

		// Affichage de chaque Port
		for (int i = 0; i< plateau.getListePorts().size() ; i++) {
			batch.draw(plateau.getListePorts().get(i).getTexturePort(),
					plateau.getListePorts().get(i).getCoinInferieurGauchePort().x,
					plateau.getListePorts().get(i).getCoinInferieurGauchePort().y,
					64,
					64);
		}

		batch.end();

		// Affichage du squelette en surcouche
		for (int i = 0; i< plateau.getListeTuiles().size() ; i++) {
			sr.begin(ShapeRenderer.ShapeType.Line);
			sr.polygon(plateau.getListeTuiles().get(i).getVertices());
			sr.setColor(0,0,0,1);
			Gdx.gl.glLineWidth(5);
			sr.end();

		}
	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}
}
