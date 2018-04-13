package pinch.bubble.onboarding.pages.bubble

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import pinch.bubble.model.Bubble

class BubbleView : View {

    private var bubbleList: List<Bubble> = ArrayList<Bubble>()

    private val diameters = ArrayList<Float>()
    private val radii = ArrayList<PointF>()

    private val paint1 = Paint()
    private val paint2 = Paint()
    private val paint3 = Paint()

    private val circlePaint1 = Paint()
    private val circlePaint2 = Paint()
    private val circlePaint3 = Paint()

    private var textPaint1 = Paint()
    private var textPaint2 = Paint()
    private var textPaint3 = Paint()

    private var textBounds = Rect()

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    fun init(bubbles: List<Bubble>) {
        bubbleList = bubbles

        paint1.color = Color.parseColor(bubbleList[0].color)
        paint2.color = Color.parseColor(bubbleList[1].color)
        paint3.color = Color.parseColor(bubbleList[2].color)

        circlePaint1.color = Color.parseColor("#1A${bubbleList[0].color.substring(1, bubbleList[0].color.length)}")
        circlePaint2.color = Color.parseColor("#1A${bubbleList[1].color.substring(1, bubbleList[1].color.length)}")
        circlePaint3.color = Color.parseColor("#1A${bubbleList[2].color.substring(1, bubbleList[2].color.length)}")

        paint1.style = Paint.Style.STROKE
        paint1.strokeWidth = 20f
        paint2.style = Paint.Style.STROKE
        paint2.strokeWidth = 20f
        paint3.style = Paint.Style.STROKE
        paint3.strokeWidth = 20f


        textPaint1.color = paint1.color
        textPaint1.isAntiAlias = true
        textPaint1.textSize = 50f

        textPaint2.color = paint2.color
        textPaint2.isAntiAlias = true
        textPaint2.textSize = 50f

        textPaint3.color = paint3.color
        textPaint3.isAntiAlias = true
        textPaint3.textSize = 50f

        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if(bubbleList.isEmpty()) return

        if (diameters.isEmpty()) {
            calcDiameters(canvas.width)
        }

        if (radii.isEmpty()) {
            calcRadii(canvas.width / 2f)
        }

        // draw circles
        canvas.drawCircle(radii[0].x, radii[0].y + 20, diameters[0] / 2, paint1)
        canvas.drawCircle(radii[1].x, radii[1].y + 20, diameters[1] / 2, paint2)
        canvas.drawCircle(radii[2].x, radii[2].y + 20, diameters[2] / 2, paint3)

        canvas.drawCircle(radii[0].x, radii[0].y + 20, diameters[0] / 2, circlePaint1)
        canvas.drawCircle(radii[1].x, radii[1].y + 20, diameters[1] / 2, circlePaint2)
        canvas.drawCircle(radii[2].x, radii[2].y + 20, diameters[2] / 2, circlePaint3)

        // draw text
        drawText(bubbleList[0].name, canvas, radii[0].x, radii[0].y, textPaint1)
        drawText(bubbleList[1].name, canvas, radii[1].x, radii[1].y, textPaint2)
        drawText(bubbleList[2].name, canvas, radii[2].x, radii[2].y, textPaint3)
    }

    private fun drawText(text: String, canvas: Canvas, x: Float, y: Float, textPaint: Paint) {
        textPaint.getTextBounds(text, 0, text.length, textBounds)
        canvas.drawText(text, x - ((textBounds.right - textBounds.left) / 2), y + ((textBounds.bottom - textBounds.top) / 2), textPaint)
    }

    private fun getCenterX(width: Int, diameterLeft: Float, diameterRight: Float): Float {
        val groupWidth = diameterLeft + diameterRight
        val restWidth = (width - groupWidth)
        return restWidth + (groupWidth / 2)
    }

    private fun calcDiameters(width: Int) {
        (0..2).forEach {
            val bubbleValue = bubbleList[it].value
            diameters.add((bubbleValue * .6f * width) / 100)
        }
    }

    private fun calcRadii(centerX: Float) {
        // radius 1
        radii.add(PointF(centerX - (diameters[0] / 2), diameters[0] / 2))

        // radius 2
        radii.add(PointF(centerX + (diameters[1] / 2), diameters[0] - (diameters[1] / 2)))

        // radius 3
        radii.add(PointF(centerX, diameters[0] - diameters[2] / 6 + (diameters[2] / 2)))
    }

}