package schedule.persistance;

import java.util.Scanner;
import schedule.domain.Restriction;
import schedule.domain.EducationalInstitution;

public class FactoriaRestriction {
  private static Scanner in = new Scanner(System.in);

  private static Restriction getRestrictionBlockTimeFrame() {
    Restriction r = new Restriction ();
    r.addParameter("1");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("Creating a blockage for a time frame for the subjects behind. \nFormat [Degree, AP, Day, from, until(not included), Subject1, Subject2 ....] \n To end the creation, enter END");
    while(!param.equals("END")){
      param = in.nextLine();
      if( !param.equals("END") ) r.addParameter(param);
    }
    return r;
  }

  private static Restriction getRestrictionLevel() {
    Restriction r = new Restriction ();
    r.addParameter("15");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("Creating a Level. \nFormat [nameDegree,nameAcademicPlan,nameSubject1,nameSubject2,...]. \nTo end the creation, enter END");
    while(!param.equals("END")){
      param = in.nextLine();
      if( !param.equals("END") ) r.addParameter(param);
    }
    return r;
  }

  private static Restriction getRestrictionGSNeedsEquipment() {
    Restriction r = new Restriction ();
    r.addParameter("2");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("Creating a need for a Subject to have an equipment in a type of session. \nFormat [nameDegree, nameAcademicPlan,nameSubject,sessionType,nameEquipment]");
    for (int i = 0; i < 5; i++) {
      param = in.nextLine();
      r.addParameter(param);
    }
    return r;
  }

    private static Restriction getRestrictionSubjectMorningAfterBalanced() {
    Restriction r = new Restriction ();
    r.addParameter("3");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("Forbids a day to a list of Subject. \nFormat [nameDegree, nameAcademicPlan,Morning/Afternoon/Balanced, Subject1, Subject2 ...]");
    System.out.println("In case of balanced groups 10,30,50... will go in the morning and the groups 20,40,60... will go in the Afternoon  \nTo end the creation, enter END");
    while(!param.equals("END")){
      param = in.nextLine();
      if( !param.equals("END") ) r.addParameter(param);
    }
    return r;
  }

    private static Restriction getRestrictionNeedsAccesibility() {
    Restriction r = new Restriction ();
    r.addParameter("4");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("Impose a list of Subjects to have 1 accesible group(meaning floor 0 or equipment Elevator). \nFormat [nameDegree, nameAcademicPlan,subject1, subject2 ...] \nTo end the creation, enter END");
    while(!param.equals("END")){
      param = in.nextLine();
      if( !param.equals("END") ) r.addParameter(param);
    }
    return r;
  }

  private static Restriction getRestrictionWithoutBuOrCl() {
    Restriction r = new Restriction ();
    r.addParameter("5");
    String param = in.nextLine();
    r.addParameter(param);
    int x;
    System.out.println("Generate the horary without a Building or Classroom. \n");
    System.out.println("If Building format: [Bu,nameBuilding] \n If Classroom format: [Cl,nameBuilding,nameClassroom]");
    param = in.nextLine();
    r.addParameter(param);
    if (param.equals("Bu")) x = 1;
    else x = 2;
    for (int i = 0; i < x; i++) {
      param = in.nextLine();
      r.addParameter(param);
    }
    return r;
  }

    private static Restriction getRestrictionImposeClBuToSST() {
    Restriction r = new Restriction ();
    r.addParameter("6");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("Impose a Classroom or Building to a subject session type. \nFormat if Building [nameDegree, nameAcademicPlan,nameSubject,sessionType, Bu, nameBuilding]");
    System.out.println("Format if classroom [nameDegree, nameAcademicPlan, nameSubject, sessionType, Cl, nameBuilding, nameClassroom]");
    for (int i = 0; i < 6; i++) {
      param = in.nextLine();
      r.addParameter(param);
    }
    if (r.getParameter(6).equals("Cl")) {
      param = in.nextLine();
      r.addParameter(param);
    }
    return r;
  }

  private static Restriction getRestrictionSSTBeforeAfter() {
    Restriction r = new Restriction ();
    r.addParameter("7");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("SS1 will go before SS2 \nFormat [nameDegree, nameAcademicPlan,nameSubject,SessionTypeBefore, SessionTypeAfter]");
    for (int i = 0; i < 5; i++) {
      param = in.nextLine();
      r.addParameter(param);
    }
    return r;
  }

  private static Restriction getRestrictionSubjectNotDay() {
    Restriction r = new Restriction ();
    r.addParameter("8");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("Forbids a day to a Subject. \nFormat [nameDegree, nameAcademicPlan,nameSubject,Day]");
    for (int i = 0; i < 4; i++) {
      param = in.nextLine();
      r.addParameter(param);
    }
    return r;
  }

  private static Restriction getRestrictionSSTNotSameDay() {
    Restriction r = new Restriction ();
    r.addParameter("9");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("SS1 will go before SS2 \nFormat [nameDegree, nameAcademicPlan,nameSubject,SessionType1, SessionType2]");
    for (int i = 0; i < 5; i++) {
      param = in.nextLine();
      r.addParameter(param);
    }
    return r;
  }

  private static Restriction getRestrictionCoRequisit() {
    Restriction r = new Restriction ();
    r.addParameter("16");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("Corequisit. \nFormat [nameDegree,nameAcademicPlan,nameSubject1,nameSubject2]. \nTo end the creation, enter END");
    for (int i = 0; i < 4; i++) {
      param = in.nextLine();
      r.addParameter(param);
    }
    return r;
  }

  private static Restriction getRestrictionSSTOneHour() {
    Restriction r = new Restriction ();
    r.addParameter("10");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("one session of SST will go to the entered hour \nFormat [nameDegree, nameAcademicPlan,nameSubject,SessionType,numGroup, Day, HourStartSession, nameBuilding, nameClassroom]");
    for (int i = 0; i < 9; i++) {
      param = in.nextLine();
      r.addParameter(param);
    }
    return r;
  }

  private static Restriction getRestrictionSameHourSSG() {
    Restriction r = new Restriction ();
    r.addParameter("12");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("One session of SSG will go to the same hour \nFormat [nameDegree, nameAcademicPlan,nameSubject,SessionType]");
    for (int i = 0; i < 4; i++) {
      param = in.nextLine();
      r.addParameter(param);
    }
    return r;
  }

  private static Restriction getRestrictionSSGDay() {
    Restriction r = new Restriction ();
    r.addParameter("11");
    String param = in.nextLine();
    r.addParameter(param);
    System.out.println("One session of the SSG will have to go to this day \nFormat [nameDegree, nameAcademicPlan,nameSubject,SessionType,day]");
    for (int i = 0; i < 5; i++) {
      param = in.nextLine();
      r.addParameter(param);
    }
    return r;
  }

  public static String getString(String s) {
      System.out.println("Enter "+s);
      return in.nextLine();
  }

  public static void factory(EducationalInstitution e, Scanner s) {
    in = s;
    String command = "initFactoria";
    String alias = null;
    while (!command.equals("END")) {
      System.out.println(" ----------------------------------------------");
			System.out.println(" --        Menu Factoria Restricion          --");
			System.out.println(" ----------------------------------------------" + "\n");
			System.out.println("block a time frame enter \"1\"");
      System.out.println("Add Level (restrictions will not apply in schedule, creation accepted) enter \"2\"");
      System.out.println("Subject session requires equipment enter \"3\"");
			System.out.println("To end enter \"END\"");
      command = in.nextLine();
      if (!command.equals("END")) {
        System.out.println("Enter Alias Restriction");
        alias = in.nextLine();
      }
      //Restriction R = new Restriction ();
      //e.addRestriction(r);
      switch(command){
        case "1": { Restriction r = getRestrictionBlockTimeFrame();
                    e.addRestriction(alias,r);
                    break;
                  }

        case "15": { Restriction r = getRestrictionLevel();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "2": { Restriction r = getRestrictionGSNeedsEquipment();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "3": { Restriction r = getRestrictionSubjectMorningAfterBalanced();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "4": { Restriction r = getRestrictionNeedsAccesibility();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "5": { Restriction r = getRestrictionWithoutBuOrCl();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "6": { Restriction r = getRestrictionImposeClBuToSST();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "7": { Restriction r = getRestrictionSSTBeforeAfter();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "8": { Restriction r = getRestrictionSubjectNotDay();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "9": { Restriction r = getRestrictionSSTNotSameDay();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "16": { Restriction r = getRestrictionCoRequisit();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "10": { Restriction r = getRestrictionSSTOneHour();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "12": { Restriction r = getRestrictionSameHourSSG();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "11": { Restriction r = getRestrictionSSGDay();
                    e.addRestriction(alias,r);
                    break;
                  }
        case "END": break;
        default:    System.out.println("Enter a valid command");
      }
    }
  }
}