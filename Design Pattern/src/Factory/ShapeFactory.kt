package Factory

enum class ShapeType {
    RECTANGLE,
    TRIANGLE,
    CIRCLE
}

abstract class Shape {
    abstract val type: ShapeType
    abstract fun getArea(): Double
}

class Rectangle(
    private val width: Double,
    private val height: Double
) : Shape() {
    override val type = ShapeType.RECTANGLE

    override fun getArea(): Double {
        return width * height
    }
}

class Triangle(
    private val base: Double,
    private val height: Double
) : Shape() {
    override val type = ShapeType.TRIANGLE

    override fun getArea(): Double {
        return 0.5 * base * height
    }
}

class Circle(
    private val radius: Double
) : Shape() {
    override val type = ShapeType.CIRCLE

    override fun getArea(): Double {
        return Math.PI * radius * radius
    }
}

object ShapeFactory {
    fun createShape(type: ShapeType, vararg dimensions: Double): Shape {
        return when (type) {
            ShapeType.RECTANGLE -> {
                if (dimensions.size != 2) throw IllegalArgumentException("사각형은 width, height 2개의 인자를 받습니다.")
                Rectangle(
                    width = dimensions[0],
                    height = dimensions[1]
                )
            }
            ShapeType.TRIANGLE -> {
                if (dimensions.size != 2) throw IllegalArgumentException("삼각형은 base, height 2개의 인자를 받습니다.")
                Triangle(
                    base = dimensions[0],
                    height = dimensions[1]
                )
            }
            ShapeType.CIRCLE -> {
                if (dimensions.size != 1) throw IllegalArgumentException("원은 radius 1개의 인자를 받습니다.")
                Circle(radius = dimensions[0])
            }
        }
    }
}

fun main() {
    val rectangle = ShapeFactory.createShape(ShapeType.RECTANGLE, 4.0, 3.0)
    val triangle = ShapeFactory.createShape(ShapeType.TRIANGLE, 4.0, 3.0)
    println("사각형의 넓이: ${rectangle.getArea()}, 삼각형의 넓이: ${triangle.getArea()}")

    val circle = ShapeFactory.createShape(ShapeType.CIRCLE, 4.0, 3.0)
    println("원의 넓이: ${circle.getArea()}")
}