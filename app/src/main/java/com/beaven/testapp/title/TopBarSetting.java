package com.beaven.testapp.title;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * @author Beaven
 */
public class TopBarSetting {

    // title背景颜色
    private int backgroundColor;
    // 标题
    private String titleTextContent;
    // 标题大小
    private float titleTextSize;
    // 标题颜色
    private int titleTextColor;

    private TitleMenu titleLeftFirstMenu;

    private TitleMenu titleLeftSecondMenu;

    private TitleMenu titleRightFirstMenu;

    private TitleMenu titleRightSecondMenu;

    public TopBarSetting(Builder builder) {
        this.backgroundColor = builder.backgroundColor;
        this.titleTextContent = builder.titleTextContent;
        this.titleTextSize = builder.titleTextSize;
        this.titleTextColor = builder.titleTextColor;
        this.titleLeftFirstMenu = builder.titleLeftFirstMenu;
        this.titleLeftSecondMenu = builder.titleLeftSecondMenu;
        this.titleRightFirstMenu = builder.titleRightFirstMenu;
        this.titleRightSecondMenu = builder.titleRightSecondMenu;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.setBackgroundColor(backgroundColor);
        builder.setTitleLeftFirstMenu(titleLeftFirstMenu);
        builder.setTitleLeftSecondMenu(titleLeftSecondMenu);
        builder.setTitleRightFirstMenu(titleRightFirstMenu);
        builder.setTitleRightSecondMenu(titleRightSecondMenu);
        builder.setTitleTextContext(titleTextContent);
        builder.setTitleTextSize(titleTextSize);
        builder.setTitleTextColor(titleTextColor);
        return builder;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public String getTitleTextContent() {
        return titleTextContent;
    }

    public float getTitleTextSize() {
        return titleTextSize;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public TitleMenu getTitleLeftFirstMenu() {
        return titleLeftFirstMenu;
    }

    public TitleMenu getTitleLeftSecondMenu() {
        return titleLeftSecondMenu;
    }

    public TitleMenu getTitleRightFirstMenu() {
        return titleRightFirstMenu;
    }

    public TitleMenu getTitleRightSecondMenu() {
        return titleRightSecondMenu;
    }

    @Override
    public String toString() {
        return "TopBarSetting{"
                + "backgroundColor="
                + backgroundColor
                + ", titleTextContent='"
                + titleTextContent
                + '\''
                + ", titleTextSize="
                + titleTextSize
                + ", titleTextColor="
                + titleTextColor
                + ", titleLeftFirstMenu="
                + titleLeftFirstMenu
                + ", titleLeftSecondMenu="
                + titleLeftSecondMenu
                + ", titleRightFirstMenu="
                + titleRightFirstMenu
                + ", titleRightSecondMenu="
                + titleRightSecondMenu
                + '}';
    }

    public static class Builder {

        // title背景颜色
        private int backgroundColor = 0;
        // 标题
        private String titleTextContent;
        // 标题大小
        private float titleTextSize = 0;
        // 标题颜色
        private int titleTextColor = 0;

        private TitleMenu titleLeftFirstMenu;

        private TitleMenu titleLeftSecondMenu;

        private TitleMenu titleRightFirstMenu;

        private TitleMenu titleRightSecondMenu;

        public Builder setBackgroundColor(@ColorInt int color) {
            this.backgroundColor = color;
            return this;
        }

        public Builder setBackgroundColorRes(@ColorRes int color, Context context) {
            this.backgroundColor = ContextCompat.getColor(context, color);
            return this;
        }

        public Builder setTitleTextContext(String content) {
            this.titleTextContent = content;
            return this;
        }

        public Builder setTitleTextSize(float size) {
            this.titleTextSize = size;
            return this;
        }

        public Builder setTitleTextColor(@ColorInt int color) {
            this.titleTextColor = color;
            return this;
        }

        public Builder setTitleTextColorRes(@ColorRes int color, Context context) {
            this.titleTextColor = ContextCompat.getColor(context, color);
            return this;
        }

        public Builder setTitleLeftFirstMenu(TitleMenu titleLeftFirstMenu) {
            this.titleLeftFirstMenu = titleLeftFirstMenu;
            return this;
        }

        public Builder setTitleLeftSecondMenu(TitleMenu titleLeftSecondMenu) {
            this.titleLeftSecondMenu = titleLeftSecondMenu;
            return this;
        }

        public Builder setTitleRightFirstMenu(TitleMenu titleRightFirstMenu) {
            this.titleRightFirstMenu = titleRightFirstMenu;
            return this;
        }

        public Builder setTitleRightSecondMenu(TitleMenu titleRightSecondMenu) {
            this.titleRightSecondMenu = titleRightSecondMenu;
            return this;
        }

        public TopBarSetting build() {
            return new TopBarSetting(this);
        }
    }

    public static class TitleMenu {

        private Drawable iconDrawable;

        private String text;

        private float textSize = 0;

        private int textColor = 0;

        private View.OnClickListener clickListener;

        public Drawable getIconDrawable() {
            return iconDrawable;
        }

        public void setIconDrawable(Drawable iconDrawable) {
            this.iconDrawable = iconDrawable;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public float getTextSize() {
            return textSize;
        }

        public void setTextSize(float textSize) {
            this.textSize = textSize;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(@ColorInt int textColor) {
            this.textColor = textColor;
        }

        public void setTextColorRes(@ColorRes int textColor, Context context) {
            this.textColor = ContextCompat.getColor(context, textColor);
        }

        public View.OnClickListener getClickListener() {
            return clickListener;
        }

        public void setClickListener(View.OnClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public String toString() {
            return "TitleMenu{"
                    + "iconDrawable="
                    + iconDrawable
                    + ", text='"
                    + text
                    + '\''
                    + ", textSize="
                    + textSize
                    + ", textColor="
                    + textColor
                    + ", clickListener="
                    + clickListener
                    + '}';
        }
    }
}
