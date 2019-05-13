package schedule.persistance;

import java.util.ArrayList;
import schedule.domain.GroupSubject;
import schedule.domain.Subject;
import schedule.domain.AcademicPlan;
import schedule.domain.Degree;
import schedule.domain.EducationalInstitution;

public class SalvadoraAcademica {
    public static void saveSubjectType(GroupSubject gs, ArrayList<String> l) {
        l.add(gs.getSessionType());
        l.add(Integer.toString(gs.getClassroomSize()));
        l.add(Integer.toString(gs.getSessionLength()));
        l.add(Integer.toString(gs.getSessionsWeek()));
    }

    public static void saveSubject(Subject s, ArrayList<String> l) {
        l.add(s.getName());
        l.add(Integer.toString(s.getSlots()));
        l.add(Integer.toString(s.getGroup(0).getTypes().size()));
        for (String str : s.getGroup(0).getTypes()) {
            SalvadoraAcademica.saveSubjectType(s.getGroup(0).getSubgroups(str).get(0), l);
        }
    }

    public static void saveAcademicPlan(AcademicPlan a, ArrayList<String> l) {
        l.add(a.getName());
        l.add(Integer.toString(a.getSubjects().size()));
        for (String s : a.getSubjects().keySet()) {
            SalvadoraAcademica.saveSubject((a.getSubject(s)), l);
        }
    }

    public static void saveDegree(Degree d, ArrayList<String> l) {
        l.add(d.getName());
        l.add(d.getType());
        l.add(Integer.toString(d.getCurriculumMap().size()));
        System.out.println(d.getCurriculumMap().size());
        for (String s : d.getCurriculumMap().keySet()) {
            System.out.println("n-");
            SalvadoraAcademica.saveAcademicPlan(d.getCurriculumMap().get(s), l);
        }
    }

    public static void save(EducationalInstitution ei, ArrayList<String> l) {
        l.add(Integer.toString(ei.getAllDegrees().size()));
        for (String s : ei.getAllDegrees().keySet()) {
            SalvadoraAcademica.saveDegree(ei.getAllDegrees().get(s), l);
        }
    }
}
