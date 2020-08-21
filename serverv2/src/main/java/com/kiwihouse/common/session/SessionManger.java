package com.kiwihouse.common.session;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author xin
 * @date 2020/6/9
 */
public class SessionManger {
    private static SessionManger instance;
    private HashMap<String, HttpSession> sessionMap;

    private SessionManger() {
        sessionMap = new HashMap<>();
    }

    public static SessionManger getInstance() {
        if (instance == null) {
            instance = new SessionManger();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        return sessionMap.get(sessionId);
    }
}
