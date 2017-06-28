package easy.find.job.model.utils;

import java.util.Date;

/**
 * Created by lollipop on 28.06.2017.
 */
public class Vacancy implements Comparable<Vacancy>{
    private String name;
    private String requirement;
    private String url;
    private Date createdDate;

    public Vacancy(String name, String requirement, String url, Date createdDate) {
        this.name = name;
        this.requirement = requirement;
        this.url = url;
        this.createdDate = createdDate;
    }

    public String getRequirement() {
        return requirement;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public int compareTo(Vacancy vacancy) {
        return vacancy.getCreatedDate().compareTo(this.getCreatedDate());
    }
}
