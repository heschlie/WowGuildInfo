package com.heschlie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.StringBuilder;

/**
 * Created by heschlie on 4/23/2015. Copyright under Iridium Flare Games LLC.
 */
public class GuildListener implements Net.HttpResponseListener {

    private Table menuScrollPane;
    private JsonValue guildInfo;
    private StringBuilder builder;
    private Assets assets;
    private Skin skin;

    public GuildListener(Table pane) {
        menuScrollPane = pane;
        builder = new StringBuilder();
        assets = Assets.getInstance();
        skin = assets.skin;
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        if (httpResponse.getStatus().getStatusCode() == HttpStatus.SC_OK) {
            String response = httpResponse.getResultAsString();
            System.out.println(response);
            JsonReader reader = new JsonReader();
            guildInfo = reader.parse(response);
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    parseGuild();
                }
            });
        } else {
            System.out.println(httpResponse.getStatus().getStatusCode());
        }
    }

    @Override
    public void failed(Throwable t) {
        Gdx.app.log("GuildListener: ", t.getMessage());
    }

    @Override
    public void cancelled() {

    }

    private void parseGuild() {
        JsonValue members = guildInfo.get("members");

        for (JsonValue member : members) {
            builder.setLength(0);
            JsonValue character = member.get("character");
            Table table = new Table();

            String name = character.getString("name");
            String server = character.getString("realm");
            String level = character.getString("level");

            table.add(createLabel(name, Color.WHITE));
            table.add(createLabel("(" + level + ")", Color.GRAY));
            table.row();
            table.add(createLabel(server, Color.ORANGE));

            menuScrollPane.add(table).align(Align.left);
            menuScrollPane.row();

            builder.append(name).append("(").append(level).append(")").append("\n").append(server).append("\n");
            System.out.println(builder);
        }
    }

    private Label createLabel(String text, Color color) {
        Label label = new Label(text, skin, "default-font", color);
        return label;
    }
}
