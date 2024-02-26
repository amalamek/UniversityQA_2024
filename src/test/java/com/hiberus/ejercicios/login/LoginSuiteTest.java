package com.hiberus.ejercicios.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginSuiteTest {

    public static WebDriver driver;

    public static WebDriverWait wait;

    @Before
    public void setUp() {
        //Paso0
        WebDriverManager.chromedriver().setup(); // Cargar Chromedriver

        driver= new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10, 500);
    }

    @Test
    public void Test1() {

        // Paso 1: Iniciar un nuevo navegador Chrome
        WebDriver driver = new ChromeDriver();

        // Paso 2: Abrir el sitio web "https://www.hiberus.com/"
        driver.get("https://www.hiberus.com/");

        // Paso 3: Hacer clic en el enlace de Consultoría y Estrategia
        WebElement  Consultoria = driver.findElement(By.xpath("//a[@href='/consultoría-y-estrategia-de-negocio']"));
        Consultoria.click();

        // Paso 4: Volver a la página de inicio (comando 'Back')
        driver.navigate().back();

        // Paso 5: Volver a la página de Consultoría y Estrategia (comando 'Forward')
        driver.navigate().forward();

        // Paso 6: Volver a la página de inicio (comando 'To')
        driver.navigate().to("https://www.hiberus.com/");

        // Paso 7: Actualizar el navegador (comando 'Refresh')
        driver.navigate().refresh();

        // Paso 8: Cerrar el navegador
        driver.quit();



    }

    @Test
    public void ValidarNumeroDeResultados()
    {
        driver.get("https://www.saucedemo.com/");

        // Escribir el nombre de usuario
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");

        // Escribir la contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        // Hacer clic en el botón de login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        int numProducts=0;
        try {
            // Validar el número de productos mostrados
            numProducts = driver.findElements(By.className("inventory_item")).size();
            Assert.assertEquals(6, numProducts);
            System.out.println("El número de productos mostrados es igual a 6. Prueba pasada!");
        } catch (Exception e) {

            // Cerrar el navegador
            driver.quit();
        }
    }
@Test
    public void Tes() {

        // Paso 1: Iniciar un nuevo navegador Chrome
        WebDriver driver = new ChromeDriver();

        // Paso 2: Abrir el sitio web "https://www.hiberus.com/"
        driver.get("https://www.hiberus.com/");

        // Paso 3: Hacer clic en el enlace de Consultoría y Estrategia
        WebElement Consultoria = driver.findElement(By.xpath("//a[@href='/consultoría-y-estrategia-de-negocio']"));
        Consultoria.click();

        // Paso 4: Volver a la página de inicio (comando 'Back')
        driver.navigate().back();

        // Paso 5: Volver a la página de Consultoría y Estrategia (comando 'Forward')
        driver.navigate().forward();

        // Paso 6: Volver a la página de inicio (comando 'To')
        driver.navigate().to("https://www.hiberus.com/");

        // Paso 7: Actualizar el navegador (comando 'Refresh')
        driver.navigate().refresh();

        // Paso 8: Cerrar el navegador
        driver.quit();


    }
    public void validarIncrementoCarrito() {






        // Abrir la página de la aplicación
        driver.get("https://www.saucedemo.com");

        // Escribir el nombre de usuario
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");

        // Escribir la contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        // Hacer clic en el botón de login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Agregar al carrito el producto Sauce Labs Bolt T-Shirt
        WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']/following-sibling::div/button"));
        addToCartButton.click();

        // Esperar un momento para que se actualice el carrito
        try {
            // Validar que en el icono del carrito se ha agregado el valor 1
            WebElement cartIcon = driver.findElement(By.className("shopping_cart_badge"));
            Assert.assertEquals("1", cartIcon.getText());
            System.out.println("Se ha agregado correctamente el producto al carrito con el valor 1.");
        } catch (Exception e) {
            System.err.println("Error durante la ejecución del caso de prueba: " + e.getMessage());
        }
    }

    public void validarVisibilidadBotonEliminar() {

        try {
            driver.get("https://www.saucedemo.com");

            // Iniciar sesión
            WebElement usernameInput4 = driver.findElement(By.id("user-name"));
            usernameInput4.sendKeys("standard_user");
            WebElement passwordInput5 = driver.findElement(By.id("password"));
            passwordInput5.sendKeys("secret_sauce");
            WebElement loginButton6 = driver.findElement(By.id("login-button"));
            loginButton6.click();

            // Agregar al carrito el producto Sauce Labs Onesie
            WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='Sauce Labs Onesie']/following-sibling::div/button"));
            addToCartButton.click();


            // Validar que el botón de eliminar producto del carrito está visible
            WebElement removeButton = driver.findElement(By.xpath("//button[@class='btn_secondary cart_button']"));
            Assert.assertTrue(removeButton.isDisplayed());
            System.out.println("El botón de eliminar producto del carrito está visible.");
        } catch (Exception e) {
            System.err.println("Error durante la ejecución del caso de prueba: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    public void eliminarProductoCarrito() {


        driver.get("https://www.saucedemo.com");

        // Iniciar sesión
        WebElement usernameInput7 = driver.findElement(By.id("user-name"));
        usernameInput7.sendKeys("standard_user");
        WebElement passwordInput8 = driver.findElement(By.id("password"));
        passwordInput8.sendKeys("secret_sauce");
        WebElement loginButton9 = driver.findElement(By.id("login-button"));
        loginButton9.click();

        // Agregar al carrito el producto Sauce Labs Onesie
        WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='Sauce Labs Onesie']/following-sibling::div/button"));
        addToCartButton.click();



        // Eliminar el producto del carrito
        WebElement removeButton = driver.findElement(By.xpath("//button[@class='btn_secondary cart_button']"));
        removeButton.click();
        try {


            // Validar que en el icono del carrito se ha eliminado el producto
            WebElement cartIcon = driver.findElement(By.className("shopping_cart_badge"));
            Assert.assertEquals("", cartIcon.getText()); // El icono del carrito estará vacío si no hay productos
            System.out.println("El producto se ha eliminado correctamente del carrito.");
        } catch (Exception e) {
            System.err.println("Error durante la ejecución del caso de prueba: " + e.getMessage());
        }
    }


    @Test
    public void loginIncorrectTest() {


    }

    @After
    public void tearDown() {
        // driver.close();
    }
}
