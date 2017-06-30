package easy.find.job.model;

import easy.find.job.model.base.BaseFindVacancies;
import easy.find.job.model.jobs.careerist.CareeristFindVacancies;
import easy.find.job.model.jobs.hh.HHFindVacancies;
import easy.find.job.model.jobs.rabotaru.RabotaRuFindVacancies;
import easy.find.job.model.jobs.superjob.SuperJobFindVacancies;
import easy.find.job.model.utils.Vacancy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by lollipop on 28.06.2017.
 */
public class VacancyCollector {
    private CopyOnWriteArrayList<Vacancy> vacancies;
    private static VacancyCollector instance;
    private static List<BaseFindVacancies> vacancyFinders;

    static {
        vacancyFinders = new ArrayList<>();
      /* vacancyFinders.add(new HHFindVacancies());
        vacancyFinders.add(new RabotaRuFindVacancies());
        vacancyFinders.add(new SuperJobFindVacancies());
        vacancyFinders.add(new CareeristFindVacancies());*/

    }

    private VacancyCollector() {
        vacancies = new CopyOnWriteArrayList<>();
        String uri = VacancyCollector.class.getResource("").getPath();
        File models = new File(uri.substring(1, uri.length()) + "/jobs");

        File[] files = models.listFiles();
        List<String> paths = new ArrayList<>();
        List<Class> classes = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                for (String line : file.list()) {
                    paths.add(file.getAbsolutePath() + "\\" + line);
                }
            } else paths.add(file.getAbsolutePath());
        }
        for (String clazz : paths) {
            try {
                classes.add(Class.forName(clazz.substring(clazz.indexOf("easy\\find\\job"), clazz.length() - 6).replaceAll("\\\\", ".")));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Class[] interfaces;
        for (Class clazz : classes) {
            System.out.println(clazz.getName());
            interfaces = clazz.getInterfaces();
            for (Class interfacce : interfaces) {
                if (interfacce.getName().equals(BaseFindVacancies.class.getName())) {
                    try {
                        vacancyFinders.add((BaseFindVacancies) clazz.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
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
