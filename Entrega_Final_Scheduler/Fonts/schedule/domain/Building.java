package schedule.domain;

import java.util.*;

public class Building implements Cloneable {
    private String name;
    private HashMap<String,Classroom> classrooms;
    private HashMap<String,Equipment> equipments;

    // ###----- CONSTRUCTORS -----###

    public Building() {
        this.name = "";
        this.classrooms = new HashMap<String,Classroom>();
        this.equipments = new HashMap<String,Equipment>();
    }

    public Building(String name) {
        this.name = name;
        this.classrooms = new HashMap<String,Classroom>();
        this.equipments = new HashMap<String,Equipment>();
    }

    @Override
    public Building clone() throws CloneNotSupportedException {
        Building b = (Building) super.clone();
	    b.classrooms = (HashMap<String, Classroom>) b.classrooms.clone();
	    b.equipments = (HashMap<String, Equipment>) b.equipments.clone();
        return b;
    }

    // ###----- MODIFIERS / SETTERS -----###

    public void setName(String name) {
        this.name = name;
    }

    public void setAllClassrooms(HashMap<String,Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public void setAllEquipments(HashMap<String,Equipment> equipments) {
        this.equipments = equipments;
    }

    public void addClassroom(Classroom classroom) {
        this.classrooms.put(classroom.getName(),classroom);
    }

    public void addEquipment(Equipment equipment) {
        this.equipments.put(equipment.getName(),equipment);
    }

    public void elimEquipment(String e) {
        equipments.remove(e);
    }

    public void elimClassroom(String c) {
        classrooms.remove(c);
    }

    // ###----- CONSULTS / GETTERS -----###

    public String getName() {
        return name;
    }

    public int getSizeClassrooms() {
        return classrooms.size();
    }

    public Set<String> getSetKeysClassrooms() {
        return classrooms.keySet();
    }

    public Set<String> getSetKeysEquipments() {
        return equipments.keySet();
    }

    public Equipment getOneEquipment(String s) {
        return equipments.get(s);
    }

    public Classroom getOneClassroom(String s) {
        return classrooms.get(s);
    }

    public HashMap<String,Classroom> getAllClassrooms() {
        return classrooms;
    }

    public int getSizeEquipments() {
        return equipments.size();
    }

    public HashMap<String,Equipment> getAllEquipments() {
        return equipments;
    }

    public Boolean containsClassroom(String name) {
        if (classrooms.containsKey(name)) return true;
        return false;
    }

    public Boolean hasElevator() {
        if (equipments.containsKey("Elevator")) return true;
        return false;
    }

    public void saveEquipments(ArrayList<String> l) {
        l.add(Integer.toString(equipments.size()));
        for (String e: equipments.keySet()) {
            l.add(e);
        }
    }

    public void saveClassrooms(ArrayList<String> l) {
        for (String s: classrooms.keySet()) {
            l.add("Cl");
            Classroom c = classrooms.get(s);
            l.add(c.getName());
            l.add(Integer.toString(c.getSize()));
            l.add(Integer.toString(c.getFloor()));
            c.saveEquipments(l);
            l.add(this.name);
        }
    }
}
