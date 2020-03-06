package com.company.sattr7.listeners;

import com.company.sattr7.entity.BusinessUser;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.app.Authentication;
import com.haulmont.cuba.security.auth.events.UserLoggedInEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

@Component("sattr7_AuthenticationEventListener")
public class AuthenticationEventListener {
    @Inject
    private Authentication authentication;
    @Inject
    private DataManager dataManager;

    @EventListener
    public void userLoggedIn(UserLoggedInEvent event) {
        if (event.getUserSession().isSystem())
            return;

        BusinessUser bu = authentication.withSystemUser(() ->
                dataManager.load(BusinessUser.class)
                    .query("select e from sattr7_BusinessUser e where e.sysUser = :user")
                    .parameter("user", event.getUserSession().getUser())
                    .view("business-user-with-sysuser")
                    .optional()
                    .orElse(null));

        if (bu != null)
            event.getUserSession().setAttribute("businessUser", bu);
    }


}