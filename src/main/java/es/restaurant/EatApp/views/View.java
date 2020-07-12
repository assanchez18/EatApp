package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.facades.HttpServletRequestFacade;
import es.restaurant.EatApp.models.facades.HttpServletResponseFacade;
import es.restaurant.EatApp.models.facades.ModelFacade;
import es.restaurant.EatApp.models.facades.SessionFacade;

public abstract class View {
	
	protected HttpServletRequestFacade request;
	protected SessionFacade session;
	protected HttpServletResponseFacade response;
	protected ModelFacade model;
	
	protected static final String EMAIL_TAG = "email";
	
	public View () {
		this.request = null;
		this.response = null;
		this.session = null;
		this.model = null;
	}
	
	public View(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.request = new HttpServletRequestFacade(req);
		this.session = new SessionFacade(req.getSession());
		this.response = new HttpServletResponseFacade(res);
		this.model = new ModelFacade(model);
	}

	public View(HttpServletRequest req) {
		this.request = new HttpServletRequestFacade(req);
		this.session = new SessionFacade(req.getSession());
		this.response = null;
		this.model = null;
	}
	
	public View(HttpServletRequest req,HttpServletResponse res) {
		this.request = new HttpServletRequestFacade(req);
		this.session = new SessionFacade(req.getSession());
		this.response = new HttpServletResponseFacade(res);
		this.model = null;
	}

	public String getEmail() {
		return this.session.getStringAttribute(EMAIL_TAG);
	};
}
