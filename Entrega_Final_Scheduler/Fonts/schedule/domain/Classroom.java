package schedule.domain;

import java.util.*;

public class Classroom implements Cloneable {
    private String name;
    private int size;
    private int floor;
    private Building building;
    private HashMap<String,Equipment> equipments;
    private Vector<Session> timeSlots;


    // ###----- CONSTRUCTORS -----###

    public Classroom() {
        this.name = "";
        this.building = new Building ();
        this.equipments = new HashMap<String,Equipment> ();
        this.timeSlots = new Vector<Session> ();
        this.timeSlots.setSize(60);
    }

    public Classroom(String name, int size, int floor) {
        this.name = name;
        this.size = size;
        this.floor = floor;
        this.building = new Building ();
        this.equipments = new HashMap<String,Equipment> ();
        this.timeSlots = new Vector<Session>();
        this.timeSlots.setSize(60);
    }

    @Override
    public Classroom clone() throws CloneNotSupportedException {
        Classroom c = (Classroom) super.clone();
            c.equipments = (HashMap<String, Equipment>) c.equipments.clone();
        return c;
    }

    // ###----- MODIFIERS / SETTERS -----###

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setBuilding(schedule.domain.Building building) {
        this.building = building;
    }

    public void setAllEquipments(HashMap<String, schedule.domain.Equipment> equipments) {
        this.equipments = equipments;
    }

    public void addEquipment(Equipment equipment) {
        this.equipments.put(equipment.getName(),equipment);
    }

    public void setTimeSlots(Vector<Session> timeSlots) {
        this.timeSlots = timeSlots;
    }

    // PRE  :: 0 <= index < 60
    // POST :: newGS is assigned to position 'index'
    public void addTimeSlot(int index, Session info) {
        timeSlots.set(index,info);
    }

    public void removeTimeSlot(int index) {
        timeSlots.set(index,null);
    }

    public void resetTimeSlots() {
        timeSlots = new Vector<Session> ();
        timeSlots.setSize(60);
    }

    public void elimEquipment(String e) {
        equipments.remove(e);
    }

    // ###----- CONSULTS / GETTERS -----###

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getFloor() {
        return floor;
    }

    public Building getBuilding() {
        return building;
    }

    public int getSizeEquipments() {
        return equipments.size();
    }

    public HashMap<String,Equipment> getAllEquipments() {
        return equipments;
    }

    public Set<String> getSetKeys() {
        return equipments.keySet();
    }

    public Equipment getOneEquipment(String s) {
        return equipments.get(s);
    }

    public Session getSlotInfo(int index) {
        return timeSlots.get(index);
    }

    public String getSlotInfoString(int index) {
        if( timeSlots.get(index) != null) return timeSlots.get(index).getInfo();
        else return " - ";
    }

    public Boolean getSlotInfoIsSubject(int index, String degree, String ap, String subject) {
        if (timeSlots.get(index) == null) return false;
        GroupSubject s = timeSlots.get(index).getGS();
        if (s.getDegree().equals(degree) && s.getAcademicPlan().equals(ap) && s.getSubject().equals(subject)) return true;
        return false;
    }

    public Boolean hasEquipment(String e) {
        if (equipments.containsKey(e)) return true;
        return false;
    }

    public Boolean notAccessible() {
        if (this.floor == 0) return false;
        if (building.hasElevator()) return false;
        return true;
    }

    public void saveEquipments(ArrayList<String> l) {
        l.add(Integer.toString(equipments.size()));
        for (String e: equipments.keySet()) {
                l.add(e);
        }
    }
}
