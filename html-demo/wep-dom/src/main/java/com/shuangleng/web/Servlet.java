package com.shuangleng.web;

import com.shuangleng.mapper.UserMapper;
import com.shuangleng.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/demo1")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//                加载mysql核心配置文件获取sql...Factory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession来执行sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
//         获取代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User users = mapper.selectAll(username,password);
        sqlSession.close();
////      获取字符输出流
//        获取在之前调用
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
//        这里给用户响应
//       writer.write("222");

      if (users != null){
           writer.print("对了呀");
        }else {
            writer.print("错了猪头");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
