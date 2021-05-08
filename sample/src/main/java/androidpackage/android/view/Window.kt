package androidpackage.android.view

import com.readdle.codegen.anotation.SwiftReference
import com.readdle.codegen.anotation.SwiftCallbackFunc

@SwiftReference
class Window private constructor() {

    internal val nativePointer: Long = 0L

    // Swift JNI release method
    external fun release()

    @SwiftCallbackFunc
    fun callbackWithoutArgs(){

    }

    private external fun returnString( result: String)
}
