package common;

import javax.servlet.http.HttpServletRequest;


public final class FenYeUtil {
	public static Page getPager(HttpServletRequest request,           // 定义pager对象，用于传到页面
			int totalRows) {
		Page pager = new Page(totalRows);                                        // 从Request对象中获取当前页号
		String currentPage = request.getParameter("currentPage");     // 如果当前页号为空，表示为首次查询该页						                                
		if (currentPage != null) {		                                           // //如果不为空，则刷新page对象，输入当前页号等信息
			Integer cp=Integer.parseInt(currentPage);
			pager.refresh(cp);
			pager.setCurrentPage(cp);
		}                                                                        // 获取当前执行的方法，首页，前一页，后一页，尾页。
		String pagerMethod = request.getParameter("pageMethod");
		if (pagerMethod != null) {
			if (pagerMethod.equals("first")) {
				pager.first();
			} else if (pagerMethod.equals("previous")) {
				pager.previous();
			} else if (pagerMethod.equals("next")) {
				pager.next();
			} else if (pagerMethod.equals("last")) {
				pager.last();
			}
		}
		pager.setRownumber((pager.getCurrentPage()-1)*pager.getPageSize()+1);  //设置序号
		return pager;
	}
}
