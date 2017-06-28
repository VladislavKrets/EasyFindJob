package easy.find.job.model.rabotaru;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import easy.find.job.model.base.BaseFindVacancies;
import easy.find.job.model.utils.HttpMethodUtils;
import easy.find.job.model.utils.Vacancy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lollipop on 28.06.2017.
 */
public class RabotaRuFindVacancies implements BaseFindVacancies{
    private HttpMethodUtils rrMethods;
    private Map<String, String> headerMap;

    public RabotaRuFindVacancies() {
        rrMethods = new HttpMethodUtils("http://api-android.rabota.ru/v2/");
        headerMap = new HashMap<>();
    }

    @Override
    public List<Vacancy> getVacancies(String text) throws IOException {
        String answer = rrMethods.getMethod(String.format("search-vacancies?w=%s&period=%s",
                text, "30"), headerMap);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(List.class, new JsonRRVacanciesListConverter());
        Gson gson = builder.create();
        List<Vacancy> vacancyList = gson.fromJson(answer, List.class);
        return vacancyList;
    }
}
