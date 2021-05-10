
import java_swift

open class Window: JavaObject{

    static public func from(javaObject: jobject) throws -> Window {
        return Window(javaObject: javaObject)
    }

    // Create java object with native pointer
    public func javaObject() throws -> jobject {
        fatalError("Should not be needed")
    }

    public func release() {
        Unmanaged.passUnretained(self).release()
    }

    public func retain() {
        _ = Unmanaged.passUnretained(self).retain()
    }

    public var jniObject: jobject{
        get{
            if self.javaObject == nil{
                self.javaObject = try! javaObject()
            }
            return self.javaObject!
        }
    }

    public func returnString(_ result: String?){

    }
}



public class WindowEnclosed: JavaObject {

    static public func from(javaObject: jobject) throws -> WindowEnclosed {
        return WindowEnclosed(javaObject: javaObject)
    }

    // Create java object with native pointer
    public func javaObject() throws -> jobject {
        fatalError("Should not be needed")
    }

    public func release() {
        Unmanaged.passUnretained(self).release()
    }

    public func retain() {
        _ = Unmanaged.passUnretained(self).retain()
    }

    public var jniObject: jobject{
        get{
            if self.javaObject == nil{
                self.javaObject = try! javaObject()
            }
            return self.javaObject!
        }
    }
    
}
