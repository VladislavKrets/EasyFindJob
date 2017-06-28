package easy.find.job.model;

import easy.find.job.model.base.BaseFindVacancies;
import easy.find.job.model.utils.Vacancy;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by lollipop on 28.06.2017.
 */
public class VacancyCollectorThread implements Runnable{

    private CopyOnWriteArrayList<Vacancy> vacancies;
    private BaseFindVacancies vacancyFinder;
    private String text;
    private CountDownLatch latch;

    public VacancyCollectorThread(CopyOnWriteArrayList<Vacancy> vacancies,
                                  BaseFindVacancies vacancyFinder, String text, CountDownLatch latch) {
        this.vacancies = vacancies;
        this.vacancyFinder = vacancyFinder;
        this.text = text;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            List<Vacancy> collectedVacancies = vacancyFinder.getVacancies(text);
            vacancies.addAll(collectedVacancies);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            latch.countDown();
        }
    }
}
