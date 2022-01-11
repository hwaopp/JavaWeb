package web.member.service.implement;

import web.member.been.MemberBean;
import web.member.dao.implement.MemberDaoImplement;
import web.member.service.MemberService;

public class MemberServiceImplement implements MemberService{

	@Override
	public MemberBean login(String username, String password) {

        if (username == null || username.length() == 0){
        	System.out.println("B");
        	return null;
        }
        if (password == null || password.length() == 0){
        	System.out.println("C");
        	return null;
        }
        
        MemberDaoImplement memberDaoImplement = new MemberDaoImplement();
        MemberBean memberBean = memberDaoImplement.selectByUsernameAndPassword(username, password);
		
		return memberBean;
	}

}
