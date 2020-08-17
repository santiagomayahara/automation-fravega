import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;

public class FravegaTest extends BaseTest {

    private HomePage home;
    private SearchResultPage searchResult;

    @Test
    public void ingresoFravega() {
        webdriver.get("https://www.fravega.com/");
    }

    @Parameters({ "producto" })
    @Test(dependsOnMethods = "ingresoFravega")
    public void buscarProducto(String producto) {
        home = new HomePage(webdriver);
        home.busquedaProducto(producto);
    }

    @Parameters({ "subcategoria" })
    @Test(dependsOnMethods = "buscarProducto")
    public void aplicarFiltroSubcategoria() {
        searchResult = new SearchResultPage(webdriver);
        searchResult.aplicarSubcategoria("Heladeras");
    }


    @Parameters({ "marca" })
    @Test(dependsOnMethods = "aplicarFiltroSubcategoria")
    public void aplicarFiltroMarca(String marca) {
        searchResult = new SearchResultPage(webdriver);
        searchResult.aplicarMarca(marca);
    }

    @Test(dependsOnMethods = "aplicarFiltroMarca")
    public void validarResultados() {
        searchResult.validarResultados();
    }

}
