package org.example

// 1. กำหนด data class สำหรับเก็บข้อมูลสินค้า
data class Product(val name: String, val price: Double, val category: String)

fun main() {
    // 2. สร้างรายการสินค้าตัวอย่าง (List<Product>)
    val products = listOf(
        Product("Laptop", 35000.0, "Electronics"),
        Product("Smartphone", 25000.0, "Electronics"),
        Product("T-shirt", 450.0, "Apparel"),
        Product("Monitor", 7500.0, "Electronics"),
        Product("Keyboard", 499.0, "Electronics"),
        Product("Jeans", 1200.0, "Apparel"),
        Product("Headphones", 1800.0, "Electronics")
    )

    // แสดงรายการสินค้าทั้งหมด
    displayProducts(products)

    // หาผลรวมราคาสินค้าในหมวด 'Electronics' ที่มีราคามากกว่า 500 บาท
    val totalElecPriceOver500 = calculateTotalPriceOver500(products, "Electronics", 500.0)
    println("วิธีที่ 1: ใช้ Chaining กับ List")
    println("ผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท: $totalElecPriceOver500 บาท")
    println("--------------------------------------------------")

    // ใช้ .asSequence() เพื่อเพิ่มประสิทธิภาพ
    val totalElecPriceOver500Sequence = calculateTotalPriceOver500Sequence(products, "Electronics", 500.0)
    println("วิธีที่ 2: ใช้ .asSequence() (ขั้นสูง)")
    println("ผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท: $totalElecPriceOver500Sequence บาท")
    println("--------------------------------------------------")

    // แยกกลุ่มสินค้าตามราคา
    val groupedProducts = groupProductsByPrice(products)
    displayGroupedProducts(groupedProducts)
    println("--------------------------------------------------")

    // อภิปรายความแตกต่างระหว่าง List และ Sequence
    discussListVsSequence()
}

// ฟังก์ชันสำหรับแสดงรายการสินค้า
fun displayProducts(products: List<Product>) {
    println("รายการสินค้าทั้งหมด:")
    products.forEach { println(it) }
    println("--------------------------------------------------")
}

// ฟังก์ชันสำหรับแยกกลุ่มสินค้าตามราคา
fun groupProductsByPrice(products: List<Product>): Map<String, List<Product>> {
    return products.groupBy { product ->
        when {
            product.price >= 10000 -> "10000 บาทขึ้นไป"
            product.price <= 1000 -> "ไม่เกิน 1000 บาท"
            product.price in 1000.0..9999.0 -> "1000 - 9999 บาท"
            else -> "อื่นๆ"
        }
    }
}

// ฟังก์ชันสำหรับแสดงกลุ่มสินค้าตามราคา
fun displayGroupedProducts(groupedProducts: Map<String, List<Product>>) {
    println("กลุ่มสินค้าตามราคา:")
    groupedProducts.forEach { (priceGroup, products) ->
        println("$priceGroup:")
        products.forEach { product ->
            println("- ${product.name} (${product.price} บาท)")
        }
        println()
    }
}

// ฟังก์ชันสำหรับคำนวณผลรวมราคาสินค้าในหมวดที่กำหนด โดยใช้ filter และ map
fun calculateTotalPriceOver500(products: List<Product>, category: String, priceThreshold: Double): Double {
    return products
        .filter { it.category == category && it.price > priceThreshold }
        .map { it.price }
        .reduce { acc, price -> acc + price }
}

// ฟังก์ชันสำหรับคำนวณผลรวมราคาสินค้าในหมวดที่กำหนด โดยใช้ Sequence
fun calculateTotalPriceOver500Sequence(products: List<Product>, category: String, priceThreshold: Double): Double {
    return products
        .asSequence()
        .filter { it.category == category && it.price > priceThreshold }
        .map { it.price }
        .reduce { acc, price -> acc + price }
}

// ฟังก์ชันสำหรับอภิปรายความแตกต่างระหว่าง List และ Sequence
fun discussListVsSequence() {
    println("อภิปรายความแตกต่างระหว่าง List และ Sequence:")
    println("1. List Operations (วิธีที่ 1):")
    println("   - ทุกครั้งที่เรียกใช้ operation (เช่น filter, map) จะมีการสร้าง Collection (List) ใหม่ขึ้นมาเพื่อเก็บผลลัพธ์ของขั้นนั้นๆ")
    println("   - ตัวอย่าง: filter ครั้งแรกสร้าง List ใหม่ -> filter ครั้งที่สองสร้าง List ใหม่อีกใบ -> map สร้าง List สุดท้าย -> sum ทำงาน")
    println("   - เหมาะกับข้อมูลขนาดเล็ก เพราะเข้าใจง่าย แต่ถ้าข้อมูลมีขนาดใหญ่มาก (ล้าน records) จะสิ้นเปลืองหน่วยความจำและเวลาในการสร้าง Collection ใหม่ๆ ซ้ำซ้อน")
    println()
    println("2. Sequence Operations (วิธีที่ 2):")
    println("   - ใช้การประมวลผลแบบ 'Lazy' (ทำเมื่อต้องการใช้ผลลัพธ์จริงๆ)")
    println("   - operations ทั้งหมด (filter, map) จะไม่ทำงานทันที แต่จะถูกเรียงต่อกันไว้")
    println("   - ข้อมูลแต่ละชิ้น (each element) จะไหลผ่าน Pipeline ทั้งหมดทีละชิ้น จนจบกระบวนการ")
    println("   - เช่น: 'Laptop' จะถูก filter category -> filter price -> map price จากนั้น 'Smartphone' ถึงจะเริ่มทำกระบวนการเดียวกัน")
    println("   - จะไม่มีการสร้าง Collection กลางทาง ทำให้ประหยัดหน่วยความจำและเร็วกว่ามากสำหรับชุดข้อมูลขนาดใหญ่ เพราะทำงานกับข้อมูลทีละชิ้นและทำทุกขั้นตอนให้เสร็จในรอบเดียว")
    println("   - การคำนวณจะเกิดขึ้นเมื่อมี 'Terminal Operation' มาเรียกใช้เท่านั้น (ในที่นี้คือ .reduce())")
}
