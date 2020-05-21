package com.wey.ten.era.rbac.system.service.impl;

import java.util.*;

import cn.hutool.core.lang.Assert;
import com.wey.ten.era.common.utils.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wey.ten.era.common.model.PageResult;
import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.Md5Util;
import com.wey.ten.era.common.utils.ResultObject;
import com.wey.ten.era.rbac.system.dao.UserInfoDao;
import com.wey.ten.era.rbac.system.entity.EnterpriseInfo;
import com.wey.ten.era.rbac.system.entity.UserInfo;
import com.wey.ten.era.rbac.system.service.EnterpriseInfoService;
import com.wey.ten.era.rbac.system.service.UserInfoService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wangcong
 * @since 2020-05-08
 */
@Service
@Transactional(readOnly = false)
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private EnterpriseInfoService enterpriseInfoService;

	@Autowired
	private JavaMailSender mailSender; //自动注入的Bean
	@Value("${spring.mail.username}")
	private String Sender; //读取配置文件中的参数


	/**
	 * 用户详情
	 * @param userInfo
	 * @return
	 */

	@Override
	public ResultObject detailUserInfo(Integer userId) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		UserInfo userInfo= userInfoDao.detailUserInfo(userId);
		//获取企业信息
		getEnterpriseInfo(userInfo);
		resultObject.setData(userInfoDao.detailUserInfo(userId));
		return resultObject;
	}

	private void getEnterpriseInfo(UserInfo userInfo) {
		if(userInfo!=null){
			Integer isParent= userInfo.getIsParent();
			if(isParent!=null && isParent!=0){
				//子账号，查询身份信息需要通过 affiliatedCompany
				userInfo.setEnterpriseInfo((EnterpriseInfo)enterpriseInfoService.detailEnterpriseInfo(userInfo.getAffiliatedCompany()).getData());
			}else{
				//主账号直接通过USERID查询
				userInfo.setEnterpriseInfo((EnterpriseInfo)enterpriseInfoService.detailEnterpriseInfoByUserId(userInfo.getUserId()).getData());
			}
		}
	}

	/**
	 * 添加用户(注册)
	 * @param userInfo
	 * @return
	 */
	@Override
	public ResultObject saveUserInfo(Map<String,String> mapParams) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		userInfoDao.saveUserInfo(mapParams);
		return resultObject;
	}

	/**
	 * 删除用户
	 * @param userInfo
	 * @return
	 */
	@Override
	public ResultObject delUserInfo(Integer userId) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		userInfoDao.delUserInfo(userId);
		return resultObject;
	}

	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	@Override
	public ResultObject updateUserInfo(Map<String,String> mapParams) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		userInfoDao.updateUserInfo(mapParams);
		return resultObject;
	}

	@Override
	public boolean verificationPhoneOnly(String phoneNumber){
		int count= userInfoDao.verificationPhoneOnly(phoneNumber);
		if(count>0)return true; else return false;
	}

	@Override
	public boolean verificationEmailOnly(String email){
		int count= userInfoDao.verificationEmailOnly(email);
		if(count>0)return true; else return false;
	}

	@Override
	public ResultObject updateAffiliatedCompany(Map<String,String> mapParams) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		if(mapParams.containsKey("affiliatedCompany")&&!StringUtils.isEmpty(mapParams.get("affiliatedCompany"))){
			//绑定
			mapParams.put("isParent", "1");
			UserInfo userinfo=userInfoDao.detailUserInfo(Integer.valueOf(mapParams.get("userId")));
			if(userinfo!=null && userinfo.getAffiliatedCompany()!=null && userinfo.getAffiliatedCompany()!=0){
				return new ResultObject(ErrorCode.BIND_FAIL_NO_PERMISSION);
			}
		}else{
			//解绑
			mapParams.put("isParent", "0");
			mapParams.put("affiliatedCompany", null);
		}
		userInfoDao.updateAffiliatedCompany(mapParams);
		return resultObject;
	}

	@Override
	public List<UserInfo> findList(Map<String, Object> param) {
		PageResult<UserInfo> pageResult = findPage(param);
		if (pageResult != null) {
			return pageResult.getData();
		}
		return null;
	}

	@Override
	public PageResult<UserInfo> findPage(Map<String, Object> param) {
		long total = 0;
		// 分页对象
		Page<UserInfo> pager = null;
		List<UserInfo> userInfos = null;
		// Page当前页 limit
		Integer page = MapUtils.getInteger(param, "page");
		Integer limit = MapUtils.getInteger(param, "limit");
		if (page != null && limit != null) {
			pager = new Page<UserInfo>(page, limit);
			userInfos = userInfoDao.findList(pager, param);
			// 总条数
			total = pager.getTotal();
		}else {
			userInfos = userInfoDao.findList(pager, param);
		}
		if(userInfos!=null&&userInfos.size()>0){
			for (int i = 0; i < userInfos.size(); i++) {
				UserInfo userInfo=userInfos.get(i);
				getEnterpriseInfo(userInfo);
				userInfos.set(i, userInfo);
			}

		}
		// 构建一个对象
		return PageResult.<UserInfo>builder().data(userInfos).code(0).count(total).build();
	}

	@Override
	public ResultObject getUserByUserId(Integer userId) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		resultObject.setData(userInfoDao.getUserByUserId(userId));
		return resultObject;
	}

	@Override
	public ResultObject sendSimpleMail(String email,String message) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS); ;
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(Sender);
		simpleMailMessage.setTo(email); //自己给客户发送邮件
		simpleMailMessage.setSubject("主题：注册验证码");//主题：简单邮件
		simpleMailMessage.setText(message);//测试邮件内容
		try {
			mailSender.send(simpleMailMessage);
			// 发送成功后存到Redis
		}catch (Exception e) {
			e.printStackTrace();
			return new ResultObject(ErrorCode.EMAIL_SEND_FAILURE);
		}

		return resultObject;
	}

	@Override
	public ResultObject modifyPassword(Map<String, String> params) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		userInfoDao.modifyPassword(params);
		return resultObject;
	}

	@Override
	public UserInfo checkLoginAccount(Integer loginType, String userName) {
		String[] loginTypeArr = new String[] {"1","2","3"};
		UserInfo userInfo = null;
		if(loginType != null && userName != null) {
			String loginTypeStr = loginType.toString();
			if(StringUtils.inStringIgnoreCase(loginTypeStr, loginTypeArr)) {
				userName = userName.trim();
				if (loginType == 1) {
					userInfo = userInfoDao.getByAccount(userName);
				} else if (loginType == 2) {
					userInfo = userInfoDao.getByMobile(userName);
				} else if (loginType == 3) {
					userInfo = userInfoDao.getByEmail(userName);
				}
			}
		}
		return userInfo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveInfo(UserInfo userInfo, boolean saveFlag) {
		Assert.notNull(userInfo, "userInfo not null");
		int result = 0;
		if (userInfo != null) {
			boolean updateFlag = false;
			// 需要校验的字段
			Integer userId = userInfo.getUserId();
			String account = userInfo.getAccountNumber();
			String email = userInfo.getEmail();
			String mobile = userInfo.getPhoneNumber();
			UserInfo query = null;
			EnterpriseInfo enterpriseInfo = null;
			if (!StringUtils.isEmpty(Arrays.asList(account, email, mobile))) {
				if (userId != null && userId.intValue() > 0) {
					query = userInfoDao.selectById(userId);
					updateFlag = true;
				}
				query = userInfoDao.getByAccount(account);
				// 如果是更新也不需要校验
				if ((query != null && saveFlag)
						|| (query != null && !saveFlag && userId != null
						&& query.getUserId().intValue() != userId.intValue())) {
					return result;
				}
				query = userInfoDao.getByEmail(email);
				if ((query != null && saveFlag)
						|| (query != null && !saveFlag && userId != null
						&& query.getUserId().intValue() != userId.intValue())) {
					return result;
				}
				query = userInfoDao.getByMobile(mobile);
				if ((query != null && saveFlag)
						|| (query != null && !saveFlag && userId != null
						&& query.getUserId().intValue() != userId.intValue())) {
					return result;
				}
				Integer isParent = userInfo.getIsParent();
				final Integer parentId = userInfo.getAffiliatedCompany();
				if (isParent != null && isParent == 1) {
					// 子账号检查父账号是否存在
					enterpriseInfo = enterpriseInfoService.getById(parentId);
					if (enterpriseInfo == null) {
						return result;
					}
				}
				// 密码加密处理
				String password = userInfo.getPassword();
				if (!StringUtils.isBlank(password)) {
					userInfo.setPassword(Md5Util.string2MD5(password));
				}
				result = 1;
			}
			if (saveFlag && result > 0) {
				// 说明是主账号
				if (enterpriseInfo == null) {
					enterpriseInfo = new EnterpriseInfo();
					// 这里写插入信息
					enterpriseInfoService.save(enterpriseInfo);
				}
				if (updateFlag) {
					userInfo.setUpdateTime(new Date());
					userInfoDao.updateById(userInfo);
				} else {
					userInfo.setCreateTime(new Date());
					userInfoDao.insert(userInfo);
				}
			}
		}
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int batchSaveInfo(List<UserInfo> userInfos, boolean saveFlag) {
		Assert.notNull(userInfos, "userInfos not null");
		int result = 0;
		if (userInfos != null && userInfos.size() > 0) {
			List<UserInfo> parentList = new ArrayList<UserInfo>();
			List<UserInfo> childrenList = new ArrayList<UserInfo>();
			for (UserInfo userInfo : userInfos) {
				result = saveInfo(userInfo, false);
				userInfo.setCreateTime(new Date());
				userInfo.setUpdateTime(new Date());
				if (result <= 0) {
					return result;
				}
				if (userInfo.getIsParent() == 1) {
					parentList.add(userInfo);
				} else {
					childrenList.add(userInfo);
				}
			}
			//保存子账号
			saveBatch(parentList);
			//保存主账号
			for (UserInfo userInfo : childrenList) {
				result = saveInfo(userInfo, true);
			}
		}
		return result;
	}

	@Override
	public ResultObject modifyBinding(Map<String, String> params) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		userInfoDao.modifyBinding(params);
		return resultObject;
	}

	@Override
	public ResultObject alterPassword(Map<String, String> params) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		userInfoDao.modifyPassword(params);
		return resultObject;
	}

	@Override
	public UserInfo getPasswordByAccount(String account, String type) {
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
		return userInfoDao.getPasswordByAccount(account,type);
	}


}
