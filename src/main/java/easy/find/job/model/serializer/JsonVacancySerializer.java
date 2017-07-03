package easy.find.job.model.serializer;

import com.google.gson.*;
import easy.find.job.model.utils.Vacancy;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lollipop on 01.07.2017.
 */
public class JsonVacancySerializer implements JsonSerializer<List<Vacancy>>{
    @Override
    public JsonElement serialize(List<Vacancy> vacancies, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray array = new JsonArray();
        JsonObject object;
        for (Vacancy vacancy : vacancies) {
            object = new JsonObject();
            object.addProperty("name", vacancy.getName());
            object.addProperty("requirement", vacancy.getRequirement());
            object.addProperty("url", vacancy.getUrl());
            object.addProperty("created_date", vacancy.getCreatedDate().getTime());
            array.add(object);
        }
        return array;
    }
}
