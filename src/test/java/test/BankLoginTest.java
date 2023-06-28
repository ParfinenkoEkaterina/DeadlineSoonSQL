package test;

import Data.DataHelper;
import PageObjects.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static Data.SQLHelper.cleanDataBase;
import static com.codeborne.selenide.Selenide.open;

public class BankLoginTest {
    @AfterAll
    static void teardown() {
        cleanDataBase();
    }

    @Test
    void shouldSuccessfulLogin() {
        open("http://localhost:9999");

    }

}
