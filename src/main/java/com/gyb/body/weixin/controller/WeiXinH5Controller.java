package com.gyb.body.weixin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.gyb.base.controller.BaseController;
import com.gyb.body.weixin.model.Access_tokenEntity;
import com.gyb.body.weixin.model.UserEntity;
import com.gyb.body.weixin.model.WeiXinEntity;
import com.gyb.body.weixin.service.impl.Access_tokenServiceImp;
import com.gyb.body.weixin.service.impl.WeixinServiceImpl;
import com.gyb.body.weixin.utils.Sign;
import com.gyb.body.weixin.utils.WeiXinUtils;
import com.gyb.config.WechatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;

/**
 * 微信H5页面专用
 */
@Controller
@RequestMapping(value = "H5")
public class WeiXinH5Controller extends BaseController {


	@Autowired
	private Access_tokenServiceImp tokenService;
	@Autowired
	private WeixinServiceImpl weixinService;


	@RequestMapping(value = "/getOpenId")
	@ResponseBody
	public HashMap sendGet(String code, HttpServletRequest request) {
		HashMap<String, Object> hashMap = getReturnMap();
		try {
			String access_token = "";
			String result = weixinService.getOpenId_H5(code);
			System.out.println("result:" + result);
			String openId = "";
			String headimgurl = null;
			// 将json字符串转换为json对象
			JSONObject jsonObject = JSONObject.fromObject(result);
			if (jsonObject.containsKey("access_token")) {
				access_token = jsonObject.getString("access_token");
			}
			if (jsonObject.containsKey("openid")) {
				openId = jsonObject.getString("openid");
			}
			System.out.println("获取到的code值为：" + code + "  --openId 为：" + openId);

			if (openId != null && !openId.equals("")) {
				//增加一次访问量
				//	int x = userService.addVisit();
			}
			//	Access_tokenEntity access_tokenPOJO = tokenService.selectOne();
			//String getAccess_token = GetAccess_token(access_tokenPOJO);
			// 通过access_token和openid获取 subscribe值，判断是否关注该公众号 0：未关注 1：已关注
			// Integer subscribe = WeiXinUtils.getSubscribe(getAccess_token, openId);
			if (!StringUtils.isEmpty(openId)) {
				hashMap.put("openId", openId);
			} else {
				hashMap.put("status", "0");
				hashMap.put("message", "获取用户唯一标识失败");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return hashMap;
	}


	/**
	 * 获取access_token值---判断时
	 * 获取access_token填写client_credential
	 * @return
	 */
	private  String GetAccess_token(Access_tokenEntity access_tokenPOJO) {
		String access_token = null;
		if (access_tokenPOJO != null) {
			Date startDate = access_tokenPOJO.getCreateTime();
			// 存入数据库的时间
			long start = startDate.getTime();
			Date date = new Date();
			// 当前时间
			long end = date.getTime();
			// 时间差
			int cha = (int) ((end - start) / 1000);
			// 在时间段内--->直接使用数据库存储的access_token
			if (cha <= 7200) {
				access_token = access_tokenPOJO.getAccessToken();
			} else {
				// 不再时间段内：说明access_token已经过期--->请求微信接口获取access_token
				access_token = weixinService.getAccess_token();
				access_tokenPOJO.setCreateTime(date);
				access_tokenPOJO.setAccessToken(access_token);
				access_tokenPOJO.setAppId(WechatConfig.appid);
				tokenService.update(access_tokenPOJO);
			}
		} else {
			// 若为空说明是第一次，将access_token存入数据库
			access_tokenPOJO = new Access_tokenEntity();
			access_token = weixinService.getAccess_token();
			access_tokenPOJO.setAccessToken(access_token);
			access_tokenPOJO.setCreateTime(new Date());
			access_tokenPOJO.setAppId(WechatConfig.appid);
			tokenService.insert(access_tokenPOJO);
		}
		return access_token;
	}

	/**
	 * 调取微信接口获取用户信息
	 *
	 * @param userjson:json对象
	 * @return
	 */
	public UserEntity getWeiXinUser(JSONObject userjson) {
		UserEntity userPOJO = new UserEntity();
		String openid = "";
		String nickname = "";
		String sex = "";
		String province = "";
		String city = "";
		String country = "";
		String headimgurl = "";
		String privilege = "";
		String unionid = "";
		if (userjson.containsKey("openid")) {
			openid = userjson.getString("openid");// 用户的唯一标识
			userPOJO.setUserId(openid);
			userPOJO.setId(openid);
			userPOJO.setFlag("1");
		}
		if (userjson.containsKey("nickname")) {
	   /*nickname = userjson.getString("nickname");// 用户昵称
	   userPOJO.setUserName(nickname);*/
		}
     /*  if (userjson.containsKey("sex")) {
	   sex = userjson.getString("sex");// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	   userPOJO.setSex(sex);
	  }
	  if (userjson.containsKey("province")) {
	   province = userjson.getString("province");// 用户个人资料填写的省份
	   userPOJO.setProvince(province);
	  }
	  if (userjson.containsKey("city")) {
	   city = userjson.getString("city");// 普通用户个人资料填写的城市
	   userPOJO.setCity(city);
	  }
	  if (userjson.containsKey("country")) {
	   country = userjson.getString("country");// 国家，如中国为CN
	   userPOJO.setCountry(country);
	  }
	  if (userjson.containsKey("headimgurl")) {
	   headimgurl = userjson.getString("headimgurl");// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	   userPOJO.setHeadimgurl(headimgurl);
	  }
	  if (userjson.containsKey("privilege")) {
	   privilege = userjson.getString("privilege");// 用户特权信息，json
	              // 数组，如微信沃卡用户为（chinaunicom）
	   userPOJO.setPrivilege(privilege);
	  }
	  if (userjson.containsKey("unionid")) {
	   unionid = userjson.getString("unionid");// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	   userPOJO.setUnionid(unionid);
	  }*/
		return userPOJO;
	}

	/**
	 * 微信分享
	 * @param: @param request
	 * @param: @return 参数说明
	 * @return: Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping("/share")
	@ResponseBody
	public Map<String, Object> share(HttpServletRequest request,String url) {
		WeiXinEntity wx = getWinXinEntity(url);
		Map<String, Object> map = new HashMap<String, Object>();
		// String sgture = WeiXinUtils.getSignatrue(wx.getTicket(), wx.getNoncestr(), wx.getTimestamp(), url);
		map.put("sgture", wx.getSignature().trim());//签名
		map.put("timestamp", wx.getTimestamp().trim());//时间戳
		map.put("noncestr",  wx.getNoncestr().trim());//随即串
		map.put("appid",WechatConfig.appid);//你的公众号APPID
		return map;
	}


	public  WeiXinEntity getWinXinEntity(String url) {
		WeiXinEntity wx = new WeiXinEntity();
		Access_tokenEntity access_tokenPOJO = tokenService.selectOne();
		// 获取 access_token
		String access_token = GetAccess_token(access_tokenPOJO);
		String ticket = Get_ticket(access_tokenPOJO);
		// String ticket = WeiXinUtils.getTicket(access_token);
		Map<String, String> ret = Sign.sign(ticket, url);
		wx.setTicket(ret.get("jsapi_ticket"));
		wx.setSignature(ret.get("signature"));//签名
		wx.setNoncestr(ret.get("nonceStr"));//生成签名的随机串
		wx.setTimestamp(ret.get("timestamp"));//生成签名的时间戳
		return wx;
	}

	/**
	 * 获取access_token值---判断时
	 * 获取access_token填写client_credential
	 * @return
	 */
	private  String Get_ticket(Access_tokenEntity access_tokenPOJO) {
		String ticket = null;
		Date startDate = access_tokenPOJO.getTicketCreateTime();
		Date date = new Date();
		// 当前时间
		long end = date.getTime();
		//如果为空 就是第一次
		if(startDate ==null){
			ticket = WeiXinUtils.getTicket(access_tokenPOJO.getAccessToken());
			access_tokenPOJO.setTicket(ticket);
			access_tokenPOJO.setTicketCreateTime(date);
			access_tokenPOJO.setAppId(WechatConfig.appid);
			tokenService.updateTicket(access_tokenPOJO);
		}else{
			// 存入数据库的时间
			long start = startDate.getTime();
			// 时间差
			int cha = (int) ((end - start) / 1000);
			// 在时间段内--->直接使用数据库存储的ticket
			if (cha <= 7200) {
				ticket = access_tokenPOJO.getTicket();
			} else {
				// 不再时间段内：说明ticket已经过期--->请求微信接口获取ticket
				ticket = WeiXinUtils.getTicket(access_tokenPOJO.getAccessToken());
				access_tokenPOJO.setTicketCreateTime(date);
				access_tokenPOJO.setTicket(ticket);
				access_tokenPOJO.setAppId(WechatConfig.appid);
				tokenService.updateTicket(access_tokenPOJO);
			}
		}
		return ticket;
	}

}
	
	
	