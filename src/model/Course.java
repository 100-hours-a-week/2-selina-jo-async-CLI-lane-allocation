// 파일: model/Course.java
package model;

public enum Course {
    CHILD("어린이", 0),
    FIN("핀", 0),
    BASIC("초급", 1),
    INTERMEDIATE("중급", 3),
    ADVANCED("상급", 6),
    MASTER("마스터", 12);

    private final String label;
    private final int minExp;

    Course(String label, int minExp) {
        this.label = label;
        this.minExp = minExp;
    }

    public String getLabel() {
        return label;
    }

    public int getMinExp() {
        return minExp;
    }
}
