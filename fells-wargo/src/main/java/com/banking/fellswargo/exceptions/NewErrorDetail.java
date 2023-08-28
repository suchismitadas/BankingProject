package com.banking.fellswargo.exceptions;

//package com.banking.fellswargo.exceptions;

import java.util.Date;

public class NewErrorDetail {
    private Date timestamp;
    private String message;
    private String details;
    private String property;
    private String code;

    public NewErrorDetail(Date timestamp, String message, String details, String property, String code) {
         super();
         this.timestamp = timestamp;
         this.message = message;
         this.details = details;
         this.property = property;
         this.code = code;
    }

    public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTimestamp() {
         return timestamp;
    }

    public String getMessage() {
         return message;
    }

    public String getDetails() {
         return details;
    }
}

