package schedule.persistance;

import java.util.ArrayList;
import schedule.domain.Restriction;
import schedule.domain.EducationalInstitution;

public class SalvadoraRestriction {

    public static void saveRestrictions(EducationalInstitution ei, ArrayList<String> l) { // HashMap<String,Restriction> aux;
        for (String s: ei.getAllRestrictions().keySet()) {
            Restriction r = ei.getAllRestrictions().get(s);
            l.add(r.getParameter(0));
            l.add(s);
            for (int i = 1; i < r.getSize(); i++) {
                l.add(r.getParameter(i));
            }
            switch(r.getParameter(0)) {
                case "1": case "15": case "4":
                case "3": l.add("END");
            }
        }
        l.add("END"); // Final parte Restriction
    }

    public static void save(EducationalInstitution ei, ArrayList<String> l) {
        SalvadoraRestriction.saveRestrictions(ei, l);
    }
}
