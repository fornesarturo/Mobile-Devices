package mx.itesm.class7;

/**
 * Created by forne on 07/03/2017.
 */

public class Student {

    private String name;
    private float grade;

    public Student(String name, float grade){
        this.name = name;
        this.grade = grade;
    }

    public String getName(){ return this.name; }
    public float getGrade(){ return this.grade; }
}
