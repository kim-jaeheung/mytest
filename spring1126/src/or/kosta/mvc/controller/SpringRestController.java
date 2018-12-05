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

	// ���ڿ��� ��ȯ�ϴ� ���
	@RequestMapping("/hello")
	public String sayHello() {

		return "Hello Rest Ful";
	}

	// ��ü�� Json���� ��ȯ�ϴ� ���
	// ������ ��û�ÿ� Jackson-databind ���̺귯���� �־�� ��
	@RequestMapping("respVO")
	public MemberVO respVO() {
		MemberVO vo = new MemberVO();
		vo.setId("xman");
		vo.setName("��浿");
		vo.setNum(8);
		vo.setAddress("��õ");
		vo.setPassword("test00");

		return vo;
	}

	// �÷��� Ÿ���� ��ü�� ��ȯ�Ͽ� JSON-ARRAY�� ��ȯ�ϴ� ��� 2
	@RequestMapping("/respList")
	public List<MemberVO> respList() {

		List<MemberVO> list = new ArrayList<>();
		for (int i = 0; i <= 12; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("xman" + i);
			vo.setName("��浿" + i);
			vo.setNum(i);
			vo.setAddress("��õ" + i);
			vo.setPassword("test00" + i);
			list.add(vo);
		}
		return list;
	}

	// �÷��� Ÿ���� ��ü�� ��ȯ�Ͽ� JSON-ARRAY�� ��ȯ�ϴ� ��� 2
	@RequestMapping("/respListReeoeNot")
	public ResponseEntity<List<MemberVO>> respListNot() {
		List<MemberVO> list = new ArrayList<>();
		for (int i = 0; i <= 12; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("xman" + i);
			vo.setName("��浿" + i);
			vo.setNum(i);
			vo.setAddress("��õ" + i);
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
					vo.setName("��浿" + i);
					vo.setNum(i);
					vo.setAddress("��õ" + i);
					vo.setPassword("test0" + i);
					list.add(vo);
				}
			} else {
				for (int i = 0; i <= 12; i++) {
					MemberVO vo = new MemberVO();
					vo.setId("zman" + i);
					vo.setName("��ɴ�" + i);
					vo.setNum(i);
					vo.setAddress("��õ" + i);
					vo.setPassword("xxoo" + i);
					list.add(vo);
				}
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);			
		}
	}
	
	// ��������
	@RequestMapping("/memberList")
	public List<MemberVO> memberList() {
		List<MemberVO> list = new ArrayList<>();
		list = ss.selectList("member.memberlist");			
		
		return list;
		
	}
}
