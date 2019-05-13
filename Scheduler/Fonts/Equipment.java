//package capaDominio;

public class Equipment implements Cloneable {
  private String name;
  private String description;

  //  ###----- CONSTRUCTORS -----###

  public Equipment() {
    // Default constructor
  }

  public Equipment(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
      return super.clone();
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
