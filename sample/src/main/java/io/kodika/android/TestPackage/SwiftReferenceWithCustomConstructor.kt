package io.kodika.android.TestPackage

import com.readdle.codegen.anotation.SwiftReference

@SwiftReference
class SwiftReferenceWithCustomConstructor {

    internal val nativePointer: Long = 0L

    // Swift JNI release method
    external fun release()
}