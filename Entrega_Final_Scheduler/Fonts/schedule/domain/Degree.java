package schedule.domain;

import java.util.*;

public class Degree implements Cloneable {
    private String name;
    private String type;
    private HashMap<String,AcademicPlan> curriculum;

    private String institution;

    /*
    ###########################################################################
    #########################----- CONSTRUCTORS -----##########################
    ###########################################################################
    */

    /**
    * A Degree is created with default values
    */
    public Degree() {
        name = new String("");
        type = new String("");
        curriculum = new HashMap<String,AcademicPlan> ();
        institution = new String("");
    }

    /**
    * A Degree is created with the given values
    * @param newName The name the Degree will have
    * @param newType The type of degree this Degree will be
    */
    public Degree(String newName, String newType) {
        name = newName;
        type = newType;
        curriculum = new HashMap<String,AcademicPlan> ();
        institution = new String("");
    }

    @Override
    public Degree clone() throws CloneNotSupportedException {
        Degree d = (Degree) super.clone();
        d.curriculum = (HashMap<String, AcademicPlan>) d.curriculum.clone();
        return d;
    }

    /*
    ###########################################################################
    #####################----- MODIFIERS / SETTERS -----#######################
    ###########################################################################
    */

    /**
    * Sets the Degree's name
    * @param newName The name the Degree will have
    */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
    * Sets the Degree's type
    * @param newType The type the Degree will have
    */
    public void setType(String newType) {
        this.type = newType;
    }

    /**
    * Sets the Degree's institution
    * @param newInstitution The institution the Degree will belong to
    */
    public void setInstitution(String newInstitution) {
        this.institution = newInstitution;
        for (Map.Entry<String,AcademicPlan> plan : this.curriculum.entrySet()) {
            plan.getValue().setInstitution(newInstitution);
        }
    }

    /**
    * Adds an academic plan to the Degree's curriculum. If it existed already, it is overwritten
    * @param newPlan The academic plan that will be added to the curriculum
    */
    public void addAcademicPlan(AcademicPlan newPlan) {
        newPlan.setInstitution(institution);
        newPlan.setDegree(name);
        this.curriculum.put(newPlan.getName(), newPlan);
    }

    public void removeAcademicPlan(String ap) {
        curriculum.remove(ap);
    }


    /*
    ###########################################################################
    #####################----- CONSULTS / GETTERS -----########################
    ###########################################################################
    */

    /**
    * Returns the Degree's name
    * @return Returns a String with the Degree's name
    */
    public String getName() {
        return this.name;
    }

    /**
    * Returns the Degree's type
    * @return Returns a String that is the Degree's type
    */
    public String getType() {
        return this.type;
    }

    /**
    * Returns the Degree's curriculum grouped by the academic plan's ID
    * @return Returns a HashMap that is the Degree's curriculum, whose key is the academic plan's ID and the value is the academic plan identified by such ID
    */
    public HashMap<String,AcademicPlan> getCurriculumMap() {
        return this.curriculum;
    }

    /**
    * Returns the Degree's curriculum
    * @return Returns a Vector that is the Degree's curriculum
    */
    public Vector<AcademicPlan> getCurriculum() {
        Vector<AcademicPlan> curriculum = new Vector<AcademicPlan>();
        for ( Map.Entry<String,AcademicPlan> academicPlan : this.curriculum.entrySet() ) {
            curriculum.add(academicPlan.getValue());
        }
        return curriculum;
    }


    public Vector<GroupSubject> getAllGS(){
        Vector<GroupSubject> GS = new Vector<GroupSubject>();
        for( Map.Entry<String,AcademicPlan> APL : curriculum.entrySet() ){
            GS.addAll(APL.getValue().getAllGS());
        }
        return GS;
    }

    /**
    * Returns the academic plan with a given name
    * @param name The name of the desired academic plan
    * @return Returns the academic plan with the given name
    */
    public AcademicPlan getAcademicPlan(String name) {
        return curriculum.get(name);
    }

    /**
    * Returns the Degree's institution
    * @return Returns a String with the institution the academic plan is included in
    */
    public String getInstitution() {
        return this.institution;
    }

    public void saveAcademicPlans(ArrayList<String> l) {
        l.add(Integer.toString(curriculum.size()));
        for (String s: curriculum.keySet()) {
            l.add(curriculum.get(s).getName());
            curriculum.get(s).saveSubjects(l);
        }
    }

}
