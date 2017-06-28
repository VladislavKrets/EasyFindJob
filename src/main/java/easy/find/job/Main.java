package easy.find.job;

import easy.find.job.model.hh.HHFindVacancies;
import easy.find.job.model.superjob.SuperJobFindVacancies;

import java.io.IOException;

/**
 * Created by lollipop on 28.06.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
       /* HHFindVacancies vacancies = new HHFindVacancies();
        vacancies.getVacancies("Java");*/

        SuperJobFindVacancies vacancies = new SuperJobFindVacancies();
        vacancies.getVacancies();
    }
}
