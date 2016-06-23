package com.example.jin_ho.mp3_project_nulltime;

/**
 * Created by Jin-Ho on 2016-06-22.
 */
public class URLScheduaData {

    private String className;
    private String classProfessor;
    private String classPoint;




    public URLScheduaData(String className, String classPoint, String classProfessor, boolean isUse) {
        this.className = className;
        this.classPoint = classPoint;
        this.classProfessor = classProfessor;
    }

    public URLScheduaData() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassProfessor() {
        return classProfessor;
    }

    public void setClassProfessor(String classProfessor) {
        this.classProfessor = classProfessor;
    }

    public String getClassPoint() {
        return classPoint;
    }

    public void setClassPoint(String classPoint) {
        this.classPoint = classPoint;
    }
}
