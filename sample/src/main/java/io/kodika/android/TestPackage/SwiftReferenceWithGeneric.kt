package io.kodika.android.TestPackage

import com.readdle.codegen.anotation.SwiftCallbackFunc
import com.readdle.codegen.anotation.SwiftReference

@SwiftReference
class SwiftReferenceWithGeneric<T: Any> private constructor() {

    internal val nativePointer: Long = 0L

    // Swift JNI release method
    external fun release()

    @SwiftCallbackFunc
    fun callType(a: SwiftReferenceWithGeneric<Int>): SwiftReferenceWithGeneric<String>{
        TODO()
    }
}