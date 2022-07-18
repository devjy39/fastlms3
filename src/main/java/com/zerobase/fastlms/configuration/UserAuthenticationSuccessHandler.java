package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.service.LoginHistoryService;
import com.zerobase.fastlms.util.ClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class UserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    private final LoginHistoryService loginHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        System.out.println(request.getHeader("user-agent"));
        System.out.println(ClientUtil.getIp(request));
        System.out.println(request.getParameter("username"));

        loginHistoryService.saveLoginHistory(request.getParameter("username"),
                ClientUtil.getIp(request), request.getHeader("user-agent"));

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
