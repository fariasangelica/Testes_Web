package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.cssSelector;

public class LoginPage {
    private WebDriver navegador;

    public LoginPage(WebDriver navegador) {
        this.navegador =navegador;

    }

    public LoginPage informarOUsuario(String usuario){
        navegador.findElement(cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys(usuario);

        return this;
   }

    public LoginPage informarASenha(String senha){
       navegador.findElement(By.cssSelector("label[for='senha']")).click();
       navegador.findElement(By.id("senha")).sendKeys(senha);


       return this;
   }

   public ListaDeProdutosPage submeterFormularioDeLogin() {
       navegador.findElement(By.cssSelector("button[type='submit']")).click();

       return new ListaDeProdutosPage(navegador);
   }



}