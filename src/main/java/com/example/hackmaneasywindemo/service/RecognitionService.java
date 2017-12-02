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


public class RecognitionService {

    public static WebDriver driver = new ChromeDriver();

    public static String vkLogin = "89118465234";
    public static String vkPassword = "Test1234567";
    public static String filePath = "/Users/anastasiavaskina/hackmaneasywin/test.png";

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

    public static String[] getPersonAudio(String id) throws InterruptedException {
        String[] strings = new String[10];
        return strings;
    }
}
