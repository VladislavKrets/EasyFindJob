package easy.find.job.model.superjob;

import easy.find.job.model.utils.HttpMethodUtils;

import java.io.IOException;
import java.util.HashMap;
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

    public void getVacancies() throws IOException {
        String answer = sjMethods.getMethod("vacancies", headersMap);
        System.out.println(answer);
    }
}
