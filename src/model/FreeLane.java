package model;

// 자유 수영 레인
public class FreeLane extends Lane {
    private final Course course;

    public FreeLane(int openingTime, int closingTime, int laneNum, int length, double depth, Course course) {
        super(openingTime, closingTime, laneNum, length, depth);
        this.course = course;
    }

    public boolean useFin(){
        return course == Course.FIN;
    }

    public boolean checkIfSuitableLane(Person person){
        // 핀 레인이 아닌 레인에서만 실행
        // 지금 레인의 요구 경력 <= 이용자의 경력 < 다음 레인의 요구 경력일 때만 배정
        return !useFin() && (person.getExp() >= course.getMinExp());
    }

    public void printResult(Person person){
        System.out.println("\n" + person.getName() + "님은 " + laneNum + "번 레인(자유수영, " + course.getLabel() + ")에 배정되었습니다.");
    }

    @Override
    public void printStatus() {
        System.out.println(laneNum + "번 레인 - " + course.getLabel() + " 자유수영(최소 경력: " + course.getMinExp() + "개월), 레인 길이: " + length + "m, 레인 수심: " + depth + "m");
    }
}
