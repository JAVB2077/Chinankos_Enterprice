package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.controller.StateController;
import chinanko.chinanko.dto.TownRequest;
import chinanko.chinanko.dto.TownResponse;
import chinanko.chinanko.model.State;
import chinanko.chinanko.model.Town;
import chinanko.chinanko.repository.StateRepository;
import chinanko.chinanko.service.StateService;

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
