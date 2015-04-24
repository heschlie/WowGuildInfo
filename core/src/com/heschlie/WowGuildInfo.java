package com.heschlie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class WowGuildInfo extends ApplicationAdapter {
    private Stage stage;
    private ScrollPane pane;
    private Table mainTable;
    private Table table;

	@Override
	public void create () {
        Assets.createInstance();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.align(Align.left);
        stage.addActor(mainTable);

        table = new Table();
        table.align(Align.left);
        pane = new ScrollPane(table, Assets.getInstance().skin);
        mainTable.add(pane).align(Align.left);

		GetWowJson.getGuildInfo("gorgonnash", "the Kush Collective", table);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
	}
}
