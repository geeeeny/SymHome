package edu.iot.home.api;

import java.io.IOException;
import java.util.List;

import edu.iot.home.model.Sensor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SensorApi {
	@GET("sensor")
	Call<List<Sensor>>list();
	
	@GET("sensor/{sensorId}")
	Call<Sensor> get(@Path("sensorId") int sensorId);
	
	@POST("sensor")
	Call<Boolean> post(@Body Sensor sensor);
	
	@PUT("sensor/{sensorId}")
	Call<Boolean> put(@Path("sensorId") int sensorId, @Body Sensor sensor);
	
	@DELETE("sensor/{sensorId}")
	Call<Boolean> delete(@Path("sensorId") int sensorId);
	
	//이 인터페이스의 구현 객체
	static SensorApi service =
			new Retrofit.Builder()
				.baseUrl("http://localhost:8080/gateway/api/")	//통신할 서버 주소
				.addConverterFactory(GsonConverterFactory.create())	//문자열->객체 변환시 gson converter를 쓰겠다.
				.build()	//클래스 생성
				.create(SensorApi.class);	//타입지정하여 인스턴스 생성
	
	//api를 통한 데이터 전송 메서드
	public static void send(Sensor sensor) {
		Call<Boolean> call = SensorApi.service.post(sensor);
		try {
			Response<Boolean> res = call.execute();
			if(res.code()==200) {
				boolean result = res.body();
				if(result) {
					System.out.println("등록 성공");
				}else {
					System.out.println("등록 실패");
				}
			}else {
				System.out.println("에러 코드: "+res.code());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
