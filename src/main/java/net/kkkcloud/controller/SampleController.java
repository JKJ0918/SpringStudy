package net.kkkcloud.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import net.kkkcloud.domain.SampleDTO;
import net.kkkcloud.domain.SampleDTOList;
import net.kkkcloud.domain.TodoDTO;

@Controller // <context:component-scan base-package="net.kkkcloud.controller" />
@RequestMapping("/sample/*") // 현재 클래스의 모든 메서드들의 기본적인 URL 경로
@Log4j2
public class SampleController {
	
	/* @InitBinder를 사용한 방식
	@InitBinder // 문자열을 날짜 형식으로 변경용
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd"); //yyyy-MM-dd 도 사용가능
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(simpleDateFormat, false));
	}*/
	
	

	@RequestMapping("") // http://localhost:80/sample/
	public void basic() {
		// 리턴 타입이 void 인 경우에는 경로와 같은 url.jsp를 찾는다.
		log.info("basic................");
	}

	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		log.info("SampleController.basicGet get+post 방식의 메서드 호출용");
	}

	@GetMapping("/basicOnlyGet") // http://localhost:80/sample/basicOnlyGet
	public void basicOnlyGet() {
		log.info("SampleController.basicGet get 방식의 메서드 호출용");
	}

	@GetMapping("/basicOnlyPost") // http://localhost:80/sample/basicOnlyPost
	public void basicPostOnly() {
		log.info("SampleController.basicGet post 방식의 메서드 호출용");
	}

	@GetMapping("/ex01") // http://localhost:80/sample/ex01
	public String ex01(SampleDTO sampleDTO) {

		log.info("" + sampleDTO);
		// INFO net.kkkcloud.controller.SampleController(ex0141) - SampleDTO(name=null,
		// age=0, height=0, weight=0)
		return "ex01"; // /WEB-INF/views/ex01.jsp
	}

	@GetMapping("/ex02") // http://localhost:80/sample/ex02?id=kkk&age=12&height=180&weight=70
	public String ex02(@RequestParam("id") String name, @RequestParam("age") int age,
			@RequestParam("height") int height, @RequestParam("weight") int weight) {
		// @RequestParam 파라미터로 사용된 변수 이름과 전달되는 파라미터의 이름이 다른 경우 유용
		// 타입을 정해서 넣을 수 있어 안전하다.
		log.info("name :" + name);
		log.info("age :" + age);
		log.info("height :" + height);
		log.info("weight :" + weight);

		return "ex02";
	}

	@GetMapping("ex02List") // http://localhost:80/sample/ex02List?ids=111&ids=222&ids=523
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {

		log.info("ids : " + ids);
		return "ex02List";
	}

	@GetMapping("ex02Array") // http://localhost:80/sample/ex02Array?ids=111&ids=222&ids=523
	public String ex02Array(@RequestParam("ids") String[] ids) {

		log.info("Array ids : " + Arrays.deepToString(ids));
		return "ex02Array";
	}

	@GetMapping("ex02Bean")
	// http://localhost:80/sample/ex02Bean?list[0].name=kkw&list[0].age=15 -> 특수문자
	// 오류
	// http://localhost:80/sample/ex02Bean?list%5B0%5D.name%3Dkkw%26list%5B0%5D.age%3D15
	// -> url 인코딩 후
	public String ex02bean(SampleDTOList list) { // 리스트 객체를 매개값으로 받음

		log.info("list dtos : " + list);
		return "ex02Bean";

	}

	@GetMapping("/ex03")
	// 1.@InitBinder - http://localhost:80/sample/ex03?title=mbcacademy1&dueDate=2024/08/15&check=true
	// 2.@DateTimeFormat - http://localhost:80/sample/ex03?title=mbcacademy2&dueDate=2024-08-15
	public String ex03(TodoDTO todoDTO) {
		log.info("todo :" + todoDTO);
		return "ex03";
	}

	@GetMapping("/ex04") //http://localhost:80/sample/ex04?name=chicken&age=7&height=20&weight=7&page=7
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		// @ModelAttribute("page") -> url을 통해서 넘어온 값을 model에 담아 준다
		
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		return "/sample/ex04"; //http://localhost:80/sample/ex04.jsp
		//view : servlet-context.xml에서 담당 -> 실제 경로 /WEB-INF/views/sample/ex04.jsp 가 있어야 함.
	}
	
	//리턴 타입에 대한 테스트
	@GetMapping("/ex05") // http://localhost:80/sample/ex05
	public void ex05() { // void -> /WEB-INF/views/sample/ex05.jsp를 찾음
		log.info("/ex05 메서드 실행");
		//파일 [/WEB-INF/views/sample/ex05.jsp]을(를) 찾을 수 없습니다.
		
	}
	
	// 컨트롤러에서 처리한 값을 json으로 출력 테스트
	@GetMapping("/ex06") // http://localhost:80/sample/ex06
	public @ResponseBody SampleDTO ex06() { // @ResponseBody SampleDTO 응답바디 영역에 객체를 담아 리턴
		
		SampleDTO dto = new SampleDTO(); // 빈 객체 생성
		dto.setName("홍길동");
		dto.setAge(39);
		dto.setHeight(170);
		dto.setWeight(75);
		
		return dto; // json{"name":"홍길동","age":39,"Height":170,"Weight":75} -> 백개발자는 json으로만 보냄
		// 프론트 개발자는 화면에 div, table 등을 이용해서 보여줌.
		
		// 응답 헤더에 값을 추가하여 보냄
	}
	
	@GetMapping("/ex07") // http://localhost:80/sample/ex07
	public ResponseEntity<String> ex07(){
		log.info("/ex07 메서드 실행......");
		
		String msg = "{\"name\":\"홍길동\",\"age\":39,\"Height\":170,\"Weight\":75}"; // json {"name":"홍길동","age":39,"Height":170,"Weight":75}
	
		HttpHeaders header = new HttpHeaders(); // httpHeaders 객체 생성 *springFrameWork
		header.add("Content-Type", "application/json;charset=UTF-8"); // 헤더에 타입 추가 json 임을 명시
		
		return new ResponseEntity<String>(msg, header, HttpStatus.OK); // HttpStatus.OK 200 정상코드 임을 보냄
		// 웹페이지 F12 > Network > Ctrl+R 클릭 시 확인가능
	}
	
	@GetMapping("/exUpload") // http://localhost:80/sample/exUpload
	public void exUpload() {
		log.info("/exUpload 메서드 실행.....");
		//리턴이 void -> http://localhost:80/sample/exUpload.jsp
	}
	
	@PostMapping("exUploadPost") // http://localhost:80/sample/exUpload
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach( file -> {
			log.info("-------------------------------------");
			log.info("name : " + file.getOriginalFilename()); //원본 파일명 출력
			log.info("name : " + file.getSize()); // 파일 크기
			log.info("toString : " + file.toString()); // toString 메서드 출력
		});
	}
	
	//Error msg test 1. http://localhost:80/sample/ex04?name=chicken&age=ab&height=20&weight=7&page=7
}
