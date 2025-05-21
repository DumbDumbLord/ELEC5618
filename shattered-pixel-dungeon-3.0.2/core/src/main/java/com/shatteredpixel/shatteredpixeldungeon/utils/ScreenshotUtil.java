package com.shatteredpixel.shatteredpixeldungeon.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
	public static void takeScreenshotToRoot() {
		try {
			Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
			// 翻转像素
			Pixmap flipped = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), pixmap.getFormat());
			for (int y = 0; y < pixmap.getHeight(); y++) {
				flipped.drawPixmap(pixmap, 0, pixmap.getHeight() - y - 1, 0, y, pixmap.getWidth(), 1);
			}
			String root = System.getProperty("user.dir");
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String filename = "screenshot_" + timestamp + ".png";
			File file = new File(root, filename);
			PixmapIO.writePNG(Gdx.files.absolute(file.getAbsolutePath()), flipped);
			pixmap.dispose();
			flipped.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
