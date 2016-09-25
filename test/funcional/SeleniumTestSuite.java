package funcional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTestSuite {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"/media/files/projetos/0_java_libraries/selenium-3.0.0-b2/chromedriver");

		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testRedirecionaSeNaoLogado() throws Exception {
		driver.get(baseUrl + "/forum/topicos");
		assertEquals("Login", driver.findElement(By.cssSelector("label")).getText());
		try {
			assertEquals("Entrar", driver.findElement(By.name("submit")).getAttribute("value"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void testENavegacaoMenuForum() throws Exception {
		driver.get(baseUrl + "/forum/login");
		driver.findElement(By.name("login")).clear();
		driver.findElement(By.name("login")).sendKeys("leonardoleal");
		driver.findElement(By.name("senha")).clear();
		driver.findElement(By.name("senha")).sendKeys("leonardoleal");
		driver.findElement(By.name("submit")).click();

		waitUntilReadyState(driver);
		assertEquals("Tópicos", driver.findElement(By.cssSelector("h3")).getText());

		driver.findElement(By.xpath("//a[contains(@href, 'topico-detalhe?id=1')]")).click();
		waitUntilReadyState(driver);
		assertEquals("Detalhes do Tópico", driver.findElement(By.cssSelector("h3")).getText());

		driver.findElement(By.linkText("Ver Tópicos")).click();
		waitUntilReadyState(driver);
		assertEquals("Tópicos", driver.findElement(By.cssSelector("h3")).getText());

		driver.findElement(By.linkText("Novo Tópico")).click();
		waitUntilReadyState(driver);
		assertEquals("Novo Tópico", driver.findElement(By.cssSelector("h3")).getText());

		driver.findElement(By.linkText("Ranking")).click();
		waitUntilReadyState(driver);
		assertEquals("Rankig de Usuários", driver.findElement(By.cssSelector("h3")).getText());
	}

	@Test
	public void testLogout() throws Exception {
		driver.get(baseUrl + "/forum/login");
		driver.findElement(By.name("login")).clear();
		driver.findElement(By.name("login")).sendKeys("leonardoleal");
		driver.findElement(By.name("senha")).clear();
		driver.findElement(By.name("senha")).sendKeys("leonardoleal");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.linkText("Logout")).click();
		waitUntilReadyState(driver);
		assertEquals("Deslogado com sucesso!", driver.findElement(By.cssSelector("p.msg.alerta")).getText());
	}

	@Test
	public void testFalhaNoLogin() throws Exception {
		driver.get(baseUrl + "/forum/login");
		driver.findElement(By.name("login")).clear();
		driver.findElement(By.name("login")).sendKeys("usuarioInexistente");
		driver.findElement(By.name("senha")).clear();
		driver.findElement(By.name("senha")).sendKeys("senhaInexistente");
		driver.findElement(By.name("submit")).click();
		waitUntilReadyState(driver);
		assertEquals("Não foi possível autenticar o usuário.",
		driver.findElement(By.cssSelector("p.msg.alerta")).getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private static void waitUntilReadyState(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);

	    wait.until(new ExpectedCondition<Boolean>() {
	        @Override
			public Boolean apply(WebDriver wdriver) {
	            return ((JavascriptExecutor) driver).executeScript(
	                "return document.readyState"
	            ).equals("complete");
	            // loading, interactive, complete
	        }
	    });
	}
}
