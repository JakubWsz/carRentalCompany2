package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.ReturnStore;
import pl.kuba.entities.Return;
import pl.kuba.infrastructure.persistence.ReturnRepository;

import java.util.List;

@Component
public class DatabaseReturnStore implements ReturnStore {
   private final ReturnRepository returnRepository;

    public DatabaseReturnStore(ReturnRepository returnRepository) {
        this.returnRepository = returnRepository;
    }

    @Override
    public Return save(Return aReturn) {
        return returnRepository.save(aReturn);
    }

    @Override
    public List<Return> findAll() {
        return returnRepository.findAll();
    }
}
