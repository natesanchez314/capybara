import java.awt.event.KeyEvent

class GameContainer : Runnable {

    private lateinit var thread: Thread
    lateinit var window: Window
    private lateinit var renderer: Renderer
    private lateinit var input: Input

    private var running = false
    private val UPDATE_CAP = 1.0 / 60.0
    val WIDTH: Int = 800
    val HEIGHT: Int = 600
    var scale: Double = 1.0
    var title = "Capybara game engine"

    fun start() {
        window = Window(this)
        renderer = Renderer(this)
        input = Input(this)
        thread = Thread(this)
        thread.run()
    }

    fun stop() {
        thread.join()
    }

    override fun run() {
        running = true
        var render = false

        var firstTime = 0.0
        var lastTime = System.nanoTime() / 1000000000.0
        var passedTime = 0.0
        var unprocessedTime = 0.0

        var frameTime = 0.0
        var frames = 0
        var fps = 0

        while (running) {
            render = false

            firstTime = System.nanoTime() / 1000000000.0
            passedTime = firstTime - lastTime
            lastTime = firstTime

            unprocessedTime += passedTime
            frameTime += passedTime

            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP
                render = true

                update()

                if (frameTime >= 1.0) {
                    frameTime = 0.0
                    fps = frames
                    frames = 0
                }
            }

            if (render) {
                render()
            } else {
                try {
                    Thread.sleep(1)
                } catch(e: InterruptedException){
                    e.printStackTrace()
                }
            }
        }
        dispose()
    }

    private fun update() {
        input.update()
    }

    private fun render() {
        renderer.clear()
        window.update()
    }

    private fun dispose(): Unit {
        // TODO
    }
}