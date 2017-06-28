package easy.find.job.model;

import easy.find.job.model.base.BaseFindVacancies;
import easy.find.job.model.hh.HHFindVacancies;
import easy.find.job.model.rabotaru.RabotaRuFindVacancies;
import easy.find.job.model.superjob.SuperJobFindVacancies;
import easy.find.job.model.utils.Vacancy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by lollipop on 28.06.2017.
 */
public class VacancyCollector {
    private CopyOnWriteArrayList<Vacancy> vacancies;
    private static VacancyCollector instance;
    private static List<BaseFindVacancies> vacancyFinders;

    static {
        vacancyFinders = new ArrayList<>();
        vacancyFinders.add(new HHFindVacancies());
        vacancyFinders.add(new RabotaRuFindVacancies());
        vacancyFinders.add(new SuperJobFindVacancies());
    }

    private VacancyCollector() {
        vacancies = new CopyOnWriteArrayList<>();
    }

    public static VacancyCollector getInstance() {
        if (instance == null) instance = new VacancyCollector();
        return instance;
    }

    public CopyOnWriteArrayList<Vacancy> collectVacancies(String text) {
        vacancies.clear();
        CountDownLatch latch = new CountDownLatch(vacancyFinders.size());
        ExecutorService executor = Executors.newFixedThreadPool(vacancyFinders.size());
        for (BaseFindVacancies vacancyFinder : vacancyFinders) {
            executor.submit(new VacancyCollectorThread(vacancies, vacancyFinder, text, latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        Collections.sort(vacancies);
        return vacancies;
    }


}
