package or.kosta.dto;

import org.springframework.web.multipart.MultipartFile;

public class ShowVO {
	private int num, price, hit;
	private String writer, grpcode, pwd, comm, regdate, reip;
	private MultipartFile multipartFile;
	private String path;
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the hit
	 */
	public int getHit() {
		return hit;
	}
	/**
	 * @param hit the hit to set
	 */
	public void setHit(int hit) {
		this.hit = hit;
	}
	/**
	 * @return the writer
	 */
	public String getWriter() {
		return writer;
	}
	/**
	 * @param writer the writer to set
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}
	/**
	 * @return the grpcode
	 */
	public String getGrpcode() {
		return grpcode;
	}
	/**
	 * @param grpcode the grpcode to set
	 */
	public void setGrpcode(String grpcode) {
		this.grpcode = grpcode;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the comm
	 */
	public String getComm() {
		return comm;
	}
	/**
	 * @param comm the comm to set
	 */
	public void setComm(String comm) {
		this.comm = comm;
	}
	/**
	 * @return the regdate
	 */
	public String getRegdate() {
		return regdate;
	}
	/**
	 * @param regdate the regdate to set
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	/**
	 * @return the reip
	 */
	public String getReip() {
		return reip;
	}
	/**
	 * @param reip the reip to set
	 */
	public void setReip(String reip) {
		this.reip = reip;
	}
	/**
	 * @return the multipartFile
	 */
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	/**
	 * @param multipartFile the multipartFile to set
	 */
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
