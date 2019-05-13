package schedule.persistance;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import schedule.domain.EducationalInstitution;

public class PersistanceControl {
    private static PersistanceControl instance = null;

    private PersistanceControl () {

    }

    public static PersistanceControl getInstance() {
        if (instance == null) {
            instance = new PersistanceControl();
        }
        return instance;
    }

    private ArrayList<String> eiToFile (EducationalInstitution ei) {
        ArrayList<String> l = new ArrayList<String>();
        SalvadoraBuilding.save(ei,l);
        SalvadoraAcademica.save(ei,l);
        SalvadoraRestriction.save(ei,l);
        return l;
    }

    private EducationalInstitution eiFromFile (Scanner in) {
        EducationalInstitution ei = new EducationalInstitution();
        FactoriaBuilding.factory(ei,in);
        FactoriaAcademica.factory(ei,in);
        FactoriaRestriction.factory(ei,in);
        return ei;
    }

    public boolean save (String path, EducationalInstitution ei) {
        try {
            ArrayList<String> lines = eiToFile(ei);
            Path file = Paths.get(path);
            Files.write(file, lines, Charset.forName("Utf-8"));
        }
        catch (IOException x) {
            return false;
        }
        return true;
    }

    public EducationalInstitution load(String path) {
        Scanner in = null;
        try {
            File file = new File(path);
            in = new Scanner(file);
        }
        catch (IOException x) {
            return null;
        }
        return eiFromFile(in);
    }
}
