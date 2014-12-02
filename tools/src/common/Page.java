package common;
/**
 * page类
 * @author zhoushiyang
 *@totalRows 总行数
 * @pageSize 每页显示的行数
 * @currentPage 当前页号
 * @totalPages 总页数
 * @startRow 当前页在数据库中的起始行
 * @rownumber 序号
 */
public class Page {

	private int  totalRows;//总行数
	private int  pageSize=15;//每页显示几行
	private int  currentPage;//当前页码
	private int  totalPages;//总页数
	private int  startRow;//当前也数据库中的起始行
	private int  rownumber=1;
	private String  goOption;//跳转页
	
	public Page(int _totalRows) {
		totalRows = _totalRows;
		totalPages = totalRows / pageSize;// 总页数
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}
		if (totalPages <= 0) {
			totalPages = 1;
		}
		currentPage = 1;
		startRow = 0;
	}
	
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		startRow = (currentPage - 1) * pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getRownumber() {
		return rownumber++;
	}
	public void setRownumber(int rownumber) {
		this.rownumber = rownumber;
	}
	
	public void first(){
		currentPage=1;
		startRow=0;
		
	}
	public void previous(){
		if(currentPage==1){
			return;
		}
		currentPage--;
		startRow=(currentPage-1)*pageSize;
	}
	public void next() {
		if (currentPage < totalPages) {
			currentPage++;
		}
		startRow = (currentPage - 1) * pageSize;
	}

	public void last() {
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize;
	}
   
	public void refresh(int _currentPage) {
		currentPage = _currentPage;
		if (currentPage > totalPages) {
			last();
		}
	}
	/*
	 *跳转页 方法
	 */
	public String getGoOption() {
		StringBuffer sb = new StringBuffer();
		String strSel = "";
		for (int i = 1; i <= this.totalPages; i++) {
			if (i == this.currentPage) {
				strSel = " selected=\"selected\"";
			} else {
				strSel = "";
			}
			sb.append("<option value=\"" + i + "\"" + strSel + ">" + i
					+ "</option>");
		}
		this.goOption = sb.toString();
		return this.goOption;
	}

}
