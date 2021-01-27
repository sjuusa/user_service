package com.sju.myapp.Repository;

import com.sju.myapp.Model.Address;
import java.util.List;
import java.util.Optional;
import com.sju.myapp.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>
{
  Optional<Login> findByEmail(String email);
}
