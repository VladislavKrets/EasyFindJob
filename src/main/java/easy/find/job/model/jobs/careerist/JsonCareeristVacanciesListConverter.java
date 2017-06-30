package easy.find.job.model.jobs.careerist;

import com.google.gson.*;
import easy.find.job.model.utils.Vacancy;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lollipop on 30.06.2017.
 */
public class JsonCareeristVacanciesListConverter implements JsonDeserializer<List<Vacancy>>{
    @Override
    public List<Vacancy> deserialize(JsonElement jsonElement, Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonArray vacanciesArray = jsonElement.getAsJsonObject().getAsJsonArray("vacancy");
        List<Vacancy> vacancies = new ArrayList<>();
        for (JsonElement vacancyElement : vacanciesArray) {
            vacancies.add(new Vacancy(
                vacancyElement.getAsJsonObject().get("name").getAsString(),
                    "",
                    "https://careerist.ru/vakansii/" + vacancyElement.getAsJsonObject().get("id").getAsString() +
                            ".html",
                    new Date(vacancyElement.getAsJsonObject().get("editTme").getAsLong() * 1000L)
            ));
        }
        return vacancies;
    }
}
