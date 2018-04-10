package mejust.com.compiler;

import javax.lang.model.element.TypeElement;

/**
 * 创建时间: 2018/04/10
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/10
 * 描述: <empty/>
 */
public class InjectInfo {

    private TypeElement typeElement;

    private LayoutInfo layoutInfo;

    private TitleBarSettingInfo titleBarSettingInfo;

    public InjectInfo(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public LayoutInfo getLayoutInfo() {
        return layoutInfo;
    }

    public void setLayoutInfo(LayoutInfo layoutInfo) {
        this.layoutInfo = layoutInfo;
    }

    public TitleBarSettingInfo getTitleBarSettingInfo() {
        return titleBarSettingInfo;
    }

    public void setTitleBarSettingInfo(TitleBarSettingInfo titleBarSettingInfo) {
        this.titleBarSettingInfo = titleBarSettingInfo;
    }
}
