package service;

import model.*;

import java.util.List;

public class LaneAllocator {
    private final Pool pool;

    public LaneAllocator(Pool pool) {
        this.pool = pool;
    }

    public List<ClassLane> getClassLanes() {
        return List.of(
                new ClassLane(pool.getOpeningTime(), pool.getClosingTime(), 1, 25, 0, Course.CHILD),
                new ClassLane(pool.getOpeningTime(), pool.getClosingTime(), 2, 25, 1.2, Course.BASIC),
                new ClassLane(pool.getOpeningTime(), pool.getClosingTime(), 3, 25, 1.2, Course.INTERMEDIATE),
                new ClassLane(pool.getOpeningTime(), pool.getClosingTime(), 4, 50, 1.4, Course.ADVANCED),
                new ClassLane(pool.getOpeningTime(), pool.getClosingTime(), 5, 50, 1.4, Course.MASTER)
        );
    }

    public List<FreeLane> getFreeLanes() {
        return List.of(
                new FreeLane(pool.getOpeningTime(), pool.getClosingTime(), 6, 25, 1.2, Course.BASIC),
                new FreeLane(pool.getOpeningTime(), pool.getClosingTime(), 7, 25, 1.2, Course.INTERMEDIATE),
                new FreeLane(pool.getOpeningTime(), pool.getClosingTime(), 8, 25, 1.2, Course.ADVANCED),
                new FreeLane(pool.getOpeningTime(), pool.getClosingTime(), 9, 50, 1.4, Course.MASTER),
                new FreeLane(pool.getOpeningTime(), pool.getClosingTime(), 10, 50, 1.4, Course.FIN)
        );
    }

    public void assignChildLane(Person person, List<ClassLane> classLanes){
        for (ClassLane lane : classLanes) {
            if (lane.isChildLane()){
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
                if (lane.useFin()){
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
