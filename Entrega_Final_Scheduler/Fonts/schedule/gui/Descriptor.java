package schedule.gui;

import java.util.*;

public abstract class Descriptor {
    private static final Map<String,String> d;
    static {
        d = new HashMap<String,String>();

        // Welcome screen (HELP)
        d.put("welcomeHelp","Choosing User enters Read-Only mode. Schedules can be generated without modifying the context.\nChoosing Admin will allow edition plus schedule generation.\n");


        // Educational Institution Screen (HELP)
        d.put("eiHelp","Press the buttons in the left panel to explore the items (Buildings, Equipments, Degrees and Restrictions) of this Educational Institution.\n" +
                "In the right panel you can modify the selected item, remove items and add new ones." +
                "\nWhen the Educational Institution is ready, press Generate Schedule to process the information. " +
                "\nRemember to save changes if you may want to use this context again.\n" +
                "\n\t > SHORTCUTS:\n\tAlt+B (Buildings)\n\tAlt+Q (Equipments)\n\tAlt+D (Degrees)\n\tAlt+R (Restrictions)\n\tAlt+S (Save Changes)\n\tAlt+G (Generate Schedule)" +
                "\n\tAlt+X (Exit program)\n\tAlt+H (Help window)\n\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\n\tAlt+Z (Remove item)" +
                "\n\nNOTE: It is highly recommended to avoid repetition in naming \n(i.e. a Degree called 'Informatica' and a Subject called 'Informatica' could result in undesired behavior).");


        // Educational Institution Screen (Building)
        d.put("eiBu","In this list, there are all the Buildings that belong to the Educational Institution.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");

        // Educational Institution Screen (Equipment)
        d.put("eiEq","In this list, there are all the Equipments contemplated by this Educational Institution.\n" +
                "Creating the equipment 'Elevator' will enable Buildings to have one and become 'accessible buildings'.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");

        // Educational Institution Screen (Degree)
        d.put("eiDe","In the list below, there are all the Degrees available in this Educational Institution.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");

        // Educational Institution Screen (Restriction)
        d.put("eiRe","In the list below, there are all the Restrictions created for the generation of the Schedule.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");


        // Degree (HELP)
        d.put("dHelp","Press the buttons in the left panel to explore the Academic Plans of this Degree.\n" +
                "If you want to discard the changes made, press 'BACK', otherwise press Accept.\n" +
                "\n\t > SHORTCUTS:\n\tAlt+P (Academic Plans)\n\tAlt+ENTER (Accept changes)\n\tAlt+BACKSPACE (Go back and discard)" +
                "\n\tAlt+X (Exit program)\n\tAlt+H (Help window)\n\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\n\tAlt+Z (Remove item)");

        // Degree (Academic Plan)
        d.put("deAP","In the list below, there are all the Academic Plans available in this Degree.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");


        // Academic Plan (HELP)
        d.put("apHelp","Press the buttons in the left panel to explore the items (Subjects, Levels) of this Academic Plan.\n" +
                "Administrators can modify these items and the name of the Academic Plan.\n" +
                "If you want to discard the changes made, press 'BACK', otherwise press Accept.\n" +
                "\n\t > SHORTCUTS:\n\tAlt+S (Subjects)\n\tAlt+L (Levels)\n\tAlt+ENTER (Accept changes)\n\tAlt+BACKSPACE (Go back and discard)" +
                "\n\tAlt+X (Exit program)\n\tAlt+H (Help window)\n\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\n\tAlt+Z (Remove item)");

        // Academic Plan (Subject)
        d.put("apSu","In the list below, there are all the Subjects described in this Academic Plan.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");

        // Academic Plan (Level)
        d.put("apLe","In the list below, there are all the Levels of subjects described in this Academic Plan.\n" +
                "When two or more subjects are on the same level, the Schedule Generator will avoid session overlapping for groups with the same ID.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");


        // Subject (HELP)
        d.put("sHelp","Press the buttons in the left panel to explore the items (Group Types, Corequisites) of this Subject.\n" +
                "Administrators can modify these items, the name of the subject and the amount of people that can enroll.\n" +
                "If you want to discard the changes made, press 'BACK', otherwise press Accept.\n" +
                "\n\t > SHORTCUTS:\n\tAlt+G (Group Type)\n\tAlt+C (Corequisite)\n\tAlt+ENTER (Accept changes)\n\tAlt+BACKSPACE (Go back and discard)" +
                "\n\tAlt+X (Exit program)\n\tAlt+H (Help window)\n\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\n\tAlt+Z (Remove item)");

        // Subject (Group Type)
        d.put("sGr","In the list below, there are all the Group Types of this subject.\n" +
                "Each group type has a type of session (it could require computers, chemistry equipment or whatever equipment exists). It also has a class size, a duration and a number of sessions per week.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");

        // Subject (Co-Req Subject)
        d.put("sCo","In the list below, there are all the subjects that are correquisite to the one in treatment.\n" +
                "When two subjects are correquisite, the Schedule Generator will avoid session overlapping for groups with the same ID.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");


        // Building (HELP)
        d.put("bHelp","Press the buttons in the left panel to explore the items (Classrooms, Equipments) available in this Building.\n" +
                "Administrators can modify these items and the name of the building.\n" +
                "If you want to discard the changes made, press 'BACK', otherwise press Accept.\n" +
                "\n\t > SHORTCUTS:\n\tAlt+C (Classroom)\n\tAlt+Q (Equipment)\n\tAlt+ENTER (Accept changes)\n\tAlt+BACKSPACE (Go back and discard)" +
                "\n\tAlt+X (Exit program)\n\tAlt+H (Help window)\n\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\n\tAlt+Z (Remove item)");

        // Building (Classroom)
        d.put("bCl","In the list below, there are all the Classrooms in this Building.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");

        // Building (B-Equipment)
        d.put("bEq","In the list below, there are all the Equipments assigned to this Building.\n" +
                "You will be able to assign any equipment as long as it exists for this Educational Institution (you can create those in the Main Menu).\n" +
                "If you want a Building to be accessible, the equipment 'Elevator' will be required.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");


        // Classroom (HELP)
        d.put("cHelp","Press the buttons in the left panel to explore the Equipments available in this Classroom.\n" +
                "Administrators can modify the name, size and floor of the classroom.\n" +
                "If you want to discard the changes made, press 'BACK', otherwise press Accept.\n" +
                "\n\t > SHORTCUTS:\n\tAlt+Q (Equipment)\n\tAlt+ENTER (Accept changes)\n\tAlt+BACKSPACE (Go back and discard)" +
                "\n\tAlt+X (Exit program)\n\tAlt+H (Help window)\n\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\n\tAlt+Z (Remove item)");

        // Classroom (C-Equipment)
        d.put("cEq","In the list below, there are all the Equipments available in this Classroom.\n" +
                "\n > SHORTCUTS:\n\tAlt+H (Help window)\tAlt+A (Add item)\n\tAlt+E (Edit item/View Details)\tAlt+Z (Remove item)");


        // Generated Schedule
        d.put("gsHelp","  Here you can see the generated schedule and modify session positions." +
                "\n  There are two possible views available: " +
                "\n\t- Filter ON: See sessions by selected subject and selected classroom" +
                "\n\t- Filter OFF: See all sessions for selected classroom" +
                "\n\n\tYou can select a session in the schedule and click on Move Session to assign it to another position");


        // Modify Schedule (HELP)
        d.put("modHelp"," Select the desired position in the schedule for the selected session" +
                "\n After clicking 'OK', the modification will be checked according to the rules" +
                "\n specified in this context");



        // RESTRICTIONS (Desc - Format)
        String TR = " This restriction ";
        String noSpaces = "\n Note: Do not use spaces before or after the commas, nor at the end.";

        d.put("r0","    Select a restriction from the dropdown menu and fill out the parameters and the alias");

        // R1
        d.put("r1",TR + "will disable an interval of hours in a weekday for a certain Subject,\n Level or the whole Academic Plan.\n No lectures will be assigned in that time frame." +
                "\n Write the name of the subjects separated by commas, or the level alias or the word ALL" + noSpaces);
        d.put("rf1", "Format: Degree,AcademicPlan,Monday(weekdays),FromHour(8 to 19),ToHour(9 to 20),Subject1,Subject2,....");

        // R2
        d.put("r2",TR + "requires an equipment to be assigned to a building or classroom in order" +
                "\n to be selected for the session type specified" + noSpaces);
        d.put("rf2", "Format: nameDegree, nameAcademicPlan,nameSubject,sessionType,nameEquipment");

        // R3
        d.put("r3",TR + "will impose the selected subjects' sessions to be put in the morning, afternoon or " +
                "\n balanced, which means that for each two groups, one will go in the morning and the other in the afternoon." + noSpaces);
        d.put("rf3", "Format: nameDegree,nameAcademicPlan,Morning/Afternoon/Balanced,Subject1,Subject2 ...");

        // R4
        d.put("r4",TR + "will impose to the selected subjects to have a group with accesibility \n Which means it will be assigned to a building with elevator or a classroom on floor 0" + noSpaces);
        d.put("rf4", "Format: nameDegree,nameAcademicPlan,subject1,subject2,...");

        // R5
        d.put("r5",TR + "will generate a Schedule without the building or Classroom selected" + noSpaces);
        d.put("rf5",  "Format1: Bu,nameBuilding | Format2: Cl,nameBuilding,nameClassroom");

        // R6
        d.put("r6",TR + "will impose to the selected subject session type's sessions to be assigned to the selected Building or Classroom" + noSpaces);
        d.put("rf6", "Format: nameDegree,nameAcademicPlan,nameSubject,sessionType,Cl/Bu,nameBuilding(,nameClassroom)");

        // R7
        d.put("r7",TR + "will impose to the second selected subject session type's sessions to be after the first selected Subject Session Type's sessions" + noSpaces);
        d.put("rf7", "Format: nameDegree,nameAcademicPlan,nameSubject,SessionTypeBefore,SessionTypeAfter");

        // R8
        d.put("r8",TR + "will forbid the selected day to the selected Subject" + noSpaces);
        d.put("rf8", "Format: nameDegree,nameAcademicPlan,nameSubject,Day");

        // R9
        d.put("r9",TR + "will impose to all the sessions of the selected subject session type to be on different days" + noSpaces);
        d.put("rf9", "Format: nameDegree,nameAcademicPlan,nameSubject,SessionType1, SessionType2");

        // R10
        d.put("r10",TR + "will impose to the first session of the selected subject session type to be on the selected day and hour and the selected classroom" + noSpaces);
        d.put("rf10", "Format: nameDegree,nameAcademicPlan,nameSubject,SessionType,numGroup,Day,HourStartSession, nameBuilding, nameClassroom");

        // R11
        d.put("r11",TR + "will force a session of the selected subject session type to be in the chosen day." +
                "\n Write days like this: Monday - Tuesday - ... - Friday " + noSpaces);
        d.put("rf11", "Format: nameDegree,nameAcademicPlan,nameSubject,SessionType,Day");

        // R12
        d.put("r12",TR + "will ensure that a session of the selected subject session type will be at the same time for all the groups." + noSpaces);
        d.put("rf12", "Format: nameDegree,nameAcademicPlan,nameSubject,SessionType");


        // EMPTY
        d.put("empty","");
        d.put("rf0","");



        // POPUPS

        d.put("Levels","    Add an alias for this level and choose existing subjects that\n    should avoid overlapping for namesake groups");
        d.put("Corequisite","    Create a correquisite for this subject.\n    Select an existing subject from the list.");
        d.put("Equipments","    Create an Equipment by writing a name and description ");
        d.put("Classroom Equipments","    Assign an existing Equipment from the list of existing equipments");
        d.put("Building Equipments","    Assign an existing Equipment from the list of existing equipments");
        d.put("Restrictions","    Select a restriction from the dropdown menu and fill out the parameters and the alias");

        d.put("MoveSession", "    Fill all the forms indicating where you want the current session \n    to be placed in the Schedule." +
                "\n    After pressing OK, if the placement for the session is correct\n    under this context, changes will be applied.");

    }


    public static String getDescription(String key){
        return d.getOrDefault(key,"-No description was found-");
    }
}
