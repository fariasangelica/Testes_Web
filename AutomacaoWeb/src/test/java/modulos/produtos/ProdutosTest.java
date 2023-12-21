package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do módulo de produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach()  {
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Maximizar a tela
        this.navegador.manage().window().maximize();


        // Navegar para a página da Loja Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");

        // Definição de tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    @DisplayName("Não é possível registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoValorIgualAZero() {

        // Fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("Posso adicionar produto que estejam na faixa de 0,01 a 7.000,00")
    public void testPossoAdicionarProdutosComValorDeUmCentavoASeteMilReais() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("300000")
                .informarCoresDoProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        // Produto adicionado com sucesso
        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);

    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor maior que 7.000,00")
    public void testNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Iphone")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }


    @AfterEach
    public void afterEach() {

        // Fechar navegador
        navegador.quit();
    }


}
