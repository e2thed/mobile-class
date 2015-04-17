//
//  Contact.swift
//  ContactViewer
//
//  Created by Edward Castillo on 4/11/15.
//  Copyright (c) 2015 seng5199-3. All rights reserved.
//

import Foundation

class Contact: NSObject {
    var name:String
    var title:String
    var email:String
    var phone:String
    var twitterId:String
    
    init(name:String, phone:String, title:String, email:String, twitterId:String) {
        self.name = name
        self.phone = phone
        self.title = title
        self.email = email
        self.twitterId = twitterId
    }
    
//    init(JSONString: String){
//        super.init()
//        
//        var error : NSError?
//        let JSONData = JSONString.dataUsingEncoding(NSUTF8StringEncoding, allowLossyConversion: false)
//        
//        let JDict: [[String:String]] = NSJSONSerialization.JSONObjectWithData(JSONData, options: NSJSONReadingOptions., error: &error) as NSDictionary
//        
//        for (key, value) in JDict{
//            let keyName = key as String
//            let keyValue: String = value as String
//            
//            if (self.respondsToSelector(NSSelectorFromString(keyName))) {
//                self.setValue(keyValue, forKey: keyName)
//            }
//            
//            // option 2 - use
//            // self.setValuesForKeysWithDictionary(JSONDictionary)
//            // instead of loop method above
//        }
//    }
    
}