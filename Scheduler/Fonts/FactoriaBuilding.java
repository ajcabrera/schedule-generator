import java.util.*;

public class FactoriaBuilding {
  private static Scanner in;

  private static Classroom CreateClassroom() {
    System.out.println("Enter name:");
    String name = in.nextLine();
    System.out.println("Enter size:");
		String sSize = in.nextLine();
		int size = Integer.parseInt(sSize);
    System.out.println("Enter floor:");
    String sFloor = in.nextLine();
		int floor = Integer.parseInt(sFloor);
    return new Classroom(name,size,floor);
  }

  private static Building CreateBuilding(String name) {
    return new Building(name);
  }

  private static Equipment CreateEquipment(String name) {
    System.out.println("Enter description:");
    String description = in.nextLine();
    return new Equipment(name, description);
  }

  private static HashMap<String,Equipment> FindEquipments(HashMap<String,Equipment> equipments) {
    System.out.println("Enter number of equipments:");
    String equip = in.nextLine();
		int numEquipments = Integer.parseInt(equip);
    HashMap<String,Equipment> e = new HashMap<String,Equipment> ();
    for (int i = 0; i < numEquipments; i++) {
      System.out.println("Enter name equipment:");
      equip = in.nextLine();
      while(!equipments.containsKey(equip)) {
        System.out.println("Name Equipments not valid, enter a valid name:");
        equip = in.nextLine();
      }
      e.put(equipments.get(equip).getName(),equipments.get(equip));
    }
    return e;
  }

  private static Building FindBuilding(HashMap<String,Building> buildings) {
    String name = in.nextLine();
    while (!buildings.containsKey(name)) {
      System.out.println("Name Building not valid, enter a valid name:");
      name = in.nextLine();
    }
    return buildings.get(name);
  }

  public static void factory(EducationalInstitution e, Scanner s) {
    in = s;
    String command = "initDriver";
    String name;
    HashMap<String,Equipment> equipments = e.getAllEquipments();
    HashMap<String,Building> buildings = e.getAllBuildings();
    while (!command.equals("END")) {
      System.out.println(" ----------------------------------------------");
			System.out.println(" -- Menu Factoria Equipment/Building/Classroom --");
			System.out.println(" ----------------------------------------------" + "\n");
			System.out.println("To create an Equipment enter \"Eq\"");
			System.out.println("To create a Building enter \"Bu\"");
			System.out.println("To create a Classroom enter \"Cl\"");
			System.out.println("To end enter \"END\"");
      System.out.println("Remember: to create a Classroom you need at least a Building");
      command = in.nextLine();
      switch(command) {
        case "Eq":  System.out.println("Enter name:");
                    name = in.nextLine();
                    equipments.put(name, CreateEquipment(name));
                    break;

        case "Bu":  System.out.println("Enter name:");
                    name = in.nextLine();
                    Building b1 = CreateBuilding(name);
                    b1.setAllEquipments(FindEquipments(equipments));
                    buildings.put(name, b1);
                    break;

        case "Cl":  Classroom c = CreateClassroom();
                    c.setAllEquipments(FindEquipments(equipments));
                    System.out.println("Enter building name:");
                    Building b2 = FindBuilding(buildings);
                    c.setBuilding(b2);
                    b2.addClassroom(c);

        case "END": break;

        default:    System.out.println("Wrong Input, please select a valid option.");
      }
    }
    e.setEquipments(equipments);
    e.setBuildings(buildings);
  }
}
