package easy.find.job.model.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import easy.find.job.model.utils.Vacancy;

import java.util.List;

/**
 * Created by lollipop on 01.07.2017.
 */
public class Serializer {
    public String serialize(List<Vacancy> vacancies) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(List.class, new JsonVacancySerializer());
        Gson gson = builder.create();
        return gson.toJson(vacancies);
    }
}
