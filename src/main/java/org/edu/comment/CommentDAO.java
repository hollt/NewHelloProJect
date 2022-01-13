package org.edu.comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends DAO {
	// 글등록
	public CommentVO insertComment(String name, String content) {
		String sql = "select value from id_repository where name = 'COMMENT'";
		String insertSql = "insert into comments values(?,?,?)";
		String updateSql = "update id_repository set value = ? where name ='COMMENT' ";

		CommentVO comment = null;
		connect();
		try {
			//시퀀스 가져오기
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int seq = -1;
			if (rs.next()) {
				seq = rs.getInt("value");

			}
			//한건입력
			psmt = conn.prepareStatement(insertSql);
			psmt.setInt(1, seq);
			psmt.setString(2, name);
			psmt.setString(3, content);
			int r = psmt.executeUpdate();
			System.out.println(r + "입력됨");

			// 비정상적으로 처리.
			
				comment = new CommentVO();
				comment.setId(seq);
				comment.setName(name);
				comment.setContent(content);
				
				psmt = conn.prepareStatement(updateSql);
				psmt.setInt(1, ++seq);
				
				r = psmt.executeUpdate();
				
				if(r<1) {
					return null;
				}
				//정상적으로 완료되었으면
				return comment;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;

	}

	// 전체목록가져오기
	public List<CommentVO> selectAll() {
		String sql = "select * from comments order by 1";
		connect();
		List<CommentVO> list = new ArrayList<CommentVO>();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				CommentVO vo = new CommentVO();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("Content"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;

	}
}
