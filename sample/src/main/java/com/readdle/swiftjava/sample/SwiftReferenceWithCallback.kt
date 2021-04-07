package com.readdle.swiftjava.sample

import com.readdle.codegen.anotation.SwiftReference
import com.readdle.codegen.anotation.SwiftCallbackFunc

@SwiftReference
class SwiftReferenceWithCallback private constructor() {

    internal val nativePointer: Long = 0L

    // Swift JNI release method
    external fun release()

    @SwiftCallbackFunc
    fun callbackWithoutArgs(){

    }

    @SwiftCallbackFunc
    fun callbackWithArg(arg: String){

    }

    private external fun returnString( result: String)
}
