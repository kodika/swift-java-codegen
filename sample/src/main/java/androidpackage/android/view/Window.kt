package androidpackage.android.view

import com.readdle.codegen.anotation.SwiftReference
import com.readdle.codegen.anotation.SwiftCallbackFunc

@SwiftReference
class Window {


    @SwiftCallbackFunc
    fun callbackWithoutArgs(){

    }

    @SwiftCallbackFunc
    fun callbackWithArgEnclosed(arg: Window.WindowEnclosed){

    }

    private external fun returnString( result: String)

    @SwiftReference
    class WindowEnclosed private constructor(){
        internal val nativePointer: Long = 0L

        // Swift JNI release method
        external fun release()
    }
}
