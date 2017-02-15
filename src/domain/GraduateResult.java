package domain;

/**
 * Created by ${BIM} on 24.01.2017.
 */
public class GraduateResult {

    private String mark;
    private String details;

    public GraduateResult() {
    }

    public GraduateResult(String mark, String details) {
        this.mark = mark;
        this.details = details;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "GraduateResultDTO{" +
                "mark='" + mark + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

}
