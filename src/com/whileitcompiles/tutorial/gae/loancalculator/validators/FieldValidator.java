/*
 *   Copyright 2010 http://whileitcompiles.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *   
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.whileitcompiles.tutorial.gae.loancalculator.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Filed validator
 * @author lukas
 * @version 1.0
 */
public class FieldValidator implements Validator {
	//attribute to read field name from
	public static final String FIELD_NAME_ATTR = "fieldName";
	//field name to validate
	private String fieldName;
	
	/**
	 * Validate field value
	 * @param context <tt>FacesContext</tt>
	 * @param component component to be validated
	 * @param value value to be validated
	 * @since 1.0
	 */
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		//get field name
		fieldName = (String) component.getAttributes().get(FIELD_NAME_ATTR);
		//convert value to string
		String strValue = value.toString();
		//get number representation
        double number = convertToNumber(strValue);
        
        //value must be grater than 0
        if (number <= 0) {
        	//throw validation exception for given field
        	throwException(fieldName + " must be a positive number");
        }
	}
	
	/**
	 * Converts value to double number
	 * @param strValue value
	 * @return double value representation
	 * @throws <tt>ValidatorException</tt> if value could not be converted
	 * @since 1.0
	 */
	private double convertToNumber(String strValue) {
		double number = 0;
        try {
        	//try to convert string to double number
        	number = Double.parseDouble(strValue);
        	//in case of any exception
        } catch (Exception exception) {
        	//throw validation exception for given field
            throwException(fieldName + " must be a number");
        }
        
		return number;
    }
	
	/**
	 * Throws <tt>ValidationException</tt>
	 * @param errMessage error message to throw
	 * @since 1.0
	 * @throws <tt>ValidationException</tt>
	 */
	private void throwException(String errMessage){
		//create message to be thrown with exception
        FacesMessage message = new FacesMessage();
        message.setDetail(errMessage);
        message.setSummary(errMessage);
        //set severity to error
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        //throw validation exception
        throw new ValidatorException(message);        
	}
}
