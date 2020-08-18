package es.restaurant.EatApp.views.helpers;

import javax.servlet.http.HttpServletRequest;

public class ParameterListReader {

	public static final String TAG_IDS = "ids[]";
	private HttpServletRequest request;
	
	public ParameterListReader(HttpServletRequest req) {
		this.request = req;
	}

	public Integer[] getParameterArray(String parameterName) {
		String[] parameters_string = this.request.getParameterValues(parameterName);
		Integer[] parameters = new Integer[parameters_string.length];
		for(int i = 0;i < parameters_string.length;i++)
		{
			try {
				parameters[i] = Integer.parseInt(parameters_string[i]);
			} catch  (NumberFormatException error) {
				return new Integer[0];
			}
		}
		return parameters;
	}

	public Integer[] getIds() {
		return this.getParameterArray(TAG_IDS);
	}
}
