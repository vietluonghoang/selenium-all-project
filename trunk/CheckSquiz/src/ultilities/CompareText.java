/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultilities;

import elements.ElementsCheck;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author Admin
 */
public class CompareText {

    private ElementsCheck ec;
    private WebDriver driver;

    public CompareText(WebDriver driver) {
        this.driver = driver;
        this.ec = new ElementsCheck();
        ArrayList<String> countryList = new ArrayList<String>();
//        countryList.add("Vereinigte Staaten");
//        countryList.add("Afghanistan");
//        countryList.add("Albanien");
//        countryList.add("Algerien");
//        countryList.add("Andorra");
//        countryList.add("Angola");
//        countryList.add("Antigua und Barbuda");
//        countryList.add("Argentinien");
//        countryList.add("Armenien");
//        countryList.add("Australien");
//        countryList.add("Österreich");
//        countryList.add("Aserbaidschan");
//        countryList.add("Bahamas");
//        countryList.add("Bahrain");
//        countryList.add("Bangladesch");
//        countryList.add("Barbados");
//        countryList.add("Weißrussland");
//        countryList.add("Belgien");
//        countryList.add("Belize");
//        countryList.add("Benin");
//        countryList.add("Bhutan");
//        countryList.add("Bolivien");
//        countryList.add("Bosnien und Herzegowina");
//        countryList.add("Botswana");
//        countryList.add("Brasilien");
//        countryList.add("Brunei");
//        countryList.add("Bulgarien");
//        countryList.add("Burkina Faso");
//        countryList.add("Burundi");
//        countryList.add("Kambodscha");
//        countryList.add("Kamerun");
//        countryList.add("Kanada");
//        countryList.add("Kap Verde");
//        countryList.add("Zentralafrikanische Republik");
//        countryList.add("Tschad");
//        countryList.add("Chile");
//        countryList.add("China");
//        countryList.add("Kolumbien");
//        countryList.add("Komoren");
//        countryList.add("Kongo");
//        countryList.add("Demokratische Republik Kongo");
//        countryList.add("Costa Rica");
//        countryList.add("Elfenbeinküste");
//        countryList.add("Kroatien");
//        countryList.add("Kuba");
//        countryList.add("Zypern");
//        countryList.add("Tschechien");
//        countryList.add("Dänemark");
//        countryList.add("Dschibuti");
//        countryList.add("Dominica");
//        countryList.add("Dominikanische Republik");
//        countryList.add("Osttimor");
//        countryList.add("Ecuador");
//        countryList.add("Ägypten");
//        countryList.add("");
        String[] ctry = {"États-Unis", "Afghanistan", "Albanie", "Algérie", "Andorre", "Angola", "Antigua-et-Barbuda", "Argentine", "Arménie",
            "Australie", "Autriche", "Azerbaïdjan", "Bahamas", "Bahreïn", "Bangladesh", "Barbade", "Biélorussie", "Belgique", "Belize", "Bénin", "Bhoutan", "Bolivie", "Bosnie-Herzégovine", "Botswana", "Brésil", "Brunei", "Bulgarie", "Burkina", "Burundi", "Cambodge", "Cameroun", "Canada", "Cap-Vert", "République centrafricaine", "Tchad", "Chili", "Chine", "Colombie", "Comores", "Congo", "République démocratique du Congo", "Costa Rica", "Côte d'Ivoire", "Croatie", "Cuba", "Chypre", "République Tchèque",
            "Danemark", "Djibouti", "Dominique", "République dominicaine", "Timor-Oriental", "Équateur", "Égypte", "El Salvador", "Guinée équatoriale", "Erythrée", "Estonie", "Éthiopie", "Fidji", "Finlande", "France", "Gabon", "Gambie", "Géorgie", "Allemagne", "Ghana", "Grèce", "Grenade", "Guatemala", "Guinée", "Guinée-Bissau", "Guyana", "Haïti", "Honduras", "Hongrie", "Islande", "Inde", "Indonésie", "Iran", "Iraq", "Irlande", "Israël", "Italie", "Jamaïque", "Japon", "Jordanie", "Kazakhstan", "Kenya", "Kiribati", "Corée du Nord", "Corée du Sud", "Koweit", "Kirghizstan", "Laos", "Lettonie",
            "Liban", "Lesotho", "Liberia", "Libye", "Liechtenstein", "Lituanie", "Luxembourg", "Macédoine", "Madagascar", "Malawi", "Malaisie", "Maldives", "Mali", "Malte", "Îles Marshall", "Mauritanie", "Maurice", "Mexique", "Micronésie", "Moldavie", "Monaco", "Mongolie", "Monténégro", "Maroc", "Mozambique", "Myanmar (Birmanie)", "Namibie", "Nauru", "Népal", "Pays-Bas", "Nouvelle-Zélande", "Nicaragua", "Niger", "Nigeria", "Norvège", "Oman", "Pakistan", "Palaos", "Panama", "Papouasie-Nouvelle-Guinée", "Paraguay", "Pérou", "Philippines", "Pologne", "Portugal", "Qatar", "Roumanie", "Russie", "Rwanda",
            "Saint-Christophe-et-Niévès", "Sainte-Lucie", "Saint-Vincent-et-les-Grenadines", "Samoa", "Saint-Marin", "Sao Tomé-et-Principe", "Arabie saoudite", "Sénégal", "Serbie", "Seychelles", "Sierra Leone", "Singapour", "Slovaquie", "Slovénie", "Îles Salomon", "Somalie", "Afrique du Sud", "Espagne", "Sri Lanka", "Soudan", "Suriname", "Swaziland", "Suède", "Suisse", "Syrie", "Taïwan", "Tadjikistan", "Tanzanie", "Thaïlande", "Togo", "Tonga", "Trinité-et-Tobago", "Tunisie", "Turquie", "Turkménistan", "Tuvalu", "Ouganda", "Ukraine", "Émirats arabes unis", "Royaume-Uni", "Uruguay", "Ouzbékistan", "Vanuatu", "État de la Cité du Vatican", "Venezuela", "Viêt Nam",
            "Western Sahara", "Yemen", "Zambia", "Zimbabwe"
        };
        getAllElementFromDropdownList("edit-country", ctry);
    }

    private void getAllElementFromDropdownList(String id, String[] countryList) {

        List<WebElement> langs = driver.findElements(By.xpath("//span[@id='lang-select-container']/a"));
        for (WebElement lang : langs) {
            try {
                Actions act = new Actions(driver);
                act.moveToElement(driver.findElement(By.id("language-select"))).build().perform();
                Thread.sleep(100);

                if (lang.getText().equals("Français")) {
                    lang.click();
                    ec.isElementVisible(driver, driver.findElement(By.id("language-select")));
                    List<WebElement> elements = driver.findElements(By.xpath("//select[@id='" + id + "']/option"));
                    for (WebElement webElement : elements) {
                        boolean isExist=false;
                        for (int i = 0; i < countryList.length; i++) {
                            if (webElement.getText().equals(countryList[i])) {
                                System.out.println("Found "+countryList[i]);
                                isExist=true;
                                break;
                            }
                        }
                        if(!isExist){
                            System.out.println("Not Found: " + webElement.getText());
                        }
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(CompareText.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CompareText.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
