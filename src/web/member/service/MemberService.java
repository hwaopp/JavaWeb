package web.member.service;

import web.member.been.MemberBean;

public interface MemberService {
	
	public MemberBean login(String username, String password);
}
