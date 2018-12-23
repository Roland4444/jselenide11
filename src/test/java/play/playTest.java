package play;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import se.roland.Extractor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;


class playTest {
    public Extractor ext = new Extractor();
    public final String dadata ="https://dadata.ru/suggestions/#address";


    @org.junit.jupiter.api.Test
    void letsopen2() throws InterruptedException {
        open("https://www.google.com/");
        $(By.className("gLFyf")).setValue("Search").pressEnter();
        Thread.sleep(1000);
        //  $(By.name("user.name")).setValue("johny");
      //  $("#submit").click();
    }


   // @org.junit.jupiter.api.Test
    void letsopen3() throws InterruptedException {
        open("https://fias.nalog.ru/ExtendedSearchPage.aspx");
        $(By.xpath("//*[text()='Административно-территориальное']")).click();

        $(By.id("ctl00_contentPlaceHolder_kladrAddressObjectControl_addressObjectMultiViewControl_addressObjectMOControl_regionMORadComboBox_Input")).click();
        Thread.sleep(10000);
        //  $(By.name("user.name")).setValue("johny");
        //  $("#submit").click();
    }

    @Test
    void dadaopen() throws InterruptedException, IOException {

        String etalon = "95cc0889-abe9-442a-ae49-13cc1266cddb";
        open("https://dadata.ru/suggestions/#address");
        $(By.id("address-input")).setValue("Астрахань 2я Воскресенская 38").sendKeys(Keys.ARROW_DOWN);
        $(By.id("address-input")).pressEnter();
        Thread. sleep(500);
     //   String dump = executeJavaScript("alert('YEY')");//''"document.documentElement.outerHTML");
      //  System.out.println(dump);
        String script = new String(Files.readAllBytes(Paths.get(new File("js/dump.js").getPath())));
        System.out.println(script);
        executeJavaScript("var div = document.createElement('h5');\ndiv.innerText=document.documentElement.outerHTML;\ndiv.className='dumpinfo';\ndocument.body.insertBefore(div, document.body.firstChild);");
        String dumped = $(By.className("dumpinfo")).getText();
        FileOutputStream fos = new FileOutputStream("dump/raw.html");
        fos.write(dumped.getBytes());
        fos.close();
        assertNotEquals(null, dumped);
        System.out.println(dumped);
        assertEquals(etalon, ext.extractTagValue(dumped, "td data-ref=\"fias-codes\""));
        //Thread.sleep(1500000);
    }



    void compareStrings() throws IOException {
        var Genned = new String(Files.readAllBytes(Paths.get(new File("dump/genned.html").getPath())));
        var Static = new String(Files.readAllBytes(Paths.get(new File("dump/static.html").getPath())));
        assertEquals(Genned, Static);
    }


}

//gLFyf gsfi