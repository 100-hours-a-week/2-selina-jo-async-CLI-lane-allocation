package sensor;

import java.util.Random;

public class TempSensorThread implements Runnable {
    private volatile boolean running = true;
    private double temp;

    public TempSensorThread(double initTemp){
        this.temp = initTemp;
    }

    @Override
    public void run(){
        Random random = new Random();
        while (running){
            if(Thread.interrupted()){
                break;
            }

            System.out.printf("\n \uD83C\uDF21\uFE0F [수온 센서] 현재 수온: %.1f°C%n", temp);

            //이전 온도에서 무작위로 0.1도 상승하거나 하강
            if (random.nextBoolean()){
                temp += 0.1;
            }else{
                temp -= 0.1;
            }

            try{
                Thread.sleep(5000); //5초마다 갱신
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
