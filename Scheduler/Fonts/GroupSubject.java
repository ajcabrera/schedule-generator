import java.util.*;

public class GroupSubject {
    private int classroomSize;
    private int sessionLength;
    private int sessionsWeek;
    private String sessionType; // Lab, Teo, Prob, Paseos por el monte...

    //Path
    private String institution;
    private String degree;
    private String academicPlan;
    private String subject;
    private String group;
    private String idSubgroup;


    /*
	###########################################################################
	#########################----- CONSTRUCTORS -----##########################
	###########################################################################
	*/

    /**
    * A GroupSubject is created with default values
    * All int are set to 1, all String are set to Null
    */
    public GroupSubject() {
        this.classroomSize = 1;
        this.sessionLength = 1;
        this.sessionsWeek = 1;
        this.sessionType = new String();

        this.institution = new String();
        this.degree = new String();
        this.academicPlan = new String();
        this.subject = new String();
        this.group = new String();
        this.idSubgroup = new String();
    }

    /**
    * A GroupSubject is created according to the given parameters
    * All remaining String are set to ""
    * @param classroomSize The minimum size the classroom has to have to host this GroupSubject, (classroomSize > 0)
    * @param sessionLength The ammount of following hours per session this GroupSubject will have to have, (sessionLength > 0)
    * @param sessionsWeek The ammount of sessions per week this GroupSubject will have to have, (sessionsWeek > 0)
    * @param sessionType The type of sessions this GroupSubject will have
    * @param idSubgroup The GroupSubject's ID
    */
    public GroupSubject(int classroomSize, int sessionLength, int sessionsWeek, String sessionType, String idSubgroup) {
        this.classroomSize = classroomSize;
        this.sessionLength = sessionLength;
        this.sessionsWeek = sessionsWeek;
        this.sessionType = sessionType;

        this.institution = new String();
        this.degree = new String();
        this.academicPlan = new String();
        this.subject = new String();
        this.group = new String();
        this.idSubgroup = idSubgroup;
    }

    public GroupSubject(GroupSubject gs) {
        this.classroomSize = gs.getClassroomSize();
        this.sessionLength = gs.getSessionLength();
        this.sessionsWeek = gs.getSessionsWeek();
        this.sessionType = gs.getSessionType();

        this.institution = gs.getInstitution();
        this.degree = gs.getDegree();
        this.academicPlan = gs.getAcademicPlan();
        this.subject = gs.getSubject();
        this.group = gs.getGroup();
        this.idSubgroup = gs.getID();
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
    * Sets the GroupSubject's classroom size
    * @param newClassroomSize The new minimum size the classrooms will have to have to host this GroupSubject (newClassroomSize > 0)
    */
    public void setClassroomSize(int newClassroomSize) {
        this.classroomSize = newClassroomSize;
    }

    /**
    * Sets the GroupSubject's session length
    * @param newSessionLength The ammount of following hours per session this GroupSubject will have to have (sessionLength > 0)
    */
    public void setSessionLength(int newSessionLength) {
        this.sessionLength = newSessionLength;
    }

    /**
    * Sets the GroupSubject's sessions per week
    * @param newSessionsWeek The ammount of sessions per week this GroupSubject will have to have (sessionsWeek > 0)
    */
    public void setSessionsWeek(int newSessionsWeek) {
        this.sessionsWeek = newSessionsWeek;
    }

    /**
    * Sets the GroupSubject's session type
    * @param newSessionType The type of sessions this GroupSubject will have
    */
    public void setSessionType(String newSessionType) {
        this.sessionType = newSessionType;
    }

    /**
    * Sets the GroupSubject's ID
    * @param newID The ID this GroupSubject will have
    */
    public void setID(String newID) {
        this.idSubgroup = newID;
    }

    /**
    * Sets the GroupSubject's institution
    * @param newInstitution The institution the GroupSubject will be in
    */
    public void setInstitution(String newInstitution) {
        this.institution = newInstitution;
    }

    /**
    * Sets the GroupSubject's degree
    * @param newDegree The degree the GroupSubject will be in
    */
    public void setDegree(String newDegree) {
        this.degree = newDegree;
    }

    /**
    * Sets the GroupSubject's academic plan
    * @param newAcademicPlan The academic plan the GroupSubject will be in
    */
    public void setAcademicPlan(String newAcademicPlan) {
        this.academicPlan = newAcademicPlan;
    }

    /**
    * Sets the GroupSubject's subject
    * @param newSubject The subject the GroupSubject will be in
    */
    public void setSubject(String newSubject) {
        this.subject = newSubject;
    }

    /**
    * Sets the GroupSubject's group
    * @param newGroup The group the GroupSubject will be in
    */
    public void setGroup(String newGroup) {
        this.group = newGroup;
    }

    /*
    ###########################################################################
    #####################----- CONSULTS / GETTERS -----########################
    ###########################################################################
    */
    /**
    * Returns the GroupSubject's minimum classroom size
    * @return Returns the minimum size the classrooms has to have to host this GroupSubject
    */
    public int getClassroomSize() {
        return this.classroomSize;
    }

    /**
    * Returns the GroupSubject's session length
    * @return Returns the ammount of following hours per session this GroupSubject has tho have
    */
    public int getSessionLength() {
        return this.sessionLength;
    }

    /**
    * Returns the GroupSubject's sessions per week
    * @return Returns the ammount of sessions per week this GroupSubject has to have
    */
    public int getSessionsWeek() {
        return this.sessionsWeek;
    }

    /**
    * Returns the GroupSubject's session type
    * @return Returns the type of sessions this GroupSubject has
    */
    public String getSessionType() {
        return this.sessionType;
    }

    /**
    * Returns the GroupSubject's ID
    * @return Returns the ID this GroupSubject has
    */
    public String getID() {
        return this.idSubgroup;
    }

    /**
    * Returns the GroupSubject's institution
    * @return Returns the institution the GroupSubject is in
    */
    public String getInstitution() {
        return this.institution;
    }

    /**
    * Returns the GroupSubject's degree
    * @return Returns the degree the GroupSubject is in
    */
    public String getDegree() {
        return this.degree;
    }

    /**
    * Returns the GroupSubject's academic plan
    * @return Returns the academic plan the GroupSubject is in
    */
    public String getAcademicPlan() {
        return this.academicPlan;
    }

    /**
    * Returns the GroupSubject's subject
    * @return Returns the subject the GroupSubject is in
    */
    public String getSubject() {
        return this.subject;
    }

    /**
    * Returns the GroupSubject's group
    * @return Returns the group the GroupSubject is in
    */
    public String getGroup() {
        return this.group;
    }
}
