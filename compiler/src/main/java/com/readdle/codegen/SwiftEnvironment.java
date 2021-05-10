package com.readdle.codegen;

class SwiftEnvironment {

    static class Type {
        String swiftType;
        String javaType;
        String swiftConstructorType;

        Type(String swiftType, String javaType) {
            this.swiftType = swiftType;
            this.javaType = javaType;
            this.swiftConstructorType = swiftType;
        }

        Type(String swiftType, String javaType, String swiftConstructorType) {
            this.swiftType = swiftType;
            this.javaType = javaType;
            this.swiftConstructorType = swiftConstructorType;
        }

        @Override
        public String toString() {
            return "Type{" +
                    "swiftType='" + swiftType + '\'' +
                    ", javaType='" + javaType + '\'' +
                    '}';
        }

        boolean isPrimitiveType() {
            return swiftType.equals("Bool") ||
                    swiftType.equals("Int") ||
                    swiftType.equals("Int8") ||
                    swiftType.equals("Int16") ||
                    swiftType.equals("Int32") ||
                    swiftType.equals("Int64") ||
                    swiftType.equals("UInt") ||
                    swiftType.equals("UInt8") ||
                    swiftType.equals("UInt16") ||
                    swiftType.equals("UInt32") ||
                    swiftType.equals("UInt64") ||
                    swiftType.equals("Float") ||
                    swiftType.equals("Double");
        }

        String javaSigType(boolean isOptional) {
            if (isOptional) {
                return "jobject";
            }
            else {
                switch (swiftType) {
                    case "Bool":
                        return "jboolean";
                    case "Int":
                    case "Int32":
                    case "UInt":
                    case "UInt32":
                        return "jint";
                    case "Int8":
                    case "UInt8":
                        return "jbyte";
                    case "Int16":
                    case "UInt16":
                        return "jshort";
                    case "Int64":
                    case "UInt64":
                        return "jlong";
                    case "Float":
                        return "jfloat";
                    case "Double":
                        return "jdouble";
                    default:
                        return "jobject";
                }
            }
        }

        String returnTypeFunc(boolean isOptional) {
            if (isOptional) {
                return "CallObjectMethod";
            }
            else {
                switch (swiftType) {
                    case "Bool":
                        return "CallBooleanMethod";
                    case "Int":
                    case "Int32":
                    case "UInt":
                    case "UInt32":
                        return "CallIntMethod";
                    case "Int8":
                    case "UInt8":
                        return "CallByteMethod";
                    case "Int16":
                    case "UInt16":
                        return "CallShortMethod";
                    case "Int64":
                    case "UInt64":
                        return "CallLongMethod";
                    case "Float":
                        return "CallFloatMethod";
                    case "Double":
                        return "CallDoubleMethod";
                    default:
                        return "CallObjectMethod";
                }
            }
        }

        String staticReturnTypeFunc(boolean isOptional) {
            if (isOptional) {
                return "CallStaticObjectMethod";
            }
            else {
                switch (swiftType) {
                    case "Bool":
                        return "CallStaticBooleanMethod";
                    case "Int":
                    case "Int32":
                    case "UInt":
                    case "UInt32":
                        return "CallStaticIntMethod";
                    case "Int8":
                    case "UInt8":
                        return "CallStaticByteMethod";
                    case "Int16":
                    case "UInt16":
                        return "CallStaticShortMethod";
                    case "Int64":
                    case "UInt64":
                        return "CallStaticLongMethod";
                    case "Float":
                        return "CallStaticFloatMethod";
                    case "Double":
                        return "CallStaticDoubleMethod";
                    default:
                        return "CallStaticObjectMethod";
                }
            }
        }

        String getFieldValue(boolean isStatic) {
            String type;
            switch (swiftType) {
                case "Bool":
                    type = "Boolean";
                    break;
                case "Int":
                case "Int32":
                case "UInt":
                case "UInt32":
                    type = "Int";
                    break;
                case "Int8":
                case "UInt8":
                    type = "Byte";
                    break;
                case "Int16":
                case "UInt16":
                    type = "Short";
                    break;
                case "Int64":
                case "UInt64":
                    type = "Long";
                    break;
                case "Float":
                    type = "Float";
                    break;
                case "Double":
                    type = "Double";
                    break;
                default:
                    type = "Object";
                    break;
            }
            return String.format("Get%s%sField", (isStatic ? "Static":""), type);
        }

        String setFieldValue(boolean isStatic) {
            String type;
            switch (swiftType) {
                case "Bool":
                    type = "Boolean";
                    break;
                case "Int":
                case "Int32":
                case "UInt":
                case "UInt32":
                    type = "Int";
                    break;
                case "Int8":
                case "UInt8":
                    type = "Byte";
                    break;
                case "Int16":
                case "UInt16":
                    type = "Short";
                    break;
                case "Int64":
                case "UInt64":
                    type = "Long";
                    break;
                case "Float":
                    type = "Float";
                    break;
                case "Double":
                    type = "Double";
                    break;
                default:
                    type = "Object";
                    break;
            }
            return String.format("Set%s%sField", (isStatic ? "Static":""), type);
        }

        Type makeUnsigned() {
            switch (swiftType) {
                case "Int":
                case "Int8":
                case "Int16":
                case "Int32":
                case "Int64":
                    return new Type("U" + swiftType, javaType, "U" + swiftConstructorType);
                default:
                    throw new IllegalStateException(swiftType + " can't be unsigned");
            }
        }

        public String primitiveDefaultValue() {
            switch (swiftType) {
                case "Bool":
                    return "jboolean(JNI_FALSE)";
                case "Int":
                case "Int32":
                case "UInt":
                case "UInt32":
                    return "jint(0)";
                case "Int8":
                case "UInt8":
                    return "jbyte(0)";
                case "Int16":
                case "UInt16":
                    return "jshort(0)";
                case "Int64":
                case "UInt64":
                    return "jlong(0)";
                case "Float":
                    return "jfloat(0)";
                case "Double":
                    return "jdouble(0)";
                default:
                    throw new IllegalStateException(swiftType + " is not primitive");
            }
        }
    }

}
