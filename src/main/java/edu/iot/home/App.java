package edu.iot.home;

import edu.iot.home.device.Light;
import edu.iot.home.device.LightSensor;

public class App 
{
	//발생시킬 데이터
	static float[] value1 = {
		80, 86, 85, 85, 81, 78, 77, 74, 73, 71, 69, 68, 65, 66, 66, 67, 60, 57, 59
	};
	
    public static void main( String[] args ) throws Exception
    {
        LightSensor sensor1 = new LightSensor("입구", "조도", value1);
        
        sensor1.setDaemon(true); //데몬 스레드
        
        //반응자(전등) 4개 연결
        sensor1.setOnChangeListener(new Light("입구", "조명1", 80));
        sensor1.setOnChangeListener(new Light("입구", "조명2", 60));
        sensor1.setOnChangeListener(new Light("입구", "조명3", 60));
        sensor1.setOnChangeListener(new Light("입구", "조명4", 70));
        sensor1.start();
        
        Thread.sleep(1000*10);
        System.out.println("------종료------");
        System.exit(0);
    }
}
