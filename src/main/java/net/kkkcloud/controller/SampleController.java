package net.kkkcloud.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;
import net.kkkcloud.domain.SampleDTO;
import net.kkkcloud.domain.SampleDTOList;

@Controller // <context:component-scan base-package="net.kkkcloud.controller" />
@RequestMapping("/sample/*") // 현재 클래스의 모든 메서드들의 기본적인 URL 경로
@Log4j2
public class SampleController {

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
	// http://localhost:80/sample/ex02Bean?list[0].name=kkw&list[0].age=15 -> 특수문자 오류
	// http://localhost:80/sample/ex02Bean?list%5B0%5D.name%3Dkkw%26list%5B0%5D.age%3D15 -> url 인코딩 후
	public String ex02bean(SampleDTOList list) { // 리스트 객체를 매개값으로 받음

		log.info("list dtos : " + list);
		return "ex02Bean";

	}

}
