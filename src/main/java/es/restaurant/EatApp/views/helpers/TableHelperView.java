package es.restaurant.EatApp.views.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TableHelperView {

	public static final String TAG_TABLE = "table";

	private HttpServletRequest request;
	private HttpSession session;

	public TableHelperView(HttpServletRequest req, HttpSession session) {
		this.request = req;
		this.session = session;
	}

	public Integer getSessionTableCode() {
		return (Integer) this.session.getAttribute(TAG_TABLE);
	}
	
	
	public void setTable(int code) {
		this.session.setAttribute(TAG_TABLE, code);
	}
	
	public void setTable() {
		setTable(getRequestedTableCode());
	}

	public Integer getRequestedTableCode() {
		return Integer.valueOf(this.request.getParameter(TAG_TABLE));
	}
}
