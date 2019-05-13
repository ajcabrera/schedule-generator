package schedule.gui;

import schedule.domain.DomainControl;

import java.util.*;
import java.lang.*;

public class PresentationControl {
    private static PresentationControl instance = null;
    private static DomainControl domain;
    private static MainFrame mf;

    private PresentationControl() {
        domain = DomainControl.getInstance();
    }

    public static PresentationControl getInstance() {
        if (instance == null) {
            instance = new PresentationControl();
        }
        return instance;
    }


    public void setMainframe(MainFrame mf){
        this.mf = mf;
    }

    /*
     ***** RESTRICTION ******
     */

    public boolean editRestriction(String oldAlias, String newAlias, String params) {
        if ((oldAlias == null)      || (oldAlias.equals("")) || (newAlias == null)
                || (newAlias.equals("")) || (params == null)    || (params.equals(""))) return false;
        else return domain.editRestriction(oldAlias, newAlias, params);
    }

    public boolean newRestriction(String alias, String params) {
        if ((alias == null) || (alias.equals("")) || (params == null) || (params.equals("")))
            return false;
        else return domain.newRestriction(alias, params);
    }

    public boolean removeRestriction(String alias) {
        if ((alias == null) || (alias.equals(""))) return false;
        else return domain.removeRestriction(alias);
    }

    public Vector<String> getRestrictionParams(String alias) {
        return domain.getRestrictionParams(alias);
    }

    public Vector<String> getLevelParams(String alias) {
        return domain.getLevelParams(alias);
    }

    public int getRestrictionType(String alias) {
        return domain.getRestrictionType(alias);
    }

    public boolean activateRestriction(String alias, boolean bool) {
        if ((alias == null) || (alias.equals(""))) return false;
        else {
            domain.activateRestriction(alias, bool);
            return true;
        }
    }


    ///////////////// LEVEL ////////////////////

    public boolean editLevel(String oldAlias, String newAlias, Vector<String> params) {
        if ((oldAlias == null)      || (oldAlias.equals("")) || (newAlias == null)
                || (newAlias.equals("")) || (params == null)  ) return false;
        else return domain.editLevel(oldAlias, newAlias, params);
    }

    public boolean newLevel(String alias, Vector<String> params) {
        if ((alias == null) || (alias.equals("")) || (params == null) )
            return false;
        else return domain.newLevel(alias, params);
    }

    public boolean removeLevel(String alias) {
        if ((alias == null) || (alias.equals(""))) return false;
        else return domain.removeLevel(alias);
    }

    public Vector<String> getLevelSubjects(String alias) {
        return domain.getLevelParams(alias);
    }

    ///////////////// CORREQ //////////////////

    public boolean editCorreq(String oldAlias, String newAlias, Vector<String> params) {
        if ((oldAlias == null)      || (oldAlias.equals("")) || (newAlias == null)
                || (newAlias.equals("")) || (params == null)) return false;
        else return domain.editCorreq(oldAlias, newAlias, params);
    }

    public boolean newCorreq(String alias, Vector<String> params) {
        if ((alias == null) || (alias.equals("")) || (params == null))
            return false;
        else return domain.newCorreq(alias, params);
    }

    public boolean removeCorreq(String alias) {
        if ((alias == null) || (alias.equals(""))) return false;
        else return domain.removeCorreq(alias);
    }

    public ArrayList<String> getLevels() { return domain.getLevels(); }

    public ArrayList<String> availableSubjectsForLevels() { return domain.availableSubjectsForLevels(); }

    public ArrayList<String> getCorreq() { return domain.getCorreq(); }

    public ArrayList<String> availableSubjectsForCorreq() { return domain.availableSubjectsForCorreq(); }




    /*
    ************* CLASSROOM ********************
    */

    public boolean editClassroom(String name) throws CloneNotSupportedException {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.editClassroom(name);
    }

    public boolean newClassroom() {
        return domain.newClassroom();
    }

    public boolean removeClassroom(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.removeClassroom(name);
    }

    public boolean saveClassroom() {
        return domain.saveClassroom();
    }

    public boolean cancelClassroom() {
        return domain.cancelClassroom();
    }

    public boolean setClassroomName(String newName) {
        if ((newName == null) || (newName.equals(""))) return false;
        else return domain.setClassroomName(newName);
    }


    public boolean setClassroomSize(String newSize) {
        int size = Integer.parseInt(newSize);
        if (size < 1) return false;
        else return domain.setClassroomSize(size);
    }

    public boolean setClassroomFloor(String newFloor) {
        int floor = Integer.parseInt(newFloor);
        if (floor < 1) return false;
        else return domain.setClassroomFloor(floor);
    }

    public String getClassroomName() { // check null!!
        return domain.getClassroomName();
    }

    public String getClassroomSize() { // check null!!
        return Integer.toString(domain.getClassroomSize());
    }

    public String getClassroomFloor() { // check null!!
        return Integer.toString(domain.getClassroomFloor());
    }

    public boolean addClassroomEquipment(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.addClassroomEquipment(name);
    }

    public boolean removeClassroomEquipment(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.removeClassroomEquipment(name);
    }

    /*
    ************* BUILDING *********************
    */

    public boolean editBuilding(String name) throws CloneNotSupportedException {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.editBuilding(name);
    }

    public boolean newBuilding() {
        return domain.newBuilding();
    }

    public boolean removeBuilding(String name) {
        if ((name == null) || (name.equals(""))) { System.out.println("Nombre nulo"); return false; }
        else return domain.removeBuilding(name);
    }

    public boolean saveBuilding() {
        return domain.saveBuilding();
    }

    public boolean cancelBuilding() {
        return domain.cancelBuilding();
    }

    public boolean setBuildingName(String newName) {
        if ((newName == null) || (newName.equals(""))) return false;
        else return domain.setBuildingName(newName);
    }

    public String getBuildingName() {
        return domain.getBuildingName();
    }

    public boolean addBuildingEquipment(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.addBuildingEquipment(name);
    }

    public boolean removeBuildingEquipment(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.removeBuildingEquipment(name);
    }

    public ArrayList<String> getBuildingEquipments() { return domain.getBuildingEquipments(); }

    public ArrayList<String> getClassroomEquipments() { return domain.getClassroomEquipments(); }

    /*
    ************* EQUIPMENT ********************
    */

    public boolean editEquipment(String name) throws CloneNotSupportedException {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.editEquipment(name);
    }

    public boolean newEquipment() {
        return domain.newEquipment();
    }

    public boolean removeEquipment(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.removeEquipment(name);
    }

    public boolean saveEquipment() {
        return domain.saveEquipment();
    }

    public boolean cancelEquipment() {
        return domain.cancelEquipment();
    }

    public boolean setEquipmentName(String newName) {
        if ((newName == null) || (newName.equals(""))) return false;
        else return domain.setEquipmentName(newName);
    }

    public boolean setEquipmentDescription(String newDescription) {
        if ((newDescription == null) || (newDescription.equals(""))) return false;
        else return domain.setEquipmentDescription(newDescription);
    }

    public String getEquipmentName() {
        return domain.getEquipmentName();
    }

    public String getEquipmentDescription() {
        return domain.getEquipmentDescription();
    }

    /*
    ************* SUBJECT TYPE *****************
    */

    public boolean editType(String oldType, String newType, String classroomSize, String sessionLength, String sessionsWeek) {
        int cs = Integer.parseInt(classroomSize);
        int sl = Integer.parseInt(sessionLength);
        int sw = Integer.parseInt(sessionsWeek);
        if ((cs < 0) || (sl < 0) || (sw < 0) || (oldType == null) || (oldType.equals("")) || (newType == null) || (newType.equals(""))) return false;
        else return domain.editType(oldType, newType, cs, sl, sw);
    }

    public boolean editType(String type, int classroomSize, int sessionLength, int sessionsWeek) { // Check if old -> null is ok
        if ((classroomSize < 0) || (sessionLength < 0) || (sessionsWeek < 0) || (type == null)
            || (type.equals(""))) return false;
        else return domain.editType(type, type, classroomSize, sessionLength, sessionsWeek);
    }

    public boolean newType(String type, String classroomSize, String sessionLength, String sessionsWeek) {
        int cs = Integer.parseInt(classroomSize);
        int sl = Integer.parseInt(sessionLength);
        int sw = Integer.parseInt(sessionsWeek);
        if ((cs < 0) || (sl < 0) || (sw < 0) || (type == null) || (type.equals(""))) return false;
        else return domain.newType(type, cs, sl, sw);
    }

    public boolean newType(String type, int classroomSize, int sessionLength, int sessionsWeek) {
        if ((classroomSize < 0) || (sessionLength < 0) || (sessionsWeek < 0) || (type == null) || (type.equals(""))) return false;
        else return domain.newType(type, classroomSize, sessionLength, sessionsWeek);
    }

    public void setOldType(String s){
        domain.setOldType(s);
    }

    public String getOldType(){
        return domain.getOldType();
    }

    public boolean removeType(String type) {
        return domain.removeType(type);
    }


    public String getLength(String type){
        return domain.getLength(type);
    }

    public String getNumSessions(String type){
        return domain.getNumSessions(type);
    }

    public String getTypeSize(String type){
        return domain.getTypeSize(type);
    }


    /*
    ************* SUBJECT **********************
    */

    public boolean editSubject(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.editSubject(name);
    }

    public boolean newSubject() {
        return domain.newSubject();
    }

    public boolean removeSubject(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.removeSubject(name);
    }

    public boolean saveSubject() {
        return domain.saveSubject();
    }

    public boolean cancelSubject() {
        return domain.cancelSubject();
    }

    public boolean setSubjectName(String newName) {
        if ((newName == null) || (newName.equals(""))) return false;
        else return domain.setSubjectName(newName);
    }

    public boolean setSubjectSlots(String newSlots) {
        int n = Integer.parseInt(newSlots);
        if (Integer.parseInt(newSlots) < 0) return false;
        else return domain.setSubjectSlots(n);
    }

    public boolean setSubjectSlots(int newSlots) {
        if (newSlots < 0) return false;
        else return domain.setSubjectSlots(newSlots);
    }

    public String getSubjectSlots() {
        return domain.getSubjectSlots();
    }

    public String getSubjectName() {
        return domain.getSubjectName();
    }

    /*
    ************* ACADEMIC PLAN ****************
    */

    public boolean editAcademicPlan(String name) throws CloneNotSupportedException {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.editAcademicPlan(name);
    }

    public boolean newAcademicPlan() {
        return domain.newAcademicPlan();
    }

    public boolean removeAcademicPlan(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.removeAcademicPlan(name);
    }

    public boolean saveAcademicPlan() {
        return domain.saveAcademicPlan();
    }

    public boolean cancelAcademicPlan() {
        return domain.cancelAcademicPlan();
    }

    public boolean setAcademicPlanName(String newName) {
        if ((newName == null) || (newName.equals(""))) return false;
        else return domain.setAcademicPlanName(newName);
    }

    public String getAcademicPlanName() {
        return domain.getAcademicPlanName();
    }

    /*
    ************* DEGREE ***************
    */

    public boolean editDegree(String name) throws CloneNotSupportedException {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.editDegree(name);
    }

    public boolean newDegree() {
        System.out.println("1");
        return domain.newDegree();
    }

    public boolean removeDegree(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else return domain.removeDegree(name);
    }

    public boolean saveDegree() {
        return domain.saveDegree();
    }

    public boolean cancelDegree() {
        return domain.cancelDegree();
    }

    public boolean setDegreeName(String newName) {
        if ((newName == null) || (newName.equals(""))) return false;
        else return domain.setDegreeName(newName);
    }

    public void setDegreeType(String type){
        domain.setDegreeType(type);
    }

    public String getDegreeType(){
        return domain.getDegreeType();
    }

    public String getDegreeName() {
        return domain.getDegreeName();
    }

    /*
    ************* EDUCATIONAL INSTITUTION LISTS ***************
    */

    public ArrayList<String> getDegrees() {
        return domain.getDegrees();
    }

    public ArrayList<String> getAcademicPlans() {
        return domain.getAcademicPlans();
    }

    public ArrayList<String> getSubjects() {
        return domain.getSubjects();
    }

    public ArrayList<String> getSubjectTypes() {
        return domain.getSubjectTypes();
    }

    public ArrayList<String> getBuildings() {
        return domain.getBuildings();
    }

    public ArrayList<String> getClassrooms() {
        return domain.getClassrooms();
    }

    public ArrayList<String> getEquipments() {
        return domain.getEquipments();
    }

    public ArrayList<String> getRestrictions() {
        return domain.getRestrictions();
    }

    /*
    ************ SCHEDULE ***********
    */

    public boolean generateSchedule() {
        return domain.generateSchedule();
    }

    public void signalTimeSchedule(String str) {
        mf.updateProcessStatus(str);
    }

    public String[][] getClassroomSchedule(String building, String classroom) {
        /*if ((building == null) || (building.equals("")) || (classroom == null) || (classroom.equals(""))) return false;
        else */return domain.getClassroomSchedule(building, classroom);
    }

    public String[][] getSubjectSchedule(    String degree,
                                                    String academicPlan,
                                                    String subject,
                                                    String building,
                                                    String classroom) {
        /*if ((degree == null)        || (degree.equals(""))  || (academicPlan == null)   || (academicPlan.equals(""))
            || (subject == null)    || (subject.equals("")) || (building == null)       || (building.equals(""))
            || (classroom == null)  || (classroom.equals(""))) return false;
        else */return domain.getSubjectSchedule(degree, academicPlan, subject, building, classroom);
    }

    public boolean updateSchedule(  String oldBuilding,
                                    String oldClassroom,
                                    int oldPosCol,
                                    int oldPosRow,
                                    String newBuilding,
                                    String newClassroom,
                                    int newPosCol,
                                    int newPosRow) {
        /*if ((oldBuilding == null)       || (oldBuilding.equals(""))     || (oldClassroom == null)   || (oldClassroom.equals(""))
            || (oldPosCol < 0)          || (oldPosRow < 0)              || (newBuilding == null)    || (newBuilding.equals(""))
            || (newClassroom == null)   || (newClassroom.equals(""))    || (newPosCol < 0)          || (newPosRow < 0)) return false;
        else */return domain.updateSchedule(oldBuilding, oldClassroom, oldPosCol, oldPosRow, newBuilding, newClassroom, newPosCol, newPosRow);
    }

    /*
    ******** EDUCATIONAL INSTITUTION *********
    */

    public boolean setEducationalInstitutionName(String name) {
        if ((name == null) || (name.equals(""))) return false;
        else domain.setEducationalInstitutionName(name);
        return true;
    }

    public String getEducationalInstitutionName() {
        return domain.getEducationalInstitutionName();
    }



    /*
        PATHS
     */

    public String getEducationalInstitutionPath() {
        return domain.getEducationalInstitutionPath();
    }

    public String getDegreePath() {
        return domain.getDegreePath();
    }

    public String getAcademicPlanPath() {
        return domain.getAcademicPlanPath();
    }

    public String getSubjectPath() {
        return domain.getSubjectPath();
    }

    public String getBuildingPath() {
        return domain.getBuildingPath();
    }

    public String getClassroomPath() {
        return domain.getClassroomPath();
    }



    /*
     ******************* I/O *******************
     */

    public void save(String path) {
        domain.save(path);
    }

    public void load(String path) {
        domain.load(path);
    }

}
