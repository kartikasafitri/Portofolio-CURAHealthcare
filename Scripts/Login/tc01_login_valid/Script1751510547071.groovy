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

WebUI.openBrowser('https://katalon-demo-cura.herokuapp.com/')
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//a[@id='btn-make-appointment']"))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//input[@id='txt-username']"))
WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//input[@id='txt-username']"),'John Doe')
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//input[@id='txt-password']"))
WebUI.setEncryptedText(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//input[@id='txt-password']"),'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM')
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//*[@id='btn-login']"))

WebUI.verifyElementPresent(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//section[@id='appointment']/div/div/form/div/label"),0)