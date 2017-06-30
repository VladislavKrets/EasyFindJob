package easy.find.job.model.jobs.superjob;

import com.google.gson.*;
import easy.find.job.model.utils.Vacancy;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lollipop on 28.06.2017.
 */
public class JsonSuperJobVacanciesListConverter implements JsonDeserializer<List<Vacancy>>{
    @Override
    public List<Vacancy> deserialize(JsonElement jsonElement, Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<Vacancy> vacancyList = new ArrayList<>();
        JsonArray objects = jsonElement.getAsJsonObject().getAsJsonArray("objects");

        for (JsonElement vacancyElement : objects) {
            vacancyList.add(new Vacancy(
                    vacancyElement.getAsJsonObject().get("profession").getAsString(),
                    vacancyElement.getAsJsonObject().get("candidat").getAsString(),
                    vacancyElement.getAsJsonObject().get("link").getAsString(),
                    new Date(vacancyElement.getAsJsonObject().get("date_published").getAsInt() * 1000L)
            ));
        }

        return vacancyList;
    }
}
