package easy.find.job;

import easy.find.job.model.hh.HHFindVacancies;
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
        HHFindVacancies hhVacancies = new HHFindVacancies();
        List<Vacancy> hhListVacancy = hhVacancies.getVacancies("Java");
        for (Vacancy vacancy : hhListVacancy) {
            System.out.println(vacancy.getUrl());
        }
        SuperJobFindVacancies sjVacancies = new SuperJobFindVacancies();
        List<Vacancy> sjListVacancy = sjVacancies.getVacancies("Java");
        for (Vacancy vacancy : sjListVacancy) {
            System.out.println(vacancy.getUrl());
        }

    }
}
