package or.kosta.dto;

public class PageVO {
	private int currentPage;	//현재 페이지 번호
	private int currentBlock;	//현재 블록 번호
	private int rowsPerPage; 	//한 페이지당 보여줄 목록 수
	private int pagesPerBlock;	//한 블록당 보려줄 페이지 수
	private int totalRows;		//전체 목록수
	private int totalPages;		//전페 페이지 수
	private int totalBlocks;		//전체 블록수
	private int startRow;		//시작 목록 번호
	private int endRow;			//마지막 목록 번호
	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the currentBlock
	 */
	public int getCurrentBlock() {
		return currentBlock;
	}
	/**
	 * @param currentBlock the currentBlock to set
	 */
	public void setCurrentBlock(int currentBlock) {
		this.currentBlock = currentBlock;
	}
	/**
	 * @return the rowsPerPage
	 */
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	/**
	 * @param rowsPerPage the rowsPerPage to set
	 */
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	/**
	 * @return the pagesPerBlock
	 */
	public int getPagesPerBlock() {
		return pagesPerBlock;
	}
	/**
	 * @param pagesPerBlock the pagesPerBlock to set
	 */
	public void setPagesPerBlock(int pagesPerBlock) {
		this.pagesPerBlock = pagesPerBlock;
	}
	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}
	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	/**
	 * @return the totalPage
	 */
	public int getTotalPages() {
		return totalPages;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	/**
	 * @return the totalBlock
	 */
	public int getTotalBlocks() {
		return totalBlocks;
	}
	/**
	 * @param totalBlock the totalBlock to set
	 */
	public void setTotalBlocks(int totalBlocks) {
		this.totalBlocks = totalBlocks;
	}
	/**
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}
	/**
	 * @param startRow the startRow to set
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	/**
	 * @return the endRow
	 */
	public int getEndRow() {
		return endRow;
	}
	/**
	 * @param endRow the endRow to set
	 */
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
	
}
