package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.Product;

public class CreateNewOrderView extends View {
	
	public static final String ORDER_TAG = "order";

	public CreateNewOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String interactGet(Collection<Product> collection) {
		this.model.addAttribute("products", collection);
		setStatusOk();
		return "createNewOrder";
	}
	
	public String interactPost(Order order) {
		this.session.removeAttribute(ORDER_TAG);
		this.model.addAttribute(ORDER_TAG, order);
		setStatusOk();
		return "showOrder";
	}
}