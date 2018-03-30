package com.beaven.testapp.test;

import java.io.Serializable;

/**
 * 创建时间: 2018/03/28 15:23<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/28 15:23<br>
 * 描述:
 */
public class TitleBar implements Serializable {

    private static final long serialVersionUID = 1836804332949751835L;

    private String title;
    private int titleColor;
    private int barColor;
    private String backText;
    private String backTextSize;
    private int backTextColor;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public void setBackTextSize(String backTextSize) {
        this.backTextSize = backTextSize;
    }

    public void setBackTextColor(int backTextColor) {
        this.backTextColor = backTextColor;
    }

    public String getTitle() {
        return title;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getBarColor() {
        return barColor;
    }

    public String getBackText() {
        return backText;
    }

    public String getBackTextSize() {
        return backTextSize;
    }

    public int getBackTextColor() {
        return backTextColor;
    }

    @Override
    public String toString() {
        return "TitleBar{"
                + "title='"
                + title
                + '\''
                + ", titleColor="
                + titleColor
                + ", barColor="
                + barColor
                + ", backText='"
                + backText
                + '\''
                + ", backTextSize='"
                + backTextSize
                + '\''
                + ", backTextColor="
                + backTextColor
                + '}';
    }
}
