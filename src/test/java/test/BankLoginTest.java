package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import Data.DataHelper;
import Data.SQLHelper;
import PageObjects.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class BankLoginTest {

    @AfterAll
    static void clearUp() {
        SQLHelper.clearDatabase();
    }

    @Test
    void shouldSuccessfullyLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());

    }

    @Test
    void shouldGetErrorWhenUserDoesNotExistsInBase() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = new DataHelper.AuthInfo(DataHelper.getRandomUser().getLogin(), DataHelper.
                getAuthInfoWithTestData().getPassword());
        loginPage.validLogin(authInfo);
        loginPage.getError();
    }

    @Test
    void shouldGerErrorWhenPasswordIsWrong() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = new DataHelper.AuthInfo(DataHelper.getAuthInfoWithTestData().getLogin(), DataHelper.
                getRandomUser().getPassword());
        loginPage.validLogin(authInfo);
        loginPage.getError();
    }

}

