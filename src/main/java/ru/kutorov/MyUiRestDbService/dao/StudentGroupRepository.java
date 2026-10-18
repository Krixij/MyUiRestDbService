package ru.kutorov.MyUiRestDbService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kutorov.MyUiRestDbService.entity.StudentGroup;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Integer>{
}
