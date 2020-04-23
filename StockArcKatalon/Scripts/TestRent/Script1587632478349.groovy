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

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8090/login')

WebUI.setText(findTestObject('Page_Login/input_Username_username'), 'gael.christe')

WebUI.setEncryptedText(findTestObject('Page_Login/input_Password_password'), 'RigbBhfdqOBGNlJIWM1ClA==')

WebUI.sendKeys(findTestObject('Page_Login/input_Password_password'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/td_Hammer(10)                Screwdriver(15_f8603d'))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/span_Hammer(10)'))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/tr_Hammer(10)                Screwdriver(15_d5e915'))

WebUI.setText(findTestObject('Object Repository/Page_Content page 1/input_Screwdriver(15)_quantity'), '3')

WebUI.click(findTestObject('Object Repository/Page_Content page 1/td_jean(Industrie)            jean(Industrie)'))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/span_jean(Industrie)'))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/input_jean(Industrie)_form-control btn btn-primary'))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/td_Hammer(10)                Screwdriver(12_3ecf16'))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/span_Hammer(10)'))

WebUI.setText(findTestObject('Object Repository/Page_Content page 1/input_Screwdriver(15)_quantity'), '5')

WebUI.click(findTestObject('Object Repository/Page_Content page 1/input_jean(Industrie)_form-control btn btn-primary'))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/span_Hammer(10)'))

WebUI.setText(findTestObject('Object Repository/Page_Content page 1/input_Screwdriver(15)_quantity'), '3')

WebUI.click(findTestObject('Object Repository/Page_Content page 1/input_jean(Industrie)_form-control btn btn-primary'))

