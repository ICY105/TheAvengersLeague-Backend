package com.revature.errorhandling;

import java.util.Objects;

public class ApiValidationError extends ApiSubError{

	private String object; // the type
	private String field; // field name
	private Object rejectedValue; // the particular value
	private String message; // the reason
	
	public ApiValidationError(final String object, final String message) {
		this.object = object;
		this.message = message;
	}
	
	public ApiValidationError(final String object, final String message, final String field) {
		this(object, message);
		this.field = field;
	}
	
	public ApiValidationError(final String object, final String message, final String field, final Object rejectedValue) {
		this(object, message, field);
		this.rejectedValue = rejectedValue;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(final String object) {
		this.object = object;
	}

	public String getField() {
		return this.field;
	}

	public void setField(final String field) {
		this.field = field;
	}

	public Object getRejectedValue() {
		return this.rejectedValue;
	}

	public void setRejectedValue(final Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ApiValidationError [object=" + this.object + ", field=" + this.field + ", rejectedValue=" + this.rejectedValue
				+ ", message=" + this.message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(this.field, this.message, this.object, this.rejectedValue);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ApiValidationError other = (ApiValidationError) obj;
		return Objects.equals(this.field, other.field) && Objects.equals(this.message, other.message)
				&& Objects.equals(this.object, other.object) && Objects.equals(this.rejectedValue, other.rejectedValue);
	}
	
}
