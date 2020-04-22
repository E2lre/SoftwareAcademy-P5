package com.safetynet.safetynetalerts.service.complement;

import com.safetynet.safetynetalerts.model.detail.Email;
import com.safetynet.safetynetalerts.model.detail.Fire;
import com.safetynet.safetynetalerts.model.detail.Flood;
import com.safetynet.safetynetalerts.model.detail.PersonInfo;

import java.util.List;

public interface ComplementService {
    public List<Fire> getFireByAddress (String address);
    public List<Flood> getFloodByStationList (List<String> stationList);
    public List<PersonInfo> getPersonInfoByFisrtNameLastName (String firstName, String lastName);
    public List<Email> getEmailByCity (String city);

}
