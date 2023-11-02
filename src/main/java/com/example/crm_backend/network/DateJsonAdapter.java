package com.example.crm_backend.network;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateJsonAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_DATE_TIME);

        return Date.from(localDateTime.atZone(ZoneOffset.systemDefault()).toInstant());
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(src.toInstant(), ZoneOffset.systemDefault());

        return new JsonPrimitive(DateTimeFormatter.ISO_DATE_TIME.format(localDateTime));
    }
}

