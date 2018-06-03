package com.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Control.Login;
import com.Control.Regist;
import com.Control.Server;

import net.sf.json.JSONObject;

/**
 * User类
 * 
 * @version 1.0
 * @author james
 */
@Controller
@RequestMapping("/user")
public class User {
    ArrayList<Integer> loggedInList = new ArrayList<>();

    @RequestMapping("/login")
    @ResponseBody
    public boolean login(HttpServletRequest request) throws Exception {
	int uid = Integer.parseInt(request.getParameter("username"));
	String password = request.getParameter("password");
	if (!Login.isExistUser(uid)) {
	    return false;
	}
	else {
	    if (Login.isokLogin(uid, password) != null) {
		loggedInList.add(uid);
		return true;
	    }
	    else {
		return false;
	    }
	}
    }

    @RequestMapping("/register")
    @ResponseBody
    public boolean register(HttpServletRequest request) throws Exception {
	int uid = Integer.parseInt(request.getParameter("username"));
	String uname = request.getParameter("nickname");
	String password = request.getParameter("password");
	return Regist.createUser(uid, uname, password);
    }
    
    @RequestMapping("/logout")
    @ResponseBody
    public boolean logout(HttpServletRequest request) throws Exception {
	int uid = Integer.parseInt(request.getParameter("username"));
	if (loggedInList.contains(uid)) {
	    int index = loggedInList.indexOf(uid);
	    loggedInList.remove(index);
	    return true;
	}
	else {
	    return false;
	}
    }
    
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(HttpServletRequest request) throws Exception {
	int uid = Integer.parseInt(request.getParameter("username"));
	String uname = request.getParameter("nickname");
	String password = request.getParameter("password");
	return Server.modifyInfo(uid, uname, password);
    }
    
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public JSONObject getUserInfo(HttpServletRequest request) throws Exception {
	int uid = Integer.parseInt(request.getParameter("username"));
	String userInfo = Server.getUser(uid);
	String[] temp = userInfo.split(" ");
	String nickname = temp[0];
	String manager = temp[1];
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("nickname", nickname);
	jsonObject.put("manager", manager);
	return jsonObject;
    }
    
    @RequestMapping("/isLoggedIn")
    @ResponseBody
    public boolean isLoggedIn(HttpServletRequest request) throws Exception {
	int uid = Integer.parseInt(request.getParameter("username"));
	if (loggedInList.contains(uid)) {
	    return true;
	}
	else {
	    return false;
	}
    }
}
