package com.beaven.testapp.title;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beaven.testapp.R;

/**
 * @author Beaven
 */
public class TopBar extends LinearLayout {

    private static final int MENU_TEXT_WIDTH = 80;

    // 标题栏左边菜单区域
    private LinearLayout layoutToolLeft;
    // 标题栏右边菜单区域
    private LinearLayout layoutToolRight;
    // 中心标题内容
    private FrameLayout layoutToolCenter;

    @ColorInt
    private int barColor;

    private int topBarHeight;
    @ColorInt
    private int centerTitleColor;

    private float centerTitleSize;

    private int menuIconSize;
    @ColorInt
    private int menuTextColor;

    private float menuTextSize;

    private TopBarSetting topBarSetting;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.top_bar_style);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.HORIZONTAL);
        layoutToolLeft = buildToolLayout();
        addView(layoutToolLeft);
        layoutToolCenter = buildCenterLayout();
        addView(layoutToolCenter);
        layoutToolRight = buildToolLayout();
        addView(layoutToolRight);
        getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int leftWidth = layoutToolLeft.getMeasuredWidth();
                        int rightWidth = layoutToolRight.getMeasuredWidth();
                        int width = Math.max(leftWidth, rightWidth);
                        layoutToolLeft.setLayoutParams(
                                new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
                        layoutToolRight.setLayoutParams(
                                new LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
                    }
                });
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.TopBar, defStyleAttr, 0);
        topBarHeight = typedArray.getDimensionPixelSize(R.styleable.TopBar_frame_topBar_height,
                dpToPx(56));
        barColor =
                typedArray.getColor(R.styleable.TopBar_frame_topBar_background_color, Color.WHITE);
        setBackgroundColor(barColor);
        int paddingSize = typedArray.getDimensionPixelSize(R.styleable.TopBar_frame_topBar_padding,
                dpToPx(16));
        setPadding(paddingSize, 0, paddingSize, 0);
        centerTitleColor =
                typedArray.getColor(R.styleable.TopBar_frame_topBar_title_color, Color.BLACK);
        centerTitleSize = typedArray.getDimension(R.styleable.TopBar_frame_topBar_title_size, 18);
        menuIconSize =
                typedArray.getDimensionPixelSize(R.styleable.TopBar_frame_topBar_menu_icon_size,
                        dpToPx(24));
        menuTextColor =
                typedArray.getColor(R.styleable.TopBar_frame_topBar_menu_text_color, Color.BLACK);
        menuTextSize = typedArray.getDimension(R.styleable.TopBar_frame_topBar_menu_text_size, 16);
        typedArray.recycle();
    }

    /**
     * 设置标题栏显示
     *
     * @param topBarSetting 标题栏配置
     */
    public void setTopBarSetting(TopBarSetting topBarSetting) {
        this.topBarSetting = topBarSetting;
        if (topBarSetting.getBackgroundColor() != 0) {
            setBackgroundColor(topBarSetting.getBackgroundColor());
        }
        setCenterTitle(topBarSetting.getTitleTextContent(), topBarSetting.getTitleTextSize(),
                topBarSetting.getTitleTextColor());
        TopBarSetting.TitleMenu leftFirstMenu = topBarSetting.getTitleLeftFirstMenu();
        TopBarSetting.TitleMenu leftSecondMenu = topBarSetting.getTitleLeftSecondMenu();
        setTitleMenu(true, layoutToolLeft, leftFirstMenu, leftSecondMenu);
        TopBarSetting.TitleMenu secondFirstMenu = topBarSetting.getTitleRightFirstMenu();
        TopBarSetting.TitleMenu secondSecondMenu = topBarSetting.getTitleRightSecondMenu();
        setTitleMenu(false, layoutToolRight, secondFirstMenu, secondSecondMenu);
    }

    /**
     * 获取当前标题栏配置文件
     *
     * @return TopBarSetting
     */
    public TopBarSetting getTopBarSetting() {
        final TopBarSetting topBarSetting = this.topBarSetting;
        TopBarSetting.Builder builder = new TopBarSetting.Builder();
        if (topBarSetting == null) {
            return builder.setBackgroundColor(barColor)
                    .setTitleTextSize(centerTitleSize)
                    .setTitleTextColor(centerTitleColor)
                    .build();
        }
        int backgroundColorSetting = topBarSetting.getBackgroundColor() == 0 ? barColor
                : topBarSetting.getBackgroundColor();
        builder.setBackgroundColor(backgroundColorSetting);
        String titleText = TextUtils.isEmpty(topBarSetting.getTitleTextContent()) ? ""
                : topBarSetting.getTitleTextContent();
        builder.setTitleTextContext(titleText);
        int titleTextColor = topBarSetting.getTitleTextColor() == 0 ? centerTitleColor
                : topBarSetting.getTitleTextColor();
        builder.setTitleTextColor(titleTextColor);
        float titleTextSize = topBarSetting.getTitleTextSize() == 0 ? centerTitleSize
                : topBarSetting.getTitleTextSize();
        return builder.setTitleTextSize(titleTextSize)
                .setTitleLeftFirstMenu(buildTitleMenu(topBarSetting.getTitleLeftFirstMenu()))
                .setTitleLeftSecondMenu(buildTitleMenu(topBarSetting.getTitleLeftSecondMenu()))
                .setTitleRightFirstMenu(buildTitleMenu(topBarSetting.getTitleRightFirstMenu()))
                .setTitleRightSecondMenu(buildTitleMenu(topBarSetting.getTitleRightSecondMenu()))
                .build();
    }

    /**
     * 设置标题栏菜单按钮区域
     *
     * @param isLeft 是否是左边区域
     * @param layout 左边或右边区域容器
     * @param titleMenus 菜单按钮配置
     */
    private void setTitleMenu(boolean isLeft, LinearLayout layout,
            TopBarSetting.TitleMenu... titleMenus) {
        layout.removeAllViews();
        for (TopBarSetting.TitleMenu titleMenu : titleMenus) {
            if (titleMenu == null) {
                return;
            }
            FrameLayout frameLayout;
            if (titleMenu.getIconDrawable() == null) {
                frameLayout = buildToolTextLayout(titleMenu.getText(), titleMenu.getTextSize(),
                        titleMenu.getTextColor(), isLeft, titleMenu.getClickListener());
            } else {
                frameLayout = buildToolImageLayout(titleMenu.getIconDrawable(),
                        titleMenu.getClickListener());
            }
            layout.addView(frameLayout);
        }
    }

    /**
     * 设置标题中心内容
     *
     * @param text 内容文字
     * @param textSize 内容文字大小
     * @param textColorRes 内容文字颜色
     */
    private void setCenterTitle(String text, float textSize, @ColorInt int textColorRes) {
        layoutToolCenter.removeAllViews();
        int color = textColorRes == 0 ? this.centerTitleColor : textColorRes;
        float size = textSize == 0 ? this.centerTitleSize : textSize;
        String content = text == null ? "" : text;
        TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        textView.setText(content);
        textView.setTextSize(size);
        textView.setTextColor(color);
        layoutToolCenter.addView(textView,
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * 生成文字菜单按钮
     *
     * @param text 文字内容
     * @param textSize 文字大小
     * @param textColor 文字颜色
     * @param left 是否位于左边区域
     * @param clickListener 按钮点击事件
     * @return 文字菜单按钮
     */
    @SuppressLint("RtlHardcoded")
    private FrameLayout buildToolTextLayout(String text, float textSize, int textColor,
            boolean left, OnClickListener clickListener) {
        String content = text == null ? "" : text;
        float size = textSize == 0 ? this.menuTextSize : textSize;
        int color = textColor == 0 ? this.menuTextColor : textColor;
        TextView textView = new TextView(getContext());
        textView.setText(content);
        textView.setTextSize(size);
        textView.setTextColor(color);
        textView.setMaxLines(1);
        FrameLayout.LayoutParams textLayoutParams =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.gravity =
                left ? Gravity.CENTER | Gravity.LEFT : Gravity.CENTER | Gravity.RIGHT;
        textView.setLayoutParams(textLayoutParams);

        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setBackground(
                ContextCompat.getDrawable(getContext(), R.drawable.selector_transparent));
        frameLayout.setClickable(true);
        LayoutParams layoutParams =
                new LayoutParams(dpToPx(MENU_TEXT_WIDTH), ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayout.setLayoutParams(layoutParams);
        frameLayout.addView(textView);
        frameLayout.setOnClickListener(clickListener);
        return frameLayout;
    }

    /**
     * 生成菜单图标按钮
     *
     * @param drawable 图标
     * @param clickListener 点击事件
     * @return 图标菜单
     */
    private FrameLayout buildToolImageLayout(Drawable drawable, OnClickListener clickListener) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawable);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        FrameLayout.LayoutParams imageLayoutParams =
                new FrameLayout.LayoutParams(menuIconSize, menuIconSize);
        imageLayoutParams.gravity = Gravity.CENTER;
        imageView.setLayoutParams(imageLayoutParams);
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setBackground(
                ContextCompat.getDrawable(getContext(), R.drawable.selector_transparent));
        frameLayout.setClickable(true);
        int touchWidth = menuIconSize + dpToPx(16);
        LayoutParams layoutParams =
                new LayoutParams(touchWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        frameLayout.setLayoutParams(layoutParams);
        frameLayout.addView(imageView);
        frameLayout.setOnClickListener(clickListener);
        return frameLayout;
    }

    /**
     * 生成中心标题容器
     */
    private FrameLayout buildCenterLayout() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        frameLayout.setLayoutParams(layoutParams);
        return frameLayout;
    }

    /**
     * 生成菜单区域容器
     */
    private LinearLayout buildToolLayout() {
        LinearLayout layout = new LinearLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(HORIZONTAL);
        return layout;
    }

    private int dpToPx(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 为topBar文本菜单赋默认值
     */
    private TopBarSetting.TitleMenu buildTitleMenu(TopBarSetting.TitleMenu titleMenu) {
        if (titleMenu != null && titleMenu.getIconDrawable() == null) {
            int textColor =
                    titleMenu.getTextColor() == 0 ? menuTextColor : titleMenu.getTextColor();
            float textSize = titleMenu.getTextSize() == 0 ? menuTextSize : titleMenu.getTextSize();
            titleMenu.setTextColor(textColor);
            titleMenu.setTextSize(textSize);
        }
        return titleMenu;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = topBarHeight;
        }
        int heightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
