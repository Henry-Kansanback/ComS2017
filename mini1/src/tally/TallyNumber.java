package tally;

public class TallyNumber {
	private String tally;
	private int value;
	private int given;
	private boolean valid;
	private String tall;
	private int place;

	public TallyNumber(String givenString) {
		int total = 0;
		int values = 0;
		int place = 0;
		valid = true;
		boolean space = false;
		if (valid) {
			for (int i = givenString.length(); i > 0; i--) {

				if ((givenString.charAt(i) == '|') || (givenString.charAt(i) == '*') || (givenString.charAt(i) == '0')
						|| (givenString.charAt(i) == ' ')) {
					if (givenString.charAt(i) == '|') {
						if (space == false) {
							values = values + 1;
						}

						else if (space == true) {
							total = total + (values * (10 ^ place));
							values = 0 + 1;
							space = false;
						}
					}
					if (givenString.charAt(i) == '*') {
						if (space == false) {
							values = values + 5;
						}

						else if (space == true) {
							total = total + (values * (10 ^ place));
							values = 0 + 5;
							space = false;
						}

					}
					if (givenString.charAt(i) == '0') {
						if (space == false) {
							values = values + 0;
						}

						else if (space == true) {
							total = total + (values * (10 ^ place));
							values = 0;
							space = false;
						}
					}
					if (givenString.charAt(i) == ' ') {
						if (space == false) {
							place = place + 1;
							this.place = place;
							space = true;
						}
						if (space == true) {

						}
					}
					if (i == 0) {
						tally = givenString;
						valid = true;
					}
				} else if ((givenString.charAt(i) != '|') && (givenString.charAt(i) != '*')
						&& (givenString.charAt(i) != '0') && (givenString.charAt(i) != ' ')) {
					valid = false;
				}
				given = total;
				this.value = total;
			}
		}

	}

	public TallyNumber(int givenValue) {
		if (givenValue >= 0) {
			given = givenValue;
			value = givenValue;
			normalize();
		}
	}

	public void add(TallyNumber other) {

	}

	public void combine(TallyNumber other) {

	}

	public int getIntValue() {
		return value;
	}

	public String getStringValue() {
		return tally;
	}

	public void normalize() {
		tall = "";
		int result = given;
		int i = 0;
		int p[] = new int[10];
		for (i = 0; result != 0; i++) {
			p[i] = result % 10;
			result = result % 10;
			if (result < 9) {
				String group = (makeOneGroup(result));
				tall += group;
			}

		}
	}

	private String makeOneGroup(int num) {

		return null;
	}
}
