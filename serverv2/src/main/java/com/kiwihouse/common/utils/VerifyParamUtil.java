package com.kiwihouse.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yjzn
 * @date 2020-3-18 09:37:36
 */
public class VerifyParamUtil {

    /**
     * 校验区域编码
     * @param code
     * @return
     */
    public static boolean isValidCode(String code) {

        if(StringUtils.isBlank(code)){
            return true;
        }else{
            if(code.length()!=6){
                return false;
            }else {
                try {
                    Integer codeInt = Integer.valueOf(code);
                    if(codeInt<100000 || codeInt>999999){
                        return false;
                    }
                }catch (NumberFormatException e){
                    return false;
                }
            }
        }
        return true;
    }
}
