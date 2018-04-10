package mejust.com.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import mejust.com.inject.LayoutId;
import mejust.com.inject.TitleBarSetting;

@AutoService(Processor.class)
public class ViewInjectProcessor extends AbstractProcessor {

    private Map<TypeElement, InjectInfo> injectInfoMap = new HashMap<>();

    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add("mejust.com.inject.LayoutId");
        annotationTypes.add("mejust.com.inject.TitleBarSetting");
        return annotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        collectionInfo(roundEnvironment);
        writeToFile();
        return true;
    }

    private void collectionInfo(RoundEnvironment roundEnvironment) {
        injectInfoMap.clear();
        Set<? extends Element> layoutElements =
                roundEnvironment.getElementsAnnotatedWith(LayoutId.class);
        for (Element element : layoutElements) {
            int layoutId = element.getAnnotation(LayoutId.class).value();
            TypeElement typeElement = (TypeElement) element;
            InjectInfo injectInfo = new InjectInfo(typeElement);
            injectInfo.setLayoutInfo(new LayoutInfo(layoutId));
            injectInfoMap.put(typeElement, injectInfo);
        }
        Set<? extends Element> titleBarSettingElements =
                roundEnvironment.getElementsAnnotatedWith(TitleBarSetting.class);
        for (Element element : titleBarSettingElements) {
            String titleValue = element.getAnnotation(TitleBarSetting.class).titleValue();
            int textColor = element.getAnnotation(TitleBarSetting.class).textColor();
            float textSize = element.getAnnotation(TitleBarSetting.class).textSize();
            int background = element.getAnnotation(TitleBarSetting.class).background();
            boolean hideBack = element.getAnnotation(TitleBarSetting.class).hideBack();
            TypeElement typeElement = (TypeElement) element;
            TitleBarSettingInfo titleBarSettingInfo =
                    new TitleBarSettingInfo(titleValue, textColor, textSize, background, hideBack);
            InjectInfo injectInfo = injectInfoMap.get(typeElement);
            if (injectInfo == null) {
                injectInfo = new InjectInfo(typeElement);
            }
            injectInfo.setTitleBarSettingInfo(titleBarSettingInfo);
            injectInfoMap.put(typeElement, injectInfo);
        }
    }

    private void writeToFile() {
        for (Map.Entry<TypeElement, InjectInfo> entry : injectInfoMap.entrySet()) {
            TypeElement typeElement = entry.getKey();
            InjectInfo injectInfo = entry.getValue();
            JavaFile javaFile = InjectFile.getInjectFile().brewJava(injectInfo);
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                error(typeElement, "Unable to write binding for type %s: %s", typeElement,
                        e.getMessage());
            }
        }
    }

    private void error(Element element, String message, Object... args) {
        printMessage(Diagnostic.Kind.ERROR, element, message, args);
    }

    private void printMessage(Diagnostic.Kind kind, Element element, String message,
            Object[] args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        processingEnv.getMessager().printMessage(kind, message, element);
    }
}
