package com.kiwihouse.support;

import com.kiwihouse.util.XssUtil;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * request请求安全过滤包装类
 *
 * @author xin
 * @date 20:41 2020/4/15
 */
public class XssSqlHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private byte[] requestBody;


    public XssSqlHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * description 重写  数组参数过滤
     *
     * @param parameter 1
     * @return java.lang.String[]
     */
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = filterParamString(values[i]);
        }
        return encodedValues;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> primary = super.getParameterMap();
        Map<String, String[]> result = new HashMap<>(16);
        for (Map.Entry<String, String[]> entry : primary.entrySet()) {
            result.put(entry.getKey(), filterEntryString(entry.getValue()));
        }
        return result;
    }

    @Override
    public String getParameter(String parameter) {
        return filterParamString(super.getParameter(parameter));
    }

    @Override
    public String getHeader(String name) {
        return filterParamString(super.getHeader(name));
    }

    @Override
    public Cookie[] getCookies() {
        Cookie[] cookies = super.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                cookie.setValue(filterParamString(cookie.getValue()));
            }
        }
        return cookies;
    }

    /**
     * description 过滤字符串数组不安全内容
     *
     * @param value 1
     * @return java.lang.String[]
     */
    private String[] filterEntryString(String[] value) {
        for (int i = 0; i < value.length; i++) {
            value[i] = filterParamString(value[i]);
        }
        return value;
    }

    /**
     * description 过滤字符串不安全内容
     *
     * @param value 1
     * @return java.lang.String
     */
    private String filterParamString(String value) {
        if (null == value) {
            return null;
        }
        // 过滤XSS 和 SQL 注入
        return XssUtil.stripSqlXss(value);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (requestBody == null) {
            try {
                requestBody = IOUtils.toByteArray(super.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new BufferedServletInputStream(requestBody);
    }

    static class BufferedServletInputStream extends ServletInputStream {
        private ByteArrayInputStream inputStream;

        public BufferedServletInputStream(byte[] buffer) {
            this.inputStream = new ByteArrayInputStream(buffer);
        }

        @Override
        public int available() throws IOException {
            return inputStream.available();
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            return inputStream.read(b, off, len);
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }
}
