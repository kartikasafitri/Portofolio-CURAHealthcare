import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import java.time.LocalDate

// Ambil tanggal hari ini
LocalDate today = LocalDate.now()
int day = today.getDayOfMonth()

WebUI.callTestCase(findTestCase('Login/tc01_login_valid'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.selectOptionByValue(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//select[@id='combo_facility']"),'Seoul CURA Healthcare Center', true)
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//input[@id='radio_program_medicare']"))
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='txt_visit_date']"))
// Tunggu datepicker muncul
WebUI.waitForElementVisible(new TestObject().addProperty('xpath', ConditionType.EQUALS,
    "//div[contains(@class,'datepicker-dropdown') and contains(@style,'display: block')]"), 10)
// Klik tanggal hari ini
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,
    "//div[contains(@class,'datepicker-dropdown') and contains(@style,'display: block')]//td[@class='day' and normalize-space(text())='" + day + "']"))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//textarea[@id='txt_comment']"))
WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//textarea[@id='txt_comment']"),'Konsultasi dengan dokter syaraf')

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//button[@id='btn-book-appointment']"))
WebUI.verifyElementPresent(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//section[@id='summary']/div/div/div/h2"),0)