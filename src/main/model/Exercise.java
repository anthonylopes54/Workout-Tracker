package model;

public class Exercise {
    String name;
    int sets;
    int reps;

    public Exercise(String name, int sets, int reps){  //Should I add a description of the exercise?

    }

    // EFFECTS: returns the number of sets for the given exercise

    public int getSets() {
        return 0;
    }

    //EFFECTS: returns the number of reps for the given exercise

    public int getReps() {
        return 0;
    }

    // EFFECTS: returns the name of the exercise

    public String getName() {
        return "";
    }

    // MODIFIES: this
    // EFFECTS: changes the value of the reps field with given parameter;
    //          if r <= 0, change value of reps to 0

    public void changeReps(int r) {

    }

    // MODIFIES: this
    // EFFECTS: changes the value of the sets field with the given parameter;
    //          if s <= 0, change value of sets to 0

    public void changeSets(int s) {

    }
}
