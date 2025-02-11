package service;

import model.*;

import java.util.List;

public class PoolService {
    private final Pool pool;

    public PoolService(Pool pool) {
        this.pool = pool;
    }

    public List<ClassLane> getClassLanes() {
        return List.of(
                new ClassLane(pool.getOpeningTime(), pool.getClosingTime(), 1, 25, 0, "어린이", 0, true),
                new ClassLane(pool.getOpeningTime(), pool.getClosingTime(), 2, 25, 1.2, "초급", 1, false),
                new ClassLane(pool.getOpeningTime(), pool.getClosingTime(), 3, 25, 1.2, "중급", 3, false),
                new ClassLane(pool.getOpeningTime(), pool.getClosingTime(), 4, 50, 1.4, "상급", 6, false),
                new ClassLane(pool.getOpeningTime(), pool.getClosingTime(), 5, 50, 1.4, "마스터", 12, false)
        );
    }

    public List<FreeLane> getFreeLanes() {
        return List.of(
                new FreeLane(pool.getOpeningTime(), pool.getClosingTime(), 6, 25, 1.2, 1, false),
                new FreeLane(pool.getOpeningTime(), pool.getClosingTime(), 7, 25, 1.2, 3, false),
                new FreeLane(pool.getOpeningTime(), pool.getClosingTime(), 8, 25, 1.2, 6, false),
                new FreeLane(pool.getOpeningTime(), pool.getClosingTime(), 9, 50, 1.4, 12, false),
                new FreeLane(pool.getOpeningTime(), pool.getClosingTime(), 10, 50, 1.4, 1, true)
        );
    }

    public void assignChildLane(Person person, List<ClassLane> classLanes){
        for (ClassLane lane : classLanes) {
            if (lane.checkIfChildLane()){
                lane.printResult(person);
                return;
            }
        }
        System.out.println("배정 가능한 어린이 레인이 없습니다. 프로그램을 다시 실행해주세요.");
    }

    public void assignClassLane(Person person, List<ClassLane> classLanes){
        ClassLane resultLane = null;
        for (ClassLane lane : classLanes) {
            if (lane.checkIfSuitableLane(person)){
                resultLane = lane;
            }
        }
        if (resultLane == null){
            System.out.println("배정 가능한 강습 레인이 없습니다. 프로그램을 다시 실행해주세요.");
        } else{
            resultLane.printResult(person);
        }
    }

    public void assignFreeLane(Person person, List<FreeLane> freeLanes, int useFin){
        if (useFin == 1){
            for (FreeLane lane : freeLanes) {
                if (lane.checkIfUseFin()){
                    lane.printResult(person);
                    return;
                }
            }
            System.out.println("배정 가능한 핀 레인이 없습니다. 프로그램을 다시 실행해주세요.");
        }else{
            FreeLane resultLane = null;
            for (FreeLane lane : freeLanes) {
                if (lane.checkIfSuitableLane(person)){
                    resultLane = lane;
                }
            }
            if (resultLane == null){
                System.out.println("배정 가능한 자유 레인이 없습니다. 프로그램을 다시 실행해주세요.");
            } else{
                resultLane.printResult(person);
            }
        }
    }
}
