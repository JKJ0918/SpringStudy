package net.kkkcloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice // 컨트롤러 지원코드 -> servlet-context.xml에 scan 코드를 넣음
@Log4j2
public class CommonExceptionAdvice {
	// aop (관심사의 분리) -> 예외처리용 코드를 분리한다.
	// 컨트롤러에서 예외처리를 담당까지 하면 많은 코드가 중복 발생
	// 예외처리용 코드를 분리하여 공통으로 관리한다.
	
	@ExceptionHandler(Exception.class) // 예외처리용 코드 메서드
	public String except(Exception ex, Model model) {
		
		log.error("except 메서드 실행...." + ex.getMessage()); // 예외처리 메시지를 콘솔 출력
		model.addAttribute("exception", ex); // 예외처리 메시지를 model에 담고
		
		log.error(model);
		
		return "error_page"; // error_page.jsp
	}
	
	@ExceptionHandler(NoHandlerFoundException.class) // 404 오류에 대한 예외 발생시
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404 상태코드
	public String handler404(NoHandlerFoundException ex) {
		return "custom404";
	}
	
	
}
