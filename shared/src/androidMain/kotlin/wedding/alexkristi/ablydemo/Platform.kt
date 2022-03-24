package wedding.alexkristi.ablydemo

import kotlinx.coroutines.flow.MutableStateFlow

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    private val _message = MutableStateFlow("")
    actual val message: CommonFlow<String> = _message.asCommonFlow()
}