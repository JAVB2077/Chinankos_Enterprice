package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.StateRequest;
import chinanko.chinanko.dto.StateResponse;
import chinanko.chinanko.model.State;

@Component
public class StateMapper {

    public static State toEntity(StateRequest r) {
        if (r == null)
            return null;
        return State.builder()
                .nameState(r.getNameState())
                .longitude(r.getLongitude())
                .latitude(r.getLatitude())
                .build();
    }

    public static StateResponse toResponse(State s) {
        if (s == null)
            return null;
        if (s.getTowns() == null)
            s.setTowns(java.util.Collections.emptyList());
        return StateResponse.builder()
                .idState(s.getIdState())
                .nameState(s.getNameState())
                .longitude(s.getLongitude())
                .latitude(s.getLatitude())
                .towns(s.getTowns().stream().map(t -> new TownMapper().toResponse(t)).toList())
                .build();
    }

    public static void copyToEntity(State s, StateRequest r) {
        if (r == null || s == null)
            return;
        s.setNameState(r.getNameState());
        s.setLongitude(r.getLongitude());
        s.setLatitude(r.getLatitude());
        if (s.getTowns() == null)
            s.setTowns(java.util.Collections.emptyList());

    }
}
