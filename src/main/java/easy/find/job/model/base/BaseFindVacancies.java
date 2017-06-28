package easy.find.job.model.base;

import easy.find.job.model.utils.Vacancy;

import java.io.IOException;
import java.util.List;

/**
 * Created by lollipop on 28.06.2017.
 */
public interface BaseFindVacancies {
    List<Vacancy> getVacancies(String text) throws IOException;
}
