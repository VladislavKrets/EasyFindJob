package easy.find.job.model.superjob;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import easy.find.job.model.utils.HttpMethodUtils;
import easy.find.job.model.utils.Vacancy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lollipop on 28.06.2017.
 */
public class SuperJobFindVacancies {
    private String apiKey;
    private HttpMethodUtils sjMethods;
    private Map<String, String> headersMap;

    public SuperJobFindVacancies() {
        apiKey = "v1.r00000000qwESQg0P98CAddVsJazsYqnlWbTUdfvlExamwS6_android.de1731b577961b029f319f0a0f986c1a747fc6b1";
        sjMethods = new HttpMethodUtils("https://api.superjob.ru/2.0/");
        headersMap = new HashMap<>();
        headersMap.put("X-Api-App-Id", apiKey);
    }

    public List<Vacancy> getVacancies(String text) throws IOException {
        String answer = sjMethods.getMethod(String.format("vacancies?keyword=%s&period=%s", text, "3"), headersMap);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(List.class, new JsonSuperJobVacanciesListConverter());
        Gson gson = builder.create();
        List<Vacancy> vacancyList = gson.fromJson(answer, List.class);
        return vacancyList;
    }
}
