package or.kosta.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.bytecode.annotation.MemberValueVisitor;
import or.kosta.dto.MemberVO;

@RestController
@RequestMapping("/members")
public class SpringRestController {
	@Autowired
	private SqlSessionTemplate ss;

	// 문자열을 반환하는 경우
	@RequestMapping("/hello")
	public String sayHello() {

		return "Hello Rest Ful";
	}

	// 객체를 Json으로 반환하는 경우
	// 웹에서 요청시에 Jackson-databind 라이브러리가 있어야 함
	@RequestMapping("respVO")
	public MemberVO respVO() {
		MemberVO vo = new MemberVO();
		vo.setId("xman");
		vo.setName("김길동");
		vo.setNum(8);
		vo.setAddress("인천");
		vo.setPassword("test00");

		return vo;
	}

	// 컬랙션 타입의 객체를 반환하여 JSON-ARRAY로 반환하는 경우 2
	@RequestMapping("/respList")
	public List<MemberVO> respList() {

		List<MemberVO> list = new ArrayList<>();
		for (int i = 0; i <= 12; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("xman" + i);
			vo.setName("김길동" + i);
			vo.setNum(i);
			vo.setAddress("인천" + i);
			vo.setPassword("test00" + i);
			list.add(vo);
		}
		return list;
	}

	// 컬랙션 타입의 객체를 반환하여 JSON-ARRAY로 반환하는 경우 2
	@RequestMapping("/respListReeoeNot")
	public ResponseEntity<List<MemberVO>> respListNot() {
		List<MemberVO> list = new ArrayList<>();
		for (int i = 0; i <= 12; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("xman" + i);
			vo.setName("김길동" + i);
			vo.setNum(i);
			vo.setAddress("인천" + i);
			vo.setPassword("test00" + i);
			list.add(vo);
		}
		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		// return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);
	}

	//
	@RequestMapping("/respList2")
	public ResponseEntity<List<MemberVO>> respList(String code) {
		List<MemberVO> list = new ArrayList<>();
		try {
			if (code.equals("1")) {
				for (int i = 0; i <= 12; i++) {
					MemberVO vo = new MemberVO();
					vo.setId("xman" + i);
					vo.setName("김길동" + i);
					vo.setNum(i);
					vo.setAddress("인천" + i);
					vo.setPassword("test0" + i);
					list.add(vo);
				}
			} else {
				for (int i = 0; i <= 12; i++) {
					MemberVO vo = new MemberVO();
					vo.setId("zman" + i);
					vo.setName("김걸단" + i);
					vo.setNum(i);
					vo.setAddress("인천" + i);
					vo.setPassword("xxoo" + i);
					list.add(vo);
				}
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);			
		}
	}
	
	// 연습문제
	@RequestMapping("/memberList")
	public List<MemberVO> memberList() {
		List<MemberVO> list = new ArrayList<>();
		list = ss.selectList("member.memberlist");			
		
		return list;
		
	}
}
