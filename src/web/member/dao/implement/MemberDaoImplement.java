package web.member.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.member.been.MemberBean;
import web.member.dao.MemberDao;

public class MemberDaoImplement implements MemberDao {
	private DataSource datasource;

	public MemberDaoImplement() {
		try {
			datasource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public MemberBean selectByUsernameAndPassword(String username, String password) {
		String sql = "select * from MEMBER where USERNAME = ? and PASSWORD = ?";

		try (Connection connection = datasource.getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				if (resultSet.next()) {
					MemberBean memberBean = new MemberBean();
					memberBean.setId(resultSet.getInt("ID"));
					memberBean.setUsername(resultSet.getString("USERNAME"));
					memberBean.setPassword(resultSet.getString("PASSWORD"));
					memberBean.setNickname(resultSet.getString("NICKNAME"));
					memberBean.setPass(resultSet.getBoolean("PASS"));
					memberBean.setLastUpdateDate(resultSet.getTimestamp("LAST_UPDATE_DATE"));
					return memberBean;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("A");
		return null;
	}

}
