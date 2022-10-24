import java.awt.BorderLayout
import java.awt.Canvas
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferStrategy
import java.awt.image.BufferedImage
import javax.swing.JFrame

class Window(gc: GameContainer) {

    private lateinit var frame: JFrame
    private lateinit var image: BufferedImage
    private lateinit var canvas: Canvas
    private lateinit var bs: BufferStrategy
    private lateinit var gfx: Graphics

    init {
        image = BufferedImage(gc.WIDTH, gc.HEIGHT, BufferedImage.TYPE_INT_RGB)
        val d = Dimension((gc.WIDTH * gc.scale).toInt(), (gc.HEIGHT * gc.scale).toInt())
        canvas = Canvas()
        canvas.preferredSize = d
        canvas.maximumSize = d
        canvas.minimumSize = d

        frame = JFrame(gc.title)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.layout = BorderLayout()
        frame.add(canvas, BorderLayout.CENTER)
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isResizable = false
        frame.isVisible = true

        canvas.createBufferStrategy(2)
        bs = canvas.bufferStrategy
        gfx = bs.drawGraphics
    }

    fun update() {
        gfx.drawImage(image,0, 0, canvas.width, canvas.height, null)
        bs.show()
    }
}