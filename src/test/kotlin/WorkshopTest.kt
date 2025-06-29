import org.example.Product
import org.example.celsiusToFahrenheit
import org.example.kilometersToMiles
import org.example.calculateTotalPriceOver500
import org.example.calculateTotalPriceOver500Sequence
import kotlin.test.Test
import kotlin.test.assertEquals

/* Extension Functions สำหรับทดสอบ */
fun List<Product>.countElectronicsOver500(): Int {
    // นับจำนวนผลิตภัณฑ์ในหมวด "Electronics" ที่มีราคาเกิน 500.0
    return this.count { it.category == "Electronics" && it.price > 500.0 }
}

fun List<Product>.sumElectronicsOver500(): Double {
    // คำนวณผลรวมราคาของผลิตภัณฑ์ในหมวด "Electronics" ที่มีราคาเกิน 500.0 โดยใช้ Sequence
    return this.asSequence()
        .filter { it.category == "Electronics" && it.price > 500.0 }
        .sumOf { it.price }
}

class WorkshopTest {
    // ข้อมูลทดสอบส่วนกลาง
    private val testProducts = listOf(
        Product("Laptop", 35000.0, "Electronics"),
        Product("Smartphone", 25000.0, "Electronics"),
        Product("T-shirt", 450.0, "Apparel"),
        Product("Monitor", 7500.0, "Electronics"),
        Product("Keyboard", 499.0, "Electronics"),
        Product("Jeans", 1200.0, "Apparel"),
        Product("Headphones", 1800.0, "Electronics")
    )

    // ================= Unit Converter Tests =================
    @Test
    fun `convert 20C to Fahrenheit`() {
        // Arrange
        val celsius = 20.0
        val expectedFahrenheit = 68.0

        // Act
        val actualFahrenheit = celsiusToFahrenheit(celsius)

        // Assert
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001)
    }

    @Test
    fun `convert 1km to miles`() {
        // Arrange
        val kilometers = 1.0
        val expectedMiles = 0.621371

        // Act
        val actualMiles = kilometersToMiles(kilometers)

        // Assert
        assertEquals(expectedMiles, actualMiles, 0.000001)
    }

    // ================= Data Analysis Tests =================
    @Test
    fun `calculate total electronics price over 500 normally`() {
        // Arrange
        val expected = 35000.0 + 25000.0 + 7500.0 + 1800.0 // 69300.0

        // Act
        val actual = calculateTotalPriceOver500(testProducts, "Electronics", 500.0)

        // Assert
        assertEquals(expected, actual, 0.001)
    }

    @Test
    fun `calculate total electronics price over 500 using sequence`() {
        // Arrange
        val expected = 69300.0

        // Act
        val actual = calculateTotalPriceOver500Sequence(testProducts, "Electronics", 500.0)

        // Assert
        assertEquals(expected, actual, 0.001)
    }

    @Test
    fun `count electronics over 500 using extension`() {
        // Arrange
        val expectedCount = 4

        // Act
        val actualCount = testProducts.countElectronicsOver500()

        // Assert
        assertEquals(expectedCount, actualCount)
    }

    @Test
    fun `sum electronics over 500 using extension`() {
        // Arrange
        val expectedSum = 69300.0

        // Act
        val actualSum = testProducts.sumElectronicsOver500()

        // Assert
        // Assert
        assertEquals(expectedSum, actualSum, 0.001)
    }
}
