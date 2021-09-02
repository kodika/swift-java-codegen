package com.readdle.codegen;

import java.io.IOException;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;

class SwiftFieldDescriptor implements JavaSwiftProcessor.WritableElement{

    private boolean isStatic;
    private boolean hasSetter;

    private String fieldName;
    private String fieldJavaClassSignature;
    private SwiftEnvironment.Type fieldSwiftType;

    SwiftFieldDescriptor(VariableElement variableElement, JavaSwiftProcessor processor){
        isStatic = variableElement.getModifiers().contains(Modifier.STATIC);
        hasSetter = (variableElement.getConstantValue() == null);

        fieldName = variableElement.getSimpleName().toString();
        fieldJavaClassSignature = Utils.javaClassToSig(variableElement.asType().toString());

        fieldSwiftType = processor.parseJavaType(variableElement.asType().toString());
        if (processor.isUnsigned(variableElement)){
            fieldSwiftType = fieldSwiftType.makeUnsigned();
        }
    }

    @Override
    public void generateCode(SwiftWriter swiftWriter, String javaFullName, String swiftType) throws IOException {
        swiftWriter.emitEmptyLine();

        swiftWriter.emitStatement(String.format("private static let javaField%s = JNI.api.Get%sFieldID(JNI.env, javaClass, \"%s\", \"%s\")",fieldName,(isStatic ? "Static" : "" ),fieldName, fieldJavaClassSignature));

        //TODO optional
        swiftWriter.emitStatement(String.format("public%s var %s: %s {", (isStatic ? " static" : "" ), fieldName, fieldSwiftType.swiftType));
        swiftWriter.emitStatement("get {");

        swiftWriter.emitStatement(String.format("let fieldJavaValue = JNI.api.%s(JNI.env,%s,%s.javaField%s)",fieldSwiftType.getFieldValue(isStatic),(isStatic ? "javaClass" : "jniObject"),swiftType,fieldName));

        swiftWriter.emitStatement("defer {");
        swiftWriter.emitStatement("if let localRef = fieldJavaValue {");
        swiftWriter.emitStatement("JNI.DeleteLocalRef(localRef)");
        swiftWriter.emitStatement("}");
        swiftWriter.emitStatement("}");

        if (fieldSwiftType.isPrimitiveType()) {
            swiftWriter.emitStatement(String.format("return %s(fromJavaPrimitive: fieldJavaValue)",fieldSwiftType.swiftType));
        }else{
            swiftWriter.emitStatement(String.format("return %s(javaObject: fieldJavaValue)",fieldSwiftType.swiftConstructorType));
        }
        swiftWriter.emitStatement("}");

        if (hasSetter) {
            swiftWriter.emitStatement("set {");
            if (fieldSwiftType.isPrimitiveType()) {
                swiftWriter.emitStatement(String.format("let javaNewValue = try! newValue.javaPrimitive()"));
            }else{
                swiftWriter.emitStatement(String.format("let javaNewValue = try! newValue.javaObject()"));
            }

            swiftWriter.emitStatement("defer {");
            swiftWriter.emitStatement("if let localRef = javaNewValue {");
            swiftWriter.emitStatement("JNI.DeleteLocalRef(localRef)");
            swiftWriter.emitStatement("}");
            swiftWriter.emitStatement("}");


            swiftWriter.emitStatement(String.format("JNI.api.%s(JNI.env,%s,%s.javaField%s, javaNewValue)",fieldSwiftType.setFieldValue(isStatic),(isStatic ? "javaClass" : "jniObject"),swiftType,fieldName));
            swiftWriter.emitStatement("}");
        }
        swiftWriter.emitStatement("}");
    }

    @Override
    public String toString(String javaClassname) {
        return null;
    }
}
