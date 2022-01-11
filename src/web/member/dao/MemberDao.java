package web.member.dao;

import web.member.been.MemberBean;

public interface MemberDao {
	
	public MemberBean selectByUsernameAndPassword(String username, String password);
}
