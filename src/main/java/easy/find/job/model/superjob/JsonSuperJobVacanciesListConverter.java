package easy.find.job.model.superjob;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import easy.find.job.model.utils.Vacancy;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lollipop on 28.06.2017.
 */
public class JsonSuperJobVacanciesListConverter implements JsonDeserializer<List<Vacancy>>{
    @Override
    public List<Vacancy> deserialize(JsonElement jsonElement, Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       
        return null;
    }
}
