package ch.lifeguardz.projects.pokeClean.forms;

import org.springframework.util.StringUtils;

public class TransferForm {

	private String longIds;

	public String getLongIds() {
		return longIds;
	}

	public void setLongIds(String longIds) {
		this.longIds = longIds;
	}

	public String[] getLongIdList() {
		return StringUtils.delimitedListToStringArray(getLongIds(), ",");
	}
	
}
