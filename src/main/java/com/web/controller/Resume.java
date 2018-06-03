package com.web.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.Control.DeleteResume;
import com.Control.DownloadResume;
import com.Control.Server;
import com.Control.UploadResume;

/**
 * Resume类
 * 
 * @version 1.0
 * @author James
 */
@Controller
@RequestMapping("/resume")
public class Resume {

    @RequestMapping("/uploadResume")
    @ResponseBody
    public boolean uploadResume(MultipartFile file, HttpServletRequest request, HttpSession session) throws Exception {
	if (file != null) {
	    int fid = Server.generateId();
	    String fname = request.getParameter("name");
	    int user_uid = Integer.parseInt(request.getParameter("username"));
	    
	    File directory = new File("/Users/James/Desktop");
	    File tempFile = File.createTempFile(String.valueOf(fid), ".doc", directory);
	    file.transferTo(tempFile);
	    
	    return UploadResume.storeFile(fid, fname, tempFile, user_uid);
	}
	else {
	    return false;
	}
    }
 
    @RequestMapping("/deleteResume")
    @ResponseBody
    public boolean deleteResume(HttpServletRequest request) throws Exception {
	int fid = Integer.parseInt(request.getParameter("templateId"));
	if (!DeleteResume.isExistFile(fid)) {
	    return false;
	}
	else {
	    String realPath = request.getSession().getServletContext().getRealPath("/");
	    String trueFileName = "resume/" + fid + ".doc";
	    String path = realPath + "/" + trueFileName;
	    return DeleteResume.deleteFile(fid, path);
	}
    }
    
    @RequestMapping("/getAllResume")
    @ResponseBody
    public JSONArray getAllResume(HttpServletRequest request) throws Exception {
	ArrayList<String> files = (ArrayList<String>) Server.getAllFile();
	JSONArray jsonArray = new JSONArray();
	for (String file : files) {
	    String []temp = file.split(" ");
	    int fid = Integer.parseInt(temp[0]);
	    String realPath = request.getSession().getServletContext().getRealPath("/");
	    String trueFileName = "resume/" + fid + ".doc";
	    String path = realPath + "/" + trueFileName;
	    DownloadResume.readFile(path, fid); // return boolean
	    String fname = temp[1];
	    int user_uid = Integer.parseInt(temp[2]);
	    String userInfo = Server.getUser(user_uid);
	    temp = userInfo.split(" ");
	    String nickname = temp[0];
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("templateId", fid);
	    jsonObject.put("name", fname);
	    jsonObject.put("nickname", nickname);
	    jsonObject.put("downloadPath", fid + ".doc");
	    jsonArray.add(jsonObject);
	}
	return jsonArray;
    }
    
    @RequestMapping("/getUserResume")
    @ResponseBody
    public JSONArray getUserResume(HttpServletRequest request) throws Exception {
	int user_uid = Integer.parseInt(request.getParameter("username"));
	ArrayList<String> files = (ArrayList<String>) Server.getUserFile(user_uid);
	JSONArray jsonArray = new JSONArray();
	for (String file : files) {
	    String []temp = file.split(" ");
	    int fid = Integer.parseInt(temp[0]);
	    String fname = temp[1];
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("templateId", fid);
	    jsonObject.put("name", fname);
	    jsonArray.add(jsonObject);
	}
	return jsonArray;
    }
}
