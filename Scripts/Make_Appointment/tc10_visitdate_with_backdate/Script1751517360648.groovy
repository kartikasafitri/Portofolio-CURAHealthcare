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
import java.time.format.DateTimeFormatter
import java.util.concurrent.ThreadLocalRandom

// Ambil hari ini
LocalDate today = LocalDate.now()

// Tentukan batas random backdate (1 sampai yesterday)
int randomDay = ThreadLocalRandom.current().nextInt(1, today.getDayOfMonth())

// Bentuk tanggal backdate dari bulan ini
LocalDate backDate = today.withDayOfMonth(randomDay)
int day = backDate.getDayOfMonth()
int month = backDate.getMonthValue()
int year = backDate.getYear()

// Format bulan-tahun untuk pengecekan header datepicker
DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
String monthYear = backDate.format(monthYearFormatter)

WebUI.callTestCase(findTestCase('Login/tc01_login_valid'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.selectOptionByValue(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//select[@id='combo_facility']"),'Seoul CURA Healthcare Center', true)
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//input[@id='radio_program_medicare']"))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='txt_visit_date']"))
// Tunggu datepicker muncul
WebUI.waitForElementVisible(new TestObject().addProperty('xpath', ConditionType.EQUALS,
    "//div[contains(@class,'datepicker-dropdown') and contains(@style,'display: block')]"), 10)

// Loop navigasi bulan jika bulan backdate berbeda dengan tampilan
while (true) {
	String currentMonthYear = WebUI.getText(new TestObject().addProperty('xpath', ConditionType.EQUALS,
		"//div[contains(@class,'datepicker-dropdown') and contains(@style,'display: block')]//th[@class='datepicker-switch']"))
	
	if (currentMonthYear.equals(monthYear)) {
		break
	} else {
		LocalDate displayedDate = LocalDate.parse("01 " + currentMonthYear, DateTimeFormatter.ofPattern("dd MMMM yyyy"))
		if (backDate.isAfter(displayedDate)) {
			WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,
				"//div[contains(@class,'datepicker-dropdown') and contains(@style,'display: block')]//th[@class='next']"))
		} else {
			WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,
				"//div[contains(@class,'datepicker-dropdown') and contains(@style,'display: block')]//th[@class='prev']"))
		}
		WebUI.delay(0.5)
	}
}

// Klik tanggal hasil random
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,
	"//div[contains(@class,'datepicker-dropdown') and contains(@style,'display: block')]//td[contains(@class,'day') and normalize-space(text())='" + day + "']"))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//textarea[@id='txt_comment']"))
WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//textarea[@id='txt_comment']"),'Konsultasi dengan dokter syaraf')

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//button[@id='btn-book-appointment']"))
WebUI.verifyElementPresent(new TestObject().addProperty('xpath', ConditionType.EQUALS,"//section[@id='summary']/div/div/div/h2"),0)