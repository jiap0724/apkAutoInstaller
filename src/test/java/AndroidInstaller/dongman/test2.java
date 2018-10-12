package AndroidInstaller.dongman;

import java.io.File;

public class test2 {

	public int apklist() {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/apps");
		File[] apks = appDir.listFiles();
		int apksnumber = 0;
		for (int i = 0; i < apks.length; i++) {
			apksnumber++;
		}
		return apks.length;
	}

	public static void main(String[] args) {
		test2 t = new test2();
		t.apklist();
		int a = t.apklist();
		System.out.println(a);
	}

}
