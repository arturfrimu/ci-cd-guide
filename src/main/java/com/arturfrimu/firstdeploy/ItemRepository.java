package com.arturfrimu.firstdeploy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<FirstDeployApplication.Item, Long> {
}
