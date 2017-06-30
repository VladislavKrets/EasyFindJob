package easy.find.job.model.jobs.hh;

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
public class JsonHHVacanciesListConverter implements JsonDeserializer<List<Vacancy>>{
    @Override
    public List<Vacancy> deserialize(JsonElement jsonElement, Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonArray items = jsonElement.getAsJsonObject().getAsJsonArray("items");
        List<Vacancy> vacancyList = new ArrayList<Vacancy>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssz");
        JsonElement requirement;
        for(JsonElement itemsElement : items) {
            requirement = itemsElement.getAsJsonObject().get("snippet").getAsJsonObject().get("requirement");

            try {
                vacancyList.add(new Vacancy(
                        itemsElement.getAsJsonObject().get("name").getAsString(),
                        requirement.isJsonNull() ? "" : requirement.getAsString(),
                        itemsElement.getAsJsonObject().get("alternate_url").getAsString(),
                        dateFormat.parse(itemsElement.getAsJsonObject().get("published_at").getAsString())

                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return vacancyList;
    }
}
