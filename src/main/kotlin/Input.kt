import java.awt.event.*

class Input(gameContainer: GameContainer) : KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private var gc: GameContainer

    private val NUM_KEYS = 256
    private var keys: BooleanArray = BooleanArray(NUM_KEYS)
    private var keysLast: BooleanArray = BooleanArray(NUM_KEYS)

    private val NUM_BUTTONS = 5
    private var buttons: BooleanArray = BooleanArray(NUM_KEYS)
    private var buttonsLast: BooleanArray = BooleanArray(NUM_KEYS)

    var mouseX: Int = 0
    var mouseY: Int = 0
    var scroll: Int = 0

    init {
        gc = gameContainer
        gc.window.canvas.addKeyListener(this)
        gc.window.canvas.addMouseListener(this)
        gc.window.canvas.addMouseMotionListener(this)
        gc.window.canvas.addMouseWheelListener(this)
    }

    fun update() {
        for (i in 0 until NUM_KEYS){
            keysLast[i] = keys[i]
        }
        for (i in 0 until NUM_BUTTONS){
            buttonsLast[i] = buttons[i]
        }
    }

    fun isKey(keyCode: Int): Boolean {
        return keys[keyCode]
    }

    fun isKeyUp(keyCode: Int): Boolean {
        return !keys[keyCode] && keysLast[keyCode]
    }

    fun isKeyDown(keyCode: Int): Boolean {
        return keys[keyCode] && !keysLast[keyCode]
    }

    fun isButton(button: Int): Boolean {
        return buttons[button]
    }

    fun isButtonUp(button: Int): Boolean {
        return !buttons[button] && keysLast[button]
    }

    fun isButtonDown(button: Int): Boolean {
        return buttons[button] && !keysLast[button]
    }

    override fun keyTyped(e: KeyEvent?) {
        TODO("Not yet implemented")
    }

    override fun keyPressed(e: KeyEvent?) {
        if (e != null) {
            keys[e.keyCode] = true
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        if (e != null) {
            keys[e.keyCode] = false
        }
    }

    override fun mouseClicked(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mousePressed(e: MouseEvent?) {
        if (e != null) {
            buttons[e.button] = true
        }
    }

    override fun mouseReleased(e: MouseEvent?) {
        if (e != null) {
            buttons[e.button] = false
        }
    }

    override fun mouseEntered(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseExited(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseDragged(e: MouseEvent?) {
        if (e != null) {
            mouseX = (e.x / gc.scale).toInt()
            mouseY = (e.y / gc.scale).toInt()
        }
    }

    override fun mouseMoved(e: MouseEvent?) {
        if (e != null) {
            mouseX = (e.x / gc.scale).toInt()
            mouseY = (e.y / gc.scale).toInt()
        }
    }

    override fun mouseWheelMoved(e: MouseWheelEvent?) {
        if (e != null) {
            scroll = e.wheelRotation
        }
    }
}