package mejust.com.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import java.io.IOException;
import java.util.HashSet;
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

@AutoService(Processor.class)
public class ViewInjectProcessor extends AbstractProcessor {

    private Set<VariableInfo> variableInfoSet = new HashSet<>();
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
        variableInfoSet.clear();
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(LayoutId.class);
        for (Element element : elements) {
            int layoutId = element.getAnnotation(LayoutId.class).value();
            TypeElement typeElement = (TypeElement) element;
            variableInfoSet.add(new VariableInfo(layoutId, typeElement));
        }
    }

    private void writeToFile() {
        for (VariableInfo info : variableInfoSet) {
            JavaFile javaFile = InjectFile.getInjectFile().brewJava(info);
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                error(info.getTypeElement(), "Unable to write binding for type %s: %s",
                        info.getTypeElement(), e.getMessage());
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
