package schedule.domain;

import schedule.persistance.PersistanceControl;
import schedule.gui.MainFrame;
import schedule.gui.PresentationControl;

import java.lang.*;
import java.util.*;

public class DomainControl {
    private static DomainControl instance = null;
    private static EducationalInstitution ei;

    private static Degree d;
    private static String dn;
    private static AcademicPlan ap;
    private static String apn;
    private static Subject s;
    private static String sn;
    private static String ssn;

    private static Equipment e;
    private static String en;
    private static Building b;
    private static String bn;
    private static Classroom c;
    private static String cn;

    private static Restriction r;
    private static String rn;

    /*
    ************* CONSTRUCTOR ***************
    */

    private DomainControl() {
        ei = new EducationalInstitution();

        d = null;
        dn = null;
        ap = null;
        apn = null;
        s = null;
        sn = null;
        ssn = null;

        e = null;
        en = null;
        b = null;
        bn = null;
        c = null;
        cn = null;

        r = null;
        rn = null;
    }

    /*
    ************* GET INSTANCE ***************
    */

    public static DomainControl getInstance() {
        if (instance == null) {
            instance = new DomainControl();
        }
        return instance;
    }

    /*
     ***** RESTRICTION ******
     */

    public boolean editRestriction (String oldAlias, String newAlias, String params) {
        if ((!ei.getRestrictionsKeys().contains(oldAlias)) || ((!oldAlias.equals(newAlias)) && (ei.getRestrictionsKeys().contains(newAlias)))) return false;
        else {
            Vector<String> l = divideParams(params);
            if (!ei.rightRestriction(l)) {
                return false;
            } else  {
                ei.removeRestriction(oldAlias);
                ei.addRestriction(newAlias, l);
            }
        }
        return true;
    }

    private Vector<String> divideParams(String params) {
        Vector<String> l = new Vector<String>();
        String str = "";
        for (char c : params.toCharArray()) {
            if (c == ',') {
                l.add(str);
                str = "";
            } else {
                str += c;
            }
        }
        l.add(str);
        return l;
    }

    public boolean newRestriction(String alias, String params) {
        if (ei.getRestrictionsKeys().contains(alias)) return false;
        else {
            Vector<String> l = divideParams(params);
            if (!ei.rightRestriction(l)) return false;
            else ei.addRestriction(alias, l);
        }
        return true;
    }

    public boolean removeRestriction(String alias) {
        ei.removeRestriction(alias);
        return true;
    }

    public Vector<String> getLevelParams(String alias) {
        Vector<String> aux = (Vector<String>)ei.getRestriction(alias).getParameters().clone();
        Vector<String> v = new Vector<>();
        for( int i = 4; i < aux.size(); i++ ) v.add(aux.get(i));
        return v;
    }

    public Vector<String> getRestrictionParams(String alias) {
        Vector<String> v = (Vector<String>)ei.getRestriction(alias).getParameters().clone();
        return v;
    }

    public int getRestrictionType(String alias) {
        return Integer.parseInt(ei.getRestriction(alias).getParameters().get(0));
    }

    public void activateRestriction(String alias, boolean bool) {
        ei.getRestriction(alias).onOffRestriction(bool);
    }

    ////////////// LEVEL ///////////////////////

    public boolean editLevel(String oldAlias, String newAlias, Vector<String> l) {
        if ((!ei.getRestrictionsKeys().contains(oldAlias)) || ((!oldAlias.equals(newAlias)) && (ei.getRestrictionsKeys().contains(newAlias)))) return false;
        else {
            if (!ei.rightRestriction(l)) {
                return false;
            }
            else {
                ei.removeRestriction(oldAlias);
                ei.addRestriction(newAlias, l);
                return true;
            }
        }
    }

    private Vector<String> divideParamsLevel(String params) {
        Vector<String> l = new Vector<String>();
        String str = "";
        l.add("15");
        l.add("1");
        l.add(d.getName());
        l.add(ap.getName());
        for (char c : params.toCharArray()) {
            if (c == ',') {
                l.add(str);
                str = "";
            } else {
                str += c;
            }
        }
        l.add(str);
        return l;
    }

    public boolean newLevel(String alias, Vector<String> l) {
        if (ei.getRestrictionsKeys().contains(alias)) return false;
        else {
            if (!ei.rightRestriction(l)) return false;
            else ei.addRestriction(alias, l);
            return true;
        }
    }

    public boolean removeLevel(String alias) {
        ei.removeRestriction(alias);
        return true;
    }

    public ArrayList<String> getLevelSubjects(String alias) {
        ArrayList<String> l = new ArrayList<String>();
        Vector<String> v = ei.getRestriction(alias).getParameters();
        for (int i = 4; i < v.size(); i++) {
            l.add(v.get(i));
        }
        return l;
    }
    public ArrayList<String> getLevels() {
        ArrayList<String> l = new ArrayList<String>();
        for (Map.Entry<String,Restriction> entry: ei.getAllRestrictions().entrySet()) {
            if (entry.getValue().getParameter(0).equals("15")) {
                if (entry.getValue().getParameter(2).equals(d.getName())) {
                    if (entry.getValue().getParameter(3).equals(ap.getName())) l.add(entry.getKey());
                }
            }
        }
        return l;
    }

    public ArrayList<String> availableSubjectsForLevels() {
        ArrayList<String> l1 = this.getLevels();
        Vector<String> v = ap.getNameSubjects();
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < v.size(); i++) {
            boolean found = false;
            for (int j = 0; j < l1.size() && !found; j++) {
                found = ei.getRestriction(l1.get(j)).isInList(v.get(i), 4);
            }
            if (!found) result.add(v.get(i));
        }
        return result;
    }

    public ArrayList<String> getCorreq() {
        ArrayList<String> l = new ArrayList<String>();
        for (Map.Entry<String,Restriction> entry: ei.getAllRestrictions().entrySet()) {
            if (entry.getValue().getParameter(0).equals("16")) {
                if (entry.getValue().getParameter(2).equals(d.getName())) {
                    if (entry.getValue().getParameter(3).equals(ap.getName())) {
                        if (entry.getValue().getParameter(4).equals(s.getName())
                                || entry.getValue().getParameter(5).equals(s.getName())) l.add(entry.getKey());
                    }
                }
            }
        }
        return l;
    }

    public ArrayList<String> availableSubjectsForCorreq() {
        ArrayList<String> l = new ArrayList<String>();
        for (String auxs : ap.getSubjectNames()) {
            if (! auxs.equals(sn)) l.add(auxs);
        }
        return l;
    }

    //////////////// CORREQ //////////////////

    public boolean editCorreq(String oldAlias, String newAlias, Vector<String> l) {
        if ((!ei.getRestrictionsKeys().contains(oldAlias)) || ((!oldAlias.equals(newAlias)) && (ei.getRestrictionsKeys().contains(newAlias)))) return false;
        else {
            if (!ei.rightRestriction(l)) {
                return false;
            }
            else {
                ei.removeRestriction(oldAlias);
                ei.addRestriction(newAlias, l);
                return true;
            }
        }
    }

    private Vector<String> divideParamsCorreq(String params) {
        Vector<String> l = new Vector<String>();
        String str = "";
        l.add("16");
        l.add("1");
        l.add(d.getName());
        l.add(ap.getName());
        for (char c : params.toCharArray()) {
            if (c == ',') {
                l.add(str);
                str = "";
            } else {
                str += c;
            }
        }
        l.add(str);
        return l;
    }

    public boolean newCorreq(String alias, Vector<String> l) {
        if (ei.getRestrictionsKeys().contains(alias)) return false;
        ei.addRestriction(alias, l);
        return true;
    }

    public boolean removeCorreq(String alias) {
        ei.removeRestriction(alias);
        return true;
    }

    /*
    ************* CLASSROOM ********************
    */

    public boolean editClassroom(String name) throws CloneNotSupportedException {
        Classroom classroom = b.getOneClassroom(name);
        if (classroom == null) return false;
        else {
            c = classroom.clone();
            cn = c.getName();
            return true;
        }
    }

    public boolean newClassroom() {
        c = new Classroom();
        cn = null;
        return true;
    }

    public boolean removeClassroom(String name) {
        if (b.getSetKeysClassrooms().contains(name)) {
            b.elimClassroom(name);
            return true;
        } else return false;
    }

    public boolean saveClassroom() {
        if ((cn != null) && (b.getSetKeysClassrooms().contains(cn))) {
            b.elimClassroom(cn);
        }
        b.addClassroom(c);
        c = null;
        cn = null;
        return true;
    }

    public boolean cancelClassroom() {
        c = null;
        cn = null;
        return true;
    }

    public boolean setClassroomName(String newName) {
        if (((cn == null) && (b.getSetKeysClassrooms().contains(newName))) || ((cn != null) && (!cn.equals(newName)) && (b.getSetKeysClassrooms().contains(newName))))
        {
            return false;
        }
        else
        {
            c.setName(newName);
            return true;
        }
    }

    public boolean setClassroomSize(int newSize) {
        c.setSize(newSize);
        return true;
    }

    public boolean setClassroomFloor(int newFloor) {
        c.setFloor(newFloor);
        return true;
    }

    public String getClassroomName() {
        return c.getName();
    }

    public int getClassroomSize() {
        return c.getSize();
    }

    public int getClassroomFloor() {
        return c.getFloor();
    }

    public boolean addClassroomEquipment(String name) {
        Equipment equipment = ei.getAllEquipments().get(name);
        if (equipment == null) return false;
        c.addEquipment(equipment);
        return true;
    }

    public boolean removeClassroomEquipment(String name) {
        c.elimEquipment(name);
        return true;
    }

    /*
    ************* BUILDING *********************
    */

    public boolean editBuilding(String name) throws CloneNotSupportedException {
        Building auxb = ei.getAllBuildings().get(name);
        if (auxb != null) {
            b = auxb.clone();
            bn = auxb.getName();
        }
        return (auxb != null);
    }

    public boolean newBuilding() {
        System.out.println("Pasa por newBuilding");
        b = new Building();
        bn = null;
        return true;
    }

    public boolean removeBuilding(String name) {
        if (ei.getAllBuildings().containsKey(name)) {
            ei.getAllBuildings().remove(name);
            return true;
        } else return false;
    }

    public boolean saveBuilding() {
        if ((bn != null)) {
            ei.getAllBuildings().remove(bn);
        } System.out.println(b.getName());
        ei.addBuilding(b);
        b = null;
        bn = null;
        return true;
    }

    public boolean cancelBuilding() {
        b = null;
        bn = null;
        return true;
    }

    public boolean setBuildingName(String newName) {
        if (((bn == null) && (ei.getAllBuildings().containsKey(newName))) || ((bn != null) && (!bn.equals(newName)) && (ei.getAllBuildings().containsKey(newName))))
        {
            return false;
        }
        else
        {
            b.setName(newName);
            return true;
        }
    }

    public String getBuildingName() {
        return b.getName();
    }

    public boolean addBuildingEquipment(String name) {
        Equipment equipment = ei.getAllEquipments().get(name);
        if (equipment == null) return false;
        else {
            b.addEquipment(equipment);
            return true;
        }
    }

    public boolean removeBuildingEquipment(String name) {
        b.elimEquipment(name);
        return true;
    }

    /*
    ************* EQUIPMENT ********************
    */

    public boolean editEquipment(String name) throws CloneNotSupportedException {
        Equipment auxe = ei.getAllEquipments().get(name);
        if (auxe != null) {
            e = auxe.clone();
            en = auxe.getName();
        }
        return (auxe != null);
    }

    public boolean newEquipment() {
        e = new Equipment();
        en = null;
        return true;
    }

    public boolean removeEquipment(String name) {
        if (ei.getAllEquipments().containsKey(name)) {
            ei.getAllEquipments().remove(name);
            return true;
        } else return false;
    }

    public boolean saveEquipment() {
        if ((en != null)) {
            ei.getAllEquipments().remove(en);
        }
        ei.addEquipment(e);
        e = null;
        en = null;
        return true;
    }

    public boolean cancelEquipment() {
        e = null;
        en = null;
        return true;
    }

    public boolean setEquipmentName(String newName) {
        if (((en == null) && (ei.getAllEquipments().containsKey(newName))) || ((en != null) && (!en.equals(newName)) &&
                (ei.getAllEquipments().containsKey(newName))))
        {
            return false;
        }
        else
        {
            e.setName(newName);
            return true;
        }
    }

    public boolean setEquipmentDescription(String newDescription) {
        e.setDescription(newDescription);
        return true;
    }

    public String getEquipmentName() {
        return e.getName();
    }

    public String getEquipmentDescription() {
        return e.getDescription();
    }


    /*
    ************* SUBJECT TYPE *****************
    */

    public boolean editType(String oldType, String newType, int classroomSize, int sessionLength, int sessionsWeek) {
        s.removeSubgroups(oldType);
        s.addSubgroups(newType, classroomSize, sessionLength, sessionsWeek);
        return true;
    }

    public boolean newType(String type, int classroomSize, int sessionLength, int sessionsWeek) {
        if (s.getGroup(0).hasType(type)) return false;
        else {
            s.addSubgroups(type, classroomSize, sessionLength, sessionsWeek);
            return true;
        }
    }

    public void setOldType(String s){
        ssn = s;
    }

    public String getOldType(){
        return ssn;
    }

    public boolean removeType(String type) {
        s.removeSubgroups(type);
        return true;
    }

    public String getLength(String type) {
        return Integer.toString(s.getGroup(0).getSubgroupMap().get(type).get(0).getSessionLength());
    }

    public String getNumSessions(String type) {
        return Integer.toString(s.getGroup(0).getSubgroupMap().get(type).get(0).getSessionsWeek());
    }

    public String getTypeSize(String type) {
        return Integer.toString(s.getGroup(0).getSubgroupMap().get(type).get(0).getClassroomSize());
    }

    /*
    ************* SUBJECT **********************
    */

    public boolean editSubject(String name) {
        Subject auxs = ap.getSubject(name);
        if (auxs != null) {
            s = new Subject(auxs);
            sn = s.getName();
        }
        return (auxs != null);
    }

    public boolean newSubject() {
        s = new Subject();
        sn = null;
        return true;
    }

    public boolean removeSubject(String name) {
        if (ap.hasSubject(name)) {
            ap.removeSubject(name);
            return true;
        } else return false;
    }

    public boolean saveSubject() {
        if ((sn != null) && (ap.hasSubject(sn))) {
            ap.getSubjects().remove(sn);
        }
        ap.addSubject(s);
        s = null;
        sn = null;
        return true;
    }

    public boolean cancelSubject() {
        s = null;
        sn = null;
        return true;
    }

    public boolean setSubjectName(String newName) {
        // If it is an edit AND the name has changed AND the new name is already taken
        // It is a new object AND the name is already taken
        if (((sn != null) && (!sn.equals(newName)) &&
                (ap.hasSubject(newName))) || ((sn == null) && (ap.hasSubject(newName))))
        {
            return false;
        }
        else
        {
            s.setName(newName);
            return true;
        }
    }

    public boolean setSubjectSlots(int newSlots) {
        if (newSlots > 0) s.setSlots(newSlots);
        return true;
    }

    public String getSubjectSlots() {
        return String.valueOf(s.getSlots());
    }

    public String getSubjectName() {
        return s.getName();
    }

    /*
    ************* ACADEMIC PLAN ****************
    */

    public boolean editAcademicPlan(String name) throws CloneNotSupportedException {
        AcademicPlan auxap = d.getAcademicPlan(name);
        if (auxap != null) {
            ap = auxap.clone();
            apn = auxap.getName();
        }
        return (ap != null);
    }

    public boolean newAcademicPlan() {
        ap = new AcademicPlan();
        apn = null;
        return true;
    }

    public boolean removeAcademicPlan(String name) {
        if (d.getCurriculumMap().containsKey(name)) {
            d.removeAcademicPlan(name);
            return true;
        } else return false;
    }

    public boolean saveAcademicPlan() {
        if ((apn != null)) {
            d.getCurriculumMap().remove(apn);
        }
        d.addAcademicPlan(ap);
        ap = null;
        apn = null;
        return true;
    }

    public boolean cancelAcademicPlan() {
        ap = null;
        apn = null;
        return true;
    }

    public boolean setAcademicPlanName(String newName) {
        // If it is an edit AND the name has changed AND the new name is already taken // It is a new object AND the name is already taken
        if (((apn != null) && (!apn.equals(newName)) && (d.getCurriculumMap().containsKey(newName))) || ((apn == null) && (d.getCurriculumMap().containsKey(newName))))
        {
            return false;
        }
        else
        {
            ap.setName(newName);
            return true;
        }
    }

    public String getAcademicPlanName() {
        return ap.getName();
    }


    /*
    ************* DEGREE ***************
    */

    public boolean editDegree(String name) throws CloneNotSupportedException {
        Degree auxd = ei.getDegree(name);
        if (auxd != null) {
            d = auxd.clone();
            dn = d.getName();
        }
        return (auxd != null);
    }

    public boolean newDegree() {
        d = new Degree();
        dn = null;
        return true;
    }

    public boolean removeDegree(String name) {
        if (ei.getAllDegrees().containsKey(name)) {
            ei.getAllDegrees().remove(name);
            return true;
        } else return false;
    }

    public boolean saveDegree() {
        if ((dn != null)) {
            ei.getAllDegrees().remove(dn);
        }
        ei.addDegree(d);
        d = null;
        dn = null;
        return true;
    }

    public boolean cancelDegree() {
        dn = null;
        d = null;
        return true;
    }

    public void setDegreeType(String type){
        d.setType(type);
    }

    public String getDegreeType(){
        return d.getType();
    }

    public boolean setDegreeName(String newName) {
        // If it is an edit AND the name has changed AND the new name is already taken // It is a new object AND the name is already taken
        if (((dn != null) && (!dn.equals(newName)) && (ei.getAllDegrees().containsKey(newName))) || ((dn == null) && (ei.getAllDegrees().containsKey(newName))))
        {
            return false;
        }
        else
        {
            d.setName(newName);
            return true;
        }
    }

    public String getDegreeName() {
        return d.getName();
    }


    /*
    ************* EDUCATIONAL INSTITUTION LISTS ***************
    */

    public ArrayList<String> getDegrees() {
        ArrayList<String> l = new ArrayList<String>();
        if(ei == null) System.out.println("NULO");
        for (String key : ei.getAllDegrees().keySet()) {
            l.add(key);
        }
        return l;
    }

    public ArrayList<String> getAcademicPlans() {
        ArrayList<String> l = new ArrayList<String>();
        if(d == null) System.out.println("NULO");
        for (String key: d.getCurriculumMap().keySet()) {
            l.add(key);
        }
        return l;
    }

    public ArrayList<String> getSubjects() {
        ArrayList<String> l = new ArrayList<String>();
        for (String key: ap.getSubjects().keySet() ) {
            l.add(key);
        }
        return l;
    }

    public ArrayList<String> getSubjectTypes() {
        ArrayList<String> l = new ArrayList<String>();
        for (String type: s.getGroup(0).getTypes()) {
            l.add(type);
        }
        return l;
    }

    public ArrayList<String> getBuildings() {
        ArrayList<String> l = new ArrayList<String>();
        for (String key: ei.getAllBuildings().keySet()) {
            l.add(key);
        }
        return l;
    }

    public ArrayList<String> getBuildingEquipments() {
        ArrayList<String> l = new ArrayList<String>();
        for (String key : b.getSetKeysEquipments()) {
            l.add(key);
        }
        return l;
    }

    public ArrayList<String> getClassroomEquipments() {
        ArrayList<String> l = new ArrayList<String>();
        for (String key : c.getSetKeys()) {
            l.add(key);
        }
        return l;
    }

    public ArrayList<String> getClassrooms() {
        ArrayList<String> l = new ArrayList<String>();
        for (String key : b.getSetKeysClassrooms()) {
            l.add(key);
        }
        return l;
    }

    public ArrayList<String> getEquipments() {
        ArrayList<String> l = new ArrayList<String>();
        for (String key: ei.getAllEquipments().keySet()) {
            l.add(key);
        }
        return l;
    }

    // id 2 -> nivel
    // id 11 -> corequisito
    public ArrayList<String> getRestrictions() {
        ArrayList<String> l = new ArrayList<String>();
        for (Map.Entry<String,Restriction> entry : ei.getAllRestrictions().entrySet()) {
            if ((!entry.getValue().getParameter(0).equals("15")) && (! entry.getValue().getParameter(0).equals("16"))) l.add(entry.getKey());
        }
        return l;
    }



    /*
    ********************** PATHS ***********************
    */

    //Building Classroom Degree AcademicPlan Subject EducationalInstitution
    public String getEducationalInstitutionPath() {
        return ei.getName();
    }

    public String getDegreePath() {
        if(d == null) return getEducationalInstitutionPath()+" > Degree";
        return getEducationalInstitutionPath() + " > " + ((d.getName() == null || d.getName().equals("")) ? "" : d.getName());
    }

    public String getAcademicPlanPath() {
        if(ap == null) return getDegreePath()+" > Academic Plan";
        return getDegreePath() + " > " + ((ap.getName() == null || ap.getName().equals("")) ? "" : ap.getName());
    }

    public String getSubjectPath() {
        if(s == null) return getAcademicPlanPath()+" > Subject";
        return getAcademicPlanPath() + " > " + ((s.getName() == null || s.getName().equals("")) ? "" : s.getName());
    }

    public String getBuildingPath() {
        if(b == null) return getEducationalInstitutionPath()+" > Building";
        return getEducationalInstitutionPath() + " > " + ((b.getName() == null || b.getName().equals("")) ? "" : b.getName());
    }

    public String getClassroomPath() {
        if(c == null) return getBuildingPath()+" > Classroom";
        return getBuildingPath() + " > " + ((c.getName() == null || c.getName().equals("")) ? "" : c.getName());
    }

    /*
    ************ SCHEDULE ***********
    */

    public boolean generateSchedule() {
        return ei.generateSchedule();
    }

    public void signalTimeSchedule(String s) {
        PresentationControl presentation = PresentationControl.getInstance();
        presentation.signalTimeSchedule(s);
    }

    public String[][] getClassroomSchedule(String building, String classroom) {
        Classroom classe = ei.getAllBuildings().get(building).getOneClassroom(classroom);
        String[][] l = new String[12][6];

        l[0][0] = " 8:00 - 09:00";
        l[1][0] = " 9:00 - 10:00";
        l[2][0] = "10:00 - 11:00";
        l[3][0] = "11:00 - 12:00";
        l[4][0] = "12:00 - 13:00";
        l[5][0] = "13:00 - 14:00";
        l[6][0] = "14:00 - 15:00";
        l[7][0] = "15:00 - 16:00";
        l[8][0] = "16:00 - 17:00";
        l[9][0] = "17:00 - 18:00";
        l[10][0] = "18:00 - 19:00";
        l[11][0] = "19:00 - 20:00";

        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                if( classe.getSlotInfoString(((i-1)*12) + j) != null ) l[j][i] = classe.getSlotInfoString(((i-1)*12) + j);
                else l[j][i] = " - ";
            }
        }

        return l;
    }

    public String[][] getSubjectSchedule(    String degree,
                                                    String academicPlan,
                                                    String subject,
                                                    String building,
                                                    String classroom) {
        Classroom classe = ei.getAllBuildings().get(building).getOneClassroom(classroom);
        String[][] l = new String[12][6];

        for (int i = 0; i < 12; i++) l[i][0] = " " + (i + 8) + ":00 - " + (i + 9) + ":00";

        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                if (classe.getSlotInfoIsSubject((((i-1)*12) + j), degree, academicPlan, subject))
                    l[j][i] = classe.getSlotInfoString(((i-1)*12) + j);
                else
                    l[j][i] = "-";
            }
        }

        return l;
    }

    public boolean updateSchedule(  String oldBuilding,
                                    String oldClassroom,
                                    int oldPosCol,
                                    int oldPosRow,
                                    String newBuilding,
                                    String newClassroom,
                                    int newPosCol,
                                    int newPosRow) {
        return ei.modifySchedule(oldBuilding, oldClassroom, (oldPosCol * 12) + oldPosRow, newBuilding, newClassroom, (newPosCol * 12) + newPosRow);
    }

    /*
    ********************* EDUCATIONAL INSTITUTION ***************
    */

    public boolean setEducationalInstitutionName(String name) {
        ei.setName(name);
        return true;
    }

    public String getEducationalInstitutionName() {
        return ei.getName();
    }

    /*
    ************* I/O ***************
    */

    public void save(String path) {
        PersistanceControl persistance = PersistanceControl.getInstance();
        persistance.save(path, ei);
    }

    public void load(String path) {
        PersistanceControl persistance = PersistanceControl.getInstance();
        EducationalInstitution aux = persistance.load(path);
        if (aux != null) ei = aux;
    }







}
