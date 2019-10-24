package ru.spart.appteka.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spart.appteka.controller.model.Drugs;
import ru.spart.appteka.repository.DrugsDataRepository;
import ru.spart.appteka.repository.UserDataRepository;
import ru.spart.appteka.repository.model.DrugsData;
import ru.spart.appteka.repository.model.UserData;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrugsService {

    private final DrugsDataRepository drugsDataRepository;
    private final UserDataRepository userDataRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public DrugsService(DrugsDataRepository drugsDataRepository, UserDataRepository userDataRepository, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.drugsDataRepository = drugsDataRepository;
        this.userDataRepository = userDataRepository;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Transactional
    public Long add(Drugs drugs) {
        DrugsData drugsData = new DrugsData();
        drugsData.setName(drugs.getName());
        drugsData.setType_id(drugs.getType_id());
        drugsData.setCount(drugs.getCount());
        drugsData.setAppointent_id(drugs.getAppointment_id());
        drugsData.setDate(drugs.getDate());
        drugsData.setUserData(getUserData());

        drugsDataRepository.saveAndFlush(drugsData);
        return drugsData.getId();
    }

    @Transactional
    public void update(long id, Drugs drugs) throws DrugsNotFound {
        DrugsData drugsData = drugsDataRepository.findByIdAndUserData(id, getUserData())
                .orElseThrow(DrugsNotFound::new);
        drugsData.setName(drugs.getName());
        drugsData.setType_id(drugs.getType_id());
        drugsData.setCount(drugs.getCount());
        drugsData.setAppointent_id(drugs.getAppointment_id());
        drugsData.setDate(drugs.getDate());

        drugsDataRepository.saveAndFlush(drugsData);
    }

    @Transactional
    public void updateAll(List<Drugs> drugs) throws DrugsNotFound {
        ArrayList<Drugs> drugsArrayList = new ArrayList<>(drugs);
        for (Drugs drug : drugsArrayList) {
//            if(drugs.getName().equals("")
//                    &&secret.getLogin().equals("")
//                    &&secret.getPassword().equals("")){
//                deleteSecret(secret.getId());
//            }
            //else {
                DrugsData drugsData = (drugsDataRepository.findById(drug.getId())
                        .orElseThrow(DrugsNotFound::new));
            drugsData.setName(drug.getName());
            drugsData.setType_id(drug.getType_id());
            drugsData.setCount(drug.getCount());
            drugsData.setAppointent_id(drug.getAppointment_id());
            drugsData.setDate(drug.getDate());
                drugsDataRepository.saveAndFlush(drugsData);
            }

        }

    @Transactional(readOnly = true)
    public Drugs getDrug(long id) throws DrugsNotFound {
        DrugsData drugsData = drugsDataRepository.findByIdAndUserData(id, getUserData())
                .orElseThrow(DrugsNotFound::new);
        Drugs drugs = new Drugs();
        drugs.setId(drugsData.getId());
        drugs.setName(drugsData.getName());
        drugs.setType_id(drugsData.getType_id());
        drugs.setCount(drugsData.getCount());
        drugs.setAppointment_id(drugsData.getAppointent_id());
        drugs.setDate(drugsData.getDate());

        return drugs;
    }

    @Transactional
    public void deleteDrug(long id) throws DrugsNotFound {
        DrugsData drugsData = drugsDataRepository.findByIdAndUserData(id, getUserData())
                .orElseThrow(DrugsNotFound::new);
        drugsDataRepository.deleteById(drugsData.getId());
    }

    @Transactional
    public List<Drugs> getAllDrugs() {
        List<Drugs> allDrugs = new ArrayList<>();
        List<DrugsData> allDrugsData = drugsDataRepository.findAllByUserData(getUserData());

        for (DrugsData drug : allDrugsData) {
            Drugs newDrug = new Drugs();
            newDrug.setId(drug.getId());
            newDrug.setName(drug.getName());
            newDrug.setType_id(drug.getType_id());
            newDrug.setCount(drug.getCount());
            newDrug.setAppointment_id(drug.getAppointent_id());
            newDrug.setDate(drug.getDate());

            allDrugs.add(newDrug);
        }

        return allDrugs;
    }

    private UserData getUserData() {
        UserDetails userDetails = userDetailsServiceImpl.getCurrent();
        return userDataRepository.findByUserLogin(userDetails.getUsername())
                .orElseThrow(RuntimeException::new);
    }
}
