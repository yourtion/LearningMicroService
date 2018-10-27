package com.yourtion.micro.user.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourtion.micro.thrift.user.dto.UserDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public abstract class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        var token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            var cookies = request.getCookies();
            for (var c : cookies) {
                if (c.getName().equals("token")) {
                    token = c.getValue();
                }
            }
        }

        UserDTO userDTO = null;
        if (StringUtils.isNotBlank(token)) {
            userDTO = requestUserInfo(token);
        }

        if (userDTO == null) {
            response.sendRedirect("http://127.0.0.1:8082/user/login");
            return;
        }

        login(request, response, userDTO);

        filterChain.doFilter(request, response);
    }

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO);

    private UserDTO requestUserInfo(String token) {
        String url = "http://127.0.0.1:8082/user/authentication";

        var client = HttpClientBuilder.create().build();
        var post = new HttpPost(url);
        InputStream inputStream = null;
        post.addHeader("token", token);
        try {
            var response = client.execute(post);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("request user info failed! statusLine:" + response.getStatusLine());
            }
            var sb = new StringBuffer();
            inputStream = response.getEntity().getContent();
            var temp = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(temp)) > 0) {
                sb.append(new String(temp, len));
            }
            var userDTO = new ObjectMapper().readValue(sb.toString(), UserDTO.class);
            return userDTO;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public void destroy() {

    }
}
