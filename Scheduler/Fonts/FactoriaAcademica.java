import java.util.*;

public class FactoriaAcademica {
  private static Scanner in = new Scanner(System.in);

  private static Degree createDegree(String name) {
		String type = getString("a TYPE for the Degree.");
    return new Degree(name,type);
  }

  private static Subject createSubject(String name) {
    int slots = 0;
    int numGroups = 0;
    slots = getGreaterThan0("Insert the amount of students that will enroll to this subject. (Must be GREATER than ZERO).");
    Subject s = new Subject(name,slots);

    //System.out.println("Creating main type of Subject " + name);
    //String i = getString("the NAME of the Type of the sessions"); // Type of class in Subject;
    //int size = getGreaterThan0("Insert the SIZE of this type of session for this subject. (Must be GREATER than ZERO).");
    //int duration = getGreaterThan0("Insert the DURATION of this type of session for this subject. (Must be GREATER than ZERO).");
    //int sesiones = getGreaterThan0("Insert the NUMBER OF WEEKLY SESSIONS of this type of session for this subject. (Must be GREATER than ZERO).");

    //s.createGroups();
    return s;
  }

  private static AcademicPlan findAcademicPlan(HashMap<String,Degree> DEGs, String name){
    for( Map.Entry<String,Degree> Dg : DEGs.entrySet() ) {
      AcademicPlan auxAP = Dg.getValue().getAcademicPlan(name);
      if( auxAP != null ) return auxAP;
    }
    return null;
  }


  private static Degree menuFactory(EducationalInstitution e){
    System.out.println("\n----------------------------------------------------------");
    System.out.println("------------------MENU FACTORY DEGREE---------------------");
    System.out.println("----------------------------------------------------------");
    Degree D = new Degree();
    String i = getString("a NAME for the Degree.");
    D = createDegree(i);
    e.addDegree( D );
    return D;
  }

  private static AcademicPlan menuFactory(EducationalInstitution e, Degree D){
    System.out.println("\n-----------------------------------------------------------------");
    System.out.println("------------------MENU FACTORY ACADEMIC PLAN---------------------");
    System.out.println("-----------------------------------------------------------------");
    AcademicPlan AP = new AcademicPlan();
    String i = getString("the NAME of the Academic Plan");
    AP = new AcademicPlan(i);
    D.addAcademicPlan( AP );
    return AP;
  }

private static void menuSubject(AcademicPlan AP){
  System.out.println("\n-----------------------------------------------------------------");
  System.out.println("Subject creation inside " + AP.getName() + " of chosen Degree");
  System.out.println("-----------------------------------------------------------------");
  System.out.println("First, enter the amount of Subjects you want to create.");
  System.out.println("-----------------------------------------------------------------");

  int n = Integer.parseInt(in.nextLine());
  int m = n;
  String i = new String("");
  while( n > 0 ){
    System.out.println("\nCreating Subject " + (m-n+1) + " of " + m);
    i = getString("the NAME of the new Subject"); // Name new Subject;
    while(i.equals("") || AP.hasSubject(i)) i = getString("the Name of a Subject not in use");
    Subject s = createSubject(i);
    menuGroups(s);
    AP.addSubject(s); //Falta asignación de string AP en Subject (todos a la vez?)
    n--;
  }
}


private static void menuGroups(Subject S){
  System.out.println("\n-----------------------------------------------------------------");
  System.out.println("Group creation inside " + S.getName() + ".");
  System.out.println("-----------------------------------------------------------------");
  System.out.println("First, enter the amount of Types of classes you want to create.");
  System.out.println("-----------------------------------------------------------------");

  // tipo(str), size(int), duration(int), nsesiones(int)
  int n = Integer.parseInt(in.nextLine());
  int m = n;
  String i = new String("");
  int size, duration, sesiones;
  while( n > 0 ){
    System.out.println("Creating Type " + (m-n+1) + " of " + m);
    i = getString("the NAME of the Type of the sessions"); // Type of class in Subject;
    size = getGreaterThan0("Insert the SIZE of this type of session for this subject. (Must be GREATER than ZERO).");
    duration = getGreaterThan0("Insert the DURATION of this type of session for this subject. (Must be GREATER than ZERO).");
    sesiones = getGreaterThan0("Insert the NUMBER OF WEEKLY SESSIONS of this type of session for this subject. (Must be GREATER than ZERO).");

    S.addSubgroups(i, size, duration, sesiones);
    n--;
  }

}

public static String getString(String s) {
    System.out.println("Enter "+s);
    return in.nextLine();
}

private static int getGreaterThan0(String s) {
    int i = 0;
    while (!(i > 0)) {
        System.out.println(s);
        i = Integer.parseInt(in.nextLine());
    }
    return i;
}


////////////////////////////////////////////////////////////////////////////////

  public static void factory(EducationalInstitution e, Scanner sc) {
    in = sc;
    int numD = getGreaterThan0("Enter the number of degree you want to create: ");
    for (int i = 0; i < numD; i++) {
      Degree D = menuFactory(e);
      if(D == null) { System.out.println("Error, null Degree selected. Redirected to main menu..."); return; }
      int numAP = getGreaterThan0("Enter the number of AcademicPlan you want to create: ");
      for (int j = 0; j < numAP; j++) {
        AcademicPlan AP = menuFactory(e,D);
        if(AP == null) { System.out.println("Error, null AcademicPlan selected. Redirected to main menu..."); return; }
        menuSubject(AP);
      }
    }
  }
}
