package or.kosta.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import or.kosta.dto.SearchVO;
import or.kosta.dto.ShowVO;

@Repository
public class ShowDao {
	
	@Autowired
	private SqlSessionTemplate ss;
	
	public void saveShow(ShowVO vo) {
		System.out.println("Writer : "+vo.getWriter());
		System.out.println("Grpcode : "+vo.getGrpcode());
		System.out.println("price : "+vo.getPrice());
		System.out.println("path : "+vo.getPath());
		System.out.println("Pwd : "+vo.getPwd());
		System.out.println("Comm : "+vo.getComm());
		System.out.println("Reip : "+vo.getReip());
		
		ss.insert("showshop.showins",vo);		
	}
	
	public List<ShowVO> getList(SearchVO svo){
		return ss.selectList("showshop.list",svo);
	}
	public List<ShowVO> getList2(){
		return ss.selectList("showshop.list2");
	}


	public int getTotalCount(SearchVO svo) {
		
		return ss.selectOne("showshop.listcount");
	}
}
