package com.porotov.articleservice.gson.Article;

import com.google.gson.*;
import com.porotov.articleservice.DTO.ArticleDTO.ArticleCreationDTO;

import java.lang.reflect.Type;

public class ArticleCreateDTODeserializer implements JsonDeserializer<ArticleCreationDTO> {

    @Override
    public ArticleCreationDTO deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        ArticleCreationDTO articleCreationDTO = new ArticleCreationDTO();

        articleCreationDTO.setTitle(jsonObject.get("title").getAsString());
        articleCreationDTO.setUrl(jsonObject.get("url").getAsString());

        return articleCreationDTO;

    }
}
