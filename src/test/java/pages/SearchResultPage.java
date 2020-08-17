package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SearchResultPage extends PageObject {

    public SearchResultPage(WebDriver webdriver) {
        super(webdriver);
    }

    @FindBy(css = "div[name=breadcrumb] li a")
    private List<WebElement> breadcrumbs;

    @FindBy(css = "a[name=\"viewAllBrands\"]")
    private WebElement verTodasLasMarcas;

    @FindBy(css = "div[class=\"styled__ItemsContainer-sc-37brzp-5 fhkySw\"] label")
    private List<WebElement> listadoMarcas;

    @FindBy(id = "apply")
    private WebElement btnApply;

    @FindBy(css = "li[name=\"totalResult\"] span")
    private WebElement resultados;

    @FindBy(css = "ul[name=\"itemsGrid\"] li")
    private List<WebElement> listadoItems;

    public void aplicarCategoria(String categoria) {
        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@name=\"categoriesAggregation\"]//a//h3[@name=\"categoryAggregation\" and contains(text(), " + categoria + ")]")));
            WebElement filtroCategoria = this.getDriver().findElement(By.xpath("//ul[@name=\"categoriesAggregation\"]//a//h3[@name=\"categoryAggregation\" and contains(text(), " + categoria + ")]"));
            filtroCategoria.click();
        } catch (Exception e) {
            Assert.fail("No se encuentra categoria " + categoria);
        }
    }

    public void aplicarSubcategoria(String subcategoria) {
        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@name=\"categoriesAggregation\"]//a//h4[@name=\"subcategoryAggregation\" and contains(text(), " + subcategoria + ")]")));
            WebElement filtroSubcategoria = this.getDriver().findElement(By.xpath("//ul[@name=\"categoriesAggregation\"]//a//h4[@name=\"subcategoryAggregation\" and contains(text(), " + subcategoria + ")]"));
            filtroSubcategoria.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("No se encuentra subcategoria " + subcategoria);
        }
    }

    public void aplicarMarca(String marca) {
        try {
            Boolean encontrado = false;
            List<WebElement> marcasVisibles = this.getDriver().findElements(By.cssSelector("ul[name=\"categoriesAggregation\"] li[name=\"brandsFilter\"] label"));
            for ( WebElement marcaVisible : marcasVisibles) {
                if ( marcaVisible.getText().equals(marca) ) {
                    marcaVisible.click();
                    encontrado = true;
                    break;
                }
            }
            if ( !encontrado ) {
                this.buscarMarcaPopUp(marca);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error al buscar marca");
        }
    }

    private void buscarMarcaPopUp(String marca) {
        Boolean encontrado = false;
        this.wait.until(ExpectedConditions.elementToBeClickable(verTodasLasMarcas));
        this.verTodasLasMarcas.click();
        this.wait.until(ExpectedConditions.visibilityOfAllElements(listadoMarcas));
        for ( WebElement marcaVisible : listadoMarcas ) {
            if ( marcaVisible.getText().contains(marca) ) {
                marcaVisible.click();
                encontrado = true;
            }
        }
        this.btnApply.click();
        Assert.assertTrue(encontrado, "No se encuentra marca " + marca);
    }

    public void validarResultados() {
        for ( WebElement item : listadoItems ) {
            String titulo = item.findElement(By.cssSelector("h4[class=\"PieceTitle-sc-1eg7yvt-0 kBpjJs\"]")).getText();
            Assert.assertTrue(titulo.contains("Samsung"), "El titulo no contiene Samsung");
        }
        Assert.assertEquals(Integer.parseInt(resultados.getText()), listadoItems.size());
        Boolean bc = false;
        for ( WebElement breadcrumb : breadcrumbs ) {
            if (breadcrumb.getText().contains("Heladeras con Freezer")) {
                bc = true;
            }
        }
        Assert.assertTrue(bc, "No se encuentra breadcrumbs");
    }
}
