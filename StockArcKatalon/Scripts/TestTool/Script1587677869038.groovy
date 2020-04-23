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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

println (System.getenv( 'local_addr' ))
WebUI.navigateToUrl('http://'+System.getenv( 'local_addr' )+"/login")

WebUI.setText(findTestObject('Page_Login/input_Email_email'), 'test@test.test')

WebUI.setEncryptedText(findTestObject('Page_Login/input_Forgot password_password'), 'xE1QVbiWs2d8zyB2/oE82A==')

WebUI.click(findTestObject('Page_Login/button_Login'))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/a_Tools'))

WebUI.setText(findTestObject('Object Repository/Page_Content page 1/input_Quantity_name'), 'Allumette')

WebUI.click(findTestObject('Object Repository/Page_Content page 1/span_UNIQUE'))

WebUI.setText(findTestObject('Object Repository/Page_Content page 1/input_DISPOSABLE_quantity'), '10')

WebUI.click(findTestObject('Object Repository/Page_Content page 1/input_DISPOSABLE_form-control btn btn-primary'))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/td_Allumette'))

WebUI.click(findTestObject('Object Repository/Page_Content page 1/td_DISPOSABLE'))

WebUI.closeBrowser()

