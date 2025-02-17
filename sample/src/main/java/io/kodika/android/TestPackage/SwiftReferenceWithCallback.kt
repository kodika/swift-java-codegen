package io.kodika.android.TestPackage

import com.readdle.codegen.anotation.*

@SwiftReference
class SwiftReferenceWithCallback private constructor() {

    internal val nativePointer: Long = 0L

    // Swift JNI release method
    external fun release()

    @SwiftReference
    class SwiftReferenceEnclosed private constructor() {
        internal val nativePointer: Long = 0L

        // Swift JNI release method
        external fun release()
    }

    @SwiftCallbackFunc
    fun callbackWithoutArgs(){

    }

    @SwiftCallbackFunc
    fun callbackWithArg(arg: String){

    }

    @SwiftCallbackFunc
    fun callbackWithEncloseArg(arg: SwiftReferenceWithCallback.SwiftReferenceEnclosed){

    }

    @SwiftCallbackFunc
    fun funcWithByteArray(b: ByteArray): ByteArray {
        TODO()
    }

    private external fun returnString( result: String)


    @SwiftProperty
    var intProperty: Int = 0

    @SwiftProperty
    val floatConstantFloatProperty: Float = 0.0F

    companion object {

        @SwiftProperty
        var staticStringProperty: String = ""

        @SwiftProperty
        val staticConstantRefProperty: SwiftReferenceWithCallback? = null
    }
}
