package es.restaurant.EatApp.GeneralControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface ControllerInterface {
	public String control(Model model, HttpServletRequest req, HttpServletResponse res);
}
