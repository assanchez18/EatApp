package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.views.helpers.EmailHelperView;

public class ProductView extends View {

	public static final String TAG_USER_ID = "userId";
	private static final String TAG_PRODUCT = "product";
	public static final String TAG_PRODUCT_ID = "productId";
	public static final String TAG_PRODUCT_NAME = "productName";
	public static final String TAG_PRODUCT_DESCRIPTION = "productDescription";
	public static final String TAG_PRODUCT_PRICE = "productPrice";
	public static final String TAG_PRODUCT_PRIORITY = "productPriority";
	
	private final String TAG_ORDERS = "orders";
	private final String MSG_PRODUCT_MODIFICATION_ERROR = "Ha habido un problema modificando el producto";
	protected final String VIEW_MANAGE_PRODUCT_STATUS_ORDERS = "manageProductStatusOrders";

	private EmailHelperView emailHelper;
	
	public ProductView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.emailHelper = new EmailHelperView(req);
	}

	public ProductView(HttpServletResponse res) {
		super(res);
	}
	
	public ProductView(Model model, HttpServletResponse res) {
		super(model, res);
	}

	public int getUserId() {
		return Integer.decode(this.request.getParameter(TAG_USER_ID));
	}

	public int getProductId() {
		return Integer.decode(this.request.getParameter(TAG_PRODUCT_ID));
	}

	public String error() {	
		return returnErrorWithMessageAndErrorCode(MSG_PRODUCT_MODIFICATION_ERROR, HttpServletResponse.SC_BAD_REQUEST);
	}

	protected void prepareModel(Collection<Order> orders) {
		this.model.addAttribute(TAG_ORDERS, orders);
		setStatusOk();
	}

	public String manageProductStatus(Collection<Order> orders) {
		this.prepareModel(orders);
		return VIEW_MANAGE_PRODUCT_STATUS_ORDERS;
	}

	public String getEmail() {
		return this.emailHelper.getEmail();
	}

	public void prepareProductModel(Product product) {
		this.model.addAttribute(TAG_PRODUCT, product);
	}

	public String getProductName() {
		return this.request.getParameter(TAG_PRODUCT_NAME);
	}

	public String getProductDescription() {
		return this.request.getParameter(TAG_PRODUCT_DESCRIPTION);
	}
	
	public double getProductPrice() {	
		return Double.parseDouble(this.request.getParameter(TAG_PRODUCT_PRICE));
	}

	public int getProductPriority() {
		return Integer.parseInt(this.request.getParameter(TAG_PRODUCT_PRIORITY));
	}
}
