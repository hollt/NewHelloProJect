package org.edu.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet{
	
	//요청url, 실제실행할 서블릿 
	HashMap<String,Controller> list = null;
	//init() - > service() -> destroy() : 서블릿의 생명주기.
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		list = new HashMap<String , Controller>();
		
		list.put("/home.do", new HomeController());
		list.put("/login.do", new LoginController());
		list.put("/memberList.do", new MemberListController());
		list.put("/requestInfo.do",new RequestInfoController());
		
		//게시글 관련
		list.put("/commentServ.do", new CommentController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI(); // localhost/edu/home.do 페이지를 요청
//		StringBuffer url = req.getRequestURL();
		String contextPath =req.getContextPath(); // ContextPath:최상위정보/edu 읽어옴
		String path = uri.substring(contextPath.length()); // /home.do
		Controller control = list.get(path);
		
//		System.out.println("url: " + url+ ", uri: " + uri + ", context: " + contextPath + ", path" + path + ", control:" + control);
		try {
			control.execute(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		
	}
}
