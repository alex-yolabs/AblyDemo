//
//  ViewController.swift
//  iosApp
//
//  Created by Alex Huang on 3/24/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import UIKit
import shared

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .red
        let foo = Greeting()
        foo.message.watchString { message in
            NSLog("🎥🙏🙏🙏 \(message)")
        }
    }

}

extension CommonFlow where T == NSString {
    func watchString(block: @escaping (String) -> Void) -> Ktor_ioCloseable {
        watch { s in block(s.orEmpty()) }
    }
}


extension CommonFlow where T == KotlinBoolean {
    func watchBool(block: @escaping (Bool) -> Void) -> Ktor_ioCloseable {
        watch { b in block(b?.toBool() ?? false) }
    }
}

extension Optional where Wrapped == NSString {
    func orEmpty() -> String {
        switch self {
        case .none:
            return ""
        case let .some(nsString):
            return nsString as String
        }
    }
}

extension KotlinBoolean {
    func toBool() -> Bool {
        boolValue
    }
}
