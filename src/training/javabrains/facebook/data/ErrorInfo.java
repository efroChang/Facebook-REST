package training.javabrains.facebook.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is to display nice error message in JSON format. 
 * Will be added to Response object in ExceptionMappers.
 *
 */

@XmlRootElement
public class ErrorInfo {

	private String errorMessage;
	private int errorCode;
	private String documentation;

	public ErrorInfo() {
	}

	public ErrorInfo(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

}
