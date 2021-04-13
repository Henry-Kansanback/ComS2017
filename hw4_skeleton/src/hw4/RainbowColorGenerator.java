package hw4;

import java.awt.Color;
import java.util.Random;

import color.ColorGenerator;
import main.Config;

public class RainbowColorGenerator implements ColorGenerator {
	private static final Color COLOR_BASE = new Color(0.0f, 0.25f, 0.0f);

	private Random r = Config.RANDOM;

	@Override
	public Color createColor() {
		int next = r.nextInt(6);
		switch (next) {
		case 0:
			return new Color(0.25f, 0.0f, 0.0f);
		case 1:
			return new Color(0.25f, 0.125f, 0.0f);
		case 2:
			return new Color(0.25f, 0.25f, 0.0f);
		case 3:
			return new Color(0.0f, 0.25f, 0.0f);
		case 4:
			return new Color(0.0f, 0.0f, 0.25f);
		case 5:
			return new Color(0.25f, 0.0f, 0.25f);
		default:
			return COLOR_BASE;
		}
	}

}
