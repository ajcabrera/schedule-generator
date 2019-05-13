package schedule.domain;

import java.util.Vector;
import java.lang.String;

public class Subject implements Cloneable {
    private String name;
    private int slots;
    private int maxGroupType;
    private Vector<Group> groups;

    private String institution;
    private String degree;
    private String academicPlan;

    /*
    ###########################################################################
    #########################----- CONSTRUCTORS -----##########################
    ###########################################################################
    */

    /**
    * A Subject with all the default values is created
    */
    public Subject() {
        name = new String("");
	slots = 1;
        maxGroupType = -1;
        institution = new String("");
	degree = new String("");
	academicPlan = new String("");

        groups = new Vector<Group> ();
        Group a = new Group();
        a.setDegree(degree);
        a.setAcademicPlan(academicPlan);
        a.setSubject(name);
        a.setInstitution(name);
        groups.add(a);
    }

    /**
    * A subject with the given values is created
    * @param name The name the Subject will have
    * @param slots The number of students that can course the subject (slots > 0)
    * @param numGroups The number of groups the subject will have (numGroups > 0)
    */
    public Subject(String name,int slots) {
        this.name = name;
        this.slots = slots;
        this.maxGroupType = -1;
        this.institution = new String("");
	this.degree = new String("");
	this.academicPlan = new String("");

        groups = new Vector<Group> ();
        Group a = new Group();
        a.setDegree(degree);
        a.setAcademicPlan(academicPlan);
        a.setSubject(name);
        a.setInstitution(name);
        groups.add(a);
    }

    public Subject(Subject s) {
        this.name = s.getName();
        this.slots = s.getSlots();
        this.maxGroupType = s.getMaxGroupType();

        this.institution = s.getInstitution();
        this.degree = s.getDegree();
        this.academicPlan = s.getAcademicPlan();

        this.groups = new Vector<Group>();
        for (Group g: s.getGroups()) {
            this.groups.add(new Group(g)); // Peta porque no existe una constructora Group(Group g)
        }
    }

    @Override
    public Subject clone() throws CloneNotSupportedException {
        return (Subject)super.clone();
    }

    /*
    ###########################################################################
    #####################----- MODIFIERS / SETTERS -----#######################
    ###########################################################################
    */

    /**
    * Sets the Subject's name
    * @param newName The name the Subject will have
    */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
    * Sets the Subject's slots
    * @param newSlots The slots the Subject will have (newSlots > 0)
    */
    public void setSlots(int newSlots) {
        this.slots = newSlots;

        int numGroups = this.slots/maxGroupType + ((this.slots % maxGroupType == 0) ? 0 : 1);
        for (int i = groups.size(); i < numGroups; i++) {
            groups.add(new Group(groups.get(0)));
            groups.get(i).setName(Integer.toString(((i+1)*10)));
        }    
    }

    /**
    * Adds a subgroup type to every Subject's group. If that type already exists, it will be overwritten
    * @param type The type the subgroups will have
    * @param classroomSize The minimum size the classrooms that host the subgroups will have to have (classroomSize > 0)
    * @param sessionLength The length of the sessions the subgroups will have to have (sessionLength > 0)
		* @param sessionsWeek The ammount of sessions per week the subgroups will have to have (sessionsWeek > 0)
    */
    public void addSubgroups(String type, int classroomSize, int sessionLength, int sessionsWeek) {
        if (classroomSize > maxGroupType) {
            maxGroupType = classroomSize;
            for (int i = 0; i < groups.size(); i++) {
                groups.get(i).setSlots(classroomSize);
                groups.get(i).addSubgroups(type,classroomSize, sessionLength, sessionsWeek);
            }
            int numGroups = this.slots/classroomSize + ((this.slots % classroomSize == 0) ? 0 : 1);
            for (int i = groups.size(); i < numGroups; i++) {
                groups.add(new Group(groups.get(0)));
                groups.get(i).setName(Integer.toString((i+1)*10));
            }
        }
        else {
            for (int i = 0; i < groups.size(); i++) {
                groups.get(i).addSubgroups(type, classroomSize, sessionLength, sessionsWeek);
            }
        }
    }

    /**
    * Removes all Subject's subgroups of a given type
    * @param type The type the removed subgroups have
    */
    public void removeSubgroups(String type) {
        int size = groups.get(0).getSizeSubgroup(type);
        for (int i = 0; i < groups.size(); i++) {
            this.groups.get(i).removeSubgroups(type);
        }
        if (groups.get(0).getTypes().size() == 0) {
            maxGroupType = -1;
        }
        else if (size == maxGroupType) {
            int x = groups.get(0).findMaxSubgroup(); 
            if (x < maxGroupType) {
                int numGroups = this.slots/x + ((this.slots % x == 0) ? 0 : 1);
                for (int i = groups.size()-1; i > numGroups; i--) {
                    groups.remove(i);
                }
                maxGroupType = x;
            }
        }
    }

    /**
    * Sets the Subject's institution
    * @param newInstitution The institution the Subject will be in
    */
    public void setInstitution(String newInstitution) {
        this.institution = newInstitution;
        for (int i = 0; i < this.groups.size(); i++) {
            groups.get(i).setInstitution(newInstitution);
        }
    }

    /**
    * Sets the Subject's degree
    * @param newDegree The degree the Subject will be in
    */
    public void setDegree(String newDegree) {
        this.degree = newDegree;
        for (int i = 0; i < this.groups.size(); i++) {
            groups.get(i).setDegree(newDegree);
        }
    }

    /**
    * Sets the Group's academic plan
    * @param newAcademicPlan The academic plan the Group will be in
    */
    public void setAcademicPlan(String newAcademicPlan) {
        this.academicPlan = newAcademicPlan;
        for (int i = 0; i < this.groups.size(); i++) {
            groups.get(i).setAcademicPlan(newAcademicPlan);
        }
    }


    /*
    ###########################################################################
    #####################----- CONSULTS / GETTERS -----########################
    ###########################################################################
    */

    /**
    * Returns the Subject's name
    * @return Returns a String with the Subject's name
    */
    public String getName() {
        return this.name;
    }

    public int getMaxGroupType() {
        return this.maxGroupType;
    }
    
    /**
    * Returns the Subject's slots
    * @return Returns an int with the Subject's slots
    */
    public int getSlots() {
        return this.slots;
    }

    /**
    * Returns the Subject's groups
    * @return Returns a Vector<Group> with all the Subject's groups
    */
    public Vector<Group> getGroups() {
        return this.groups;
    }

    /**
    * Returns the i'th Subject's group
    * @param i The position the returned group has in the Vector (0 <= i < this.groups.size())
    * @return Returns the i'th group in Subject's Vector<Group> groups
    */
    public Group getGroup(int i) {
        return this.groups.get(i);
    }

    /**
    * Returns the number of groups this Subject has
    * @return Returns an int with the amount of groups this Subject has
    */
    public int getNumberGroups() {
        return this.groups.size();
    }

    /**
    * Returns all the Subject's subgroups
    * @return Returns a Vector<GroupSubject> with all of Subject's subgroups
    */
    public Vector<GroupSubject> getSubgroups() {
        Vector<GroupSubject> vec = new Vector<GroupSubject>();
        for (int i = 0; i < groups.size(); i++) {
            vec.addAll(groups.get(i).getSubgroups());
        }
        return vec;
    }

    /**
    * Returns the Subject's institution
    * @return Returns the institution the Subject is in
    */
    public String getInstitution() {
        return this.institution;
    }

    /**
    * Returns the Subject's degree
    * @return Returns the degree the Subject is in
    */
    public String getDegree() {
        return this.degree;
    }

    /**
    * Returns the Subject's academic plan
    * @return Returns the academic plan the Subject is in
    */
    public String getAcademicPlan() {
        return this.academicPlan;
    }

    public Boolean asSubjectType(String name) {
        return groups.get(0).hasType(name);
    }
}
