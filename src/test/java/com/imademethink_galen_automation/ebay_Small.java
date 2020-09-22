package com.imademethink_galen_automation;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class ebay_Small
{

    //	Testing general web element attributes
    String galenSampleUrl 		= "https://ebay.com.au";

    @Test
    public void homePageLayoutTest() throws IOException
    {
        // testing done at specific browser size which can be customised per user needs
        driver.manage().window().setSize(new Dimension(browserSizeSmallW, browserSizeSmallH));

        //Create a layoutReport object
        //checkLayout function checks the layout and returns a LayoutReport object
        LayoutReport objLayoutReport =
                Galen.checkLayout(driver, specFilePath, Arrays.asList("Desktop_Small"));

        //Create a galen test info list
        List<GalenTestInfo> objGalentestsList	= new LinkedList<GalenTestInfo>();
        //Create a GalenTestInfo object
        GalenTestInfo objSingleGalenTest 		= GalenTestInfo.fromString("ebay size test");
        //Get layoutReport and assign to test object
        objSingleGalenTest.getReport().layout(objLayoutReport, "ebay size test small");
        //Add test object to the tests list
        objGalentestsList.add(objSingleGalenTest);
        //Create a htmlReportBuilder object
        HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
        //Create a report under specified folder based on tests list
        htmlReportBuilder.build(objGalentestsList, "ReportFolder_ebay_small");
        //If layoutReport has errors Assert Fail
        if (objLayoutReport.errors() > 0)
        {
            System.out.println("Layout test failed for ebay size test small");
            Assert.fail();
        }
        System.out.println("Layout test PASSED for ebay size test small");
    }

    private WebDriver driver = null;
    String specFilePath 			= "galen_spec_gspec/ebay_sizes.gspec";
    int browserSizeLargeW 	= 1500; int browserSizeLargeH 	= 1200;
    int browserSizeMediumW= 1152; int browserSizeMediumH = 864;
    int browserSizeSmallW 	= 950; int browserSizeSmallH 		= 600;

    @Before
    public void setUp()
    {
        String browserDriverBasePath 				= new File("").getAbsoluteFile().toString() + File.separator + "Resources" + File.separator + "BrowserDriver" + File.separator;
        String browserDriverBasePathChrome 	= browserDriverBasePath + "chromedriver.exe";
        File chromeDriverExecutable 					= new File(browserDriverBasePathChrome);
        chromeDriverExecutable.setExecutable(true);
        System.setProperty("webdriver.chrome.driver",browserDriverBasePathChrome);
        driver 															= new ChromeDriver();
        driver.get(galenSampleUrl);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}