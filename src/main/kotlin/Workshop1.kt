package org.example

// Workshop #1: Simple Console Application - Unit Converter

fun main() {
    // 2. ใช้ while (true) เพื่อให้โปรแกรมทำงานวนซ้ำ
    while (true) {
        // 1. แสดงเมนูให้ผู้ใช้เลือก
        println("===== Unit Converter =====")
        println("โปรดเลือกหน่วยที่ต้องการแปลง:")
        println("1. Celsius to Fahrenheit")
        println("2. Kilometers to Miles")
        println("พิมพ์ 'exit' เพื่อออกจากโปรแกรม")
        print("เลือกเมนู (1, 2, or exit): ")

        // 2. รับข้อมูลตัวเลือกจากผู้ใช้
        val choice = readln()

        // 3. ควบคุมการทำงานด้วย when expression
        when (choice) {
            "1" -> convertCelsiusToFahrenheit()
            "2" -> convertKilometersToMiles()
            "exit" -> {
                println("ออกจากโปรแกรม...")
                return
            }
            else -> println("ตัวเลือกไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง.")
        }

        println()
    }
}

// 4. สร้างฟังก์ชันแยกสำหรับการแปลงหน่วย Celsius to Fahrenheit
fun celsiusToFahrenheit(celsius: Double): Double {
    return celsius * 9.0 / 5.0 + 32
}

// 4. สร้างฟังก์ชันแยกสำหรับการแปลงหน่วย Kilometers to Miles
fun kilometersToMiles(kilometers: Double): Double {
    return kilometers * 0.621371
}

// ฟังก์ชันสำหรับจัดการกระบวนการแปลง Celsius to Fahrenheit ทั้งหมด
fun convertCelsiusToFahrenheit() {
    print("ป้อนค่าองศาเซลเซียส (Celsius): ")
    val input = readln()

    // 5. จัดการ Null Safety ด้วย toDoubleOrNull() และ Elvis operator (?:)
    val celsius = input.toDoubleOrNull() ?: run {
        println("ข้อมูลไม่ถูกต้อง กรุณากรอกตัวเลข.")
        return
    }

    val fahrenheitResult = celsiusToFahrenheit(celsius)

    // 6. แสดงผลลัพธ์
    println("ผลลัพธ์: $celsius °C เท่ากับ ${"%.2f".format(fahrenheitResult)} °F")
}

// ฟังก์ชันสำหรับจัดการกระบวนการแปลง Kilometers to Miles ทั้งหมด
fun convertKilometersToMiles() {
    print("ป้อนค่ากิโลเมตร (Kilometers): ")
    val input = readln()

    // 5. จัดการ Null Safety ด้วย toDoubleOrNull() และ Elvis operator (?:)
    val kilometers = input.toDoubleOrNull() ?: run {
        println("ข้อมูลไม่ถูกต้อง กรุณากรอกตัวเลข.")
        return
    }

    val milesResult = kilometersToMiles(kilometers)

    // 6. แสดงผลลัพธ์
    println("ผลลัพธ์: $kilometers km เท่ากับ ${"%.2f".format(milesResult)} miles")
}
