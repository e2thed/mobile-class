//
//  DetailViewController.swift
//  ContactViewer
//
//  Created by Edward Castillo on 4/11/15.
//  Copyright (c) 2015 seng5199-3. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var detailDescriptionLabel: UILabel!


    var detailItem: Contact? {
        didSet {
            // Update the view.
            self.configureView()
        }
    }

    func configureView() {
        // Update the user interface for the detail item.
        if let detail: Contact = self.detailItem {
            if let label = self.detailDescriptionLabel {
                label.text = detail.name
            }
        }

    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.configureView()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

