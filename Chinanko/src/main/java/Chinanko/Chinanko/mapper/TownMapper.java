package Chinanko.Chinanko.mapper;

import org.springframework.stereotype.Component;

import Chinanko.Chinanko.controller.StateController;
import Chinanko.Chinanko.dto.TownRequest;
import Chinanko.Chinanko.dto.TownResponse;
import Chinanko.Chinanko.model.State;
import Chinanko.Chinanko.model.Town;
import Chinanko.Chinanko.repository.StateRepository;
import Chinanko.Chinanko.service.StateService;

@Component
public class TownMapper {

    public static Town toEntity(TownRequest r){
        if(r==null) 
        return null;
        return Town.builder()
            .nameTown(r.getNameTown())
            .latitude(r.getLatitude())
            .longitude(r.getLongitude())
            .state(State.builder().idState(r.getStateId()).build())
            .build();
    }

    public static TownResponse toResponse(Town t){
        if(t==null) 
        return null;
        
        return TownResponse.builder()
            .idTown(t.getIdTown())
            .nameTown(t.getNameTown())
            .latitude(t.getLatitude())
            .longitude(t.getLongitude())
            .stateName(t.getState().getNameState())
            .build();
    }

    public static void copyToEntity(Town t, TownRequest r){
        if(r==null || t==null) return;
        t.setNameTown(r.getNameTown());
        t.setLatitude(r.getLatitude());
        t.setLongitude(r.getLongitude());
        t.setState(State.builder().idState(r.getStateId()).build());
    }
}
