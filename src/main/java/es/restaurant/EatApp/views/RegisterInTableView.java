package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import es.restaurant.EatApp.views.helpers.EmailHelperView;
import es.restaurant.EatApp.views.helpers.TableHelperView;

public class RegisterInTableView extends View {
	
	private final String MSG_CODE_ERROR = "El código escaneado es incorrecto";
	public static final String VIEW_REGISTRY_IN_TABLE = "registryInTable";
	
	private TableHelperView tableHelper;
	private EmailHelperView emailHelper;
	
	public RegisterInTableView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.tableHelper = new TableHelperView(req);
		this.emailHelper = new EmailHelperView(req);
	}
	
	public String register() {
		this.tableHelper.setTable();
		setStatusOk();
		return VIEW_MAIN_USER;
	}
	
	public String errorWrongTable() {
		this.addError(MSG_CODE_ERROR);
		this.response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return VIEW_REGISTRY_IN_TABLE;
	}
	
	public int getTableCode() {
		return this.tableHelper.getRequestedTableCode();
	}

	public String getEmail() {
		return this.emailHelper.getEmail();
	}

	public String showRegisterInTableForm() {
		return VIEW_REGISTRY_IN_TABLE;
	}
}
