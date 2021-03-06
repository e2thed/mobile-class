//
//  MasterViewController.swift
//  ContactViewer
//
//  Created by Edward Castillo on 4/11/15.
//  Copyright (c) 2015 seng5199-3. All rights reserved.
//

import UIKit
//import Foundation


class MasterViewController: UITableViewController {

    var detailViewController: DetailViewController? = nil
    var contacts = [Contact]()
    let fh = FileHandler()
    

    override func awakeFromNib() {
        super.awakeFromNib()
        if UIDevice.currentDevice().userInterfaceIdiom == .Pad {
            self.clearsSelectionOnViewWillAppear = false
            self.preferredContentSize = CGSize(width: 320.0, height: 600.0)
        }
        let contact1 = Contact(name: "Walter White", phone: "612-664-1234", title: "Chemist", email: "walt@bb.com", twitterId: "ww")
        let contact2 = Contact(name: "Skyler White", phone: "612-664-1235", title: "Mom", email: "sky@bb.com", twitterId: "skyblue")
        let contact3 = Contact(name: "Jessie Pinkman", phone: "612-664-1236", title: "Junkie", email: "jessie@bb.com", twitterId: "jp")
        contacts.append(contact1)
        contacts.append(contact2)
        contacts.append(contact3)
        
        var details = [[String:String]]()
        
        for var i = 0; i < contacts.count; ++i {
            details.append(["name":contacts[i].name
                ,"phone":contacts[i].phone
                ,"title":contacts[i].title
                ,"email":contacts[i].email
                ,"twitterId":contacts[i].twitterId])
        }
        
        
        
//        for var index = 0; index < contacts.count; ++index {
//            if index == 0 {
//                details = [["name":contacts[index].name,"phone":contacts[index].phone,"title":contacts[index].title,"email":contacts[index].email,"twitterId":contacts[index].twitterId]]
//            } else {
//                details.append(["name":contacts[index].name,"phone":contacts[index].phone,"title":contacts[index].title,"email":contacts[index].email,"twitterId":contacts[index].twitterId])
//            }
//        }
        
        ////This is for the initial creation of the file
        let myData = fh.convertToJSON(details)
        fh.writeToFile(myData)
        
        print(fh.readFromFile())
        
        var data = fh.readFromFile()
        
        if let json: NSDictionary = NSJSONSerialization.JSONObjectWithData(data, options: NSJSONReadingOptions.MutableContainers, error: nil) as? NSDictionary {
            if let items = json["items"] as? NSArray {
            for item in items {
            // construct your model objects here
            }
            }
        }
        
        
        
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.navigationItem.leftBarButtonItem = self.editButtonItem()

        let addButton = UIBarButtonItem(barButtonSystemItem: .Add, target: self, action: "insertNewObject:")
        self.navigationItem.rightBarButtonItem = addButton
        if let split = self.splitViewController {
            let controllers = split.viewControllers
            self.detailViewController = controllers[controllers.count-1].topViewController as? DetailViewController
        }
        //let documentsPath = NSSearchPathForDirectoriesInDomains(DocumentDirectory, .UserDomainMask, true)[0] as NSString
        //let path = NSBundle.mainBundle().pathForResource("fileName", ofType: "fileExt")
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func insertNewObject(sender: AnyObject) {
        //objects.insert(NSDate(), atIndex: 0)
        //let indexPath = NSIndexPath(forRow: 0, inSection: 0)
        //self.tableView.insertRowsAtIndexPaths([indexPath], withRowAnimation: .Automatic)
        
    }

    // MARK: - Segues

    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "showDetail" {
            if let indexPath = self.tableView.indexPathForSelectedRow() {
                let object = contacts[indexPath.row] as Contact
                let controller = (segue.destinationViewController as UINavigationController).topViewController as DetailViewController
                controller.detailItem = object
                controller.navigationItem.leftBarButtonItem = self.splitViewController?.displayModeButtonItem()
                controller.navigationItem.leftItemsSupplementBackButton = true
            }
        }
    }

    // MARK: - Table View

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return contacts.count
    }

    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath) as UITableViewCell

        let object = contacts[indexPath.row] as Contact
        cell.textLabel!.text = object.name
        //hack to avoid making custom cell
        cell.detailTextLabel!.text = "\(object.phone)       \(object.title)"
        return cell
    }

    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }

    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if editingStyle == .Delete {
            contacts.removeAtIndex(indexPath.row)
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
        } else if editingStyle == .Insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view.
        }
    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        let evc = EditViewController(nibName: "EditViewController", bundle: nil)
        self.navigationController?.pushViewController(evc, animated: true)
    }


}

