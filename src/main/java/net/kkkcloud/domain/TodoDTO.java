package net.kkkcloud.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	//@InitBinder를 사용한 방식
	/*private String title;
	private Date dueDate;*/

	//@DateTimeFormat을 이용한 방식
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
}
