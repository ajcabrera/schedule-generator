package schedule.domain;

import java.util.*;

public class Session {
    private int complexity;

    private int id;
    private int pos;
    private BitSet availability;

    private GroupSubject gs;

    //  ###----- CONSTRUCTORS -----###

    public Session() {
        this.gs = new GroupSubject();
    }

    public Session(GroupSubject gs,int id, int numClassrooms) {
        this.id = id;
        this.gs = gs;
        this.pos = -1;
        this.availability = new BitSet (60 * numClassrooms);
        availability.set(0, 60 * numClassrooms, true); // Todos a true
        this.complexity = 0;
    }


    //  ###----- CONSULTS / GETTERS -----###

    public int getID() {
        return id;
    }

    public int getPos() {
        return pos;
    }

    public int getCardinality(){
        return availability.cardinality();
    }

    public int getComplexity(){
        return complexity;
    }

    public GroupSubject getGS(){
        return gs;
    }

    public int getNextPos (int index) {
        return availability.nextSetBit(index+1);
    }

    public boolean getBit (int pos) {
        return availability.get(pos);
    }

    private boolean notEqualAll(int pos, int length, int test) {
        for (int i = 0; i < length; i++) {
            if (test == (pos+i)) return false;
        }
        return true;
    }

    private boolean notEqualOne(int pos, int length, int test) {
        for (int i = 0; i < length; i++) {
            if (test == (pos+i)%60) return false;
        }
        return true;
    }

    public int getNumHoursAvailable(int pos, int length,  int numClassrooms) {
        int available = 0;
        for (int i = 0; i < 60; i++) {
            boolean b = false;
            if (notEqualOne(pos,length,i)) {
                for (int j = 0; j < numClassrooms; j++) {
                    if (availability.get(i+60*j)) b = true; 
                }
                if (b) available++;
            }
        }
        return available;
    }

    public int getNumHoursAvailableOther(int pos, int length, int numClassrooms) {
        int available = 0;
        for (int i = 0; i < 60; i++) {
            boolean b = false;
            for (int j = 0; j < numClassrooms; j++) {
                if (availability.get(i+60*j) && notEqualAll(pos,length,i+60*j)) b = true; 
            }
            if (b) available++;
        }
        return available;
    }

    public String getInfo(){
        return gs.getSubject() + " (" + gs.getSessionType() + " " + gs.getGroup() + "-" + gs.getID() + ")"; // + " session " + id + ")";
    }

    public Boolean posAvailable(int pos) {
        int length = gs.getSessionLength();
        if ((pos%60)/12 != ((pos+length-1)%60)/12) return false; // sessions can't be on different days
        for (int l = 0; l < length; l++) {
            if (!availability.get(pos+l)) return false;
        }
        return true;
    }

    //  ###----- MODIFIERS / SETTERS -----###

    public void setToOne (int numClassrooms) {
        availability.set(0,60*numClassrooms,true);
    }

    public void setBits(boolean b, int pos){
        availability.set(pos, b);
    }

    public void setBits(int from, int to, boolean b){
        availability.set(from, to, b);
    }

    public void computeComplexity(int suplement){
        complexity = availability.size() - availability.cardinality();
        complexity += suplement*200;
    }

    public void changePosition(int newPos) {
        availability.set(pos,pos+this.gs.getSessionLength(),true);
        assignPosition(newPos);
    }

    public void clearPos() {
        pos = -1;
    }

    public void assignPosition(int newPos) {
        pos = newPos;
        availability.set(newPos,newPos+this.gs.getSessionLength(),false);
  }
}
