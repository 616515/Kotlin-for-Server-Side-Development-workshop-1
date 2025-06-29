import org.example.Product
import org.example.celsiusToFahrenheit
import org.example.kilometersToMiles
import org.example.calculateTotalPriceOver500
import org.example.calculateTotalPriceOver500Sequence
import kotlin.test.Test
import kotlin.test.assertEquals

class WorkshopTest {
    // ข้อมูลทดสอบส่วนกลางสำหรับการคำนวณสินค้า
    private val testProducts = listOf(
        Product("Laptop", 35000.0, "Electronics"),
        Product("Smartphone", 25000.0, "Electronics"),
        Product("T-shirt", 450.0, "Apparel"),
        Product("Monitor", 7500.0, "Electronics"),
        Product("Keyboard", 499.0, "Electronics"),
        Product("Jeans", 1200.0, "Apparel"),
        Product("Headphones", 1800.0, "Electronics")
    )

    // 1. ทดสอบการแปลง 1 กิโลเมตรเป็นไมล์
    @Test
    fun `test kilometersToMiles with one kilometer`() {
        // Arrange
        val kilometers = 1.0
        val expectedMiles = 0.621371

        // Act
        val actualMiles = kilometersToMiles(kilometers)

        // Assert
        assertEquals(expectedMiles, actualMiles, 0.000001)
    }

    // 2. ทดสอบการคำนวณราคารวมของสินค้า Electronics ที่ราคาเกิน 500
    @Test
    fun `test calculateTotalElectronicsPriceOver500`() {
        // Arrange (35000 + 25000 + 7500 + 1800)
        val expectedTotal = 35000.0 + 25000.0 + 7500.0 + 1800.0

        // Act
        val actualTotal = calculateTotalPriceOver500(testProducts, "Electronics", 500.0)

        // Assert
        assertEquals(expectedTotal, actualTotal, 0.001)
    }

    // 3. ทดสอบการแปลงอุณหภูมิเซลเซียสเป็นฟาเรนไฮต์กับค่าติดลบ
    @Test
    fun `test celsiusToFahrenheit with negative value`() {
        // Arrange
        val celsius = -15.0
        val expectedFahrenheit = 5.0

        // Act
        val actualFahrenheit = celsiusToFahrenheit(celsius)

        // Assert
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001)
    }

    // 4. ทดสอบการแปลงอุณหภูมิเซลเซียสเป็นฟาเรนไฮต์กับค่าเป็นบวก
    @Test
    fun `test celsiusToFahrenheit with positive value`() {
        // Arrange
        val celsius = 25.0
        val expectedFahrenheit = 77.0

        // Act
        val actualFahrenheit = celsiusToFahrenheit(celsius)

        // Assert
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001)
    }

    // 5. ทดสอบการแปลงอุณหภูมิเซลเซียสเป็นฟาเรนไฮต์กับค่าเป็นศูนย์
    @Test
    fun `test celsiusToFahrenheit with zero`() {
        // Arrange
        val celsius = 0.0
        val expectedFahrenheit = 32.0

        // Act
        val actualFahrenheit = celsiusToFahrenheit(celsius)

        // Assert
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001)
    }

    // 6. ทดสอบการนับจำนวนสินค้าทั้งหมด (ทั้งหมด 7 ชิ้น)
    @Test
    fun `test sum of size`() {
        // Arrange
        val expectedSize = 7

        // Act
        val actualSize = testProducts.size

        // Assert
        assertEquals(expectedSize, actualSize)
    }
}
