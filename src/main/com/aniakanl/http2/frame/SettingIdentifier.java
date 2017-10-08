package com.aniakanl.http2.frame;

public enum SettingIdentifier {
	SETTINGS_HEADER_TABLE_SIZE(0x1), 
	SETTINGS_ENABLE_PUSH(0x2), 
	SETTINGS_MAX_CONCURRENT_STREAMS(0x3), 
	SETTINGS_INITIAL_WINDOW_SIZE(0x4), 
	SETTINGS_MAX_FRAME_SIZE(0x5), 
	SETTINGS_MAX_HEADER_LIST_SIZE(0x6), 
	SETTINGS_NONE(0x0); // Not part of RFC																						// RFC

	int value;

	SettingIdentifier(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static SettingIdentifier getEnum(int value) {
		SettingIdentifier result = SettingIdentifier.SETTINGS_NONE;

		for (SettingIdentifier e : SettingIdentifier.values()) {
			if (e.getValue() == value)
				result = e;
		}
		return result;
	}

}
