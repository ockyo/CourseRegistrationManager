
package controller;

import exception.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Registering;

public class InfoFilter {
    
    public boolean isStudentIdValid(String id) throws InvalidStudentIdException {
        var regex = "^[a-z]\\d{2}[a-z]{4}\\d{3}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(id);
        if(matcher.matches()) {
            return true;
        } else {
            var msg = "Mã sinh viên không hợp lệ: " + id;
            throw new InvalidStudentIdException(id, msg);
        }
    }

    public boolean isPersonIdValid(String id) throws InvalidPersonIdException {
        var regex = "^([A-Z0-9]{9,13})$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(id);
        if(matcher.matches()) {
            return true;
        } else {
            var msg = "Số CMND/Căn cước/Hộ chiếu không hợp lệ: " + id;
            throw new InvalidPersonIdException(id, msg);
        }
    }

    public boolean isNameValid(String name) throws InvalidNameException {
        var regex = "^[\\D]{2,50}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | 
                Pattern.CANON_EQ | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()) {
            return true;
        } else {
            var msg = "Họ tên không hợp lệ: " + name;
            throw new InvalidNameException(name, msg);
        }
    }

    public boolean isEmailValid(String email) throws InvalidEmailException {
        var regex = "^[a-z]+[a-z0-9._]*@gmail.com$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()) {
            return true;
        } else {
            var msg = "Email không hợp lệ: " + email;
            throw new InvalidEmailException(email, msg);
        }
    }

    public boolean isPhoneNumberValid(String phoneNumber) 
            throws InvalidPhoneNumberException {
        var regex = "^[0]{1}[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if(matcher.matches()) {
            return true;
        } else {
            var msg = "Số điện thoại không hợp lệ: " + phoneNumber;
            throw new InvalidPhoneNumberException(phoneNumber, msg);
        }
    }

    public boolean isDateOfBirthValid(String dobString) 
            throws InvalidDateOfBirthException {
        var regex = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dobString);
        if(matcher.matches()) {
            return true;
        } else {
            var msg = "Ngày sinh không hợp lệ: " + dobString;
            throw new InvalidDateOfBirthException(dobString, msg);
        }
    }
    
    public boolean isRecordExist(List<Registering> registerings, Registering r) {
        return registerings.contains(r);
    }
}
