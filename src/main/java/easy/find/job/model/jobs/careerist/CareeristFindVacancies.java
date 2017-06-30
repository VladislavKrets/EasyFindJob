package easy.find.job.model.jobs.careerist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import easy.find.job.model.base.BaseFindVacancies;
import easy.find.job.model.utils.HttpMethodUtils;
import easy.find.job.model.utils.Vacancy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lollipop on 29.06.2017.
 */
public class CareeristFindVacancies implements BaseFindVacancies{

    private String apiKey;
    private HttpMethodUtils careeristMethods;
    private Map<String, String> headersMap;

    public CareeristFindVacancies() {
        apiKey = "b7ba58a3e64c5ff0d9fbc4faebb86c28b1bc40b9";
        careeristMethods = new HttpMethodUtils("http://smapi.careerist.ru/");
        headersMap = new HashMap<>();
    }

    @Override
    public List<Vacancy> getVacancies(String text) throws IOException {
        String answer = careeristMethods.getMethod(String.format("vacancy-search?text=%s", text), headersMap);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(List.class, new JsonCareeristVacanciesListConverter());
        Gson gson = builder.create();
        List<Vacancy> vacancies = gson.fromJson(answer, List.class);
        return vacancies;
    }
}
