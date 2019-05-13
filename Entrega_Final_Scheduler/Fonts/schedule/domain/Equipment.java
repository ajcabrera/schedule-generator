package schedule.domain;

public class Equipment implements Cloneable {
    private String name;
    private String description;

    //  ###----- CONSTRUCTORS -----###

    public Equipment() {
        this.name = "";
        this.description = "";
    }

    public Equipment(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public Equipment clone() throws CloneNotSupportedException {
        return (Equipment)super.clone();
    }

    //  ###----- CONSULTS / GETTERS -----###

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    //  ###----- MODIFIERS / SETTERS -----###

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
