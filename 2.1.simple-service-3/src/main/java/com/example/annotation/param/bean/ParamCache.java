package com.example.annotation.param.bean;

import com.example.domain.Yijing;

import java.util.ArrayList;

public class ParamCache {
    public final static ArrayList<Yijing> LIST;

    static {
        LIST = new ArrayList<Yijing>();
        ParamCache.LIST.add(new Yijing("1", "乾", "qián"));
        ParamCache.LIST.add(new Yijing("2", "坤", "kūn"));
        ParamCache.LIST.add(new Yijing("3", "屯", "zhūn"));
        ParamCache.LIST.add(new Yijing("4", "蒙", "méng"));
        ParamCache.LIST.add(new Yijing("5", "需", "xū"));

        ParamCache.LIST.add(new Yijing("6", "讼", "sòng"));
        ParamCache.LIST.add(new Yijing("7", "师", "shī"));
        ParamCache.LIST.add(new Yijing("8", "比", "bì"));
        ParamCache.LIST.add(new Yijing("9", "小畜", "xiao xù"));
        ParamCache.LIST.add(new Yijing("10", "履", "lǚ"));

        ParamCache.LIST.add(new Yijing("11", "泰", "tài"));
        ParamCache.LIST.add(new Yijing("12", "否", "pǐ"));
        ParamCache.LIST.add(new Yijing("13", "同人", "tóng rén"));
        ParamCache.LIST.add(new Yijing("14", "大有", "dà yōu"));

        ParamCache.LIST.add(new Yijing("15", "谦", "qiān"));
        ParamCache.LIST.add(new Yijing("16", "豫", "yǜ"));
        ParamCache.LIST.add(new Yijing("17", "随", "suí"));
        ParamCache.LIST.add(new Yijing("18", "蛊", "gǔ"));
        ParamCache.LIST.add(new Yijing("19", "临", "lín"));

        ParamCache.LIST.add(new Yijing("20", "观", "guān"));
        ParamCache.LIST.add(new Yijing("21", "噬嗑", "shì hé"));
        ParamCache.LIST.add(new Yijing("22", "贲", "bì"));
        ParamCache.LIST.add(new Yijing("23", "剥", "bō"));
        ParamCache.LIST.add(new Yijing("24", "复", "fù"));

        ParamCache.LIST.add(new Yijing("25", "无妄", "wú wàng"));
        ParamCache.LIST.add(new Yijing("26", "大畜", "dà xù"));
        ParamCache.LIST.add(new Yijing("27", "颐", "yí"));
        ParamCache.LIST.add(new Yijing("28", "大过", "dà guò"));

        ParamCache.LIST.add(new Yijing("29", "坎", "kǎn"));
        ParamCache.LIST.add(new Yijing("30", "离", "lí"));
        ParamCache.LIST.add(new Yijing("31", "咸", "xián"));
        ParamCache.LIST.add(new Yijing("32", "恒", "héng"));
        ParamCache.LIST.add(new Yijing("33", "遁", "dùn"));

        ParamCache.LIST.add(new Yijing("34", "大壮", "dà zhuàng"));
        ParamCache.LIST.add(new Yijing("35", "晋", "jìn"));
        ParamCache.LIST.add(new Yijing("36", "明夷", "míng yí"));
        ParamCache.LIST.add(new Yijing("37", "家人", "jiā ren"));

        ParamCache.LIST.add(new Yijing("38", "睽", "kuí"));
        ParamCache.LIST.add(new Yijing("39", "蹇", "jiǎn"));
        ParamCache.LIST.add(new Yijing("40", "解", "xiè"));
        ParamCache.LIST.add(new Yijing("41", "损", "sǔn"));
        ParamCache.LIST.add(new Yijing("42", "益", "yì"));

        ParamCache.LIST.add(new Yijing("43", "夬", "guài"));
        ParamCache.LIST.add(new Yijing("44", "姤", "gòu"));
        ParamCache.LIST.add(new Yijing("45", "萃", "cuì"));
        ParamCache.LIST.add(new Yijing("46", "升", "shēng"));
        ParamCache.LIST.add(new Yijing("47", "困", "kùn"));

        ParamCache.LIST.add(new Yijing("48", "井", "jǐng"));
        ParamCache.LIST.add(new Yijing("49", "革", "gé"));
        ParamCache.LIST.add(new Yijing("50", "鼎", "dǐng"));
        ParamCache.LIST.add(new Yijing("51", "震", "zhèn"));
        ParamCache.LIST.add(new Yijing("52", "艮", "gèn"));

        ParamCache.LIST.add(new Yijing("53", "渐", "jiàn"));
        ParamCache.LIST.add(new Yijing("54", "归妹", "guī mèi"));
        ParamCache.LIST.add(new Yijing("55", "丰", "fēng"));
        ParamCache.LIST.add(new Yijing("56", "旅", "lǚ"));

        ParamCache.LIST.add(new Yijing("57", "巽", "xùn"));
        ParamCache.LIST.add(new Yijing("58", "兑", "duì"));
        ParamCache.LIST.add(new Yijing("59", "涣", "huàn"));
        ParamCache.LIST.add(new Yijing("60", "节", "jié"));
        ParamCache.LIST.add(new Yijing("61", "中孚", "zhōng fú"));

        ParamCache.LIST.add(new Yijing("62", "小过", "xiǎo guò"));
        ParamCache.LIST.add(new Yijing("63", "既济", "jì jì"));
        ParamCache.LIST.add(new Yijing("64", "未济", "wèi jì"));
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Yijing> copy() {
        return (ArrayList<Yijing>) ParamCache.LIST.clone();
    }

    public static Yijing find(final String seqId) {
        for (final Yijing yijing : ParamCache.LIST) {
            if (seqId.equals(yijing.getSequence())) {
                return yijing;
            }
        }
        return null;
    }
}
