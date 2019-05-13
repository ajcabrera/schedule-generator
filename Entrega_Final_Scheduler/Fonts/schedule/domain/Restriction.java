package schedule.domain;

import java.util.*;
import java.lang.*;

public class Restriction implements Cloneable {
    private Vector<String> params;

    /*
    ###########################################################################
    #########################----- CONSTRUCTORS -----##########################
    ###########################################################################
    */

    public Restriction() {
        this.params = new Vector<String> ();
    }

    public Restriction(Vector<String> params) {
        this.params = params;
    }


    /*
    ###########################################################################
    #####################----- MODIFIERS / SETTERS -----#######################
    ###########################################################################
    */

    public void setParameters(Vector<String> params) {
        this.params = params;
    }

    public void addParameter(String parameter) {
        this.params.add(parameter);
    }

    public void modifyParameter(int index, String parameter) {
        this.params.set(index,parameter);
    }

    public void onOffRestriction(boolean b) {
        if (b) this.params.set(1,"1");
        else this.params.set(1,"0");
    }

    @Override
    public Restriction clone() throws CloneNotSupportedException {
        return (Restriction)super.clone();
    }

    /*
    ###########################################################################
    #####################----- CONSULTS / GETTERS -----########################
    ###########################################################################
    */

    public int getSize() {
        return params.size();
    }

    public Vector<String> getParameters() {
        return params;
    }

    public Boolean isInList(String s, int index) {
        for (int i = index; i <params.size(); i++) {
            if (params.get(i).equals(s)) return true;
        }
        return false;
    }

    public String getParameter(int i) {
        return params.get(i);
    }

    public int posSubject(String name, int index) {
        for (int i = index; i < params.size(); i++) {
            if (params.get(i).equals(name)) return i;
        }
        return -1;
    }
}
