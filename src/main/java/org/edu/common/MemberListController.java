package org.edu.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberListController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String method = req.getMethod();
		String result = "";
		if(method.equals("GET")) {
			result = doGet();
		}else if(method.equals("POST")) {
			result = doPost();
		}
		
		// [{"id":1,"name":"Hong"},{"id":2,"name":"Park"},{"id":3,"name":"Kim"}]
		
		String id =req.getParameter("id"); //파라메터의값을 읽겠습니다
		String name = req.getParameter("name");
		String score = req.getParameter("score");
		
		resp.setContentType("text/json;charset=utf-8");
		String json = "[{\"id\":1,\"name\":\"Hong\"},{\"id\":2,\"name\":\"Park\"},{\"id\":3,\"name\":\"Kim\"},{\"id\":"+ id +",\"name\":\""+ name +"\"},{\"id\":5,\"name\":\""+result+"\"}]";
		resp.getWriter().print(json);
	}
	
	public String doGet() {
		return "Get방식 호출";
	}
	public String doPost() {
		return "Post방식 호출";
	}

}
