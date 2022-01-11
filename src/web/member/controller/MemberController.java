package web.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.member.been.MemberBean;
import web.member.service.implement.MemberServiceImplement;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		Gson gson = new Gson();
		
		try (
				BufferedReader bufferedReader = request.getReader();				
				){			
			
			MemberBean memberBean = gson.fromJson(bufferedReader, MemberBean.class);
			
			MemberServiceImplement memberServiceImplement = new MemberServiceImplement();
			MemberBean memberBean2 = memberServiceImplement.login(memberBean.getUsername(), memberBean.getPassword());
			
			if (memberBean2 != null) {
				String jsonString = gson.toJson(memberBean2);
				response.getWriter().print(jsonString);				
			}else {
				memberBean.setMessage("帳號或密碼錯誤");
				String jsonString2 = gson.toJson(memberBean);
				response.getWriter().print(jsonString2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
