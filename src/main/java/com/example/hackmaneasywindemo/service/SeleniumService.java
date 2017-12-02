package com.example.hackmaneasywindemo.service;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class SeleniumService {

    public static WebDriver driver = new ChromeDriver();

    public static String vkLogin = "";
    public static String vkPassword = "";
    public static String filePath = "";

//    public static void main(String[] args) throws InterruptedException {
//        getPersonVkUrl();
//        getPersonAudio();
//
//    }


    public static String getPersonVkUrl(){

        driver.get("https://findface.ru/");


        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[ng-click=\"login()\"]")));

        WebElement elLogButton = driver.findElement(By.cssSelector("button[ng-click=\"login()\"]"));
        elLogButton.click();

        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        String parentWindowHandler = driver.getWindowHandle();
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);

        WebElement elVkemail= driver.findElement(By.cssSelector("input[name=\"email\"]"));
        elVkemail.click();
        elVkemail.sendKeys(vkLogin);


        WebElement elVkPass = driver.findElement(By.cssSelector("input[name=\"pass\"]"));
        elVkPass.click();
        elVkPass.sendKeys(vkPassword);

        WebElement elVkButton = driver.findElement(By.id("install_allow"));
        elVkButton.click();

        driver.switchTo().window(parentWindowHandler);

        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[ngf-select=\"upload($file)\"]")));

        driver.findElements(By.cssSelector("input[type='file']")).get(0).sendKeys(filePath);

        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("rect")));


        List<WebElement> rects = driver.findElements(By.cssSelector("rect"));

        WebElement personRect = null;
        double size = 0;

        for (WebElement rect: rects) {
            double currSize = Double.valueOf(rect.getAttribute("width")) * Double.valueOf(rect.getAttribute("height"));
            System.out.println(currSize);
            if (currSize > size){
                size = currSize;
                personRect = rect;
            }
        }

        personRect.click();

        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[ng-click=\"viewUserProfileFound(user)\"]")));


        WebElement element = driver.findElements(By.cssSelector("div[ng-click=\"viewUserProfileFound(user)\"]")).get(0);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);

        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[ng-click=\"goVkProfileUser(userData)\"]")));

        driver.findElement(By.cssSelector("button[ng-click=\"goVkProfileUser(userData)\"]")).click();

        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.numberOfWindowsToBe(2));


        String vkHandle = null;

        for (String handle: driver.getWindowHandles()) {
            vkHandle = handle;
        }

        driver.switchTo().window(vkHandle);

        return driver.getCurrentUrl();
    }

    public static String[] getPersonAudio() throws InterruptedException {

        driver.findElement(By.cssSelector("#profile_audios > a.module_header")).click();
        JavascriptExecutor jse = ((JavascriptExecutor) driver);

        for (int i = 0; i < 3; i++) {
        Thread.sleep(1000);
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
        Thread.sleep(1000);

        List<WebElement> audioWrappers = driver.findElements(By.cssSelector(".audio_row_with_cover"));

        String[] audios = new String[audioWrappers.size()];

        for (int i = 0; i < audios.length; i++) {
            String wrapperClass = audioWrappers.get(i).getAttribute("class");
            audios[i] = driver.findElement(By.cssSelector("."+wrapperClass.replaceAll(" ",".") + " " + ".audio_row__title_inner._audio_row__title_inner")).getText() + " - " + driver.findElement(By.cssSelector("."+wrapperClass.replaceAll(" ",".") + " " + "a[onmouseover=\"setTitle(this)\"]")).getText();
        }


        return  audios;
    }
}
