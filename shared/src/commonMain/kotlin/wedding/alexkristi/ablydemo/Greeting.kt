package wedding.alexkristi.ablydemo

class Greeting {

    private val platform = Platform()

    fun greeting(): String {
        return "Hello, ${platform.platform}!"
    }

    val message = platform.message
}