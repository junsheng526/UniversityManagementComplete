package entity;

/**
 *
 * @author Your Name A
 */
import java.io.Serializable;

public class ProgrammeInfo implements Serializable {

    private String programmeCode;
    private String category;
    private String name;

    public ProgrammeInfo() {

    }

    public ProgrammeInfo(String programmeCode, String name) {
        this.programmeCode = programmeCode;
        this.name = name;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%-11s %-40s", programmeCode, name);
    }
}
