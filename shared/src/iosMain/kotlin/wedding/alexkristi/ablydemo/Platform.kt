package wedding.alexkristi.ablydemo

import platform.UIKit.UIDevice
import cocoapods.Ably.ARTRealtime
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import platform.Foundation.NSLog

actual class Platform actual constructor() {
    private val key = "ably key"
    private val ably = ARTRealtime(key = key)
    private val _message = MutableSharedFlow<String>(0, 1, BufferOverflow.DROP_OLDEST)

    init {
        ably.connection().on {
            NSLog("artConnectionStateChange: $it")
        }
        val channel = ably.channels.get("some channel id")
        channel.subscribe {
            val data = it?.data as? String
            if (data != null) {
                _message.tryEmit(data)
            } else {
                NSLog("No data. artMessage: $it")
            }
        }
    }

    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    actual val message: CommonFlow<String> = _message.asCommonFlow()

}