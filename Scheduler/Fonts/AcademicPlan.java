import java.util.*;

public class AcademicPlan implements Cloneable {
    private String name;
    private HashMap<String,Subject> subjects;

    private String institution;
    private String degree;

    /*
		###########################################################################
		#########################----- CONSTRUCTORS -----##########################
		###########################################################################
		*/

    /**
    * An academic plan is created with default values
    */
    public AcademicPlan() {
        // Default constructor
        name = new String("");
        subjects = new HashMap<String,Subject>();
        institution = new String("");
        degree = new String("");
    }

    /**
    * An academic plan is created according to the given parameters
    * @param newName Name of the academic plan
    */
    public AcademicPlan(String newName) {
        this.name = newName;
        institution = new String("");
        degree = new String("");
        subjects = new HashMap<String,Subject>();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /*
    ###########################################################################
    #####################----- MODIFIERS / SETTERS -----#######################
    ###########################################################################
    */

    /**
    * Sets the academic plan's name
    * @param newName The name the academic plan will have
    */
    public void setName(String newName) {
        this.name = newName;
        for (Map.Entry<String,Subject> subject : this.subjects.entrySet()) {
						subject.getValue().setAcademicPlan(newName);
				}
    }

    /**
    * Sets the academic plan's institution
    * @param newInstitution The institution the academic plan will be in
    */
    public void setInstitution(String newInstitution) {
        this.institution = newInstitution;
        for (Map.Entry<String,Subject> subject : this.subjects.entrySet()) {
						subject.getValue().setInstitution(newInstitution);
				}
    }

    /**
    * Sets the academic plan's degree
    * @param newDegree The degree the academic plan will be in
    */
    public void setDegree(String newDegree) {
        this.degree = newDegree;
        for (Map.Entry<String,Subject> subject : this.subjects.entrySet()) {
			subject.getValue().setDegree(newDegree);
		}
    }

    /**
    * Adds a subject to the academic plan
    * @param newSubject The new subject the academic plan will have
    */
    public void addSubject(Subject newSubject) {
        //Aquí podría ser muy útil asignar Institution-Degree-AcadPlan a la Subject
        newSubject.setInstitution(institution);
        newSubject.setDegree(degree);
        newSubject.setAcademicPlan(this.getName());
        subjects.put(newSubject.getName(), newSubject);
    }

    public void removeSubject(String subject) {
        subjects.remove(subject);
    }


    /*
		###########################################################################
		#####################----- CONSULTS / GETTERS -----########################
		###########################################################################
		*/

    /**
    * Returns the academic plan's name
    * @return Returns a String that is the academic plan's name
    */
    public String getName() {
        return this.name;
    }

    /**
    * Returns if a subject exists in the academic plan
    * @param subjectName The name of the subject that wants to be checked
    * @return Returns a boolean stating if the subject exists in the academic plan
    */
    public boolean hasSubject(String subjectName) {
        return this.subjects.containsKey(subjectName);
    }

    /**
    * Returns a Subject with the given name
    * @param name The name of the subject that wants to be retrieved
    * @return Returns a Subject with the given name
    */
    public Subject getSubject(String name) {
        return this.subjects.get(name);
    }


    public Vector<GroupSubject> getAllGS(){
      Vector<GroupSubject> GS = new Vector<GroupSubject>();
      for( Map.Entry<String,Subject> SUB : subjects.entrySet() ){
        GS.addAll(SUB.getValue().getSubgroups());
      }
      return GS;
    }


    /**
    * Returns the academic plan's institution
    * @return Returns a String with the institution the academic plan is included in
    */
    public String getInstitution() {
        return this.institution;
    }

    /**
    * Returns the academic plan's degree
    * @return Returns a String with the degree the academic plan is included in
    */
    public String getDegree() {
        return this.degree;
    }

    public void saveSubjects(ArrayList<String> l) {
        l.add(Integer.toString(subjects.size()));
        for (String s: subjects.keySet()) {
            l.add(subjects.get(s).getName());
            l.add(Integer.toString(subjects.get(s).getSlots()));
            subjects.get(s).getGroup(0).saveGroupType(l);
        }
    }

}
