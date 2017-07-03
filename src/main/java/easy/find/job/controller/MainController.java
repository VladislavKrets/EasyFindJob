package easy.find.job.controller;

import easy.find.job.model.VacancyCollector;
import easy.find.job.model.utils.Vacancy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lollipop on 01.07.2017.
 */
@Controller
public class MainController {
    private List<Vacancy> vacancyList;
    private int page;
    @RequestMapping(value = "/")
    public String rootPage(Model model) {
        return "RootPage";
    }

    @RequestMapping(value = "?text={vacancyName}&page={page}")
    public String rootPageVacancies(@PathVariable String vacancyName, @PathVariable int page, ModelMap model) {
        VacancyCollector collector = VacancyCollector.getInstance();
        vacancyList = collector.collectVacancies(vacancyName);
        if (++page < vacancyList.size()) {
            page = this.page;
        }
        List<Vacancy> shortVacancyList = new ArrayList<>();
        for (int i = page * 10; i < page * 10 + 10; i++) {
            shortVacancyList.add(vacancyList.get(i));
        }
        return "RootPage";
    }
}
