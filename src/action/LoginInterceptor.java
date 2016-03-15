package action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		final String not_login = "not_login"; 
    	
    	String role = (String)invocation.getInvocationContext().getSession().get("user");
    	if(role == null) return not_login;
    	else {
    		return invocation.invoke();
		}
	}

}
