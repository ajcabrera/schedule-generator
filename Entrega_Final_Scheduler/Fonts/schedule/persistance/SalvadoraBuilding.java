package schedule.persistance;

import java.util.ArrayList;
import schedule.domain.Classroom;
import schedule.domain.Building;
import schedule.domain.Equipment;
import schedule.domain.EducationalInstitution;

public class SalvadoraBuilding {
    public static void saveClassroom(Classroom c, ArrayList<String> l) {
        l.add("Cl");
        l.add(c.getName());
        l.add(Integer.toString(c.getSize()));
        l.add(Integer.toString(c.getFloor()));
        l.add(Integer.toString(c.getSetKeys().size()));
        for (String s : c.getSetKeys()) {
            l.add(s);
        }
    }

    public static void saveBuilding(Building b, ArrayList<String> l) {
        l.add("Bu");
        l.add(b.getName());
        l.add(Integer.toString(b.getSetKeysEquipments().size()));
        for (String s : b.getSetKeysEquipments()) {
            l.add(s);
        }
        for (Classroom c : b.getAllClassrooms().values()) {
            SalvadoraBuilding.saveClassroom(c, l);
            l.add(b.getName());
        }
    }

    public static void saveEquipment(Equipment e, ArrayList<String> l) {
        l.add("Eq");
        l.add(e.getName());
        l.add(e.getDescription());
    }

    public static void save(EducationalInstitution ei, ArrayList<String> l) {
        for (Equipment e : ei.getAllEquipments().values()) {
            SalvadoraBuilding.saveEquipment(e, l);
        }
        for (Building b : ei.getAllBuildings().values()) {
            SalvadoraBuilding.saveBuilding(b, l);
        }
        l.add("END");
    }
}
