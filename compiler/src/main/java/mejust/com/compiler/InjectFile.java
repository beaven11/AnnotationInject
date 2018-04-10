package mejust.com.compiler;

import com.google.auto.common.MoreElements;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

/**
 * 创建时间: 2018/03/30 16:19<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/30 16:19<br>
 * 描述:
 */
public class InjectFile {

    private static final String ACTIVITY_TYPE = "android.app.Activity";

    private static final String FRAGMENT_TYPE = "android.support.v4.app.Fragment";

    private static final ClassName VIEW_GROUP = ClassName.get("android.view", "ViewGroup");

    private static final ClassName VIEW = ClassName.get("android.view", "View");

    public static InjectFile getInjectFile() {
        return SingleHolder.injectFile;
    }

    private static final class SingleHolder {
        private static final InjectFile injectFile = new InjectFile();
    }

    public JavaFile brewJava(InjectInfo info) {
        return JavaFile.builder(getPackageClassName(info.getTypeElement()).packageName(),
                buildTypeSpec(info))
                .addFileComment("Generated code from Inject Layout. Do not modify!")
                .build();
    }

    private ClassName getPackageClassName(TypeElement typeElement) {
        String packageName = MoreElements.getPackage(typeElement).getQualifiedName().toString();
        String className = typeElement.getQualifiedName()
                .toString()
                .substring(packageName.length() + 1)
                .replace('.', '$');
        return ClassName.get(packageName, className + "_InjectLayout");
    }

    private TypeSpec buildTypeSpec(InjectInfo info) {
        TypeSpec.Builder builder =
                TypeSpec.classBuilder(getPackageClassName(info.getTypeElement()).simpleName())
                        .addModifiers(Modifier.PUBLIC)
                        .addField(getTargetTypeName(info.getTypeElement()), "target",
                                Modifier.PRIVATE)
                        .addMethod(constructor(info));
        LayoutInfo layoutInfo = info.getLayoutInfo();
        if (layoutInfo != null) {
            builder.addMethod(createLayoutMethod(info.getTypeElement(), layoutInfo));
        }
        return builder.build();
    }

    /**
     * 创建构造函数
     */
    private MethodSpec constructor(InjectInfo info) {
        MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                .addParameter(getTargetTypeName(info.getTypeElement()), "target")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("this.target = target");
        TitleBarSettingInfo titleBarSettingInfo = info.getTitleBarSettingInfo();
        if (titleBarSettingInfo != null) {
            builder.addStatement("target.topBarSettingBuilder.setTitleTextContext($S)",
                    titleBarSettingInfo.getTextValue())
                    .addStatement(
                            "target.topBar.setTopBarSetting(target.topBarSettingBuilder.build())");
        }
        return builder.build();
    }

    /**
     * 创建view注入函数
     */
    private MethodSpec createLayoutMethod(TypeElement typeElement, LayoutInfo info) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("layout")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(getTargetTypeName(typeElement), "target");
        TypeMirror typeMirror = typeElement.asType();
        if (isSubtypeOfType(typeMirror, ACTIVITY_TYPE)) {
            builder.addStatement("target.setContentView($L)", info.getLayoutId());
        } else if (isSubtypeOfType(typeMirror, FRAGMENT_TYPE)) {
            builder.returns(VIEW);
            builder.addParameter(VIEW_GROUP, "viewGroup");
            builder.addStatement("return target.getLayoutInflater().inflate($L,viewGroup,false)",
                    info.getLayoutId());
        }
        return builder.build();
    }

    private TypeName getTargetTypeName(TypeElement typeElement) {
        TypeMirror typeMirror = typeElement.asType();
        return TypeName.get(typeMirror);
    }

    private static boolean isSubtypeOfType(TypeMirror typeMirror, String otherType) {
        if (isTypeEqual(typeMirror, otherType)) {
            return true;
        }
        if (typeMirror.getKind() != TypeKind.DECLARED) {
            return false;
        }
        DeclaredType declaredType = (DeclaredType) typeMirror;
        List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
        if (typeArguments.size() > 0) {
            StringBuilder typeString = new StringBuilder(declaredType.asElement().toString());
            typeString.append('<');
            for (int i = 0; i < typeArguments.size(); i++) {
                if (i > 0) {
                    typeString.append(',');
                }
                typeString.append('?');
            }
            typeString.append('>');
            if (typeString.toString().equals(otherType)) {
                return true;
            }
        }
        Element element = declaredType.asElement();
        if (!(element instanceof TypeElement)) {
            return false;
        }
        TypeElement typeElement = (TypeElement) element;
        TypeMirror superType = typeElement.getSuperclass();
        if (isSubtypeOfType(superType, otherType)) {
            return true;
        }
        for (TypeMirror interfaceType : typeElement.getInterfaces()) {
            if (isSubtypeOfType(interfaceType, otherType)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTypeEqual(TypeMirror typeMirror, String otherType) {
        return otherType.equals(typeMirror.toString());
    }
}
