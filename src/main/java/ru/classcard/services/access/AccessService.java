package ru.classcard.services.access;

import ru.classcard.model.User;
import ru.classcard.model.UserRole;

import java.util.EnumMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
import static ru.classcard.model.UserRole.ADMIN;
import static ru.classcard.model.UserRole.CLASS_MANAGER;
import static ru.classcard.model.UserRole.CLASS_MEMBER;
import static ru.classcard.services.access.Page.CLASS_MEMBER_MENU;
import static ru.classcard.services.access.Page.CLASS_MANAGER_MENU;
import static ru.classcard.services.access.Page.ADMIN_MENU;

public class AccessService {

    private static final EnumMap<UserRole, List<Page>> ACCESS_RULES = new EnumMap<>(UserRole.class);

    static {
        ACCESS_RULES.put(ADMIN,        asList(ADMIN_MENU));
        ACCESS_RULES.put(CLASS_MEMBER, asList(CLASS_MEMBER_MENU));
        ACCESS_RULES.put(CLASS_MANAGER,  asList(CLASS_MEMBER_MENU, CLASS_MANAGER_MENU));
    }


    public boolean checkIsAccessible(User user, Page accessRule) {
        return ACCESS_RULES.get(user.getRole()).contains(accessRule);
    }

    public boolean checkIsAccessible(User user, String url) {
        for (Page page : ACCESS_RULES.get(user.getRole())) {
            Pattern pattern = Pattern.compile(page.getUrlPattern());
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
