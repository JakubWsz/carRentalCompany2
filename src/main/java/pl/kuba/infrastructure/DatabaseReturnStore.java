package pl.kuba.infrastructure;

import org.springframework.stereotype.Component;
import pl.kuba.domain.ReturnStore;
import pl.kuba.entities.Return;

@Component
public class DatabaseReturnStore implements ReturnStore {
   private final ReturnStore returnRepository;

    public DatabaseReturnStore(ReturnStore returnRepository) {
        this.returnRepository = returnRepository;
    }

    @Override
    public Return save(Return aReturn) {
        return returnRepository.save(aReturn);
    }
}
