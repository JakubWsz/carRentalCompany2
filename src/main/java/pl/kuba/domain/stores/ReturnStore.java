package pl.kuba.domain.stores;

import pl.kuba.entities.Return;

import java.util.List;

public interface ReturnStore {
    Return save(Return aReturn);

    List<Return> findAll();
}
