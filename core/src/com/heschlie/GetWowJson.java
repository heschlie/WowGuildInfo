package com.heschlie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heschlie on 4/23/2015. Copyright under Iridium Flare Games LLC.
 */
public class GetWowJson {

    public static void getGuildInfo(String server, String guild, Table pane) {
        // Convert the strings to be valid for Battle.net
        guild = guild.replaceAll("\\s", "%20").toLowerCase();
        server = server.replaceAll("\\s", "%20").toLowerCase();

        final StringBuilder sb = new StringBuilder();
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("fields", "members");
        parameters.put("locale", "en_US");
        parameters.put("apikey", Constants.key);

        String uri = "https://us.api.battle.net/wow/guild/" + server + "/" + guild;

        HttpRequestBuilder builder = new HttpRequestBuilder();
        Net.HttpRequest get = builder.newRequest().url(uri).content(HttpParametersUtils.convertHttpParameters(parameters)).method(Net.HttpMethods.GET).timeout(2500).build();

        Gdx.net.sendHttpRequest(get, new GuildListener(pane));
    }

}
