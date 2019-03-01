package top.forcebing.borrowhome.common.repository;

import top.forcebing.borrowhome.common.model.FileModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<FileModel,Integer> {


}