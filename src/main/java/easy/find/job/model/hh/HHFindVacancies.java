package easy.find.job.model.hh;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import easy.find.job.model.base.BaseFindVacancies;
import easy.find.job.model.utils.HttpMethodUtils;
import easy.find.job.model.utils.Vacancy;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lollipop on 28.06.2017.
 */
public class HHFindVacancies implements BaseFindVacancies{

    private HttpMethodUtils hhMethods;
    private Map<String, String> headersMap;
    private String accessToken;

    public HHFindVacancies() {
        accessToken = "M9G045L2PLAG2F1KK3M5EVL1RO058T0CT69LB2B6G2G0NH0TG045JANQM8RUUNNQ";
        hhMethods = new HttpMethodUtils("https://api.hh.ru/");
        headersMap = new HashMap<>();
        headersMap.put("Authorization", "Bearer " + accessToken);
    }

    @Override
    public List<Vacancy> getVacancies(String text) throws IOException {

        String answer = hhMethods.getMethod(String.format("vacancies?text=%s&date_from=%s&per_page=%s",
                text, new SimpleDateFormat("yyyy-MM-dd")
                        .format(new Date(new Date().getTime() - 86400000)),
                "500"), headersMap);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(List.class, new JsonHHVacanciesListConverter());
        Gson gson = builder.create();
        List<Vacancy> vacancyList = gson.fromJson(answer, List.class);
        return vacancyList;
    }
}
