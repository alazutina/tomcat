package event.repository;

import java.util.List;

interface GenericRepository <T,ID>{

      T save(T t);
      T update(T t);
      List<T> getAll();
      T getById(ID id);
      void deleteById(ID id);
}
