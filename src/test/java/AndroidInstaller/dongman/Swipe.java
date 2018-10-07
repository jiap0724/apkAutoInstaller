package AndroidInstaller.dongman;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class Swipe {

	public AndroidDriver<WebElement> driver;
	int Height;
	int Width;

	public Swipe(AndroidDriver<WebElement> driver) {
		this.driver = driver;
		this.Height = driver.manage().window().getSize().height;
		this.Width = driver.manage().window().getSize().width;

	}

	// 向上滑动
	public void swipe_Up(int duration) {
		driver.swipe(Width / 2, Height * 7 / 8, Width / 2, Height / 8, duration);
		// driver.swipe(startx, starty, endx, endy, duration);
	}

	// 向下滑动
	public void swipe_Down(int duration) {
		driver.swipe(Width / 2, Height / 8, Width / 2, Height * 7 / 8, duration);
	}

	// 向左滑动
	public void swipe_Left(int duration) {
		driver.swipe(Width * 7 / 8, Height / 2, Width / 8, Height / 2, duration);
	}

	// 向右滑动
	public void swipe_Right(int duration) {
		driver.swipe(Width / 8, Height / 2, Width * 7 / 8, Height / 2, duration);
	}

}
