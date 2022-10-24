import java.awt.image.DataBufferInt

class Renderer(gc: GameContainer) {

    private var pW: Int
    private var pH: Int
    private var pixels: IntArray

    init {
        pW = gc.WIDTH
        pH = gc.HEIGHT
        val dbi = gc.window.image.raster.dataBuffer as DataBufferInt
        pixels = dbi.data
    }

    fun clear() {
        for (i in pixels.indices){
            pixels[i] = 0
        }
    }
}