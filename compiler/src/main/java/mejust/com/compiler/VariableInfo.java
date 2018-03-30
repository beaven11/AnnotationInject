package mejust.com.compiler;

import javax.lang.model.element.TypeElement;

public class VariableInfo {

    private int layoutId;

    private TypeElement typeElement;

    public VariableInfo(int layoutId, TypeElement typeElement) {
        this.layoutId = layoutId;
        this.typeElement = typeElement;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }
}
