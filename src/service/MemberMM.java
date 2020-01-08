package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDao;
import bean.Forward;

public class MemberMM {
	HttpServletRequest request;
	HttpServletResponse response;
	
	
	public MemberMM(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}


	public Forward access() {
		Forward fw=new Forward();
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		MemberDao mDao=new MemberDao();
		int result=mDao.access(id,pw);	//1:성공, -1:id부재,  0:pw부재
		mDao.close();
		if(result==-1) {
			System.out.println("id가 존재하지 않아");
			request.setAttribute("msgAccess", "id존재하지 않아요!");
		}
		else if(result==0){
			System.out.println("pw가 틀렸어");
			request.setAttribute("msgAccess", "pw가 틀립니다.");
		}
		else {//로그인 성공시 
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
		}
		fw.setPath("main.jsp");
		fw.setRedirect(false);
		return fw;
	}


	public Forward joinfrm() {
		Forward fw=new Forward();
		fw.setPath("JoinForm.jsp");
		fw.setRedirect(false);
		return fw;
	}


	public Forward logout() {
		HttpSession session=request.getSession();
		session.invalidate();
		Forward fw=new Forward();
		fw.setPath("index.jsp");
		fw.setRedirect(true);
		return fw;
	}


	public Forward home() {
		Forward fw=new Forward();
		fw.setPath("main.jsp");
		fw.setRedirect(false);
		return fw;
	}


	public Forward petsittersearch() {
		Forward fw=new Forward();
		fw.setPath("petsittersearch.jsp");
		fw.setRedirect(false);
		return fw;
	}
	
	
	
	

}