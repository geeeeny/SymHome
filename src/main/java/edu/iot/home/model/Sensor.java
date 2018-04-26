package edu.iot.home.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
	private int 	sensorId;	// id
	private String  name;		// 장치 식별명
	private float 	value;		// 센서값
	private String 	type;		// 센서 종류
	private String 	location;	// 설치 위치
	private String 	regDate;	// 측정일/시간
}
