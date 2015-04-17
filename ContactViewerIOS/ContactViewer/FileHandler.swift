//
//  FileHandler.swift
//  ContactViewer
//
//  Created by user28142 on 4/15/15.
//  Copyright (c) 2015 tinyapollo. All rights reserved.
//

import Foundation

class FileHandler: NSObject {
    
    let file = "contacts.json"
    var textRead : NSData!
    
    func readFromFile () -> NSData {
        if let dirs : [String] = NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.AllDomainsMask, true) as? [String] {
        let dir = dirs[0] //documents directory
        let path = dir.stringByAppendingPathComponent(file);
//        textRead = String(contentsOfFile: path, encoding: NSUTF8StringEncoding, error: nil)!
        textRead = NSData(contentsOfFile: path)
    } else {
        return NSData()
        }
        return textRead
        
    }
    
    func writeToFile (dataWrite:NSData) {
        if let dirs : [String] = NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.AllDomainsMask, true) as? [String] {
        let dir = dirs[0] //documents directory
        let path = dir.stringByAppendingPathComponent(file);
        
        dataWrite.writeToFile(path, atomically: false)
    } else {
        println("Unable to write to file.")
        }
        
    }
    
    func convertToJSON(data:[[String:String]]) -> NSData {
        return NSJSONSerialization.dataWithJSONObject(data, options: nil, error: nil)!
    }
    
    func convertFromJSON() {
        
    }
    
    func convertJSONToString(data: NSData) -> String {
        return NSString(data: data, encoding: NSUTF8StringEncoding)!
    }
}