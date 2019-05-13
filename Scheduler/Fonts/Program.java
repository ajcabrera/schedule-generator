import java.util.*;

public class Program{

  private static void printMenu(){
    System.out.println("\n------------------------------------------------------------------------");
    System.out.println("-----------------------------  MAIN MENU  ------------------------------");
    System.out.println("------------------------------------------------------------------------");
    System.out.println("(1) To launch Building Factory (Buildings, Classrooms, Equipments) enter: \"B\"");
    System.out.println("(2) To launch Academic Factory (Degree, Academic Plan, Subjects, Groups) enter: \"A\"");
    System.out.println("(3) To launch Restriction Factory enter: \"R\"");
    System.out.println("(4) To generate schedule enter: \"G\"");
    System.out.println("(5) To end the execution enter: \"END\"");
    System.out.println("------------------------------------------------------------------------\n");
  }

  private static void printExit(){
    System.out.println("-----------------------------------------");
    System.out.println("\tExiting program");
    System.out.println("-----------------------------------------");
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    EducationalInstitution E = new EducationalInstitution();
    FactoriaBuilding.factory(E,in);
    FactoriaAcademica.factory(E,in);
    FactoriaRestriction.factory(E,in);


    String i = new String("");
    while( !i.equals("END") ){

      printMenu();

      i = in.nextLine();
      switch (i){
        case "R": FactoriaRestriction.factory(E,in); break; //Restricciones
        case "B": FactoriaBuilding.factory(E,in); break;
        case "A": FactoriaAcademica.factory(E,in); break;
        case "G": E.generateSchedule(in); break;
        System.out.println(E.rightRestriction(a));break;
        case "PRINT": E.printSchedule(); break;
        case "END" : printExit(); break;
        default:  System.out.println("Please, select a valid option.");
      }
    }
  }
}

// Boolean getSlotInfoIsSubject(String degree, String ap, String subject, int index)
