package com.volleyball.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class GetDropdownDataServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		String parentId = request.getParameter("parentId");
		if(parentId == null || parentId==""){
			parentId = "0";//如果传的父结点为空，则默认赋值为中国的父结点，也就是每一级查询所有省份。
		}
		Connection conn = null;
		String json = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/try" , "root" , "qq112233");
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select region_id,region_name from region where parent_id = "+parentId);
            ArrayList rsList = new ArrayList();
            Map map = null;
            while(rs.next()){
            	map = new HashMap();
            	map.put("id", rs.getInt(1));
            	map.put("name", rs.getString(2));
            	rsList.add(map);
            }
            rs=null;
            Gson gson = new Gson();
            json = gson.toJson(rsList);
            System.out.println("json="+json);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
        	if(conn != null){
        		try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
		response.setCharacterEncoding("UTF-8");//防止出现中文乱码现象
		response.getWriter().print(json);
	}
}
