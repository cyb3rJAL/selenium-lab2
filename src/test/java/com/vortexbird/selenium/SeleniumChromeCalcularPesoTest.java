package com.vortexbird.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

//@ExtendWith(SeleniumJupiter.class)
public class SeleniumChromeCalcularPesoTest {

	@RegisterExtension
	static SeleniumJupiter seleniumJupiter = new SeleniumJupiter();

	@BeforeAll
	static void setUp() {
		seleniumJupiter.getConfig().setVnc(true);
	}
	
	@Test
	void calcularPesoIdeal_debe_calcular_peso_correctamente_local(ChromeDriver driver) throws InterruptedException {

		/*
		 * ************************************* 
		 * Arrange 
		 * Sea la siguiente configuración
		 * del navegador 
		 * *************************************
		 */
		driver.get("http://salud.coomeva.com.co/publicaciones.php?id=8338");
		// driver.manage().window().setSize(new Dimension(2560, 1417));
		
		String nombreComboEdad = "edad";
		String xpathEdad = "//option[. = '40 a 49 años']";
		
		String nombreComboAltura = "altura";
		String xpathAltura = "//option[. = '1 m 70 cm']";
		
		String nombreCajaTextoResultadoPeso = "pesomedio";
		
		String pesoIdealEsperao = "72.9";
		String pesoIdealObtenido = null;
		
		/*
		 * ************************************* 
		 * Act 
		 * Ejecute el paso a paso de la prueba
		 * *************************************
		 */
		// Click en el radio de sexo
		driver.findElement(By.name("sexo")).click();
		
		// Seleccione la edad 40 a 49 anhos
		driver.findElement(By.name(nombreComboEdad)).click();
		WebElement dropdownEdad = driver.findElement(By.name(nombreComboEdad));
		dropdownEdad.findElement(By.xpath(xpathEdad)).click();

		// Seleccione la altura 1 m 70 cm
		driver.findElement(By.name(nombreComboAltura)).click();
		WebElement dropdown = driver.findElement(By.name(nombreComboAltura));
		dropdown.findElement(By.xpath(xpathAltura)).click();

		// Clic en el botón "calculate"
		driver.findElement(By.name("calculate")).click();

		// Obtener el resultado del peso medio
		pesoIdealObtenido = driver.findElement(By.name(nombreCajaTextoResultadoPeso)).getAttribute("value");

		/*
		 * ************************************* 
		 * Assert 
		 * Valide el resultado esperado
		 * *************************************
		 */
		assertEquals(pesoIdealObtenido, pesoIdealEsperao);

	}
	
	

}
