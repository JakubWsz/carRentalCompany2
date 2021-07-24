package pl.kuba.domain.repository;

import pl.kuba.domain.stores.ReturnStore;
import pl.kuba.entities.Return;

import java.util.ArrayList;
import java.util.List;

public class TestReturnStore implements ReturnStore {
  List<Return> returns = new ArrayList<>();

    @Override
    public Return save(Return aReturn) {
        returns.add(aReturn);
        return aReturn;
    }

    @Override
    public List<Return> findAll() {
        return new ArrayList<>(returns);
    }
}
