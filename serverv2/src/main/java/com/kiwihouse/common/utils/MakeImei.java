package com.kiwihouse.common.utils;

/**
 * imei由15位数字组成，
 * 前6位(TAC)是型号核准号码，代表手机类型。
 * 接着2位(FAC)是最后装配号，代表产地。
 * 后6位(SNR)是串号，代表生产顺序号。
 * 最后1位 (SP)是检验码。
 *
 * 检验码计算：
 * (1).将偶数位数字分别乘以2，分别计算个位数和十位数之和
 * (2).将奇数位数字相加，再加上上一步算得的值
 * (3).如果得出的数个位是0则校验位为0，否则为10减去个位数
 *
 * @author yjzn
 * @date 2020-1-8 20:00:24
 */
public class MakeImei {
    public static void main(String[] args) {
        String imeiString="3556677800";
        StringBuilder str = new StringBuilder("0000");
        for(int k=0; k<1000; k++){
            int int_str = Integer.parseInt(str.toString());
            str = new StringBuilder(int_str + 1 + "");
            int length = str.length();
            if(length<4){
                for(int j=0;j<(4-length);j++){
                    str.insert(0, "0");
                }
            }
            String imei= imeiString+str;
            char[] imeiChar=imei.toCharArray();
            int resultInt=0;
            for (int i = 0; i < imeiChar.length; i++) {
                int a=Integer.parseInt(String.valueOf(imeiChar[i]));
                i++;
                int temp=Integer.parseInt(String.valueOf(imeiChar[i]))*2;
                int b=temp<10?temp:temp-9;
                resultInt+=a+b;
            }
            resultInt%=10;
            resultInt=resultInt==0?0:10-resultInt;
            imei+=resultInt;
            System.out.println(imei);
        }
    }
}



