import java.util.*;

public class Group implements Cloneable {
	private String name;
	private int slots;
	private HashMap<String,Vector<GroupSubject>> subgroups;

	private String institution;
	private String degree;
	private String academicPlan;
	private String subject;

	/*
	###########################################################################
	#########################----- CONSTRUCTORS -----##########################
	###########################################################################
	*/

	/**
	* A group is created with default values
	* All int to 1, All String to "", All data structures to their default constructors
	*/
	public Group() {
		this.name = new String("10");
		this.slots = 1;
		this.subgroups = new HashMap<String,Vector<GroupSubject>>();

		this.institution = new String("");
		this.degree = new String("");
		this.academicPlan = new String("");
		this.subject = new String("");
	}

	/**
	* A group is created with the given parameters
	* All unspecified String are set to "" and data structures to their default constructor
	* @param name The name the Group will have
	* @param slots The number of students that will be able to be inside the group
	*/
	public Group(String name, int slots) {
		this.name = name;
		this.slots = slots;
		this.subgroups = new HashMap<String,Vector<GroupSubject>>();

		this.institution = new String("");
		this.degree = new String("");
		this.academicPlan = new String("");
		this.subject = new String("");
	}

	// String type, int classroomSize, int sessionLength, int sessionsWeek
	public Group(Group g) {
		this.name = g.getName();
		this.slots = g.getSlots();
		this.subgroups = new HashMap<String,Vector<GroupSubject>>();

		for (Map.Entry<String, Vector<GroupSubject>> v : g.getSubgroupMap().entrySet()) {
			Vector<GroupSubject> nv = new Vector<GroupSubject>();
			for (GroupSubject gs : v.getValue()) {
				nv.add(new GroupSubject(gs));
			}
			this.subgroups.put(v.getKey(), nv);
		}

		this.institution = g.getInstitution();
		this.degree = g.getDegree();
		this.academicPlan = g.getAcademicPlan();
		this.subject = g.getSubject();
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
	* Sets the Group's name
	* @param newName The name the Group will have
	*/
	public void setName(String newName) {
		this.name = newName;
		for (Map.Entry<String,Vector<GroupSubject>> subgroup : this.subgroups.entrySet()) {
			for (int i = 0; i < subgroup.getValue().size(); i++) {
				subgroup.getValue().get(i).setGroup(newName);
			}
		}
	}

	/**
	* Sets the Group's slots.
	* The number of subgroups is recalculated to have just the minimum amount
	* @param newSlots The number of slots the Group will have (newSlots > 0)
	*/
	public void setSlots(int newSlots) {
		this.slots = newSlots;
		for (Map.Entry<String,Vector<GroupSubject>> subgroup : this.subgroups.entrySet()) {
			String type = subgroup.getValue().get(0).getSessionType();
			int classroomSize = subgroup.getValue().get(0).getClassroomSize();
			int sessionLength = subgroup.getValue().get(0).getSessionLength();
			int sessionsWeek = subgroup.getValue().get(0).getSessionsWeek();
			subgroup.setValue(this.getSubgroups(type, classroomSize, sessionLength, sessionsWeek));
		}
	}

	/**
	* Sets the Group's subgroups to be a copy of the given ones
	* Adjustes their Group to be this one
	* @param newSubgroups The subgroups that are to be copied
	*/
	public void setSubgroups(HashMap<String,Vector<GroupSubject>> newSubgroups) {
		this.subgroups = new HashMap<String,Vector<GroupSubject>>();
		for (Map.Entry<String,Vector<GroupSubject>> subgroup : newSubgroups.entrySet()) {
			GroupSubject gs = subgroup.getValue().get(0);
			String type = gs.getSessionType();
			int classroomSize = gs.getClassroomSize();
			int sessionLength = gs.getSessionLength();
			int sessionsWeek = gs.getSessionsWeek();
			this.addSubgroups(type,classroomSize,sessionLength,sessionsWeek);
		}
	}

	/**
	* Adds to Group the subgroups of a given type
	* If they already exist, they are overwritten
	* @param type The type the subgroups will have
	* @param classroomSize The minimum size the classrooms that host the subgroups will have to have (classroomSize > 0)
	* @param sessionLength The length of the sessions the subgroups will have to have (sessionLength > 0)
	* @param sessionsWeek The ammount of sessions per week the subgroups will have to have (sessionsWeek > 0)
	*/
	public void addSubgroups(String type, int classroomSize, int sessionLength, int sessionsWeek) {
		subgroups.put(type, this.getSubgroups(type, classroomSize, sessionLength, sessionsWeek));
	}

	/**
	* Removes all subgroups of a given type from Group
	* @param type The type of the removed subgroups
	*/
	public void removeSubgroups(String type) {
		this.subgroups.remove(type);
	}

	/**
    * Sets the Group's institution
    * @param newInstitution The institution the Group will be in
    */
    public void setInstitution(String newInstitution) {
        this.institution = newInstitution;
		for (Map.Entry<String,Vector<GroupSubject>> subgroup : this.subgroups.entrySet()) {
			for (int i = 0; i < subgroup.getValue().size(); i++) {
				subgroup.getValue().get(i).setInstitution(newInstitution);
			}
		}
    }

		/**
    * Sets the Group's degree
    * @param newDegree The degree the Group will be in
    */
    public void setDegree(String newDegree) {
        this.degree = newDegree;
		for (Map.Entry<String,Vector<GroupSubject>> subgroup : this.subgroups.entrySet()) {
			for (int i = 0; i < subgroup.getValue().size(); i++) {
				subgroup.getValue().get(i).setDegree(newDegree);
			}
		}
    }

		/**
    * Sets the Group's academic plan
    * @param newAcademicPlan The academic plan the Group will be in
    */
    public void setAcademicPlan(String newAcademicPlan) {
        this.academicPlan = newAcademicPlan;
		for (Map.Entry<String,Vector<GroupSubject>> subgroup : this.subgroups.entrySet()) {
			for (int i = 0; i < subgroup.getValue().size(); i++) {
				subgroup.getValue().get(i).setAcademicPlan(newAcademicPlan);
			}
		}
    }

		/**
    * Sets the Group's subject
    * @param newSubject The subject the Group will be in
    */
    public void setSubject(String newSubject) {
        this.subject = newSubject;
		for (Map.Entry<String,Vector<GroupSubject>> subgroup : this.subgroups.entrySet()) {
			for (int i = 0; i < subgroup.getValue().size(); i++) {
				subgroup.getValue().get(i).setSubject(newSubject);
			}
		}
    }


	/*
	###########################################################################
	#####################----- CONSULTS / GETTERS -----########################
	###########################################################################
	*/

	/**
	* Returns a Vector of subgroups linked to Group
	* @param type The type the subgroups will have
	* @param classroomSize The minimum size the classroom has to have to host this subgroups, (classroomSize > 0)
	* @param sessionLength The ammount of following hours per session this subgroups will have to have, (sessionLength > 0)
	* @param sessionsWeek The ammount of sessions per week this subgroups will have to have, (sessionsWeek > 0)
	* @return Returns a Vector of GroupSubject that simbolize the subgroups of this group, having the given parameters. There will be as many groups as the times ~classroomSize~ fits into ~slots~ (ceil)
	*/
	private Vector<GroupSubject> getSubgroups(String type, int classroomSize, int sessionLength, int sessionsWeek) {
		Vector<GroupSubject> sessGroup = new Vector<GroupSubject>();
		for (int i = 0; i < ((this.slots / classroomSize) + ((this.slots % classroomSize == 0) ? 0 : 1)); i++) {
			GroupSubject g = new GroupSubject(classroomSize, sessionLength, sessionsWeek, type, Integer.toString(i+1));
			g.setGroup(this.name);
			g.setSubject(this.subject);
			g.setAcademicPlan(this.academicPlan);
			g.setDegree(this.degree);
			g.setInstitution(this.institution);
			sessGroup.add(g);
		}
		return sessGroup;
	}

	public int findMaxSubgroup() {
		int max = 1;
		for (String s : subgroups.keySet()) {
			GroupSubject aux = subgroups.get(s).get(0);
			if (aux.getClassroomSize() > max) max = aux.getClassroomSize();
		}
		return max;
	}

	public int getSizeSubgroup(String type) {
		for (String s : subgroups.keySet()) {
			if (s.equals(type)) return subgroups.get(s).get(0).getClassroomSize();
		}
		return -1;
	}

	/**
	* Returns the Group's name
	* @return Returns a String with the Group's name
	*/
	public String getName() {
		return this.name;
	}

	/**
	* Returns the Group's slots
	* @return Returns an int with the Group's slots
	*/
	public int getSlots() {
		return this.slots;
	}

	/**
	* Returns all the Group's subgroups, grouped by their type
	* @return Returns a HashMap where the key is the type of the subgroup and the value is the Vector of subgroups that have that given type
	*/
	public HashMap<String,Vector<GroupSubject>> getSubgroupMap() {
		return this.subgroups;
	}

	/**
	* Returns all the Group's subgroups
	* @return Returns a Vector with all the Group's subgroups
	*/
	public Vector<GroupSubject> getSubgroups() {
		Vector<GroupSubject> vec = new Vector<GroupSubject>();
		for (Map.Entry<String,Vector<GroupSubject>> subgroup : subgroups.entrySet()) {
			vec.addAll(subgroup.getValue());
		}
		return vec;
	}

	/**
	* Returns all the Group's subgroups of a given type
	* @param type The type the returned subgroups will have to have
	* @return Returns a Vector with all the Group's subgroups that belong to a given type
	*/
	public Vector<GroupSubject> getSubgroups(String type) {
		return this.subgroups.get(type);
	}

	/**
	* Returns all the Group's subgroups types
	* @return Returns a Vector<String> with all the types the Group's subgroups have
	*/
	public Vector<String> getTypes() {
		Vector<String> vec = new Vector<String>();
		for (Map.Entry<String,Vector<GroupSubject>> subgroup : subgroups.entrySet()) {
			vec.add(subgroup.getKey());
		}
		return vec;
	}

	/**
	* Returns if the group has a subgroup of the given type
	* @param type The type that wants to be checked
	* @return Returns a boolean that indicates if there is any subgroup of the given type
	*/
	public boolean hasType(String type) {
		return this.subgroups.containsKey(type);
	}

	/**
    * Returns the Group's institution
    * @return Returns the institution the Group is in
    */
    public String getInstitution() {
        return this.institution;
    }

		/**
    * Returns the Group's degree
    * @return Returns the degree the Group is in
    */
    public String getDegree() {
        return this.degree;
    }

		/**
    * Returns the Group's academic plan
    * @return Returns the academic plan the Group is in
    */
    public String getAcademicPlan() {
        return this.academicPlan;
    }

		/**
    * Returns the Group's subject
    * @return Returns the subject the Group is in
    */
    public String getSubject() {
        return this.subject;
    }

    public void saveGroupType(ArrayList<String> l) {
    	l.add(Integer.toString(subgroups.size()));
        for (String s: subgroups.keySet()) {
            l.add(subgroups.get(s).get(0).getSessionType());
            l.add(Integer.toString(subgroups.get(s).get(0).getClassroomSize()));
            l.add(Integer.toString(subgroups.get(s).get(0).getSessionLength()));
            l.add(Integer.toString(subgroups.get(s).get(0).getSessionsWeek()));
        }

    }
}
