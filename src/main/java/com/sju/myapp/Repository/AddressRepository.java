package com.sju.myapp.Repository;

import com.sju.myapp.Model.Login;
import java.util.List;
import com.sju.myapp.Model.Address;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>
{
  List<Address> findByCountry(String country);
}
