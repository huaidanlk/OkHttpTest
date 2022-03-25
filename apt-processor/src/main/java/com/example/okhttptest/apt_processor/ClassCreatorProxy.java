package com.example.okhttptest.apt_processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

public class ClassCreatorProxy {
    private String mBindClassName;
    private String mPackageName;
    private TypeElement mTypeElement;
    private Map<Integer, VariableElement> mVariableElementMap = new HashMap<>();

    public ClassCreatorProxy(Elements elementsUtils,TypeElement classElement) {
        this.mTypeElement = classElement;
        PackageElement packageElement = elementsUtils.getPackageOf(mTypeElement);
        String className = mTypeElement.getSimpleName().toString();
        mPackageName = packageElement.getQualifiedName().toString();
        mBindClassName = className + "_ViewBinding";
    }

    public void putElement(int id,VariableElement element){
        mVariableElementMap.put(id,element);
    }


    /**
     * 创建Java代码
     * javapoet
     *
     * @return
     */
    public TypeSpec generateJavaCode(){
        TypeSpec bindingClass = TypeSpec.classBuilder(mBindClassName)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(generateMethod())
                .build();
        return bindingClass;
    }


    private MethodSpec generateMethod(){
        ClassName host = ClassName.bestGuess(mTypeElement.getQualifiedName().toString());
        MethodSpec.Builder methodBuilder =MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(host,"host");
        for(int id : mVariableElementMap.keySet()){
            VariableElement element = mVariableElementMap.get(id);
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();
            methodBuilder.addCode("host." + name + " = "+"（" + type + ")(((android.app.Activity)host).findViewById(" + id + "));" );
        }
        return methodBuilder.build();
    }

    public String getPackageName() {
        return mPackageName;
    }
}
