package com.safetynet.safetynetalerts.service.alert;



import com.safetynet.safetynetalerts.model.detail.Childs;
import com.safetynet.safetynetalerts.model.detail.Phone;

import java.util.List;

public interface AlertService {
    //public List<Person> getChildByAddress (String address);
    public Childs getChildByAddress (String address);
    public List<Phone> getPhoneByStation (String station);
}
