package chinanko.chinanko.mapper;
import org.springframework.stereotype.Component;

import chinanko.chinanko.model.StateOfEvent;
import chinanko.chinanko.dto.StateOfEventResponse;

@Component
public class StateOfEventMapper {

   
    public static StateOfEventResponse toResponse(StateOfEvent s){
        if(s == null) return null;
        return StateOfEventResponse.builder()
               .IdStateOfEvent(s.getIdStateEvent())
               .state(s.getState())
               .build();
    }
}
