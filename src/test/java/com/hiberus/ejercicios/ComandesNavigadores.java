package com.hiberus.ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ComandesNavigadores {

    public static WebDriver driver;

    public static WebDriverWait wait;

    @Before
    public void setUp() {
        //Paso0
        WebDriverManager.chromedriver().setup(); // Cargar Chromedriver

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10, 500);
    }



    @Test
    public void login()

    {
        driver.get("https://www.saucedemo.com/");
        String url="https://www.saucedemo.com/inventory.html";
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");

        // Escribir la contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        // Hacer clic en el botón de login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

       String actualUrl= driver.getCurrentUrl();


        Assert.assertTrue("url does not match",actualUrl.equalsIgnoreCase(url));
        //Assert.assertFalse("does match", actualUrl.equalsIgnoreCase(url));
        System.out.println("se ha muestrado correctamente");

    }

    @Test

    public void ErrorMessage()
    {

        driver.get("https://www.saucedemo.com/");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_use");

        // Escribir la contraseña
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        // Hacer clic en el botón de login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();


        // Validar que aparece el mensaje de error
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        if (errorMessage.isDisplayed()) {
            System.out.println("Mensaje de error: " + errorMessage.getText());
            System.out.println("Test exitoso. Se encontró el mensaje de error.");
        } else {
            System.out.println("Test fallido. No se encontró el mensaje de error.");
        }

    }




    @Test
    public void ValidarNumeroDeResultados() {
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
        // int numProducts=0;
        List<WebElement> numProducts = null;
        try {

            // Validar el número de productos mostrados
            numProducts = driver.findElements(By.className("inventory_item"));


        } catch (NoSuchElementException nsee) {
            System.out.println("el element no existe" + nsee.getMessage());

            // Cerrar el navegador
            driver.quit();
        }

        Assert.assertEquals("el resultado esperado es :6 y el resultado obtenido es:" + numProducts.size(), 6, numProducts.size());
        System.out.println("El número de productos mostrados es igual a 6. Prueba pasada!");
    }


    @Test

    public void existeElemento()
    {
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


        WebElement elemnt= driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));
        boolean bool=elemnt.isDisplayed();
        Assert.assertTrue("el elemento no existe",bool);
        System.out.println("ele elemento existe");


    }
    @Test
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


        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        addToCartButton.click();
        WebElement cartIcon = null;
        // Esperar un momento para que se actualice el carrito
        try {
            // Validar que en el icono del carrito se ha agregado el valor 1
            cartIcon = driver.findElement(By.className("shopping_cart_badge"));


        } catch (NoSuchElementException  e) {
            System.err.println("Error durante la ejecución del caso de prueba: " + e.getMessage());
        }
        Assert.assertEquals("no se ha agregado", 1, Integer.parseInt(cartIcon.getText()));
        System.out.println("Se ha agregado correctamente el producto al carrito con el valor 1.");


    }

    @Test
    public void validarVisibilidadBotonEliminar() {
        driver.get("https://www.saucedemo.com");

        // Iniciar sesión
        WebElement usernameInput4 = driver.findElement(By.id("user-name"));
        usernameInput4.sendKeys("standard_user");
        WebElement passwordInput5 = driver.findElement(By.id("password"));
        passwordInput5.sendKeys("secret_sauce");
        WebElement loginButton6 = driver.findElement(By.id("login-button"));
        loginButton6.click();

        // Agregar al carrito el producto Sauce Labs Onesie
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));

        addToCartButton.click();
        try {

            // Validar que el botón de eliminar producto del carrito está visible
            WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-backpack"));

            Assert.assertTrue(removeButton.isDisplayed());
            System.out.println("El botón de eliminar producto del carrito está visible.");
        } catch (Exception e) {
            System.err.println("Error durante la ejecución del caso de prueba: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }


//no funciona

    @Test
    public void validariconEnEliminar() {
        driver.get("https://www.saucedemo.com");

        // Iniciar sesión
        WebElement usernameInput4 = driver.findElement(By.id("user-name"));
        usernameInput4.sendKeys("standard_user");
        WebElement passwordInput5 = driver.findElement(By.id("password"));
        passwordInput5.sendKeys("secret_sauce");
        WebElement loginButton6 = driver.findElement(By.id("login-button"));
        loginButton6.click();

        // Agregar al carrito el producto Sauce Labs Onesie
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));

        addToCartButton.click();
        // Validar que el botón de eliminar producto del carrito está visible
        WebElement removeButton = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-bolt-t-shirt']"));
        removeButton.click();
        WebElement  cartIcon1=null;

            // Validar que en el icono del carrito se ha agregado el valor 1
        cartIcon1 = driver.findElement(By.className("shopping_cart_badge"));

        Assert.assertFalse(" n esta visible pues se ha eliminado",cartIcon1.isDisplayed());
        System.out.println("el elemento  no se ha eliminado");
    }


    @Test
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
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        addToCartButton.click();


        // Eliminar el producto del carrito
        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
        removeButton.click();
        try {


            // Validar que en el icono del carrito se ha eliminado el producto
            WebElement cartIcon = driver.findElement(By.className("shopping_cart_container"));
            Assert.assertEquals("", cartIcon.getText()); // El icono del carrito estará vacío si no hay productos
            System.out.println("El producto se ha eliminado correctamente del carrito.");
        } catch (Exception e) {
            System.err.println("Error durante la ejecución del caso de prueba: " + e.getMessage());
        }
    }

    @Test
    public void agregarTresProductosAlCarrito() {
        try {
            // Paso 1: Ir a la página de inicio
            driver.get("https://www.saucedemo.com");

            // Paso 2: Ingresar el nombre de usuario
            WebElement usernameInput = driver.findElement(By.id("user-name"));
            usernameInput.sendKeys("standard_user");

            // Paso 3: Ingresar la contraseña
            WebElement passwordInput = driver.findElement(By.id("password"));
            passwordInput.sendKeys("secret_sauce");

            // Paso 4: Hacer clic en el botón de inicio de sesión
            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            // Paso 5: Agregar 3 productos al azar al carrito
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                // Paso 5.1: Obtener la lista de todos los botones "Add to Cart"
                //(By.xpath(".//button[contains(@id,'add-to-cart')]"));
                List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(@class, 'btn_primary') and contains(@class, 'btn_inventory')]"));

                // Paso 5.2: Elegir un producto al azar y hacer clic en su botón "Add to Cart"
                //el index es de 0 a 8 prk la lista tiene 9 elementos , pero addToCartButtons.size() es exclusivo
                int randomIndex = random.nextInt(addToCartButtons.size());
                WebElement addToCartButton = addToCartButtons.get(randomIndex);
                addToCartButton.click();
                

            }

            // Paso 6: Validar que en el icono del carrito se han agregado los 3 productos
            WebElement cartIcon = driver.findElement(By.className("shopping_cart_badge"));
            Assert.assertEquals("3", cartIcon.getText());
            System.out.println("Se han agregado correctamente los 3 productos al carrito.");
        } catch (Exception e) {
            // Manejo de excepciones
            System.err.println("Error durante la ejecución del caso de prueba: " + e.getMessage());
        }
    }


    @Test
    public void ordenarInventario() {
        try {
            // Ir a la página de inicio
            driver.get("https://www.saucedemo.com");

            // Iniciar sesión
            WebElement usernameInput = driver.findElement(By.id("user-name"));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));
            usernameInput.sendKeys("standard_user");
            passwordInput.sendKeys("secret_sauce");
            loginButton.click();

            // Seleccionar el filtro "Name (Z to A)"
            Select select = new Select(driver.findElement(By.className("product_sort_container")));
            select.selectByVisibleText("Name (Z to A)");


            // Obtener los nombres de los productos ordenados
            java.util.List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
            String[] productNames = new String[productElements.size()];
            for (int i = 0; i < productElements.size(); i++) {
                productNames[i] = productElements.get(i).getText();
            }
            int n = productNames.length;
            for (int i = 1; i < n; i++) {

                Assert.assertTrue((productNames[i - 1].compareTo(productNames[i]) <= 0));
            }

            System.out.println("El inventario se ha ordenado correctamente de Z a A.");
        } catch (Exception e) {
            System.err.println("Error durante la ejecución del caso de prueba: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }


    @Test
    public void ordenarInventarioPorPrecio() {
        try {
            // Ir a la página de inicio
            driver.get("https://www.saucedemo.com");

            // Iniciar sesión
            WebElement usernameInput = driver.findElement(By.id("user-name"));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));
            usernameInput.sendKeys("standard_user");
            passwordInput.sendKeys("secret_sauce");
            loginButton.click();

            // Seleccionar el filtro "Price (low to high)"
            Select select = new Select(driver.findElement(By.className("product_sort_container")));
            select.selectByVisibleText("Price (low to high)");

            // Obtener los precios de los productos ordenados
            java.util.List<WebElement> productElements = driver.findElements(By.className("inventory_item_price"));
            String[] productPrices = new String[productElements.size()];
            for (int i = 0; i < productElements.size(); i++) {
                productPrices[i] = productElements.get(i).getText().replace("$", "");
            }

            // Convertir los precios a números decimales
            double[] prices = new double[productPrices.length];
            for (int i = 0; i < productPrices.length; i++) {
                prices[i] = Double.parseDouble(productPrices[i]);
            }

            // Validar que los precios de los productos estén ordenados de menor a mayor
            for (int i = 1; i < prices.length; i++) {
                Assert.assertTrue(prices[i - 1] <= prices[i]);
            }

            System.out.println("El inventario se ha ordenado correctamente de menor a mayor precio.");
        } catch (Exception e) {
            System.err.println("Error durante la ejecución del caso de prueba: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void ordenarInventarioPorPrecio2() {
        try {
            // Ir a la página de inicio
            driver.get("https://www.saucedemo.com");

            // Iniciar sesión
            WebElement usernameInput = driver.findElement(By.id("user-name"));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));
            usernameInput.sendKeys("standard_user");
            passwordInput.sendKeys("secret_sauce");
            loginButton.click();

            // Seleccionar el filtro "Price (high to low)"
            Select select = new Select(driver.findElement(By.className("product_sort_container")));
            select.selectByVisibleText("Price (high to low)");

            // Obtener los precios de los productos ordenados
            List<WebElement> productElements = driver.findElements(By.className("inventory_item_price"));
            String[] productPrices = new String[productElements.size()];
            for (int i = 0; i < productElements.size(); i++) {
                productPrices[i] = productElements.get(i).getText().replace("$", "");
            }

            // Convertir los precios a números decimales
            double[] prices = new double[productPrices.length];
            for (int i = 0; i < productPrices.length; i++) {
                prices[i] = Double.parseDouble(productPrices[i]);
            }

            // Validar que los precios de los productos estén ordenados de mayor a menor
            for (int i = 1; i < prices.length; i++) {
                Assert.assertTrue(prices[i - 1] >= prices[i]);
            }

            System.out.println("El inventario se ha ordenado correctamente de mayor a menor precio.");
        } catch (Exception e) {
            System.err.println("Error durante la ejecución del caso de prueba: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }


    @Test

    public void carrito()
    {
        driver.get("https://www.saucedemo.com");

        // Iniciar sesión
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();

        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            // Paso 5.1: Obtener la lista de todos los botones "Add to Cart"
            //(By.xpath(".//button[contains(@id,'add-to-cart')]"));
            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(@class, 'btn_primary') and contains(@class, 'btn_inventory')]"));

            // Paso 5.2: Elegir un producto al azar y hacer clic en su botón "Add to Cart"
            //el index es de 0 a 8 prk la lista tiene 9 elementos , pero addToCartButtons.size() es exclusivo
            int randomIndex = random.nextInt(addToCartButtons.size());
            WebElement addToCartButton = addToCartButtons.get(randomIndex);
            addToCartButton.click();


        }

        WebElement carr=driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
       carr.click();
       List<WebElement> remove=driver.findElements(By.xpath("//button[contains(@class,'btn btn_secondary) and contains( @class,cart_button')]"));
         int j1=random.nextInt(remove.size());
            WebElement w1=remove.get(j1);
            w1.click();
        List<WebElement>  elementosCaritto=null;
            try{

                 elementosCaritto=driver.findElements(By.xpath("//div[@class='cart_item']"));

            }catch (NoSuchElementException e){ System.err.println("element not found"+e);}

         Assert.assertEquals("aun no se ha eliminado",1,elementosCaritto.size());

    }

    @Test
    public void eliminarProductoCarrit() {
      // Paso 1: Ir a la página https://www.saucedemo.com
        driver.get("https://www.saucedemo.com");

        // Paso 2: Escribir el username standard_user y password secret_sauce y pulsar en el botón del Login
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Paso 3: Agregar al carrito 2 productos elegidos al azar
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            // Paso 5.1: Obtener la lista de todos los botones "Add to Cart"
            //(By.xpath(".//button[contains(@id,'add-to-cart')]"));
            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(@class, 'btn_primary') and contains(@class, 'btn_inventory')]"));

            // Paso 5.2: Elegir un producto al azar y hacer clic en su botón "Add to Cart"
            //el index es de 0 a 8 prk la lista tiene 9 elementos , pero addToCartButtons.size() es exclusivo
            int randomIndex = random.nextInt(addToCartButtons.size());
            WebElement addToCartButton = addToCartButtons.get(randomIndex);
            addToCartButton.click();


        }

        // Paso 4: Ir al carrito
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_badge"));
        cartIcon.click();

        // Paso 5: Eliminar uno de los productos


        List <WebElement> removeButton = driver.findElements(By.xpath("//button[contains(@class, 'btn_secondary') and contains(@class, 'cart_button')]"));

        removeButton.get(0).click();
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        // Paso 6: Obtener el número de elementos en el carrito y verificar si es igual a 1
         int numberOfItems = cartItems.size();

// Usar assertEquals para verificar si el número de elementos es igual a 1
        Assert.assertEquals("La prueba ha fallado: se esperaba 1 elemento en el carrito.", 1, numberOfItems);
         System.out.println("prueba exotosa, 1 elemento en el carrito");
        // Paso 7: Assert para verificar que el producto eliminado no está en el carrito

        // Cerrar el navegador
        driver.quit();
    }

    @Test

    public void checkout()
    {

        driver.get("https://www.saucedemo.com");

        // Paso 2: Escribir el username standard_user y password secret_sauce y pulsar en el botón del Login
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();



        // Paso 5: Agregar 3 productos al azar al carrito
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            // Paso 5.1: Obtener la lista de todos los botones "Add to Cart"
            //(By.xpath(".//button[contains(@id,'add-to-cart')]"));
            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(@class, 'btn_primary') and contains(@class, 'btn_inventory')]"));

            // Paso 5.2: Elegir un producto al azar y hacer clic en su botón "Add to Cart"
            //el index es de 0 a 8 prk la lista tiene 9 elementos , pero addToCartButtons.size() es exclusivo
            int randomIndex = random.nextInt(addToCartButtons.size());
            WebElement addToCartButton = addToCartButtons.get(randomIndex);
            addToCartButton.click();

        }

        // Paso 4: Ir al carrito
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_badge"));
        cartIcon.click();
        //checkout   class="btn btn_action btn_medium checkout_button "

        WebElement checkout = driver.findElement(By.xpath("//button[@class='btn btn_action btn_medium checkout_button ']"));
        checkout.click();

        //rellenar los campos

        WebElement Firstname = driver.findElement(By.id("first-name"));
        Firstname.sendKeys("standard_user");
        WebElement LastName = driver.findElement(By.id("last-name"));
        LastName.sendKeys("secret_sauce");
        WebElement zip= driver.findElement(By.id("postal-code"));
        zip.sendKeys("secret_sauce");
        WebElement continu = driver.findElement(By.id("continue"));
        continu.click();

     //probar la suma
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        double totalPriceInCart = 0.0;

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            totalPriceInCart += price;
        }


        // Paso adicional: Obtener el valor del impuesto
        WebElement taxElement = driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
        String taxText = taxElement.getText().replace("Tax: $", "");
        double tax = Double.parseDouble(taxText);

        // Calcular el precio total con impuestos
        double totalPriceWithTax = totalPriceInCart + tax;



        // comparar
        WebElement precioTotal = driver.findElement(By.xpath("//div[@class='summary_info_label summary_total_label']"));
        String totalText = taxElement.getText().replace("Total: $", "");
        double totalValu = Double.parseDouble(taxText);

        Assert.assertEquals("los valores no son similarios", totalPriceWithTax, totalValu, 0.01);
        System.out.println("prueba exitosa");


    }

    @Test
    public void realizarPedido()
    {

        driver.get("https://www.saucedemo.com");

        // Paso 2: Escribir el username standard_user y password secret_sauce y pulsar en el botón del Login
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

            Random random = new Random();

            // Paso 5.1: Obtener la lista de todos los botones "Add to Cart"
            //(By.xpath(".//button[contains(@id,'add-to-cart')]"));
            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(@class, 'btn_primary') and contains(@class, 'btn_inventory')]"));

            // Paso 5.2: Elegir un producto al azar y hacer clic en su botón "Add to Cart"
            //el index es de 0 a 8 prk la lista tiene 9 elementos , pero addToCartButtons.size() es exclusivo
            int randomIndex = random.nextInt(addToCartButtons.size());
            WebElement addToCartButton = addToCartButtons.get(randomIndex);
            addToCartButton.click();

        // Paso 4: Ir al carrito
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_badge"));
        cartIcon.click();

        //checkout   class="btn btn_action btn_medium checkout_button "

        WebElement checkout = driver.findElement(By.xpath("//button[@class='btn btn_action btn_medium checkout_button ']"));
        checkout.click();


        //rellenar los campos

        WebElement Firstname = driver.findElement(By.id("first-name"));
        Firstname.sendKeys("standard_user");
        WebElement LastName = driver.findElement(By.id("last-name"));
        LastName.sendKeys("secret_sauce");
        WebElement zip= driver.findElement(By.id("postal-code"));
        zip.sendKeys("secret_sauce");
        WebElement continu = driver.findElement(By.id("continue"));
        continu.click();

        //finish
        //checkout   class="btn btn_action btn_medium checkout_button "

        WebElement finish = driver.findElement(By.xpath("//button[@class='btn btn_action btn_medium cart_button']"));
        finish.click();

        //"//div[text()='Your order has been dispatched, and will arrive just as fast as the pony can get there!']"
        WebElement terminer=null;
        try {
      terminer = driver.findElement(By.xpath("//div[text()='Your order has been dispatched, and will arrive just as fast as the pony can get there!']"));
    terminer.click();
       }catch(NoSuchElementException E)
        {
        System.err.println(E);
         }
        Assert.assertTrue("the message is not displauyed",terminer.isDisplayed());

    }

    @Test
    public void loginIncorrectTest() {


    }

    @After
    public void tearDown() {
        // driver.close();
    }
}
