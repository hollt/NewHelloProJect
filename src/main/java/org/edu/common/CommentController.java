package org.edu.common;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edu.comment.CommentDAO;
import org.edu.comment.CommentVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommentController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		
		// 요청정보에서 req 에서 파라메터로 넘어온 값을 읽음
		String cmd = req.getParameter("cmd");

		CommentDAO dao = new CommentDAO();
		Gson gson = new GsonBuilder().create();
		PrintWriter out = resp.getWriter();

		if (cmd.equals("selectAll")) { /// 리스트 보여주기.
			List<CommentVO> list = dao.selectAll();

			out.print(gson.toJson(list));

		} else if (cmd.equals("insert")) {
			String name = req.getParameter("name");
			String content = req.getParameter("content");

			CommentVO vo = dao.insertComment(name, content);
			// {"retCode": "Success","retVal": vo}
			// {"retCode": "Fail","retVal": "에러발생"}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			if (vo == null) {
				resultMap.put("retCode", "Fail");
				resultMap.put("retVal", "에러발생");
			} else {
				resultMap.put("retCode", "Success");
				resultMap.put("retVal", vo);
			}
			out.print(gson.toJson(resultMap));
		}
	}

}
