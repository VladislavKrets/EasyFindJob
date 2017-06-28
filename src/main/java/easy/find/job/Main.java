package easy.find.job;

import easy.find.job.model.VacancyCollector;
import easy.find.job.model.hh.HHFindVacancies;
import easy.find.job.model.rabotaru.RabotaRuFindVacancies;
import easy.find.job.model.superjob.SuperJobFindVacancies;
import easy.find.job.model.utils.Vacancy;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by lollipop on 28.06.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        /*HHFindVacancies hhVacancies = new HHFindVacancies();
        List<Vacancy> hhListVacancy = hhVacancies.getVacancies("Java");
        for (Vacancy vacancy : hhListVacancy) {
            System.out.println(vacancy.getUrl());
        }*/

        /*SuperJobFindVacancies sjVacancies = new SuperJobFindVacancies();
        List<Vacancy> sjListVacancy = sjVacancies.getVacancies("Java");
        System.out.println(sjListVacancy.size());
        for (Vacancy vacancy : sjListVacancy) {
            System.out.println(vacancy.getUrl());
        }*/
        /*RabotaRuFindVacancies rrVacancies = new RabotaRuFindVacancies();
        List<Vacancy> rrListVacancy =  rrVacancies.getVacancies("Java");
        for (Vacancy vacancy : rrListVacancy) {
            System.out.println(vacancy.getUrl());
        }*/
        VacancyCollector vacancyCollector = VacancyCollector.getInstance();
        List<Vacancy> vacancyList = vacancyCollector.collectVacancies("Java");
        System.out.println(vacancyList.size());
        for (Vacancy vacancy : vacancyList) {
            System.out.println(vacancy.getName());
            System.out.println(vacancy.getUrl());
            System.out.println(vacancy.getCreatedDate());
            System.out.println();
        }

    }
}
