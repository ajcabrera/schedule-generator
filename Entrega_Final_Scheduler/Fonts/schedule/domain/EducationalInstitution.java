package schedule.domain;

import java.util.*;

public class EducationalInstitution {
    private String name;
    private HashMap<String,Degree> academicOffer;
    private HashMap<String,Restriction> restrictions;

    private Vector<Restriction> staticRestrictions; // Restrictions used in applyStaticRestrictions
    private Vector<Restriction> dynamicRestrictions; // Restrictions used in updateRestSession

    private HashMap<String,Building> buildings;
    private HashMap<String,Equipment> equipments;
    private Vector<Session> sessions;
    private Vector<Classroom> CR;
    /*
    ###########################################################################
    #########################----- CONSTRUCTORS -----##########################
    ###########################################################################
    */

    /**
    * An EducationalInstitution is created with default values
    */
    public EducationalInstitution() {
        name = new String("");
        academicOffer = new HashMap<String,Degree>();
        restrictions = new HashMap<String,Restriction>();
        buildings = new HashMap<String,Building>();
        equipments = new HashMap<String,Equipment>();
        staticRestrictions = new Vector<Restriction> ();
        dynamicRestrictions = new Vector<Restriction> ();
        CR = new Vector<Classroom>();
        sessions = new Vector<Session>();
    }

    /**
    * An EducationalInstitution is created with the given parameters
    * @param newName The name the EducationalInstitution will have
    */
    public EducationalInstitution(String newName) {
        name = newName;
        academicOffer = new HashMap<String,Degree>();
        restrictions = new HashMap<String,Restriction>();
        buildings = new HashMap<String,Building>();
        equipments = new HashMap<String,Equipment>();
        staticRestrictions = new Vector<Restriction> ();
        dynamicRestrictions = new Vector<Restriction> ();
        CR = new Vector<Classroom>();
        sessions = new Vector<Session>();
    }


    /*
    ###########################################################################
    #####################----- MODIFIERS / SETTERS -----#######################
    ###########################################################################
    */
    /**
    * Sets the EducationalInstitution's name
    * @param newName The name the EducationalInstitution will have
    */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
    * Adds a degree to the EducationalInstitution's academic offer
    * @param newDegree The degree that will be added to the academic offer
    */
    public void addDegree(Degree newDegree) {
        newDegree.setInstitution(name);
        this.academicOffer.put(newDegree.getName(),newDegree);
    }

    /**
    * Adds a restriction to EducationalInstitution
    * @param restriction The restriction added to EducationalInstitution
    */
    public void addRestriction(String alias,Vector<String> restriction) {
        this.restrictions.put(alias, new Restriction(restriction));
    }

    public void addRestriction(String alias,Restriction restriction) {
        this.restrictions.put(alias, restriction);
    }

    public void removeRestriction(String alias) {
        this.restrictions.remove(alias);
    }

    /**
    * Adds a building to EducationalInstitution
    * @param building The building added to EducationalInstitution's buildings
    */
    public void addBuilding(Building building) {
        this.buildings.put(building.getName(),building);
    }

    /**
    * Sets the EducationalInstitution's buildings
    * @param newBuildings The buildings set to EducationalInstitution
    */
    public void setBuildings(HashMap<String,Building> newBuildings) {
        this.buildings = newBuildings;
    }

    /**
    * Sets the equipments that can be used in EducationalInstitution's classrooms
    * @param newEquipments The equipments that can be used in EducationalInstitution's classrooms
    */
    public void setEquipments(HashMap<String,Equipment> newEquipments) {
        this.equipments = newEquipments;
    }

    /**
    * Adds an equipment to the group of equipments that can be used in EducationalInstitution's classrooms
    * @param equipment The equipment that is being added
    */
    public void addEquipment(Equipment equipment) {
        this.equipments.put(equipment.getName(), equipment);
    }

    public void elimEquipment(Equipment e) {
        this.equipments.remove(e);
        for (int i = 0; i < buildings.size(); i++) buildings.get(i).elimEquipment(e.getName());
    }


    /*
    ###########################################################################
    #####################----- CONSULTS / GETTERS -----########################
    ###########################################################################
    */

    /**
    * Returns the EducationalInstitution's name
    * @return Returns a String which is the EducationalInstitution's name
    */
    public String getName() {
        return this.name;
    }

    /**
    * Returns the number of degrees in offer by the EducationalInstitution
    * @return Returns an int which is the number of degrees in offer by the EducationalInstitution
    */
    public int getNumDegrees() {
        return this.academicOffer.size();
    }

    /**
    * Returns the degree with a given name from the academic offer
    * @param name The name of the degree that is wanted to be retrieved
    * @return Returns a Degree, which is the one with the given name
    */
    public Degree getDegree(String name) {
        return academicOffer.get(name);
    }

    public Classroom getOneClassroom(String name) {
        for(String key : buildings.keySet()) {
            Building aux = buildings.get(key);
            if (!aux.getOneClassroom(name).equals(null)) return aux.getOneClassroom(name);
        }
        return null;
    }

    /**
    * Returns the Map with all the Equipments in the EI, keyed by the name of the Equipment
    * @return Returns the HashMap <nameEquipment,Equipment>
    */
    public HashMap<String,Equipment> getAllEquipments(){
        return equipments;
    }

    /**
    * Returns the Map with all the Buildings in the EI, keyed by the name of the Building
    * @return Returns the HashMap <nameBuilding,Building>
    */
    public HashMap<String,Building> getAllBuildings(){
        return buildings;
    }

    /**
    * @return Returns the hashMap with all the degrees in academic offer
    */
    public HashMap<String,Degree> getAllDegrees(){
        return academicOffer;
    }

    public HashMap<String,Restriction> getAllRestrictions() {
        return restrictions;
    }

    public Set<String> getRestrictionsKeys() {
        return restrictions.keySet();
    }

    public Restriction getRestriction(String s) {
        return restrictions.get(s);
    }

    /*
    ###########################################################################
    ##################-----  PARTE CHECK RESTRICTION -----#####################
    ###########################################################################
    */

    private Boolean correctDegreeAP(String a, String b) {
        if (academicOffer.get(a) == null) return false;
        if (academicOffer.get(a).getAcademicPlan(b) == null) return false;
        return true;
    }

    private Boolean correctDay(String a) {
        if (a.equals("Monday") || a.equals("Tuesday") || a.equals("Wednesday") || a.equals("Thursday") || a.equals("Friday")) return true;
        return false;
    }

    private Boolean correctInterval(String a, String b) {
        if (Integer.parseInt(a) < Integer.parseInt(b) && correctHour(a) && Integer.parseInt(a) < 21) return true;
        return false;
    }

    private Boolean correctHour(String a) {
        int x = Integer.parseInt(a);
        if (x > 8 && x < 20) return true;
        return false;
    }

    private Boolean correctSubjects(Vector<String> r, int index) {
        for (int i = index; i < r.size(); i++) {
            if (academicOffer.get(r.get(2)).getAcademicPlan(r.get(3)).getSubject(r.get(i)) == null) return false;
        }
        return true;
    }

    private Boolean correctSubject(String degree, String ap, String a) {
        if (academicOffer.get(degree).getAcademicPlan(ap).getSubject(a) == null) return false;
        return true;
    }

    private Boolean correctSST(String degree, String ap, String subject, String type) {
        if (academicOffer.get(degree).getAcademicPlan(ap).getSubject(subject).asSubjectType(type)) return true;
        return false;
    }

    private Boolean correctBuCl(Vector<String> r, int index) {
        if (r.get(index).equals("Bu") && buildings.get(r.get(index+1)) != null) return true;
        else if (r.get(index).equals("Cl") && buildings.get(r.get(index+1))!= null && buildings.get(r.get(index+1)).containsClassroom(r.get(index+2))) return true;
        return false;
    }

    private Boolean correctClassroom(String building, String classroom) {
        if (buildings.get(building) == null) return false;
        if (buildings.get(building).containsClassroom(classroom)) return true;
        return false;
    }

    private Boolean correctNumGroup(Vector<String> r) {
        int numG = academicOffer.get(r.get(2)).getAcademicPlan(r.get(3)).getSubject(r.get(4)).getNumberGroups();
        for (int i = 1; i <= numG; i++) {
            if (r.get(6).equals(Integer.toString(i*10))) return true;
        }
        return false;
    }

    public Boolean rightRestriction(Vector<String> r) {
        switch(r.get(0)) {
            case "1":
                if (r.size() < 8) return false;
                if (correctDegreeAP(r.get(2),r.get(3)) && correctDay(r.get(4)) && correctInterval(r.get(5),r.get(6)) && correctSubjects(r, 7)) return true; break;
            case "2":
                if (r.size() != 7) return false;
                if (correctDegreeAP(r.get(2),r.get(3)) && correctSubject(r.get(2),r.get(3),r.get(4)) && correctSST(r.get(2),r.get(3),r.get(4),r.get(5)) && (equipments.get(r.get(6))!= null)) return true; break;
            case "3":
                if (r.size() < 5)return false;
                if (correctDegreeAP(r.get(2),r.get(3)) && (r.get(4).equals("Morning") || r.get(4).equals("Afternoon") || r.get(4).equals("Balanced")) && correctSubjects(r,5)) return true; break;
            case "4":
                if (r.size() < 5) return false;
                if (correctDegreeAP(r.get(2),r.get(3)) && correctSubjects(r,4)) return true; break;
            case "5":
                if (r.size() < 4) return false;
                if (correctBuCl(r,2)) return true; break;
            case "6":
                if (r.size() < 8) return false;
                if (correctDegreeAP(r.get(2),r.get(3)) && correctSubject(r.get(2),r.get(3),r.get(4)) && correctSST(r.get(2),r.get(3),r.get(4),r.get(5)) && correctBuCl(r,6)) return true; break;
            case "9":
            case "7":
                if (r.size() != 7) return false;
                if (correctDegreeAP(r.get(2),r.get(3)) && correctSubject(r.get(2),r.get(3),r.get(4)) && correctSST(r.get(2),r.get(3),r.get(4),r.get(5)) && correctSST(r.get(2),r.get(3),r.get(4),r.get(6))) return true; break;
            case "8":
                if (r.size() != 6) return false;
                if (correctDegreeAP(r.get(2),r.get(3)) && correctSubject(r.get(2),r.get(3),r.get(4)) && correctDay(r.get(5))) return true; break;
            case "10":
                if (r.size() != 11) return false;
                if (correctDegreeAP(r.get(2),r.get(3)) && correctSubject(r.get(2),r.get(3),r.get(4)) && correctSST(r.get(2),r.get(3),r.get(4),r.get(5)) && correctNumGroup(r) && correctDay(r.get(7)) && correctHour(r.get(8)) && correctClassroom(r.get(9),r.get(10))) return true; break;
            case "11":
                if (r.size() != 7) return false;
                if (correctDegreeAP(r.get(2),r.get(3)) && correctSubject(r.get(2),r.get(3),r.get(4)) && correctSST(r.get(2),r.get(3),r.get(4),r.get(5)) && correctDay(r.get(6))) return true; break;
            case "12":
                if (r.size() != 6) return false;
                if (correctDegreeAP(r.get(2),r.get(3)) && correctSubject(r.get(2),r.get(3),r.get(4)) && correctSST(r.get(2),r.get(3),r.get(4),r.get(5))) return true; break;
            case "15":
            case "16":
                return true;
        }
        return false;
    }

    /*
    ###########################################################################
    #####################-----  PARTE EDIT/REMOVE -----########################
    ###########################################################################
    */

    public void removeAcademicPlan(String degree, String name) {
        Vector<String> aux = new Vector<String> ();
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            if (!r.getParameter(0).equals("5") && r.getParameter(2).equals(degree) && r.getParameter(3).equals(name)) aux.add(i);
        }
        for (int i = 0; i < aux.size(); i++) restrictions.remove(aux.get(i));
    }

    public void editAcademicPlan(String degree, String newName, String oldName) {
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            if (!r.getParameter(0).equals("5") && r.getParameter(2).equals(degree) && r.getParameter(3).equals(oldName)) r.modifyParameter(3,newName);
        }
    }

    public void removeDegree(String name) {
        Vector<String> aux = new Vector<String> ();
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            if (!r.getParameter(0).equals("5") && r.getParameter(2).equals(name)) aux.add(i);
        }
        for (int i = 0; i < aux.size(); i++) restrictions.remove(aux.get(i));
    }

    public void editDegree(String newName, String oldName) {
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            if (!r.getParameter(0).equals("5") && r.getParameter(2).equals(oldName)) r.modifyParameter(2,newName);
        }
    }

    public void removeBuilding(String name) {
        Vector<String> aux = new Vector<String> ();
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            if (r.getParameter(0).equals("5")  && r.getParameter(3).equals(name)) aux.add(i);
            else if (r.getParameter(0).equals("6")  && r.getParameter(7).equals(name)) aux.add(i);
        }
        for (int i = 0; i < aux.size(); i++) restrictions.remove(aux.get(i));
    }

    public void editBuilding(String newName, String oldName) {
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            if (r.getParameter(0).equals("5") && r.getParameter(3).equals(oldName)) r.modifyParameter(4,newName);
            else if (r.getParameter(0).equals("6") && r.getParameter(7).equals(oldName)) r.modifyParameter(4,newName);
        }
    }

    public void removeClassroom(String name, String building) {
        Vector<String> aux = new Vector<String> ();
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            if (r.getParameter(0).equals("5") && r.getParameter(2).equals("Cl") && r.getParameter(3).equals(building) && r.getParameter(4).equals(name)) aux.add(i);
            else if (r.getParameter(0).equals("6") && r.getParameter(6).equals("Cl") && r.getParameter(7).equals(building) && r.getParameter(8).equals(name)) aux.add(i);
        }
        for (int i = 0; i < aux.size(); i++) restrictions.remove(aux.get(i));
    }

    public void editClassroom(String newName, String oldName, String building) {
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            if (r.getParameter(0).equals("5") && r.getParameter(2).equals("Cl") && r.getParameter(3).equals(building) && r.getParameter(4).equals(oldName)) r.modifyParameter(4,newName);
            else if (r.getParameter(0).equals("6") && r.getParameter(6).equals("Cl") && r.getParameter(7).equals(building) && r.getParameter(8).equals(oldName)) r.modifyParameter(8,newName);
        }
    }

    public void removeEquipment(String name) {
        Vector<String> aux = new Vector<String> ();
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            if (r.getParameter(0).equals("2") && r.getParameter(6).equals(name)) aux.add(i);
        }
        for (int i = 0; i < aux.size(); i++) restrictions.remove(aux.get(i));
    }

    public void editEquipment(String newName, String oldName) {
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            if (r.getParameter(0).equals("2") && r.getParameter(6).equals(oldName)) r.modifyParameter(6,newName);
        }
    }

    private Boolean rightDegreeAndAP(Restriction r, String degree, String ap) {
        if (r.getParameter(2).equals(degree) && r.getParameter(3).equals(ap)) return true;
        return false;
    }

    // Functions that need to be implemented
    public void removeSubject(String name, String degree, String ap) {
        Vector<String> aux = new Vector<String> ();
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            switch(r.getParameter(0)) {
            case "2" : case "6": case "7": case "8": case "9": case "10": case "12":
            case "11": if (rightDegreeAndAP(r,degree,ap) && r.getParameter(4).equals(name)) aux.add(i); break;
            case "1" : if (rightDegreeAndAP(r,degree,ap) && r.posSubject(name,7)!=-1) aux.add(i); break;
            case "3" : if (rightDegreeAndAP(r,degree,ap) && r.posSubject(name,5)!=-1) aux.add(i); break;
            case "4": case "16":
            case "15": if (rightDegreeAndAP(r,degree,ap) && r.posSubject(name,4)!=-1) aux.add(i); break;
            }
        }
        for (int i = 0; i < aux.size(); i++) restrictions.remove(aux.get(i));
    }

    public void editSubject(String newName, String oldName, String degree, String ap) {
        for (String i : restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            switch(r.getParameter(0)) {
            case "2" : case "6": case "7": case "8": case "9": case "10": case "12":
            case "11": if (rightDegreeAndAP(r,degree,ap) && r.getParameter(4).equals(oldName)) r.modifyParameter(4,newName); break;
            case "1" : if (rightDegreeAndAP(r,degree,ap) && r.posSubject(oldName,7)!=-1) r.modifyParameter(r.posSubject(oldName,7),newName); break;
            case "3" : if (rightDegreeAndAP(r,degree,ap) && r.posSubject(oldName,5)!=-1) r.modifyParameter(r.posSubject(oldName,5),newName); break;
            case "4": case "16":
            case "15": if (rightDegreeAndAP(r,degree,ap) && r.posSubject(oldName,4)!=-1) r.modifyParameter(r.posSubject(oldName,4),newName); break;
            }
        }
    }

    public void removeSubjectGroupType(String name, String degree, String ap, String subject) {
        Vector<String> aux = new Vector<String> ();
        for (String i: restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            switch(r.getParameter(0)) {
            case "2" : case "6": case "10": case "12":
            case "11": if (rightDegreeAndAP(r,degree,ap) && r.getParameter(4).equals(subject) && r.getParameter(5).equals(name)) aux.add(i); break;
            case "7" :
            case "8" : if (rightDegreeAndAP(r,degree,ap) && r.getParameter(4).equals(subject) && r.posSubject(name,5) != -1) aux.add(i); break;
            }
        }
        for (int i = 0; i < aux.size(); i++) restrictions.remove(aux.get(i));
    }

    public void editSubjectGroupType(String newName, String oldName, String degree, String ap, String subject) {
        for (String i: restrictions.keySet()) {
            Restriction r = restrictions.get(i);
            switch(r.getParameter(0)) {
            case "2" : case "6": case "10": case "12":
            case "11": if (rightDegreeAndAP(r,degree,ap) && r.getParameter(4).equals(subject) && r.getParameter(5).equals(oldName)) r.modifyParameter(5,newName); break;
            case "7" :
            case "8" : if (rightDegreeAndAP(r,degree,ap) && r.getParameter(4).equals(subject) && r.posSubject(oldName,5) != -1) r.modifyParameter(r.posSubject(oldName,5),newName); break;
            }
        }
    }



    /*
    ###########################################################################
    #####################-----   PARTE ALGORITMO  -----########################
    ###########################################################################
    */
    /**
    * Returns all the EducationalInstitution's classrooms
    * @return Returns a Vector<Classroom> with all the classrooms of EducationalInstitution
    */

    private int getIndexCR(String building, String classroom) {
        for (int i = 0; i < CR.size(); i++) {
            if (CR.get(i).getBuilding().getName().equals(building) && CR.get(i).getName().equals(classroom)) return i;
        }
        return -1;
    }

    public Boolean modifySchedule(String oldBuilding, String oldClassroom, int oldPos, String newBuilding, String newClassroom, int newPos) {
        Classroom oldClass = CR.get(getIndexCR(oldBuilding,oldClassroom));
        Session actual = oldClass.getSlotInfo(oldPos);
        int x = getIndexCR(newBuilding, newClassroom);
        if (!actual.posAvailable(x*60+newPos)) return false;
        for (int i = 0; i < actual.getGS().getSessionLength(); i++) {
            oldClass.removeTimeSlot(oldPos+i);
            CR.get(x).addTimeSlot(newPos+i, actual);
        }
        actual.assignPosition(x*60+newPos);
        applyStaticRestrictions(0);
        for (int j = 0; j < sessions.size(); j++) {
            updateRestSession(j,sessions.size()-1,sessions.get(j).getPos());
        }
        for (int j = sessions.size()-1; j >= 0; j--) {
            reverseUpdateRestSession(j,j-1,sessions.get(j).getPos());
        }
        return true;
    }

    private void getAllClassrooms() {
        Vector<String> bu = new Vector<String> ();
        Vector<Restriction> cl = new Vector<Restriction> ();
        for (String j : restrictions.keySet()) {
            Restriction r = restrictions.get(j);
            if (r.getParameter(0).equals("5")) {
                    if (r.getParameter(2).equals("Bu")) bu.add(r.getParameter(3));
                    else if (r.getParameter(2).equals("Cl")) cl.add(r);
            }
        }

        CR = new Vector<Classroom>();
        for (Map.Entry<String,Building> building : buildings.entrySet()) {
            Building bAux = building.getValue();
            if (!bu.contains(bAux.getName())) {
                for(String key : bAux.getSetKeysClassrooms()) {
                    bAux.getOneClassroom(key).resetTimeSlots();
                    CR.add(bAux.getOneClassroom(key));
                }
            }
        }
        for (Restriction r : cl) {
            for (int i = 0; i < CR.size(); i++) {
                Classroom c = CR.get(i);
                if (c.getBuilding().getName().equals(r.getParameter(3)) && c.getName().equals(r.getParameter(4))) CR.remove(i);
            }
        }
    }

    private Classroom getClassroom(String name){
        for(int i = 0; i < CR.size(); i++)
            if( CR.get(i).getName().equals(name) ) return CR.get(i);
        return null;
    }

    private Vector<GroupSubject> getAllGS(){
        Vector<GroupSubject> GS = new Vector<GroupSubject>();
        for( Map.Entry<String,Degree> DEG : academicOffer.entrySet() ){
            GS.addAll(DEG.getValue().getAllGS());
        }
        return GS;
    }

    private void generateSession(){
        sessions = new Vector<Session>();
        Vector<GroupSubject> VGS = getAllGS();
        for(int i = 0; i < VGS.size(); i++) {
            GroupSubject gsAux = VGS.get(i);
            for (int j = 0; j < gsAux.getSessionsWeek(); j++) {
                sessions.add( new Session( gsAux, j+1 , CR.size() ) );
            }
        }
    }

    private Boolean isInRestriction(Restriction r, GroupSubject s) {
        switch (r.getParameter(0)) {
            case "7":
            case "9":
            case "12":
                if (r.getParameter(2).equals(s.getDegree()) && r.getParameter(3).equals(s.getAcademicPlan()) && r.getParameter(4).equals(s.getSubject())) return true; break;
            case "16":
            case "15":
                if (r.getParameter(2).equals(s.getDegree()) && r.getParameter(3).equals(s.getAcademicPlan()) && r.isInList(s.getSubject(),4)) return true; break;
        }
        return false;
    }
    
    // Updates complexity of all sessions under current circunstances
    private void computeComplexity(){
    //Compute Complexity after restrictions
        for(int j = 0; j < sessions.size(); j++){
            int bonus = 0;
            for (int i = 0; i < dynamicRestrictions.size(); i++) {
                if (isInRestriction(dynamicRestrictions.get(i), sessions.get(j).getGS())) bonus++;
            }
            sessions.get(j).computeComplexity(bonus);
        }
    }

    // PRE ::  pos must be between 0 and N, where N is all GSubject's bitSet size (-1), (for us, 60*numClassrooms-1).
    // POST :: Returns true if all GS have enough spots available to satisfy their requirements. false otherwise.
    private Boolean stillSAT( int pointer, int pos){
        Session currentS = sessions.get(pointer);
        int length = currentS.getGS().getSessionLength();
        if ((pos%60)/12 != ((pos+length-1)%60)/12) return false; // sessions can't be on different days
        for (int k = 0; k < length; k++) {
            if (!currentS.getBit(pos+k)) return false;
        }
        pointer++;
        while( pointer < sessions.size() ){
            currentS = sessions.get(pointer);
            if((currentS.getNumHoursAvailableOther(pos,length,CR.size())) < 1) return false;
            pointer++;
        }
        return true;
    }

    private void readRestrictions() {
        staticRestrictions = new Vector<Restriction>();
        dynamicRestrictions = new Vector<Restriction>();
        for (String i : restrictions.keySet()) {
            switch (restrictions.get(i).getParameter(0)) {
                case "1" :
                case "2" :
                case "3" :
                case "4" :
                case "6" :
                case "8" :
                case "10":
                case "11": staticRestrictions.add(restrictions.get(i)); break;
                case "15" :
                case "7" :
                case "9":
                case "16":
                case "12": dynamicRestrictions.add(restrictions.get(i)); break;
            }
        }
    }

    //Goes throw all DynamicRestrictions and find the ones that includes basesessions
    private Vector<Restriction> getDynamicRestrictionssessions(Session basesessions) {
        Vector<Restriction> a = new Vector<Restriction> ();
        for (int i = 0; i < dynamicRestrictions.size(); i++) {
            Restriction restric = dynamicRestrictions.get(i);
            switch (restric.getParameter(0)) {
                case "16":
                case "15" : if (rightSubjectList(basesessions.getGS(),restric,4)) a.add(restric); break;
                case "7" :
                case "9": if (isOneOfTheSST(basesessions.getGS(),restric) != -1) a.add(restric); break;
                case "12": if (isTheSST(basesessions.getGS(),restric)) a.add(restric); break;
            }
        }
        return a;
    }

    // Clears all bits for impossible positions in each sessions.
    private void applyStaticRestrictions(int fromWhere){
    // Check for all subgroups if they fit in each classroom
        for (int i = fromWhere; i < sessions.size(); i++){
                Session aux = sessions.get(i);
                aux.setToOne(CR.size());
            for(int j = 0; j < CR.size(); j++){
                if(aux.getGS().getClassroomSize() > CR.get(j).getSize())
                aux.setBits(j*60,(j+1)*60,false);
            }
            for (int j = 0; j < staticRestrictions.size(); j++) {
                switch(staticRestrictions.get(j).getParameter(0)) {
                    case "1" : blockTimeFrame(aux,staticRestrictions.get(j)); break;
                    case "2" : GSSTypeNeedsEquipment(aux,staticRestrictions.get(j)); break;
                    case "3" : subjectMorAfter(aux,staticRestrictions.get(j)); break;
                    case "4" : needsAccesibility(aux,staticRestrictions.get(j)); break;
                    case "6" : imposeClOrBu(aux,staticRestrictions.get(j)); break;
                    case "8" : subjectNotDayX(aux,staticRestrictions.get(j)); break;
                    case "10": imposeHour(aux,j,staticRestrictions.get(j)); break;
                    case "11": imposeDay(aux,j, staticRestrictions.get(j)); break;
                    default: System.out.println("-Invalid restriction-");
                }
            }
        }
    }

    // PRE  :: pos must be between 0 and N, where N is all sessions's bitSet size (-1), (for us, 60*numClassrooms-1).
    // POST :: All elements in sessions from pointer to the end will have their bitSet positiZon 'pos' set to 'b'
    private void updateRestSession( int base, int pointer, int pos ){
        Session basesessions = sessions.get(base);
        Vector<Restriction> restric = getDynamicRestrictionssessions(basesessions);
        for (int i = pointer; i < sessions.size(); i++) {
            Session currentS = sessions.get(i);
            currentS.setBits(pos,pos+basesessions.getGS().getSessionLength(),false);
            updateSameSubjectGroup(basesessions,currentS,pos);
            for (int j = 0; j < restric.size(); j++) {
                switch (restric.get(j).getParameter(0)) {
                    case "16":
                    case "15" : SameLevel(basesessions,currentS,restric.get(j),pos); break; // here example case per level, will have to define the functions.
                    case "7" : SSTBeforeAfter(basesessions,currentS,restric.get(j),pos); break;
                    case "9": SSTNotSameDay(basesessions,currentS,restric.get(j),pos); break;
                    case "12":SSTsameHour(currentS,restric.get(j),pos, j, restric); break;
                }
            }
        }
    }

    private void reverseUpdateRestSession( int base, int pointer, int pos) {
        Session basesessions = sessions.get(base);
        Vector<Restriction> restric = getDynamicRestrictionssessions(basesessions);
        for (int i = pointer; i >= 0; i--) {
            Session currentS = sessions.get(i);
            currentS.setBits(pos,pos+basesessions.getGS().getSessionLength(),false);
            updateSameSubjectGroup(basesessions,currentS,pos);
            for (int j = 0; j < restric.size(); j++) {
                switch (restric.get(j).getParameter(0)) {
                    case "16":
                    case "15" : SameLevel(basesessions,currentS,restric.get(j),pos); break; // here example case per level, will have to define the functions.
                    case "7" : SSTBeforeAfter(basesessions,currentS,restric.get(j),pos); break;
                    case "9": SSTNotSameDay(basesessions,currentS,restric.get(j),pos); break;
                    case "12": SSTsameHour(currentS,restric.get(j),pos, j, restric); break;
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // MANDATORY RESTRICTION
    ///////////////////////////////////////////////////////////////////////////////////////////

    private void updateSameSubjectGroup(Session base, Session aux, int pos) {
        if (sameSubjectGroup(base.getGS(),aux.getGS())) {
            for(int i = 0; i < CR.size(); i++) {
                aux.setBits(60*i+(pos%60),60*i+(pos%60)+base.getGS().getSessionLength()+1,false);
                if (base.getGS().getSessionType().equals(aux.getGS().getSessionType())) {
                    int day = (pos%60)/12;
                    aux.setBits(60*i+day*12,60*i+(day+1)*12,false);
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL FUNCTIONS RESTRICTION
    ///////////////////////////////////////////////////////////////////////////////////////////

    private static Boolean sameSubject(GroupSubject a, GroupSubject b) {
        if (a.getDegree().equals(b.getDegree()) &&
            a.getAcademicPlan().equals(b.getAcademicPlan()) &&
            a.getSubject().equals(b.getSubject())) return true;
        return false;
    }

    private static Boolean sameSubjectGroup(GroupSubject a, GroupSubject b) {
        if (sameSubject(a,b) &&	a.getGroup().equals(b.getGroup())) return true;
        return false;
    }

    private static Boolean rightSubject(GroupSubject a, Restriction r) {
        if (rightAP(a,r) && a.getSubject().equals(r.getParameter(4))) return true;
        return false;
    }

    private static Boolean rightSST(GroupSubject a, Restriction r) {
        if (rightSubject(a,r) && a.getSessionType().equals(r.getParameter(5))) return true;
        return false;
    }

    private static Boolean rightAP(GroupSubject a, Restriction r) {
        if (a.getDegree().equals(r.getParameter(2)) &&
            a.getAcademicPlan().equals(r.getParameter(3))) return true;
        return false;
    }

    private static Boolean rightSubjectList(GroupSubject a, Restriction r, int index) {
        if (rightAP(a,r) && r.isInList(a.getSubject(),index)) return true;
        return false;
    }

    private static int getDay(String s) {
        switch(s){
            case "Monday": return 0;
            case "Tuesday": return 1;
            case "Wednesday": return 2;
            case "Thursday": return 3;
            case "Friday": return 4;
            default: return -1;
        }
    }

    private static int getPos(String d, String h) {
        int day = getDay(d);
        if (day != -1) {
            int pos = Integer.parseInt(h) - 8;
            if(pos > -1 && pos < 13) return pos+day*12;
        }
        return -1; // ERROR
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // STATIC FUNCTIONS RESTRICTION
    ///////////////////////////////////////////////////////////////////////////////////////////

    private void blockTimeFrame(Session a, Restriction r){
        if (rightSubjectList(a.getGS(),r,7)) {
                int from = getPos(r.getParameter(4),r.getParameter(5));
                int to = getPos(r.getParameter(4),r.getParameter(6));
                if (from != -1 && to != -1) {
                for(int i = 0; i < CR.size(); i++){
                    a.setBits(60*i+from, 60*i+to, false);
                }
            }
        }
    }

    private void GSSTypeNeedsEquipment(Session a, Restriction r) {
        if (rightSST(a.getGS(),r)) {
            for (int i = 0; i < CR.size(); i++) {
                if (!CR.get(i).hasEquipment(r.getParameter(6))) a.setBits(i*60,(i+1)*60,false);
            }
        }
    }

    private void subjectMorAfter(Session a, Restriction r) {
        if (rightSubjectList(a.getGS(),r,5)) {
            if (r.getParameter(4).equals("Morning")) {
                for (int i = 0; i < 5*CR.size(); i++) {
                    a.setBits(12*i+6,12*(i+1),false);
                }
            }
            else if (r.getParameter(4).equals("Afternoon")) {
                for (int i = 0; i < 5*CR.size(); i++) {
                    a.setBits(12*i,12*i+6,false);
                }
            }
            // In case Balanced: Group (name/10)%2 == 1 means groups 10,30,50... Morning and groups 20,40,60... Afternoon
            else if (r.getParameter(4).equals("Balanced")) {
                int x = Integer.parseInt(a.getGS().getGroup());
                if ((x/10)%2 == 1) {  // Means Morning
                    for (int i = 0; i < 5*CR.size(); i++) {
                        a.setBits(12*i+6,12*(i+1),false);
                    }
                }
                else { // Means Afternoon
                    for (int i = 0; i < 5*CR.size(); i++) {
                        a.setBits(12*i,12*i+6,false);
                    }
                }
            }
        }
    }

    private void needsAccesibility(Session a, Restriction r) {
        if (rightSubjectList(a.getGS(),r,4) && a.getGS().getGroup().equals("10")) {
            for (int i = 0; i < CR.size(); i++) {
                if (CR.get(i).notAccessible()) {
                    a.setBits(i*60,(i+1)*60,false);
                }
            }
        }
    }

    private void imposeClOrBu(Session a, Restriction r) {
        if(rightSST(a.getGS(),r)) {
            if (r.getParameter(6).equals("Bu")) {
                for (int i = 0; i < CR.size(); i++) {
                    if (!CR.get(i).getBuilding().getName().equals(r.getParameter(7))) a.setBits(i*60,(i+1)*60,false);
                }
            }
            else if (r.getParameter(6).equals("Cl")) {
                for (int i = 0; i < CR.size(); i++) {
                    if (!CR.get(i).getBuilding().getName().equals(r.getParameter(7)) || !CR.get(i).getName().equals(r.getParameter(8))) a.setBits(i*60,(i+1)*60,false);
                }
            }
        }
    }

    private void subjectNotDayX(Session a, Restriction r) {
        if (rightSubject(a.getGS(),r)) {
            int day = getDay(r.getParameter(5));
            if (day != -1) {
                for (int i = 0; i < CR.size(); i++) {
                    a.setBits(i*60+day*12,i*60+day*12+12,false);
                }
            }
        }
    }

    private int numImposedSessionGroup(int index, String restriction, GroupSubject a) {
        int count = 1;
        for (int i = 0; i < index; i++) {
            Restriction r = staticRestrictions.get(i);
            if (r.getParameter(0).equals(restriction) && rightSST(a,r) && a.getGroup().equals(r.getParameter(6))) count++;
        }
        return count;
    }

    private void imposeHour(Session a, int index, Restriction r) {
        if (rightSST(a.getGS(),r) && a.getGS().getGroup().equals(r.getParameter(6))) {
            if (numImposedSessionGroup(index,r.getParameter(0),a.getGS()) == a.getID()) {
                int pos = getPos(r.getParameter(7),r.getParameter(8));
                for (int i = 0; i < CR.size(); i++) {
                    if (CR.get(i).getBuilding().getName().equals(r.getParameter(9)) && CR.get(i).getName().equals(r.getParameter(10))) {
                        a.setBits(i*60,i*60+pos,false);
                        a.setBits(i*60+pos+ a.getGS().getSessionLength(), (i+1)*60, false);
                    }
                    else a.setBits(i*60,(i+1)*60,false);
                }
            }
        }
    }

    private int numImposedSessionDay(int index, String restriction, GroupSubject a) {
        int count = 1;
        for (int i = 0; i < index; i++) {
            Restriction r = staticRestrictions.get(i);
            if (r.getParameter(0).equals(restriction) && rightSST(a,r)) count++;
        }
        return count;
    }

    private void imposeDay(Session a, int index, Restriction r) {
        if (rightSST(a.getGS(),r) && numImposedSessionDay(index,r.getParameter(0),a.getGS()) == a.getID()) {
            int day = getDay(r.getParameter(6));
            if (day != -1) {
                for (int i = 0; i < CR.size(); i++) {
                    a.setBits(i*60,i*60+day*12,false);
                    a.setBits(i*60+(day+1)*12, (i+1)*60, false);
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // DYNAMIC FUNCTIONS RESTRICTION
    ///////////////////////////////////////////////////////////////////////////////////////////

    private void SameLevel(Session base, Session currentS, Restriction r, int pos) {
        if (rightSubjectList(currentS.getGS(),r, 4) && base.getGS().getGroup().equals(currentS.getGS().getGroup())) {
            for(int i = 0; i < CR.size(); i++) {
                currentS.setBits(60*i+(pos%60),60*i+(pos%60)+base.getGS().getSessionLength()+1,false);
            }
        }
    }

    private int isOneOfTheSST(GroupSubject actual, Restriction r) {
        if (rightSubject(actual,r)) {
            if (actual.getSessionType().equals(r.getParameter(5))) return 1;
            if (actual.getSessionType().equals(r.getParameter(6))) return 2;
        }
        return -1;
    }

    private void SSTBeforeAfter(Session base, Session currentS, Restriction r, int pos) {
        int x = isOneOfTheSST(currentS.getGS(),r);
        if (x == -1) return;
        // Before
        else if (x == 1) {
            for (int i = 0; i < CR.size(); i++) {
                currentS.setBits(60*i+(pos%60),60*(i+1),false);
            }
        }
        // After
        else if (x == 2) {
            for (int i = 0; i < CR.size(); i++) {
                currentS.setBits(60*i,60*i+(pos%60),false);
            }
        }
    }

    private void SSTNotSameDay(Session base, Session currentS, Restriction r, int pos) {
        if (isOneOfTheSST(currentS.getGS(),r) != -1) {
            int day = (pos%60)/12;
            for (int i = 0; i < CR.size(); i++) { // Set to false day of Pos.
                currentS.setBits(60*i+day*12,60*i+(day+1)*12,false);
            }
        }
    }

    private Boolean isTheSST(GroupSubject actual, Restriction r) {
        if (rightSubject(actual,r) && actual.getSessionType().equals(r.getParameter(5))) return true;
        return false;
    }

    private int numImposedSession(int index, String restriction, GroupSubject a, Vector<Restriction> restric) {
        int count = 1;
        for (int i = 0; i < index; i++) {
            Restriction r = restric.get(i);
            if (r.getParameter(0).equals(restriction) && rightSST(a,r)) count++;
        }
        return count;
    }

    private void SSTsameHour(Session currentS, Restriction r, int pos, int j, Vector<Restriction> restric) {
        if (rightSubject(currentS.getGS(),r) && r.getParameter(5).equals(currentS.getGS().getSessionType()) && numImposedSession(j,r.getParameter(0),currentS.getGS(),restric) == currentS.getID()) {
            for (int i = 0; i < CR.size(); i++) {
                currentS.setBits(i*60,i*60+pos%60,false);
                currentS.setBits(i*60+pos%60+currentS.getGS().getSessionLength(),(i+1)*60,false);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // GENERATE SCHEDULE & PRINT FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////////////////////

    // PRE  :: 0 <= i < 60
    // POST :: Returns the String with the Day [M,T,W,T,F] and Hour Interval [8-19h] obtainable from i.
    public String getTimeFrame( int i ){
        String output = new String("");

        switch( i/12 ){
            case 0: output += "Monday    "; break;
            case 1: output += "Tuesday   "; break;
            case 2: output += "Wednesday "; break;
            case 3: output += "Thursday  "; break;
            default: output += "Friday    ";
        }
        if( i % 12 + 8 < 10) output += " ";
        output += Integer.toString( i % 12 + 8 ) + "-";
        if( i % 12 + 9 < 10) output += " ";
        return output + Integer.toString( i % 12 + 9) + ":\t";
    }

    // Aux to Merge sort by complexity of the Session
    public void merge(Vector<Session> R, Vector<Session> A, Vector<Session> B){
        int sizeA = A.size();
        int sizeB = B.size();
        int i = 0;
        int j = 0;
        R.clear();
        while( i < sizeA && j < sizeB ){
            if( A.get(i).getComplexity() >= B.get(j).getComplexity() ) {
                R.add(i+j, A.get(i));
                i++;
            }
            else {
                R.add(i+j, B.get(j));
                j++;
            }
        }
        while( i < sizeA ) { R.add(i+j, A.get(i)); i++; }
        while( j < sizeB ) { R.add(i+j, B.get(j)); j++; }
    }

    // Merge sort by complexity of the Session
    public void mergeSort(Vector<Session> A){
        int sizeA = A.size();
        int m = sizeA/2;
        if( m < 1 ) return;
        Vector<Session> L = new Vector<Session>(m);
        Vector<Session> R = new Vector<Session>(sizeA-m);
        for( int i = 0 ; i < m; i++ ){
            L.add(A.get(i));
            R.add(A.get(i+m));
        }
        if( sizeA > 2*m) R.add(A.get(sizeA-1));
        mergeSort(L);
        mergeSort(R);
        merge(A,L,R);
    }

    // Forward checking algorithm to generate schedule.
    public boolean generateSchedule() {
        this.getAllClassrooms();
        this.generateSession();
        this.readRestrictions();
        applyStaticRestrictions(0);
        computeComplexity();
        mergeSort(sessions);
        
        DomainControl dc = DomainControl.getInstance();

        int i = 0;
        int limit = 0;
        Session currentS = new Session();
        while(i < sessions.size() && i != -1 && limit <400000) {
            limit++;
            int pos = -1;
            currentS = sessions.get(i);

            for (pos = currentS.getNextPos(pos); pos > -1 && currentS.getPos() == -1; pos = currentS.getNextPos(pos)) {
                
                String busySignal = "Computing schedule... (" +i+ "/" +sessions.size()+ ") - Classroom: " + CR.get((pos)/60).getName() +", on "+ getTimeFrame(pos) +"   Session: "+ currentS.getInfo();
                dc.signalTimeSchedule(busySignal);
                
                if (pos > -1 && stillSAT(i, pos)) {
                    updateRestSession(i,i+1,pos);
                    currentS.assignPosition(pos);
                    for (int l = 0; l < currentS.getGS().getSessionLength(); l++) {
                        CR.get((pos+l)/60).addTimeSlot((pos+l)%60, currentS);
                    }
                }
            }
            if (currentS.getPos() == -1) {
                i--;
                if (i != -1) {
                    currentS = sessions.get(i);
                    pos = currentS.getPos();
                    for (int l = 0; l < currentS.getGS().getSessionLength(); l++) {
                        CR.get((pos+l)/60).removeTimeSlot((pos+l)%60);
                    }
                    currentS.clearPos();

                    applyStaticRestrictions(i+1); // apply from position i+1
                    for (int j = 0; j < i; j++) {
                        updateRestSession(j,i+1,sessions.get(j).getPos());
                    }
                }
            }
            else i++;
        }

        if (limit == 400000) {
            i = -1;
        }

        if (i > 0) {
            for (int j = sessions.size()-1; j >= 0; j--) {
                reverseUpdateRestSession(j,j-1,sessions.get(j).getPos());
            }
        }
        return i >= 0;
    }


    private void cout(String s){
        System.out.println(s);
    }
}

