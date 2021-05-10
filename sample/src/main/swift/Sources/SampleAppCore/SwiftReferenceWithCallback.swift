//
//  SwiftReferenceWithCallback.swift
//
//
//  Created by Kostas Antonopoulos on 5/4/21.
//

import Foundation
import java_swift

public class SwiftReferenceWithCallback: JavaObject {

    public convenience init(){
        self.init(javaObject: nil)
        
        //Available only if create one Package and not Generated module that depends on SampleAppCore
        //self.javaObject = self.javaObject()
    }

    public func returnString(_ result: String?){

    }
}



public class SwiftReferenceEnclosed: JavaObject {

}