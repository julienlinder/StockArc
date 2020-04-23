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

WebUI.setEncryptedText(findTestObject('Page_Login/input_Password_password'), '4nvbrPglk7k=')

WebUI.sendKeys(findTestObject('Page_Login/input_Password_password'), Keys.chord(Keys.ENTER))

WebUI.verifyElementPresent(findTestObject('Page_Login/div_Name or Password is incorrect Please ch_8a3690'), 0)

WebUI.setText(findTestObject('Page_Login/input_Username_username'), 'gael.christe')

WebUI.setEncryptedText(findTestObject('Page_Login/input_Password_password'), 'RigbBhfdqOBGNlJIWM1ClA==')

WebUI.sendKeys(findTestObject('Page_Login/input_Password_password'), Keys.chord(Keys.ENTER))

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Content page 1/h1_Rent'), 0)

WebUI.closeBrowser()

