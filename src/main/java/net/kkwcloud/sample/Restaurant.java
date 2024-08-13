package net.kkwcloud.sample;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;


@Component // 스프링이 객체를 관리하게 함 --> 필수 : root-context.xml 에 context:component-scan 패키지 추가
@Data // lombok이 DTO 처럼 관리
public class Restaurant {
	
	//필드
	@Setter(onMethod_ = @Autowired) // 자동으로 setChef()를 컴파일 시 생성한다.
	private Chef chef;	//setChef(Chef)
	private String restaurantName;
	private Date openTime;
	private Date closeTime;
	
	//생성자
	
	
	//메서드
	
}
