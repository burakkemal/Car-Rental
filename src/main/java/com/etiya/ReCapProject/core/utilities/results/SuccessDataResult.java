package com.etiya.ReCapProject.core.utilities.results;

public class SuccessDataResult<T> extends DataResult<T> {

	public SuccessDataResult (T data,String message) {
		super(true, data, message);
	}

	public SuccessDataResult(T data) {
		super(true, data);
	}

}
