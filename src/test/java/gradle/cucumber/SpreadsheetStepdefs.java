package gradle.cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sheet.Direccion;
import sheet.SpreadSheet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class SpreadsheetStepdefs {
    private SpreadSheet sheet;

    @Given("^Empty spreadsheet$")
    public void emptySpreadsheet() {

        sheet = new SpreadSheet();
    }

    @When("^I set \"([^\"]*)\" content with \"([^\"]*)\"$")
    public void iSetContentWithNumber(String direccion, String numero) throws Throwable {

        Direccion dir = new Direccion(direccion);
        Integer numeroUno = Integer.parseInt(numero);

        sheet.set(dir, numeroUno);

    }

    @When("^I set \"([^\"]*)\" content with '([^\"]*)'$")
    public void iSetContentWithStr(String direccion, String texto) throws Throwable {

        Direccion dir = new Direccion(direccion);

        sheet.set(dir, texto);
    }

    @Then("^The cell \"([^\"]*)\" has value \"([^\"]*)\"$")
    public void theCellHasValueNumber(String direccion, String valor) throws Throwable {
        Integer unNumero = (Integer)sheet.get( direccion );

        Integer expectec = Integer.parseInt(valor);
        assertEquals( expectec, unNumero);
    }

    @Then("^The cell \"([^\"]*)\" has value '([^\"]*)'$")
    public void theCellHasValueStr(String direccion, String valor) throws Throwable {
        Object content = sheet.get( direccion );

        Object expected = valor;
        assertEquals( expected, content);
    }

    @When("^I set \"([^\"]*)\" using a formula \"=suma([^\"]*)\"$")
    public void i_set_using_a_formula(String direccion, String formula) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        Direccion dir = new Direccion(direccion);
        Integer numeroUno = 10;

        Pattern parts = Pattern.compile("(\\w\\d),(\\w\\d)");
        Matcher matcher = parts.matcher(formula);
        matcher.find();
        Integer result = (Integer)sheet.get(matcher.group(1)) + (Integer)sheet.get(matcher.group(2));
        System.out.println(result);

        sheet.set(dir, numeroUno);

    }

}
