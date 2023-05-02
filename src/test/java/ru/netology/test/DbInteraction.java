package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.deleteAllDB;
import static ru.netology.data.SQLHelper.deleteCodes;
import static ru.netology.data.DataHelper.*;

public class DbInteraction {
    @BeforeAll
    static void setUpAll() {
        Configuration.headless = false;
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterEach
    void setDown() {
        deleteCodes();
    }

    @AfterAll
    static void setDownAll() {
        deleteAllDB();
    }

    @Test
    void shouldReturnAccessWithFirstValidLogin() {
        new LoginPage().validLogin(getFirstAuthInfo()).accessPage();
    }

    @Test
    void shouldReturnAccessWithSecondValidLogin() {
        new LoginPage().validLogin(getSecondAuthInfo()).accessPage();
    }

    @Test
    void shouldReturnFailWithInvalidLogin() {
        new LoginPage().invalidLogin(getInvalidAuthInfo());
    }

    @Test
    void shouldReturnFailWithInvalidCode() {
        new LoginPage().validLogin(getFirstAuthInfo()).invalidCode();
    }

    @Test
    void shouldReturnFailWithEmptyCode() {
        new LoginPage().validLogin(getSecondAuthInfo()).emptyCode();
    }

    @Test
    void shouldEnterAccountPageFirstAccount() {
        new LoginPage().validLogin(getFirstAuthInfo()).validVerify();
        new DashboardPage().accessLogin();
    }

    @Test
    void shouldEnterAccountPageSecondAccount() {
        new LoginPage().validLogin(getSecondAuthInfo()).validVerify();
        new DashboardPage().accessLogin();
    }

    @Test
    void shouldReturnFailAfterTripleEntry() {
        new LoginPage().validLogin(getInvalidPassFirstAuthInfo());
        new LoginPage().validLogin(getInvalidPassFirstAuthInfo());
        new LoginPage().validLogin(getInvalidPassFirstAuthInfo());
        new LoginPage().invalidLogin(getFirstAuthInfo());
    }
}
