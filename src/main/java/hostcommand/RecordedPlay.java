package hostcommand;

public class RecordedPlay {
	public RecordedPlay(String event, String refId, float wait, String eventSelectValue, boolean isPreviousPlayEnded) {
		this.event = event;
		this.refId = refId;
		this.wait = wait;
		this.eventSelectValue = eventSelectValue;
		this.isPreviousPlayEnded = isPreviousPlayEnded;
	}
	

	private String event;
	private String refId;
	private float wait;
	private String eventSelectValue;
	private Boolean isPreviousPlayEnded;
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	
	public float getWait() {
		return wait;
	}
	public void setWait(float wait) {
		this.wait = wait;
	}
	
	public String getEventSelectValue() {
		return eventSelectValue;
	}
	public void setEventSelectValue(String eventSelectValue) {
		this.eventSelectValue = eventSelectValue;
	}
	
	public Boolean getIsPreviousPlayEnded() {
		return isPreviousPlayEnded;
	}
	public void setIsPreviousPlayEnded(Boolean isPreviousPlayEnded) {
		this.isPreviousPlayEnded = isPreviousPlayEnded;
	}
	
	
}
