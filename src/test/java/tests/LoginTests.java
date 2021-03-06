package tests;

import manager.MyDataProvider;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (applicationManager.getUserhelper().isLoginRegistrationSuccess())
            applicationManager.getUserhelper().logout();
    }

    //  @Test
    //public void loginSuccsess() {
//        //open login form
//         WebElement loginItem = wd.findElement(By.cssSelector("[href='/login']"));
//        loginItem.click();
//        //type email fill
//        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys("bobik@gmail.com");
//        //type password fill
//        WebElement passInput = wd.findElement(By.xpath("//input[2]"));
//        passInput.click();
//        passInput.clear();
//        passInput.sendKeys("Bobik12345$");
//        //click login button
//        wd.findElement(By.xpath("//*[text()=' Login']")).click();
//        //assert
//        Assert.assertTrue(wd.findElements(By.xpath("//*[text()='Sign Out']")).size() > 0);
//
    // }

    @Test(dataProvider = "validLoginData", dataProviderClass = MyDataProvider.class)
    public void loginSuccessNew(String email, String password) {
        logger.info("test starts with email:" + email + " password:" + password);
        //open login registration form
        applicationManager.getUserhelper().openLoginRegistrationform();
        //fill form
        applicationManager.getUserhelper().fillLogonRegistrationForm(email, password);
        //click login button
        applicationManager.getUserhelper().submitLogin();
        Assert.assertTrue(applicationManager.getUserhelper().isLoginRegistrationSuccess());
    }

    @Test
    public void loginSuccessModel() {
        User user = new User().withEmail("bobik@gmail.com").withPassword("Bobik12345$");
        applicationManager.getUserhelper().openLoginRegistrationform();
        applicationManager.getUserhelper().fillLogonRegistrationForm(user);
        applicationManager.getUserhelper().submitLogin();
        Assert.assertTrue(applicationManager.getUserhelper().isLoginRegistrationSuccess());
    }

    @Test
    public void loginNegativePass() {
        User user = new User().withEmail("bobik@gmail.com").withPassword("bobik");
        applicationManager.getUserhelper().openLoginRegistrationform();
        applicationManager.getUserhelper().fillLogonRegistrationForm(user);
        applicationManager.getUserhelper().submitLogin();
        Assert.assertFalse(applicationManager.getUserhelper().isLoginRegistrationSuccess());
        Assert.assertTrue(applicationManager.getUserhelper().isAlertDispalyed());
        Assert.assertTrue(applicationManager.getUserhelper().isErrorWrongFormat());
    }
    @Test (dataProvider = "validLoginData",dataProviderClass = MyDataProvider.class)
    public void loginModelDataPrividerCSV(String email, String password){

        logger.info("Tests start with email : "+email+"and password : "+password);

        applicationManager.getUserhelper().openLoginRegistrationform();
        applicationManager.getUserhelper().fillLogonRegistrationForm(email,password);
        applicationManager.getUserhelper().submitLogin();

        Assert.assertTrue(applicationManager.getUserhelper().isLoginRegistrationSuccess());
        logger.info("test passed");
    }
}
