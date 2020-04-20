package com.safetynet.safetynetalerts.service.alert;

import com.safetynet.safetynetalerts.model.Person;

import java.util.List;

public interface AlertService {
    public List<Person> getChildByAddress (String address);
}
