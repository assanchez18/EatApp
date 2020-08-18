package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import es.restaurant.EatApp.views.helpers.TableHelperView;

public class RegisterInTableView extends View {
	
	private static final String MSG_CODE_ERROR = "El código escaneado es incorrecto";
	public static final String VIEW_REGISTRY_IN_TABLE = "registryInTable";
	
	private TableHelperView tableHelper;
	
	public RegisterInTableView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.tableHelper = new TableHelperView(this.request, this.session);
	}
	
	public String register() {
		this.tableHelper.setTable();
		setStatusOk();
		return VIEW_MAIN_USER;
	}
	
	public String errorNotFound() {
		return this.returnErrorWithMessage(MSG_CODE_ERROR, HttpServletResponse.SC_NOT_FOUND, VIEW_REGISTRY_IN_TABLE);
	}
	
	public int getTableCode() {
		return this.tableHelper.getRequestedTableCode();
	}

	public String interact() {
		return VIEW_REGISTRY_IN_TABLE;
	}

}
