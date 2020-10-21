package com.start.www.common.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.start.www.common.bean.ParamBean;
import com.start.www.common.bean.UserBean;
import com.start.www.common.dao.CommonDao;
import com.start.www.common.dao.UserDao;
import com.start.www.util.SecurityUtil;

@Service
public class UserService {
	
	private UserDao userDao;
	private CommonDao commonDao;

	@Inject
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Inject
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	
	public int checkId(ParamBean param){
		return userDao.checkId(param);
	}

	public int joinUserAction(UserBean param){
		
        String newPassword = SecurityUtil.encryptSHA256(param.getUserPw());
        param.setUserPw(newPassword);
        
        String newResidentNum = SecurityUtil.encryptSHA256(param.getResidentNum());
        param.setResidentNum(newResidentNum);
		
		return userDao.joinUserAction(param);
	}
	
	
	public String loginAction(UserBean param, HttpSession session){
		
		/* resultType
		 * N : 사용자 없음
		 * S : 정지된 계정
		 * L : 패스워드5회 이상 틀려 잠긴 계정
		 * F : 패스워드 틀림
		 * T : 로그인 성공
		 */
		String resultType= "N";
		
		UserBean userInfo = userDao.loginAction(param);
		
		if(userInfo == null){
			//사용자 없음
			resultType ="N";
		} else if(userInfo.getUseYn() == "N"){
			//정지된 계정
			resultType ="S";
		}else if(userInfo.getLockYn()=="Y"){
			//로그인5회 실패
			resultType ="L";
		}else{
			String checkPassword = SecurityUtil.encryptSHA256(param.getUserPw());
			if(!checkPassword.equals(userInfo.getUserPw())){
				//로그인 실패 cnt증가
				int failCnt = userInfo.getFailCnt()+1;
				userInfo.setFailCnt(failCnt);
				if(failCnt >= 5){
					userInfo.setLockYn("Y");
				}
				userDao.UpdateFailCnt(userInfo);
				resultType ="F";
			}else{
				//로그인
				//실패 횟수 초기화
				int failCnt = 0;
				userInfo.setFailCnt(failCnt);
				userInfo.setLockYn("N");
				userDao.UpdateFailCnt(userInfo);
				
				userInfo.setAccessType("L");
				
				//로그인 로그 추가
				session.setAttribute("userId", userInfo.getUserId());
				session.setAttribute("userNicNm", userInfo.getUserNicNm());
				session.setAttribute("userType", userInfo.getUserType());
				session.setAttribute("showInfoLv", userInfo.getShowInfoLv());
				
				resultType ="T";
			}
		}
		return resultType;
	}

	
public void logoutAction(UserBean param, HttpSession session) {
	
	session.setAttribute("userId", null);
	session.setAttribute("userNicNm", null);
	session.setAttribute("userType", null);
	session.setAttribute("showInfoLv", null);
	}
	
	

	
}
