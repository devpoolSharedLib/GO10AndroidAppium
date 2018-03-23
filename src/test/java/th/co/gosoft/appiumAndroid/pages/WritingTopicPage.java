package th.co.gosoft.appiumAndroid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WritingTopicPage extends BasePage {
	
	By txtHostSubject = By.id(app_package_name+"txtHostSubject");
	By txtHostContent = By.xpath("//android.widget.FrameLayout[contains(@resource-id, 'content_frame')]/android.widget.LinearLayout"
			+ "/android.webkit.WebView/android.webkit.WebView/android.view.View");
	By btnPost = By.id(app_package_name+"btnPost");
	
	public WritingTopicPage(WebDriver driver) {
		super(driver);
	}
	
	public BoardContentPage writeSubjectAndContent(){
		waitForVisibilityOf(txtHostSubject);
		driver.findElement(txtHostSubject).sendKeys(BoardContentPage.HOST_SUBJECT);
		
		waitForVisibilityOf(txtHostContent);
		WebElement content = driver.findElement(txtHostContent);
		content.click();
		content.sendKeys(BoardContentPage.HOST_CONTENT);
		driver.findElement(btnPost).click();
		
		return new BoardContentPage(driver);
	}
	
	public BoardContentPage createNewTopicForDelete(){
		waitForVisibilityOf(txtHostSubject);
		driver.findElement(txtHostSubject).sendKeys(BoardContentPage.HOST_SUBJECT_DELETE);
		
		waitForVisibilityOf(txtHostContent);
		WebElement content = driver.findElement(txtHostContent);
		content.click();
		content.sendKeys(BoardContentPage.HOST_CONTENT_DELETE);
		driver.findElement(btnPost).click();
		
		return new BoardContentPage(driver);
	}
}
