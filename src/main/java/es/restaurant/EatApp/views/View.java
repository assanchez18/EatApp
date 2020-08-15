package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public abstract class View {
	
	protected HttpServletRequest request;
	protected HttpSession session;
	protected HttpServletResponse response;
	protected Model model;

	public static final String TAG_TABLE = "table";
	public static final String TAG_EMAIL = "email";
	protected static final String TAG_ERROR = "error";
	protected static final String VIEW_MAIN_USER = "mainUserView";
	protected static final String VIEW_ERROR = "error";
	protected static final String VIEW_INDEX = "index";
	
	public View () {
		this.request = null;
		this.response = null;
		this.session = null;
		this.model = null;
	}
	
	public View(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.request = req;
		this.session = req.getSession();
		this.response = res;
		this.model = model;
	}

	public View(HttpServletRequest req) {
		this.request = req;
		this.session = req.getSession();
		this.response = null;
		this.model = null;
	}
	
	public View(HttpServletRequest req, HttpServletResponse res) {
		this.request = req;
		this.session = req.getSession();
		this.response = res;
		this.model = null;
	}

	public View(Model model, HttpServletResponse res) {
		this.request = null;
		this.session = null;
		this.response = res;
		this.model = model;
	}

	public View(HttpServletResponse res) {
		this.request = null;
		this.session = null;
		this.response = res;
		this.model = null;
	}

	public String getEmail() {
		return (String) this.session.getAttribute(TAG_EMAIL);
	}

	public Integer getTableCode() {
		return (Integer) this.session.getAttribute(TAG_TABLE);
	}

	protected void setStatusOk() {
		this.response.setStatus(HttpServletResponse.SC_OK);
	}
	
	public String returnErrorWithMessage(String message, int code, String view) {
		this.addError(message);
		this.response.setStatus(code);
		return view;
	}
	
	private void addError(String msg) {
		this.model.addAttribute(TAG_ERROR, msg);
	}
}
