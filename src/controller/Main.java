package controller;

import model.*;
import service.LaneAllocator;
import util.ScriptPrinter;
import util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pool pool = new Pool(6, 22);  // 개장 시간: 6시, 폐장 시간: 22시
        LaneAllocator laneAllocator = new LaneAllocator(pool);
        List<ClassLane> classLanes = laneAllocator.getClassLanes();
        List<FreeLane> freeLanes = laneAllocator.getFreeLanes();

        // 안내 문구 출력
        ScriptPrinter scriptPrinter = new ScriptPrinter();
        scriptPrinter.printStartScript();
        scriptPrinter.printLaneList(classLanes, freeLanes);

        // 1. 이용 시간 입력
        InputValidator.getValidTimeInput(scanner, pool);

        // 2. 사용자 정보 입력
        Person person = InputValidator.getValidPersonInput(scanner);

        // 3. 어린이 여부 -> 어린이면 어린이 강습 레인에 배정 후 프로그램을 빠르게 끝낸다
        int isChild = InputValidator.getValidIntInput("\n어린이인가요? (Yes: 1, No: 0) ", scanner);
        if (isChild == 1) {
            laneAllocator.assignChildLane(person, classLanes);
            return;
        }

        // 4. 강습 vs 자유 선택
        int choice = InputValidator.getValidIntInput("\n강습 레인(1) 또는 자유 레인(0)을 선택하세요: ", scanner);

        if (choice == 1){ // 강습 레인 배정
            laneAllocator.assignClassLane(person, classLanes);
            return;
        }

        // 자유 레인 배정
        int useFin = InputValidator.getValidIntInput("\n핀을 사용하시겠습니까? (Yes: 1, No: 0) ", scanner);
        laneAllocator.assignFreeLane(person, freeLanes, useFin);

        scanner.close();
    }
}
