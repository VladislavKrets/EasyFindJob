package easy.find.job.model.rabotaru;

import com.google.gson.*;
import easy.find.job.model.utils.Vacancy;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lollipop on 28.06.2017.
 */
public class JsonRRVacanciesListConverter implements JsonDeserializer<List<Vacancy>>{
    @Override
    public List<Vacancy> deserialize(JsonElement jsonElement, Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonArray data = jsonElement.getAsJsonObject().getAsJsonArray("data");
        List<Vacancy> vacancyList = new ArrayList<>();

        for (JsonElement vacancyElement : data) {
            try {
                vacancyList.add(new Vacancy(
                        vacancyElement.getAsJsonObject().get("name").getAsString(),
                        "",
                        "https://rabota.ru/vacancy/" + vacancyElement.getAsJsonObject().get("id").getAsString(),
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(vacancyElement.getAsJsonObject().get("publishDate").getAsString())
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return vacancyList;
    }
}
