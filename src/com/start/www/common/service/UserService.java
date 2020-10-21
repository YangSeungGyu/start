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
		 * N : ����� ����
		 * S : ������ ����
		 * L : �н�����5ȸ �̻� Ʋ�� ��� ����
		 * F : �н����� Ʋ��
		 * T : �α��� ����
		 */
		String resultType= "N";
		
		UserBean userInfo = userDao.loginAction(param);
		
		if(userInfo == null){
			//����� ����
			resultType ="N";
		} else if(userInfo.getUseYn() == "N"){
			//������ ����
			resultType ="S";
		}else if(userInfo.getLockYn()=="Y"){
			//�α���5ȸ ����
			resultType ="L";
		}else{
			String checkPassword = SecurityUtil.encryptSHA256(param.getUserPw());
			if(!checkPassword.equals(userInfo.getUserPw())){
				//�α��� ���� cnt����
				int failCnt = userInfo.getFailCnt()+1;
				userInfo.setFailCnt(failCnt);
				if(failCnt >= 5){
					userInfo.setLockYn("Y");
				}
				userDao.UpdateFailCnt(userInfo);
				resultType ="F";
			}else{
				//�α���
				//���� Ƚ�� �ʱ�ȭ
				int failCnt = 0;
				userInfo.setFailCnt(failCnt);
				userInfo.setLockYn("N");
				userDao.UpdateFailCnt(userInfo);
				
				userInfo.setAccessType("L");
				
				//�α��� �α� �߰�
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
