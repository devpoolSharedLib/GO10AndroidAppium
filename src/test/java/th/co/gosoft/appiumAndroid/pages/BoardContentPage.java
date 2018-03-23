package th.co.gosoft.appiumAndroid.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardContentPage extends BasePage {
	
	public static final String HOST_SUBJECT = "Appium - Android New Topic";
    public static final String HOST_CONTENT = "Appium - android new content";
    public static final String COMMENT_CONTENT = "Appium - android new comment";
	public static final CharSequence HOST_SUBJECT_DELETE = "Appium - Android New Topic For Delete";
	public static final CharSequence HOST_CONTENT_DELETE = "Appium - android new comment for delete";
	public static final String COMMENT_FOR_DELETE1 = "Appium - android new comment One";
	public static final String COMMENT_FOR_DELETE2 = "Appium - android new comment Two";
	    
	By commentListView = By.id(app_package_name + "commentListView"); 
	By hostSubject = By.id(app_package_name + "hostSubject");
//	By hostContent = By.id(app_package_name + "hostContent");
	By hostContent = By.xpath("//android.widget.ListView[contains(@resource-id, 'commentListView')]/android.widget.RelativeLayout[@index='0']"
			+ "/android.webkit.WebView[@index='5']/android.webkit.WebView[@index='0']/android.view.View[@index='0']");
	By btnComment = By.xpath("//android.widget.Button[contains(@resource-id, 'btnComment')]");
	By btnLike = By.id(app_package_name + "btnLike");
	By txtLikeCount = By.id(app_package_name + "txtLikeCount");
	By btnDeleteHost = By.xpath("//android.widget.ListView[contains(@resource-id, 'commentListView')]/android.widget.RelativeLayout[@index='0']"
			+ "/android.widget.ImageButton[contains(@resource-id,'btnDelete')]");
	By btnDeleteComment1 = By.xpath("//android.widget.ListView[contains(@resource-id, 'commentListView')]/android.widget.RelativeLayout[@index='1']"
			+ "/android.widget.ImageButton[contains(@resource-id,'btnDelete')]");
	By btnDeleteComment2 = By.xpath("//android.widget.ListView[contains(@resource-id, 'commentListView')]/android.widget.RelativeLayout[@index='2']"
			+ "/android.widget.ImageButton[contains(@resource-id,'btnDelete')]");
	By btnDelete = By.id("android:id/text1");
	By textComment1 = By.id(app_package_name + "commentContent");

	public BoardContentPage(WebDriver driver) {
		super(driver);
	}
	
	public String getHostSubject(){
		waitForVisibilityOf(commentListView);
		return driver.findElement(hostSubject).getText();
	}
	
	public String getHostContent(){
		waitForVisibilityOf(commentListView);
		WebElement eleHostContent = driver.findElement(hostContent);
		String contentDesc = eleHostContent.getAttribute("name");
		return contentDesc;
	}
	
	public String getDeletContent(int index){
		String result;
		switch (index) {
		case 1 :
			waitForVisibilityOf(textComment1);
			result = driver.findElement(textComment1).getText();
			break;
		default:
			result = "";
			break;
		}
		return result;
	}
	
	public String getCommentContent(){
		String outputString;
		waitForVisibilityOf(commentListView);
		List<WebElement> webElementList = driver.findElements(By.xpath("//android.widget.ListView[contains(@resource-id, 'commentListView')]/android.widget.RelativeLayout"));
		if(webElementList.isEmpty()) {
			outputString = "Cannot find last comment.";
		} else {
			outputString = webElementList.get(webElementList.size()-1).findElement(By.id(app_package_name + "commentContent")).getText();
		}
		
		return outputString;
	}
	
	public WritingCommentPage pressCommentBtn(){
		waitForClickabilityOf(btnComment);
		driver.findElement(btnComment).click();
		return new WritingCommentPage(driver);
	}
	
	public WritingCommentPage pressDeleteBtn(int index){
		switch (index) {
		case 1 :
			waitForClickabilityOf(btnDeleteComment1);
			driver.findElement(btnDeleteComment1).click();
			break;
		case 2 :
			waitForClickabilityOf(btnDeleteComment2);
			driver.findElement(btnDeleteComment2).click();
			break;	
		default:
			break;
		}
		waitForClickabilityOf(btnDelete);
		driver.findElement(btnDelete).click();
		return new WritingCommentPage(driver);
	}
	
	public RoomPage pressDeleteTopic(){
		waitForClickabilityOf(btnDeleteHost);
		driver.findElement(btnDeleteHost).click();
		waitForClickabilityOf(btnDelete);
		driver.findElement(btnDelete).click();
		return new RoomPage(driver);
	}
	
	public BoardContentPage pressLikeBtn(){
		waitForClickabilityOf(btnLike);
		driver.findElement(btnLike).click();
		return new BoardContentPage(driver);
	}
	
	public String getLikeCount(){
		waitForClickabilityOf(txtLikeCount);
		return driver.findElement(txtLikeCount).getText();
	}

}
