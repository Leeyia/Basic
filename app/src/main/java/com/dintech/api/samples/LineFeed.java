package com.dintech.api.samples;

public class LineFeed {


    public String makelinefeed(String s) {
        //用空格作为分隔符，将单词存到字符数组里面
        String[] str = s.split(" ");
        //利用StringBuffer对字符串进行修改
        StringBuffer buffer = new StringBuffer();
        //判断单词长度，计算
        int len = 0;
        for (int i = 0; i < str.length; i++) {
            //System.out.println(str);
            //len = str[i].length();
            //叠加
            len += str[i].length();
            //System.out.println(len);
            if (len > 20) {
                buffer.append("\n" + str[i] + " ");//利用StringBuffer对字符串进行修改
                len = str[i].length()+1;//+1为换行后读出空格一位
                //System.out.println(len);
            } else {
                buffer.append(str[i] + " ");
                len++;
                //System.out.println(len);
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        LineFeed lf = new LineFeed();
        System.out
                .println(lf
                        .makelinefeed("The function returns the string, but with line breaks inserted at just the right places to make sure that no line is longer than the column number."));
    }
}
