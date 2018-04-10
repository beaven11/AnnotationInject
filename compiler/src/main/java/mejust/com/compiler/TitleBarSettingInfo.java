package mejust.com.compiler;

/**
 * 创建时间: 2018/04/10
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/10
 * 描述: <empty/>
 */
public class TitleBarSettingInfo {

    private String textValue;

    private int textColor;

    private float textSize;

    private int backgroundColor;

    private boolean hideBack;

    public TitleBarSettingInfo(String textValue, int textColor, float textSize, int backgroundColor,
            boolean hideBack) {
        this.textValue = textValue;
        this.textColor = textColor;
        this.textSize = textSize;
        this.backgroundColor = backgroundColor;
        this.hideBack = hideBack;
    }

    public String getTextValue() {
        return textValue;
    }

    public int getTextColor() {
        return textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isHideBack() {
        return hideBack;
    }
}
