package net.kkkcloud.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TimeMapper {

	//create
	@Insert("insert into board values()")
	public String createBoard();

	//read
	@Select("select sysdate from dual")
	public String getTime();

	// @Select로 쿼리를 처리하는 것에 한계가 있어서 xml을 활용한다
	// resources 폴더에 패키지명과 같은 폴더를 다단계로 생성하고 xml 파일명을 인터페이스 명과 일치 시킴\
	public String getTimeXML();

	//update
	@Update("update set values()")
	public String updateBoard();

	//delete
	@Delete("delete from board where 조건")
	public String deleteBoard();
	}
