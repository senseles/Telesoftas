package lt.telesoft.restController.json;

import com.fasterxml.jackson.annotation.JsonValue;


public enum ResponseStatus
{
	SUCCESS,
	ERROR;

	@JsonValue
	protected String toJson() {
		return name().toLowerCase();
	}
}
