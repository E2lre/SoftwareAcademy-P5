package com.safetynet.safetynetalerts.service.alert;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.Phone;

import java.util.List;

public interface AlertService {
    public List<Person> getChildByAddress (String address);
    public List<Phone> getPhoneByStation (String station);
}
