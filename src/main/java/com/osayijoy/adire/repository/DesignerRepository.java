package com.osayijoy.adire.repository;

import com.osayijoy.adire.entity.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesignerRepository extends JpaRepository<Designer,String> {

    Optional<Designer>  findByEmail(String email);
}
